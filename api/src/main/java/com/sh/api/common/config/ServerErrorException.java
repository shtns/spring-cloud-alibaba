package com.sh.api.common.config;

/**
 * 服务运行异常
 *
 *
 * @author 盛浩
 * @date 2021/2/28 20:48
 */
public class ServerErrorException extends RuntimeException {

    public ServerErrorException(String msg) {
        super(msg);
    }
}
