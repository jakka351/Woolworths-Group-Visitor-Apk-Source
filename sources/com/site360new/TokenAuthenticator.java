package com.site360new;

import android.content.Context;
import android.util.Log;
import io.sentry.protocol.Response;
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
import kotlin.text.StringsKt;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.Route;
import retrofit2.Call;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/site360new/TokenAuthenticator;", "Lokhttp3/Authenticator;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiClient", "Lcom/site360new/ApiClient;", "keychainManager", "Lcom/site360new/KeychainManager;", "authenticate", "Lokhttp3/Request;", "route", "Lokhttp3/Route;", Response.TYPE, "Lokhttp3/Response;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TokenAuthenticator implements Authenticator {
    private final ApiClient apiClient;
    private final Context context;
    private final KeychainManager keychainManager;

    public TokenAuthenticator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.keychainManager = new KeychainManager(context);
        this.apiClient = new ApiClient(context);
    }

    @Override // okhttp3.Authenticator
    public Request authenticate(Route route, okhttp3.Response response) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, InvalidAlgorithmParameterException {
        TokenData data;
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.code() != 401) {
            Log.d("TokenAuthenticator", "Not a 401 response (" + response.code() + "), skipping token refresh");
            return null;
        }
        Log.d("TokenAuthenticator", "Handling 401 response - attempting token refresh");
        String refreshToken = this.keychainManager.getRefreshToken();
        Log.d("TokenAuthenticator", "Retrieved refresh token from keychain: " + (refreshToken != null ? "present (length: " + refreshToken.length() + ")" : "null"));
        if (refreshToken == null) {
            Log.e("TokenAuthenticator", "No refresh token available");
            return null;
        }
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest(refreshToken);
        Log.d("TokenAuthenticator", "Created refresh token request body");
        Call<RefreshResponse> callRefreshToken = this.apiClient.getApiService().refreshToken(refreshTokenRequest);
        try {
            Log.d("TokenAuthenticator", "Executing refresh token API call");
            retrofit2.Response<RefreshResponse> responseExecute = callRefreshToken.execute();
            Log.d("TokenAuthenticator", "Refresh API response: isSuccessful=" + responseExecute.isSuccessful() + ", code=" + responseExecute.code());
            if (responseExecute.isSuccessful()) {
                RefreshResponse refreshResponseBody = responseExecute.body();
                if (((refreshResponseBody == null || (data = refreshResponseBody.getData()) == null) ? null : data.getAccessToken()) != null) {
                    RefreshResponse refreshResponseBody2 = responseExecute.body();
                    Intrinsics.checkNotNull(refreshResponseBody2);
                    TokenData data2 = refreshResponseBody2.getData();
                    Intrinsics.checkNotNull(data2);
                    String accessToken = data2.getAccessToken();
                    Intrinsics.checkNotNull(accessToken);
                    RefreshResponse refreshResponseBody3 = responseExecute.body();
                    Intrinsics.checkNotNull(refreshResponseBody3);
                    TokenData data3 = refreshResponseBody3.getData();
                    Intrinsics.checkNotNull(data3);
                    String refreshToken2 = data3.getRefreshToken();
                    Log.d("TokenAuthenticator", "Token refresh successful - new access token: " + StringsKt.take(accessToken, 10) + "..., new refresh token: " + (refreshToken2 != null ? "present" : "null"));
                    this.keychainManager.setAccessToken(accessToken);
                    Log.d("TokenAuthenticator", "Saved new access token to keychain");
                    if (refreshToken2 != null) {
                        this.keychainManager.setRefreshToken(refreshToken2);
                        Log.d("TokenAuthenticator", "Saved new refresh token to keychain");
                    }
                    RNTokenManager.INSTANCE.notifyTokenUpdate();
                    Log.d("TokenAuthenticator", "Token refresh completed successfully, retrying original request");
                    return response.request().newBuilder().header("Authorization", "Bearer " + accessToken).build();
                }
            }
            ResponseBody responseBodyErrorBody = responseExecute.errorBody();
            Log.e("TokenAuthenticator", "Token refresh failed: " + (responseBodyErrorBody != null ? responseBodyErrorBody.string() : null));
            return null;
        } catch (Exception e) {
            Log.e("TokenAuthenticator", "Exception during token refresh: " + e.getMessage());
            return null;
        }
    }
}
