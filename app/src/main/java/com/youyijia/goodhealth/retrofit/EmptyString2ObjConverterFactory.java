package com.youyijia.goodhealth.retrofit;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.youyijia.hyoukalibrary.base.BaseEntity;
import com.youyijia.hyoukalibrary.utils.L;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 描述: 解决后台返回data为空字符串时 替换成大括号
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2017-07-20 14:39
 */

public class EmptyString2ObjConverterFactory extends Converter.Factory {

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeToken<?> typeToken = TypeToken.get(type);
        Class<?> rawType = typeToken.getRawType();
        //Log.i("EmptyString2Obj","type::::"+type);
        Class innerClazz = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
        Log.i("innerClazz",innerClazz.getName());
        //如果是用的BaseEntity<T>
        if (rawType.equals(BaseEntity.class)){
            Log.i("EmptyString2Obj","rawType::::"+rawType.getCanonicalName());
            if (!"java.lang.String".equals(innerClazz.getName())){
                L.i("into EmptyString2ObjConverter");
                return new EmptyString2ObjConverter<>(type);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    @Nullable
    @Override
    public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.stringConverter(type, annotations, retrofit);
    }
}
