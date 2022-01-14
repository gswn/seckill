package org.seckill.exception;

/**
 * 秒杀关闭异常
 * @ClassName SeckillCloseException
 * @Description
 * @Author lity
 * @Date 2022/1/8 11:46
 * @Version
 **/
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
