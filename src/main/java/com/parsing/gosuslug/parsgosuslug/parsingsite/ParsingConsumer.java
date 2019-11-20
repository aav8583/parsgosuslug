package com.parsing.gosuslug.parsgosuslug.parsingsite;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ParsingConsumer implements ParsingSite {

    @Autowired
    @Qualifier("ParsingUrl")
    ParsingUrl parsingUrl;

    private Document doc;
    private Element element;
    private Elements elements;
    private String url;
    private List<String> consumerList = new ArrayList<>();

    @PostConstruct
    public void giveBean() {
        parsing();
    }

    public ParsingConsumer() {
    }

    @Override
    public List<String> parsing() {
        try {
            for (String str : parsingUrl.getUrlList()) {
                String url = "http://zakupki.gov.ru" + str;
                doc = Jsoup.connect(url).get();
                elements = doc.getElementsByTag("td");
                for (int j = 0; j < elements.size(); j++) {
                    if (elements.get(j).text().equals("Организация, осуществляющая размещение")) {
                        element = elements.get(j + 1);
                        String s = element.text();
                        consumerList.add(s);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return consumerList;
    }


}
