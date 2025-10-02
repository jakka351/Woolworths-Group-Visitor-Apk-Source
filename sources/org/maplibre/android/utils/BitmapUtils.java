package org.maplibre.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class BitmapUtils {
    public static Bitmap createBitmapFromView(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(524288);
        view.buildDrawingCache();
        if (view.getDrawingCache() == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return bitmapCreateBitmap;
    }

    @Deprecated
    public static Bitmap mergeBitmap(Bitmap bitmap, Bitmap bitmap2) {
        return mergeBitmap(bitmap, bitmap2, 10.0f, 10.0f);
    }

    public static Bitmap mergeBitmaps(Bitmap bitmap, Bitmap bitmap2) {
        return mergeBitmap(bitmap, bitmap2, 0.0f, 0.0f);
    }

    public static Bitmap mergeBitmap(Bitmap bitmap, Bitmap bitmap2, float f, float f2) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmap2, f, f2, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return null;
        }
        Drawable drawableMutate = constantState.newDrawable().mutate();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawableMutate.getIntrinsicWidth(), drawableMutate.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawableMutate.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawableMutate.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static byte[] getByteArrayFromDrawable(Drawable drawable) {
        Bitmap bitmapFromDrawable;
        if (drawable == null || (bitmapFromDrawable = getBitmapFromDrawable(drawable)) == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapFromDrawable.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Drawable getDrawableFromByteArray(Context context, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
    }

    public static Drawable getDrawableFromRes(Context context, int i) {
        return getDrawableFromRes(context, i, null);
    }

    public static Drawable getDrawableFromRes(Context context, int i, Integer num) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (drawable == null) {
            return null;
        }
        if (num == null) {
            return drawable;
        }
        drawable.setTint(num.intValue());
        return drawable;
    }

    public static boolean equals(Bitmap bitmap, Bitmap bitmap2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getHeight() * bitmap.getRowBytes());
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
        bitmap2.copyPixelsToBuffer(byteBufferAllocate2);
        return Arrays.equals(byteBufferAllocate.array(), byteBufferAllocate2.array());
    }
}
