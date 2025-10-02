package com.site360new;

import kotlin.Metadata;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\nH'J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\rH'¨\u0006\u000e"}, d2 = {"Lcom/site360new/ApiService;", "", "checkout", "Lretrofit2/Call;", "Ljava/lang/Void;", "data", "Lcom/site360new/CheckoutData;", "refreshToken", "Lcom/site360new/RefreshResponse;", "body", "Lcom/site360new/RefreshTokenRequest;", "update", "Lcom/site360new/UpdateResponse;", "Lcom/site360new/UpdateData;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ApiService {
    @POST("v3/visit/checkout")
    Call<Void> checkout(@Body CheckoutData data);

    @POST("v4/users/refresh-token")
    Call<RefreshResponse> refreshToken(@Body RefreshTokenRequest body);

    @POST("v3/UpdateCycle")
    Call<UpdateResponse> update(@Body UpdateData data);
}
