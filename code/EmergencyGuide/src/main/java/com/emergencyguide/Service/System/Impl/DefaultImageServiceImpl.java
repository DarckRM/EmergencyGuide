package com.emergencyguide.Service.System.Impl;

import com.emergencyguide.Dao.System.DefaultImgDao;
import com.emergencyguide.Entity.Image;
import com.emergencyguide.Service.System.DefaultImageService;
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
 * @date 2021/6/2 0:25
 * @Description
 */
@Service
public class DefaultImageServiceImpl implements DefaultImageService {

    Logger logger = LoggerFactory.getLogger(DefaultImageService.class);

    // 缓存集合Key值
    private String REDIS_LIST_KEY = "IMAGE_LIST";
    // 缓存单数据Key值
    private String REDIS_INFO_KEY = "IMAGE_";

    @Autowired
    private DefaultImgDao defaultImgDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Image> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        List<Image> images = defaultImgDao.selectList(page, limit, params);
        logger.info(images.toString());

        return images;
    }

    @Override
    public int selectListCount(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

        return defaultImgDao.selectListCount(params);
    }

    @Override
    public List<Image> selectAllList() {
        return defaultImgDao.selectAllList();
    }

    @Override
    public Image selectById(int id) {
        return defaultImgDao.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return defaultImgDao.delete(id);
    }

    @Override
    public int updateById(Image image) {
        return defaultImgDao.updateById(image);
    }

    @Override
    public int insert(Image image) {

        int insert = defaultImgDao.insert(image);
        if (insert > 0) {
            redisUtil.del(REDIS_LIST_KEY);
        }
        return insert;
    }

}
