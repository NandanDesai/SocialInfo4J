package io.github.nandandesai.linkedin4j;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nandandesai.linkedin4j.models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class Mapper {

    static List<MiniProfile> mapMiniProfiles(String json) throws IOException {
        List<MiniProfile> miniProfiles=new ArrayList<>();
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        JsonNode elementsArray2=elementsArray.get(0).get("elements");
        for(int i=0; i<elementsArray2.size(); i++) {
            JsonNode imageJson=elementsArray2.get(i).get("image");
            JsonNode attributeJson=imageJson.get("attributes").get(0);
            JsonNode miniProfileJson=attributeJson.get("miniProfile");
            String firstName=miniProfileJson.get("firstName").asText();
            String lastName=miniProfileJson.get("lastName").asText();
            String name=firstName+" "+lastName;
            String occupation=miniProfileJson.get("occupation").asText();
            String publicIdentifier=miniProfileJson.get("publicIdentifier").asText();
            String tempProfilePicUrl="";
            try{
                JsonNode vectorImageJson=miniProfileJson.get("picture").get("com.linkedin.common.VectorImage");
                JsonNode artifactsArray=vectorImageJson.get("artifacts");
                String rootUrl=vectorImageJson.get("rootUrl").asText();
                String picUrl=artifactsArray.get(3).get("fileIdentifyingUrlPathSegment").asText();
                tempProfilePicUrl=rootUrl+picUrl;
            }catch (NullPointerException npe){
                //npe.printStackTrace();
            }
            miniProfiles.add(new MiniProfile(publicIdentifier, name, occupation, tempProfilePicUrl));
            //System.out.println(miniProfileJson.toString());
        }

        return miniProfiles;
    }

    static Profile mapProfile(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode positionGroupViewJson=jsonNode.get("positionGroupView");
        List<EmploymentPosition> employmentPositions=mapEmploymentPositions(positionGroupViewJson.toString());

        JsonNode honorViewJson=jsonNode.get("honorView");
        List<Honor> honors=mapHonors(honorViewJson.toString());

        JsonNode languageViewJson=jsonNode.get("languageView");
        List<Language> languages=mapLanguage(languageViewJson.toString());

        JsonNode publicationViewJson=jsonNode.get("publicationView");
        List<Publication> publications=mapPublication(publicationViewJson.toString());

        JsonNode educationViewJson=jsonNode.get("educationView");
        List<Education> educationList=mapEducation(educationViewJson.toString());

        JsonNode certificationViewJson=jsonNode.get("certificationView");
        List<Certification> certifications=mapCertification(certificationViewJson.toString());

        JsonNode projectViewJson=jsonNode.get("projectView");
        List<Project> projects=mapProject(projectViewJson.toString());

        JsonNode volunteerExperienceViewJson=jsonNode.get("volunteerExperienceView");
        List<VolunteerExperience> volunteerExperiences=mapVolunteerExperience(volunteerExperienceViewJson.toString());

        JsonNode skillViewJson=jsonNode.get("skillView");
        List<String> skills=mapSkillsOrCourses(skillViewJson.toString());

        JsonNode courseViewJson=jsonNode.get("courseView");
        List<String> courses=mapSkillsOrCourses(courseViewJson.toString());

        String name="";
        String location="";
        boolean isStudent=false;
        String industry="";
        String summary="";
        String headline="";

        //build the Profile object here

        JsonNode profileJson=jsonNode.get("profile");
        String firstName=profileJson.get("firstName").asText();
        String lastName=profileJson.get("lastName").asText();

        name=firstName+" "+lastName;
        location=profileJson.get("locationName").asText();
        isStudent=profileJson.get("student").asBoolean();
        industry=profileJson.get("industryName").asText();
        summary=profileJson.get("summary").asText();
        headline=profileJson.get("headline").asText();
        String publicIdentifier=profileJson.get("miniProfile").get("publicIdentifier").asText();
        Profile profile=new Profile(name, location, isStudent, industry, summary, headline, skills, courses, certifications, educationList,
                employmentPositions, honors, languages, projects, publications, volunteerExperiences);
        profile.setPublicIdentifier(publicIdentifier);
        return profile;
    }

    //this method can return skills or courses based on the json sent to it.
    private static List<String> mapSkillsOrCourses(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<String> items=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            items.add(elementJson.get("name").asText());
        }
        return items;
    }

    private static List<VolunteerExperience> mapVolunteerExperience(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<VolunteerExperience> volunteerExperiences=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String companyName="";
            String role="";
            Date startDate=null;
            Date endDate=null;
            String cause="";
            String description="";
            try{

            }catch (NullPointerException npe){}
            try{
                companyName=elementJson.get("companyName").asText();
            }catch (NullPointerException npe){}
            try{
                role=elementJson.get("role").asText();
            }catch (NullPointerException npe){}
            JsonNode timePeriodJson=elementJson.get("timePeriod");
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("startDate").get("year").asInt();
                int month=timePeriodJson.get("startDate").get("month").asInt();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                startDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("endDate").get("year").asInt();
                int month=timePeriodJson.get("endDate").get("month").asInt();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                endDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                cause=elementJson.get("cause").asText();
            }catch (NullPointerException npe){}
            try{
                description=elementJson.get("description").asText();
            }catch (NullPointerException npe){}
            volunteerExperiences.add(new VolunteerExperience(companyName, role, startDate, endDate, cause, description));
        }
        return volunteerExperiences;
    }

    private static List<Project> mapProject(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<Project> projects=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String title="";
            String description="";
            Date startDate=null;
            Date endDate=null;
            ArrayList<String> members=new ArrayList<>();
            try{
                title=elementJson.get("title").asText();
            }catch (NullPointerException npe){}
            try{
                description=elementJson.get("description").asText();
            }catch (NullPointerException npe){}
            JsonNode timePeriodJson=elementJson.get("timePeriod");
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("startDate").get("year").asInt();
                int month=timePeriodJson.get("startDate").get("month").asInt();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                startDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("endDate").get("year").asInt();
                int month=timePeriodJson.get("endDate").get("month").asInt();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                endDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                JsonNode membersArray=elementJson.get("members");
                for(int j=0; j<membersArray.size(); j++){
                    String memberName=membersArray.get(j).get("member").get("firstName").asText() +" "+
                            membersArray.get(j).get("member").get("lastName").asText();
                    members.add(memberName);
                }
            }catch (NullPointerException npe){}
            projects.add(new Project(title, description, startDate, endDate, members));
        }
        return projects;
    }

    private static List<Certification> mapCertification(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<Certification> certifications=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String name="";
            String authority="";
            Date startDate=null;
            Date endDate=null;
            String url="";
            try{
                name=elementJson.get("name").asText();
            }catch (NullPointerException npe){}
            try{
                authority=elementJson.get("authority").asText();
            }catch (NullPointerException npe){}
            JsonNode timePeriodJson=elementJson.get("timePeriod");
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("startDate").get("year").asInt();
                int month=timePeriodJson.get("startDate").get("month").asInt();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                startDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("endDate").get("year").asInt();
                int month=timePeriodJson.get("endDate").get("month").asInt();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                endDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                url=elementJson.get("url").asText();
            }catch (NullPointerException npe){}
            certifications.add(new Certification(name, authority, startDate, endDate, url));
        }
        return certifications;
    }

    private static List<Education> mapEducation(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<Education> educations=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String schoolName="";
            String degreeName="";
            String activities="";
            String grade="";
            Date startDate=null;
            Date endDate=null;
            String fieldOfStudy="";
            try{
                JsonNode schoolJson=elementJson.get("school");
                schoolName=schoolJson.get("schoolName").asText();
            }catch (NullPointerException npe){}
            try{
                degreeName=elementJson.get("degreeName").asText();
            }catch (NullPointerException npe){}
            try{
                activities=elementJson.get("activities").asText();
            }catch (NullPointerException npe){}
            try{
                grade=elementJson.get("grade").asText();
            }catch (NullPointerException npe){}
            try{
                grade=elementJson.get("grade").asText();
            }catch (NullPointerException npe){}
            JsonNode timePeriodJson=elementJson.get("timePeriod");
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("startDate").get("year").asInt();
                calendar.set(Calendar.YEAR, year);
                startDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                Calendar calendar=Calendar.getInstance();
                int year=timePeriodJson.get("endDate").get("year").asInt();
                calendar.set(Calendar.YEAR, year);
                endDate=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                fieldOfStudy=elementJson.get("fieldOfStudy").asText();
            }catch (NullPointerException npe){}
            educations.add(new Education(schoolName, degreeName, activities, grade, startDate, endDate, fieldOfStudy));
        }
        return educations;
    }

    private static List<Publication> mapPublication(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<Publication> publications=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String name="";
            String publisher="";
            String description="";
            String url="";
            Date date=null;
            ArrayList<String> authors=new ArrayList<>();
            try{
                name=elementJson.get("name").asText();
            }catch (NullPointerException npe){}
            try{
                publisher=elementJson.get("publisher").asText();
            }catch (NullPointerException npe){}
            try{
                description=elementJson.get("description").asText();
            }catch (NullPointerException npe){}
            try{
                url=elementJson.get("url").asText();
            }catch (NullPointerException npe){}
            try{
                JsonNode dateJson=elementJson.get("date");
                int day=dateJson.get("day").asInt();
                int month=dateJson.get("month").asInt();
                int year=dateJson.get("year").asInt();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, day);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
                date=calendar.getTime();
            }catch (NullPointerException npe){}
            try{
                //authors not done yet...
            }catch (NullPointerException npe){}
            publications.add(new Publication(name, publisher, description, url, date, authors));
        }
        return publications;
    }

    private static List<Language> mapLanguage(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<Language> languages=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String name=elementJson.get("name").asText();
            String proficiency=elementJson.get("proficiency").asText();
            languages.add(new Language(name,proficiency));
        }
        return languages;
    }

    private static List<Honor> mapHonors(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<Honor> honors=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String title="";
            String description="";
            String issuer="";
            int issueMonth=0;
            int issueYear=0;
            Date issueDate=null;
            try {
                title = elementJson.get("title").asText();
            }catch (NullPointerException npe){}
            try {
                description = elementJson.get("description").asText();
            }catch (NullPointerException npe){}
            try {
                issuer = elementJson.get("issuer").asText();
            }catch (NullPointerException npe){}
            try {
                issueMonth = elementJson.get("issueDate").get("month").asInt();
            }catch (NullPointerException npe){}
            try {
                issueYear = elementJson.get("issueDate").get("year").asInt();
            }catch (NullPointerException npe){}
            if(issueMonth!=0 || issueYear!=0) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, issueMonth);
                calendar.set(Calendar.YEAR, issueYear);
                issueDate = calendar.getTime();
            }
            honors.add(new Honor(title, description, issuer, issueDate));
        }
        return honors;
    }

    private static List<EmploymentPosition> mapEmploymentPositions(String json) throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode=objectMapper.readValue(json, JsonNode.class);
        JsonNode elementsArray=jsonNode.get("elements");
        ArrayList<EmploymentPosition> employmentPositions=new ArrayList<>();
        for(int i=0; i<elementsArray.size(); i++) {
            JsonNode elementJson = elementsArray.get(i);
            String companyName=elementJson.get("name").asText();
            JsonNode positionsArray=elementJson.get("positions");
            ArrayList<EmploymentPosition.Position> positions=new ArrayList<>();
            for(int j=0; j<positionsArray.size(); j++){
                JsonNode positionJson=positionsArray.get(j);
                String location="";
                String title="";
                String description="";
                Date startDate=null;
                Date endDate=null;
                try {
                    location = positionJson.get("locationName").asText();
                }catch (NullPointerException npe){}

                try {
                    title = positionJson.get("title").asText();
                }catch (NullPointerException npe){}

                try {
                    description = positionJson.get("description").asText();
                }catch (NullPointerException npe){}

                JsonNode timePeriodJson=positionJson.get("timePeriod");
                int startMonth=0;
                int startYear=0;
                try {
                    startMonth = timePeriodJson.get("startDate").get("month").asInt();
                }catch (NullPointerException npe){}
                try {
                    startYear = timePeriodJson.get("startDate").get("year").asInt();
                }catch (NullPointerException npe){}
                if(startMonth!=0 || startYear!=0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, startMonth);
                    calendar.set(Calendar.YEAR, startYear);
                    startDate = calendar.getTime();
                }

                int endMonth=0;
                int endYear=0;
                try {
                    endMonth = timePeriodJson.get("endDate").get("month").asInt();
                }catch (NullPointerException npe){}
                try {
                    endYear = timePeriodJson.get("endDate").get("year").asInt();
                }catch (NullPointerException npe){}
                if(endYear!=0 || endMonth!=0) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.MONTH, endMonth);
                    calendar.set(Calendar.YEAR, endYear);
                    endDate = calendar.getTime();
                }

                positions.add(new EmploymentPosition.Position(location, title, description, startDate, endDate));
            }
            employmentPositions.add(new EmploymentPosition(companyName, positions));
        }
        return employmentPositions;
    }
}
