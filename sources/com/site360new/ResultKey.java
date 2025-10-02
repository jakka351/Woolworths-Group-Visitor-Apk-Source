package com.site360new;

import kotlin.Metadata;

/* compiled from: Site360Package.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0015"}, d2 = {"Lcom/site360new/ResultKey;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "DEVICE_ID", "DEVICE_MODEL", "DEVICE_MANUFACTURER", "FILE", "FILE_TYPE", "FILE_SIZE", "FACES", "ANGLE", "EYES", "LATITUDE", "LONGITUDE", "ACCURACY", "MOCK_LOCATION", "INSIDE_POLYGON", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum ResultKey {
    DEVICE_ID("deviceId"),
    DEVICE_MODEL("deviceModel"),
    DEVICE_MANUFACTURER("deviceManufacturer"),
    FILE("file"),
    FILE_TYPE("fileType"),
    FILE_SIZE("fileSize"),
    FACES("faces"),
    ANGLE("angle"),
    EYES("eyes"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    ACCURACY("accuracy"),
    MOCK_LOCATION("mockLocation"),
    INSIDE_POLYGON("insidePolygon");

    private final String value;

    ResultKey(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
