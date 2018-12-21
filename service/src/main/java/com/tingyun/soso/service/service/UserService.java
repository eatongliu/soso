package com.tingyun.soso.service.service;

import com.tingyun.soso.service.domain.User;
import com.tingyun.soso.service.vo.RespData;

/**
 * Created by liuyutong on 2018/2/11.
 */
public interface UserService {

    User selectByOneField(String field, String value, String sourceCode);

    boolean checkDuplicateUsername(Long id, String username);
    boolean checkDuplicateEmail(Long id, String username);
    boolean checkDuplicatePhone(Long id, String username);

    /**
     *  登陆
     * @param username  username/email/phone
     * @param password  密码
     * @param loginType 0 username, 1 email, 2 phone
     * @return
     */
    RespData login(String username, String password, String loginType, String sourceCode);
}
