package com.nuc.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.nuc.mapper.CustomerMapper;
import com.nuc.entity.Customer;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.apache.ibatis.io.Resources;

import javax.xml.bind.SchemaOutputResolver;
import java.io.InputStream;
import java.util.Map;

public class CustomerTest {
//    @Test
//    public void test(){
//        String resource = "mybatis-config.xml";
//        SqlSession sqlSession = null;
//        try {
//            InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
//            //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
//            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//            SqlSessionFactory factory = builder.build(inputStream);
//            sqlSession = factory.openSession();
//
//            //增
////            Customer customer = new Customer("123","sjt");
////            int flag = sqlSession.insert("insertCus",customer);
////            System.out.println(flag);
//
//           //删
////            sqlSession.delete("deleteCus","1");
//
//            //改
////            Customer cus = new Customer();
////            cus.setCus_code("000");
////            cus.setCus_id("1");
////            cus.setCus_name("sjt000");
////            sqlSession.update("updateCus",cus);
//
//            //查
//            Customer customer = sqlSession.selectOne("selectCusOne",4);
//            System.out.println(customer);
//
//
//            List<Customer> list =  new ArrayList<Customer>();
//            list = sqlSession.selectList("selectCus");
//            for (Customer c:list){
//                System.out.println(c);
//            }
//
//            Map<Integer,Customer> map = sqlSession.selectMap("selectCus","cus_id");
//            System.out.println(map);
//            for(Map.Entry<Integer,Customer> entry:map.entrySet()){
//                System.out.println(entry.getKey()+" "+entry.getValue());
//            }
//            sqlSession.commit();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            sqlSession.close();
//        }
//    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        SqlSession sqlSession = null;
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        /**
         *  factory.openSession(); //需手动提交事务
         *   factory.openSession(true); //系统自动提交事务
         */
        sqlSession = factory.openSession();

        CustomerMapper dao = sqlSession.getMapper(CustomerMapper.class);

        Customer cus = new Customer();
        cus.setCus_code("000new");
        cus.setCus_id(null);
        cus.setCus_name("sjt000new");

        dao.updateCus(cus);
        dao.deleteCus(5);

        Customer cus2 = new Customer();
        cus2.setCus_code("cus2");
        cus2.setCus_id(null);
        cus2.setCus_name("cus2Test");
        dao.insertCus(cus2);
        /**
         * 获取自增主键信息，现在mapper中配置userGeneratorKeys = true，指定keyProperty=“主键属性”
         * 在输出端直接通过对象名.主键属性，即可获取。
         */
        System.out.println("id:"+cus2.getCus_id());//insert之后才能获取id
        List<Customer> customers = dao.selectCus();
        for(Customer c:customers){
            System.out.println(c);
        }
        System.out.println(dao.selectCusOne("5"));
        sqlSession.commit();//如果没有提交，数据库的数据不会改变
        sqlSession.close();
    }

    @Test
    public void UUIDTest() throws IOException{
        String resource = "mybatis-config.xml";
        SqlSession sqlSession = null;
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper dao = sqlSession.getMapper(CustomerMapper.class);
        Customer cus = new Customer();
        cus.setCus_code("UUIDTest");
        cus.setCus_id(null);
        cus.setCus_name("UUIDTest");
        dao.insertCus(cus);
        sqlSession.close();
    }

    @Test
    public void ParamTest() throws IOException{
        String resource = "mybatis-config.xml";
        SqlSession sqlSession = null;
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper dao = sqlSession.getMapper(CustomerMapper.class);
        List<Customer> list = dao.selectByCon("UUIDTest","UUIDTest");
        System.out.println(list);
        List<Customer> list2 = dao.selectByCon2("cus2","cus2Test");
        System.out.println(list2);
        Map<Object,Object> map = new HashMap<Object, Object>();
        map.put("code","000new");
        map.put("name","sjt000new");
        List<Customer> list3 = dao.selectByCon3(map);
        System.out.println(list3);
    }

    @Test
    public void MoHuTest() throws IOException{
        String resource = "mybatis-config.xml";
        SqlSession sqlSession = null;
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper dao = sqlSession.getMapper(CustomerMapper.class);
        List<Customer> list = dao.selectByName("%UIDTes%");
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void selectCusMarByCusIdTest() throws IOException {
        String resource = "mybatis-config.xml";
        SqlSession sqlSession = null;
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper dao = sqlSession.getMapper(CustomerMapper.class);
        /**嵌套查询
         1、查询所有信息，并直接通过属性名来赋值
         2、查询所有信息，并通过association赋值给类对象
         */
        Customer customer = dao.selectCusMarByCusId("10");
        System.out.println(customer);
        sqlSession.close();
    }
    @Test
    public void selectByIdTest() throws IOException {
        String resource = "mybatis-config.xml";
        SqlSession sqlSession = null;
        InputStream inputStream = Resources.getResourceAsStream(resource);//读取mybatis配置文件
        //SqlSessionFactoryBuilder这个类的作用就是为了创建SqlSessionFactory的
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        sqlSession = factory.openSession(true);
        CustomerMapper dao = sqlSession.getMapper(CustomerMapper.class);
        /**
         * 分步查询
         * 1、先查询customer信息，在通过customer提供的markerId去查询超市信息。
         * 2、启用延迟加载。
         * 代码不需要做任何处理，但是需要在配置文件中配置
         * 		<setting name="lazyLoadingEnabled" value="true" />
         <setting name="aggressiveLazyLoading"  value="false" />
         启用延迟加载的好处，就是可以根据需要，来加载所需内容
         */
        Customer customer = dao.selectById("10");
        System.out.println(customer.getCus_name());
        System.out.println(customer);
    }
}
