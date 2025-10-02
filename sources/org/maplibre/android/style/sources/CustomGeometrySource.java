package org.maplibre.android.style.sources;

import io.sentry.SentryBaseEvent;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* compiled from: CustomGeometrySource.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 ;2\u00020\u0001:\u00039:;B'\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tB\u001d\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\nJ\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u001e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bJ&\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\u0010$\u001a\u0004\u0018\u00010%J\u001d\u0010&\u001a\u00020\u00162\b\u0010'\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010(H\u0083 J$\u0010!\u001a\b\u0012\u0004\u0012\u00020#0)2\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010)H\u0083 ¢\u0006\u0002\u0010*J)\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0083 J!\u0010-\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0083 J\u0011\u0010.\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0083 J\t\u0010/\u001a\u00020\u0016H\u0085 J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u00100\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 H\u0002J \u00101\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0003J\u0010\u00102\u001a\u00020\u00162\u0006\u00103\u001a\u00020\u0012H\u0002J \u00104\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0003J\b\u00105\u001a\u00020\u0016H\u0003J\b\u00106\u001a\u00020\u0016H\u0003J \u00107\u001a\u0002082\u0006\u0010,\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001bH\u0003R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lorg/maplibre/android/style/sources/CustomGeometrySource;", "Lorg/maplibre/android/style/sources/Source;", "id", "", "options", "Lorg/maplibre/android/style/sources/CustomGeometrySourceOptions;", "provider", "Lorg/maplibre/android/style/sources/GeometryTileProvider;", "<init>", "(Ljava/lang/String;Lorg/maplibre/android/style/sources/CustomGeometrySourceOptions;Lorg/maplibre/android/style/sources/GeometryTileProvider;)V", "(Ljava/lang/String;Lorg/maplibre/android/style/sources/GeometryTileProvider;)V", "executorLock", "Ljava/util/concurrent/locks/Lock;", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "awaitingTasksMap", "", "Lorg/maplibre/android/style/sources/CustomGeometrySource$TileID;", "Lorg/maplibre/android/style/sources/CustomGeometrySource$GeometryTileRequest;", "inProgressTasksMap", "Ljava/util/concurrent/atomic/AtomicBoolean;", "invalidateRegion", "", "bounds", "Lorg/maplibre/android/geometry/LatLngBounds;", "invalidateTile", "zoomLevel", "", "x", "y", "setTileData", "data", "Lorg/maplibre/geojson/FeatureCollection;", "querySourceFeatures", "", "Lorg/maplibre/geojson/Feature;", "filter", "Lorg/maplibre/android/style/expressions/Expression;", "initialize", "sourceId", "", "", "([Ljava/lang/Object;)[Lorg/maplibre/geojson/Feature;", "nativeSetTileData", "z", "nativeInvalidateTile", "nativeInvalidateBounds", "finalize", "tileId", "fetchTile", "executeRequest", SentryBaseEvent.JsonKeys.REQUEST, "cancelTile", "startThreads", "releaseThreads", "isCancelled", "", "TileID", "GeometryTileRequest", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CustomGeometrySource extends Source {
    public static final int THREAD_POOL_LIMIT = 4;
    public static final String THREAD_PREFIX = "CustomGeom";
    private final Map<TileID, GeometryTileRequest> awaitingTasksMap;
    private ThreadPoolExecutor executor;
    private final Lock executorLock;
    private final Map<TileID, AtomicBoolean> inProgressTasksMap;
    private final GeometryTileProvider provider;
    private static final AtomicInteger poolCount = new AtomicInteger();

    private final native void initialize(String sourceId, Object options);

    private final native void nativeInvalidateBounds(LatLngBounds bounds);

    private final native void nativeInvalidateTile(int z, int x, int y);

    private final native void nativeSetTileData(int z, int x, int y, FeatureCollection data);

    private final native Feature[] querySourceFeatures(Object[] filter);

    protected final native void finalize() throws Throwable;

    public CustomGeometrySource(String str, CustomGeometrySourceOptions customGeometrySourceOptions, GeometryTileProvider geometryTileProvider) {
        this.provider = geometryTileProvider;
        this.executorLock = new ReentrantLock();
        this.awaitingTasksMap = new HashMap();
        this.inProgressTasksMap = new HashMap();
        initialize(str, customGeometrySourceOptions);
    }

    public CustomGeometrySource(String str, GeometryTileProvider geometryTileProvider) {
        this(str, new CustomGeometrySourceOptions(), geometryTileProvider);
    }

    public final void invalidateRegion(LatLngBounds bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        nativeInvalidateBounds(bounds);
    }

    public final void invalidateTile(int zoomLevel, int x, int y) {
        nativeInvalidateTile(zoomLevel, x, y);
    }

    public final void setTileData(int zoomLevel, int x, int y, FeatureCollection data) {
        Intrinsics.checkNotNullParameter(data, "data");
        nativeSetTileData(zoomLevel, x, y, data);
    }

    public final List<Feature> querySourceFeatures(Expression filter) {
        checkThread();
        Feature[] featureArrQuerySourceFeatures = querySourceFeatures(filter != null ? filter.toArray() : null);
        return CollectionsKt.listOf(Arrays.copyOf(featureArrQuerySourceFeatures, featureArrQuerySourceFeatures.length));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTileData(TileID tileId, FeatureCollection data) {
        nativeSetTileData(tileId.getZ(), tileId.getX(), tileId.getY(), data);
    }

    private final void fetchTile(int z, int x, int y) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        TileID tileID = new TileID(z, x, y);
        GeometryTileRequest geometryTileRequest = new GeometryTileRequest(tileID, this.provider, this.awaitingTasksMap, this.inProgressTasksMap, this, atomicBoolean);
        synchronized (this.awaitingTasksMap) {
            synchronized (this.inProgressTasksMap) {
                ThreadPoolExecutor threadPoolExecutor = this.executor;
                Intrinsics.checkNotNull(threadPoolExecutor);
                if (threadPoolExecutor.getQueue().contains(geometryTileRequest)) {
                    ThreadPoolExecutor threadPoolExecutor2 = this.executor;
                    Intrinsics.checkNotNull(threadPoolExecutor2);
                    threadPoolExecutor2.remove(geometryTileRequest);
                    executeRequest(geometryTileRequest);
                    Unit unit = Unit.INSTANCE;
                } else if (this.inProgressTasksMap.containsKey(tileID)) {
                    this.awaitingTasksMap.put(tileID, geometryTileRequest);
                } else {
                    executeRequest(geometryTileRequest);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
    }

    private final void executeRequest(GeometryTileRequest request) {
        this.executorLock.lock();
        try {
            ThreadPoolExecutor threadPoolExecutor = this.executor;
            if (threadPoolExecutor != null) {
                Intrinsics.checkNotNull(threadPoolExecutor);
                if (!threadPoolExecutor.isShutdown()) {
                    ThreadPoolExecutor threadPoolExecutor2 = this.executor;
                    Intrinsics.checkNotNull(threadPoolExecutor2);
                    threadPoolExecutor2.execute(request);
                }
            }
        } finally {
            this.executorLock.unlock();
        }
    }

    private final void cancelTile(int z, int x, int y) {
        TileID tileID = new TileID(z, x, y);
        synchronized (this.awaitingTasksMap) {
            synchronized (this.inProgressTasksMap) {
                AtomicBoolean atomicBoolean = this.inProgressTasksMap.get(tileID);
                if (atomicBoolean == null || !atomicBoolean.compareAndSet(false, true)) {
                    GeometryTileRequest geometryTileRequest = new GeometryTileRequest(tileID, null, null, null, null, null);
                    ThreadPoolExecutor threadPoolExecutor = this.executor;
                    Intrinsics.checkNotNull(threadPoolExecutor);
                    if (!threadPoolExecutor.getQueue().remove(geometryTileRequest)) {
                        this.awaitingTasksMap.remove(tileID);
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    private final void startThreads() {
        this.executorLock.lock();
        try {
            ThreadPoolExecutor threadPoolExecutor = this.executor;
            if (threadPoolExecutor != null) {
                Intrinsics.checkNotNull(threadPoolExecutor);
                if (!threadPoolExecutor.isShutdown()) {
                    ThreadPoolExecutor threadPoolExecutor2 = this.executor;
                    Intrinsics.checkNotNull(threadPoolExecutor2);
                    threadPoolExecutor2.shutdownNow();
                }
            }
            ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(4, 4, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: org.maplibre.android.style.sources.CustomGeometrySource.startThreads.1
                private final AtomicInteger threadCount = new AtomicInteger();
                private final int poolId = CustomGeometrySource.poolCount.getAndIncrement();

                public final AtomicInteger getThreadCount() {
                    return this.threadCount;
                }

                public final int getPoolId() {
                    return this.poolId;
                }

                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Intrinsics.checkNotNullParameter(runnable, "runnable");
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format(Locale.US, "%s-%d-%d", Arrays.copyOf(new Object[]{CustomGeometrySource.THREAD_PREFIX, Integer.valueOf(this.poolId), Integer.valueOf(this.threadCount.getAndIncrement())}, 3));
                    Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                    return new Thread(runnable, str);
                }
            });
            this.executorLock.unlock();
            this.executor = threadPoolExecutor3;
        } catch (Throwable th) {
            this.executorLock.unlock();
            throw th;
        }
    }

    private final void releaseThreads() {
        this.executorLock.lock();
        try {
            ThreadPoolExecutor threadPoolExecutor = this.executor;
            Intrinsics.checkNotNull(threadPoolExecutor);
            threadPoolExecutor.shutdownNow();
        } finally {
            this.executorLock.unlock();
        }
    }

    private final boolean isCancelled(int z, int x, int y) {
        AtomicBoolean atomicBoolean = this.inProgressTasksMap.get(new TileID(z, x, y));
        Intrinsics.checkNotNull(atomicBoolean);
        return atomicBoolean.get();
    }

    /* compiled from: CustomGeometrySource.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0003H\u0016J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0096\u0002R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u0014"}, d2 = {"Lorg/maplibre/android/style/sources/CustomGeometrySource$TileID;", "", "z", "", "x", "y", "<init>", "(III)V", "getZ", "()I", "setZ", "(I)V", "getX", "setX", "getY", "setY", "hashCode", "equals", "", "other", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class TileID {
        private int x;
        private int y;
        private int z;

        public TileID(int i, int i2, int i3) {
            this.z = i;
            this.x = i2;
            this.y = i3;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getZ() {
            return this.z;
        }

        public final void setX(int i) {
            this.x = i;
        }

        public final void setY(int i) {
            this.y = i;
        }

        public final void setZ(int i) {
            this.z = i;
        }

        public int hashCode() {
            return Arrays.hashCode(new int[]{this.z, this.x, this.y});
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (other == null || !Intrinsics.areEqual(getClass(), other.getClass()) || !(other instanceof TileID)) {
                return false;
            }
            TileID tileID = (TileID) other;
            return this.z == tileID.z && this.x == tileID.x && this.y == tileID.y;
        }
    }

    /* compiled from: CustomGeometrySource.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0000\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0007\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/maplibre/android/style/sources/CustomGeometrySource$GeometryTileRequest;", "Ljava/lang/Runnable;", "id", "Lorg/maplibre/android/style/sources/CustomGeometrySource$TileID;", "provider", "Lorg/maplibre/android/style/sources/GeometryTileProvider;", "awaiting", "", "inProgress", "Ljava/util/concurrent/atomic/AtomicBoolean;", "_source", "Lorg/maplibre/android/style/sources/CustomGeometrySource;", "_cancelled", "<init>", "(Lorg/maplibre/android/style/sources/CustomGeometrySource$TileID;Lorg/maplibre/android/style/sources/GeometryTileProvider;Ljava/util/Map;Ljava/util/Map;Lorg/maplibre/android/style/sources/CustomGeometrySource;Ljava/util/concurrent/atomic/AtomicBoolean;)V", "sourceRef", "Ljava/lang/ref/WeakReference;", "cancelled", "run", "", "isCancelled", "", "equals", "other", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class GeometryTileRequest implements Runnable {
        private final Map<TileID, GeometryTileRequest> awaiting;
        private final AtomicBoolean cancelled;
        private final TileID id;
        private final Map<TileID, AtomicBoolean> inProgress;
        private final GeometryTileProvider provider;
        private final WeakReference<CustomGeometrySource> sourceRef;

        public GeometryTileRequest(TileID id, GeometryTileProvider geometryTileProvider, Map<TileID, GeometryTileRequest> map, Map<TileID, AtomicBoolean> map2, CustomGeometrySource customGeometrySource, AtomicBoolean atomicBoolean) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
            this.provider = geometryTileProvider;
            this.awaiting = map;
            this.inProgress = map2;
            this.sourceRef = new WeakReference<>(customGeometrySource);
            this.cancelled = atomicBoolean;
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<TileID, GeometryTileRequest> map = this.awaiting;
            Intrinsics.checkNotNull(map);
            synchronized (map) {
                Map<TileID, AtomicBoolean> map2 = this.inProgress;
                Intrinsics.checkNotNull(map2);
                synchronized (map2) {
                    if (this.inProgress.containsKey(this.id)) {
                        if (!this.awaiting.containsKey(this.id)) {
                            this.awaiting.put(this.id, this);
                        }
                        return;
                    }
                    this.inProgress.put(this.id, this.cancelled);
                    if (!isCancelled()) {
                        GeometryTileProvider geometryTileProvider = this.provider;
                        Intrinsics.checkNotNull(geometryTileProvider);
                        FeatureCollection featuresForBounds = geometryTileProvider.getFeaturesForBounds(LatLngBounds.INSTANCE.from(this.id.getZ(), this.id.getX(), this.id.getY()), this.id.getZ());
                        CustomGeometrySource customGeometrySource = this.sourceRef.get();
                        if (!isCancelled() && customGeometrySource != null) {
                            customGeometrySource.setTileData(this.id, featuresForBounds);
                        }
                    }
                    synchronized (this.awaiting) {
                        Map<TileID, AtomicBoolean> map3 = this.inProgress;
                        Intrinsics.checkNotNull(map3);
                        synchronized (map3) {
                            this.inProgress.remove(this.id);
                            if (this.awaiting.containsKey(this.id)) {
                                GeometryTileRequest geometryTileRequest = this.awaiting.get(this.id);
                                CustomGeometrySource customGeometrySource2 = this.sourceRef.get();
                                if (customGeometrySource2 != null && geometryTileRequest != null) {
                                    ThreadPoolExecutor threadPoolExecutor = customGeometrySource2.executor;
                                    Intrinsics.checkNotNull(threadPoolExecutor);
                                    threadPoolExecutor.execute(geometryTileRequest);
                                }
                                this.awaiting.remove(this.id);
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                        Unit unit2 = Unit.INSTANCE;
                    }
                }
            }
        }

        private final boolean isCancelled() {
            AtomicBoolean atomicBoolean = this.cancelled;
            Intrinsics.checkNotNull(atomicBoolean);
            return atomicBoolean.get();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
                return false;
            }
            return Intrinsics.areEqual(this.id, ((GeometryTileRequest) other).id);
        }
    }
}
