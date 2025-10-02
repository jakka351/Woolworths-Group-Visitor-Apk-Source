package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzux extends zzth implements RandomAccess, zzvb {
    private int[] zza;
    private int zzb;

    static {
        new zzux(new int[0], 0, false);
    }

    zzux() {
        this(new int[10], 0, true);
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
        int iIntValue = ((Integer) obj).intValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        int i3 = i + 1;
        int[] iArr = this.zza;
        if (i2 < iArr.length) {
            System.arraycopy(iArr, i, iArr, i3, i2 - i);
        } else {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i);
            System.arraycopy(this.zza, i, iArr2, i3, this.zzb - i);
            this.zza = iArr2;
        }
        this.zza[i] = iIntValue;
        this.zzb++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzvc.zzb;
        collection.getClass();
        if (!(collection instanceof zzux)) {
            return super.addAll(collection);
        }
        zzux zzuxVar = (zzux) collection;
        int i = zzuxVar.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        int[] iArr = this.zza;
        if (i3 > iArr.length) {
            this.zza = Arrays.copyOf(iArr, i3);
        }
        System.arraycopy(zzuxVar.zza, 0, this.zza, this.zzb, zzuxVar.zzb);
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
        if (!(obj instanceof zzux)) {
            return super.equals(obj);
        }
        zzux zzuxVar = (zzux) obj;
        if (this.zzb != zzuxVar.zzb) {
            return false;
        }
        int[] iArr = zzuxVar.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (this.zza[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Integer.valueOf(this.zza[i]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.zzb; i2++) {
            i = (i * 31) + this.zza[i2];
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Integer) obj).intValue();
        int i = this.zzb;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zza[i2] == iIntValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        zzh(i);
        int[] iArr = this.zza;
        int i2 = iArr[i];
        if (i < this.zzb - 1) {
            System.arraycopy(iArr, i + 1, iArr, i, (r2 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zza();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] iArr = this.zza;
        System.arraycopy(iArr, i2, iArr, i, this.zzb - i2);
        this.zzb -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        zza();
        zzh(i);
        int[] iArr = this.zza;
        int i2 = iArr[i];
        iArr[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvb
    public final /* bridge */ /* synthetic */ zzvb zzd(int i) {
        if (i >= this.zzb) {
            return new zzux(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final int zze(int i) {
        zzh(i);
        return this.zza[i];
    }

    public final void zzf(int i) {
        zza();
        int i2 = this.zzb;
        int[] iArr = this.zza;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[((i2 * 3) / 2) + 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.zza = iArr2;
        }
        int[] iArr3 = this.zza;
        int i3 = this.zzb;
        this.zzb = i3 + 1;
        iArr3[i3] = i;
    }

    private zzux(int[] iArr, int i, boolean z) {
        super(z);
        this.zza = iArr;
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Integer) obj).intValue());
        return true;
    }
}
