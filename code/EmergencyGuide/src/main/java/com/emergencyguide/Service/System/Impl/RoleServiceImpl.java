package com.emergencyguide.Service.System.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.RoleService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/11 1:42
 * @Description
 */
@Service
public class RoleServiceImpl implements RoleService {

    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    // 缓存集合Key值
    private String REDISLISTKEY = "ROLELIST_";
    // 缓存单数据Key值
    private String REDISINFOKEY = "ROLE_";

    //这个方法用于写入Redis缓存
    @Override
    public List<Role> selectAllList() {

        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY);
        if (hasKey) {
            System.out.println("已从Redis中取得权限数据");
            List<Role> roles = redisUtil.getList(REDISLISTKEY, Role.class);
            return roles;
        }

        List<Role> roles = roleDao.selectAllList();
        // 存在到缓存中
        redisUtil.set(REDISLISTKEY, roles);
        logger.info(roles.toString());
        return roles;

    }

    @Override
    public List<Role> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);

        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY);
        if (hasKey) {
            System.out.println("已从Redis中取得权限数据");
            List<Role> roles = redisUtil.getList(REDISLISTKEY, Role.class);
            return roles;
        }

        List<Role> roles = roleDao.selectList(params);
        // 存在到缓存中
        redisUtil.set(REDISLISTKEY, roles);
        logger.info(roles.toString());
        return roles;
    }

    @Override
    public int selectListCount(String searchParams) {
        return roleDao.selectListCount();
    }
}
