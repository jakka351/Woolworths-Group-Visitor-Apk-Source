package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzup implements zzvu {
    private static final zzup zza = new zzup();

    private zzup() {
    }

    public static zzup zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvu
    public final zzvt zzb(Class cls) {
        if (!zzuw.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzvt) zzuw.zzx(cls.asSubclass(zzuw.class)).zzf(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvu
    public final boolean zzc(Class cls) {
        return zzuw.class.isAssignableFrom(cls);
    }
}
