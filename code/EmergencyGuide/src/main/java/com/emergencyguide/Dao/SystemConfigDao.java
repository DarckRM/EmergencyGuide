package com.emergencyguide.Dao;

import com.emergencyguide.Entity.SystemConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author DarckLH
 * @date 2021/5/10 15:54
 * @Description
 */
@Mapper
public interface SystemConfigDao {

    public SystemConfig selectSystemConfig();

    public int updateSystemConfig(SystemConfig systemConfig);

}
