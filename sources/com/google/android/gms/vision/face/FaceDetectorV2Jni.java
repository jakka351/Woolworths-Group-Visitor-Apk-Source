package com.google.android.gms.vision.face;

import android.content.res.AssetManager;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzah;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzuh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzv;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzve;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzx;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public class FaceDetectorV2Jni {
    private final zzuh zza;

    public FaceDetectorV2Jni() {
        zzuh zzuhVarZza = zzuh.zza();
        this.zza = zzuhVarZza;
        zzuhVarZza.zzc(zzah.zza);
    }

    private native void closeDetectorJni(long j);

    private native byte[] detectFacesImageByteArrayJni(long j, byte[] bArr, byte[] bArr2);

    private native byte[] detectFacesImageByteArrayMultiPlanesJni(long j, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr4);

    private native byte[] detectFacesImageByteBufferJni(long j, ByteBuffer byteBuffer, byte[] bArr);

    private native byte[] detectFacesImageByteBufferMultiPlanesJni(long j, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr);

    private native long initDetectorJni(byte[] bArr, AssetManager assetManager);

    public final long zza(zzx zzxVar, AssetManager assetManager) {
        Log.v("FaceDetectorV2Jni", "initialize.start()");
        long jInitDetectorJni = initDetectorJni(zzxVar.zzr(), assetManager);
        Log.v("FaceDetectorV2Jni", "initialize.end()");
        return jInitDetectorJni;
    }

    public final zzv zzb(long j, byte[] bArr, zzh zzhVar) throws RemoteException {
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteArray.start()");
        zzv zzvVarZzb = null;
        try {
            byte[] bArrDetectFacesImageByteArrayJni = detectFacesImageByteArrayJni(j, bArr, zzhVar.zzr());
            if (bArrDetectFacesImageByteArrayJni != null && bArrDetectFacesImageByteArrayJni.length > 0) {
                zzvVarZzb = zzv.zzb(bArrDetectFacesImageByteArrayJni, this.zza);
            }
        } catch (zzve e) {
            Log.e("FaceDetectorV2Jni", "detectFacesImageByteArray failed to parse result: ".concat(String.valueOf(e.getMessage())));
        }
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteArray.end()");
        return zzvVarZzb;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.mlkit_vision_face_bundled.zzv zzc(long r17, byte[] r19, byte[] r20, byte[] r21, int r22, int r23, int r24, int r25, int r26, int r27, com.google.android.gms.internal.mlkit_vision_face_bundled.zzh r28) {
        /*
            r16 = this;
            java.lang.String r0 = "detectFacesImageByteArrayMultiPlanes.start()"
            java.lang.String r1 = "FaceDetectorV2Jni"
            android.util.Log.v(r1, r0)
            r2 = 0
            byte[] r15 = r28.zzr()     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L39
            r3 = r16
            r4 = r17
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r13 = r26
            r14 = r27
            byte[] r0 = r3.detectFacesImageByteArrayMultiPlanesJni(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L39
            if (r0 == 0) goto L36
            int r3 = r0.length     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L39
            if (r3 <= 0) goto L36
            r3 = r16
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuh r4 = r3.zza     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L34
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzv r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzv.zzb(r0, r4)     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L34
            goto L4d
        L34:
            r0 = move-exception
            goto L3c
        L36:
            r3 = r16
            goto L4d
        L39:
            r0 = move-exception
            r3 = r16
        L3c:
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = "detectFacesImageByteArrayMultiPlanes failed to parse result: "
            java.lang.String r0 = r4.concat(r0)
            android.util.Log.e(r1, r0)
        L4d:
            java.lang.String r0 = "%s detectFacesImageByteArrayMultiPlanes.end()"
            android.util.Log.v(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.face.FaceDetectorV2Jni.zzc(long, byte[], byte[], byte[], int, int, int, int, int, int, com.google.android.gms.internal.mlkit_vision_face_bundled.zzh):com.google.android.gms.internal.mlkit_vision_face_bundled.zzv");
    }

    public final zzv zzd(long j, ByteBuffer byteBuffer, zzh zzhVar) throws RemoteException {
        Log.v("FaceDetectorV2Jni", "detectFacesImageByteBuffer.start()");
        zzv zzvVarZzb = null;
        try {
            byte[] bArrDetectFacesImageByteBufferJni = detectFacesImageByteBufferJni(j, byteBuffer, zzhVar.zzr());
            if (bArrDetectFacesImageByteBufferJni != null && bArrDetectFacesImageByteBufferJni.length > 0) {
                zzvVarZzb = zzv.zzb(bArrDetectFacesImageByteBufferJni, this.zza);
            }
        } catch (zzve e) {
            Log.e("FaceDetectorV2Jni", "detectFacesImageByteBuffer failed to parse result: ".concat(String.valueOf(e.getMessage())));
        }
        Log.v("FaceDetectorV2Jni", "%s detectFacesImageByteBuffer.end()");
        return zzvVarZzb;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.mlkit_vision_face_bundled.zzv zze(long r17, java.nio.ByteBuffer r19, java.nio.ByteBuffer r20, java.nio.ByteBuffer r21, int r22, int r23, int r24, int r25, int r26, int r27, com.google.android.gms.internal.mlkit_vision_face_bundled.zzh r28) {
        /*
            r16 = this;
            java.lang.String r0 = "detectFacesImageByteBufferMultiPlanes.start()"
            java.lang.String r1 = "FaceDetectorV2Jni"
            android.util.Log.v(r1, r0)
            r2 = 0
            byte[] r15 = r28.zzr()     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L39
            r3 = r16
            r4 = r17
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r13 = r26
            r14 = r27
            byte[] r0 = r3.detectFacesImageByteBufferMultiPlanesJni(r4, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L39
            if (r0 == 0) goto L36
            int r3 = r0.length     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L39
            if (r3 <= 0) goto L36
            r3 = r16
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuh r4 = r3.zza     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L34
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzv r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzv.zzb(r0, r4)     // Catch: com.google.android.gms.internal.mlkit_vision_face_bundled.zzve -> L34
            goto L4d
        L34:
            r0 = move-exception
            goto L3c
        L36:
            r3 = r16
            goto L4d
        L39:
            r0 = move-exception
            r3 = r16
        L3c:
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r4 = "detectFacesImageByteBufferMultiPlanes failed to parse result: "
            java.lang.String r0 = r4.concat(r0)
            android.util.Log.e(r1, r0)
        L4d:
            java.lang.String r0 = "detectFacesImageByteBuffer.end()"
            android.util.Log.v(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.face.FaceDetectorV2Jni.zze(long, java.nio.ByteBuffer, java.nio.ByteBuffer, java.nio.ByteBuffer, int, int, int, int, int, int, com.google.android.gms.internal.mlkit_vision_face_bundled.zzh):com.google.android.gms.internal.mlkit_vision_face_bundled.zzv");
    }

    public final void zzf(long j) {
        Log.v("FaceDetectorV2Jni", "closeDetector.start()");
        closeDetectorJni(j);
        Log.v("FaceDetectorV2Jni", "closeDetector.end()");
    }
}
