package com.maplibre.rctmln.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import org.maplibre.android.BuildConfig;

/* loaded from: classes3.dex */
public class ResourceUtils {
    public static Drawable getDrawableByName(Context context, String str) {
        Resources resources;
        int identifier;
        if (context == null || str == null || str.isEmpty() || (resources = context.getResources()) == null || (identifier = resources.getIdentifier(str, BuildConfig.FLAVOR, context.getPackageName())) == 0) {
            return null;
        }
        return ContextCompat.getDrawable(context, identifier);
    }
}
