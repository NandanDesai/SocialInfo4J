package io.github.nandandesai.facebookscraper4j;

import io.github.nandandesai.facebookscraper4j.exceptions.FacebookException;
import io.github.nandandesai.facebookscraper4j.models.Photo;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class PhotoScraper {
    private Map<String, String> cookies;
    private Proxy proxy;

    public PhotoScraper(Map<String, String> cookies, Proxy proxy) {
        this.cookies = cookies;
        this.proxy = proxy;
    }

    List<Photo> getAllPhotos(String username) throws IOException, FacebookException {
        if (username == null || username.equals("") || cookies == null) {
            throw new IllegalArgumentException("\"username\" or \"cookies\" cannot be null or empty");
        }
        String url="";
        if(username.matches("\\d+")){
            url="https://m.facebook.com/profile.php?id="+username+"&v=photos";
        }else{
            url="https://m.facebook.com/"+username+"/photos";
        }
        List<Photo> photos=new ArrayList<>();
        Connection.Response response = Utils.getJsoupConnection(url, proxy)
                .method(Connection.Method.GET)
                .cookies(cookies)
                .execute();
        if (response.statusCode() == 404) {
            throw new FacebookException("Profile not found for username: " + username, response.statusCode());
        } else if (response.statusCode() != 200) {
            throw new FacebookException(response.statusMessage(), response.statusCode());
        } else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Document doc=response.parse();
            try {
                Element photosContainerDiv = doc.getElementsByAttributeValue("title", "Uploads").first();
                Element photosDiv = photosContainerDiv.child(1);
                Element seeAllDiv=null;
                try {
                    seeAllDiv = photosContainerDiv.child(2);
                }catch (IndexOutOfBoundsException e){}

                if(seeAllDiv==null){
                    Elements aTags = photosDiv.getElementsByTag("a");
                    for (Element a : aTags) {
                        String fullPhotoUrl=a.attr("href");
                        if(!fullPhotoUrl.equalsIgnoreCase("")){
                            Photo photo=getPhoto(fullPhotoUrl);
                            if(photo!=null){
                                photos.add(photo);
                            }
                        }
                    }
                }else{
                    String allPhotosLink=seeAllDiv.getElementsByTag("a").attr("href");
                    Connection.Response allPhotosResponse = Utils.getJsoupConnection("https://m.facebook.com"+allPhotosLink, proxy)
                            .method(Connection.Method.GET)
                            .cookies(cookies)
                            .execute();

                    if(allPhotosResponse.statusCode()!=200){
                        return photos;
                    }

                    Document allPhotosDoc=allPhotosResponse.parse();
                    photos=getPhotos(allPhotosDoc);
                }
            }catch (NullPointerException|IndexOutOfBoundsException e){
                //e.printStackTrace();
            }
        }

        return photos;
    }

    private List<Photo> getPhotos(Document allPhotosDoc){
        List<Photo> photos=new ArrayList<>();
        try {
            Element tableEl = allPhotosDoc.getElementsByTag("table").last();
            Element divEl = tableEl.getElementsByTag("div").first();
            Elements aTags = divEl.children();
            for (Element a : aTags) {
                String fullPhotoUrl=a.attr("href");
                if(!fullPhotoUrl.equalsIgnoreCase("")){
                    Photo photo=getPhoto(fullPhotoUrl);
                    if(photo!=null){
                        photos.add(photo);
                    }
                }
            }
        }catch (NullPointerException | IndexOutOfBoundsException | IOException e){
            e.printStackTrace();
        }
        return photos;
    }

    private Photo getPhoto(String fullPhotoUrl) throws IOException {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Connection.Response fullPhotoResponse = Utils.getJsoupConnection("https://m.facebook.com"+fullPhotoUrl, proxy)
                .method(Connection.Method.GET)
                .cookies(cookies)
                .execute();

        if(fullPhotoResponse.statusCode()!=200){
            return null;
        }

        try {
            Document doc = fullPhotoResponse.parse();
            Element imgEl = doc.getElementsByTag("img").get(1);

            String photoUrl = imgEl.attr("src");

            Element mPhotoContent = doc.getElementById("MPhotoContent");
            String dateString = mPhotoContent.getElementsByTag("abbr").first().text();
            Date uploadedDate=null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            try {
                uploadedDate = sdf.parse(dateString);
            } catch (ParseException e1) {
                try{
                    dateString=dateString+" "+Calendar.getInstance().get(Calendar.YEAR);
                    uploadedDate = sdf.parse(dateString);
                }catch (ParseException e2){}
            }
            return new Photo(photoUrl, uploadedDate);
        }catch (NullPointerException e){
            return null;
        }
    }
}

