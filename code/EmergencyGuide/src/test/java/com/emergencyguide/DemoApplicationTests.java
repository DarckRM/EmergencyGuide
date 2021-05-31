package com.emergencyguide;

import com.emergencyguide.Controller.Logo.PersonalLogoController;
import com.emergencyguide.Controller.api.PostApiController;
import com.emergencyguide.Dao.Community.*;
import com.emergencyguide.Dao.Logo.PersonalLogoDao;
import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Dao.SystemConfigDao;
import com.emergencyguide.Entity.*;
import com.emergencyguide.Service.Community.AddressService;
import com.emergencyguide.Service.Community.CommentService;
import com.emergencyguide.Service.Community.PostService;
import com.emergencyguide.Service.Community.StationService;
import com.emergencyguide.Service.System.RoleService;
import com.emergencyguide.Service.SystemConfigService;
import com.emergencyguide.Utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

    @Autowired
    RoleService roleService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    CommentDao commentDao;
    @Autowired
    AddressService addressService;

    @Test
    void contextLoads() {

        HashMap<String, Object> searchParams = new HashMap<>();

        searchParams.put("id",4);

        Address address = new Address();

        address.setId(7);
        address.setOpenid("oJMuB4uvA0V0-FdLqX4Szhqsjh5E");
        address.setAddress("LIMBO");
        address.setMobilephone("不存在的");
        address.setRecipient("皇老人");
        address.setIsdefault("否");

        System.out.println(addressService.changeDefaultAddress(3));

    }

}
