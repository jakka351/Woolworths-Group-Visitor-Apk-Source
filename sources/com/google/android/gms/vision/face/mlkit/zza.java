package com.google.android.gms.vision.face.mlkit;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzac;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzad;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzaf;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzag;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzg;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzh;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzk;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzl;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzsg;
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
    private static final GmsLogger zza = new GmsLogger("FaceDetector", "");
    private final Context zzb;
    private final zzst zzc;
    private final zzx zzd;
    private final FaceDetectorV2Jni zze;
    private final zzb zzf;
    private final zzsg zzg;
    private long zzh = -1;

    zza(Context context, zzst zzstVar, FaceDetectorV2Jni faceDetectorV2Jni, zzb zzbVar) {
        this.zzb = context;
        this.zzc = zzstVar;
        int iZzc = zzstVar.zzc();
        zzaf zzafVarZza = zzag.zza();
        zzafVarZza.zzb("models");
        zzag zzagVar = (zzag) zzafVarZza.zzn();
        zzw zzwVarZza = zzx.zza();
        zzy zzyVarZza = zzz.zza();
        zzyVarZza.zzc(zzagVar);
        zzyVarZza.zzb(zzagVar);
        zzyVarZza.zzd(zzagVar);
        zzwVarZza.zzc(zzyVarZza);
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
            int iZze = zzstVar.zze();
            if (iZze == 1) {
                zzwVarZza.zzk(2);
            } else if (iZze == 2) {
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
        this.zzd = (zzx) zzwVarZza.zzn();
        this.zze = faceDetectorV2Jni;
        this.zzf = zzbVar;
        this.zzg = zzsg.zza(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.util.List zze(com.google.android.gms.internal.mlkit_vision_face_bundled.zzv r29) {
        /*
            Method dump skipped, instructions count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.face.mlkit.zza.zze(com.google.android.gms.internal.mlkit_vision_face_bundled.zzv):java.util.List");
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
            zzvVarZzb = this.zze.zzd(this.zzh, byteBuffer, zzhVar);
        } else if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            zzvVarZzb = this.zze.zzb(this.zzh, byteBuffer.array(), zzhVar);
        } else {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            zzvVarZzb = this.zze.zzb(this.zzh, bArr, zzhVar);
        }
        return zzvVarZzb != null ? zze(zzvVarZzb) : new ArrayList();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy
    public final List zzb(IObjectWrapper iObjectWrapper, zzsp zzspVar) throws Throwable {
        List listZzg;
        zzv zzvVarZzc;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jCurrentTimeMillis = System.currentTimeMillis();
        int iZzb = zzspVar.zzb();
        if (iZzb == -1) {
            listZzg = zzg(com.google.android.gms.internal.mlkit_vision_face_bundled.zzd.zza((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), true), zzspVar, 2);
        } else if (iZzb == 17) {
            listZzg = zzg((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzspVar, 2);
        } else if (iZzb == 35) {
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
                zzvVarZzc = this.zze.zze(this.zzh, buffer, buffer2, buffer3, planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), zzhVar);
            } else if (buffer.hasArray() && buffer.arrayOffset() == 0) {
                zzvVarZzc = this.zze.zzc(this.zzh, buffer.array(), buffer2.array(), buffer3.array(), planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), zzhVar);
            } else {
                byte[] bArr = new byte[buffer.remaining()];
                buffer.get(bArr);
                byte[] bArr2 = new byte[buffer2.remaining()];
                buffer.get(bArr);
                byte[] bArr3 = new byte[buffer3.remaining()];
                buffer.get(bArr);
                zzvVarZzc = this.zze.zzc(this.zzh, bArr, bArr2, bArr3, planes[0].getPixelStride(), planes[1].getPixelStride(), planes[2].getPixelStride(), planes[0].getRowStride(), planes[1].getRowStride(), planes[2].getRowStride(), zzhVar);
            }
            listZzg = zzvVarZzc != null ? zze(zzvVarZzc) : new ArrayList();
        } else {
            if (iZzb != 842094169) {
                String str = "Unsupported image format " + zzspVar.zzb() + " at API " + Build.VERSION.SDK_INT;
                Log.e("FaceDetector", str);
                this.zzg.zzc(25503, 1, jCurrentTimeMillis, System.currentTimeMillis());
                throw zze.zza(str);
            }
            listZzg = zzg((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzspVar, 7);
        }
        List list = listZzg;
        this.zzf.zza(this.zzc, zzspVar, list, SystemClock.elapsedRealtime() - jElapsedRealtime);
        this.zzg.zzc(25503, 0, jCurrentTimeMillis, System.currentTimeMillis());
        return list;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy
    public final void zzc() throws Throwable {
        this.zzh = this.zze.zza(this.zzd, this.zzb.getAssets());
        this.zzf.zzc(this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzsy
    public final void zzd() {
        long j = this.zzh;
        if (j > 0) {
            this.zze.zzf(j);
            this.zzh = -1L;
        }
    }
}
