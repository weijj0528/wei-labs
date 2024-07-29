package com.weiun.springtx.jdbc.service;

public interface AccountService {

    /**
     * 转账成功测试
     */
    void transferSuccess(Integer fromUserId, Integer toUserId, Integer money) throws Exception;

    /**
     * 转账失败测试
     */
    void transferError(Integer fromUserId, Integer toUserId, Integer money) throws Exception;

}
