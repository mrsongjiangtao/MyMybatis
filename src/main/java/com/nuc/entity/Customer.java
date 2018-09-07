package com.nuc.entity;

import java.io.Serializable;

public class Customer implements Serializable {
    private String cus_id;
    private String cus_name;
    private String cus_code;
    private Market market;
    private String cus_address;
    private String cus_tel;
    private Integer cus_age;

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    public String getCus_tel() {
        return cus_tel;
    }

    public void setCus_tel(String cus_tel) {
        this.cus_tel = cus_tel;
    }

    public Integer getCus_age() {
        return cus_age;
    }

    public void setCus_age(Integer cus_age) {
        this.cus_age = cus_age;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_code() {
        return cus_code;
    }

    public void setCus_code(String cus_code) {
        this.cus_code = cus_code;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
    public Customer(){}
    public Customer(String cus_code,String cus_name,Market market){
        super();
        this.cus_code = cus_code;
        this.cus_name = cus_name;
        this.market = market;
    }

    public Customer(String cus_id, String cus_name, String cus_code,
                    Market market, String cus_address, String cus_tel,
                    Integer cus_age) {
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_code = cus_code;
        this.market = market;
        this.cus_address = cus_address;
        this.cus_tel = cus_tel;
        this.cus_age = cus_age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cus_id='" + cus_id + '\'' +
                ", cus_name='" + cus_name + '\'' +
                ", cus_code='" + cus_code + '\'' +
                ", market=" + market +
                ", cus_address='" + cus_address + '\'' +
                ", cus_tel='" + cus_tel + '\'' +
                ", cus_age=" + cus_age +
                '}';
    }
}
