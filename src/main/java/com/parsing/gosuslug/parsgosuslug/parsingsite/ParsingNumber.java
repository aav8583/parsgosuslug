//package com.parsing.gosuslug.parsgosuslug.parsingsite;
//
//import lombok.Data;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Component
//public class ParsingNumber implements ParsingSite{
//
//    private Document doc;
//    private Element element;
//    private Elements elements;
//    private String url;
//    private List<String> numberList = new ArrayList<>();
//
//
//    @Override
//    public List<String> parsing() {
//        return null;
//    }
//
//    @Autowired
//    @Override
//    public List<String> parsingWithUrl(ParsingUrl parsingUrl) {
//        System.err.println("From parsingNumber");
//        try {
//            for (String str : parsingUrl.parsing()) {
//                String url = "http://zakupki.gov.ru" + str;
//                doc = Jsoup.connect(url).get();
//                elements = doc.getElementsByTag("h1");
//                element = elements.first();
//                String s = element.text();
//                numberList.add(s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return numberList;
//    }
//}
