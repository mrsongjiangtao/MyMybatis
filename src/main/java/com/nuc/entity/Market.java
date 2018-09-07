package com.nuc.entity;

import java.util.List;

public class Market {
    private String market_id;
    private String market_name;
    private String market_address;
    private List<Customer> customer;

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public String getMarket_address() {
        return market_address;
    }

    public void setMarket_address(String market_address) {
        this.market_address = market_address;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public Market(){}
    public Market(String market_id,String market_name,String market_address,List<Customer> customer){
        this.market_id = market_id;
        this.market_name = market_name;
        this.market_address = market_address;
        this.customer = customer;
    }
    public Market(String market_id,String market_name,String market_address){
        this.market_id = market_id;
        this.market_name = market_name;
        this.market_address = market_address;
    }

    @Override
    public String toString() {
        return "Market{" +
                "market_id='" + market_id + '\'' +
                ", market_name='" + market_name + '\'' +
                ", market_address='" + market_address + '\'' +
                ", customer=" + customer +
                '}';
    }
}
