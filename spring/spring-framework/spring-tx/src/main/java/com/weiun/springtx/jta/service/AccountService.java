package com.weiun.springtx.jta.service;

public interface AccountService {

    void addAccountSuccess(Integer userId, Integer money);

    void addAccountError(Integer userId, Integer money);

}
