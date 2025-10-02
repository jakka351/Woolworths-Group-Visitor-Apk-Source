package com.site360new;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/site360new/RefreshResponse;", "", "data", "Lcom/site360new/TokenData;", "message", "", "error", "(Lcom/site360new/TokenData;Ljava/lang/String;Ljava/lang/String;)V", "getData", "()Lcom/site360new/TokenData;", "getError", "()Ljava/lang/String;", "getMessage", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class RefreshResponse {

    @SerializedName("data")
    private final TokenData data;

    @SerializedName("error")
    private final String error;

    @SerializedName("message")
    private final String message;

    public static /* synthetic */ RefreshResponse copy$default(RefreshResponse refreshResponse, TokenData tokenData, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            tokenData = refreshResponse.data;
        }
        if ((i & 2) != 0) {
            str = refreshResponse.message;
        }
        if ((i & 4) != 0) {
            str2 = refreshResponse.error;
        }
        return refreshResponse.copy(tokenData, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final TokenData getData() {
        return this.data;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component3, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final RefreshResponse copy(TokenData data, String message, String error) {
        return new RefreshResponse(data, message, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RefreshResponse)) {
            return false;
        }
        RefreshResponse refreshResponse = (RefreshResponse) other;
        return Intrinsics.areEqual(this.data, refreshResponse.data) && Intrinsics.areEqual(this.message, refreshResponse.message) && Intrinsics.areEqual(this.error, refreshResponse.error);
    }

    public int hashCode() {
        TokenData tokenData = this.data;
        int iHashCode = (tokenData == null ? 0 : tokenData.hashCode()) * 31;
        String str = this.message;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.error;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "RefreshResponse(data=" + this.data + ", message=" + this.message + ", error=" + this.error + ")";
    }

    public RefreshResponse(TokenData tokenData, String str, String str2) {
        this.data = tokenData;
        this.message = str;
        this.error = str2;
    }

    public final TokenData getData() {
        return this.data;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getError() {
        return this.error;
    }
}
