package org.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * @ClassName RepeatKillException
 * @Description
 * @Author lity
 * @Date 2022/1/8 11:42
 * @Version
 **/
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message, Throwable cause){
        super(message, cause);
    }
}
