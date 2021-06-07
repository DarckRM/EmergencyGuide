package com.emergencyguide;

import com.emergencyguide.Dao.Community.*;
import com.emergencyguide.Dao.System.DefaultImgDao;
import com.emergencyguide.Entity.*;
import com.emergencyguide.Service.System.RoleService;
import com.emergencyguide.Utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

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
    DefaultImgDao defaultImgDao;

    @Test
    void contextLoads() {

        HashMap<String, Object> searchParams = new HashMap<>();

        Image image = new Image();

        image.setImgname("客户默认头像");
        image.setPath("/......");
        image.setInfo("测试内容");
        image.setId(2);
        searchParams.put("id",1);

        System.out.println(defaultImgDao.selectList(0,1,searchParams));

    }

    @Test
    void TestFileHashMemoryUsage() throws NoSuchAlgorithmException, IOException {

        String path = "E:\\Emeguide\\DefaultImages";
        File file = new File(path);
        MessageDigest md = MessageDigest.getInstance("MD5");

        File[] fs = file.listFiles();
        for(File f:fs) {
            if (!f.isDirectory()) {
                System.out.println(getCRC32(f.getAbsolutePath(), md) + f.getName());
            }
        }

    }
    private static String getCRC32(String filepath, MessageDigest md) throws IOException {

        // DigestInputStream is better, but you also can hash file like this.
        try (InputStream fis = new FileInputStream(filepath)) {
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }

        CRC32 crc32 = new CRC32();
        crc32.update(result.toString().getBytes());

        return crc32.getValue() % 250000 + "";

    }


}
