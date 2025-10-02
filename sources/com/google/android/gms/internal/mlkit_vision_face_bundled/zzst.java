package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzst extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzst> CREATOR = new zzsu();
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final boolean zze;
    private final float zzf;

    public zzst(int i, int i2, int i3, int i4, boolean z, float f) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = z;
        this.zzf = f;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zze);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final float zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzc;
    }

    public final int zzc() {
        return this.zzd;
    }

    public final int zzd() {
        return this.zzb;
    }

    public final int zze() {
        return this.zza;
    }

    public final boolean zzf() {
        return this.zze;
    }
}
