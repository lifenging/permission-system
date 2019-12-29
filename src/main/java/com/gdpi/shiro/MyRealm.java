package com.gdpi.shiro;

import com.gdpi.bean.Permission;
import com.gdpi.bean.User;
import com.gdpi.service.PermissionService;
import com.gdpi.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    //权限认证

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();

        User user = userService.findByUserName(username);

        if (user==null){
            return null;
        }

        /**
         * 执行到这里，用户名是正确。那么，就要校验密码
         * 不用自己校验，shiro自动校验.
         * 原理: 1. 用户名正确； 2. shiro知道用户输入的密码； 3. 需要shiro数据库正确的密码。
         */
        // 参数1：认证后的身份对象。通过subject.getPrincipal()获取这里的参数1
        // 参数2：数据库中正确的密码
        // 参数3：realm名称，可以随意定义。 getName()获取的是默认名称

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        return simpleAuthenticationInfo;
    }


    // 授权方法： 告诉shiro用户的权限
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        User user = (User) principal.getPrimaryPrincipal();

       List<Permission> permissionList =  permissionService.findUserPermissionByUserId(user.getId());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (permissionList!=null&&permissionList.size()>0){
            for (Permission permission : permissionList) {

                info.addStringPermission(permission.getPermissionName());
            }
        }
        return info;
    }
}
