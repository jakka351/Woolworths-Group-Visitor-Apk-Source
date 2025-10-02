package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzsh implements zzrw {
    private final zzni zza;
    private zzqw zzb = new zzqw();
    private final int zzc;

    private zzsh(zzni zzniVar, int i) {
        this.zza = zzniVar;
        zzsn.zza();
        this.zzc = i;
    }

    public static zzrw zzf(zzni zzniVar, int i) {
        return new zzsh(zzniVar, i);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrw
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrw
    public final zzrw zzb(zznh zznhVar) {
        this.zza.zze(zznhVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrw
    public final zzrw zzc(zzqw zzqwVar) {
        this.zzb = zzqwVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrw
    public final String zzd() {
        String strZzk;
        zzqy zzqyVarZzd = this.zza.zzh().zzd();
        return (zzqyVarZzd == null || (strZzk = zzqyVarZzd.zzk()) == null || strZzk.isEmpty()) ? "NA" : (String) Preconditions.checkNotNull(zzqyVarZzd.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrw
    public final byte[] zze(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zzg(this.zzb.zzm());
        try {
            zzsn.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzlb.zza).ignoreNullValues(true).build().encode(this.zza.zzh()).getBytes("utf-8");
            }
            zznk zznkVarZzh = this.zza.zzh();
            zzbq zzbqVar = new zzbq();
            zzlb.zza.configure(zzbqVar);
            return zzbqVar.zza().zza(zznkVarZzh);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
