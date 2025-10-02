package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzbd extends zzaw {
    final transient Object[] zza;

    private zzbd(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zza = objArr;
    }

    static zzbd zzg(int i, Object[] objArr, zzav zzavVar) {
        zzan.zza(Objects.requireNonNull(objArr[0]), Objects.requireNonNull(objArr[1]));
        return new zzbd(null, objArr, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0003  */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaw, java.util.Map
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L5
        L3:
            r4 = r0
            goto L1b
        L5:
            java.lang.Object[] r1 = r3.zza
            r2 = 0
            r2 = r1[r2]
            java.lang.Object r2 = java.util.Objects.requireNonNull(r2)
            boolean r4 = r2.equals(r4)
            if (r4 == 0) goto L3
            r4 = 1
            r4 = r1[r4]
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)
        L1b:
            if (r4 != 0) goto L1e
            return r0
        L1e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzbd.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaw
    final zzaq zza() {
        return new zzbc(this.zza, 1, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaw
    final zzax zzd() {
        return new zzba(this, this.zza, 0, 1);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaw
    final zzax zze() {
        return new zzbb(this, new zzbc(this.zza, 0, 1));
    }
}
