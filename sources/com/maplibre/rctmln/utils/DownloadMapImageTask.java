package com.maplibre.rctmln.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.views.imagehelper.ImageSource;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;

/* loaded from: classes3.dex */
public class DownloadMapImageTask extends AsyncTask<Map.Entry<String, ImageEntry>, Void, List<Map.Entry<String, Bitmap>>> {
    public static final String LOG_TAG = "DownloadMapImageTask";

    @Nullable
    private OnAllImagesLoaded mCallback;
    private final Object mCallerContext = this;
    private WeakReference<Context> mContext;
    private WeakReference<MapLibreMap> mMap;

    public interface OnAllImagesLoaded {
        void onAllImagesLoaded();
    }

    public DownloadMapImageTask(Context context, MapLibreMap mapLibreMap, @Nullable OnAllImagesLoaded onAllImagesLoaded) {
        this.mContext = new WeakReference<>(context.getApplicationContext());
        this.mMap = new WeakReference<>(mapLibreMap);
        this.mCallback = onAllImagesLoaded;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    @SafeVarargs
    public final List<Map.Entry<String, Bitmap>> doInBackground(Map.Entry<String, ImageEntry>... entryArr) throws NumberFormatException {
        DownloadMapImageTask downloadMapImageTask = this;
        Map.Entry<String, ImageEntry>[] entryArr2 = entryArr;
        ArrayList arrayList = new ArrayList();
        Context context = downloadMapImageTask.mContext.get();
        if (context == null) {
            return arrayList;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int length = entryArr2.length;
        int i = 0;
        while (i < length) {
            Map.Entry<String, ImageEntry> entry = entryArr2[i];
            ImageEntry value = entry.getValue();
            String string = value.uri;
            if (string.startsWith("/")) {
                string = Uri.fromFile(new File(string)).toString();
            }
            if (string.startsWith("http://") || string.startsWith("https://") || string.startsWith("file://") || string.startsWith("asset://") || string.startsWith("data:")) {
                DataSource<CloseableReference<CloseableImage>> dataSourceFetchDecodedImage = Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(new ImageSource(context, string).getUri()).setRotationOptions(RotationOptions.autoRotate()).build(), downloadMapImageTask.mCallerContext);
                CloseableReference closeableReference = null;
                try {
                    CloseableReference closeableReference2 = (CloseableReference) DataSources.waitForFinalResult(dataSourceFetchDecodedImage);
                    if (closeableReference2 != null) {
                        try {
                            CloseableImage closeableImage = (CloseableImage) closeableReference2.get();
                            if (closeableImage instanceof CloseableStaticBitmap) {
                                Bitmap bitmapCopy = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap().copy(Bitmap.Config.ARGB_8888, true);
                                bitmapCopy.setDensity((int) (value.getScaleOr(1.0d) * 160.0d));
                                arrayList.add(new AbstractMap.SimpleEntry(entry.getKey(), bitmapCopy));
                            } else {
                                FLog.e(LOG_TAG, "Failed to load bitmap from: " + string);
                            }
                        } catch (Throwable th) {
                            th = th;
                            closeableReference = closeableReference2;
                            try {
                                Log.w(LOG_TAG, th.getLocalizedMessage());
                                i++;
                                downloadMapImageTask = this;
                                entryArr2 = entryArr;
                            } finally {
                                dataSourceFetchDecodedImage.close();
                                if (closeableReference != null) {
                                    CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                                }
                            }
                        }
                    } else {
                        FLog.e(LOG_TAG, "Failed to load bitmap from: " + string);
                    }
                    dataSourceFetchDecodedImage.close();
                    if (closeableReference2 != null) {
                        CloseableReference.closeSafely((CloseableReference<?>) closeableReference2);
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                Bitmap bitmapFromResource = BitmapUtils.getBitmapFromResource(context, string, downloadMapImageTask.getBitmapOptions(displayMetrics, Double.valueOf(value.scale)));
                if (bitmapFromResource != null) {
                    arrayList.add(new AbstractMap.SimpleEntry(entry.getKey(), bitmapFromResource));
                } else {
                    FLog.e(LOG_TAG, "Failed to load bitmap from: " + string);
                }
            }
            i++;
            downloadMapImageTask = this;
            entryArr2 = entryArr;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(List<Map.Entry<String, Bitmap>> list) {
        Style style;
        MapLibreMap mapLibreMap = this.mMap.get();
        if (mapLibreMap != null && list != null && list.size() > 0 && (style = mapLibreMap.getStyle()) != null) {
            HashMap<String, Bitmap> map = new HashMap<>();
            for (Map.Entry<String, Bitmap> entry : list) {
                map.put(entry.getKey(), entry.getValue());
            }
            style.addImages(map);
        }
        OnAllImagesLoaded onAllImagesLoaded = this.mCallback;
        if (onAllImagesLoaded != null) {
            onAllImagesLoaded.onAllImagesLoaded();
        }
    }

    private BitmapFactory.Options getBitmapOptions(DisplayMetrics displayMetrics, Double d) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScreenDensity = displayMetrics.densityDpi;
        options.inTargetDensity = displayMetrics.densityDpi;
        if (d.doubleValue() != 0.0d) {
            options.inDensity = (int) (d.doubleValue() * 160.0d);
        }
        return options;
    }
}
