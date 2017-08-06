package com.test;

import com.test.dto.TestPressure;
import com.test.dynamic.datasource.DynamicDataSourceHolder;
import com.test.mapper.TestPressureMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by xx on 2017-08-03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class TestDataBasePressure {

    @Autowired
    private TestPressureMapper pressureMapper;

    @Autowired
    private SqlSessionFactory sessionFactory;



    @Test
    public void testStart() throws  Exception{
        List<TestPressure> pressureList = new ArrayList<TestPressure>();
        TestPressure p1;
        for (int i=1;i<=1000;i++){
             p1 = new TestPressure();
             p1.setPhone("1507486"+i);
            p1.setAddr("上海市浦东新区XX路XX号："+i);
            p1.setAge(new BigDecimal(i));
            p1.setCountry("中国"+i);
           p1.setId(new BigDecimal(1));
            p1.setDepartment("oracle:"+i);
            p1.setName("张三:"+i);
            p1.setSex("男:"+i);
            pressureList.add(p1);
        }


        System.out.println("---------测试开始----------");
        //testForInsertForeach(pressureList,"Oracle11g");
         testBatch(pressureList,"Oracle11g");
        //multipleThreadBatchInsert(pressureList,"Oracle11g");
        System.out.println();
        /**
         * 切换数据源 为mysql
         */
        DynamicDataSourceHolder.setDataSource("dataSource2");
       //testForInsertForeach(pressureList,"MySQL");
       testBatch(pressureList,"MySQL");
       // multipleThreadBatchInsert(pressureList,"MySQL");

        //System.out.println("\n\n");
        //testBatch(pressureList);
        //System.out.println("\n\n");
        //  multipleThreadBatchInsert(pressureList);
        System.out.println("---------测试结束----------");

    }


    /**
     * 测试for循环一条一条插入
     * @param pressures
     * @param database
     */
    public void testForInsertForeach(List<TestPressure> pressures,String database){
        System.out.println("------------------for循环插入开始执行-------------");
        System.out.println("Size："+pressures.size());
        System.out.println("database："+database);
        long start = System.currentTimeMillis();
        System.out.println("开始时间："+start);
        pressures.forEach(e->{
             pressureMapper.insert(e);
        });
     long end = System.currentTimeMillis();
     System.out.println("结束时间："+end);
     System.out.println(database+"执行时间："+(end-start));
     System.out.println("------------------for循环插入结束执行-------------");


    }

    /**
     *测试批量一次性插入
     *
     */
    public void testBatch(List<TestPressure> pressures,String database){
        System.out.println("-------------------批量插入开始执行---------------");
        System.out.println("Size："+pressures.size());
        System.out.println("database："+database);
        long start = System.currentTimeMillis();
        System.out.println("开始时间："+start);
         pressureMapper.batchInsert(pressures);
        long end = System.currentTimeMillis();
        System.out.println("结束时间："+end);
        System.out.println(database+"执行时间："+(end-start));
        System.out.println("-------------------批量插入结束执行---------------");


    }

    /**
     * 测试多线程分批插入数据
     * @param pressures
     * @param database
     * @throws Exception
     */
    public void multipleThreadBatchInsert(List<TestPressure> pressures,String database) throws  Exception{
        System.out.println("-------------------多线程批量插入开始执行---------------");
        System.out.println("Size："+pressures.size());
        System.out.println("database："+database);
        int threadSize=10;
        System.out.println("线程数："+threadSize);
        long start = System.currentTimeMillis();
        System.out.println("开始时间："+start);
        int taskSize = pressures.size()/threadSize;
        for(int i=0;i<threadSize;i++) {
            List<TestPressure> ps = pressures.subList(i*taskSize,i==threadSize-1?pressures.size():(i+1)*taskSize);
            Thread t = new Thread(() -> {
                pressureMapper.batchInsert(ps);
            });
            t.start();
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("结束时间："+end);
        System.out.println(database+"执行时间："+(end-start));
        System.out.println("-------------------多线程批量插入结束执行---------------");

    }

}
