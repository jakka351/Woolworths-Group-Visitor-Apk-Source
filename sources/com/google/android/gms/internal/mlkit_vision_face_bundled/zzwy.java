package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzwy implements PrivilegedExceptionAction {
    zzwy() {
    }

    @Override // java.security.PrivilegedExceptionAction
    public final /* bridge */ /* synthetic */ Object run() throws Exception {
        for (Field field : Unsafe.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (Unsafe.class.isInstance(obj)) {
                return (Unsafe) Unsafe.class.cast(obj);
            }
        }
        return null;
    }
}
