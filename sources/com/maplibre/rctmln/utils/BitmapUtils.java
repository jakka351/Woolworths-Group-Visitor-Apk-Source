package com.maplibre.rctmln.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Base64;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import org.maplibre.android.BuildConfig;

/* loaded from: classes3.dex */
public class BitmapUtils {
    public static final String LOG_TAG = "BitmapUtils";
    private static int CACHE_SIZE = 1048576;
    private static LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(CACHE_SIZE) { // from class: com.maplibre.rctmln.utils.BitmapUtils.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount();
        }
    };

    public static Bitmap getBitmapFromURL(String str) {
        return getBitmapFromURL(str, null);
    }

    public static Bitmap getBitmapFromURL(String str, BitmapFactory.Options options) throws IOException {
        Bitmap image = getImage(str);
        if (image != null) {
            return image;
        }
        try {
            InputStream inputStreamOpenStream = new URL(str).openStream();
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenStream, null, options);
            inputStreamOpenStream.close();
            addImage(str, bitmapDecodeStream);
            return bitmapDecodeStream;
        } catch (Exception e) {
            Log.w(LOG_TAG, e.getLocalizedMessage());
            return Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        }
    }

    public static Bitmap getBitmapFromResource(Context context, String str, BitmapFactory.Options options) {
        Resources resources = context.getResources();
        return BitmapFactory.decodeResource(resources, resources.getIdentifier(str, BuildConfig.FLAVOR, context.getPackageName()), options);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0021 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String createTempFile(android.content.Context r3, android.graphics.Bitmap r4) throws java.io.IOException {
        /*
            java.lang.String r0 = "BitmapUtils"
            r1 = 0
            java.lang.String r2 = ".png"
            java.io.File r3 = r3.getCacheDir()     // Catch: java.io.IOException -> L15
            java.io.File r3 = java.io.File.createTempFile(r0, r2, r3)     // Catch: java.io.IOException -> L15
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.io.IOException -> L13
            r2.<init>(r3)     // Catch: java.io.IOException -> L13
            goto L1f
        L13:
            r2 = move-exception
            goto L17
        L15:
            r2 = move-exception
            r3 = r1
        L17:
            java.lang.String r2 = r2.getLocalizedMessage()
            android.util.Log.w(r0, r2)
            r2 = r1
        L1f:
            if (r3 != 0) goto L22
            return r1
        L22:
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG
            r1 = 100
            r4.compress(r0, r1, r2)
            closeSnapshotOutputStream(r2)
            android.net.Uri r3 = android.net.Uri.fromFile(r3)
            java.lang.String r3 = r3.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maplibre.rctmln.utils.BitmapUtils.createTempFile(android.content.Context, android.graphics.Bitmap):java.lang.String");
    }

    public static String createBase64(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        closeSnapshotOutputStream(byteArrayOutputStream);
        return "data:image/png;base64," + Base64.encodeToString(byteArray, 2);
    }

    public static Bitmap viewToBitmap(View view, int i, int i2, int i3, int i4) {
        if (view != null) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (i5 > 0 && i6 > 0) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i5, i6, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.eraseColor(0);
                view.draw(new Canvas(bitmapCreateBitmap));
                return bitmapCreateBitmap;
            }
        }
        return null;
    }

    private static void addImage(String str, Bitmap bitmap) {
        mCache.put(str, bitmap);
    }

    private static Bitmap getImage(String str) {
        return mCache.get(str);
    }

    private static void closeSnapshotOutputStream(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            Log.w(LOG_TAG, e.getLocalizedMessage());
        }
    }
}
