package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzpd implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzou zzouVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        zzov[] zzovVarArr = null;
        zzos[] zzosVarArr = null;
        String[] strArrCreateStringArray = null;
        zzon[] zzonVarArr = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    zzouVar = (zzou) SafeParcelReader.createParcelable(parcel, header, zzou.CREATOR);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    zzovVarArr = (zzov[]) SafeParcelReader.createTypedArray(parcel, header, zzov.CREATOR);
                    break;
                case 5:
                    zzosVarArr = (zzos[]) SafeParcelReader.createTypedArray(parcel, header, zzos.CREATOR);
                    break;
                case 6:
                    strArrCreateStringArray = SafeParcelReader.createStringArray(parcel, header);
                    break;
                case 7:
                    zzonVarArr = (zzon[]) SafeParcelReader.createTypedArray(parcel, header, zzon.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzoq(zzouVar, strCreateString, strCreateString2, zzovVarArr, zzosVarArr, strArrCreateStringArray, zzonVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzoq[i];
    }
}
