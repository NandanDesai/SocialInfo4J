package io.github.nandandesai.facebookscraper4j;

import io.github.nandandesai.facebookscraper4j.exceptions.FacebookException;
import io.github.nandandesai.facebookscraper4j.models.Education;
import io.github.nandandesai.facebookscraper4j.models.Employment;
import io.github.nandandesai.facebookscraper4j.models.FamilyMember;
import io.github.nandandesai.facebookscraper4j.models.Profile;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

class ProfileScraper {
    Profile getProfile(String username, Map<String, String> cookies, Proxy proxy) throws IOException, FacebookException {
        if (username == null || username.equals("") || cookies == null) {
            throw new IllegalArgumentException("\"username\" or \"cookies\" cannot be null or empty");
        }
        String url="";
        if(username.matches("\\d+")){
            url="https://m.facebook.com/profile.php?id="+username;
        }else{
            url="https://m.facebook.com/"+username;
        }

        Connection.Response response = Utils.getJsoupConnection(url, proxy)
                .method(Connection.Method.GET)
                .cookies(cookies)
                .execute();

        if (response.statusCode() == 404) {
            throw new FacebookException("Profile not found for username: " + username, response.statusCode());
        } else if (response.statusCode() != 200) {
            throw new FacebookException(response.statusMessage(), response.statusCode());
        } else {
            Document doc = response.parse();
            //System.out.println(doc);


            //String username;
            String name = doc.title();
            String currentCity = "";
            String hometown = "";
            String gender = "";
            String relationshipStatus = "";
            List<String> languages=new ArrayList<>();

            Date dateOfBirth = null;

            List<Education> educationList = new ArrayList<>();
            List<Employment> employmentList = new ArrayList<>();
            List<FamilyMember> familyMembers = new ArrayList<>();

            //get current city and hometown
            Element livingEl = doc.getElementById("living");
            if (livingEl != null) {
                Elements tableEls = livingEl.getElementsByTag("table");
                for (Element tableEl : tableEls) {
                    Elements tdEls = tableEl.getElementsByTag("td");
                    try {
                        String key = tdEls.get(0).text();
                        String value = tdEls.get(1).text();

                        if (key.equalsIgnoreCase("Current City")) {
                            currentCity = value;
                        } else if (key.equalsIgnoreCase("Home Town")) {
                            hometown = value;
                        }
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                    }
                }
            }

            //get gender, languages and dob
            Element basicInfoEl = doc.getElementById("basic-info");
            if (basicInfoEl != null) {
                //Elements tableEls=basicInfoEl.getElementsByAttributeValueMatching("class","e\\D");
                Elements tableEls = basicInfoEl.getElementsByTag("table");
                for (Element tableEl : tableEls) {
                    Elements tdEls = tableEl.getElementsByTag("td");
                    try {
                        String key = tdEls.get(0).text();
                        String value = tdEls.get(1).text();

                        if (key.equalsIgnoreCase("Birthday") || key.equalsIgnoreCase("Year of Birth")) {
                            dateOfBirth=getDate(value);
                        } else if (key.equalsIgnoreCase("Gender")) {
                            gender = value;
                        } else if (key.equalsIgnoreCase("Languages")) {
                            String[] langs = value.split(",|\\sand\\s");
                            for (String lang : langs) {
                                languages.add(lang.trim());
                            }
                        }
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                    }
                }
            }

            //get relationship status
            Element relationshipEl = doc.getElementById("relationship");
            if (relationshipEl != null) {
                try {
                    Element relationshipDiv = relationshipEl.child(0).child(1);
                    relationshipStatus = relationshipDiv.text();
                    if (relationshipStatus.contains("Married")) {
                        Element spouseEl = relationshipDiv.getElementsByTag("a").first();
                        String spouseLink = spouseEl.attr("href");
                        String spouseUsername = Utils.getProperUsername(spouseLink);
                        familyMembers.add(new FamilyMember(spouseUsername, "Spouse"));
                    }
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                }
            }

            //get education list
            Element educationEl = doc.getElementById("education");
            if (educationEl != null) {
                Elements edEls = educationEl.getElementsByAttributeValueMatching("id", "u_._.");
                for (Element edEl : edEls) {
                    String schoolName = "";
                    String schoolType = "";
                    List<String> classmates = new ArrayList<>();

                    try {
                        schoolName = edEl.getElementsByTag("span").get(0).text();
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                    }

                    try {
                        Element schoolTypeSpanEl = edEl.getElementsByTag("span").get(1);
                        String text=schoolTypeSpanEl.text();
                        if(!containsDate(text)) {
                            schoolType = text;
                        }
                    } catch (NullPointerException | IndexOutOfBoundsException e) {
                    }

                    //scrape classmates
                    try {
                        Element classmatesEl = edEl.getElementsByTag("li").first();
                        String classmateText = classmatesEl.text();
                        if (classmateText.contains("with") && classmateText.matches(".*\\s\\d+\\sothers")) {
                            Elements aTags = classmatesEl.getElementsByTag("a");
                            for (Element aTag : aTags) {
                                if (aTag.text().matches("\\d+\\sothers")) {
                                    String classmatesLink = aTag.attr("href");
                                    classmates = scrapeClassmates(classmatesLink, cookies, proxy);
                                    break;
                                }
                            }
                        } else if (classmateText.contains("with")) {
                            Elements aTags = classmatesEl.getElementsByTag("a");
                            for (Element aTag : aTags) {
                                String classmatesLink = aTag.attr("href");
                                classmates.add(Utils.getProperUsername(classmatesLink));
                            }

                        }
                    } catch (NullPointerException npe) {
                    }
                    educationList.add(new Education(schoolName, schoolType, classmates));
                }

            }
            //get employment list
            Element employmentEl = doc.getElementById("work");
            if (employmentEl != null) {
                Elements emEls = employmentEl.getElementsByAttributeValueMatching("id", "u_._.");
                for (Element emEl : emEls) {
                    String companyName = "";
                    String role = "";
                    Date startDate = null;
                    Date endDate = null;

                    try {
                        companyName = emEl.getElementsByTag("span").get(0).text();
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                    }

                    try {
                        String text=emEl.getElementsByTag("span").get(1).text();
                        if(!containsDate(text)) {
                            role = text;
                        }else{
                            String dateString = text;
                            String[] dates = dateString.split("-");
                            String fromDate = dates[0].trim();
                            String toDate = dates[1].trim();
                            startDate=getDate(fromDate);
                            endDate=getDate(toDate);
                        }
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                    }

                    try {
                        String dateString = emEl.getElementsByTag("span").get(2).text();
                        if(containsDate(dateString)) {
                            String[] dates = dateString.split("-");
                            String fromDate = dates[0].trim();
                            String toDate = dates[1].trim();
                            startDate = getDate(fromDate);
                            endDate = getDate(toDate);
                        }
                    } catch (IndexOutOfBoundsException | NullPointerException e) {
                    }
                    employmentList.add(new Employment(companyName, role, startDate, endDate));
                }
            }

            //get family members
            Element familyEl = doc.getElementById("family");
            if (familyEl != null) {
                Elements h3Els=familyEl.getElementsByTag("h3");
                for(int i=0; i<h3Els.size(); i=i+2){
                    try{
                        String famUsername=h3Els.get(i).child(0).attr("href");
                        famUsername=Utils.getProperUsername(famUsername);
                        String relation=h3Els.get(i+1).text();
                        familyMembers.add(new FamilyMember(famUsername, relation));
                    }catch (NullPointerException|IndexOutOfBoundsException e){}
                }
            }
            return new Profile(username, name, currentCity, hometown, gender, relationshipStatus, languages, dateOfBirth, educationList, employmentList, familyMembers);
        }
    }

    private List<String> scrapeClassmates(String url, Map<String, String> cookies, Proxy proxy) {
        try {
            //trying to simulate human behavior
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> classmates = new ArrayList<>();
        try {
            Connection.Response response = Utils.getJsoupConnection("https://m.facebook.com" + url, proxy)
                    .method(Connection.Method.GET)
                    .cookies(cookies)
                    //.header("Referer","https://m.facebook.com")
                    .execute();
            if (response.statusCode() == 200) {
                Document doc = response.parse();
                Elements nameEls = doc.getElementsByTag("span");
                if (nameEls != null) {
                    for (Element nameEl : nameEls) {
                        try {
                            String classmateUsernameLink = nameEl.parent().attr("href");
                            String classmateUsername = Utils.getProperUsername(classmateUsernameLink);
                            classmates.add(classmateUsername);
                        } catch (NullPointerException npe) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classmates;
    }

    private boolean containsDate(String text){
        if(text.matches(".*\\d{4}.*") || text.matches(".*(January|February|March|April|May|June|July|August|September|October|November|December).*")){
            return true;
        }
        return false;
    }

    private Date getDate(String text){
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM yyyy");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf4 = new SimpleDateFormat("dd MMMM");
        Date date=null;
        try {
            date = sdf1.parse(text);
        } catch (ParseException e) {
            try {
                date = sdf2.parse(text);
            } catch (ParseException e1) {
                try {
                    date = sdf3.parse(text);
                } catch (ParseException e3) {
                    try {
                        date = sdf4.parse(text);
                    } catch (ParseException e4) {
                    }
                }
            }
        }
        return date;
    }
}
