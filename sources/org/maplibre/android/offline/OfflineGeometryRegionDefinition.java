package org.maplibre.android.offline;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.Geometry;
import org.maplibre.turf.TurfMeasurement;

/* compiled from: OfflineGeometryRegionDefinition.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 +2\u00020\u0001:\u0001+B5\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB=\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000b\u0010\u000fB\u0011\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u000b\u0010\u0012J\b\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020&H\u0016R$\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006@BX\u0087\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00078\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R \u0010\b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00078\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R \u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n8\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR \u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e8\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u0004\u0018\u00010 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0015¨\u0006,"}, d2 = {"Lorg/maplibre/android/offline/OfflineGeometryRegionDefinition;", "Lorg/maplibre/android/offline/OfflineRegionDefinition;", "styleURL", "", "geometry", "Lorg/maplibre/geojson/Geometry;", "minZoom", "", "maxZoom", "pixelRatio", "", "<init>", "(Ljava/lang/String;Lorg/maplibre/geojson/Geometry;DDF)V", "includeIdeographs", "", "(Ljava/lang/String;Lorg/maplibre/geojson/Geometry;DDFZ)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "value", "getStyleURL", "()Ljava/lang/String;", "getGeometry", "()Lorg/maplibre/geojson/Geometry;", "getMinZoom", "()D", "getMaxZoom", "getPixelRatio", "()F", "getIncludeIdeographs", "()Z", "bounds", "Lorg/maplibre/android/geometry/LatLngBounds;", "getBounds", "()Lorg/maplibre/android/geometry/LatLngBounds;", "type", "getType", "describeContents", "", "writeToParcel", "", "dest", "flags", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OfflineGeometryRegionDefinition implements OfflineRegionDefinition {
    private Geometry geometry;
    private boolean includeIdeographs;
    private double maxZoom;
    private double minZoom;
    private float pixelRatio;
    private String styleURL;
    public static final Parcelable.Creator<?> CREATOR = new Parcelable.Creator<Object>() { // from class: org.maplibre.android.offline.OfflineGeometryRegionDefinition$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel in) {
            Intrinsics.checkNotNullParameter(in, "in");
            return new OfflineGeometryRegionDefinition(in);
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int size) {
            return new OfflineGeometryRegionDefinition[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public String getType() {
        return "shaperegion";
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public String getStyleURL() {
        return this.styleURL;
    }

    public final Geometry getGeometry() {
        return this.geometry;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public double getMinZoom() {
        return this.minZoom;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public double getMaxZoom() {
        return this.maxZoom;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public float getPixelRatio() {
        return this.pixelRatio;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public boolean getIncludeIdeographs() {
        return this.includeIdeographs;
    }

    public OfflineGeometryRegionDefinition(String str, Geometry geometry, double d, double d2, float f) {
        this(str, geometry, d, d2, f, false);
    }

    public OfflineGeometryRegionDefinition(String str, Geometry geometry, double d, double d2, float f, boolean z) {
        this.styleURL = str;
        this.geometry = geometry;
        this.minZoom = d;
        this.maxZoom = d2;
        this.pixelRatio = f;
        this.includeIdeographs = z;
    }

    public OfflineGeometryRegionDefinition(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.styleURL = parcel.readString();
        String string = parcel.readString();
        Intrinsics.checkNotNull(string);
        this.geometry = Feature.fromJson(string).geometry();
        this.minZoom = parcel.readDouble();
        this.maxZoom = parcel.readDouble();
        this.pixelRatio = parcel.readFloat();
        this.includeIdeographs = parcel.readByte() != 0;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public LatLngBounds getBounds() {
        Geometry geometry = this.geometry;
        if (geometry == null) {
            return null;
        }
        double[] dArrBbox = TurfMeasurement.bbox(geometry);
        return LatLngBounds.INSTANCE.from(dArrBbox[3], dArrBbox[2], dArrBbox[1], dArrBbox[0]);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(getStyleURL());
        dest.writeString(Feature.fromGeometry(this.geometry).toJson());
        dest.writeDouble(getMinZoom());
        dest.writeDouble(getMaxZoom());
        dest.writeFloat(getPixelRatio());
        dest.writeByte(getIncludeIdeographs() ? (byte) 1 : (byte) 0);
    }
}
