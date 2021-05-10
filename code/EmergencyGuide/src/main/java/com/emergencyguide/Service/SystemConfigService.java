package com.emergencyguide.Service;

import com.emergencyguide.Entity.SystemConfig;

/**
 * @author DarckLH
 * @date 2021/5/10 16:06
 * @Description
 */
public interface SystemConfigService {

    public SystemConfig selectSystemConfig();

    public int updateSystemConfig(SystemConfig systemConfig);
}
