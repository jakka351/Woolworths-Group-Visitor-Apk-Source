package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzud implements zzxi {
    private final zzuc zza;

    private zzud(zzuc zzucVar) {
        byte[] bArr = zzvc.zzb;
        this.zza = zzucVar;
        zzucVar.zza = this;
    }

    public static zzud zza(zzuc zzucVar) {
        zzud zzudVar = zzucVar.zza;
        return zzudVar != null ? zzudVar : new zzud(zzucVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzB(int i, int i2) throws IOException {
        this.zza.zzs(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzD(int i, long j) throws IOException {
        this.zza.zzu(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    @Deprecated
    public final void zzF(int i) throws IOException {
        this.zza.zzr(i, 3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzG(int i, String str) throws IOException {
        this.zza.zzp(i, str);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzI(int i, int i2) throws IOException {
        this.zza.zzs(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzK(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzd(int i, zztu zztuVar) throws IOException {
        this.zza.zze(i, zztuVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zztu) list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    @Deprecated
    public final void zzh(int i) throws IOException {
        this.zza.zzr(i, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzi(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzk(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzm(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzo(int i, float f) throws IOException {
        this.zza.zzf(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzq(int i, Object obj, zzwh zzwhVar) throws IOException {
        zzuc zzucVar = this.zza;
        zzucVar.zzr(i, 3);
        zzwhVar.zzi((zzvw) obj, zzucVar.zza);
        zzucVar.zzr(i, 4);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzr(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzt(int i, long j) throws IOException {
        this.zza.zzu(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzv(int i, Object obj, zzwh zzwhVar) throws IOException {
        this.zza.zzm(i, (zzvw) obj, zzwhVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzw(int i, Object obj) throws IOException {
        if (obj instanceof zztu) {
            this.zza.zzo(i, (zztu) obj);
        } else {
            this.zza.zzn(i, (zzvw) obj);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzx(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzz(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzH(int i, List list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzvi)) {
            while (i2 < list.size()) {
                this.zza.zzp(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        zzvi zzviVar = (zzvi) list;
        while (i2 < list.size()) {
            Object objZza = zzviVar.zza();
            if (objZza instanceof String) {
                this.zza.zzp(i, (String) objZza);
            } else {
                this.zza.zze(i, (zztu) objZza);
            }
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzJ(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzux)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzs(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzz = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzz += zzuc.zzz(((Integer) list.get(i3)).intValue());
            }
            this.zza.zzt(iZzz);
            while (i2 < list.size()) {
                this.zza.zzt(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzux zzuxVar = (zzux) list;
        if (!z) {
            while (i2 < zzuxVar.size()) {
                this.zza.zzs(i, zzuxVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzz2 = 0;
        for (int i4 = 0; i4 < zzuxVar.size(); i4++) {
            iZzz2 += zzuc.zzz(zzuxVar.zze(i4));
        }
        this.zza.zzt(iZzz2);
        while (i2 < zzuxVar.size()) {
            this.zza.zzt(zzuxVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzL(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzvl)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzuc.zzA(((Long) list.get(i3)).longValue());
            }
            this.zza.zzt(iZzA);
            while (i2 < list.size()) {
                this.zza.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzvl zzvlVar = (zzvl) list;
        if (!z) {
            while (i2 < zzvlVar.size()) {
                this.zza.zzu(i, zzvlVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzvlVar.size(); i4++) {
            iZzA2 += zzuc.zzA(zzvlVar.zze(i4));
        }
        this.zza.zzt(iZzA2);
        while (i2 < zzvlVar.size()) {
            this.zza.zzv(zzvlVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzl(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzux)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzux zzuxVar = (zzux) list;
        if (!z) {
            while (i2 < zzuxVar.size()) {
                this.zza.zzf(i, zzuxVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzuxVar.size(); i6++) {
            zzuxVar.zze(i6);
            i5 += 4;
        }
        this.zza.zzt(i5);
        while (i2 < zzuxVar.size()) {
            this.zza.zzg(zzuxVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzn(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzvl)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzvl zzvlVar = (zzvl) list;
        if (!z) {
            while (i2 < zzvlVar.size()) {
                this.zza.zzh(i, zzvlVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzvlVar.size(); i6++) {
            zzvlVar.zze(i6);
            i5 += 8;
        }
        this.zza.zzt(i5);
        while (i2 < zzvlVar.size()) {
            this.zza.zzi(zzvlVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zztl)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Boolean) list.get(i4)).booleanValue();
                i3++;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
                i2++;
            }
            return;
        }
        zztl zztlVar = (zztl) list;
        if (!z) {
            while (i2 < zztlVar.size()) {
                this.zza.zzd(i, zztlVar.zzf(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zztlVar.size(); i6++) {
            zztlVar.zzf(i6);
            i5++;
        }
        this.zza.zzt(i5);
        while (i2 < zztlVar.size()) {
            this.zza.zzb(zztlVar.zzf(i2) ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzs(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzux)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzuc.zzA(((Integer) list.get(i3)).intValue());
            }
            this.zza.zzt(iZzA);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzux zzuxVar = (zzux) list;
        if (!z) {
            while (i2 < zzuxVar.size()) {
                this.zza.zzj(i, zzuxVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzuxVar.size(); i4++) {
            iZzA2 += zzuc.zzA(zzuxVar.zze(i4));
        }
        this.zza.zzt(iZzA2);
        while (i2 < zzuxVar.size()) {
            this.zza.zzk(zzuxVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzvl)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Long) list.get(i4)).longValue();
                i3 += 8;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzi(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzvl zzvlVar = (zzvl) list;
        if (!z) {
            while (i2 < zzvlVar.size()) {
                this.zza.zzh(i, zzvlVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzvlVar.size(); i6++) {
            zzvlVar.zze(i6);
            i5 += 8;
        }
        this.zza.zzt(i5);
        while (i2 < zzvlVar.size()) {
            this.zza.zzi(zzvlVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzue)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Double) list.get(i4)).doubleValue();
                i3 += 8;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
            return;
        }
        zzue zzueVar = (zzue) list;
        if (!z) {
            while (i2 < zzueVar.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(zzueVar.zze(i2)));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzueVar.size(); i6++) {
            zzueVar.zze(i6);
            i5 += 8;
        }
        this.zza.zzt(i5);
        while (i2 < zzueVar.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(zzueVar.zze(i2)));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzp(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzuo)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Float) list.get(i4)).floatValue();
                i3 += 4;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
            return;
        }
        zzuo zzuoVar = (zzuo) list;
        if (!z) {
            while (i2 < zzuoVar.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(zzuoVar.zze(i2)));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzuoVar.size(); i6++) {
            zzuoVar.zze(i6);
            i5 += 4;
        }
        this.zza.zzt(i5);
        while (i2 < zzuoVar.size()) {
            this.zza.zzg(Float.floatToRawIntBits(zzuoVar.zze(i2)));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzux)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                ((Integer) list.get(i4)).intValue();
                i3 += 4;
            }
            this.zza.zzt(i3);
            while (i2 < list.size()) {
                this.zza.zzg(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzux zzuxVar = (zzux) list;
        if (!z) {
            while (i2 < zzuxVar.size()) {
                this.zza.zzf(i, zzuxVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int i5 = 0;
        for (int i6 = 0; i6 < zzuxVar.size(); i6++) {
            zzuxVar.zze(i6);
            i5 += 4;
        }
        this.zza.zzt(i5);
        while (i2 < zzuxVar.size()) {
            this.zza.zzg(zzuxVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzux)) {
            if (!z) {
                while (i2 < list.size()) {
                    zzuc zzucVar = this.zza;
                    int iIntValue = ((Integer) list.get(i2)).intValue();
                    zzucVar.zzs(i, (iIntValue >> 31) ^ (iIntValue + iIntValue));
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzz = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                int iIntValue2 = ((Integer) list.get(i3)).intValue();
                iZzz += zzuc.zzz((iIntValue2 >> 31) ^ (iIntValue2 + iIntValue2));
            }
            this.zza.zzt(iZzz);
            while (i2 < list.size()) {
                zzuc zzucVar2 = this.zza;
                int iIntValue3 = ((Integer) list.get(i2)).intValue();
                zzucVar2.zzt((iIntValue3 >> 31) ^ (iIntValue3 + iIntValue3));
                i2++;
            }
            return;
        }
        zzux zzuxVar = (zzux) list;
        if (!z) {
            while (i2 < zzuxVar.size()) {
                zzuc zzucVar3 = this.zza;
                int iZze = zzuxVar.zze(i2);
                zzucVar3.zzs(i, (iZze >> 31) ^ (iZze + iZze));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzz2 = 0;
        for (int i4 = 0; i4 < zzuxVar.size(); i4++) {
            int iZze2 = zzuxVar.zze(i4);
            iZzz2 += zzuc.zzz((iZze2 >> 31) ^ (iZze2 + iZze2));
        }
        this.zza.zzt(iZzz2);
        while (i2 < zzuxVar.size()) {
            zzuc zzucVar4 = this.zza;
            int iZze3 = zzuxVar.zze(i2);
            zzucVar4.zzt((iZze3 >> 31) ^ (iZze3 + iZze3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzE(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzvl)) {
            if (!z) {
                while (i2 < list.size()) {
                    zzuc zzucVar = this.zza;
                    long jLongValue = ((Long) list.get(i2)).longValue();
                    zzucVar.zzu(i, (jLongValue >> 63) ^ (jLongValue + jLongValue));
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                long jLongValue2 = ((Long) list.get(i3)).longValue();
                iZzA += zzuc.zzA((jLongValue2 >> 63) ^ (jLongValue2 + jLongValue2));
            }
            this.zza.zzt(iZzA);
            while (i2 < list.size()) {
                zzuc zzucVar2 = this.zza;
                long jLongValue3 = ((Long) list.get(i2)).longValue();
                zzucVar2.zzv((jLongValue3 >> 63) ^ (jLongValue3 + jLongValue3));
                i2++;
            }
            return;
        }
        zzvl zzvlVar = (zzvl) list;
        if (!z) {
            while (i2 < zzvlVar.size()) {
                zzuc zzucVar3 = this.zza;
                long jZze = zzvlVar.zze(i2);
                zzucVar3.zzu(i, (jZze >> 63) ^ (jZze + jZze));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzvlVar.size(); i4++) {
            long jZze2 = zzvlVar.zze(i4);
            iZzA2 += zzuc.zzA((jZze2 >> 63) ^ (jZze2 + jZze2));
        }
        this.zza.zzt(iZzA2);
        while (i2 < zzvlVar.size()) {
            zzuc zzucVar4 = this.zza;
            long jZze3 = zzvlVar.zze(i2);
            zzucVar4.zzv((jZze3 >> 63) ^ (jZze3 + jZze3));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzj(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzux)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzuc.zzA(((Integer) list.get(i3)).intValue());
            }
            this.zza.zzt(iZzA);
            while (i2 < list.size()) {
                this.zza.zzk(((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        zzux zzuxVar = (zzux) list;
        if (!z) {
            while (i2 < zzuxVar.size()) {
                this.zza.zzj(i, zzuxVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzuxVar.size(); i4++) {
            iZzA2 += zzuc.zzA(zzuxVar.zze(i4));
        }
        this.zza.zzt(iZzA2);
        while (i2 < zzuxVar.size()) {
            this.zza.zzk(zzuxVar.zze(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi
    public final void zzu(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzvl)) {
            if (!z) {
                while (i2 < list.size()) {
                    this.zza.zzu(i, ((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            this.zza.zzr(i, 2);
            int iZzA = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzA += zzuc.zzA(((Long) list.get(i3)).longValue());
            }
            this.zza.zzt(iZzA);
            while (i2 < list.size()) {
                this.zza.zzv(((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        zzvl zzvlVar = (zzvl) list;
        if (!z) {
            while (i2 < zzvlVar.size()) {
                this.zza.zzu(i, zzvlVar.zze(i2));
                i2++;
            }
            return;
        }
        this.zza.zzr(i, 2);
        int iZzA2 = 0;
        for (int i4 = 0; i4 < zzvlVar.size(); i4++) {
            iZzA2 += zzuc.zzA(zzvlVar.zze(i4));
        }
        this.zza.zzt(iZzA2);
        while (i2 < zzvlVar.size()) {
            this.zza.zzv(zzvlVar.zze(i2));
            i2++;
        }
    }
}
