package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀6
         * Seckill{seckillId=1000,
         * name='1000元秒杀6',
         * number=100,
         * startTime=Fri Jan 07 00:00:00 CST 2022,
         * endTime=Mon Jan 10 00:00:00 CST 2022,
         * createTime=Fri Jan 07 16:15:47 CST 2022}
         */
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for(Seckill seckill : seckills){
            System.out.println(seckill);
        }
        /**
         * Caused by: org.apache.ibatis.binding.BindingException:
         * Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
         */
        //List<Seckill> queryAll(int offset, int limit) -> queryAll(arg0, arg1);
        //java沒有保存形參的记录
        /**
         * Seckill{seckillId=1000, name='1000元秒杀6', number=100, startTime=Fri Jan 07 00:00:00 CST 2022, endTime=Mon Jan 10 00:00:00 CST 2022, createTime=Fri Jan 07 16:15:47 CST 2022}
         * Seckill{seckillId=1001, name='500元秒杀7', number=200, startTime=Fri Jan 07 00:00:00 CST 2022, endTime=Mon Jan 10 00:00:00 CST 2022, createTime=Fri Jan 07 16:15:47 CST 2022}
         * Seckill{seckillId=1002, name='300元秒杀8', number=300, startTime=Fri Jan 07 00:00:00 CST 2022, endTime=Mon Jan 10 00:00:00 CST 2022, createTime=Fri Jan 07 16:15:47 CST 2022}
         * Seckill{seckillId=1003, name='200元秒杀9', number=400, startTime=Fri Jan 07 00:00:00 CST 2022, endTime=Mon Jan 10 00:00:00 CST 2022, createTime=Fri Jan 07 16:15:47 CST 2022}
         */
    }

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount = " + updateCount);
        //updateCount = 1
        /**
         * 09:51:27.488 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
         * 09:51:27.488 [main] DEBUG org.mybatis.spring.SqlSessionUtils - SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4201c465] was not registered for synchronization because synchronization is not active
         * 09:51:28.806 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@1807e3f6] will not be managed by Spring
         * 09:51:28.809 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing: update seckill set number = number - 1 where seckill_id = ? and start_time <= ? and end_time >= ? and number > 0;
         * 09:51:28.850 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2022-01-08 09:51:27.473(Timestamp), 2022-01-08 09:51:27.473(Timestamp)
         * 09:51:28.902 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
         * 09:51:28.902 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4201c465]
         */
    }


}