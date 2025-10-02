package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public abstract class zzs extends zzl implements Set {

    @CheckForNull
    private transient zzp zza;

    zzs() {
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
        return zzz.zza(this);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zzd */
    public abstract zzaa iterator();

    public final zzp zzf() {
        zzp zzpVar = this.zza;
        if (zzpVar != null) {
            return zzpVar;
        }
        zzp zzpVarZzg = zzg();
        this.zza = zzpVarZzg;
        return zzpVarZzg;
    }

    zzp zzg() {
        Object[] array = toArray();
        int i = zzp.zzd;
        return zzp.zzg(array, array.length);
    }
}
