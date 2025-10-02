package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzwj {
    public static final /* synthetic */ int zza = 0;
    private static final zzwv zzb;

    static {
        int i = zzwe.zza;
        zzb = new zzwx();
    }

    public static void zzA(int i, List list, zzxi zzxiVar, zzwh zzwhVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzud) zzxiVar).zzv(i, list.get(i2), zzwhVar);
        }
    }

    public static void zzB(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzy(i, list, z);
    }

    public static void zzC(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzA(i, list, z);
    }

    public static void zzD(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzC(i, list, z);
    }

    public static void zzE(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzE(i, list, z);
    }

    public static void zzF(int i, List list, zzxi zzxiVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzH(i, list);
    }

    public static void zzG(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzJ(i, list, z);
    }

    public static void zzH(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzL(i, list, z);
    }

    static boolean zzI(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int zza(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzux) {
            zzux zzuxVar = (zzux) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(zzuxVar.zze(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzA;
    }

    static int zzb(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzuc.zzz(i << 3) + 4);
    }

    static int zzc(List list) {
        return list.size() * 4;
    }

    static int zzd(int i, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzuc.zzz(i << 3) + 8);
    }

    static int zze(List list) {
        return list.size() * 8;
    }

    static int zzf(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzux) {
            zzux zzuxVar = (zzux) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(zzuxVar.zze(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzA;
    }

    static int zzg(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvl) {
            zzvl zzvlVar = (zzvl) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(zzvlVar.zze(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzA;
    }

    static int zzh(int i, Object obj, zzwh zzwhVar) {
        int i2 = i << 3;
        if (!(obj instanceof zzvh)) {
            return zzuc.zzz(i2) + zzuc.zzx((zzvw) obj, zzwhVar);
        }
        int iZzz = zzuc.zzz(i2);
        int iZza = ((zzvh) obj).zza();
        return iZzz + zzuc.zzz(iZza) + iZza;
    }

    static int zzi(List list) {
        int iZzz;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzux) {
            zzux zzuxVar = (zzux) list;
            iZzz = 0;
            while (i < size) {
                int iZze = zzuxVar.zze(i);
                iZzz += zzuc.zzz((iZze >> 31) ^ (iZze + iZze));
                i++;
            }
        } else {
            iZzz = 0;
            while (i < size) {
                int iIntValue = ((Integer) list.get(i)).intValue();
                iZzz += zzuc.zzz((iIntValue >> 31) ^ (iIntValue + iIntValue));
                i++;
            }
        }
        return iZzz;
    }

    static int zzj(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvl) {
            zzvl zzvlVar = (zzvl) list;
            iZzA = 0;
            while (i < size) {
                long jZze = zzvlVar.zze(i);
                iZzA += zzuc.zzA((jZze >> 63) ^ (jZze + jZze));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                long jLongValue = ((Long) list.get(i)).longValue();
                iZzA += zzuc.zzA((jLongValue >> 63) ^ (jLongValue + jLongValue));
                i++;
            }
        }
        return iZzA;
    }

    static int zzk(List list) {
        int iZzz;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzux) {
            zzux zzuxVar = (zzux) list;
            iZzz = 0;
            while (i < size) {
                iZzz += zzuc.zzz(zzuxVar.zze(i));
                i++;
            }
        } else {
            iZzz = 0;
            while (i < size) {
                iZzz += zzuc.zzz(((Integer) list.get(i)).intValue());
                i++;
            }
        }
        return iZzz;
    }

    static int zzl(List list) {
        int iZzA;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzvl) {
            zzvl zzvlVar = (zzvl) list;
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(zzvlVar.zze(i));
                i++;
            }
        } else {
            iZzA = 0;
            while (i < size) {
                iZzA += zzuc.zzA(((Long) list.get(i)).longValue());
                i++;
            }
        }
        return iZzA;
    }

    public static zzwv zzm() {
        return zzb;
    }

    static Object zzn(Object obj, int i, int i2, Object obj2, zzwv zzwvVar) {
        Object obj3 = obj2;
        if (obj2 == null) {
            zzuw zzuwVar = (zzuw) obj;
            zzww zzwwVar = zzuwVar.zzc;
            obj3 = zzwwVar;
            if (zzwwVar == zzww.zzc()) {
                zzww zzwwVarZzf = zzww.zzf();
                zzuwVar.zzc = zzwwVarZzf;
                obj3 = zzwwVarZzf;
            }
        }
        ((zzww) obj3).zzj(i << 3, Long.valueOf(i2));
        return obj3;
    }

    static void zzo(zzui zzuiVar, Object obj, Object obj2) {
        zzum zzumVar = ((zzus) obj2).zzb;
        if (zzumVar.zza.isEmpty()) {
            return;
        }
        ((zzus) obj).zzb().zzi(zzumVar);
    }

    static void zzp(zzwv zzwvVar, Object obj, Object obj2) {
        zzuw zzuwVar = (zzuw) obj;
        zzww zzwwVarZze = zzuwVar.zzc;
        zzww zzwwVar = ((zzuw) obj2).zzc;
        if (!zzww.zzc().equals(zzwwVar)) {
            if (zzww.zzc().equals(zzwwVarZze)) {
                zzwwVarZze = zzww.zze(zzwwVarZze, zzwwVar);
            } else {
                zzwwVarZze.zzd(zzwwVar);
            }
        }
        zzuwVar.zzc = zzwwVarZze;
    }

    public static void zzq(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzc(i, list, z);
    }

    public static void zzr(int i, List list, zzxi zzxiVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zze(i, list);
    }

    public static void zzs(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzg(i, list, z);
    }

    public static void zzt(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzj(i, list, z);
    }

    public static void zzu(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzl(i, list, z);
    }

    public static void zzv(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzn(i, list, z);
    }

    public static void zzw(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzp(i, list, z);
    }

    public static void zzx(int i, List list, zzxi zzxiVar, zzwh zzwhVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((zzud) zzxiVar).zzq(i, list.get(i2), zzwhVar);
        }
    }

    public static void zzy(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzs(i, list, z);
    }

    public static void zzz(int i, List list, zzxi zzxiVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzxiVar.zzu(i, list, z);
    }
}
