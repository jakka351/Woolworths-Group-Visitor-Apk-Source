package com.google.android.gms.vision.face.mlkit;

import android.content.Context;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzng;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zznh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzrz;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzsa;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzse;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzsp;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzst;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzb {
    private final zzd zza;

    public zzb(Context context, boolean z) {
        zzrz zzrzVarZzd = zzrz.zzd("optional-module-face").zzd();
        this.zza = new zzd(new zzse(context, new SharedPrefManager(context), new zzsa(context, zzrzVarZzd), zzrzVarZzd.zzb()), false);
    }

    final void zza(zzst zzstVar, zzsp zzspVar, List list, long j) throws Throwable {
        this.zza.zza(zzstVar, zznh.OPTIONAL_MODULE_FACE_DETECTION_INFERENCE, zzng.NO_ERROR);
    }

    final void zzb(zzst zzstVar, String str, long j) throws Throwable {
        this.zza.zza(zzstVar, zznh.OPTIONAL_MODULE_FACE_DETECTION_CREATE, str != null ? zzng.OPTIONAL_MODULE_CREATE_ERROR : zzng.NO_ERROR);
    }

    final void zzc(zzst zzstVar) throws Throwable {
        this.zza.zza(zzstVar, zznh.OPTIONAL_MODULE_FACE_DETECTION_INIT, zzng.NO_ERROR);
    }
}
