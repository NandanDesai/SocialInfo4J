package io.github.nandandesai.facebookscraper4j;

import io.github.nandandesai.facebookscraper4j.exceptions.FacebookException;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


class FriendsIterator implements Iterator<List<String>> {
    private String username;
    private String nextUrl=null;
    private Map<String, String> cookies;
    private Document doc;
    private Proxy proxy;

    public FriendsIterator(String username, Map<String, String> cookies, Proxy proxy) {
        if (username == null || username.equals("") || cookies == null) {
            throw new IllegalArgumentException("\"username\" or \"cookies\" cannot be null or empty");
        }
        this.cookies=cookies;
        this.proxy=proxy;
        this.username=username;
        if(username.matches("\\d+")){
            nextUrl="https://m.facebook.com/profile.php?id="+username+"&v=friends";
        }else{
            nextUrl="https://m.facebook.com/"+username+"/friends";
        }
    }

    @Override
    public boolean hasNext() {
        if(nextUrl!=null){
            try {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Connection.Response response = Utils.getJsoupConnection(nextUrl, proxy)
                        .method(Connection.Method.GET)
                        .cookies(cookies)
                        .execute();

                if (response.statusCode() == 404) {
                    throw new FacebookException("Profile not found for username: " + username, response.statusCode());
                } else if (response.statusCode() != 200) {
                    throw new FacebookException(response.statusMessage(), response.statusCode());
                } else {
                    doc=response.parse();
                }
            }catch (FacebookException e){
                e.printStackTrace();
                return false;
            }
            catch (IOException e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<String> next() {
        List<String> friends=scrapeFriends(doc);
        Element moreFriendsEl=doc.getElementById("m_more_friends");
        if(moreFriendsEl!=null){
            nextUrl="https://m.facebook.com"+moreFriendsEl.getElementsByTag("a").first().attr("href");
        }else{
            nextUrl=null;
        }
        return friends;
    }

    private List<String> scrapeFriends(Document doc){
        List<String> friends=new ArrayList<>();

        Elements presEls=doc.getElementsByAttributeValue("role","presentation");
        for(Element presEl:presEls){
            try {
                String username = presEl.getElementsByTag("a").first().attr("href");
                username=Utils.getProperUsername(username);
                if(!username.equalsIgnoreCase("home.php") && !username.equalsIgnoreCase("pagescreate")) {
                    friends.add(username);
                }
            }catch (NullPointerException e){}
        }
        return friends;
    }
}
