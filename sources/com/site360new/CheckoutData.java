package com.site360new;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\rJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jz\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0019\u0010\u0015¨\u0006*"}, d2 = {"Lcom/site360new/CheckoutData;", "", "visitId", "", "deviceId", "", "authToken", "appId", "status", "checkOutMethod", "checkOutMethodInternal", "defaultTimer", "checkOutTime", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "getAuthToken", "getCheckOutMethod", "getCheckOutMethodInternal", "getCheckOutTime", "getDefaultTimer", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDeviceId", "getStatus", "getVisitId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/site360new/CheckoutData;", "equals", "", "other", "hashCode", "toString", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class CheckoutData {

    @SerializedName("AppId")
    private final String appId;

    @SerializedName("AuthToken")
    private final String authToken;

    @SerializedName("CheckOutMethod")
    private final String checkOutMethod;

    @SerializedName("CheckOutMethodInternal")
    private final String checkOutMethodInternal;

    @SerializedName("CheckOutTime")
    private final String checkOutTime;

    @SerializedName("DefaultTimer")
    private final Integer defaultTimer;

    @SerializedName("DeviceId")
    private final String deviceId;

    @SerializedName("Status")
    private final String status;

    @SerializedName("VisitId")
    private final Integer visitId;

    /* renamed from: component1, reason: from getter */
    public final Integer getVisitId() {
        return this.visitId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAuthToken() {
        return this.authToken;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAppId() {
        return this.appId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCheckOutMethod() {
        return this.checkOutMethod;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCheckOutMethodInternal() {
        return this.checkOutMethodInternal;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getDefaultTimer() {
        return this.defaultTimer;
    }

    /* renamed from: component9, reason: from getter */
    public final String getCheckOutTime() {
        return this.checkOutTime;
    }

    public final CheckoutData copy(Integer visitId, String deviceId, String authToken, String appId, String status, String checkOutMethod, String checkOutMethodInternal, Integer defaultTimer, String checkOutTime) {
        return new CheckoutData(visitId, deviceId, authToken, appId, status, checkOutMethod, checkOutMethodInternal, defaultTimer, checkOutTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckoutData)) {
            return false;
        }
        CheckoutData checkoutData = (CheckoutData) other;
        return Intrinsics.areEqual(this.visitId, checkoutData.visitId) && Intrinsics.areEqual(this.deviceId, checkoutData.deviceId) && Intrinsics.areEqual(this.authToken, checkoutData.authToken) && Intrinsics.areEqual(this.appId, checkoutData.appId) && Intrinsics.areEqual(this.status, checkoutData.status) && Intrinsics.areEqual(this.checkOutMethod, checkoutData.checkOutMethod) && Intrinsics.areEqual(this.checkOutMethodInternal, checkoutData.checkOutMethodInternal) && Intrinsics.areEqual(this.defaultTimer, checkoutData.defaultTimer) && Intrinsics.areEqual(this.checkOutTime, checkoutData.checkOutTime);
    }

    public int hashCode() {
        Integer num = this.visitId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.deviceId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.authToken;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.appId;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.status;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.checkOutMethod;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.checkOutMethodInternal;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num2 = this.defaultTimer;
        int iHashCode8 = (iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str7 = this.checkOutTime;
        return iHashCode8 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "CheckoutData(visitId=" + this.visitId + ", deviceId=" + this.deviceId + ", authToken=" + this.authToken + ", appId=" + this.appId + ", status=" + this.status + ", checkOutMethod=" + this.checkOutMethod + ", checkOutMethodInternal=" + this.checkOutMethodInternal + ", defaultTimer=" + this.defaultTimer + ", checkOutTime=" + this.checkOutTime + ")";
    }

    public CheckoutData(Integer num, String str, String str2, String str3, String str4, String str5, String str6, Integer num2, String str7) {
        this.visitId = num;
        this.deviceId = str;
        this.authToken = str2;
        this.appId = str3;
        this.status = str4;
        this.checkOutMethod = str5;
        this.checkOutMethodInternal = str6;
        this.defaultTimer = num2;
        this.checkOutTime = str7;
    }

    public final Integer getVisitId() {
        return this.visitId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getAuthToken() {
        return this.authToken;
    }

    public final String getAppId() {
        return this.appId;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getCheckOutMethod() {
        return this.checkOutMethod;
    }

    public final String getCheckOutMethodInternal() {
        return this.checkOutMethodInternal;
    }

    public final Integer getDefaultTimer() {
        return this.defaultTimer;
    }

    public final String getCheckOutTime() {
        return this.checkOutTime;
    }
}
