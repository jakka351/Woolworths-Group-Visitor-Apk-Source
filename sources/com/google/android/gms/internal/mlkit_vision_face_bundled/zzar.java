package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzar extends zzao {
    public zzar() {
        super(4);
    }

    public final zzau zzb() {
        this.zzc = true;
        return zzau.zzg(this.zza, this.zzb);
    }

    public final zzar zza(Object obj) {
        obj.getClass();
        int i = this.zzb + 1;
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i) {
            int i2 = length + (length >> 1) + 1;
            if (i2 < i) {
                int iHighestOneBit = Integer.highestOneBit(i - 1);
                i2 = iHighestOneBit + iHighestOneBit;
            }
            if (i2 < 0) {
                i2 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i2);
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
        Object[] objArr2 = this.zza;
        int i3 = this.zzb;
        this.zzb = i3 + 1;
        objArr2[i3] = obj;
        return this;
    }
}
