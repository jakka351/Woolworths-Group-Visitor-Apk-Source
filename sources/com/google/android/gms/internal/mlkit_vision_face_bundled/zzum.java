package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzum {
    private static final zzum zzb = new zzum(true);
    final zzwr zza = new zzwk();
    private boolean zzc;

    private zzum() {
    }

    public static int zza(zzul zzulVar, Object obj) {
        int iZzd;
        int iZzz;
        zzxg zzxgVarZzb = zzulVar.zzb();
        zzulVar.zza();
        zzulVar.zze();
        List list = (List) obj;
        int size = list.size();
        zzulVar.zzd();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj2 = list.get(i2);
            int iZzz2 = zzuc.zzz(1616448016);
            if (zzxgVarZzb == zzxg.GROUP) {
                zzvw zzvwVar = (zzvw) obj2;
                byte[] bArr = zzvc.zzb;
                if (zzvwVar instanceof zztg) {
                    throw null;
                }
                iZzz2 += iZzz2;
            }
            zzxh zzxhVar = zzxh.INT;
            int iZzA = 4;
            switch (zzxgVarZzb) {
                case DOUBLE:
                    ((Double) obj2).doubleValue();
                    iZzA = 8;
                    i += iZzz2 + iZzA;
                case FLOAT:
                    ((Float) obj2).floatValue();
                    i += iZzz2 + iZzA;
                case INT64:
                    iZzA = zzuc.zzA(((Long) obj2).longValue());
                    i += iZzz2 + iZzA;
                case UINT64:
                    iZzA = zzuc.zzA(((Long) obj2).longValue());
                    i += iZzz2 + iZzA;
                case INT32:
                    iZzA = zzuc.zzA(((Integer) obj2).intValue());
                    i += iZzz2 + iZzA;
                case FIXED64:
                    ((Long) obj2).longValue();
                    iZzA = 8;
                    i += iZzz2 + iZzA;
                case FIXED32:
                    ((Integer) obj2).intValue();
                    i += iZzz2 + iZzA;
                case BOOL:
                    ((Boolean) obj2).booleanValue();
                    iZzA = 1;
                    i += iZzz2 + iZzA;
                case STRING:
                    if (obj2 instanceof zztu) {
                        iZzd = ((zztu) obj2).zzd();
                        iZzz = zzuc.zzz(iZzd);
                        iZzA = iZzz + iZzd;
                        i += iZzz2 + iZzA;
                    } else {
                        iZzA = zzuc.zzy((String) obj2);
                        i += iZzz2 + iZzA;
                    }
                case GROUP:
                    iZzA = ((zzvw) obj2).zzu();
                    i += iZzz2 + iZzA;
                case MESSAGE:
                    if (obj2 instanceof zzvg) {
                        iZzd = ((zzvg) obj2).zza();
                        iZzz = zzuc.zzz(iZzd);
                    } else {
                        iZzd = ((zzvw) obj2).zzu();
                        iZzz = zzuc.zzz(iZzd);
                    }
                    iZzA = iZzz + iZzd;
                    i += iZzz2 + iZzA;
                case BYTES:
                    if (obj2 instanceof zztu) {
                        iZzd = ((zztu) obj2).zzd();
                        iZzz = zzuc.zzz(iZzd);
                    } else {
                        iZzd = ((byte[]) obj2).length;
                        iZzz = zzuc.zzz(iZzd);
                    }
                    iZzA = iZzz + iZzd;
                    i += iZzz2 + iZzA;
                case UINT32:
                    iZzA = zzuc.zzz(((Integer) obj2).intValue());
                    i += iZzz2 + iZzA;
                case ENUM:
                    iZzA = obj2 instanceof zzuy ? zzuc.zzA(((zzuy) obj2).zza()) : zzuc.zzA(((Integer) obj2).intValue());
                    i += iZzz2 + iZzA;
                case SFIXED32:
                    ((Integer) obj2).intValue();
                    i += iZzz2 + iZzA;
                case SFIXED64:
                    ((Long) obj2).longValue();
                    iZzA = 8;
                    i += iZzz2 + iZzA;
                case SINT32:
                    int iIntValue = ((Integer) obj2).intValue();
                    iZzA = zzuc.zzz((iIntValue >> 31) ^ (iIntValue + iIntValue));
                    i += iZzz2 + iZzA;
                case SINT64:
                    long jLongValue = ((Long) obj2).longValue();
                    iZzA = zzuc.zzA((jLongValue >> 63) ^ (jLongValue + jLongValue));
                    i += iZzz2 + iZzA;
                default:
                    throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
            }
        }
        return i;
    }

    public static zzum zzd() {
        return zzb;
    }

    private final void zzm(Map.Entry entry) {
        zzul zzulVar = (zzul) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof zzvg;
        zzulVar.zze();
        if (z) {
            throw new IllegalStateException("Lazy fields can not be repeated");
        }
        Object objZze = zze(zzulVar);
        List list = (List) value;
        int size = list.size();
        if (objZze == null) {
            objZze = new ArrayList(size);
        }
        List list2 = (List) objZze;
        for (int i = 0; i < size; i++) {
            Object objZzb = list.get(i);
            if (objZzb instanceof zzwb) {
                objZzb = ((zzwb) objZzb).zzb();
            } else if (objZzb instanceof byte[]) {
                byte[] bArr = (byte[]) objZzb;
                int length = bArr.length;
                Object obj = new byte[length];
                System.arraycopy(bArr, 0, obj, 0, length);
                objZzb = obj;
            }
            list2.add(objZzb);
        }
        this.zza.put(zzulVar, objZze);
    }

    private static boolean zzn(Map.Entry entry) {
        zzul zzulVar = (zzul) entry.getKey();
        if (zzulVar.zzc() != zzxh.MESSAGE) {
            return true;
        }
        zzulVar.zze();
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (obj instanceof zzvx) {
                if (!((zzvx) obj).zzt()) {
                    return false;
                }
            } else if (!(obj instanceof zzvg)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        }
        return true;
    }

    private static final int zzo(Map.Entry entry) {
        zzul zzulVar = (zzul) entry.getKey();
        Object value = entry.getValue();
        if (zzulVar.zzc() == zzxh.MESSAGE) {
            zzulVar.zze();
        }
        return zza(zzulVar, value);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void zzp(com.google.android.gms.internal.mlkit_vision_face_bundled.zzul r2, java.lang.Object r3) {
        /*
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzxg r0 = r2.zzb()
            byte[] r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzvc.zzb
            r3.getClass()
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzxg r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzxg.DOUBLE
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzxh r1 = com.google.android.gms.internal.mlkit_vision_face_bundled.zzxh.INT
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzxh r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L43;
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L2b;
                case 7: goto L22;
                case 8: goto L19;
                default: goto L18;
            }
        L18:
            goto L48
        L19:
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.zzvw
            if (r0 != 0) goto L47
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.zzvg
            if (r0 == 0) goto L48
            goto L47
        L22:
            boolean r0 = r3 instanceof java.lang.Integer
            if (r0 != 0) goto L47
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.zzuy
            if (r0 == 0) goto L48
            goto L47
        L2b:
            boolean r0 = r3 instanceof com.google.android.gms.internal.mlkit_vision_face_bundled.zztu
            if (r0 != 0) goto L47
            boolean r0 = r3 instanceof byte[]
            if (r0 == 0) goto L48
            goto L47
        L34:
            boolean r0 = r3 instanceof java.lang.String
            goto L45
        L37:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L45
        L3a:
            boolean r0 = r3 instanceof java.lang.Double
            goto L45
        L3d:
            boolean r0 = r3 instanceof java.lang.Float
            goto L45
        L40:
            boolean r0 = r3 instanceof java.lang.Long
            goto L45
        L43:
            boolean r0 = r3 instanceof java.lang.Integer
        L45:
            if (r0 == 0) goto L48
        L47:
            return
        L48:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2.zza()
            r1 = 202056002(0xc0b2142, float:1.0718179E-31)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzxg r2 = r2.zzb()
            com.google.android.gms.internal.mlkit_vision_face_bundled.zzxh r2 = r2.zza()
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.Object[] r2 = new java.lang.Object[]{r1, r2, r3}
            java.lang.String r3 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_face_bundled.zzum.zzp(com.google.android.gms.internal.mlkit_vision_face_bundled.zzul, java.lang.Object):void");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzum) {
            return this.zza.equals(((zzum) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int iZzc = this.zza.zzc();
        int iZzo = 0;
        for (int i = 0; i < iZzc; i++) {
            iZzo += zzo(this.zza.zzg(i));
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            iZzo += zzo((Map.Entry) it.next());
        }
        return iZzo;
    }

    /* renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public final zzum clone() {
        zzum zzumVar = new zzum();
        int iZzc = this.zza.zzc();
        for (int i = 0; i < iZzc; i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            zzumVar.zzj((zzul) ((zzwl) entryZzg).zza(), entryZzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzd()) {
            zzumVar.zzj((zzul) entry.getKey(), entry.getValue());
        }
        return zzumVar;
    }

    public final Object zze(zzul zzulVar) {
        Object obj = this.zza.get(zzulVar);
        if (!(obj instanceof zzvg)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        return this.zza.isEmpty() ? Collections.emptyIterator() : this.zza.entrySet().iterator();
    }

    public final void zzg(zzul zzulVar, Object obj) {
        List arrayList;
        zzp(zzulVar, obj);
        Object objZze = zze(zzulVar);
        if (objZze == null) {
            arrayList = new ArrayList();
            this.zza.put(zzulVar, arrayList);
        } else {
            arrayList = (List) objZze;
        }
        arrayList.add(obj);
    }

    public final void zzh() {
        if (this.zzc) {
            return;
        }
        int iZzc = this.zza.zzc();
        for (int i = 0; i < iZzc; i++) {
            Map.Entry entryZzg = this.zza.zzg(i);
            if (entryZzg.getValue() instanceof zzuw) {
                ((zzuw) entryZzg.getValue()).zzD();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzi(zzum zzumVar) {
        int iZzc = zzumVar.zza.zzc();
        for (int i = 0; i < iZzc; i++) {
            zzm(zzumVar.zza.zzg(i));
        }
        Iterator it = zzumVar.zza.zzd().iterator();
        while (it.hasNext()) {
            zzm((Map.Entry) it.next());
        }
    }

    public final void zzj(zzul zzulVar, Object obj) {
        zzulVar.zze();
        if (!(obj instanceof List)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        List list = (List) obj;
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Object obj2 = list.get(i);
            zzp(zzulVar, obj2);
            arrayList.add(obj2);
        }
        this.zza.put(zzulVar, arrayList);
    }

    public final boolean zzk() {
        return this.zzc;
    }

    public final boolean zzl() {
        int iZzc = this.zza.zzc();
        for (int i = 0; i < iZzc; i++) {
            if (!zzn(this.zza.zzg(i))) {
                return false;
            }
        }
        Iterator it = this.zza.zzd().iterator();
        while (it.hasNext()) {
            if (!zzn((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    private zzum(boolean z) {
        zzh();
        zzh();
    }
}
