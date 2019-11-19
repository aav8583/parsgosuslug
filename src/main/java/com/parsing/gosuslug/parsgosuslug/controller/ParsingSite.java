package com.parsing.gosuslug.parsgosuslug.controller;


import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//selenium   selenid

@Data
@RestController
public class ParsingSite {

//    @Autowired
//    ParsingUrl parsingUrl;
//
//    @Autowired
//    ParsingConsumer parsingConsumer;
//
//    @Autowired
//    ParsingMoney parsingMoney;
//
//    @Autowired
//    ParsingData parsingData;
//
//    @Autowired
//    ParsingNumber parsingNumber;

    private Document doc;
    private Element element;
    private Elements elements;
    private String url;
    private List<String> urlList = new ArrayList<>();
    private List<String> consumerList = new ArrayList<>();
    private List<String> moneyList = new ArrayList<>();
    private List<String> dataList = new ArrayList<>();
    private List<String> numberList = new ArrayList<>();





        {
            try {
                File file = new File("link.txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader br = new BufferedReader(fileReader);
                String link = br.readLine();
                System.out.println(link);
//                doc = Jsoup.connect("http://zakupki.gov.ru/epz/order/extendedsearch/results.html?morphology=on&pageNumber=1&sortDirection=false&recordsPerPage=_10&showLotsInfoHidden=false&fz44=on&fz223=on&sortBy=UPDATE_DATE&af=on&publishDateFrom=+&currencyIdGeneral=-1&customerPlaceWithNested=on&customerPlace=5277335%2C5277327&customerPlaceCodes=77000000000%2C50000000000&delKladrIdsWithNested=on&contractStageList_0=on&contractStageList_1=on&contractStageList_2=on&contractStageList_3=on&contractStageList=0%2C1%2C2%2C3&contractPriceCurrencyId=-1").get();
                doc = Jsoup.connect(link).get();
                elements = doc.getElementsByAttributeValue("class", "registry-entry__header-top__number");
                elements.forEach(elements -> {
                    element = elements.child(0);
                    url = element.attr("href");
                    urlList.add(url);
                });
                ParsingSiteTest parsingSiteTest = new ParsingSiteTest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        class ParsingSiteTest {

            Document doc;
            Element element;
            Elements elements;

            public ParsingSiteTest() {
                addElements(consumerList,"Организация, осуществляющая размещение");
                addNumber(numberList);
                addMoney(moneyList);
                addElementsData(dataList);
            }



            public List<String> addElementsData(List<String> list) {
                {
                    try {
                        for (String str : urlList) {
                            String url = "http://zakupki.gov.ru" + str;
                            doc = Jsoup.connect(url).get();
                            elements = doc.getElementsByTag("td");
                            for (int j = 0; j < elements.size(); j++) {
                                if (elements.get(j).text().equals("Дата и время начала срока подачи заявок") || elements.get(j).text().equals("Дата и время начала подачи заявок")) {
                                    element = elements.get(j + 1);
                                    String s = element.text();
                                    list.add(s);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return list;
                }
            }

            public List<String> addElements(List<String> list, String strEnter) {
                try {
                    for (String str : urlList) {
                        String url = "http://zakupki.gov.ru" + str;
                        doc = Jsoup.connect(url).get();
                        elements = doc.getElementsByTag("td");
                        for (int j = 0; j < elements.size(); j++) {
                            if (elements.get(j).text().equals(strEnter)) {
                                element = elements.get(j + 1);
                                String s = element.text();
                                list.add(s);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return list;
            }

            public List<String> addNumber(List<String> list) {
                try {
                    for (String str : urlList) {
                        String url = "http://zakupki.gov.ru" + str;
                        doc = Jsoup.connect(url).get();
                        elements = doc.getElementsByTag("h1");
                        element = elements.first();
                        String s = element.text();
                                list.add(s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return list;
            }

            public List<String> addMoney(List<String> list) {
                try {
                    for (String str : urlList) {
                        String url = "http://zakupki.gov.ru" + str;
                        doc = Jsoup.connect(url).get();
                        elements = doc.getElementsByTag("td");
                        for (int j = 0; j < elements.size(); j++) {
                            if (elements.get(j).text().equals("Начальная (максимальная) цена контракта")) {
                                element = elements.get(j + 1);
                                String s = element.text();
                                list.add(s);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return list;
            }



        }
}
