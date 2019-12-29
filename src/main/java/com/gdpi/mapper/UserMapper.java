package com.gdpi.mapper;

import com.gdpi.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> findAll();

    void delete(String id);

    void insert(User user);

    User findById(Integer id);

    void update(User user);

    User findByUserName(String username);

    void deleteUserRoleByUserId(String userId);

    void saveUserRole(@Param("userId") String userId, @Param("roleId")String roleId);
}
