package org.demon.dao;

import org.demon.domain.Role;

import java.util.List;

public interface RoleDao {

    /**
     * 返回所有角色信息，包括角色包含的用户
     * @return
     */
    List<Role> findAll();
}
