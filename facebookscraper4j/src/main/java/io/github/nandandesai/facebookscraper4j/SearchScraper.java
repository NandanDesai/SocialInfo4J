package io.github.nandandesai.facebookscraper4j;

import io.github.nandandesai.facebookscraper4j.models.MiniProfile;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class SearchScraper {

    List<MiniProfile> searchUsers(String query, Map<String, String> cookies, Proxy proxy) throws IOException {
        if (query == null || query.equals("") || cookies == null) {
            throw new IllegalArgumentException("\"query\" or \"cookies\" cannot be null or empty");
        }

        String encodedQuery=Utils.urlEncode(query);

        String url="https://m.facebook.com/search/people/?q="+encodedQuery;

        Connection.Response response = Utils.getJsoupConnection(url, proxy)
                .method(Connection.Method.GET)
                .cookies(cookies)
                .execute();

        Document doc=response.parse();

        //System.out.println(doc);
        List<MiniProfile> searchResults=new ArrayList<>();

        Element resultEl=doc.getElementById("BrowseResultsContainer");

        if(resultEl==null){
            return searchResults;
        }

        Elements presEls=resultEl.getElementsByAttributeValue("role", "presentation");

        for(Element presEl:presEls){
            try {
                String username="";
                String name="";
                String profilePicUrl="";
                String description="";

                Elements tdEls = presEl.getElementsByTag("td");
                Element imgTdEl = tdEls.get(0);
                Element nameTdEl = tdEls.get(1);

                Element imgEl = imgTdEl.getElementsByTag("img").first();

                profilePicUrl=imgEl.attr("src");

                Element aEl=nameTdEl.getElementsByTag("a").first();
                username=Utils.getProperUsername(aEl.attr("href"));

                Elements divEls=aEl.children();
                name=divEls.get(0).text();

                String desc1=divEls.get(1).text();
                String desc2=divEls.get(2).text();

                if(!desc1.equalsIgnoreCase("")){
                    description=description+desc1;
                }
                if(!desc2.equalsIgnoreCase("")){
                    description=description+", "+desc2;
                }

                searchResults.add(new MiniProfile(username, name, profilePicUrl, description));
            }catch (NullPointerException|IndexOutOfBoundsException e){}
        }

        return searchResults;
    }




}
