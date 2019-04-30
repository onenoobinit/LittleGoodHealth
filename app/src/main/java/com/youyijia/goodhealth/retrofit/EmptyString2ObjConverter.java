package com.youyijia.goodhealth.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 描述: 解决后台返回data为空字符串时 替换成大括号 防止报错
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-07-20 15:19
 */

public class EmptyString2ObjConverter<T> implements Converter<ResponseBody,T> {
    Type type;

    public EmptyString2ObjConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String data = jsonObject.getString("data");
            boolean hasData = jsonObject.has("data");
            if ("400".equals(jsonObject.getString("status")) && !hasData){
                jsonObject.put("data","{}");
                return (T)new Gson().fromJson(jsonObject.toString(),type);
            }
            return (T)new Gson().fromJson(json,type);
        } catch (JSONException e) {
            e.printStackTrace();
            if ("No value for data".equals(e.getMessage())){
                Log.i("jsonException","------ 没有data");
                return (T)new Gson().fromJson(json,type);
            }
        }
        return null;
    }
}

