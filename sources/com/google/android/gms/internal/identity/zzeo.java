package com.google.android.gms.internal.identity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
public final class zzeo {
    private static final SimpleDateFormat zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.ROOT);
    private static final SimpleDateFormat zzb = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.ROOT);
    private static final StringBuilder zzc = new StringBuilder(33);

    public static String zza(long j) {
        return j >= 0 ? zza.format(new Date(j)) : Long.toString(j);
    }

    public static String zzb(long j) {
        String string;
        StringBuilder sb = zzc;
        synchronized (sb) {
            sb.setLength(0);
            zzc(j, sb);
            string = sb.toString();
        }
        return string;
    }

    public static StringBuilder zzc(long j, StringBuilder sb) {
        if (j == 0) {
            sb.append("0s");
            return sb;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z = false;
        if (j < 0) {
            sb.append("-");
            if (j != Long.MIN_VALUE) {
                j = -j;
            } else {
                j = Long.MAX_VALUE;
                z = true;
            }
        }
        if (j >= DateUtils.MILLIS_PER_DAY) {
            sb.append(j / DateUtils.MILLIS_PER_DAY);
            sb.append("d");
            j %= DateUtils.MILLIS_PER_DAY;
        }
        if (true == z) {
            j = 25975808;
        }
        if (j >= DateUtils.MILLIS_PER_HOUR) {
            sb.append(j / DateUtils.MILLIS_PER_HOUR);
            sb.append("h");
            j %= DateUtils.MILLIS_PER_HOUR;
        }
        if (j >= DateUtils.MILLIS_PER_MINUTE) {
            sb.append(j / DateUtils.MILLIS_PER_MINUTE);
            sb.append("m");
            j %= DateUtils.MILLIS_PER_MINUTE;
        }
        if (j >= 1000) {
            sb.append(j / 1000);
            sb.append("s");
            j %= 1000;
        }
        if (j > 0) {
            sb.append(j);
            sb.append("ms");
        }
        return sb;
    }
}
