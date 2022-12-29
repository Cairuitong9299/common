import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.ExpiryPolicy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.DESTools;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i <5 ; i++) {
            hid_test();

        }
//        test2();
//        batch_hide_test();
//        test();
    }

    public static void hid_test() {
        try {
            String sourceType = "06";
            String targetType = "01";
            String ts = String.valueOf(System.currentTimeMillis());
            String content = "oc3kgWqhzrnbb89j2t8lBqiqAoaEco9xn8iiPuCSjJ4ywhH3bZHswVGUEgq8Ar7l";
            String bizName = "idmmaping";
            //加密,签名
            //加密
            String body = DESTools.encryptStr(bizName + ts, content, null);
            //签名
            String sign = DigestUtils.md5Hex(ts + bizName);
            //网络请求
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            //请求体
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request request = new Request.Builder()
                    .url("http://120.76.216.20:8080/hid/relation/mapping/" + sourceType + "/" + targetType + "?bizName=" + bizName + "&ts=" + ts + "&sign=" + sign)
                    .method("POST", requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("ContentType", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void batch_hide_test() {
        try {
            String sourceType = "06";
            String targetType = "01";
            String ts = String.valueOf(System.currentTimeMillis());
            List<String> list = new ArrayList<>();
            list.add("9AArqrzTeoSCb8QDREkm2530430yrELBzg18YkwE2FYZDeAZ5u7TJVKvEIXctsQd");
            list.add("oc3kgWqhzrnbb89j2t8lBqiqAoaEco9xn8iiPuCSjJ4ywhH3bZHswVGUEgq8Ar7l");
            // String content = "[\"9AArqrzTeoSCb8QDREkm2530430yrELBzg18YkwE2FYZDeAZ5u7TJVKvEIXctsQd\",\"oc3kgWqhzrnbb89j2t8lBqiqAoaEco9xn8iiPuCSjJ4ywhH3bZHswVGUEgq8Ar7l\"]";
            String content = JSON.toJSONString(list);
            String bizName = "idmmaping";
            String body = DESTools.encryptStr(bizName + ts, content, null);
            String sign = DigestUtils.md5Hex(ts + bizName);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request request = new Request.Builder()
                    .url("http://120.76.216.20:8080/hid/relation/mapping_batch/" + sourceType + "/" + targetType + "/pair" + "?bizName=" + bizName + "&ts=" + ts + "&sign=" + sign)
                    .method("POST", requestBody)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("ContentType", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void test() {

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(10);//最大连接数
        genericObjectPoolConfig.setMinIdle(10);//最小连接数
        genericObjectPoolConfig.setMaxWaitMillis(100);//最大等待时间
        JedisPool jedisPool = new JedisPool(genericObjectPoolConfig, "120.76.216.20", 6379, 2000, "123456");
        Jedis jedis = jedisPool.getResource();
        Random rd = new Random();

        for (int j = 0; j < 1000; j++) {
            StringBuffer sb = new StringBuffer();
            String base = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
            for (int i = 0; i < 64; i++) {
                sb.append(base.charAt(rd.nextInt(base.length())));
            }
            String result = sb.toString();

            StringBuffer sb1 = new StringBuffer();
            String base1 = "0123456789";
            for (int i = 0; i < 15; i++) {
                sb1.append(base1.charAt(rd.nextInt(base1.length())));
            }
            String result1 = sb1.toString();

            StringBuffer sb2 = new StringBuffer();
            String base2 = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
            for (int i = 0; i < 20; i++) {
                sb2.append(base2.charAt(rd.nextInt(base2.length())));
            }
            String result2 = sb2.toString();

            jedis.set("06_" + result, result2);
            jedis.set("01_" + result1, result2);
            HashMap map = new HashMap<>();
            map.put("06", result);
            map.put("01", result1);
            jedis.hmset("H_" + result2, map);
        }
    }


}