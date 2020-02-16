# SocialInfo4J
![](https://static.xx.fbcdn.net/rsrc.php/v3/ya/r/O2aKM2iSbOw.png)  ![](https://static-exp1.licdn.com/scds/common/u/images/logos/favicons/v1/favicon.ico)  ![](https://www.instagram.com/static/images/ico/favicon-192.png/68d99ba29cc8.png)

This repository contains a collection of Java libraries which can be used to fetch data from popular social networks like Facebook, Instagram and LinkedIn. There is a [separate repository](https://github.com/NandanDesai/TwitterScraper4J) of mine to fetch data from Twitter.

***The libraries I am releasing in this repo are for educational and research purposes only. I have not provided the JAR files. You will have to build it yourself. Also, this repo is NOT affiliated to Facebook, Instagram and LinkedIn. This is my personal research project.***

This repo shows different ways with which data can be fetched from an internet service. Official public API is not the only way. Data can be fetched by easily scraping the legacy sites (so that we'll get plain old HTML pages without any JavaScript jargon). Data can also be fetched by using the private APIs, i.e., the API which are meant to be accessed by the Android/iOS apps. By emulating an Android/iOS device through our code, we can easily hit requests to the private API and that spits out the data.

There are many Python libraries that do this kind of stuff but here are my Java libraries to fill up that space!

## FacebookScraper4J
This library uses Facebook's legacy page to scrape data.

#### Code examples

 1. Login to facebook
```java 
FacebookScraper facebookScraper=FacebookScraper.builder().username(email).password(password).getInstance();
//You can also use a Proxy. Just use builder().proxy(<proxy instance>)
```
 2. Get profile information
```java 
//this method takes username or userID of your target profile
Profile profile=facebookScraper.getProfile("zuck"); 
```
 3. Get a list of all the friends of a user
```java 
Iterator<List<String>> it=facebookScraper.getFriends(username);
while(it.hasNext()){
    List<String> friendsUsernames=it.next();
    System.out.println(friendsUsernames);
}
```
 4. Get photos of a user
```java 
List<Photo> photos=facebookScraper.getPhotos("zuck");
```
 5. Search a user
```java 
List<MiniProfile> searchResults=facebookScraper.searchUsers("your search query"));
```
 ## InstagramWrapper4J
 
 This library is a wrapper around [instagram4j](https://github.com/brunocvcunha/instagram4j) library which uses Instagram's private API to fetch the data. I have stripped down that library significantly and removed features like Liking a post, Commenting on a post, DMs etc. The goal of my wrapper library is only to fetch the data and not to modifying anything on the server. I have also used OkHttp instead of Apache HttpComponents to make the library Android-friendly.

#### Code examples

 1. Login to Instagram
```java 
Instagram instagram=Instagram.builder().username("your username").password("your password").getInstance();
```
 2. Get profile information
```java 
Profile profile=instagram.getProfile("<username of the target>");
```
 3. Get user's timeline (photos and videos)
```java 
UserTimeline userTimeline =instagram.getUserTimeline("<username of the target>");
```
 4. Search a user
```java 
List<MiniProfile> searchResults =instagram.searchUsers("search query");
```

## LinkedIn4J

This library uses LinkedIn's private API to fetch data.

#### Code examples

 1. Login to LinkedIn
```java 
LinkedIn linkedIn=LinkedIn.builder().username("your email").password("your password").getInstance();
```
 2. Get profile information
```java 
Profile profile=linkedIn.getProfile("<username or publicID of the target>");
```
 3. Search a user
```java 
List<MiniProfile> searchResults=linkedIn.searchProfile("your search query");
```
## TwitterScraper4J
You can visit my [another repo](https://github.com/NandanDesai/TwitterScraper4J) for this.

## License
![CC BY-NC-SA license](https://mirrors.creativecommons.org/presskit/buttons/88x31/png/by-nc-sa.png)

The entire code in this repo is released under [Creative Commons BY-NC-SA 4.0 license](https://creativecommons.org/licenses/by-nc-sa/4.0/). "This license lets others remix, tweak, and build upon your work non-commercially, as long as they credit you and license their new creations under the identical terms."

