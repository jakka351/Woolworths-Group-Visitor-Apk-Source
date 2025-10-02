package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzsv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzsv> CREATOR = new zzsw();
    private final int zza;
    private final Rect zzb;
    private final float zzc;
    private final float zzd;
    private final float zze;
    private final float zzf;
    private final float zzg;
    private final float zzh;
    private final float zzi;
    private final List zzj;
    private final List zzk;

    public zzsv(int i, Rect rect, float f, float f2, float f3, float f4, float f5, float f6, float f7, List list, List list2) {
        this.zza = i;
        this.zzb = rect;
        this.zzc = f;
        this.zzd = f2;
        this.zze = f3;
        this.zzf = f4;
        this.zzg = f5;
        this.zzh = f6;
        this.zzi = f7;
        this.zzj = list;
        this.zzk = list2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zza;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzc);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzd);
        SafeParcelWriter.writeFloat(parcel, 5, this.zze);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzf);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzg);
        SafeParcelWriter.writeFloat(parcel, 8, this.zzh);
        SafeParcelWriter.writeFloat(parcel, 9, this.zzi);
        SafeParcelWriter.writeTypedList(parcel, 10, this.zzj, false);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzk, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
