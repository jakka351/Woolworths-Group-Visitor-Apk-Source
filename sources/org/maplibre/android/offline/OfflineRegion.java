package org.maplibre.android.offline;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.sentry.protocol.SentryThread;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.LibraryLoader;
import org.maplibre.android.MapLibre;
import org.maplibre.android.net.ConnectivityReceiver;
import org.maplibre.android.offline.OfflineRegion;
import org.maplibre.android.storage.FileSource;

/* compiled from: OfflineRegion.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 A2\u00020\u0001:\u0007;<=>?@AB1\b\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0014J\b\u0010#\u001a\u00020\u0014H\u0002J\u0010\u0010$\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010&J\u000e\u0010'\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020!2\u0006\u0010)\u001a\u00020,J\u0010\u0010-\u001a\u00020!2\b\u0010)\u001a\u0004\u0018\u00010.J\u0016\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\n2\u0006\u0010)\u001a\u000201J\u0019\u00102\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0083 J\t\u00103\u001a\u00020!H\u0085 J\u0011\u00104\u001a\u00020!2\u0006\u0010)\u001a\u00020&H\u0083 J\u0011\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020\u001dH\u0083 J\u0011\u00107\u001a\u00020!2\u0006\u0010)\u001a\u00020*H\u0083 J\u0011\u00108\u001a\u00020!2\u0006\u0010)\u001a\u00020,H\u0083 J\u0019\u00109\u001a\u00020!2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010)\u001a\u000201H\u0083 J\u0011\u0010:\u001a\u00020!2\u0006\u0010)\u001a\u00020.H\u0083 R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00038\u0002X\u0083D¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006B"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion;", "", "offlineRegionPtr", "", "fileSource", "Lorg/maplibre/android/storage/FileSource;", "idParam", "definition", "Lorg/maplibre/android/offline/OfflineRegionDefinition;", "metadata", "", "<init>", "(JLorg/maplibre/android/storage/FileSource;JLorg/maplibre/android/offline/OfflineRegionDefinition;[B)V", "context", "Landroid/content/Context;", "nativePtr", "id", "getId", "()J", "isDeleted", "", "getDefinition", "()Lorg/maplibre/android/offline/OfflineRegionDefinition;", "value", "getMetadata", "()[B", "handler", "Landroid/os/Handler;", SentryThread.JsonKeys.STATE, "", "isDeliveringInactiveMessages", "()Z", "setDeliverInactiveMessages", "", "deliverInactiveMessages", "deliverMessages", "setObserver", "observer", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionObserver;", "setDownloadState", "getStatus", "callback", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionStatusCallback;", "delete", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionDeleteCallback;", "invalidate", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionInvalidateCallback;", "updateMetadata", "bytes", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionUpdateMetadataCallback;", "initialize", "finalize", "setOfflineRegionObserver", "setOfflineRegionDownloadState", "offlineRegionDownloadState", "getOfflineRegionStatus", "deleteOfflineRegion", "updateOfflineRegionMetadata", "invalidateOfflineRegion", "OfflineRegionObserver", "OfflineRegionStatusCallback", "OfflineRegionDeleteCallback", "OfflineRegionInvalidateCallback", "OfflineRegionUpdateMetadataCallback", "DownloadState", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OfflineRegion {
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_INACTIVE = 0;
    private final OfflineRegionDefinition definition;
    private final FileSource fileSource;
    private final long id;
    private boolean isDeleted;
    private boolean isDeliveringInactiveMessages;
    private byte[] metadata;
    private final long nativePtr;
    private int state;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Context context = MapLibre.getApplicationContext();

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion$DownloadState;", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @Retention(RetentionPolicy.SOURCE)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface DownloadState {
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionDeleteCallback;", "", "onDelete", "", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface OfflineRegionDeleteCallback {
        void onDelete();

        void onError(String error);
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionInvalidateCallback;", "", "onInvalidate", "", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface OfflineRegionInvalidateCallback {
        void onError(String error);

        void onInvalidate();
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionObserver;", "", "onStatusChanged", "", "status", "Lorg/maplibre/android/offline/OfflineRegionStatus;", "onError", "error", "Lorg/maplibre/android/offline/OfflineRegionError;", "mapboxTileCountLimitExceeded", "limit", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface OfflineRegionObserver {
        void mapboxTileCountLimitExceeded(long limit);

        void onError(OfflineRegionError error);

        void onStatusChanged(OfflineRegionStatus status);
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionStatusCallback;", "", "onStatus", "", "status", "Lorg/maplibre/android/offline/OfflineRegionStatus;", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface OfflineRegionStatusCallback {
        void onError(String error);

        void onStatus(OfflineRegionStatus status);
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionUpdateMetadataCallback;", "", "onUpdate", "", "metadata", "", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface OfflineRegionUpdateMetadataCallback {
        void onError(String error);

        void onUpdate(byte[] metadata);
    }

    private final native void deleteOfflineRegion(OfflineRegionDeleteCallback callback);

    private final native void getOfflineRegionStatus(OfflineRegionStatusCallback callback);

    private final native void initialize(long offlineRegionPtr, FileSource fileSource);

    private final native void invalidateOfflineRegion(OfflineRegionInvalidateCallback callback);

    private final native void setOfflineRegionDownloadState(int offlineRegionDownloadState);

    private final native void setOfflineRegionObserver(OfflineRegionObserver callback);

    private final native void updateOfflineRegionMetadata(byte[] metadata, OfflineRegionUpdateMetadataCallback callback);

    protected final native void finalize();

    private OfflineRegion(long j, FileSource fileSource, long j2, OfflineRegionDefinition offlineRegionDefinition, byte[] bArr) {
        this.fileSource = fileSource;
        this.id = j2;
        this.definition = offlineRegionDefinition;
        this.metadata = bArr;
        initialize(j, fileSource);
    }

    public final long getId() {
        return this.id;
    }

    public final OfflineRegionDefinition getDefinition() {
        return this.definition;
    }

    public final byte[] getMetadata() {
        return this.metadata;
    }

    /* renamed from: isDeliveringInactiveMessages, reason: from getter */
    public final boolean getIsDeliveringInactiveMessages() {
        return this.isDeliveringInactiveMessages;
    }

    public final void setDeliverInactiveMessages(boolean deliverInactiveMessages) {
        this.isDeliveringInactiveMessages = deliverInactiveMessages;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean deliverMessages() {
        if (this.state == 1) {
            return true;
        }
        return this.isDeliveringInactiveMessages;
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"org/maplibre/android/offline/OfflineRegion$setObserver$1", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionObserver;", "onStatusChanged", "", "status", "Lorg/maplibre/android/offline/OfflineRegionStatus;", "onError", "error", "Lorg/maplibre/android/offline/OfflineRegionError;", "mapboxTileCountLimitExceeded", "limit", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineRegion$setObserver$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02201 implements OfflineRegionObserver {
        final /* synthetic */ OfflineRegionObserver $observer;

        C02201(OfflineRegionObserver offlineRegionObserver) {
            this.$observer = offlineRegionObserver;
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionObserver
        public void onStatusChanged(final OfflineRegionStatus status) {
            Intrinsics.checkNotNullParameter(status, "status");
            if (OfflineRegion.this.deliverMessages()) {
                Handler handler = OfflineRegion.this.handler;
                final OfflineRegionObserver offlineRegionObserver = this.$observer;
                handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$setObserver$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        OfflineRegion.C02201.onStatusChanged$lambda$0(offlineRegionObserver, status);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onStatusChanged$lambda$0(OfflineRegionObserver offlineRegionObserver, OfflineRegionStatus offlineRegionStatus) {
            if (offlineRegionObserver != null) {
                offlineRegionObserver.onStatusChanged(offlineRegionStatus);
            }
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionObserver
        public void onError(final OfflineRegionError error) {
            Intrinsics.checkNotNullParameter(error, "error");
            if (OfflineRegion.this.deliverMessages()) {
                Handler handler = OfflineRegion.this.handler;
                final OfflineRegionObserver offlineRegionObserver = this.$observer;
                handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$setObserver$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        OfflineRegion.C02201.onError$lambda$1(offlineRegionObserver, error);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineRegionObserver offlineRegionObserver, OfflineRegionError offlineRegionError) {
            if (offlineRegionObserver != null) {
                offlineRegionObserver.onError(offlineRegionError);
            }
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionObserver
        public void mapboxTileCountLimitExceeded(final long limit) {
            if (OfflineRegion.this.deliverMessages()) {
                Handler handler = OfflineRegion.this.handler;
                final OfflineRegionObserver offlineRegionObserver = this.$observer;
                handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$setObserver$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        OfflineRegion.C02201.mapboxTileCountLimitExceeded$lambda$2(offlineRegionObserver, limit);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void mapboxTileCountLimitExceeded$lambda$2(OfflineRegionObserver offlineRegionObserver, long j) {
            if (offlineRegionObserver != null) {
                offlineRegionObserver.mapboxTileCountLimitExceeded(j);
            }
        }
    }

    public final void setObserver(OfflineRegionObserver observer) {
        setOfflineRegionObserver(new C02201(observer));
    }

    public final void setDownloadState(int state) {
        if (this.state == state) {
            return;
        }
        if (state == 1) {
            ConnectivityReceiver.instance(this.context).activate();
            this.fileSource.activate();
        } else {
            this.fileSource.deactivate();
            ConnectivityReceiver.instance(this.context).deactivate();
        }
        this.state = state;
        setOfflineRegionDownloadState(state);
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"org/maplibre/android/offline/OfflineRegion$getStatus$1", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionStatusCallback;", "onStatus", "", "status", "Lorg/maplibre/android/offline/OfflineRegionStatus;", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineRegion$getStatus$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02181 implements OfflineRegionStatusCallback {
        final /* synthetic */ OfflineRegionStatusCallback $callback;

        C02181(OfflineRegionStatusCallback offlineRegionStatusCallback) {
            this.$callback = offlineRegionStatusCallback;
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionStatusCallback
        public void onStatus(final OfflineRegionStatus status) {
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionStatusCallback offlineRegionStatusCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$getStatus$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.C02181.onStatus$lambda$0(offlineRegion, offlineRegionStatusCallback, status);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onStatus$lambda$0(OfflineRegion offlineRegion, OfflineRegionStatusCallback offlineRegionStatusCallback, OfflineRegionStatus offlineRegionStatus) {
            offlineRegion.fileSource.deactivate();
            offlineRegionStatusCallback.onStatus(offlineRegionStatus);
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionStatusCallback
        public void onError(final String error) {
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionStatusCallback offlineRegionStatusCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$getStatus$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.C02181.onError$lambda$1(offlineRegion, offlineRegionStatusCallback, error);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineRegion offlineRegion, OfflineRegionStatusCallback offlineRegionStatusCallback, String str) {
            offlineRegion.fileSource.deactivate();
            offlineRegionStatusCallback.onError(str);
        }
    }

    public final void getStatus(OfflineRegionStatusCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.fileSource.activate();
        getOfflineRegionStatus(new C02181(callback));
    }

    public final void delete(OfflineRegionDeleteCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.isDeleted) {
            return;
        }
        this.isDeleted = true;
        this.fileSource.activate();
        deleteOfflineRegion(new AnonymousClass1(callback));
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineRegion$delete$1", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionDeleteCallback;", "onDelete", "", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineRegion$delete$1, reason: invalid class name */
    public static final class AnonymousClass1 implements OfflineRegionDeleteCallback {
        final /* synthetic */ OfflineRegionDeleteCallback $callback;

        AnonymousClass1(OfflineRegionDeleteCallback offlineRegionDeleteCallback) {
            this.$callback = offlineRegionDeleteCallback;
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionDeleteCallback
        public void onDelete() {
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionDeleteCallback offlineRegionDeleteCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$delete$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.AnonymousClass1.onDelete$lambda$0(offlineRegion, offlineRegionDeleteCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onDelete$lambda$0(OfflineRegion offlineRegion, OfflineRegionDeleteCallback offlineRegionDeleteCallback) {
            offlineRegion.fileSource.deactivate();
            offlineRegionDeleteCallback.onDelete();
            offlineRegion.finalize();
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionDeleteCallback
        public void onError(final String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionDeleteCallback offlineRegionDeleteCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$delete$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.AnonymousClass1.onError$lambda$1(offlineRegion, offlineRegionDeleteCallback, error);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineRegion offlineRegion, OfflineRegionDeleteCallback offlineRegionDeleteCallback, String str) {
            offlineRegion.isDeleted = false;
            offlineRegion.fileSource.deactivate();
            offlineRegionDeleteCallback.onError(str);
        }
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineRegion$invalidate$1", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionInvalidateCallback;", "onInvalidate", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineRegion$invalidate$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02191 implements OfflineRegionInvalidateCallback {
        final /* synthetic */ OfflineRegionInvalidateCallback $callback;

        C02191(OfflineRegionInvalidateCallback offlineRegionInvalidateCallback) {
            this.$callback = offlineRegionInvalidateCallback;
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionInvalidateCallback
        public void onInvalidate() {
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionInvalidateCallback offlineRegionInvalidateCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$invalidate$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.C02191.onInvalidate$lambda$0(offlineRegion, offlineRegionInvalidateCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onInvalidate$lambda$0(OfflineRegion offlineRegion, OfflineRegionInvalidateCallback offlineRegionInvalidateCallback) {
            offlineRegion.fileSource.deactivate();
            if (offlineRegionInvalidateCallback != null) {
                offlineRegionInvalidateCallback.onInvalidate();
            }
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionInvalidateCallback
        public void onError(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionInvalidateCallback offlineRegionInvalidateCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$invalidate$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.C02191.onError$lambda$1(offlineRegion, offlineRegionInvalidateCallback, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineRegion offlineRegion, OfflineRegionInvalidateCallback offlineRegionInvalidateCallback, String str) {
            offlineRegion.fileSource.deactivate();
            if (offlineRegionInvalidateCallback != null) {
                offlineRegionInvalidateCallback.onError(str);
            }
        }
    }

    public final void invalidate(OfflineRegionInvalidateCallback callback) {
        this.fileSource.activate();
        invalidateOfflineRegion(new C02191(callback));
    }

    /* compiled from: OfflineRegion.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"org/maplibre/android/offline/OfflineRegion$updateMetadata$1", "Lorg/maplibre/android/offline/OfflineRegion$OfflineRegionUpdateMetadataCallback;", "onUpdate", "", "metadata", "", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineRegion$updateMetadata$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02211 implements OfflineRegionUpdateMetadataCallback {
        final /* synthetic */ OfflineRegionUpdateMetadataCallback $callback;

        C02211(OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback) {
            this.$callback = offlineRegionUpdateMetadataCallback;
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionUpdateMetadataCallback
        public void onUpdate(final byte[] metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegion offlineRegion = OfflineRegion.this;
            final OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$updateMetadata$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineRegion.C02211.onUpdate$lambda$0(offlineRegion, metadata, offlineRegionUpdateMetadataCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onUpdate$lambda$0(OfflineRegion offlineRegion, byte[] bArr, OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback) {
            offlineRegion.metadata = bArr;
            offlineRegionUpdateMetadataCallback.onUpdate(bArr);
        }

        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionUpdateMetadataCallback
        public void onError(final String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            Handler handler = OfflineRegion.this.handler;
            final OfflineRegionUpdateMetadataCallback offlineRegionUpdateMetadataCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineRegion$updateMetadata$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    offlineRegionUpdateMetadataCallback.onError(error);
                }
            });
        }
    }

    public final void updateMetadata(byte[] bytes, OfflineRegionUpdateMetadataCallback callback) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        Intrinsics.checkNotNullParameter(callback, "callback");
        updateOfflineRegionMetadata(bytes, new C02211(callback));
    }

    static {
        LibraryLoader.load();
    }
}
