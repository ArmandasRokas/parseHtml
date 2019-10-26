import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HiveTool {

    public static void main(String[] args) {

        int hiveId = 240;
        String startTime = "2019-10-19";
        String endTime = "2019-10-26";
        int numOfDays = 7;


        Document doc = null;
        try {
            doc = Jsoup.connect("http://hivetool.net/db/hive_graph706.pl?chart=Temperature&new_hive_id="+hiveId+"&start_time=" + startTime+ "+23%3A59%3A59&end_time=" +endTime+ "+23%3A59%3A59&hive_id="+hiveId+"&number_of_days=" +numOfDays+ "&last_max_dwdt_lbs_per_hour=30&weight_filter=Raw&max_dwdt_lbs_per_hour=&days=&begin=&end=&units=Metric&undefined=Skip&download_data=Download&download_file_format=csv").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elements = doc.getElementsByTag("div");
        Element e = elements.get(2);
        String[] lines = e.wholeText().split("\n");

        for(int i = 2; i<lines.length; i++){
            String[] raw_data = lines[i].split("(,|\\s)");
            if(raw_data.length == 0){
                break;
            }

            System.out.println(raw_data[0] + " " + raw_data[1] + " " + raw_data[2] );

        }

    }

}
