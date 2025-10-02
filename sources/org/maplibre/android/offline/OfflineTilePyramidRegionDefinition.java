package org.maplibre.android.offline;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;

/* compiled from: OfflineTilePyramidRegionDefinition.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0001'B3\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB;\b\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000b\u0010\u000fB\u0011\b\u0016\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u000b\u0010\u0012J\b\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\"H\u0016R$\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00078\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R \u0010\b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00078\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R \u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n8\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR \u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e8\u0016@RX\u0097\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0015¨\u0006("}, d2 = {"Lorg/maplibre/android/offline/OfflineTilePyramidRegionDefinition;", "Lorg/maplibre/android/offline/OfflineRegionDefinition;", "styleURL", "", "bounds", "Lorg/maplibre/android/geometry/LatLngBounds;", "minZoom", "", "maxZoom", "pixelRatio", "", "<init>", "(Ljava/lang/String;Lorg/maplibre/android/geometry/LatLngBounds;DDF)V", "includeIdeographs", "", "(Ljava/lang/String;Lorg/maplibre/android/geometry/LatLngBounds;DDFZ)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "value", "getStyleURL", "()Ljava/lang/String;", "getBounds", "()Lorg/maplibre/android/geometry/LatLngBounds;", "getMinZoom", "()D", "getMaxZoom", "getPixelRatio", "()F", "getIncludeIdeographs", "()Z", "type", "getType", "describeContents", "", "writeToParcel", "", "dest", "flags", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OfflineTilePyramidRegionDefinition implements OfflineRegionDefinition {
    private final LatLngBounds bounds;
    private boolean includeIdeographs;
    private double maxZoom;
    private double minZoom;
    private float pixelRatio;
    private String styleURL;
    public static final Parcelable.Creator<?> CREATOR = new Parcelable.Creator<Object>() { // from class: org.maplibre.android.offline.OfflineTilePyramidRegionDefinition$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel in) {
            Intrinsics.checkNotNullParameter(in, "in");
            return new OfflineTilePyramidRegionDefinition(in);
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int size) {
            return new OfflineTilePyramidRegionDefinition[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public String getType() {
        return "tileregion";
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public String getStyleURL() {
        return this.styleURL;
    }

    @Override // org.maplibre.android.offline.OfflineRegionDefinition
    public LatLngBounds getBounds() {
        return this.bounds;
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

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OfflineTilePyramidRegionDefinition(String str, LatLngBounds bounds, double d, double d2, float f) {
        this(str, bounds, d, d2, f, false);
        Intrinsics.checkNotNullParameter(bounds, "bounds");
    }

    public OfflineTilePyramidRegionDefinition(String str, LatLngBounds bounds, double d, double d2, float f, boolean z) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.styleURL = str;
        this.bounds = bounds;
        this.minZoom = d;
        this.maxZoom = d2;
        this.pixelRatio = f;
        this.includeIdeographs = z;
    }

    public OfflineTilePyramidRegionDefinition(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.styleURL = parcel.readString();
        this.bounds = new LatLngBounds.Builder().include(new LatLng(parcel.readDouble(), parcel.readDouble())).include(new LatLng(parcel.readDouble(), parcel.readDouble())).build();
        this.minZoom = parcel.readDouble();
        this.maxZoom = parcel.readDouble();
        this.pixelRatio = parcel.readFloat();
        this.includeIdeographs = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(getStyleURL());
        if (getBounds() != null) {
            dest.writeDouble(getBounds().latitudeNorth);
            dest.writeDouble(getBounds().longitudeEast);
            dest.writeDouble(getBounds().latitudeSouth);
            dest.writeDouble(getBounds().longitudeWest);
        }
        dest.writeDouble(getMinZoom());
        dest.writeDouble(getMaxZoom());
        dest.writeFloat(getPixelRatio());
        dest.writeByte(getIncludeIdeographs() ? (byte) 1 : (byte) 0);
    }
}
