package com.emergencyguide.Service.System;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Image;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/6/2 0:25
 * @Description
 */
public interface DefaultImageService {
    public int insert(Image image);
    public int updateById(Image image);
    public Image selectById(int id);
    public List<Image> selectAllList();
    public int selectListCount(String searchParams);
    public List<Image> selectList(int page, int limit, String searchParams);
    public int deleteById(int id);
}
