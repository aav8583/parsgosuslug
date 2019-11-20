package com.parsing.gosuslug.parsgosuslug.parsingsite;

import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class ParsingUrl implements ParsingSite{

    private Document doc;
    private Element element;
    private Elements elements;
    private String url;
    private List<String> urlList = new ArrayList<>();

    public ParsingUrl() {
    }

    @PostConstruct
    public void giveBean() {
        parsing();
    }

    @Override
    public List<String> parsing() {
        try {
            File file = new File("link.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String link = br.readLine();
//                doc = Jsoup.connect("http://zakupki.gov.ru/epz/order/extendedsearch/results.html?morphology=on&pageNumber=1&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&fz44=on&fz223=on&sortBy=UPDATE_DATE&af=on&publishDateFrom=+&currencyIdGeneral=-1&customerPlaceWithNested=on&customerPlace=5277335%2C5277327&customerPlaceCodes=77000000000%2C50000000000&delKladrIdsWithNested=on&contractStageList_0=on&contractStageList_1=on&contractStageList_2=on&contractStageList_3=on&contractStageList=0%2C1%2C2%2C3&contractPriceCurrencyId=-1").get();
            doc = Jsoup.connect(link).get();
            elements = doc.getElementsByAttributeValue("class", "registry-entry__header-top__number");
            elements.forEach(elements -> {
                element = elements.child(0);
                url = element.attr("href");
                urlList.add(url);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  urlList;
    }

}
