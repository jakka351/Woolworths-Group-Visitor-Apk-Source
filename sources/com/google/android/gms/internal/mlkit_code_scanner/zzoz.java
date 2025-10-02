package com.google.android.gms.internal.mlkit_code_scanner;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzoz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzoz> CREATOR = new zzpa();
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final byte[] zzd;
    private final Point[] zze;
    private final int zzf;
    private final zzos zzg;
    private final zzov zzh;
    private final zzow zzi;
    private final zzoy zzj;
    private final zzox zzk;
    private final zzot zzl;
    private final zzop zzm;
    private final zzoq zzn;
    private final zzor zzo;

    public zzoz(int i, String str, String str2, byte[] bArr, Point[] pointArr, int i2, zzos zzosVar, zzov zzovVar, zzow zzowVar, zzoy zzoyVar, zzox zzoxVar, zzot zzotVar, zzop zzopVar, zzoq zzoqVar, zzor zzorVar) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bArr;
        this.zze = pointArr;
        this.zzf = i2;
        this.zzg = zzosVar;
        this.zzh = zzovVar;
        this.zzi = zzowVar;
        this.zzj = zzoyVar;
        this.zzk = zzoxVar;
        this.zzl = zzotVar;
        this.zzm = zzopVar;
        this.zzn = zzoqVar;
        this.zzo = zzorVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zze, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzf);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzh, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzi, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.zzm, i, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzn, i, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzo, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        return this.zzf;
    }

    public final zzop zzc() {
        return this.zzm;
    }

    public final zzoq zzd() {
        return this.zzn;
    }

    public final zzor zze() {
        return this.zzo;
    }

    public final zzos zzf() {
        return this.zzg;
    }

    public final zzot zzg() {
        return this.zzl;
    }

    public final zzov zzh() {
        return this.zzh;
    }

    public final zzow zzi() {
        return this.zzi;
    }

    public final zzox zzj() {
        return this.zzk;
    }

    public final zzoy zzk() {
        return this.zzj;
    }

    public final String zzl() {
        return this.zzb;
    }

    public final String zzm() {
        return this.zzc;
    }

    public final byte[] zzn() {
        return this.zzd;
    }
}
