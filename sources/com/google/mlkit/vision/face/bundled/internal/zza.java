package com.google.mlkit.vision.face.bundled.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzac;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzad;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzaf;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzag;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzd;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzg;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzk;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzl;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzsp;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzst;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzsx;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzv;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzw;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzx;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzy;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzz;
import com.google.android.gms.vision.face.FaceDetectorV2Jni;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zza extends zzsx {
    private final Context zza;
    private final zzx zzb;
    private final FaceDetectorV2Jni zzc;
    private long zzd = -1;

    zza(Context context, zzst zzstVar, FaceDetectorV2Jni faceDetectorV2Jni) {
        this.zza = context;
        int iZzc = zzstVar.zzc();
        zzaf zzafVarZza = zzag.zza();
        zzafVarZza.zzb("models_bundled");
        zzag zzagVar = (zzag) zzafVarZza.zzn();
        int iZze = zzstVar.zze();
        zzy zzyVarZza = zzz.zza();
        zzaf zzafVarZza2 = zzag.zza();
        zzafVarZza2.zzb("models_bundled");
        zzafVarZza2.zza(iZze == 2 ? "fssd_medium_8bit_v5.tflite" : "fssd_25_8bit_v2.tflite");
        zzyVarZza.zzc((zzag) zzafVarZza2.zzn());
        zzaf zzafVarZza3 = zzag.zza();
        zzafVarZza3.zzb("models_bundled");
        zzafVarZza3.zza(iZze == 2 ? "fssd_medium_8bit_gray_v5.tflite" : "fssd_25_8bit_gray_v2.tflite");
        zzyVarZza.zzb((zzag) zzafVarZza3.zzn());
        zzaf zzafVarZza4 = zzag.zza();
        zzafVarZza4.zzb("models_bundled");
        zzafVarZza4.zza(iZze == 2 ? "fssd_anchors_v5.pb" : "fssd_anchors_v2.pb");
        zzyVarZza.zza((zzag) zzafVarZza4.zzn());
        zzyVarZza.zzd(zzagVar);
        zzz zzzVar = (zzz) zzyVarZza.zzn();
        zzw zzwVarZza = zzx.zza();
        zzwVarZza.zzd(zzzVar);
        zzk zzkVarZza = zzl.zza();
        zzkVarZza.zza(zzagVar);
        zzkVarZza.zzb(zzagVar);
        zzwVarZza.zza(zzkVarZza);
        zzac zzacVarZza = zzad.zza();
        zzacVarZza.zzb(zzagVar);
        zzacVarZza.zzc(zzagVar);
        zzacVarZza.zzd(zzagVar);
        zzacVarZza.zza(zzagVar);
        zzwVarZza.zze(zzacVarZza);
        boolean z = false;
        boolean z2 = iZzc == 2;
        zzwVarZza.zzg(z2);
        if (!z2 && zzstVar.zzf()) {
            z = true;
        }
        zzwVarZza.zzb(z);
        zzwVarZza.zzf(zzstVar.zza());
        zzwVarZza.zzh(true);
        if (z2) {
            zzwVarZza.zzk(4);
            zzwVarZza.zzj(4);
        } else {
            int iZze2 = zzstVar.zze();
            if (iZze2 == 1) {
                zzwVarZza.zzk(2);
            } else if (iZze2 == 2) {
                zzwVarZza.zzk(3);
            }
            int iZzd = zzstVar.zzd();
            if (iZzd == 1) {
                zzwVarZza.zzj(2);
            } else if (iZzd == 2) {
                zzwVarZza.zzj(3);
            }
            int iZzb = zzstVar.zzb();
            if (iZzb == 1) {
                zzwVarZza.zzi(2);
            } else if (iZzb == 2) {
                zzwVarZza.zzi(3);
            }
        }
        this.zzb = (zzx) zzwVarZza.zzn();
        this.zzc = faceDetectorV2Jni;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List zze(com.google.android.gms.internal.mlkit_vision_face_bundled.zzv r29) {
        /*
            Method dump skipped, instructions count: 610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.face.bundled.internal.zza.zze(com.google.android.gms.internal.mlkit_vision_face_bundled.zzv):java.util.List");
    }

    private static int zzf(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 2;
        }
        throw new IllegalArgumentException("Unsupported rotation degree: " + i);
    }

    private final List zzg(ByteBuffer byteBuffer, zzsp zzspVar, int i) throws RemoteException {
        zzv zzvVarZzb;
        zzg zzgVarZza = zzh.zza();
        zzgVarZza.zzc(zzspVar.zzd());
        zzgVarZza.zza(zzspVar.zza());
        zzgVarZza.zze(zzf(zzspVar.zzc()));
        zzgVarZza.zzd(i);
        if (zzspVar.zze() > 0) {
            zzgVarZza.zzb(zzspVar.zze() * 1000);
        }
        zzh zzhVar = (zzh) zzgVarZza.zzn();
        if (byteBuffer.isDirect()) {
            zzvVarZzb = this.zzc.zzd(this.zzd, byteBuffer, zzhVar);
        } else if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            zzvVarZzb = this.zzc.zzb(this.zzd, byteBuffer.array(), zzhVar);
        } else {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            zzvVarZzb = this.zzc.zzb(this.zzd, bArr, zzhVar);
        }
        return zzvVarZzb != null ? zze(zzvVarZzb) : new ArrayList();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy
    public final List zzb(IObjectWrapper iObjectWrapper, zzsp zzspVar) throws RemoteException {
        zzv zzvVarZzc;
        int iZzb = zzspVar.zzb();
        if (iZzb == -1) {
            return zzg(zzd.zza((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), true), zzspVar, 2);
        }
        if (iZzb == 17) {
            return zzg((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzspVar, 2);
        }
        if (iZzb != 35) {
            if (iZzb == 842094169) {
                return zzg((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzspVar, 7);
            }
            String str = "Unsupported image format " + zzspVar.zzb() + " at API " + Build.VERSION.SDK_INT;
            Log.e("ThickFaceDetector", str);
            throw new RemoteException(str);
        }
        Image.Plane[] planes = ((Image) ObjectWrapper.unwrap(iObjectWrapper)).getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        ByteBuffer buffer2 = planes[1].getBuffer();
        ByteBuffer buffer3 = planes[2].getBuffer();
        zzg zzgVarZza = zzh.zza();
        zzgVarZza.zzc(zzspVar.zzd());
        zzgVarZza.zza(zzspVar.zza());
        zzgVarZza.zze(zzf(zzspVar.zzc()));
        if (zzspVar.zze() > 0) {
            zzgVarZza.zzb(zzspVar.zze() * 1000);
        }
        zzh zzhVar = (zzh) zzgVarZza.zzn();
        if (buffer.isDirect()) {
            zzvVarZzc = this.zzc.zze(this.zzd, buffer, buffer2, buffer3, planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), zzhVar);
        } else if (buffer.hasArray() && buffer.arrayOffset() == 0) {
            zzvVarZzc = this.zzc.zzc(this.zzd, buffer.array(), buffer2.array(), buffer3.array(), planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), zzhVar);
        } else {
            byte[] bArr = new byte[buffer.remaining()];
            buffer.get(bArr);
            byte[] bArr2 = new byte[buffer2.remaining()];
            buffer.get(bArr);
            byte[] bArr3 = new byte[buffer3.remaining()];
            buffer.get(bArr);
            zzvVarZzc = this.zzc.zzc(this.zzd, bArr, bArr2, bArr3, planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), zzhVar);
        }
        return zzvVarZzc != null ? zze(zzvVarZzc) : new ArrayList();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy
    public final void zzc() {
        this.zzd = this.zzc.zza(this.zzb, this.zza.getAssets());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy
    public final void zzd() {
        long j = this.zzd;
        if (j > 0) {
            this.zzc.zzf(j);
            this.zzd = -1L;
        }
    }
}
