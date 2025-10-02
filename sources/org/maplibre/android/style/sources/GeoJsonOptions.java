package org.maplibre.android.style.sources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.style.expressions.Expression;

/* compiled from: GeoJsonOptions.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002&\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001j\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0003`\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\tJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0010J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\tJ\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\tJ\u001e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d¨\u0006\u001f"}, d2 = {"Lorg/maplibre/android/style/sources/GeoJsonOptions;", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "<init>", "()V", "withMinZoom", "minZoom", "", "withMaxZoom", "maxZoom", "withBuffer", "buffer", "withLineMetrics", "lineMetrics", "", "withTolerance", "tolerance", "", "withCluster", "cluster", "withClusterMaxZoom", "clusterMaxZoom", "withClusterRadius", "clusterRadius", "withClusterProperty", "propertyName", "operatorExpr", "Lorg/maplibre/android/style/expressions/Expression;", "mapExpr", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class GeoJsonOptions extends HashMap<String, Object> {
    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj == null ? true : obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    public /* bridge */ boolean containsKey(String str) {
        return super.containsKey((Object) str);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<Map.Entry<String, Object>> entrySet() {
        return getEntries();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj == null ? true : obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    public /* bridge */ Object get(String str) {
        return super.get((Object) str);
    }

    public /* bridge */ Set<Map.Entry<String, Object>> getEntries() {
        return super.entrySet();
    }

    public /* bridge */ Set<String> getKeys() {
        return super.keySet();
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj == null ? true : obj instanceof String) ? obj2 : getOrDefault((String) obj, obj2);
    }

    public /* bridge */ Object getOrDefault(String str, Object obj) {
        return super.getOrDefault((Object) str, (String) obj);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ Collection<Object> getValues() {
        return super.values();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Set<String> keySet() {
        return getKeys();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object remove(Object obj) {
        if (obj == null ? true : obj instanceof String) {
            return remove((String) obj);
        }
        return null;
    }

    public /* bridge */ Object remove(String str) {
        return super.remove((Object) str);
    }

    @Override // java.util.HashMap, java.util.Map
    public final /* bridge */ boolean remove(Object obj, Object obj2) {
        if (obj == null ? true : obj instanceof String) {
            return remove((String) obj, obj2);
        }
        return false;
    }

    public /* bridge */ boolean remove(String str, Object obj) {
        return super.remove((Object) str, obj);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Collection<Object> values() {
        return getValues();
    }

    public final GeoJsonOptions withMinZoom(int minZoom) {
        put("minzoom", Integer.valueOf(minZoom));
        return this;
    }

    public final GeoJsonOptions withMaxZoom(int maxZoom) {
        put("maxzoom", Integer.valueOf(maxZoom));
        return this;
    }

    public final GeoJsonOptions withBuffer(int buffer) {
        put("buffer", Integer.valueOf(buffer));
        return this;
    }

    public final GeoJsonOptions withLineMetrics(boolean lineMetrics) {
        put("lineMetrics", Boolean.valueOf(lineMetrics));
        return this;
    }

    public final GeoJsonOptions withTolerance(float tolerance) {
        put("tolerance", Float.valueOf(tolerance));
        return this;
    }

    public final GeoJsonOptions withCluster(boolean cluster) {
        put("cluster", Boolean.valueOf(cluster));
        return this;
    }

    public final GeoJsonOptions withClusterMaxZoom(int clusterMaxZoom) {
        put("clusterMaxZoom", Integer.valueOf(clusterMaxZoom));
        return this;
    }

    public final GeoJsonOptions withClusterRadius(int clusterRadius) {
        put("clusterRadius", Integer.valueOf(clusterRadius));
        return this;
    }

    public final GeoJsonOptions withClusterProperty(String propertyName, Expression operatorExpr, Expression mapExpr) {
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        Intrinsics.checkNotNullParameter(operatorExpr, "operatorExpr");
        Intrinsics.checkNotNullParameter(mapExpr, "mapExpr");
        HashMap map = containsKey("clusterProperties") ? (HashMap) get("clusterProperties") : new HashMap();
        Object value = operatorExpr instanceof Expression.ExpressionLiteral ? ((Expression.ExpressionLiteral) operatorExpr).toValue() : operatorExpr.toArray();
        Object[] array = mapExpr.toArray();
        Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
        Intrinsics.checkNotNull(map);
        map.put(propertyName, new Object[]{value, array});
        put("clusterProperties", map);
        return this;
    }
}
