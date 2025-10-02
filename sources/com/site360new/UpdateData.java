package com.site360new;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b!\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010$\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010%\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001fJn\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eHÆ\u0001¢\u0006\u0002\u0010*J\u0013\u0010+\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u000eHÖ\u0001J\t\u0010.\u001a\u00020\nHÖ\u0001R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\f\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001d\u0010\u001bR\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001f¨\u0006/"}, d2 = {"Lcom/site360new/UpdateData;", "", "latitude", "", "longitude", "accuracy", "", "acknowledge", "", "deviceId", "", "authToken", "appId", "userId", "", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAccuracy", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getAcknowledge", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getAppId", "()Ljava/lang/String;", "getAuthToken", "getDeviceId", "getLatitude", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLongitude", "getUserId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/site360new/UpdateData;", "equals", "other", "hashCode", "toString", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UpdateData {

    @SerializedName("horizontal_accuracy")
    private final Float accuracy;

    @SerializedName("uc_acknowledge")
    private final Boolean acknowledge;

    @SerializedName("appId")
    private final String appId;

    @SerializedName("auth_token")
    private final String authToken;

    @SerializedName("device_id")
    private final String deviceId;

    @SerializedName("latitude")
    private final Double latitude;

    @SerializedName("longitude")
    private final Double longitude;

    @SerializedName("UserId")
    private final Integer userId;

    /* renamed from: component1, reason: from getter */
    public final Double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component2, reason: from getter */
    public final Double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component3, reason: from getter */
    public final Float getAccuracy() {
        return this.accuracy;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getAcknowledge() {
        return this.acknowledge;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getAuthToken() {
        return this.authToken;
    }

    /* renamed from: component7, reason: from getter */
    public final String getAppId() {
        return this.appId;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getUserId() {
        return this.userId;
    }

    public final UpdateData copy(Double latitude, Double longitude, Float accuracy, Boolean acknowledge, String deviceId, String authToken, String appId, Integer userId) {
        return new UpdateData(latitude, longitude, accuracy, acknowledge, deviceId, authToken, appId, userId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateData)) {
            return false;
        }
        UpdateData updateData = (UpdateData) other;
        return Intrinsics.areEqual((Object) this.latitude, (Object) updateData.latitude) && Intrinsics.areEqual((Object) this.longitude, (Object) updateData.longitude) && Intrinsics.areEqual((Object) this.accuracy, (Object) updateData.accuracy) && Intrinsics.areEqual(this.acknowledge, updateData.acknowledge) && Intrinsics.areEqual(this.deviceId, updateData.deviceId) && Intrinsics.areEqual(this.authToken, updateData.authToken) && Intrinsics.areEqual(this.appId, updateData.appId) && Intrinsics.areEqual(this.userId, updateData.userId);
    }

    public int hashCode() {
        Double d = this.latitude;
        int iHashCode = (d == null ? 0 : d.hashCode()) * 31;
        Double d2 = this.longitude;
        int iHashCode2 = (iHashCode + (d2 == null ? 0 : d2.hashCode())) * 31;
        Float f = this.accuracy;
        int iHashCode3 = (iHashCode2 + (f == null ? 0 : f.hashCode())) * 31;
        Boolean bool = this.acknowledge;
        int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.deviceId;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.authToken;
        int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.appId;
        int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.userId;
        return iHashCode7 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "UpdateData(latitude=" + this.latitude + ", longitude=" + this.longitude + ", accuracy=" + this.accuracy + ", acknowledge=" + this.acknowledge + ", deviceId=" + this.deviceId + ", authToken=" + this.authToken + ", appId=" + this.appId + ", userId=" + this.userId + ")";
    }

    public UpdateData(Double d, Double d2, Float f, Boolean bool, String str, String str2, String str3, Integer num) {
        this.latitude = d;
        this.longitude = d2;
        this.accuracy = f;
        this.acknowledge = bool;
        this.deviceId = str;
        this.authToken = str2;
        this.appId = str3;
        this.userId = num;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final Float getAccuracy() {
        return this.accuracy;
    }

    public final Boolean getAcknowledge() {
        return this.acknowledge;
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

    public final Integer getUserId() {
        return this.userId;
    }
}
