package com.gdpi.mapper;

import com.gdpi.bean.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询列表
     * @return
     */
    List<Role> findAll();

    /**
     * 删除
     * @param roleId
     */
    void delete(String roleId);

    /**
     * 根据id查询
     * @param roleId
     * @return
     */
    Role findById(String roleId);

    /**
     * 修改
     */
    void update(Role role);

    void save(Role role);

    List<Role> findUserRoleByUserId(String userId);

    void deleteRolePermissionByRoleId(String roleId);

    void saveRolePermission(String roleId, String permissionId);
}


