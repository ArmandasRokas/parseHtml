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


        Document doc = null;
        try {
            doc = Jsoup.connect("http://hivetool.net/db/hive_graph706.pl?chart=Temperature&new_hive_id="+hiveId+"&start_time=" + startTime+ "+23%3A59%3A59&end_time=" +endTime+ "+23%3A59%3A59&hive_id=240&number_of_days=7&last_max_dwdt_lbs_per_hour=30&weight_filter=Raw&max_dwdt_lbs_per_hour=&days=&begin=&end=&units=Metric&undefined=Skip&download_data=Download&download_file_format=csv").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elements = doc.getElementsByTag("div");
        Element e = elements.get(2);
        String[] lines = e.wholeText().split("\n");

        for(int i = 2; i<lines.length; i++){
            String[] line = lines[i].split(" ");
            if(line.length == 0){
                break;
            }
            String date =line[0];
            String[] raw_data = line[1].split(",");


            System.out.println(date + " " + raw_data[0]);
        }

    }

}
