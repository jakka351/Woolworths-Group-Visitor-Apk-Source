package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public abstract class zzsx extends zzb implements zzsy {
    public zzsx() {
        super("com.google.mlkit.vision.face.aidls.IFaceDetector");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc();
            parcel2.writeNoException();
        } else if (i == 2) {
            zzd();
            parcel2.writeNoException();
        } else {
            if (i != 3) {
                return false;
            }
            IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
            zzsp zzspVar = (zzsp) zzc.zza(parcel, zzsp.CREATOR);
            zzc.zzb(parcel);
            List listZzb = zzb(iObjectWrapperAsInterface, zzspVar);
            parcel2.writeNoException();
            parcel2.writeTypedList(listZzb);
        }
        return true;
    }
}
