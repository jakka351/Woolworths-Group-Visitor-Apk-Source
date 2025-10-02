package com.maplibre.rctmln.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes3.dex */
public class CustomHeadersInterceptor implements Interceptor {
    public static final CustomHeadersInterceptor INSTANCE = new CustomHeadersInterceptor();
    private Map<String, String> customHeaders = new HashMap();

    public void addHeader(String str, String str2) {
        this.customHeaders.put(str, str2);
    }

    public void removeHeader(String str) {
        this.customHeaders.remove(str);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builderNewBuilder = chain.request().newBuilder();
        for (Map.Entry<String, String> entry : this.customHeaders.entrySet()) {
            builderNewBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        return chain.proceed(builderNewBuilder.build());
    }
}
