package org.maplibre.android.location;

import android.content.Context;
import android.graphics.Bitmap;
import org.maplibre.android.R;
import org.maplibre.android.utils.BitmapUtils;

/* loaded from: classes2.dex */
class LayerBitmapProvider {
    private final Context context;

    LayerBitmapProvider(Context context) {
        this.context = context;
    }

    Bitmap generateBitmap(int i, Integer num) {
        return BitmapUtils.getBitmapFromDrawable(BitmapUtils.getDrawableFromRes(this.context, i, num));
    }

    Bitmap generateShadowBitmap(LocationComponentOptions locationComponentOptions) {
        return Utils.generateShadow(BitmapUtils.getDrawableFromRes(this.context, R.drawable.maplibre_user_icon_shadow), locationComponentOptions.elevation());
    }
}
