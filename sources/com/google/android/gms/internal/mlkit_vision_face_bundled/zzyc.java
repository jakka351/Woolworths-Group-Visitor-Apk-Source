package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzyc extends zzuw implements zzvx {
    private static final zzyc zzb;
    private byte zze = 2;
    private zzvb zzd = zzA();

    static {
        zzyc zzycVar = new zzyc();
        zzb = zzycVar;
        zzuw.zzF(zzyc.class, zzycVar);
    }

    private zzyc() {
    }

    public static zzyc zzd() {
        return zzb;
    }

    public final List zze() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return Byte.valueOf(this.zze);
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"zzd", zzxz.class});
        }
        if (i2 == 3) {
            return new zzyc();
        }
        zzya zzyaVar = null;
        if (i2 == 4) {
            return new zzyb(zzyaVar);
        }
        if (i2 == 5) {
            return zzb;
        }
        this.zze = obj == null ? (byte) 0 : (byte) 1;
        return null;
    }
}
