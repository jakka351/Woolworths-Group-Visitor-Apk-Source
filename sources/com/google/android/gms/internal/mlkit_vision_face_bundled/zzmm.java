package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public enum zzmm implements zzbk {
    UNKNOWN_LANDMARKS(0),
    NO_LANDMARKS(1),
    ALL_LANDMARKS(2);

    private final int zze;

    zzmm(int i) {
        this.zze = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzbk
    public final int zza() {
        return this.zze;
    }
}
