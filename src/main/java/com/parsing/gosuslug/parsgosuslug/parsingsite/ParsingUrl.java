package com.parsing.gosuslug.parsgosuslug.parsingsite;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.parsing.gosuslug.parsgosuslug.controller.RecordController;
import lombok.Data;
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
public class ParsingUrl implements ParsingSite {

    private Document doc;
    private Element element;
    private Elements elements;
    private String url;
    private List<String> urlList = new ArrayList<>();
    private String link;
    private int counter = 0;
    RecordController rc = new RecordController();


    public ParsingUrl(){
    }

//    @PostConstruct
//    public void giveBean() {
////        parsing();
//    }



    @PostConstruct
    @Override
    public List<String> parsing(){
        try {
            Configuration.browser = "chrome";
            Configuration.headless = true;
            File file = new File("link.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            link = br.readLine();
            Selenide.open(link);
            ElementsCollection elCol = Selenide.$$x("//*[contains(text(),'Подача заявок')]/..//a");
            elCol.forEach(selenideElement -> {
                urlList.add(selenideElement.attr("href"));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urlList;
    }

}
