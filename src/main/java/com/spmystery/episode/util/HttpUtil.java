package com.spmystery.episode.util;




import com.google.gson.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.spmystery.episode.user.entity.Token;
import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


@Slf4j
public class HttpUtil {
    private HttpUtil() {

    }

    public static final String TAG = "HttpUtil";

    public static final String BACKEND_URL = "http://localhost:8080";



    private static final OkHttpClient CLIENT = new OkHttpClient();

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                String datetime = json.getAsJsonPrimitive().getAsString();
                return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }).create();
    //https://square.github.io/okhttp/recipes/
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static <T> T get(String url, Class<T> clazz) {
        Request request = new Request.Builder()
                .url(BACKEND_URL + url)
                .build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error(TAG, "get request error, response code is: " + response.code() + "url is: " + url);
                return null;
            }
            ResponseBody body = response.body();
            if (body == null) {
                log.error(TAG, "get data is null, url is:" + url);
                return null;
            }
            return GSON.fromJson(body.string(), clazz);
        } catch (Exception e) {
            log.error(TAG, "get data error, url is: " + url, e);
            return null;
        }
    }

    public static <T> T post(String url, Object t, Class<T> clazz) {
        RequestBody requestBody = RequestBody.create(JSON, GSON.toJson(t));
        Request request = new Request.Builder()
                .url(BACKEND_URL + url)
                .post(requestBody)
                .build();
        try(Response response = CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error(TAG, "post request error, response code is: " + response.code() + "url is: " + url);
                return null;
            }
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                log.error(TAG, "response of post request is null, url is:" + url);
                return null;
            }
            return GSON.fromJson(responseBody.string(), clazz);
        } catch (Exception e) {
            log.error(TAG, "post request error, url is: " + url, e);
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, Object> param = new HashMap<>();
        param.put("openId", "openId");
        param.put("unionId", "unionId");
        param.put("headImgUrl", "headimgurl");
        param.put("nickname", "nickname");
        param.put("sex", "1");
        param.put("province", "province");
        param.put("city", "city");
        param.put("country", "country");
        Token post = post("/drama/user/register", param, Token.class);
        if (post == null) {
            System.out.println("token is null");
        } else {
            System.out.println(post.getId());
        }
    }
}
