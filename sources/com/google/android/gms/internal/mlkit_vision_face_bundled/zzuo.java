package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzuo extends zzth implements RandomAccess, zzvb {
    private float[] zza;
    private int zzb;

    static {
        new zzuo(new float[0], 0, false);
    }

    zzuo() {
        this(new float[10], 0, true);
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
        float fFloatValue = ((Float) obj).floatValue();
        zza();
        if (i < 0 || i > (i2 = this.zzb)) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        int i3 = i + 1;
        float[] fArr = this.zza;
        if (i2 < fArr.length) {
            System.arraycopy(fArr, i, fArr, i3, i2 - i);
        } else {
            float[] fArr2 = new float[((i2 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            System.arraycopy(this.zza, i, fArr2, i3, this.zzb - i);
            this.zza = fArr2;
        }
        this.zza[i] = fFloatValue;
        this.zzb++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zza();
        byte[] bArr = zzvc.zzb;
        collection.getClass();
        if (!(collection instanceof zzuo)) {
            return super.addAll(collection);
        }
        zzuo zzuoVar = (zzuo) collection;
        int i = zzuoVar.zzb;
        if (i == 0) {
            return false;
        }
        int i2 = this.zzb;
        if (Integer.MAX_VALUE - i2 < i) {
            throw new OutOfMemoryError();
        }
        int i3 = i2 + i;
        float[] fArr = this.zza;
        if (i3 > fArr.length) {
            this.zza = Arrays.copyOf(fArr, i3);
        }
        System.arraycopy(zzuoVar.zza, 0, this.zza, this.zzb, zzuoVar.zzb);
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
        if (!(obj instanceof zzuo)) {
            return super.equals(obj);
        }
        zzuo zzuoVar = (zzuo) obj;
        if (this.zzb != zzuoVar.zzb) {
            return false;
        }
        float[] fArr = zzuoVar.zza;
        for (int i = 0; i < this.zzb; i++) {
            if (Float.floatToIntBits(this.zza[i]) != Float.floatToIntBits(fArr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        zzh(i);
        return Float.valueOf(this.zza[i]);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int iFloatToIntBits = 1;
        for (int i = 0; i < this.zzb; i++) {
            iFloatToIntBits = (iFloatToIntBits * 31) + Float.floatToIntBits(this.zza[i]);
        }
        return iFloatToIntBits;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Float)) {
            return -1;
        }
        float fFloatValue = ((Float) obj).floatValue();
        int i = this.zzb;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zza[i2] == fFloatValue) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        zzh(i);
        float[] fArr = this.zza;
        float f = fArr[i];
        if (i < this.zzb - 1) {
            System.arraycopy(fArr, i + 1, fArr, i, (r2 - i) - 1);
        }
        this.zzb--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        zza();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.zza;
        System.arraycopy(fArr, i2, fArr, i, this.zzb - i2);
        this.zzb -= i2 - i;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        float fFloatValue = ((Float) obj).floatValue();
        zza();
        zzh(i);
        float[] fArr = this.zza;
        float f = fArr[i];
        fArr[i] = fFloatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvb
    public final /* bridge */ /* synthetic */ zzvb zzd(int i) {
        if (i >= this.zzb) {
            return new zzuo(Arrays.copyOf(this.zza, i), this.zzb, true);
        }
        throw new IllegalArgumentException();
    }

    public final float zze(int i) {
        zzh(i);
        return this.zza[i];
    }

    public final void zzf(float f) {
        zza();
        int i = this.zzb;
        float[] fArr = this.zza;
        if (i == fArr.length) {
            float[] fArr2 = new float[((i * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, i);
            this.zza = fArr2;
        }
        float[] fArr3 = this.zza;
        int i2 = this.zzb;
        this.zzb = i2 + 1;
        fArr3[i2] = f;
    }

    private zzuo(float[] fArr, int i, boolean z) {
        super(z);
        this.zza = fArr;
        this.zzb = i;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzth, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzf(((Float) obj).floatValue());
        return true;
    }
}
