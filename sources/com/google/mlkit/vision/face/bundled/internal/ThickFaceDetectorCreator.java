package com.google.mlkit.vision.face.bundled.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzst;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzta;
import com.google.android.gms.vision.face.FaceDetectorV2Jni;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public class ThickFaceDetectorCreator extends zzta {
    static {
        System.loadLibrary("face_detector_v2_jni");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztb
    public zzsy newFaceDetector(IObjectWrapper iObjectWrapper, zzst zzstVar) throws RemoteException {
        return new zza((Context) ObjectWrapper.unwrap(iObjectWrapper), zzstVar, new FaceDetectorV2Jni());
    }
}
