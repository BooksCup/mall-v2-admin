package com.bc.wd.ream;

//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.mapper.Wrapper;
//import com.pwl.shiro.entity.SysUser;
//import com.pwl.shiro.mapper.SysPermissionMapper;
//import com.pwl.shiro.mapper.SysRoleMapper;
//import com.pwl.shiro.service.SysPermissionService;
//import com.pwl.shiro.service.SysRoleService;
//import com.pwl.shiro.service.SysUserService;
import com.bc.wd.utils.shiro.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Pan Weilong
 * @date 2019/6/20 20:11
 * @description: 认证和鉴权接口
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    //token密钥
    @Value("${security.secret}")
    private String secret;

//    @Autowired
//    private SysUserService sysUserService;
//
//    @Autowired
//    private SysPermissionService sysPermissionService;
//
//    @Autowired
//    private SysRoleService sysRoleService;
//
//    @Autowired
//    private SysRoleMapper sysRoleMapper;
//
//    @Autowired
//    private SysPermissionMapper sysPermissionMapper;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //String userId=principals.getPrimaryPrincipal().toString();
        //从数据库读取资源
        //可以控制到GET POST请求
//        List<String> sysPermissions=new ArrayList<>();
//        sysPermissions.add("/GET/sysUser/**");
//        sysPermissions.add("/DELETE/sysUser");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermissions(sysPermissions);

//        //赋予角色
//        List<Role> roles = roleService.selectRoleByUserId(user.getId());
//        for (Role role : roles) {
//            info.addRole(role.getRoleKey());
//        }
//        //赋予资源
//        List<Menu> permissions = menuService.selectPermsByUserId(user.getId());
//        for (Menu permission : permissions) {
//            info.addStringPermission(permission.getPerms());
//        }
//        String token =principals.getPrimaryPrincipal().toString();
//        String userId = JWTUtil.getUserId(token);
//        List<String> roleNameList = sysRoleMapper.sysRoleNameListByUserId(userId);
//        info.addRoles(roleNameList);
//        List<String> permissions = sysPermissionMapper.sysPerNameListByUserId(userId);
//        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 认证流程
     *
     * @param
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得userId，用于和数据库进行对比
        String userId = JWTUtil.getUserId(token);
        if (userId == null) {
            throw new AuthenticationException("token invalid");
        }
//        //查询用户是否存在
//        Wrapper<SysUser> objectEntityWrapper = new EntityWrapper<>();
//        objectEntityWrapper.eq("user_id","1");
//        SysUser sysUser = sysUserService.selectOne(objectEntityWrapper);
//        if (sysUser == null) {
//            throw new AuthenticationException("User didn't existed!");
//        }
        //校验token的正确性
        if (! JWTUtil.verify(token, userId, secret)) {
            throw new AuthenticationException("Token expired or incorrect.");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
