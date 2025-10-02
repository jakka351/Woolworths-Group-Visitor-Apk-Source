package com.google.android.gms.internal.mlkit_vision_face_bundled;

import sun.misc.Unsafe;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzwz extends zzxb {
    zzwz(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final void zzc(Object obj, long j, boolean z) {
        if (zzxc.zzb) {
            zzxc.zzD(obj, j, z ? (byte) 1 : (byte) 0);
        } else {
            zzxc.zzE(obj, j, z ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final void zzd(Object obj, long j, byte b) {
        if (zzxc.zzb) {
            zzxc.zzD(obj, j, b);
        } else {
            zzxc.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzxb
    public final boolean zzg(Object obj, long j) {
        return zzxc.zzb ? zzxc.zzt(obj, j) : zzxc.zzu(obj, j);
    }
}
