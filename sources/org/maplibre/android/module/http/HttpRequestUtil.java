package org.maplibre.android.module.http;

import okhttp3.OkHttpClient;
import okio.Buffer;

/* loaded from: classes2.dex */
public class HttpRequestUtil {
    public static void setLogEnabled(boolean z) {
        HttpRequestImpl.enableLog(z);
    }

    public static void setPrintRequestUrlOnFailure(boolean z) {
        HttpRequestImpl.enablePrintRequestUrlOnFailure(z);
    }

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        HttpRequestImpl.setOkHttpClient(okHttpClient);
    }

    static String toHumanReadableAscii(String str) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt > 31 && iCodePointAt < 127) {
                iCharCount += Character.charCount(iCodePointAt);
            } else {
                Buffer buffer = new Buffer();
                buffer.writeUtf8(str, 0, iCharCount);
                while (iCharCount < length) {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    buffer.writeUtf8CodePoint((iCodePointAt2 <= 31 || iCodePointAt2 >= 127) ? 63 : iCodePointAt2);
                    iCharCount += Character.charCount(iCodePointAt2);
                }
                return buffer.readUtf8();
            }
        }
        return str;
    }
}
