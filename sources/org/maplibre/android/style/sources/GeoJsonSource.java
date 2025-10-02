package org.maplibre.android.style.sources;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.common.util.UriUtil;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.Geometry;

/* compiled from: GeoJsonSource.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0013\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\bB\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u000bB\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\rB'\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u000eB\u001b\b\u0017\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0004\u0010\u0011B%\b\u0017\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u0012B\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0004\u0010\u0015B%\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u0016B\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u0004\u0010\u0019B'\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u001aB\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u0004\u0010\u001dB'\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\u001eB\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\u0004\u0010!B'\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0004\u0010\"J\u0010\u0010#\u001a\u00020$2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010#\u001a\u00020$2\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u0010\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0018J\u000e\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020\u0007J\u0010\u0010'\u001a\u00020$2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u000e\u0010(\u001a\u00020$2\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010(\u001a\u00020$2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007J\u0012\u0010)\u001a\u00020$2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007H\u0002J\n\u0010*\u001a\u0004\u0018\u00010\u0007H\u0002J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001c002\b\u00101\u001a\u0004\u0018\u000102J\u000e\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u001cJ\u001e\u00105\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u0003J\u000e\u00108\u001a\u0002092\u0006\u00104\u001a\u00020\u001cJ\u001d\u0010:\u001a\u00020$2\b\u0010;\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010<H\u0085 J\u0013\u0010=\u001a\u00020$2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007H\u0085 J\u000b\u0010>\u001a\u0004\u0018\u00010\u0007H\u0085 J\u0011\u0010?\u001a\u00020$2\u0006\u0010\f\u001a\u00020\u0007H\u0083 J\u0013\u0010@\u001a\u00020$2\b\u0010\f\u001a\u0004\u0018\u00010\u0018H\u0083 J\u0013\u0010A\u001a\u00020$2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0083 J\u0013\u0010B\u001a\u00020$2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0083 J$\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001c0C2\u000e\u00101\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010CH\u0083 ¢\u0006\u0002\u0010DJ \u0010E\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010C2\u0006\u0010\u001b\u001a\u00020\u001cH\u0083 ¢\u0006\u0002\u0010FJ0\u0010G\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010C2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u0003H\u0083 ¢\u0006\u0002\u0010HJ\u0011\u0010I\u001a\u0002092\u0006\u0010\u001b\u001a\u00020\u001cH\u0083 J\t\u0010J\u001a\u00020$H\u0085 R(\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010+\u001a\u0004\u0018\u00010\u00078G@GX\u0086\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b'\u0010\bR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b.\u0010-¨\u0006K"}, d2 = {"Lorg/maplibre/android/style/sources/GeoJsonSource;", "Lorg/maplibre/android/style/sources/Source;", "nativePtr", "", "<init>", "(J)V", "id", "", "(Ljava/lang/String;)V", "options", "Lorg/maplibre/android/style/sources/GeoJsonOptions;", "(Ljava/lang/String;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", "geoJson", "(Ljava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", "url", "Ljava/net/URL;", "(Ljava/lang/String;Ljava/net/URL;)V", "(Ljava/lang/String;Ljava/net/URL;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Ljava/net/URI;", "(Ljava/lang/String;Ljava/net/URI;)V", "(Ljava/lang/String;Ljava/net/URI;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", "features", "Lorg/maplibre/geojson/FeatureCollection;", "(Ljava/lang/String;Lorg/maplibre/geojson/FeatureCollection;)V", "(Ljava/lang/String;Lorg/maplibre/geojson/FeatureCollection;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", "feature", "Lorg/maplibre/geojson/Feature;", "(Ljava/lang/String;Lorg/maplibre/geojson/Feature;)V", "(Ljava/lang/String;Lorg/maplibre/geojson/Feature;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", "geometry", "Lorg/maplibre/geojson/Geometry;", "(Ljava/lang/String;Lorg/maplibre/geojson/Geometry;)V", "(Ljava/lang/String;Lorg/maplibre/geojson/Geometry;Lorg/maplibre/android/style/sources/GeoJsonOptions;)V", "setGeoJson", "", "featureCollection", "json", "setUrl", "setUri", "setUrlInternal", "getUrlInternal", "value", "getUrl", "()Ljava/lang/String;", "getUri", "querySourceFeatures", "", "filter", "Lorg/maplibre/android/style/expressions/Expression;", "getClusterChildren", "cluster", "getClusterLeaves", "limit", TypedValues.CycleType.S_WAVE_OFFSET, "getClusterExpansionZoom", "", "initialize", "layerId", "", "nativeSetUrl", "nativeGetUrl", "nativeSetGeoJsonString", "nativeSetFeatureCollection", "nativeSetFeature", "nativeSetGeometry", "", "([Ljava/lang/Object;)[Lorg/maplibre/geojson/Feature;", "nativeGetClusterChildren", "(Lorg/maplibre/geojson/Feature;)[Lorg/maplibre/geojson/Feature;", "nativeGetClusterLeaves", "(Lorg/maplibre/geojson/Feature;JJ)[Lorg/maplibre/geojson/Feature;", "nativeGetClusterExpansionZoom", "finalize", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GeoJsonSource extends Source {
    private final native Feature[] nativeGetClusterChildren(Feature feature);

    private final native int nativeGetClusterExpansionZoom(Feature feature);

    private final native Feature[] nativeGetClusterLeaves(Feature feature, long limit, long offset);

    private final native void nativeSetFeature(Feature feature);

    private final native void nativeSetFeatureCollection(FeatureCollection geoJson);

    private final native void nativeSetGeoJsonString(String geoJson);

    private final native void nativeSetGeometry(Geometry geometry);

    private final native Feature[] querySourceFeatures(Object[] filter);

    protected final native void finalize() throws Throwable;

    protected final native void initialize(String layerId, Object options);

    protected final native String nativeGetUrl();

    protected final native void nativeSetUrl(String url);

    public GeoJsonSource(long j) {
        super(j);
    }

    public GeoJsonSource(String str) {
        initialize(str, null);
        setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public GeoJsonSource(String str, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public GeoJsonSource(String str, String str2) {
        boolean z = false;
        if (str2 != null && !StringsKt.startsWith$default(str2, "http", false, 2, (Object) null)) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Expected a raw json body".toString());
        }
        initialize(str, null);
        setGeoJson(str2);
    }

    public GeoJsonSource(String str, String str2, GeoJsonOptions geoJsonOptions) {
        boolean z = false;
        if (str2 != null && !StringsKt.startsWith$default(str2, "http", false, 2, (Object) null) && !StringsKt.startsWith$default(str2, UriUtil.LOCAL_ASSET_SCHEME, false, 2, (Object) null) && !StringsKt.startsWith$default(str2, "file", false, 2, (Object) null)) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Expected a raw json body".toString());
        }
        initialize(str, geoJsonOptions);
        setGeoJson(str2);
    }

    @Deprecated(message = "use {@link #GeoJsonSource(String, URI)} instead")
    public GeoJsonSource(String str, URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        initialize(str, null);
        nativeSetUrl(url.toExternalForm());
    }

    @Deprecated(message = "use {@link #GeoJsonSource(String, URI, GeoJsonOptions)} instead")
    public GeoJsonSource(String str, URL url, GeoJsonOptions geoJsonOptions) {
        Intrinsics.checkNotNullParameter(url, "url");
        initialize(str, geoJsonOptions);
        nativeSetUrl(url.toExternalForm());
    }

    public GeoJsonSource(String str, URI uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        initialize(str, null);
        nativeSetUrl(uri.toString());
    }

    public GeoJsonSource(String str, URI uri, GeoJsonOptions geoJsonOptions) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        initialize(str, geoJsonOptions);
        nativeSetUrl(uri.toString());
    }

    public GeoJsonSource(String str, FeatureCollection featureCollection) {
        initialize(str, null);
        setGeoJson(featureCollection);
    }

    public GeoJsonSource(String str, FeatureCollection featureCollection, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(featureCollection);
    }

    public GeoJsonSource(String str, Feature feature) {
        initialize(str, null);
        setGeoJson(feature);
    }

    public GeoJsonSource(String str, Feature feature, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(feature);
    }

    public GeoJsonSource(String str, Geometry geometry) {
        initialize(str, null);
        setGeoJson(geometry);
    }

    public GeoJsonSource(String str, Geometry geometry, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(geometry);
    }

    public final void setGeoJson(Feature feature) {
        if (this.detached) {
            return;
        }
        checkThread();
        nativeSetFeature(feature);
    }

    public final void setGeoJson(Geometry geometry) {
        if (this.detached) {
            return;
        }
        checkThread();
        nativeSetGeometry(geometry);
    }

    public final void setGeoJson(FeatureCollection featureCollection) {
        if (this.detached) {
            return;
        }
        checkThread();
        if (featureCollection != null && featureCollection.features() != null) {
            nativeSetFeatureCollection(FeatureCollection.fromFeatures(new ArrayList(featureCollection.features())));
        } else {
            nativeSetFeatureCollection(featureCollection);
        }
    }

    public final void setGeoJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        if (this.detached) {
            return;
        }
        checkThread();
        nativeSetGeoJsonString(json);
    }

    @Deprecated(message = "use {@link #setUri(URI)} instead")
    public final void setUrl(URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        checkThread();
        setUrl(url.toExternalForm());
    }

    public final void setUri(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        setUri(uri.toString());
    }

    public final void setUri(String uri) {
        checkThread();
        nativeSetUrl(uri);
    }

    private final void setUrlInternal(String url) {
        checkThread();
        nativeSetUrl(url);
    }

    private final String getUrlInternal() {
        checkThread();
        return nativeGetUrl();
    }

    @Deprecated(message = "use {@link #getUri()} instead")
    public final String getUrl() {
        return getUrlInternal();
    }

    @Deprecated(message = "use {@link #setUri(String)} instead")
    public final void setUrl(String str) {
        setUrlInternal(str);
    }

    public final String getUri() {
        checkThread();
        return nativeGetUrl();
    }

    public final List<Feature> querySourceFeatures(Expression filter) {
        checkThread();
        Feature[] featureArrQuerySourceFeatures = querySourceFeatures(filter != null ? filter.toArray() : null);
        if (featureArrQuerySourceFeatures == null) {
            return new ArrayList();
        }
        List<Feature> listAsList = Arrays.asList(Arrays.copyOf(featureArrQuerySourceFeatures, featureArrQuerySourceFeatures.length));
        Intrinsics.checkNotNullExpressionValue(listAsList, "asList(...)");
        return listAsList;
    }

    public final FeatureCollection getClusterChildren(Feature cluster) {
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        checkThread();
        Feature[] featureArrNativeGetClusterChildren = nativeGetClusterChildren(cluster);
        Intrinsics.checkNotNull(featureArrNativeGetClusterChildren);
        FeatureCollection featureCollectionFromFeatures = FeatureCollection.fromFeatures(featureArrNativeGetClusterChildren);
        Intrinsics.checkNotNullExpressionValue(featureCollectionFromFeatures, "fromFeatures(...)");
        return featureCollectionFromFeatures;
    }

    public final FeatureCollection getClusterLeaves(Feature cluster, long limit, long offset) {
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        checkThread();
        Feature[] featureArrNativeGetClusterLeaves = nativeGetClusterLeaves(cluster, limit, offset);
        Intrinsics.checkNotNull(featureArrNativeGetClusterLeaves);
        FeatureCollection featureCollectionFromFeatures = FeatureCollection.fromFeatures(featureArrNativeGetClusterLeaves);
        Intrinsics.checkNotNullExpressionValue(featureCollectionFromFeatures, "fromFeatures(...)");
        return featureCollectionFromFeatures;
    }

    public final int getClusterExpansionZoom(Feature cluster) {
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        checkThread();
        return nativeGetClusterExpansionZoom(cluster);
    }
}
