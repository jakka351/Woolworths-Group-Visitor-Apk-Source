package com.google.android.gms.internal.auth;

import android.util.Log;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.CharEncoding;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
/* loaded from: classes3.dex */
public abstract class zzbz extends FastSafeParcelableJsonResponse {
    @Override // com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse
    public final byte[] toByteArray() {
        try {
            return toString().getBytes(CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            Log.e("AUTH", "Error serializing object.", e);
            return null;
        }
    }
}
