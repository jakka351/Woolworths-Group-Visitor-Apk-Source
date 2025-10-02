package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzwk extends zzwr {
    zzwk() {
        super(null);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwr
    public final void zza() {
        if (!zzj()) {
            for (int i = 0; i < zzc(); i++) {
                Map.Entry entryZzg = zzg(i);
                ((zzul) ((zzwl) entryZzg).zza()).zze();
                entryZzg.setValue(Collections.unmodifiableList((List) entryZzg.getValue()));
            }
            for (Map.Entry entry : zzd()) {
                ((zzul) entry.getKey()).zze();
                entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
            }
        }
        super.zza();
    }
}
