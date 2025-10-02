package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzua extends IOException {
    zzua() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzua(String str, Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
    }

    zzua(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
