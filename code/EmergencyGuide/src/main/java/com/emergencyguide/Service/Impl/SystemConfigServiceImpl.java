package com.emergencyguide.Service.Impl;

import com.emergencyguide.Controller.System.UserController;
import com.emergencyguide.Dao.SystemConfigDao;
import com.emergencyguide.Entity.SystemConfig;
import com.emergencyguide.Service.SystemConfigService;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author DarckLH
 * @date 2021/5/10 16:05
 * @Description
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Autowired
    private RedisUtil redisUtil;
    // 缓存单数据Key值
    private String REDISINFOKEY = "SYSTEMCONFIG_";

    public SystemConfig selectSystemConfig() {

        String key = REDISINFOKEY;
        Boolean hasKey = redisUtil.hasKey(key);
        // 缓存存在
        if (hasKey) {
            SystemConfig systemConfig = redisUtil.getModel(key, SystemConfig.class);
            logger.debug(this.getClass() + ">>从缓存取得数据 >>" + systemConfig.toString());
            return systemConfig;
        }
        SystemConfig systemConfig = systemConfigDao.selectSystemConfig();
        redisUtil.set(key, systemConfig);
        logger.debug(this.getClass() + ">>插入缓存 >>" + systemConfig.toString());

        return systemConfig;
    }

    public int updateSystemConfig(SystemConfig systemConfig) {
        String key = REDISINFOKEY;
        int update = systemConfigDao.updateSystemConfig(systemConfig);
        if (update > 0) {
            redisUtil.del(key);
            logger.debug(this.getClass() + ">>从缓存中删除记录");
        }
        return update;
    }
}
