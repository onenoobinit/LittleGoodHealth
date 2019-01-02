package com.mobile.hyoukalibrary.glide;

/**
 * 描述: 圆形的Transformation
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-12 11:44
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 圆形的Transformation
 * Created by Raye on 2016/5/3.
 * http://www.raye.wang/2016/08/25/glidejia-zai-yuan-xing-he-yuan-jiao-tu-pian-shi-yong-ji-lu/?utm_source=tuicool&utm_medium=referral
 */
public class CircleTransform extends BitmapTransformation {
	public CircleTransform(Context context) {
		super(context);
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
		return circleCrop(pool,toTransform);
	}
	private  Bitmap circleCrop(BitmapPool pool, Bitmap source) {

		int size = Math.min(source.getWidth(), source.getHeight());

		int width = (source.getWidth() - size) / 2;
		int height = (source.getHeight() - size) / 2;

		Bitmap bitmap = pool.get(size, size, Bitmap.Config.ARGB_8888);
		if (bitmap == null) {
			bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		BitmapShader shader =
				new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
		if (width != 0 || height != 0) {
			// source isn't square, move viewport to center
			Matrix matrix = new Matrix();
			matrix.setTranslate(-width, -height);
			shader.setLocalMatrix(matrix);
		}
		paint.setShader(shader);
		paint.setAntiAlias(true);

		float r = size / 2f;
		canvas.drawCircle(r, r, r, paint);

		return bitmap;
	}

	@Override
	public String getId() {
		return getClass().getName();
	}
}