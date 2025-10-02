package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzop extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzop> CREATOR = new zzpc();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final zzoo zzf;
    private final zzoo zzg;

    public zzop(String str, String str2, String str3, String str4, String str5, zzoo zzooVar, zzoo zzooVar2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = zzooVar;
        this.zzg = zzooVar2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final zzoo zza() {
        return this.zzg;
    }

    public final zzoo zzb() {
        return this.zzf;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final String zze() {
        return this.zzd;
    }

    public final String zzf() {
        return this.zze;
    }

    public final String zzg() {
        return this.zza;
    }
}
