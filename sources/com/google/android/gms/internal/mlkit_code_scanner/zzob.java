package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzob implements zznq {
    private final zzkc zza;
    private zzmq zzb = new zzmq();

    private zzob(zzkc zzkcVar, int i) {
        this.zza = zzkcVar;
        zzok.zza();
    }

    public static zznq zze(zzkc zzkcVar) {
        return new zzob(zzkcVar, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final zznq zza(zzkb zzkbVar) {
        this.zza.zzd(zzkbVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final zznq zzb(zzmq zzmqVar) {
        this.zzb = zzmqVar;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final String zzc() {
        zzms zzmsVarZzc = this.zza.zzf().zzc();
        return (zzmsVarZzc == null || zzg.zzb(zzmsVarZzc.zzk())) ? "NA" : (String) Preconditions.checkNotNull(zzmsVarZzc.zzk());
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznq
    public final byte[] zzd(int i, boolean z) {
        this.zzb.zzf(Boolean.valueOf(1 == (i ^ 1)));
        this.zzb.zze(false);
        this.zza.zze(this.zzb.zzm());
        try {
            zzok.zza();
            if (i == 0) {
                return new JsonDataEncoderBuilder().configureWith(zzig.zza).ignoreNullValues(true).build().encode(this.zza.zzf()).getBytes("utf-8");
            }
            zzke zzkeVarZzf = this.zza.zzf();
            zzal zzalVar = new zzal();
            zzig.zza.configure(zzalVar);
            return zzalVar.zza().zza(zzkeVarZzf);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Failed to covert logging to UTF-8 byte array", e);
        }
    }
}
