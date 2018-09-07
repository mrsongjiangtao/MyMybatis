package com.nuc.test;

import com.nuc.entity.Customer;
import com.nuc.entity.Market;
import com.nuc.entity.SearchCustomer;
import com.nuc.mapper.SearchCustomerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SearchCustomerTest {
    static SqlSession sqlSession = null;
    public static SearchCustomerMapper config() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        SearchCustomerMapper mapper = sqlSession.getMapper(SearchCustomerMapper.class);
        return mapper;
    }
    @Test
    public void selectByConditionTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<Customer> customers = new ArrayList<Customer>();
        SearchCustomer searchCustomer = new SearchCustomer(null,"太原","3",35);
        customers = mapper.selectByCondition(searchCustomer);
        System.out.println(customers);
        sqlSession.close();
    }

    @Test
    public void selectByConditionTrimTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<Customer> customers = new ArrayList<Customer>();
        SearchCustomer searchCustomer = new SearchCustomer(null,"太原","35",null);
        customers = mapper.selectByConditionTrim(searchCustomer);
        System.out.println(customers);
        sqlSession.close();
    }

    @Test
    public void selectByConditionChoseTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<Customer> customers = new ArrayList<Customer>();
        SearchCustomer searchCustomer = new SearchCustomer(null,"太原","35",null);
        customers = mapper.selectByConditionChose(searchCustomer);
        System.out.println(customers);
        sqlSession.close();
    }

    @Test
    public void updateCustomerTest() throws IOException {
        SearchCustomerMapper mapper = config();
        Customer customer = new Customer("9","updateName","updateCode",null,"updateAddress","17735115622",99);
        mapper.updateCustomer(customer);
        sqlSession.close();
    }

    @Test
    public void selectByConditionAllTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<String> list = new ArrayList<>();
        list.add("北京");
        list.add("太原");
        List<Customer> listRes = mapper.selectByConditionAll(list);
        for(Customer customer:listRes){
            System.out.println(customer);
        }
        sqlSession.close();
    }

    @Test
    public void insertMoreTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<Customer> list = new ArrayList<>();
//        list.add(new Customer(null,"moreName3","1112223",null,"moreAddress3","10299998888",2));
//        list.add(new Customer(null,"moreName4","2221114",null,"moreAddress4","10199998888",9));

        list.add(new Customer("1001","moreName","111222",
                new Market("001","12","ty"),
                "moreAddress","12299998888",1));

        list.add(new Customer("1002","moreName2","222111",
                new Market("002","12","ty"),
                "moreAddress2","11199998888",2));
        mapper.insertMore(list);
        sqlSession.close();
    }

    @Test
    public void deleteMoreTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<String> list = new ArrayList<>();
        list.add("1001");
        list.add("1002");
        mapper.deleteMore(list);
        sqlSession.close();
    }

    @Test
    public void updateMoreTest() throws IOException {
        SearchCustomerMapper mapper = config();
        List<Customer> list = new ArrayList<>();
        list.add(new Customer("1001","newMoreName3","1112223",null,"moreAddress3","10299998888",2));
        list.add(new Customer("1002","newMoreName4","2221114",null,"moreAddress4","10199998888",9));
        mapper.updateMore(list);
        sqlSession.close();
    }

}
