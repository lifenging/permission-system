package com.gdpi.mapper;

import com.gdpi.bean.Permission;

import java.util.List;

public interface PermissionMapper {
    /**
     * 查询列表
     * @return
     */
    List<Permission> findAll();

    /**
     * 删除
     * @param permissionId
     */
    void delete(String permissionId);

    /**
     * 根据id查询
     * @param permissionId
     * @return
     */
    Permission findById(String permissionId);

    /**
     * 修改
     */
    void update(Permission permission);

    void save(Permission permission);

    List<Permission> findRolePermissionByRoleId(String roleId);

    List<Permission> findUserPermissionByUserId(Integer userId);
}


