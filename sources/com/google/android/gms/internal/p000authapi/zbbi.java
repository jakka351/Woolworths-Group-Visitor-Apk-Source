package com.google.android.gms.internal.p000authapi;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes3.dex */
public abstract class zbbi extends zbbf implements List, RandomAccess {
    private static final zbbl zba = new zbbg(zbbj.zba, 0);

    zbbi() {
    }

    public static zbbi zbi(Collection collection) {
        if (collection instanceof zbbf) {
            zbbi zbbiVarZbe = ((zbbf) collection).zbe();
            if (!zbbiVarZbe.zbf()) {
                return zbbiVarZbe;
            }
            Object[] array = zbbiVarZbe.toArray();
            return zbj(array, array.length);
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        for (int i = 0; i < length; i++) {
            if (array2[i] == null) {
                StringBuilder sb = new StringBuilder(String.valueOf(i).length() + 9);
                sb.append("at index ");
                sb.append(i);
                throw new NullPointerException(sb.toString());
            }
        }
        return zbj(array2, array2.length);
    }

    static zbbi zbj(Object[] objArr, int i) {
        return i == 0 ? zbbj.zba : new zbbj(objArr, i);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (Objects.equals(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !Objects.equals(it.next(), it2.next())) {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i = 0; i < size; i++) {
            iHashCode = (iHashCode * 31) + get(i).hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    /* renamed from: zba */
    public final zbbk iterator() {
        return listIterator(0);
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    @Deprecated
    public final zbbi zbe() {
        return this;
    }

    @Override // com.google.android.gms.internal.p000authapi.zbbf
    int zbg(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // java.util.List
    /* renamed from: zbh, reason: merged with bridge method [inline-methods] */
    public zbbi subList(int i, int i2) {
        zbbc.zbc(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? zbbj.zba : new zbbh(this, i, i3);
    }

    @Override // java.util.List
    /* renamed from: zbk, reason: merged with bridge method [inline-methods] */
    public final zbbl listIterator(int i) {
        zbbc.zbb(i, size(), "index");
        return isEmpty() ? zba : new zbbg(this, i);
    }
}
