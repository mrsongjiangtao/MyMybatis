package com.nuc.mapper;

import com.nuc.entity.Customer;
import com.nuc.entity.SearchCustomer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchCustomerMapper {
    public List<Customer> selectByCondition(SearchCustomer searchCustomer);
    public List<Customer> selectByConditionTrim(SearchCustomer searchCustomer);
//    使用choose查询顾客信息，要求通过code查询，
//    如果code不存在，则通过address查询，如果address不存在，则查询所有年龄大于18岁的顾客
    public List<Customer> selectByConditionChose(SearchCustomer searchCustomer);

    //根据条件进行更新客户信息，如果条件有值，则更新该字段，否则不更新该字段
    public void updateCustomer(Customer customer);

    //单条件多值查询,例如，查询北京，太原的顾客信息
    public List<Customer> selectByConditionAll(@Param("address") List<String> address);

    //批量添加
    public void insertMore(@Param("customers") List<Customer> customers);

    //批量删除
    public void deleteMore(@Param("cus_id") List<String> cus_id);
    //批量修改
    public void updateMore(@Param("customers") List<Customer> customers);
}
