package com.nuc.test;

import com.nuc.entity.Customer;
import com.nuc.entity.Market;
import com.nuc.mapper.MarketMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketTest {
    static SqlSession sqlSession = null;
    public static MarketMapper config() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        MarketMapper mapper = sqlSession.getMapper(MarketMapper.class);
        return mapper;
    }
    @Test
    public void insertMarketTest() throws IOException {
        MarketMapper mapper = config();
        Market market = new Market(null,"nucMarket","taiyuan");
        mapper.insertMarket(market);
        sqlSession.close();
    }
    @Test
    public void deleteMarketTest() throws IOException {
        MarketMapper mapper = config();
        mapper.deleteMarket("1ed4ee32aa6711e89de500e04c3600fa");//已删除
        sqlSession.close();
    }
    @Test
    public void updateMarketTest() throws IOException {
        MarketMapper mapper = config();
        Market market = new Market("427f5eb2aa6711e89de500e04c3600fa","nucMarketNew","taiyuanNew");
        mapper.updateMarket(market);//已更新
        sqlSession.close();
    }
    @Test
    public void selectMarketTest() throws IOException {
        MarketMapper mapper = config();
        List<Market> list = mapper.selectMarket();
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void selectByNameListTest() throws IOException {
        MarketMapper mapper = config();
        List<Market> list = mapper.selectByNameList("ucMarke");
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void selectByNameMapTest() throws IOException {
        MarketMapper mapper = config();
        Map<String,Market> map = mapper.selectByNameMap("ucMarke");
        for(Map.Entry<String,Market> m:map.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue().getMarket_name());
        }
        sqlSession.close();
    }

    @Test
    public void selectByNameAddTest() throws IOException {
        MarketMapper mapper = config();
        Map<Object,Object> map = new HashMap<>();
        map.put("cMarketNe","aiyuanNe");
        List<Market> market = mapper.selectByNameAdd(map);
        System.out.println(market);
        sqlSession.close();
    }

    @Test
    public void selectByIdTest() throws IOException {
        MarketMapper mapper = config();
        Market market = mapper.selectById("427f5eb2aa6711e89de500e04c3600fa");
        System.out.println(market);
        sqlSession.close();
    }

    @Test
    public void getCustomerByMarketIdTest() throws IOException {//一对多嵌套查询
        MarketMapper mapper = config();
        List<Customer> customers = mapper.getCustomerByMarketId("427f5eb2aa6711e89de500e04c3600fa");
        System.out.println(customers);
        sqlSession.close();
    }

    @Test
    public void selectAllCusByIdTest() throws IOException {//一对多分步查询
        MarketMapper mapper = config();
        Market market = mapper.selectAllCusById("427f5eb2aa6711e89de500e04c3600fa");
        System.out.println(market.getMarket_name()+" : "+market.getMarket_address());
        System.out.println(market);
        sqlSession.close();
    }
}
