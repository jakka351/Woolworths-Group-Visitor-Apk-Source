package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public abstract class zzax extends zzaq implements Set {

    @CheckForNull
    private transient zzau zza;

    zzax() {
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzbe.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzbf iterator();

    public final zzau zzf() {
        zzau zzauVar = this.zza;
        if (zzauVar != null) {
            return zzauVar;
        }
        zzau zzauVarZzg = zzg();
        this.zza = zzauVarZzg;
        return zzauVarZzg;
    }

    zzau zzg() {
        Object[] array = toArray();
        int i = zzau.zzd;
        return zzau.zzg(array, array.length);
    }
}
