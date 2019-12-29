package com.gdpi.controller;

import com.gdpi.bean.Role;
import com.gdpi.bean.User;
import com.gdpi.service.RoleService;
import com.gdpi.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * SpringMVC处理请求的控制器类
 */
@Controller // 创建对象，加入容器。
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired  // 自动从容器中找UserService类型的对象注入(依赖注入)
    private UserService userService;

    /**
     * 1. 用户列表 http://localhost:8080/user/list
     */
    @RequestMapping("/list")
    public String list(Model model) {
        // 调用service
        List<User> list = userService.findAll();

        // 保存结果，返回
        model.addAttribute("list", list);

        // 返回跳转的路径名称  (默认是转发)
        return "user-list";
    }


    /**
     * 2. 用户删除 http://localhost:8080/user/delete?id=100
     */
    @RequestMapping("/delete")
    public String delete(String id) {
        userService.delete(id);
        return "redirect:/user/pageInfo";
    }

    @RequestMapping("/insert")
    public String insert(User user) {
        userService.insert(user);

        return "redirect:/user/pageInfo";
    }


    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Model model) {
        User userId=userService.findById(id);
        model.addAttribute("userId",userId);
        return "user-update";
    }


    @RequestMapping("/update")
    public String update(User user) {
        userService.update(user);

        return "redirect:/user/pageInfo";
    }


    @RequestMapping("/pageInfo")
    public String findByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, Model model){

//        Subject subject = SecurityUtils.getSubject();
//        subject.checkPermission("用户管理");


        PageInfo<User> pageInfo = userService.findByPage(pageNum, pageSize);

        model.addAttribute("pageInfo",pageInfo);

        return "user-list";
    }

    /**
     * 5. 用户添加角色： 从user-list.jsp点击添加角色，进入到user-role-add.jsp页面
     */


    @RequestMapping("/userRole")
    public String userRole(String id,Model model) {

        // 查询所有角色
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList",roleList);

        // 查询用户已经具有的角色 (查询条件： 用户id;  求： 角色信息)
        //SELECT r.* FROM user_role ur INNER JOIN role r ON ur.roleId=r.roleId WHERE ur.userId='1'
        // 查询用户角色的主要目的： 页面默认选中.
        List<Role> userRoleList = roleService.findUserRoleByUserId(id);
        // 定义一个角色字符串，保存所有角色名称
        String roleStr = "";  // roleStr = "普通用户,管理员,"     roleStr.contains("")
        if (userRoleList != null && userRoleList.size()>0){
            for (Role role : userRoleList) {
                roleStr += role.getRoleName() + ",";
            }
        }
        model.addAttribute("roleStr",roleStr);
        model.addAttribute("userId",id);

        return "user-role-add";
    }


    @RequestMapping("/saveUserRole")
    public String saveUserRole(String userId, String[] ids){

        userService.saveUserRole(userId,ids);

        return "redirect:/user/pageInfo";
    }




}
