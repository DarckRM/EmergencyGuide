package com.emergencyguide.Service;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/5 20:41
 * @Description
 */
public interface BaseService<T> {
    List<T> selectList(int page, int limit, String searchParams);

    int selectListCount(String searchParams);

    List<T> selectAllList();

    T selectById(long id);

    int deleteById(long id);

    int updateById(T model);

    int insert(T model);
}
