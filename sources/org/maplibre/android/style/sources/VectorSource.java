package org.maplibre.android.style.sources;

import android.net.Uri;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.geojson.Feature;

/* compiled from: VectorSource.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0017\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\nB\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0004\u0010\rB\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\u000eB\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0004\u0010\u0011J+\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u000e\b\u0001\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0002\u0010\u0019J\u001d\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00072\b\u0010 \u001a\u0004\u0018\u00010!H\u0085 J\t\u0010\"\u001a\u00020\u001eH\u0085 J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007H\u0085 J2\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00162\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\u00162\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u0016H\u0083 ¢\u0006\u0002\u0010%R\u0013\u0010\b\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001b¨\u0006&"}, d2 = {"Lorg/maplibre/android/style/sources/VectorSource;", "Lorg/maplibre/android/style/sources/Source;", "nativePtr", "", "<init>", "(J)V", "id", "", "url", "Ljava/net/URL;", "(Ljava/lang/String;Ljava/net/URL;)V", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Landroid/net/Uri;", "(Ljava/lang/String;Landroid/net/Uri;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "tileSet", "Lorg/maplibre/android/style/sources/TileSet;", "(Ljava/lang/String;Lorg/maplibre/android/style/sources/TileSet;)V", "querySourceFeatures", "", "Lorg/maplibre/geojson/Feature;", "sourceLayerIds", "", "filter", "Lorg/maplibre/android/style/expressions/Expression;", "([Ljava/lang/String;Lorg/maplibre/android/style/expressions/Expression;)Ljava/util/List;", "getUrl", "()Ljava/lang/String;", "getUri", "initialize", "", "layerId", "payload", "", "finalize", "nativeGetUrl", "sourceLayerId", "([Ljava/lang/String;[Ljava/lang/Object;)[Lorg/maplibre/geojson/Feature;", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VectorSource extends Source {
    private final native Feature[] querySourceFeatures(String[] sourceLayerId, Object[] filter);

    protected final native void finalize() throws Throwable;

    protected final native void initialize(String layerId, Object payload);

    protected final native String nativeGetUrl();

    public VectorSource(long j) {
        super(j);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "use {@link #VectorSource(String, Uri)} instead")
    public VectorSource(String str, URL url) {
        this(str, url.toExternalForm());
        Intrinsics.checkNotNullParameter(url, "url");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public VectorSource(String str, Uri uri) {
        this(str, uri.toString());
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    public VectorSource(String str, String str2) {
        initialize(str, str2);
    }

    public VectorSource(String str, TileSet tileSet) {
        Intrinsics.checkNotNullParameter(tileSet, "tileSet");
        initialize(str, tileSet.toValueObject());
    }

    public final List<Feature> querySourceFeatures(String[] sourceLayerIds, Expression filter) {
        Intrinsics.checkNotNullParameter(sourceLayerIds, "sourceLayerIds");
        checkThread();
        Feature[] featureArrQuerySourceFeatures = querySourceFeatures(sourceLayerIds, filter != null ? filter.toArray() : null);
        return CollectionsKt.listOf(Arrays.copyOf(featureArrQuerySourceFeatures, featureArrQuerySourceFeatures.length));
    }

    @Deprecated(message = "use {@link #getUri()} instead")
    public final String getUrl() {
        checkThread();
        return nativeGetUrl();
    }

    public final String getUri() {
        checkThread();
        return nativeGetUrl();
    }
}
