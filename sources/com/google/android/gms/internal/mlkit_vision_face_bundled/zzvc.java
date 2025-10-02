package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.lang3.CharEncoding;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzvc {
    static final Charset zza;
    public static final byte[] zzb;

    static {
        Charset.forName(CharEncoding.US_ASCII);
        zza = Charset.forName(CharEncoding.UTF_8);
        Charset.forName(CharEncoding.ISO_8859_1);
        byte[] bArr = new byte[0];
        zzb = bArr;
        ByteBuffer.wrap(bArr);
        int i = zzty.zza;
        try {
            new zztw(bArr, 0, 0, false, null).zza(0);
        } catch (zzve e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    static int zzb(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object zzc(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("messageType");
    }
}
