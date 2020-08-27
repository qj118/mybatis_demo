package org.demon.dao;

import org.demon.domain.Account;

import java.util.List;

public interface AccountDao {

    /**
     * 返回所有账户信息，包括用户信息
     * @return
     */
    List<Account> findAll();

    List<Account> findByUid(int uid);
}
