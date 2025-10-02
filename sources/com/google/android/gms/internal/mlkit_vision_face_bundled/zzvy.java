package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzvy {
    private static final char[] zza;

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    static String zza(zzvw zzvwVar, String str) throws SecurityException {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzd(zzvwVar, sb, 0);
        return sb.toString();
    }

    static void zzb(StringBuilder sb, int i, String str, Object obj) throws SecurityException {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        zzc(i, sb);
        if (!str.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Character.toLowerCase(str.charAt(0)));
            for (int i2 = 1; i2 < str.length(); i2++) {
                char cCharAt = str.charAt(i2);
                if (Character.isUpperCase(cCharAt)) {
                    sb2.append("_");
                }
                sb2.append(Character.toLowerCase(cCharAt));
            }
            str = sb2.toString();
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzwt.zza(new zztt(((String) obj).getBytes(zzvc.zza))));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zztu) {
            sb.append(": \"");
            sb.append(zzwt.zza((zztu) obj));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzuw) {
            sb.append(" {");
            zzd((zzuw) obj, sb, i + 2);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            zzc(i, sb);
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj);
            return;
        }
        int i3 = i + 2;
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        zzb(sb, i3, "key", entry.getKey());
        zzb(sb, i3, "value", entry.getValue());
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        zzc(i, sb);
        sb.append("}");
    }

    private static void zzc(int i, StringBuilder sb) {
        while (i > 0) {
            int i2 = 80;
            if (i <= 80) {
                i2 = i;
            }
            sb.append(zza, 0, i2);
            i -= i2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0203  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zzd(com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw r18, java.lang.StringBuilder r19, int r20) throws java.lang.SecurityException {
        /*
            Method dump skipped, instructions count: 572
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvy.zzd(com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw, java.lang.StringBuilder, int):void");
    }
}
