package com.mobile.hyoukalibrary.glide;

/**
 * 描述: 圆角效果的Transform
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-12 11:43
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 圆角效果的Transform
 * Created by Raye on 2016/5/10.
 * http://www.raye.wang/2016/08/25/glidejia-zai-yuan-xing-he-yuan-jiao-tu-pian-shi-yong-ji-lu/?utm_source=tuicool&utm_medium=referral
 */
public class CornersTransform  extends BitmapTransformation {
	private float radius;
	public CornersTransform(Context context) {
		super(context);
		radius = 10;
	}

	public CornersTransform(Context context,float radius){
		super(context);
		this.radius = radius;
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
		return cornersCrop(pool,toTransform);
	}
	private Bitmap cornersCrop(BitmapPool pool, Bitmap source) {
		if (source == null) {
            return null;
        }

		Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
		if (result == null) {
			result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
		paint.setAntiAlias(true);
		RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
		canvas.drawRoundRect(rectF, radius, radius, paint);
		return result;
	}

	@Override
	public String getId() {
		return getClass().getName();
	}
}
