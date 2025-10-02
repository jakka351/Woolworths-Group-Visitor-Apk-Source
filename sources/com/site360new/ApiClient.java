package com.site360new;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\u0006J\b\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/site360new/ApiClient;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "apiService", "Lcom/site360new/ApiService;", "keychainManager", "Lcom/site360new/KeychainManager;", "getApiService", "httpClient", "Lokhttp3/OkHttpClient;", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ApiClient {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static String baseUrl = "";
    private ApiService apiService;
    private final Context context;
    private final KeychainManager keychainManager;

    public ApiClient(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.keychainManager = new KeychainManager(context);
    }

    public final ApiService getApiService() throws SecurityException {
        if (this.apiService == null) {
            Object objCreate = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(httpClient()).build().create(ApiService.class);
            Intrinsics.checkNotNullExpressionValue(objCreate, "retrofit.create(ApiService::class.java)");
            this.apiService = (ApiService) objCreate;
        }
        ApiService apiService = this.apiService;
        if (apiService != null) {
            return apiService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("apiService");
        return null;
    }

    private final OkHttpClient httpClient() {
        return new OkHttpClient.Builder().addInterceptor(new AuthInterceptor(this.context)).authenticator(new TokenAuthenticator(this.context)).build();
    }

    /* compiled from: ApiService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/site360new/ApiClient$Companion;", "", "()V", "baseUrl", "", "getBaseUrl", "()Ljava/lang/String;", "setBaseUrl", "(Ljava/lang/String;)V", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getBaseUrl() {
            return ApiClient.baseUrl;
        }

        public final void setBaseUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            ApiClient.baseUrl = str;
        }
    }
}
