package com.nuc.entity;

public class SearchCustomer {
    private String cus_code;
    private String cus_address;
    private String cus_tel;
    private Integer cus_age;

    public String getCus_code() {
        return cus_code;
    }

    public void setCus_code(String cus_code) {
        this.cus_code = cus_code;
    }

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

    public SearchCustomer(){}

    public SearchCustomer(String cus_code, String cus_address, String cus_tel, Integer cus_age) {
        this.cus_code = cus_code;
        this.cus_address = cus_address;
        this.cus_tel = cus_tel;
        this.cus_age = cus_age;
    }

    @Override
    public String toString() {
        return "SearchCustomer{" +
                "cus_code='" + cus_code + '\'' +
                ", cus_address='" + cus_address + '\'' +
                ", cus_tel='" + cus_tel + '\'' +
                ", cus_age=" + cus_age +
                '}';
    }
}
