package com.google.android.gms.vision.face.mlkit;

import android.os.RemoteException;
import com.google.android.gms.common.util.PlatformVersion;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zze {
    static RemoteException zza(String str) {
        return PlatformVersion.isAtLeastIceCreamSandwichMR1() ? new RemoteException(str) : new RemoteException();
    }
}
