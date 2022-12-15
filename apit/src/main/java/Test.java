import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import util.DESTools;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i <3 ; i++) {
            hid_test();
        }

//        batch_hide_test();
//        test();
    }

    public static void hid_test() {
        try {
            String sourceType = "06";
            String targetType = "01";
            String ts = String.valueOf(System.currentTimeMillis());
            String content = "dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jyiu";
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
            String content = "[\"dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jyiu\",\"dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jlll\"]";
            String bizName = "idmapping";
            String body = DESTools.encryptStr(bizName + ts, content, null);
            String sign = DigestUtils.md5Hex(ts + bizName);
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request request = new Request.Builder()
                    .url("http://120.76.216.20:8080/hid/relation/mapping_batch/" + sourceType + "/" + targetType + "/pair"+ "?bizName=" + bizName + "&ts=" + ts + "&sign=" + sign)
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
        String str ="[\"dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jyiu\",\"dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jlll\"]";
        System.out.println(str);
//        ArrayList list =new ArrayList<>();
//        list.add("dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jyiu");
//        list.add("dkmgtrng657897jmhytg456hmlur432789khmy6532dfr5jkou89mgtrfe34jlll");
//        String s = JSON.toJSONString(list);
//        System.out.println(s);
        JSONArray jsonArr = JSONArray.parseArray(str); // 将字符串转成JSONArray对象
        System.out.println(jsonArr.size());
        Iterator iterator = jsonArr.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
