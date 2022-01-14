package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}", exposer);
        //exposer=Exposer{exposed=true, md5=82fc35a2e6fbed958bad1c21fd63f61e', seckillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void executeSeckill() {
        long id = 1000;
        long phone = 12312039484L;
        String md5 = "82fc35a2e6fbed958bad1c21fd63f61e";

        try {
            SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
            logger.info("result={}",execution);
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }
        //org.seckill.exception.SeckillException: seckill data rewrite

        /**
         * 17:29:49.615 [main] DEBUG %PARSER_ERROR[logger] - Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.618 [main] DEBUG %PARSER_ERROR[logger] - JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@2ce6c6ec] will be managed by Spring
         * 17:29:49.621 [main] DEBUG %PARSER_ERROR[logger] - ==>  Preparing: update seckill set number = number - 1 where seckill_id = ? and start_time <= ? and end_time >= ? and number > 0;
         * 17:29:49.656 [main] DEBUG %PARSER_ERROR[logger] - ==> Parameters: 1000(Long), 2022-01-11 17:29:49.609(Timestamp), 2022-01-11 17:29:49.609(Timestamp)
         * 17:29:49.699 [main] DEBUG %PARSER_ERROR[logger] - <==    Updates: 1
         * 17:29:49.700 [main] DEBUG %PARSER_ERROR[logger] - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.700 [main] DEBUG %PARSER_ERROR[logger] - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0] from current transaction
         * 17:29:49.701 [main] DEBUG %PARSER_ERROR[logger] - ==>  Preparing: insert ignore into success_killed(seckill_id, user_phone, state, create_time) values(?, ?, ?, ?)
         * 17:29:49.702 [main] DEBUG %PARSER_ERROR[logger] - ==> Parameters: 1000(Long), 12312039483(Long), 0(Short), 2022-01-11 17:29:49.7(Timestamp)
         * 17:29:49.739 [main] DEBUG %PARSER_ERROR[logger] - <==    Updates: 1
         * 17:29:49.747 [main] DEBUG %PARSER_ERROR[logger] - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.748 [main] DEBUG %PARSER_ERROR[logger] - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0] from current transaction
         * 17:29:49.748 [main] DEBUG %PARSER_ERROR[logger] - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id = s.seckill_id where sk.seckill_id=? and sk.user_phone=?
         * 17:29:49.749 [main] DEBUG %PARSER_ERROR[logger] - ==> Parameters: 1000(Long), 12312039483(Long)
         * 17:29:49.808 [main] DEBUG %PARSER_ERROR[logger] - <==      Total: 1
         * 17:29:49.809 [main] DEBUG %PARSER_ERROR[logger] - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.810 [main] DEBUG %PARSER_ERROR[logger] - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.810 [main] DEBUG %PARSER_ERROR[logger] - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.810 [main] DEBUG %PARSER_ERROR[logger] - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@f99f5e0]
         * 17:29:49.876 [main] INFO  %PARSER_ERROR[logger] - result=org.seckill.dto.SeckillExecution@12d2ce03
         * 17:29:49.883 [Thread-1] INFO  %PARSER_ERROR[logger] - {dataSource-1} closed
         * 17:33:29.417 [main] INFO  %PARSER_ERROR[logger] - result=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1000, userPhone=12312039484, state=0, createTime=Tue Jan 11 17:33:29 CST 2022}}
         */
    }

    //测试代码完整逻辑，注意可重复执行
    @Test
    public void testSeckillLogic() throws Exception {
        long id = 1002;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exposer={}", exposer);
            long phone = 12312039484L;
            String md5 = exposer.getMd5();

            try {
                SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}", execution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }
        } else {
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }
}