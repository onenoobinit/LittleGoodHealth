package com.youyijia.goodhealth.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import com.youyijia.hyoukalibrary.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangqiang on 2019/1/31.
 */
public class CommonUtil {
    public static void saveBitmap2file(Bitmap bmp, String bitName, Context context) {

        String fileName;
        File file;
        if (Build.BRAND.equals("Xiaomi")) { // 小米手机
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + bitName;
        } else {
            fileName = Environment.getExternalStorageDirectory().getPath() + "/DCIM/" + bitName;
        }
        file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bmp.compress(Bitmap.CompressFormat.JPEG, 90, out)) {
                out.flush();
                out.close();
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), bitName, null);
            }
            ToastUtil.show(context, "图片保存成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
