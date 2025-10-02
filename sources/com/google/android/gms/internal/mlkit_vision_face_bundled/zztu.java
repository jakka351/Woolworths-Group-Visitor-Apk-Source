package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public abstract class zztu implements Iterable, Serializable {
    public static final zztu zzb = new zztt(zzvc.zzb);
    private int zza = 0;

    static {
        int i = zzti.zza;
    }

    zztu() {
    }

    public static zztu zzj(byte[] bArr, int i, int i2) {
        zzh(i, i + i2, bArr.length);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new zztt(bArr2);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int iZze = this.zza;
        if (iZze == 0) {
            int iZzd = zzd();
            iZze = zze(iZzd, 0, iZzd);
            if (iZze == 0) {
                iZze = 1;
            }
            this.zza = iZze;
        }
        return iZze;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zztn(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzwt.zza(this) : zzwt.zza(zzf(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i);

    abstract byte zzb(int i);

    public abstract int zzd();

    protected abstract int zze(int i, int i2, int i3);

    public abstract zztu zzf(int i, int i2);

    abstract void zzg(zztm zztmVar) throws IOException;

    protected final int zzi() {
        return this.zza;
    }

    static int zzh(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Beginning index: " + i + " < 0");
        }
        if (i2 < i) {
            throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + i + ", " + i2);
        }
        throw new IndexOutOfBoundsException("End index: " + i2 + " >= " + i3);
    }
}
