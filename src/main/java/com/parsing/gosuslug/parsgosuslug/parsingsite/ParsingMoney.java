package com.parsing.gosuslug.parsgosuslug.parsingsite;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Data
@Component
public class ParsingMoney implements ParsingSite{

    @Autowired
    ParsingUrl parsingUrl;

    private Document doc;
    private Element element;
    private Elements elements;
    private List<String> moneyList = new ArrayList<>();

    public ParsingMoney() {
    }

    @PostConstruct
    @Override
    public List<String> parsing() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
            for (String str : parsingUrl.getUrlList()) {
                Selenide.open(str);
                String s = Selenide.$x("//h2[4]/../div[4]/table/tbody/tr/td[2]").text();
               moneyList.add(s);
        }
        return moneyList;
    }
}
