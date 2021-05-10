package com.emergencyguide.Service.System;

import com.emergencyguide.Entity.Role;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/11 1:40
 * @Description
 */
public interface RoleService {

    public List<Role> selectAllList();

    public int selectListCount(String searchParams);

    public List<Role> selectList(int page, int limit, String searchParams);

}
