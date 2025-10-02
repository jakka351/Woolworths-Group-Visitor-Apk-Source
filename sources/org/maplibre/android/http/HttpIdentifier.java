package org.maplibre.android.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import org.maplibre.android.MapLibre;
import org.maplibre.android.MapStrictMode;

/* loaded from: classes2.dex */
public class HttpIdentifier {
    private HttpIdentifier() {
    }

    public static String getIdentifier() {
        return getIdentifier(MapLibre.getApplicationContext());
    }

    private static String getIdentifier(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return String.format("%s/%s (%s)", context.getPackageName(), packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
        } catch (Exception e) {
            MapStrictMode.strictModeViolation(e);
            return "";
        }
    }
}
