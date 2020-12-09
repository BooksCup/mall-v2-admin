//package com.whl.demo.config;
//
//import com.whl.demo.filter.JWTFilter;
//import com.whl.demo.ream.UrlPermissionResolver;
//import com.whl.demo.ream.UserRealm;
//import com.whl.demo.utils.shiro.JwtProperties;
//import org.apache.shiro.codec.Base64;
//import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
//import org.apache.shiro.mgt.DefaultSubjectDAO;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import javax.servlet.Filter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author Pan Weilong
// * @date 2019/6/24 22:19
// * @description: 接口.
// */
//@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public UserRealm userRealm(){
//        UserRealm userRealm = new UserRealm();
//        userRealm.setPermissionResolver(new UrlPermissionResolver());
//        return userRealm;
//    }
//
//    @Bean("securityManager")
//    public DefaultWebSecurityManager getManager(UserRealm userRealm) {
//        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
//        // 记住登录状态 前后端分离可以不需要记住 .
//         manager.setRememberMeManager(rememberMeManager());
//        // 使用自己的realm
//        manager.setRealm(userRealm);
//
//        /*
//         * 关闭shiro自带的session，详情见文档
//         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
//         */
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        manager.setSubjectDAO(subjectDAO);
//
//        return manager;
//    }
//
//    /**
//     * cookie管理对象;
//     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
//     * @return
//     */
//    @Bean
//    public CookieRememberMeManager rememberMeManager(){
//        //System.out.println("ShiroConfiguration.rememberMeManager()");
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
//        return cookieRememberMeManager;
//    }
//
//    /**
//     * cookie对象;
//     * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
//     * @return
//     */
//    @Bean
//    public SimpleCookie rememberMeCookie(){
//        //System.out.println("ShiroConfiguration.rememberMeCookie()");
//        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
//        simpleCookie.setMaxAge(259200);
//        return simpleCookie;
//    }
//
//
//    @Bean("shiroFilter")
//    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager, StringRedisTemplate stringRedisTemplate,JwtProperties jwtProp) {
//        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
//
//        // 添加自己的过滤器并且取名为jwt
//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("jwt", new JWTFilter(jwtProp.getSecret(),jwtProp.getTokenExpireTime(),stringRedisTemplate));
//        factoryBean.setFilters(filterMap);
//
//        factoryBean.setSecurityManager(securityManager);
//
//        /*
//         * 自定义url规则
//         * http://shiro.apache.org/web.html#urls-
//         */
//        Map<String, String> filterRuleMap = new HashMap<>();
//        // 所有请求通过我们自己的JWT Filter
//        filterRuleMap.put("/appuser/login", "anon");
//        filterRuleMap.put("/**", "jwt");
//        // 访问401和404页面不通过我们的Filter
//        filterRuleMap.put("/401", "anon");
//        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
//        return factoryBean;
//    }
//
//    /**
//     * 下面的代码是添加注解支持
//     */
//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
//        // https://zhuanlan.zhihu.com/p/29161098
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }
//}
