package com.gdpi.service;

import com.gdpi.bean.Role;
import com.gdpi.bean.User;
import com.gdpi.mapper.RoleMapper;
import com.gdpi.mapper.UserMapper;
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
public class RoleService {

    @Autowired  // 自动从容器中找UserMapper类型的对象赋值给当前变量
    private RoleMapper roleMapper;

    /**
     * 查询列表
     */
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    /**
     * 删除
     */
    public void delete(String id) {
        roleMapper.delete(id);
    }

    /**
     * 根据id查询
     */
    public Role findById(String id) {
        return roleMapper.findById(id);
    }

    /**
     * 修改
     */
    public void update(Role Role) {
        roleMapper.update(Role);
    }

    /**
     * 分页查询
     */
    public PageInfo<Role> findByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleMapper.findAll();
        PageInfo<Role> pageInfo = new PageInfo<Role>(list);
        return pageInfo;
    }

    public void save(Role role) {
        roleMapper.save(role);
    }


    public List<Role> findUserRoleByUserId(String userId) {


        return roleMapper.findUserRoleByUserId(userId);

    }
}

