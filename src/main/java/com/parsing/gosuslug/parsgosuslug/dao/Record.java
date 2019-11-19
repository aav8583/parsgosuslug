package com.parsing.gosuslug.parsgosuslug.dao;


import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class Record {


    private String customer;
    private String number;
    private String data;
    private String money;
    private String url;

    public Record() {
    }




    public Record(String customer, String number, String data, String money,String url) {
        this.customer = customer;
        this.number = number;
        this.data = data;
        this.money = money;
        this.url = url;
    }
}
