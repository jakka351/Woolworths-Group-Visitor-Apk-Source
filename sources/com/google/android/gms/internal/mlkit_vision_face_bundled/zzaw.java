package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public abstract class zzaw implements Map, Serializable {

    @CheckForNull
    private transient zzax zza;

    @CheckForNull
    private transient zzax zzb;

    @CheckForNull
    private transient zzaq zzc;

    zzaw() {
    }

    public static zzaw zzc(Object obj, Object obj2) {
        zzan.zza("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
        return zzbd.zzg(1, new Object[]{"optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID}, null);
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(@CheckForNull Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(@CheckForNull Object obj) {
        return values().contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(@CheckForNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    @CheckForNull
    public abstract Object get(@CheckForNull Object obj);

    @Override // java.util.Map
    @CheckForNull
    public final Object getOrDefault(@CheckForNull Object obj, @CheckForNull Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzbe.zza(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzax zzaxVar = this.zzb;
        if (zzaxVar != null) {
            return zzaxVar;
        }
        zzax zzaxVarZze = zze();
        this.zzb = zzaxVarZze;
        return zzaxVarZze;
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CheckForNull
    @Deprecated
    public final Object remove(@CheckForNull Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        if (size < 0) {
            throw new IllegalArgumentException("size cannot be negative but was: " + size);
        }
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    abstract zzaq zza();

    @Override // java.util.Map
    /* renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public final zzaq values() {
        zzaq zzaqVar = this.zzc;
        if (zzaqVar != null) {
            return zzaqVar;
        }
        zzaq zzaqVarZza = zza();
        this.zzc = zzaqVarZza;
        return zzaqVarZza;
    }

    abstract zzax zzd();

    abstract zzax zze();

    @Override // java.util.Map
    /* renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final zzax entrySet() {
        zzax zzaxVar = this.zza;
        if (zzaxVar != null) {
            return zzaxVar;
        }
        zzax zzaxVarZzd = zzd();
        this.zza = zzaxVarZzd;
        return zzaxVarZzd;
    }
}
