package com.parsing.gosuslug.parsgosuslug.controller;


import com.parsing.gosuslug.parsgosuslug.dao.Record;
import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@RestController
public class RecordController {


    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet("Просто лист");
    List<Record> dataList = fillData();
    int rowNum = 0;






    Row row1 = sheet.createRow(rowNum);

    {
        row1.createCell(0).setCellValue("Заказчик");
        row1.createCell(1).setCellValue("№");
        row1.createCell(2).setCellValue("Дата Приема заявок");
        row1.createCell(3).setCellValue("Сумма");
        row1.createCell(4).setCellValue("Ссылка");

        for (Record dataModel : dataList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }


        try (FileOutputStream out = new FileOutputStream(new File("File1.xls"))) {
            workbook.write(out);
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }



//    @Autowired
    private List<Record> fillData() {

        List<Record> dataModels = new ArrayList<>();

        ParsingSite parsingSite = new ParsingSite();

        List<String> consumerList = new ArrayList<>(parsingSite.getConsumerList());
        List<String> numberList1 = new ArrayList<>(parsingSite.getNumberList());
        List<String> dataList1 = new ArrayList<>(parsingSite.getDataList());
        List<String> urlList = new ArrayList<>(parsingSite.getUrlList());
        List<String> moneyList1 = new ArrayList<>(parsingSite.getMoneyList());


        for (int i = 0; i < consumerList.size(); i++) {
            dataModels.add(new Record(consumerList.get(i), numberList1.get(i),dataList1.get(i),moneyList1.get(i),"http://zakupki.gov.ru" + urlList.get(i)));

        }

        return dataModels;
    }

    private static void createSheetHeader(HSSFSheet sheet, int rowNum, Record dataModel) {
        Row row = sheet.createRow(rowNum);

        row.createCell(0).setCellValue(dataModel.getCustomer());
        row.createCell(1).setCellValue(dataModel.getNumber());
        row.createCell(2).setCellValue(dataModel.getData());
        row.createCell(3).setCellValue(dataModel.getMoney());
        row.createCell(4).setCellValue(dataModel.getUrl());
    }


}


