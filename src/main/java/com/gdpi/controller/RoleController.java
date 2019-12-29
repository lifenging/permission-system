package com.gdpi.controller;

import com.gdpi.bean.Permission;
import com.gdpi.bean.Role;
import com.gdpi.service.PermissionService;
import com.gdpi.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private HttpSession session;
    @Autowired  // 自动从容器中找RoleService类型的对象注入(依赖注入)
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 角色列表 - 支持分页
     * @return
     */
    @RequestMapping("/findByPage")
    public String findByPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "5") Integer pageSize, HttpServletRequest request) {

//        Subject subject = SecurityUtils.getSubject();
//        subject.checkPermission("角色管理");
        PageInfo<Role> pageInfo = roleService.findByPage(pageNum, pageSize);
        request.setAttribute("pageInfo",pageInfo);
        return "role-list";
    }


    /**
     * 2. 角色删除 http://localhost:8080/role/delete?id=100
     */
    @RequestMapping("/delete")
    public String delete(String id){
        roleService.delete(id);
        return "redirect:/role/findByPage";
    }

    /**
     * 3. 修改（1）从role-list.jsp点击修改，进入role-update.jsp修改页面
     * http://localhost:8080/role/toUpdate?id=1
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(String id, Model model){
        // 根据id查询
        Role role = roleService.findById(id);

        // 保存数据到request域
        model.addAttribute("role",role);

        // 转发
        return "role-update";
    }

    /**
     * 4. 修改（2）修改保存
     * http://localhost:8080/role/update.do
     */
    @RequestMapping("/update")
    public String update(Role role){
        // 调用service
        roleService.update(role);

        // 重定向到列表
        return "redirect:/role/findByPage";
    }

    /**
     * 5. 添加
     * http://localhost:8080/role/save.do
     */
    @RequestMapping("/save")
    public String save(Role role){

        // 调用service
        roleService.save(role);

        // 重定向到列表
        return "redirect:/role/findByPage";
    }

    @RequestMapping("/toRolePermission")
    public String toRolePermission(String id,Model model){

        model.addAttribute("roleId",id);
        return "role-permission-add";
    }


    @RequestMapping("/getTreeData")
    @ResponseBody
    public List<Map<String,Object>> getTreeData(String roleId, Model model){

        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        // 查询所有权限
        List<Permission> permissionList = permissionService.findAll();

        // 查询角色已经拥有的权限
        List<Permission> rolePermissionList= permissionService.findRolePermissionByRoleId(roleId);

        for (Permission p : permissionList) {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id",p.getPermissionId());
            map.put("pId",p.getParentId());
            map.put("name",p.getPermissionName());
            map.put("open",true);

            if (permissionList!=null&& permissionList.size()>0){

                for (Permission permission : rolePermissionList) {

                    if (p.getPermissionId()==permission.getPermissionId()){
                        map.put("checked",true);
                    }
                }
            }
            list.add(map);
        }

        return list;
    }

    @RequestMapping("/saveUserRole")
    public String saveRolePermission(String roleId, String permissionIds){

        permissionService.saveRolePermission(roleId,permissionIds);
        return "redirect:/role/findByPage";
    }

}

