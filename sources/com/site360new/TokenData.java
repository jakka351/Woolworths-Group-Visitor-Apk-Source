package com.site360new;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ>\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/site360new/TokenData;", "", "accessToken", "", "refreshToken", "idToken", "expiresIn", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAccessToken", "()Ljava/lang/String;", "getExpiresIn", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIdToken", "getRefreshToken", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/site360new/TokenData;", "equals", "", "other", "hashCode", "toString", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TokenData {

    @SerializedName("accessToken")
    private final String accessToken;

    @SerializedName("expiresIn")
    private final Integer expiresIn;

    @SerializedName("idToken")
    private final String idToken;

    @SerializedName("refreshToken")
    private final String refreshToken;

    public static /* synthetic */ TokenData copy$default(TokenData tokenData, String str, String str2, String str3, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tokenData.accessToken;
        }
        if ((i & 2) != 0) {
            str2 = tokenData.refreshToken;
        }
        if ((i & 4) != 0) {
            str3 = tokenData.idToken;
        }
        if ((i & 8) != 0) {
            num = tokenData.expiresIn;
        }
        return tokenData.copy(str, str2, str3, num);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    /* renamed from: component3, reason: from getter */
    public final String getIdToken() {
        return this.idToken;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getExpiresIn() {
        return this.expiresIn;
    }

    public final TokenData copy(String accessToken, String refreshToken, String idToken, Integer expiresIn) {
        return new TokenData(accessToken, refreshToken, idToken, expiresIn);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) other;
        return Intrinsics.areEqual(this.accessToken, tokenData.accessToken) && Intrinsics.areEqual(this.refreshToken, tokenData.refreshToken) && Intrinsics.areEqual(this.idToken, tokenData.idToken) && Intrinsics.areEqual(this.expiresIn, tokenData.expiresIn);
    }

    public int hashCode() {
        String str = this.accessToken;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.refreshToken;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.idToken;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.expiresIn;
        return iHashCode3 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "TokenData(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", idToken=" + this.idToken + ", expiresIn=" + this.expiresIn + ")";
    }

    public TokenData(String str, String str2, String str3, Integer num) {
        this.accessToken = str;
        this.refreshToken = str2;
        this.idToken = str3;
        this.expiresIn = num;
    }

    public final String getAccessToken() {
        return this.accessToken;
    }

    public final String getRefreshToken() {
        return this.refreshToken;
    }

    public final String getIdToken() {
        return this.idToken;
    }

    public final Integer getExpiresIn() {
        return this.expiresIn;
    }
}
