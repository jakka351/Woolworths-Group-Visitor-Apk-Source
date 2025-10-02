package org.maplibre.android.annotations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.maplibre.android.MapStrictMode;
import org.maplibre.android.R;
import org.maplibre.android.exceptions.TooManyIconsException;
import org.maplibre.android.utils.BitmapUtils;

@Deprecated
/* loaded from: classes2.dex */
public final class IconFactory {
    private static final String ICON_ID_PREFIX = "com.mapbox.icons.icon_";
    private static IconFactory instance;
    private Context context;
    private Icon defaultMarker;
    private int nextId = 0;
    private BitmapFactory.Options options;

    public static synchronized IconFactory getInstance(Context context) {
        if (instance == null) {
            instance = new IconFactory(context.getApplicationContext());
        }
        return instance;
    }

    private IconFactory(Context context) {
        this.context = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics2);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        BitmapFactory.Options options = new BitmapFactory.Options();
        this.options = options;
        options.inScaled = true;
        this.options.inDensity = 160;
        this.options.inTargetDensity = displayMetrics.densityDpi;
        this.options.inScreenDensity = displayMetrics2.densityDpi;
    }

    public Icon fromBitmap(Bitmap bitmap) {
        if (this.nextId < 0) {
            throw new TooManyIconsException();
        }
        StringBuilder sb = new StringBuilder(ICON_ID_PREFIX);
        int i = this.nextId + 1;
        this.nextId = i;
        return new Icon(sb.append(i).toString(), bitmap);
    }

    public Icon fromResource(int i) {
        Drawable drawableFromRes = BitmapUtils.getDrawableFromRes(this.context, i);
        if (drawableFromRes instanceof BitmapDrawable) {
            return fromBitmap(((BitmapDrawable) drawableFromRes).getBitmap());
        }
        throw new IllegalArgumentException("Failed to decode image. The resource provided must be a Bitmap.");
    }

    public Icon defaultMarker() {
        if (this.defaultMarker == null) {
            this.defaultMarker = fromResource(R.drawable.maplibre_marker_icon_default);
        }
        return this.defaultMarker;
    }

    private Icon fromInputStream(InputStream inputStream) {
        return fromBitmap(BitmapFactory.decodeStream(inputStream, null, this.options));
    }

    public Icon fromAsset(String str) {
        try {
            return fromInputStream(this.context.getAssets().open(str));
        } catch (IOException e) {
            MapStrictMode.strictModeViolation(e);
            return null;
        }
    }

    public Icon fromPath(String str) {
        return fromBitmap(BitmapFactory.decodeFile(str, this.options));
    }

    public Icon fromFile(String str) {
        try {
            return fromInputStream(this.context.openFileInput(str));
        } catch (FileNotFoundException e) {
            MapStrictMode.strictModeViolation(e);
            return null;
        }
    }

    public static Icon recreate(String str, Bitmap bitmap) {
        return new Icon(str, bitmap);
    }
}
