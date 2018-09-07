package com.nuc.test;

import com.nuc.entity.Customer;
import com.nuc.entity.SearchCustomer;
import com.nuc.mapper.CustomerMapper;
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

public class CacheTest {
    static SqlSession sqlSession = null;
    public static CustomerMapper config() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        return mapper;
    }
    @Test
    public void oneLevelCacheTest() throws IOException {
        //测试一级缓存
        //前提：两次查询相同，一级缓存不需要配置
        //两个SqlSession之间不能从缓存中获取查询
        //在两次查询之间不能有：clearCache,close关闭连接，增删改等操作，否则，第二次查询不会从缓冲中查询
        CustomerMapper mapper = config();
        Customer customer = mapper.selectCusOne("1001");
        System.out.println(customer);
        Customer customer1 = mapper.selectCusOne("1001");
        System.out.println(customer1);
        sqlSession.close();
    }

    @Test
    public void secondLevelCacheTest() throws IOException {
        //测试二级缓存
        /*
        事务必须提交，否则二级缓存无法实现
        在对应的mapper和entity中必须开启缓存的实现序列化
        会话关闭或者提交时，不影响二级缓存，二级缓存取自namescpace,一个namespace作用域一个缓存区域
        两个SqlSession之间不是从缓存中获取的
        insert，update，delete都会刷新缓存
         */
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        Customer c = mapper.selectCusOne("1002");
        System.out.println(c);
        sqlSession.close();

        SqlSession sqlSession2 = factory.openSession(true);
        CustomerMapper mapper2 = sqlSession2.getMapper(CustomerMapper.class);
        Customer c2 = mapper2.selectCusOne("1002");
        System.out.println(c2);
    }
}
