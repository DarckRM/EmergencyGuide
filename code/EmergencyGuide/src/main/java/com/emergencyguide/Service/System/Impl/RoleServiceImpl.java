package com.emergencyguide.Service.System.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.RoleService;
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

    //这个方法用于写入Redis缓存
    @Override
    public List<Role> selectAllList() {

        return roleDao.selectAllList();

    }

    @Override
    public List<Role> selectList(int page, int limit, String searchParams) {

        String role = "";
        String authority = "";
        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            role = json.getString("role");
            authority = json.getString("authority");
        }

        Map<String, Object> params = new HashMap<>();

        params.put("role", role.isEmpty() ? null : role);
        params.put("authority", authority.isEmpty() ? null : authority);

        List<Role> roles = roleDao.selectList(params);
        logger.info(roles.toString());
        return roles;
    }

    @Override
    public int selectListCount(String searchParams) {
        return roleDao.selectListCount();
    }
}
