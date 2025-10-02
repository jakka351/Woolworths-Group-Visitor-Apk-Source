package org.maplibre.android.http;

/* loaded from: classes2.dex */
public class HttpRequestUrl {
    private HttpRequestUrl() {
    }

    public static String buildResourceUrl(String str, String str2, int i, boolean z) {
        String str3;
        if (!isValidMapboxEndpoint(str)) {
            return str2;
        }
        if (i == 0) {
            str3 = str2 + "?";
        } else {
            str3 = str2 + "&";
        }
        String str4 = str3;
        return z ? str4 + "offline=true" : str4;
    }

    private static boolean isValidMapboxEndpoint(String str) {
        return str.equals("mapbox.com") || str.endsWith(".mapbox.com") || str.equals("mapbox.cn") || str.endsWith(".mapbox.cn");
    }
}
