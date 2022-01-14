package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long id = 1001L;
        long phone = 18000000001L;
        short state = 0;
        Date createTime = new Date();
        int insertCount = successKilledDao.insertSuccessKilled(id, phone, state, createTime);
        System.out.println("insertCount = " + insertCount);
        //第一次：insertCount = 1
        //第二次：insertCount = 0
        //联合主键保证不允许重复秒杀
    }

    @Test
    public void queryByIdWithSeckill() {
        long id = 1001L;
        long phone = 18000000001L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        //SuccessKilled{seckillId=1000, userPhone=18000000000, state=-1, createTime=Sat Jan 08 10:25:04 CST 2022}
        //Seckill{seckillId=1000, name='1000元秒杀6', number=98, startTime=Fri Jan 07 00:00:00 CST 2022, endTime=Mon Jan 10 00:00:00 CST 2022, createTime=Fri Jan 07 16:15:47 CST 2022}

        //SuccessKilled{seckillId=1001, userPhone=18000000001, state=0, createTime=Sat Jan 08 10:39:58 CST 2022}
        //Seckill{seckillId=1001, name='500元秒杀7', number=200, startTime=Fri Jan 07 00:00:00 CST 2022, endTime=Mon Jan 10 00:00:00 CST 2022, createTime=Fri Jan 07 16:15:47 CST 2022}
    }
}