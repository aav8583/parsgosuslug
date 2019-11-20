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
public class ParsingMoney implements ParsingSite{

    @Autowired
    @Qualifier("ParsingUrl")
    ParsingUrl parsingUrl;

    private Document doc;
    private Element element;
    private Elements elements;
    private List<String> moneyList = new ArrayList<>();

    public ParsingMoney() {
    }

    @PostConstruct
    public void giveBean() {
        parsing();
    }

    @Override
    public List<String> parsing() {
        try {
            for (String str : parsingUrl.getUrlList()) {
                String url = "http://zakupki.gov.ru" + str;
                doc = Jsoup.connect(url).get();
                elements = doc.getElementsByTag("td");
                for (int j = 0; j < elements.size(); j++) {
                    if (elements.get(j).text().equals("Начальная (максимальная) цена контракта")) {
                        element = elements.get(j + 1);
                        String s = element.text();
                        moneyList.add(s);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moneyList;
    }
}
