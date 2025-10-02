package com.google.android.gms.internal.mlkit_vision_face_bundled;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.facebook.soloader.Elf64_Ehdr;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzvz<T> implements zzwh<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzxc.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzvw zzg;
    private final boolean zzh;
    private final int[] zzi;
    private final int zzj;
    private final int zzk;
    private final zzwv zzl;
    private final zzui zzm;

    private zzvz(int[] iArr, Object[] objArr, int i, int i2, zzvw zzvwVar, boolean z, int[] iArr2, int i3, int i4, zzwc zzwcVar, zzvj zzvjVar, zzwv zzwvVar, zzui zzuiVar, zzvr zzvrVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i;
        this.zzf = i2;
        boolean z2 = false;
        if (zzuiVar != null && (zzvwVar instanceof zzus)) {
            z2 = true;
        }
        this.zzh = z2;
        this.zzi = iArr2;
        this.zzj = i3;
        this.zzk = i4;
        this.zzl = zzwvVar;
        this.zzm = zzuiVar;
        this.zzg = zzvwVar;
    }

    private static void zzA(Object obj) {
        if (!zzL(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private final void zzB(Object obj, Object obj2, int i) {
        if (zzI(obj2, i)) {
            int iZzs = zzs(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzs;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzwh zzwhVarZzv = zzv(i);
            if (!zzI(obj, i)) {
                if (zzL(object)) {
                    Object objZze = zzwhVarZzv.zze();
                    zzwhVarZzv.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzD(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzL(object2)) {
                Object objZze2 = zzwhVarZzv.zze();
                zzwhVarZzv.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzwhVarZzv.zzg(object2, object);
        }
    }

    private final void zzC(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzM(obj2, i2, i)) {
            int iZzs = zzs(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = iZzs;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzwh zzwhVarZzv = zzv(i);
            if (!zzM(obj, i2, i)) {
                if (zzL(object)) {
                    Object objZze = zzwhVarZzv.zze();
                    zzwhVarZzv.zzg(objZze, object);
                    unsafe.putObject(obj, j, objZze);
                } else {
                    unsafe.putObject(obj, j, object);
                }
                zzE(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzL(object2)) {
                Object objZze2 = zzwhVarZzv.zze();
                zzwhVarZzv.zzg(objZze2, object2);
                unsafe.putObject(obj, j, objZze2);
                object2 = objZze2;
            }
            zzwhVarZzv.zzg(object2, object);
        }
    }

    private final void zzD(Object obj, int i) {
        int iZzp = zzp(i);
        long j = 1048575 & iZzp;
        if (j == 1048575) {
            return;
        }
        zzxc.zzq(obj, j, (1 << (iZzp >>> 20)) | zzxc.zzc(obj, j));
    }

    private final void zzE(Object obj, int i, int i2) {
        zzxc.zzq(obj, zzp(i2) & 1048575, i);
    }

    private final void zzF(Object obj, int i, Object obj2) {
        zzb.putObject(obj, zzs(i) & 1048575, obj2);
        zzD(obj, i);
    }

    private final void zzG(Object obj, int i, int i2, Object obj2) {
        zzb.putObject(obj, zzs(i2) & 1048575, obj2);
        zzE(obj, i, i2);
    }

    private final boolean zzH(Object obj, Object obj2, int i) {
        return zzI(obj, i) == zzI(obj2, i);
    }

    private final boolean zzI(Object obj, int i) {
        int iZzp = zzp(i);
        long j = iZzp & 1048575;
        if (j != 1048575) {
            return (zzxc.zzc(obj, j) & (1 << (iZzp >>> 20))) != 0;
        }
        int iZzs = zzs(i);
        long j2 = iZzs & 1048575;
        switch (zzr(iZzs)) {
            case 0:
                return Double.doubleToRawLongBits(zzxc.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzxc.zzb(obj, j2)) != 0;
            case 2:
                return zzxc.zzd(obj, j2) != 0;
            case 3:
                return zzxc.zzd(obj, j2) != 0;
            case 4:
                return zzxc.zzc(obj, j2) != 0;
            case 5:
                return zzxc.zzd(obj, j2) != 0;
            case 6:
                return zzxc.zzc(obj, j2) != 0;
            case 7:
                return zzxc.zzw(obj, j2);
            case 8:
                Object objZzf = zzxc.zzf(obj, j2);
                if (objZzf instanceof String) {
                    return !((String) objZzf).isEmpty();
                }
                if (objZzf instanceof zztu) {
                    return !zztu.zzb.equals(objZzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzxc.zzf(obj, j2) != null;
            case 10:
                return !zztu.zzb.equals(zzxc.zzf(obj, j2));
            case 11:
                return zzxc.zzc(obj, j2) != 0;
            case 12:
                return zzxc.zzc(obj, j2) != 0;
            case 13:
                return zzxc.zzc(obj, j2) != 0;
            case 14:
                return zzxc.zzd(obj, j2) != 0;
            case 15:
                return zzxc.zzc(obj, j2) != 0;
            case 16:
                return zzxc.zzd(obj, j2) != 0;
            case 17:
                return zzxc.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzJ(Object obj, int i, int i2, int i3, int i4) {
        return i2 == 1048575 ? zzI(obj, i) : (i3 & i4) != 0;
    }

    private static boolean zzK(Object obj, int i, zzwh zzwhVar) {
        return zzwhVar.zzk(zzxc.zzf(obj, i & 1048575));
    }

    private static boolean zzL(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzuw) {
            return ((zzuw) obj).zzI();
        }
        return true;
    }

    private final boolean zzM(Object obj, int i, int i2) {
        return zzxc.zzc(obj, (long) (zzp(i2) & 1048575)) == i;
    }

    private static boolean zzN(Object obj, long j) {
        return ((Boolean) zzxc.zzf(obj, j)).booleanValue();
    }

    private static final void zzO(int i, Object obj, zzxi zzxiVar) throws IOException {
        if (obj instanceof String) {
            zzxiVar.zzG(i, (String) obj);
        } else {
            zzxiVar.zzd(i, (zztu) obj);
        }
    }

    static zzww zzd(Object obj) {
        zzuw zzuwVar = (zzuw) obj;
        zzww zzwwVar = zzuwVar.zzc;
        if (zzwwVar != zzww.zzc()) {
            return zzwwVar;
        }
        zzww zzwwVarZzf = zzww.zzf();
        zzuwVar.zzc = zzwwVarZzf;
        return zzwwVarZzf;
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0398  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz zzl(java.lang.Class r34, com.google.android.gms.internal.mlkit_vision_face_bundled.zzvt r35, com.google.android.gms.internal.mlkit_vision_face_bundled.zzwc r36, com.google.android.gms.internal.mlkit_vision_face_bundled.zzvj r37, com.google.android.gms.internal.mlkit_vision_face_bundled.zzwv r38, com.google.android.gms.internal.mlkit_vision_face_bundled.zzui r39, com.google.android.gms.internal.mlkit_vision_face_bundled.zzvr r40) {
        /*
            Method dump skipped, instructions count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzl(java.lang.Class, com.google.android.gms.internal.mlkit_vision_face_bundled.zzvt, com.google.android.gms.internal.mlkit_vision_face_bundled.zzwc, com.google.android.gms.internal.mlkit_vision_face_bundled.zzvj, com.google.android.gms.internal.mlkit_vision_face_bundled.zzwv, com.google.android.gms.internal.mlkit_vision_face_bundled.zzui, com.google.android.gms.internal.mlkit_vision_face_bundled.zzvr):com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz");
    }

    private static double zzm(Object obj, long j) {
        return ((Double) zzxc.zzf(obj, j)).doubleValue();
    }

    private static float zzn(Object obj, long j) {
        return ((Float) zzxc.zzf(obj, j)).floatValue();
    }

    private static int zzo(Object obj, long j) {
        return ((Integer) zzxc.zzf(obj, j)).intValue();
    }

    private final int zzp(int i) {
        return this.zzc[i + 2];
    }

    private final int zzq(int i, int i2) {
        int length = (this.zzc.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzc[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private static int zzr(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzs(int i) {
        return this.zzc[i + 1];
    }

    private static long zzt(Object obj, long j) {
        return ((Long) zzxc.zzf(obj, j)).longValue();
    }

    private final zzva zzu(int i) {
        int i2 = i / 3;
        return (zzva) this.zzd[i2 + i2 + 1];
    }

    private final zzwh zzv(int i) {
        Object[] objArr = this.zzd;
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzwh zzwhVar = (zzwh) objArr[i3];
        if (zzwhVar != null) {
            return zzwhVar;
        }
        zzwh zzwhVarZzb = zzwe.zza().zzb((Class) objArr[i3 + 1]);
        this.zzd[i3] = zzwhVarZzb;
        return zzwhVarZzb;
    }

    private final Object zzw(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private final Object zzx(Object obj, int i) {
        zzwh zzwhVarZzv = zzv(i);
        int iZzs = zzs(i) & 1048575;
        if (!zzI(obj, i)) {
            return zzwhVarZzv.zze();
        }
        Object object = zzb.getObject(obj, iZzs);
        if (zzL(object)) {
            return object;
        }
        Object objZze = zzwhVarZzv.zze();
        if (object != null) {
            zzwhVarZzv.zzg(objZze, object);
        }
        return objZze;
    }

    private final Object zzy(Object obj, int i, int i2) {
        zzwh zzwhVarZzv = zzv(i2);
        if (!zzM(obj, i, i2)) {
            return zzwhVarZzv.zze();
        }
        Object object = zzb.getObject(obj, zzs(i2) & 1048575);
        if (zzL(object)) {
            return object;
        }
        Object objZze = zzwhVarZzv.zze();
        if (object != null) {
            zzwhVarZzv.zzg(objZze, object);
        }
        return objZze;
    }

    private static Field zzz(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:207:0x054e  */
    /* JADX WARN: Type inference failed for: r0v115, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v118, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v120, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v137 */
    /* JADX WARN: Type inference failed for: r0v185, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v256, types: [int] */
    /* JADX WARN: Type inference failed for: r0v263, types: [int] */
    /* JADX WARN: Type inference failed for: r0v268 */
    /* JADX WARN: Type inference failed for: r0v269 */
    /* JADX WARN: Type inference failed for: r0v270 */
    /* JADX WARN: Type inference failed for: r0v271 */
    /* JADX WARN: Type inference failed for: r0v272 */
    /* JADX WARN: Type inference failed for: r0v273 */
    /* JADX WARN: Type inference failed for: r0v274 */
    /* JADX WARN: Type inference failed for: r0v275 */
    /* JADX WARN: Type inference failed for: r0v276 */
    /* JADX WARN: Type inference failed for: r0v277 */
    /* JADX WARN: Type inference failed for: r0v278 */
    /* JADX WARN: Type inference failed for: r0v279 */
    /* JADX WARN: Type inference failed for: r0v280 */
    /* JADX WARN: Type inference failed for: r0v281 */
    /* JADX WARN: Type inference failed for: r0v282 */
    /* JADX WARN: Type inference failed for: r0v283 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v120, types: [int] */
    /* JADX WARN: Type inference failed for: r1v123, types: [int] */
    /* JADX WARN: Type inference failed for: r1v162 */
    /* JADX WARN: Type inference failed for: r1v165 */
    /* JADX WARN: Type inference failed for: r1v166 */
    /* JADX WARN: Type inference failed for: r1v167 */
    /* JADX WARN: Type inference failed for: r1v168 */
    /* JADX WARN: Type inference failed for: r1v80, types: [int] */
    /* JADX WARN: Type inference failed for: r1v82 */
    /* JADX WARN: Type inference failed for: r2v32, types: [int] */
    /* JADX WARN: Type inference failed for: r2v37 */
    /* JADX WARN: Type inference failed for: r2v38, types: [int] */
    /* JADX WARN: Type inference failed for: r2v42, types: [int] */
    /* JADX WARN: Type inference failed for: r2v46, types: [int] */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55, types: [int] */
    /* JADX WARN: Type inference failed for: r2v89 */
    /* JADX WARN: Type inference failed for: r2v90 */
    /* JADX WARN: Type inference failed for: r2v91 */
    /* JADX WARN: Type inference failed for: r2v92 */
    /* JADX WARN: Type inference failed for: r2v93 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27, types: [int] */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30, types: [int] */
    /* JADX WARN: Type inference failed for: r3v35 */
    /* JADX WARN: Type inference failed for: r3v39, types: [int] */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v46, types: [int] */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v54 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v31, types: [int] */
    /* JADX WARN: Type inference failed for: r4v35 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v38, types: [int] */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v43 */
    /* JADX WARN: Type inference failed for: r4v44 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [int] */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(java.lang.Object r20) {
        /*
            Method dump skipped, instructions count: 2198
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zza(java.lang.Object):int");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final int zzb(Object obj) {
        int i;
        long jDoubleToLongBits;
        int iFloatToIntBits;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.zzc.length; i4 += 3) {
            int iZzs = zzs(i4);
            int[] iArr = this.zzc;
            int i5 = 1048575 & iZzs;
            int iZzr = zzr(iZzs);
            int i6 = iArr[i4];
            long j = i5;
            int iHashCode = 37;
            switch (iZzr) {
                case 0:
                    i = i3 * 53;
                    jDoubleToLongBits = Double.doubleToLongBits(zzxc.zza(obj, j));
                    byte[] bArr = zzvc.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 1:
                    i = i3 * 53;
                    iFloatToIntBits = Float.floatToIntBits(zzxc.zzb(obj, j));
                    i3 = i + iFloatToIntBits;
                    break;
                case 2:
                    i = i3 * 53;
                    jDoubleToLongBits = zzxc.zzd(obj, j);
                    byte[] bArr2 = zzvc.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 3:
                    i = i3 * 53;
                    jDoubleToLongBits = zzxc.zzd(obj, j);
                    byte[] bArr3 = zzvc.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 4:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 5:
                    i = i3 * 53;
                    jDoubleToLongBits = zzxc.zzd(obj, j);
                    byte[] bArr4 = zzvc.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 6:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 7:
                    i = i3 * 53;
                    iFloatToIntBits = zzvc.zza(zzxc.zzw(obj, j));
                    i3 = i + iFloatToIntBits;
                    break;
                case 8:
                    i = i3 * 53;
                    iFloatToIntBits = ((String) zzxc.zzf(obj, j)).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 9:
                    i2 = i3 * 53;
                    Object objZzf = zzxc.zzf(obj, j);
                    if (objZzf != null) {
                        iHashCode = objZzf.hashCode();
                    }
                    i3 = i2 + iHashCode;
                    break;
                case 10:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 11:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 12:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 13:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 14:
                    i = i3 * 53;
                    jDoubleToLongBits = zzxc.zzd(obj, j);
                    byte[] bArr5 = zzvc.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 15:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzc(obj, j);
                    i3 = i + iFloatToIntBits;
                    break;
                case 16:
                    i = i3 * 53;
                    jDoubleToLongBits = zzxc.zzd(obj, j);
                    byte[] bArr6 = zzvc.zzb;
                    iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                    i3 = i + iFloatToIntBits;
                    break;
                case 17:
                    i2 = i3 * 53;
                    Object objZzf2 = zzxc.zzf(obj, j);
                    if (objZzf2 != null) {
                        iHashCode = objZzf2.hashCode();
                    }
                    i3 = i2 + iHashCode;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case 50:
                    i = i3 * 53;
                    iFloatToIntBits = zzxc.zzf(obj, j).hashCode();
                    i3 = i + iFloatToIntBits;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = Double.doubleToLongBits(zzm(obj, j));
                        byte[] bArr7 = zzvc.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = Float.floatToIntBits(zzn(obj, j));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr8 = zzvc.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr9 = zzvc.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr10 = zzvc.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzvc.zza(zzN(obj, j));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = ((String) zzxc.zzf(obj, j)).hashCode();
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzxc.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzxc.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case Elf64_Ehdr.e_shstrndx /* 62 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr11 = zzvc.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzo(obj, j);
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        jDoubleToLongBits = zzt(obj, j);
                        byte[] bArr12 = zzvc.zzb;
                        iFloatToIntBits = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzM(obj, i6, i4)) {
                        i = i3 * 53;
                        iFloatToIntBits = zzxc.zzf(obj, j).hashCode();
                        i3 = i + iFloatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int iHashCode2 = (i3 * 53) + ((zzuw) obj).zzc.hashCode();
        return this.zzh ? (iHashCode2 * 53) + ((zzus) obj).zzb.zza.hashCode() : iHashCode2;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x0a28 A[PHI: r0 r8 r10 r11 r12 r13 r24
  0x0a28: PHI (r0v62 int) = (r0v15 int), (r0v17 int), (r0v20 int), (r0v27 int), (r0v42 int), (r0v55 int), (r0v56 int), (r0v65 int) binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]
  0x0a28: PHI (r8v89 int) = (r8v57 int), (r8v58 int), (r8v60 int), (r8v64 int), (r8v74 int), (r8v82 int), (r8v83 int), (r8v92 int) binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]
  0x0a28: PHI (r10v78 int) = (r10v45 int), (r10v46 int), (r10v48 int), (r10v52 int), (r10v61 int), (r10v67 int), (r10v69 int), (r10v81 int) binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]
  0x0a28: PHI (r11v48 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj) = 
  (r11v10 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v11 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v13 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v17 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v33 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v39 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v41 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r11v51 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
 binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]
  0x0a28: PHI (r12v62 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>) = 
  (r12v13 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v14 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v16 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v26 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v44 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v50 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v54 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
  (r12v65 com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz<T>)
 binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]
  0x0a28: PHI (r13v60 int) = (r13v37 int), (r13v38 int), (r13v40 int), (r13v44 int), (r13v51 int), (r13v53 int), (r13v54 int), (r13v63 int) binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]
  0x0a28: PHI (r24v21 sun.misc.Unsafe) = 
  (r24v14 sun.misc.Unsafe)
  (r24v14 sun.misc.Unsafe)
  (r24v14 sun.misc.Unsafe)
  (r24v14 sun.misc.Unsafe)
  (r24v17 sun.misc.Unsafe)
  (r24v14 sun.misc.Unsafe)
  (r24v14 sun.misc.Unsafe)
  (r24v14 sun.misc.Unsafe)
 binds: [B:438:0x09de, B:422:0x097d, B:405:0x0922, B:395:0x08ee, B:278:0x06d4, B:243:0x0623, B:329:0x07b0, B:175:0x048f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0ca4 A[PHI: r1 r4 r8 r29
  0x0ca4: PHI (r1v178 int) = 
  (r1v150 int)
  (r1v151 int)
  (r1v152 int)
  (r1v153 int)
  (r1v154 int)
  (r1v155 int)
  (r1v156 int)
  (r1v157 int)
  (r1v164 int)
  (r1v169 int)
  (r1v179 int)
 binds: [B:534:0x0c8d, B:531:0x0c71, B:528:0x0c59, B:525:0x0c42, B:522:0x0c2a, B:519:0x0c11, B:512:0x0bef, B:498:0x0bb8, B:496:0x0bac, B:477:0x0b16, B:464:0x0a95] A[DONT_GENERATE, DONT_INLINE]
  0x0ca4: PHI (r4v109 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj) = 
  (r4v85 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v86 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v87 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v88 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v89 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v90 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v91 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v92 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v97 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v101 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
  (r4v110 com.google.android.gms.internal.mlkit_vision_face_bundled.zztj)
 binds: [B:534:0x0c8d, B:531:0x0c71, B:528:0x0c59, B:525:0x0c42, B:522:0x0c2a, B:519:0x0c11, B:512:0x0bef, B:498:0x0bb8, B:496:0x0bac, B:477:0x0b16, B:464:0x0a95] A[DONT_GENERATE, DONT_INLINE]
  0x0ca4: PHI (r8v123 int) = 
  (r8v100 int)
  (r8v101 int)
  (r8v102 int)
  (r8v103 int)
  (r8v104 int)
  (r8v105 int)
  (r8v106 int)
  (r8v107 int)
  (r8v110 int)
  (r8v118 int)
  (r8v124 int)
 binds: [B:534:0x0c8d, B:531:0x0c71, B:528:0x0c59, B:525:0x0c42, B:522:0x0c2a, B:519:0x0c11, B:512:0x0bef, B:498:0x0bb8, B:496:0x0bac, B:477:0x0b16, B:464:0x0a95] A[DONT_GENERATE, DONT_INLINE]
  0x0ca4: PHI (r29v16 int) = 
  (r29v8 int)
  (r29v8 int)
  (r29v8 int)
  (r29v8 int)
  (r29v8 int)
  (r29v8 int)
  (r29v8 int)
  (r29v8 int)
  (r29v10 int)
  (r29v12 int)
  (r29v8 int)
 binds: [B:534:0x0c8d, B:531:0x0c71, B:528:0x0c59, B:525:0x0c42, B:522:0x0c2a, B:519:0x0c11, B:512:0x0bef, B:498:0x0bb8, B:496:0x0bac, B:477:0x0b16, B:464:0x0a95] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0cda  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0d26  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x0a2b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:617:0x0ca7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:619:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:659:0x0a3c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:660:0x0cbd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzc(java.lang.Object r38, byte[] r39, int r40, int r41, int r42, com.google.android.gms.internal.mlkit_vision_face_bundled.zztj r43) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3650
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzc(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.mlkit_vision_face_bundled.zztj):int");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final Object zze() {
        return ((zzuw) this.zzg).zzy();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzf(java.lang.Object r8) {
        /*
            r7 = this;
            boolean r0 = zzL(r8)
            if (r0 != 0) goto L8
            goto L95
        L8:
            boolean r0 = r8 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
            r1 = 0
            if (r0 == 0) goto L1b
            r0 = r8
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw r0 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw) r0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r0.zzG(r2)
            r0.zza = r1
            r0.zzE()
        L1b:
            int[] r0 = r7.zzc
        L1d:
            int r2 = r0.length
            if (r1 >= r2) goto L87
            int r2 = r7.zzs(r1)
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r3 = r3 & r2
            int r2 = zzr(r2)
            long r3 = (long) r3
            r5 = 9
            if (r2 == r5) goto L71
            r5 = 60
            if (r2 == r5) goto L59
            r5 = 68
            if (r2 == r5) goto L59
            switch(r2) {
                case 17: goto L71;
                case 18: goto L4f;
                case 19: goto L4f;
                case 20: goto L4f;
                case 21: goto L4f;
                case 22: goto L4f;
                case 23: goto L4f;
                case 24: goto L4f;
                case 25: goto L4f;
                case 26: goto L4f;
                case 27: goto L4f;
                case 28: goto L4f;
                case 29: goto L4f;
                case 30: goto L4f;
                case 31: goto L4f;
                case 32: goto L4f;
                case 33: goto L4f;
                case 34: goto L4f;
                case 35: goto L4f;
                case 36: goto L4f;
                case 37: goto L4f;
                case 38: goto L4f;
                case 39: goto L4f;
                case 40: goto L4f;
                case 41: goto L4f;
                case 42: goto L4f;
                case 43: goto L4f;
                case 44: goto L4f;
                case 45: goto L4f;
                case 46: goto L4f;
                case 47: goto L4f;
                case 48: goto L4f;
                case 49: goto L4f;
                case 50: goto L3d;
                default: goto L3c;
            }
        L3c:
            goto L84
        L3d:
            sun.misc.Unsafe r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzb
            java.lang.Object r5 = r2.getObject(r8, r3)
            if (r5 == 0) goto L84
            r6 = r5
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzvq r6 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzvq) r6
            r6.zzc()
            r2.putObject(r8, r3, r5)
            goto L84
        L4f:
            java.lang.Object r2 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzxc.zzf(r8, r3)
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzvb r2 = (com.google.android.gms.internal.mlkit_vision_face_bundled.zzvb) r2
            r2.zzb()
            goto L84
        L59:
            int[] r2 = r7.zzc
            r2 = r2[r1]
            boolean r2 = r7.zzM(r8, r2, r1)
            if (r2 == 0) goto L84
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh r2 = r7.zzv(r1)
            sun.misc.Unsafe r5 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzb
            java.lang.Object r3 = r5.getObject(r8, r3)
            r2.zzf(r3)
            goto L84
        L71:
            boolean r2 = r7.zzI(r8, r1)
            if (r2 == 0) goto L84
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh r2 = r7.zzv(r1)
            sun.misc.Unsafe r5 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzb
            java.lang.Object r3 = r5.getObject(r8, r3)
            r2.zzf(r3)
        L84:
            int r1 = r1 + 3
            goto L1d
        L87:
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzwv r0 = r7.zzl
            r0.zza(r8)
            boolean r0 = r7.zzh
            if (r0 == 0) goto L95
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzui r0 = r7.zzm
            r0.zza(r8)
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzf(java.lang.Object):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final void zzg(Object obj, Object obj2) {
        zzA(obj);
        obj2.getClass();
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzs = zzs(i);
            int i2 = 1048575 & iZzs;
            int[] iArr = this.zzc;
            int iZzr = zzr(iZzs);
            int i3 = iArr[i];
            long j = i2;
            switch (iZzr) {
                case 0:
                    if (zzI(obj2, i)) {
                        zzxc.zzo(obj, j, zzxc.zza(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzI(obj2, i)) {
                        zzxc.zzp(obj, j, zzxc.zzb(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzI(obj2, i)) {
                        zzxc.zzr(obj, j, zzxc.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzI(obj2, i)) {
                        zzxc.zzr(obj, j, zzxc.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzI(obj2, i)) {
                        zzxc.zzq(obj, j, zzxc.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzI(obj2, i)) {
                        zzxc.zzr(obj, j, zzxc.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzI(obj2, i)) {
                        zzxc.zzq(obj, j, zzxc.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzI(obj2, i)) {
                        zzxc.zzm(obj, j, zzxc.zzw(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzI(obj2, i)) {
                        zzxc.zzs(obj, j, zzxc.zzf(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzB(obj, obj2, i);
                    break;
                case 10:
                    if (zzI(obj2, i)) {
                        zzxc.zzs(obj, j, zzxc.zzf(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzI(obj2, i)) {
                        zzxc.zzq(obj, j, zzxc.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzI(obj2, i)) {
                        zzxc.zzq(obj, j, zzxc.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzI(obj2, i)) {
                        zzxc.zzq(obj, j, zzxc.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzI(obj2, i)) {
                        zzxc.zzr(obj, j, zzxc.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzI(obj2, i)) {
                        zzxc.zzq(obj, j, zzxc.zzc(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzI(obj2, i)) {
                        zzxc.zzr(obj, j, zzxc.zzd(obj2, j));
                        zzD(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzB(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zzvb zzvbVarZzd = (zzvb) zzxc.zzf(obj, j);
                    zzvb zzvbVar = (zzvb) zzxc.zzf(obj2, j);
                    int size = zzvbVarZzd.size();
                    int size2 = zzvbVar.size();
                    if (size > 0 && size2 > 0) {
                        if (!zzvbVarZzd.zzc()) {
                            zzvbVarZzd = zzvbVarZzd.zzd(size2 + size);
                        }
                        zzvbVarZzd.addAll(zzvbVar);
                    }
                    if (size > 0) {
                        zzvbVar = zzvbVarZzd;
                    }
                    zzxc.zzs(obj, j, zzvbVar);
                    break;
                case 50:
                    int i4 = zzwj.zza;
                    zzxc.zzs(obj, j, zzvr.zza(zzxc.zzf(obj, j), zzxc.zzf(obj2, j)));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case 52:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                case 54:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                case 56:
                case 57:
                case Elf64_Ehdr.e_shentsize /* 58 */:
                case 59:
                    if (zzM(obj2, i3, i)) {
                        zzxc.zzs(obj, j, zzxc.zzf(obj2, j));
                        zzE(obj, i3, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzC(obj, obj2, i);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case Elf64_Ehdr.e_shstrndx /* 62 */:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    if (zzM(obj2, i3, i)) {
                        zzxc.zzs(obj, j, zzxc.zzf(obj2, j));
                        zzE(obj, i3, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzC(obj, obj2, i);
                    break;
            }
        }
        zzwj.zzp(this.zzl, obj, obj2);
        if (this.zzh) {
            zzwj.zzo(this.zzm, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final void zzh(Object obj, byte[] bArr, int i, int i2, zztj zztjVar) throws IOException {
        zzc(obj, bArr, i, i2, 0, zztjVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x04b5  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x05a9  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x05cb  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x05ed  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0232  */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi(java.lang.Object r24, com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1844
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzi(java.lang.Object, com.google.android.gms.internal.mlkit_vision_face_bundled.zzxi):void");
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    public final boolean zzj(Object obj, Object obj2) {
        boolean zZzI;
        for (int i = 0; i < this.zzc.length; i += 3) {
            int iZzs = zzs(i);
            long j = iZzs & 1048575;
            switch (zzr(iZzs)) {
                case 0:
                    if (!zzH(obj, obj2, i) || Double.doubleToLongBits(zzxc.zza(obj, j)) != Double.doubleToLongBits(zzxc.zza(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 1:
                    if (!zzH(obj, obj2, i) || Float.floatToIntBits(zzxc.zzb(obj, j)) != Float.floatToIntBits(zzxc.zzb(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 2:
                    if (!zzH(obj, obj2, i) || zzxc.zzd(obj, j) != zzxc.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 3:
                    if (!zzH(obj, obj2, i) || zzxc.zzd(obj, j) != zzxc.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 4:
                    if (!zzH(obj, obj2, i) || zzxc.zzc(obj, j) != zzxc.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 5:
                    if (!zzH(obj, obj2, i) || zzxc.zzd(obj, j) != zzxc.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 6:
                    if (!zzH(obj, obj2, i) || zzxc.zzc(obj, j) != zzxc.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 7:
                    if (!zzH(obj, obj2, i) || zzxc.zzw(obj, j) != zzxc.zzw(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 8:
                    if (!zzH(obj, obj2, i) || !zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 9:
                    if (!zzH(obj, obj2, i) || !zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 10:
                    if (!zzH(obj, obj2, i) || !zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 11:
                    if (!zzH(obj, obj2, i) || zzxc.zzc(obj, j) != zzxc.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 12:
                    if (!zzH(obj, obj2, i) || zzxc.zzc(obj, j) != zzxc.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 13:
                    if (!zzH(obj, obj2, i) || zzxc.zzc(obj, j) != zzxc.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 14:
                    if (!zzH(obj, obj2, i) || zzxc.zzd(obj, j) != zzxc.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 15:
                    if (!zzH(obj, obj2, i) || zzxc.zzc(obj, j) != zzxc.zzc(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 16:
                    if (!zzH(obj, obj2, i) || zzxc.zzd(obj, j) != zzxc.zzd(obj2, j)) {
                        return false;
                    }
                    continue;
                    break;
                case 17:
                    if (!zzH(obj, obj2, i) || !zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    zZzI = zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j));
                    break;
                case 50:
                    zZzI = zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                case 52:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                case 54:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                case 56:
                case 57:
                case Elf64_Ehdr.e_shentsize /* 58 */:
                case 59:
                case 60:
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                case Elf64_Ehdr.e_shstrndx /* 62 */:
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                case 64:
                case 65:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                case 68:
                    long jZzp = zzp(i) & 1048575;
                    if (zzxc.zzc(obj, jZzp) != zzxc.zzc(obj2, jZzp) || !zzwj.zzI(zzxc.zzf(obj, j), zzxc.zzf(obj2, j))) {
                        return false;
                    }
                    continue;
                    break;
                default:
            }
            if (!zZzI) {
                return false;
            }
        }
        if (!((zzuw) obj).zzc.equals(((zzuw) obj2).zzc)) {
            return false;
        }
        if (this.zzh) {
            return ((zzus) obj).zzb.equals(((zzus) obj2).zzb);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009b  */
    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwh
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzk(java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzvz.zzk(java.lang.Object):boolean");
    }
}
