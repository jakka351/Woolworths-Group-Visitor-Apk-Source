package com.site360new;

import android.content.Context;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/site360new/AuthInterceptor;", "Lokhttp3/Interceptor;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "keychainManager", "Lcom/site360new/KeychainManager;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AuthInterceptor implements Interceptor {
    private final Context context;
    private final KeychainManager keychainManager;

    public AuthInterceptor(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.keychainManager = new KeychainManager(context);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, InvalidAlgorithmParameterException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        String accessToken = this.keychainManager.getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        Request.Builder builderNewBuilder = chain.request().newBuilder();
        builderNewBuilder.addHeader("Authorization", "Bearer " + accessToken);
        builderNewBuilder.addHeader("x-api-key", BuildConfig.X_API_KEY);
        return chain.proceed(builderNewBuilder.build());
    }
}
