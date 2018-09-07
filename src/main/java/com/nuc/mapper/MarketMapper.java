package com.nuc.mapper;

import com.nuc.entity.Customer;
import com.nuc.entity.Market;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MarketMapper {
    //增删改查
    public void insertMarket(Market market);
    public void deleteMarket(String market_id);
    public void updateMarket(Market market);
    public List<Market> selectMarket();
    //通过market_name模糊查询market信息
    public List<Market> selectByNameList(@Param("name") String market_name);

    @MapKey("market_id")//赋值的是Market中的market_id(实体中的)，mybatis自动获取
    public Map<String,Market> selectByNameMap(@Param("name") String market_name);

    //通过market_name和address模糊查询market信息，返回list信息。要求使用map作为参数传递。
    public List<Market> selectByNameAdd(Map<Object,Object> map);

    //通过market_id查询market信息。
    public Market selectById(String market_id);

    //一对多集合嵌套映射
    public List<Customer> getCustomerByMarketId(String market_id);

    //一对多集合分步映射
    public Market selectAllCusById(String market_id);
}
