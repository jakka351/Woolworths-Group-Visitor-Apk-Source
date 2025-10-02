package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
class zzwr extends AbstractMap {
    private Object[] zza;
    private int zzb;
    private boolean zzd;
    private volatile zzwp zze;
    private Map zzc = Collections.emptyMap();
    private Map zzf = Collections.emptyMap();

    private zzwr() {
    }

    private final int zzl(Comparable comparable) {
        int i = this.zzb - 1;
        int i2 = 0;
        if (i >= 0) {
            int iCompareTo = comparable.compareTo(((zzwl) this.zza[i]).zza());
            if (iCompareTo > 0) {
                return -(i + 2);
            }
            if (iCompareTo == 0) {
                return i;
            }
        }
        while (i2 <= i) {
            int i3 = (i2 + i) / 2;
            int iCompareTo2 = comparable.compareTo(((zzwl) this.zza[i3]).zza());
            if (iCompareTo2 < 0) {
                i = i3 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i3;
                }
                i2 = i3 + 1;
            }
        }
        return -(i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzm(int i) {
        zzo();
        Object value = ((zzwl) this.zza[i]).getValue();
        Object[] objArr = this.zza;
        System.arraycopy(objArr, i + 1, objArr, i, (this.zzb - i) - 1);
        this.zzb--;
        if (!this.zzc.isEmpty()) {
            Iterator it = zzn().entrySet().iterator();
            Object[] objArr2 = this.zza;
            int i2 = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            objArr2[i2] = new zzwl(this, (Comparable) entry.getKey(), entry.getValue());
            this.zzb++;
            it.remove();
        }
        return value;
    }

    private final SortedMap zzn() {
        zzo();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzo() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzo();
        if (this.zzb != 0) {
            this.zza = null;
            this.zzb = 0;
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzl(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzwp(this, null);
        }
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzwr)) {
            return super.equals(obj);
        }
        zzwr zzwrVar = (zzwr) obj;
        int size = size();
        if (size != zzwrVar.size()) {
            return false;
        }
        int i = this.zzb;
        if (i != zzwrVar.zzb) {
            return entrySet().equals(zzwrVar.entrySet());
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzg(i2).equals(zzwrVar.zzg(i2))) {
                return false;
            }
        }
        if (i != size) {
            return this.zzc.equals(zzwrVar.zzc);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZzl = zzl(comparable);
        return iZzl >= 0 ? ((zzwl) this.zza[iZzl]).getValue() : this.zzc.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int i = this.zzb;
        int iHashCode = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iHashCode += this.zza[i2].hashCode();
        }
        return this.zzc.size() > 0 ? iHashCode + this.zzc.hashCode() : iHashCode;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzo();
        Comparable comparable = (Comparable) obj;
        int iZzl = zzl(comparable);
        if (iZzl >= 0) {
            return zzm(iZzl);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzb + this.zzc.size();
    }

    public void zza() {
        if (this.zzd) {
            return;
        }
        this.zzc = this.zzc.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzc);
        this.zzf = this.zzf.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzf);
        this.zzd = true;
    }

    public final int zzc() {
        return this.zzb;
    }

    public final Iterable zzd() {
        return this.zzc.isEmpty() ? Collections.emptySet() : this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final Object put(Comparable comparable, Object obj) {
        zzo();
        int iZzl = zzl(comparable);
        if (iZzl >= 0) {
            return ((zzwl) this.zza[iZzl]).setValue(obj);
        }
        zzo();
        if (this.zza == null) {
            this.zza = new Object[16];
        }
        int i = -(iZzl + 1);
        if (i >= 16) {
            return zzn().put(comparable, obj);
        }
        if (this.zzb == 16) {
            zzwl zzwlVar = (zzwl) this.zza[15];
            this.zzb = 15;
            zzn().put(zzwlVar.zza(), zzwlVar.getValue());
        }
        Object[] objArr = this.zza;
        int length = objArr.length;
        System.arraycopy(objArr, i, objArr, i + 1, (16 - i) - 1);
        this.zza[i] = new zzwl(this, comparable, obj);
        this.zzb++;
        return null;
    }

    public final Map.Entry zzg(int i) {
        if (i < this.zzb) {
            return (zzwl) this.zza[i];
        }
        throw new ArrayIndexOutOfBoundsException(i);
    }

    public final boolean zzj() {
        return this.zzd;
    }

    /* synthetic */ zzwr(zzwq zzwqVar) {
    }
}
