package com.google.android.gms.vision.face.mlkit;

import com.google.android.gms.internal.mlkit_vision_face_bundled.zzmj;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzmk;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzml;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzmm;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzmn;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zznf;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzng;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zznh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzse;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzst;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzd {
    private final zzse zza;

    public zzd(zzse zzseVar, boolean z) {
        this.zza = zzseVar;
    }

    public final void zza(zzst zzstVar, zznh zznhVar, zzng zzngVar) throws Throwable {
        zzmj zzmjVar = new zzmj();
        if (zzstVar.zzb() == 2) {
            zzmjVar.zza(zzmk.ALL_CLASSIFICATIONS);
        } else {
            zzmjVar.zza(zzmk.NO_CLASSIFICATIONS);
        }
        if (zzstVar.zzd() == 2) {
            zzmjVar.zzd(zzmm.ALL_LANDMARKS);
        } else {
            zzmjVar.zzd(zzmm.NO_LANDMARKS);
        }
        if (zzstVar.zzc() == 2) {
            zzmjVar.zzb(zzml.ALL_CONTOURS);
        } else {
            zzmjVar.zzb(zzml.NO_CONTOURS);
        }
        if (zzstVar.zze() == 2) {
            zzmjVar.zzf(zzmn.ACCURATE);
        } else {
            zzmjVar.zzf(zzmn.FAST);
        }
        zzmjVar.zze(Float.valueOf(zzstVar.zza()));
        zzmjVar.zzc(Boolean.valueOf(zzstVar.zzf()));
        this.zza.zzc(new zzc(zznf.TYPE_THICK, zzmjVar.zzk(), zzngVar, zznhVar == zznh.OPTIONAL_MODULE_FACE_DETECTION_CREATE ? 1 : 0), zznhVar);
    }
}
