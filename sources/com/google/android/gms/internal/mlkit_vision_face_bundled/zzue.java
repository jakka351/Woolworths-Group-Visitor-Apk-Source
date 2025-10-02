package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzue extends zzth implements RandomAccess, zzvb {
    private double[] zza;
    private int zzb;

    static {
        new zzue(new double[0], 0, false);
    }

    zzue() {
        this(new double[10], 0, true);
    }

    private final String zzg(int i) {
        return "Index:" + i + ", Size:" + this.zzb;
    }

    private final void zzh(int i) {
        if (i < 0 || i >= this.zzb) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int i, Object obj) {
        int i2;
        double dDoubleValue = ((Double) obj).doubleValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        int i3 = i + 1;
        double[] dArr = this.zza;
        if (i2 < dArr.length) {
            System.arraycopy(dArr, i, dArr, i3, i2 - i);
        } else {
            double[] dArr2 = new double[((i2 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            System.arraycopy(this.zza, i, dArr2, i3, this.zzb - i);
            this.zza = dArr2;
        }
        this.zza[i] = dDoubleValue;
        this.zzb++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzvc.zzb;
        collection.getClass();
        if (!(collection instanceof zzue)) {
            return super.addAll(collection);
        }
        zzue zzueVar = (zzue) collection;
        int i = zzueVar.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        double[] dArr = this.zza;
        if (i3 > dArr.length) {
            this.zza = Arrays.copyOf(dArr, i3);
        }
        System.arraycopy(zzueVar.zza, 0, this.zza, this.zzb, zzueVar.zzb);
        this.zzb = i3;
        this.modCount++;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzue)) {
            return super.equals(obj);
        }
        zzue zzueVar = (zzue) obj;
        if (this.zzb != zzueVar.zzb) {
            return false;
        }
        double[] dArr = zzueVar.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (Double.doubleToLongBits(this.zza[i]) != Double.doubleToLongBits(dArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Double.valueOf(this.zza[i]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            long jDoubleToLongBits = Double.doubleToLongBits(this.zza[i2]);
            byte[] bArr = zzvc.zzb;
            i = (i * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Double)) {
            return -1;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        int i = this.zzb;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zza[i2] == dDoubleValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        zzh(i);
        double[] dArr = this.zza;
        double d = dArr[i];
        if (i < this.zzb - 1) {
            System.arraycopy(dArr, i + 1, dArr, i, (r3 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zza();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zza;
        System.arraycopy(dArr, i2, dArr, i, this.zzb - i2);
        this.zzb -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        double dDoubleValue = ((Double) obj).doubleValue();
        zza();
        zzh(i);
        double[] dArr = this.zza;
        double d = dArr[i];
        dArr[i] = dDoubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvb
    public final /* bridge */ /* synthetic */ zzvb zzd(int i) {
        if (i >= this.zzb) {
            return new zzue(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final double zze(int i) {
        zzh(i);
        return this.zza[i];
    }

    public final void zzf(double d) {
        zza();
        int i = this.zzb;
        double[] dArr = this.zza;
        if (i == dArr.length) {
            double[] dArr2 = new double[((i * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, i);
            this.zza = dArr2;
        }
        double[] dArr3 = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        dArr3[i2] = d;
    }

    private zzue(double[] dArr, int i, boolean z) {
        super(z);
        this.zza = dArr;
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Double) obj).doubleValue());
        return true;
    }
}
