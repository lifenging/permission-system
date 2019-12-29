package com.gdpi.service;

import com.gdpi.bean.Permission;
import com.gdpi.mapper.PermissionMapper;
import com.gdpi.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service表示业务逻辑层,调用mapper数据访问，完成业务逻辑
 */
@Service    // 创建对象，加入容器
@Transactional
public class PermissionService {

    @Autowired  // 自动从容器中找UserMapper类型的对象赋值给当前变量
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询列表
     */
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    /**
     * 删除
     */
    public void delete(String id) {
        permissionMapper.delete(id);
    }

    /**
     * 根据id查询
     */
    public Permission findById(String id) {
        return permissionMapper.findById(id);
    }

    /**
     * 修改
     */
    public void update(Permission permission) {
        permissionMapper.update(permission);
    }

    /**
     * 分页查询
     */
    public PageInfo<Permission> findByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> list = permissionMapper.findAll();
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(list);
        return pageInfo;
    }

    public void save(Permission permission) {
        permissionMapper.save(permission);
    }

    public List<Permission> findRolePermissionByRoleId(String roleId) {

        return permissionMapper.findRolePermissionByRoleId(roleId);
    }

    public void saveRolePermission(String roleId, String permissionIds) {

        roleMapper.deleteRolePermissionByRoleId(roleId);

        if (permissionIds!=null&&permissionIds.length()>0){
            String[] split = permissionIds.split(",");
            if (split!=null&&split.length>0){
                for (String permissionId : split) {
                    roleMapper.saveRolePermission(roleId,permissionId);
                }
            }

        }

    }

    public List<Permission> findUserPermissionByUserId(Integer userId) {
        return permissionMapper.findUserPermissionByUserId(userId);
    }
}

