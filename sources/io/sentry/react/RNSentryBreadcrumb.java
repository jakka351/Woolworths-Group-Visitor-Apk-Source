package io.sentry.react;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.ReadableMap;

/* loaded from: classes3.dex */
public class RNSentryBreadcrumb {
    public static String getCurrentScreenFrom(ReadableMap readableMap) {
        String string = readableMap.hasKey("category") ? readableMap.getString("category") : null;
        if (string == null || !string.equals(NotificationCompat.CATEGORY_NAVIGATION)) {
            return null;
        }
        ReadableMap map = readableMap.hasKey("data") ? readableMap.getMap("data") : null;
        if (map == null) {
            return null;
        }
        try {
            if (map.hasKey(TypedValues.TransitionType.S_TO)) {
                return map.getString(TypedValues.TransitionType.S_TO);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.sentry.Breadcrumb fromMap(com.facebook.react.bridge.ReadableMap r6) {
        /*
            io.sentry.Breadcrumb r0 = new io.sentry.Breadcrumb
            r0.<init>()
            java.lang.String r1 = "message"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L14
            java.lang.String r1 = r6.getString(r1)
            r0.setMessage(r1)
        L14:
            java.lang.String r1 = "type"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L23
            java.lang.String r1 = r6.getString(r1)
            r0.setType(r1)
        L23:
            java.lang.String r1 = "category"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto L32
            java.lang.String r1 = r6.getString(r1)
            r0.setCategory(r1)
        L32:
            java.lang.String r1 = "level"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto La1
            java.lang.String r1 = r6.getString(r1)
            int r2 = r1.hashCode()
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r2) {
                case 3237038: goto L71;
                case 95458899: goto L67;
                case 96784904: goto L5d;
                case 97203460: goto L53;
                case 1124446108: goto L49;
                default: goto L48;
            }
        L48:
            goto L7b
        L49:
            java.lang.String r2 = "warning"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L7b
            r1 = r5
            goto L7c
        L53:
            java.lang.String r2 = "fatal"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L7b
            r1 = 0
            goto L7c
        L5d:
            java.lang.String r2 = "error"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L7b
            r1 = r3
            goto L7c
        L67:
            java.lang.String r2 = "debug"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L7b
            r1 = r4
            goto L7c
        L71:
            java.lang.String r2 = "info"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L7b
            r1 = 4
            goto L7c
        L7b:
            r1 = -1
        L7c:
            if (r1 == 0) goto L9c
            if (r1 == r5) goto L96
            if (r1 == r4) goto L90
            if (r1 == r3) goto L8a
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.INFO
            r0.setLevel(r1)
            goto La1
        L8a:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.ERROR
            r0.setLevel(r1)
            goto La1
        L90:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.DEBUG
            r0.setLevel(r1)
            goto La1
        L96:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.WARNING
            r0.setLevel(r1)
            goto La1
        L9c:
            io.sentry.SentryLevel r1 = io.sentry.SentryLevel.FATAL
            r0.setLevel(r1)
        La1:
            java.lang.String r1 = "data"
            boolean r2 = r6.hasKey(r1)
            if (r2 == 0) goto Ld9
            com.facebook.react.bridge.ReadableMap r6 = r6.getMap(r1)
            java.util.HashMap r6 = r6.toHashMap()
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
        Lb9:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto Ld9
            java.lang.Object r1 = r6.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getValue()
            if (r2 == 0) goto Lb9
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            r0.setData(r2, r1)
            goto Lb9
        Ld9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.react.RNSentryBreadcrumb.fromMap(com.facebook.react.bridge.ReadableMap):io.sentry.Breadcrumb");
    }
}
