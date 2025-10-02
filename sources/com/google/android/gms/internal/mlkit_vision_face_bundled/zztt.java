package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
class zztt extends zzts {
    protected final byte[] zza;

    zztt(byte[] bArr) {
        super(null);
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zztu) || zzd() != ((zztu) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (!(obj instanceof zztt)) {
            return obj.equals(this);
        }
        zztt zzttVar = (zztt) obj;
        int iZzi = zzi();
        int iZzi2 = zzttVar.zzi();
        if (iZzi != 0 && iZzi2 != 0 && iZzi != iZzi2) {
            return false;
        }
        int iZzd = zzd();
        if (iZzd > zzttVar.zzd()) {
            throw new IllegalArgumentException("Length too large: " + iZzd + zzd());
        }
        if (iZzd > zzttVar.zzd()) {
            throw new IllegalArgumentException("Ran off end of other: 0, " + iZzd + ", " + zzttVar.zzd());
        }
        if (!(zzttVar instanceof zztt)) {
            return zzttVar.zzf(0, iZzd).equals(zzf(0, iZzd));
        }
        byte[] bArr = this.zza;
        byte[] bArr2 = zzttVar.zza;
        zzttVar.zzc();
        int i = 0;
        int i2 = 0;
        while (i < iZzd) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    protected final int zze(int i, int i2, int i3) {
        return zzvc.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    public final zztu zzf(int i, int i2) {
        int iZzh = zzh(0, i2, zzd());
        return iZzh == 0 ? zztu.zzb : new zztp(this.zza, 0, iZzh);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
    final void zzg(zztm zztmVar) throws IOException {
        ((zztz) zztmVar).zzc(this.zza, 0, zzd());
    }
}
