package com.gdpi.controller;

import com.gdpi.bean.Permission;
import com.gdpi.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private HttpSession session;
    @Autowired  // 自动从容器中找PermissionService类型的对象注入(依赖注入)
    private PermissionService permissionService;

    /**
     * 角色列表 - 支持分页
     * @return
     */
    @RequestMapping("/findByPage")
    public String findByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpServletRequest request) {
/*
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("权限管理");*/
        PageInfo<Permission> pageInfo = permissionService.findByPage(pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "permission-list";
    }


    /**
     * 2. 角色删除 http://localhost:8080/permission/delete?id=100
     */
    @RequestMapping("/delete")
    public String delete(String id){
        permissionService.delete(id);
        return "redirect:/permission/findByPage";
    }

    /**
     * 3. 修改（1）从permission-list.jsp点击修改，进入permission-update.jsp修改页面
     * http://localhost:8080/permission/toUpdate?id=1
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model){
        // 根据id查询
        Permission permission = permissionService.findById(id);

        // 保存数据到request域
        model.addAttribute("permission",permission);

        // 查询所有权限作为父菜单下拉列表显示
        List<Permission> permissionList = permissionService.findAll();
        model.addAttribute("permissionList",permissionList);


        // 转发
        return "permission-update";
    }

    /**
     * 4. 修改（2）修改保存
     * http://localhost:8080/permission/update.do
     */
    @RequestMapping("/update")
    public String update(Permission permission){
        // 调用service
        permissionService.update(permission);

        // 重定向到列表
        return "redirect:/permission/findByPage";
    }

    /**
     * 5. 添加
     * http://localhost:8080/permission/save.do
     */
    @RequestMapping("/save")
    public String save(Permission permission){

        // 调用service
        permissionService.save(permission);

        // 重定向到列表
        return "redirect:/permission/findByPage";
    }


    /**
     * 进入添加页面: 从permission-list.jsp----->permission-add.jsp
     */
    @RequestMapping("/toAdd")
    public String toAdd(HttpServletRequest request) {
        // 查询所有的权限，作为父菜单下拉列表显示
        List<Permission> permissionList = permissionService.findAll();
        request.setAttribute("permissionList",permissionList);

        return "permission-add";
    }



}

