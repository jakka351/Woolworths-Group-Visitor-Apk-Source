package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzz {
    static int zza(Set set) {
        Iterator it = set.iterator();
        int iHashCode = 0;
        while (it.hasNext()) {
            Object next = it.next();
            iHashCode += next != null ? next.hashCode() : 0;
        }
        return iHashCode;
    }
}
