package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzoq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoq> CREATOR = new zzpd();
    private final zzou zza;
    private final String zzb;
    private final String zzc;
    private final zzov[] zzd;
    private final zzos[] zze;
    private final String[] zzf;
    private final zzon[] zzg;

    public zzoq(zzou zzouVar, String str, String str2, zzov[] zzovVarArr, zzos[] zzosVarArr, String[] strArr, zzon[] zzonVarArr) {
        this.zza = zzouVar;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzovVarArr;
        this.zze = zzosVarArr;
        this.zzf = strArr;
        this.zzg = zzonVarArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeTypedArray(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzf, false);
        SafeParcelWriter.writeTypedArray(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final zzou zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final zzon[] zzd() {
        return this.zzg;
    }

    public final zzos[] zze() {
        return this.zze;
    }

    public final zzov[] zzf() {
        return this.zzd;
    }

    public final String[] zzg() {
        return this.zzf;
    }
}
