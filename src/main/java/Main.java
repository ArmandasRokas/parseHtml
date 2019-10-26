import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://hivetool.net/hive_data.shtml").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Title: " + doc.title());

        Element content = doc.getElementById("green");
     //   System.out.println(content.toString());
        ArrayList<Hive> hives = new ArrayList<Hive>();

        while(content != null){
            Elements links = content.getElementsByTag("a");
            String[] arrOfStr = links.get(0).attr("href").split("=", 2);
            Hive hive = new Hive();
            hive.setId(Integer.valueOf(arrOfStr[1]));
            hive.setName(links.get(0).text());
            content = content.nextElementSibling();
            hives.add(hive);
        }

        for (Hive hive :
                hives) {
            System.out.println(hive.toString());
        }



      //  Element next = content.nextElementSibling();
      //  Elements nextLinks = next.getElementsByTag("a");

      //  System.out.println(nextLinks.get(0).attr("href") + " " + nextLinks.get(0).text());
        //Elements newsHeadlines = doc.select("#mp-itn b a");
        //System.out.println(newsHeadlines.toString());
        /*for (Element headline : newsHeadlines) {
            System.out.println(String.format("%s\n\t%s",
                    headline.attr("title"), headline.absUrl("href")));
        }
        */
    }
}
