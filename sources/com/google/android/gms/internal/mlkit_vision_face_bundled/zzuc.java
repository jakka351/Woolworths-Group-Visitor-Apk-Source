package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public abstract class zzuc extends zztm {
    private static final Logger zzb = Logger.getLogger(zzuc.class.getName());
    private static final boolean zzc = zzxc.zzx();
    zzud zza;

    private zzuc() {
        throw null;
    }

    /* synthetic */ zzuc(zzub zzubVar) {
    }

    public static int zzA(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    @Deprecated
    static int zzw(int i, zzvw zzvwVar, zzwh zzwhVar) {
        int iZzz = zzz(i << 3);
        return iZzz + iZzz + ((zztf) zzvwVar).zzp(zzwhVar);
    }

    static int zzx(zzvw zzvwVar, zzwh zzwhVar) {
        int iZzp = ((zztf) zzvwVar).zzp(zzwhVar);
        return zzz(iZzp) + iZzp;
    }

    public static int zzy(String str) {
        int length;
        try {
            length = zzxf.zzc(str);
        } catch (zzxe unused) {
            length = str.getBytes(zzvc.zza).length;
        }
        return zzz(length) + length;
    }

    public static int zzz(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zzC(String str, zzxe zzxeVar) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzxeVar);
        byte[] bytes = str.getBytes(zzvc.zza);
        try {
            int length = bytes.length;
            zzt(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzua(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zztu zztuVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    abstract void zzm(int i, zzvw zzvwVar, zzwh zzwhVar) throws IOException;

    public abstract void zzn(int i, zzvw zzvwVar) throws IOException;

    public abstract void zzo(int i, zztu zztuVar) throws IOException;

    public abstract void zzp(int i, String str) throws IOException;

    public abstract void zzr(int i, int i2) throws IOException;

    public abstract void zzs(int i, int i2) throws IOException;

    public abstract void zzt(int i) throws IOException;

    public abstract void zzu(int i, long j) throws IOException;

    public abstract void zzv(long j) throws IOException;
}
