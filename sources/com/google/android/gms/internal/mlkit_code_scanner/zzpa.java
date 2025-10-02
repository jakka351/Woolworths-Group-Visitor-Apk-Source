package com.google.android.gms.internal.mlkit_code_scanner;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzpa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        byte[] bArrCreateByteArray = null;
        Point[] pointArr = null;
        zzos zzosVar = null;
        zzov zzovVar = null;
        zzow zzowVar = null;
        zzoy zzoyVar = null;
        zzox zzoxVar = null;
        zzot zzotVar = null;
        zzop zzopVar = null;
        zzoq zzoqVar = null;
        zzor zzorVar = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    break;
                case 5:
                    pointArr = (Point[]) SafeParcelReader.createTypedArray(parcel, header, Point.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    zzosVar = (zzos) SafeParcelReader.createParcelable(parcel, header, zzos.CREATOR);
                    break;
                case 8:
                    zzovVar = (zzov) SafeParcelReader.createParcelable(parcel, header, zzov.CREATOR);
                    break;
                case 9:
                    zzowVar = (zzow) SafeParcelReader.createParcelable(parcel, header, zzow.CREATOR);
                    break;
                case 10:
                    zzoyVar = (zzoy) SafeParcelReader.createParcelable(parcel, header, zzoy.CREATOR);
                    break;
                case 11:
                    zzoxVar = (zzox) SafeParcelReader.createParcelable(parcel, header, zzox.CREATOR);
                    break;
                case 12:
                    zzotVar = (zzot) SafeParcelReader.createParcelable(parcel, header, zzot.CREATOR);
                    break;
                case 13:
                    zzopVar = (zzop) SafeParcelReader.createParcelable(parcel, header, zzop.CREATOR);
                    break;
                case 14:
                    zzoqVar = (zzoq) SafeParcelReader.createParcelable(parcel, header, zzoq.CREATOR);
                    break;
                case 15:
                    zzorVar = (zzor) SafeParcelReader.createParcelable(parcel, header, zzor.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzoz(i, strCreateString, strCreateString2, bArrCreateByteArray, pointArr, i2, zzosVar, zzovVar, zzowVar, zzoyVar, zzoxVar, zzotVar, zzopVar, zzoqVar, zzorVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzoz[i];
    }
}
