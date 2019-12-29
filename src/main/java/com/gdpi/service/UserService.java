package com.gdpi.service;

import com.gdpi.bean.User;
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
public class UserService {

    @Autowired  // 自动从容器中找UserMapper类型的对象赋值给当前变量
    private UserMapper userMapper;

    /**
     * 查询列表
     */
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 删除
     */
    public void delete(String id){
        userMapper.delete(id);

    }

    public void insert(User user) {
        userMapper.insert(user);
    }


    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public PageInfo<User> findByPage(Integer pageNum, Integer pageSize){


        PageHelper.startPage(pageNum,pageSize);
        List<User> all = userMapper.findAll();
        PageInfo<User> pageInfo = new PageInfo<User>(all);

        return pageInfo;
    }

    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    public void saveUserRole(String userId, String[] rolesId) {

        userMapper.deleteUserRoleByUserId(userId);

        if (rolesId!=null&&rolesId.length>0){
            for (String roleId : rolesId) {
                userMapper.saveUserRole(userId,roleId);
            }
        }


    }
}

