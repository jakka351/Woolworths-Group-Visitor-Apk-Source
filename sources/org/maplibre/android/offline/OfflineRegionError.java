package org.maplibre.android.offline;

import io.sentry.clientreport.DiscardedEvent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OfflineRegionError.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011B\u0019\b\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0012"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegionError;", "", DiscardedEvent.JsonKeys.REASON, "", "message", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "getMessage", "equals", "", "o", "hashCode", "", "toString", "ErrorReason", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OfflineRegionError {
    public static final String REASON_CONNECTION = "REASON_CONNECTION";
    public static final String REASON_NOT_FOUND = "REASON_NOT_FOUND";
    public static final String REASON_OTHER = "REASON_OTHER";
    public static final String REASON_SERVER = "REASON_SERVER";
    public static final String REASON_SUCCESS = "REASON_SUCCESS";
    private final String message;
    private final String reason;

    /* compiled from: OfflineRegionError.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegionError$ErrorReason;", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface ErrorReason {
    }

    private OfflineRegionError(String str, String str2) {
        this.reason = str;
        this.message = str2;
    }

    public final String getReason() {
        return this.reason;
    }

    public final String getMessage() {
        return this.message;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Intrinsics.areEqual(getClass(), o.getClass())) {
            return false;
        }
        OfflineRegionError offlineRegionError = (OfflineRegionError) o;
        if (Intrinsics.areEqual(this.reason, offlineRegionError.reason)) {
            return Intrinsics.areEqual(this.message, offlineRegionError.message);
        }
        return false;
    }

    public int hashCode() {
        return (this.reason.hashCode() * 31) + this.message.hashCode();
    }

    public String toString() {
        return "OfflineRegionError{reason='" + this.reason + "', message='" + this.message + "'}";
    }
}
