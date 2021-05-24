package com.emergencyguide.Utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/24 20:07
 * @Description
 */
@Component
public class EasyGeneraterParams {

    //封装查询Map
    public Map<String, Object> easySearchParams(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        if (searchParams != null) {
            JSONObject jsonParams = JSONObject.parseObject(searchParams);
            for (Map.Entry<String, Object> entry : jsonParams.entrySet()) {

                String tempparam = entry.getKey();
                String tempvalue = jsonParams.getString(tempparam);

                params.put(tempparam, tempvalue.isEmpty() ? null : tempvalue);
            }
        }

        return params;

    }

}
