package com.nuc.mapper;

import com.nuc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomerMapper{
    public void insertCus(Customer customer);
    public void deleteCus(Integer integer);
    public void updateCus(Customer customer);
    public List<Customer> selectCus();
    public Customer selectCusOne(String cus_id);
    //第一种多参传递
    public  List<Customer> selectByCon(String cus_code,String cus_name);
    public  List<Customer> selectByCon2(@Param("code") String cus_code, @Param("name") String cus_name);
    public  List<Customer> selectByCon3(Map<Object,Object> map);

    //模糊查询
    public List<Customer> selectByName(@Param("name") String cus_name);

    //关联查询
    public Customer selectCusMarByCusId(String cus_id);
    public Customer selectById(String cus_id);

    //一对多分布查询超市对应的顾客
    public Customer selectByMarketId(String market_id);
}
