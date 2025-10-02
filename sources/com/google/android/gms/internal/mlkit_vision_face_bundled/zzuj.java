package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzuj extends zzui {
    zzuj() {
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzui
    final void zza(Object obj) {
        ((zzus) obj).zzb.zzh();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzui
    final void zzb(zzxi zzxiVar, Map.Entry entry) throws IOException {
        zzxg zzxgVar = zzxg.DOUBLE;
        switch (r0.zzb) {
            case DOUBLE:
                zzwj.zzs(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case FLOAT:
                zzwj.zzw(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case INT64:
                zzwj.zzz(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case UINT64:
                zzwj.zzH(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case INT32:
                zzwj.zzy(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case FIXED64:
                zzwj.zzv(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case FIXED32:
                zzwj.zzu(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case BOOL:
                zzwj.zzq(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case STRING:
                zzwj.zzF(202056002, (List) entry.getValue(), zzxiVar);
                break;
            case GROUP:
                List list = (List) entry.getValue();
                if (list != null && !list.isEmpty()) {
                    zzwj.zzx(202056002, (List) entry.getValue(), zzxiVar, zzwe.zza().zzb(list.get(0).getClass()));
                    break;
                }
                break;
            case MESSAGE:
                List list2 = (List) entry.getValue();
                if (list2 != null && !list2.isEmpty()) {
                    zzwj.zzA(202056002, (List) entry.getValue(), zzxiVar, zzwe.zza().zzb(list2.get(0).getClass()));
                    break;
                }
                break;
            case BYTES:
                zzwj.zzr(202056002, (List) entry.getValue(), zzxiVar);
                break;
            case UINT32:
                zzwj.zzG(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case ENUM:
                zzwj.zzy(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case SFIXED32:
                zzwj.zzB(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case SFIXED64:
                zzwj.zzC(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case SINT32:
                zzwj.zzD(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
            case SINT64:
                zzwj.zzE(202056002, (List) entry.getValue(), zzxiVar, false);
                break;
        }
    }
}
