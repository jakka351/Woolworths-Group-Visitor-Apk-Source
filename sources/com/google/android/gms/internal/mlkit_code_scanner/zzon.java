package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzon extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzon> CREATOR = new zzom();
    private final int zza;
    private final String[] zzb;

    public zzon(int i, String[] strArr) {
        this.zza = i;
        this.zzb = strArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int zza() {
        return this.zza;
    }

    public final String[] zzb() {
        return this.zzb;
    }
}
