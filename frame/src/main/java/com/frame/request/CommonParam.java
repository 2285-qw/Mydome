package com.frame.request;

import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @package com.mcht.bnexpress.util
 * @fileName CommonParm
 * @data on 2019/7/11 16:39
 * @describe 通用请求参数体
 */
public class CommonParam {
    //添加通用请求参数
    public static Map<String, Object> addCommonParameter(Map<String, Object> param) {
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonce = UUID.randomUUID().toString().replace("-", "");
      /*  String channel = ChannelUtils.getChannel();*/
        String androidID = DeviceUtils.getAndroidID();
       /* String uId = UserUtil.getUid();*/
        Map<String, Object> mParam = new HashMap<>();
        if (param != null && !param.isEmpty())
            mParam.put("data", param);//其他参数
        //mParam.put("Uid", uId);
        mParam.put("Timestamp", timeStamp);
        mParam.put("Nonce", nonce);
        //mParam.put("Signdata", getSignData(param, uId, timeStamp, nonce, androidID, channel));
        mParam.put("DeviceID", androidID);
        mParam.put("IMEI", androidID);
        //mParam.put("Channel", channel);
        mParam.put("OSVer", DeviceUtils.getSDKVersionName());
        mParam.put("APPVer", AppUtils.getAppVersionName());
        mParam.put("IMSI", androidID);
        mParam.put("Model", DeviceUtils.getModel());
        mParam.put("PushToken", "-1");
        mParam.put("Ip", 0);
        mParam.put("SystemType", 0);
        return mParam;
    }

    //得到加密后的数据
    /*public static String getSignData(Map<String, Object> data, String uid, String timestamp, String nonce, String deviceid, String channel) {
        Map<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareToIgnoreCase(t1);
            }
        });
        treeMap.put("timestamp", timestamp);
        treeMap.put("nonce", nonce);
        treeMap.put("channel", channel);
        treeMap.put("deviceid", deviceid);
        treeMap.put("uid", uid);
        if (data != null && !data.isEmpty())
            treeMap.putAll(data);
        StringBuilder temp = new StringBuilder();
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {//对固定参数和传进来的参数进行拼接，拼接形式：&timestamp=sdsada
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                if (!TextUtils.isEmpty(value.toString())) //不是空值才进行拼接加密
                    temp.append("&").append(key).append("=").append(value);
            } else if (!(value instanceof ArrayList))
                temp.append("&").append(key).append("=").append(value);
        }
        return SignUtils.sign(temp.substring(1));//base64加密
    }*/
}