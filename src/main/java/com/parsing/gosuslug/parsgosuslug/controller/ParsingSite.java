package com.parsing.gosuslug.parsgosuslug.controller;


import com.parsing.gosuslug.parsgosuslug.parsingsite.*;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


//selenium   selenid

@Data
@RestController
public class ParsingSite {


    @Autowired
    ParsingUrl parsingUrl;

    @Autowired
    ParsingConsumer parsingConsumer;

    @Autowired
    ParsingMoney parsingMoney;

    @Autowired
    ParsingData parsingData;

    @Autowired
    ParsingNumber parsingNumber;

    private Document doc;
    private Element element;
    private Elements elements;
    private String url;


    private List<String> urlList;
    private List<String> consumerList;
    private List<String> moneyList;
    private List<String> dataList;
    private List<String> numberList;

    private List<List> megaList;


    public ParsingSite() {

    }


    @PostConstruct
    public void methodTest() {
        urlList = new ArrayList<>(parsingUrl.getUrlList());
        consumerList = new ArrayList<>(parsingConsumer.getConsumerList());
        dataList = new ArrayList<>(parsingData.getDataList());
        moneyList = new ArrayList<>(parsingMoney.getMoneyList());
        numberList = new ArrayList<>(parsingNumber.getNumberList());
    }


}
