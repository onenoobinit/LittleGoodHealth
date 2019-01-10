package com.mobile.android.retrofit;

import com.mobile.hyoukalibrary.base.BaseEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 描述: dada
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2018-01-12 18:37
 */
public class ToStringConverter<T> implements Converter<ResponseBody,T> {
    @Override
    public T convert(ResponseBody value) throws IOException {
        String json = value.string();
        try {
            JSONObject jsonObject = new JSONObject(json);
            String data = jsonObject.getString("data");
            //String newData = "1"+data+"1";
            BaseEntity entity =new BaseEntity();
            /*entity.setStatus(jsonObject.getInt("status"));
            entity.setError(jsonObject.getInt("error_code"));
            entity.setMessage(jsonObject.getString("message"));
            entity.setData(data);*/
            /*L.i("newData",newData);
            String newJson = json.replace(data, newData);
            L.i("newJson",newJson);*/
            return (T) entity;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
