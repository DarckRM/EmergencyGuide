package com.emergencyguide.Service.Community.Impl;

import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Dao.Community.StationDao;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Station;
import com.emergencyguide.Service.Community.StationService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/30 13:14
 * @Description
 */
@Service
public class StationServiceImpl implements StationService {


    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    // 缓存集合Key值
    private String REDIS_LIST_KEY = "STATION_LIST";
    // 缓存单数据Key值
    private String REDIS_INFO_KEY = "STATION_";

    @Autowired
    private StationDao stationDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Station> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        List<Station> stations = stationDao.selectList(page, limit, params);
        logger.info(stations.toString());

        return stations;
    }

    @Override
    public int selectListCount(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

        return stationDao.selectListCount(params);
    }

    @Override
    public List<Station> selectAllList() {
        return null;
    }

    @Override
    public Station selectById(int id) {
        return stationDao.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return stationDao.delete(id);
    }

    @Override
    public int updateById(Station station) {
        return stationDao.updateById(station);
    }

    @Override
    public int insert(Station station) {

        int insert = stationDao.insert(station);
        if (insert > 0) {
            redisUtil.del(REDIS_LIST_KEY);
        }
        return insert;
    }

}
