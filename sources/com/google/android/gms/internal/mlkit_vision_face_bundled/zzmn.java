package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public enum zzmn implements zzbk {
    UNKNOWN_PERFORMANCE(0),
    FAST(1),
    ACCURATE(2);

    private final int zze;

    zzmn(int i) {
        this.zze = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzbk
    public final int zza() {
        return this.zze;
    }
}
