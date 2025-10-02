package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzwa implements zzwh {
    private final zzvw zza;
    private final zzwv zzb;
    private final boolean zzc;
    private final zzui zzd;

    private zzwa(zzwv zzwvVar, zzui zzuiVar, zzvw zzvwVar) {
        this.zzb = zzwvVar;
        this.zzc = zzvwVar instanceof zzus;
        this.zzd = zzuiVar;
        this.zza = zzvwVar;
    }

    static zzwa zzc(zzwv zzwvVar, zzui zzuiVar, zzvw zzvwVar) {
        return new zzwa(zzwvVar, zzuiVar, zzvwVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final int zza(Object obj) {
        int iZzb = ((zzuw) obj).zzc.zzb();
        return this.zzc ? iZzb + ((zzus) obj).zzb.zzb() : iZzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final int zzb(Object obj) {
        int iHashCode = ((zzuw) obj).zzc.hashCode();
        return this.zzc ? (iHashCode * 53) + ((zzus) obj).zzb.zza.hashCode() : iHashCode;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final Object zze() {
        zzvw zzvwVar = this.zza;
        return zzvwVar instanceof zzuw ? ((zzuw) zzvwVar).zzy() : zzvwVar.zzK().zzp();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final void zzf(Object obj) {
        this.zzb.zza(obj);
        this.zzd.zza(obj);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final void zzg(Object obj, Object obj2) {
        zzwj.zzp(this.zzb, obj, obj2);
        if (this.zzc) {
            zzwj.zzo(this.zzd, obj, obj2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bb A[EDGE_INSN: B:57:0x00bb->B:33:0x00bb BREAK  A[LOOP:1: B:18:0x0065->B:60:0x0065], SYNTHETIC] */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzh(java.lang.Object r11, byte[] r12, int r13, int r14, com.google.android.gms.internal.mlkit_vision_face_bundled.zztj r15) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r11
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw r0 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw) r0
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzww r1 = r0.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzww r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzww.zzc()
            if (r1 != r2) goto L11
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzww r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzww.zzf()
            r0.zzc = r1
        L11:
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzus r11 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzus) r11
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzum r11 = r11.zzb()
            r0 = 0
            r2 = r0
        L19:
            if (r13 >= r14) goto Lc6
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zzj(r12, r13, r15)
            int r13 = r15.zza
            r3 = 11
            r5 = 2
            if (r13 == r3) goto L63
            r3 = r13 & 7
            if (r3 != r5) goto L5e
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuh r2 = r15.zzd
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw r3 = r10.zza
            int r5 = r13 >>> 3
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuu r8 = r2.zzb(r3, r5)
            r2 = r8
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuu r2 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzuu) r2
            if (r8 == 0) goto L53
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw r13 = r8.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwe r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzwe.zza()
            java.lang.Class r13 = r13.getClass()
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh r13 = r2.zzb(r13)
            int r13 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zze(r13, r12, r4, r14, r15)
            java.lang.Object r2 = r15.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzut r3 = r8.zzd
            r11.zzj(r3, r2)
            goto L5c
        L53:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zzi(r2, r3, r4, r5, r6, r7)
        L5c:
            r2 = r8
            goto L19
        L5e:
            int r13 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zzp(r13, r12, r4, r14, r15)
            goto L19
        L63:
            r13 = 0
            r3 = r0
        L65:
            if (r4 >= r14) goto Lbb
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zzj(r12, r4, r15)
            int r6 = r15.zza
            int r7 = r6 >>> 3
            r8 = r6 & 7
            if (r7 == r5) goto L9e
            r9 = 3
            if (r7 == r9) goto L77
            goto Lb2
        L77:
            if (r2 == 0) goto L93
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw r6 = r2.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwe r7 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzwe.zza()
            java.lang.Class r6 = r6.getClass()
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh r6 = r7.zzb(r6)
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zze(r6, r12, r4, r14, r15)
            java.lang.Object r6 = r15.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzut r7 = r2.zzd
            r11.zzj(r7, r6)
            goto L65
        L93:
            if (r8 != r5) goto Lb2
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zza(r12, r4, r15)
            java.lang.Object r3 = r15.zzc
            com.google.android.gms.internal.mlkit_vision_face_bundled.zztu r3 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zztu) r3
            goto L65
        L9e:
            if (r8 != 0) goto Lb2
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zzj(r12, r4, r15)
            int r13 = r15.zza
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuh r2 = r15.zzd
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw r6 = r10.zza
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuu r2 = r2.zzb(r6, r13)
            r6 = r2
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuu r6 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzuu) r6
            goto L65
        Lb2:
            r7 = 12
            if (r6 == r7) goto Lbb
            int r4 = com.google.android.gms.internal.mlkit_vision_face_bundled.zztk.zzp(r6, r12, r4, r14, r15)
            goto L65
        Lbb:
            if (r3 == 0) goto Lc3
            int r13 = r13 << 3
            r13 = r13 | r5
            r1.zzj(r13, r3)
        Lc3:
            r13 = r4
            goto L19
        Lc6:
            if (r13 != r14) goto Lc9
            return
        Lc9:
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzve r11 = new com.google.android.gms.internal.mlkit_vision_face_bundled.zzve
            java.lang.String r12 = "Failed to parse the message."
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzwa.zzh(java.lang.Object, byte[], int, int, com.google.android.gms.internal.mlkit_vision_face_bundled.zztj):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final void zzi(Object obj, zzxi zzxiVar) throws IOException {
        Iterator itZzf = ((zzus) obj).zzb.zzf();
        if (!itZzf.hasNext()) {
            ((zzuw) obj).zzc.zzk(zzxiVar);
            return;
        }
        zzul zzulVar = (zzul) ((Map.Entry) itZzf.next()).getKey();
        if (zzulVar.zzc() == zzxh.MESSAGE) {
            zzulVar.zze();
        }
        throw new IllegalStateException("Found invalid MessageSet item.");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final boolean zzj(Object obj, Object obj2) {
        if (!((zzuw) obj).zzc.equals(((zzuw) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zzus) obj).zzb.equals(((zzus) obj2).zzb);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final boolean zzk(Object obj) {
        return ((zzus) obj).zzb.zzl();
    }
}
