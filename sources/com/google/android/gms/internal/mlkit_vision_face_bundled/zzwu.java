package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzwu extends RuntimeException {
    public zzwu(zzvw zzvwVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzve zza() {
        return new zzve(getMessage());
    }
}
