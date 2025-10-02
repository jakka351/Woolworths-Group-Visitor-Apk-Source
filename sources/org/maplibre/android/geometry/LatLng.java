package org.maplibre.android.geometry;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import io.sentry.protocol.MetricSummary;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.geojson.Point;
import org.maplibre.turf.TurfConstants;
import org.maplibre.turf.TurfMeasurement;

/* compiled from: LatLng.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 )2\u00020\u0001:\u0001)B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0019\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0002\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u000eB\u0011\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0002\u0010\u0011J\u0006\u0010\u001a\u001a\u00020\u0000J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020 H\u0016J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020 H\u0016J\u000e\u0010(\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0000R(\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0004\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R(\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00058\u0006@FX\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015¨\u0006*"}, d2 = {"Lorg/maplibre/android/geometry/LatLng;", "Landroid/os/Parcelable;", "<init>", "()V", "latitude", "", "longitude", "(DD)V", "altitude", "(DDD)V", "location", "Landroid/location/Location;", "(Landroid/location/Location;)V", "latLng", "(Lorg/maplibre/android/geometry/LatLng;)V", "in", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "getLatitude", "()D", "setLatitude", "(D)V", "getLongitude", "setLongitude", "getAltitude", "setAltitude", "wrap", "equals", "", "other", "", "hashCode", "", "toString", "", "describeContents", "writeToParcel", "", "out", "flags", "distanceTo", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LatLng implements Parcelable {
    private double altitude;
    private double latitude;
    private double longitude;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<LatLng> CREATOR = new Parcelable.Creator<LatLng>() { // from class: org.maplibre.android.geometry.LatLng$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public LatLng createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LatLng(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LatLng[] newArray(int size) {
            return new LatLng[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(double d) {
        if (!(!Double.isNaN(d))) {
            throw new IllegalArgumentException("latitude must not be NaN".toString());
        }
        if (!(Math.abs(d) <= 90.0d)) {
            throw new IllegalArgumentException("latitude must be between -90 and 90".toString());
        }
        this.latitude = d;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(double d) {
        if (!(!Double.isNaN(d))) {
            throw new IllegalArgumentException("longitude must not be NaN".toString());
        }
        if (!(!Double.isInfinite(d))) {
            throw new IllegalArgumentException("longitude must not be infinite".toString());
        }
        this.longitude = d;
    }

    public final double getAltitude() {
        return this.altitude;
    }

    public final void setAltitude(double d) {
        this.altitude = d;
    }

    public LatLng() {
        setLatitude(0.0d);
        setLongitude(0.0d);
    }

    public LatLng(double d, double d2) {
        setLatitude(d);
        setLongitude(d2);
    }

    public LatLng(double d, double d2, double d3) {
        setLatitude(d);
        setLongitude(d2);
        this.altitude = d3;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LatLng(Location location) {
        this(location.getLatitude(), location.getLongitude(), location.getAltitude());
        Intrinsics.checkNotNullParameter(location, "location");
    }

    public LatLng(LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        setLatitude(latLng.latitude);
        setLongitude(latLng.longitude);
        this.altitude = latLng.altitude;
    }

    public LatLng(Parcel in) {
        Intrinsics.checkNotNullParameter(in, "in");
        setLatitude(in.readDouble());
        setLongitude(in.readDouble());
        this.altitude = in.readDouble();
    }

    public final LatLng wrap() {
        return new LatLng(this.latitude, INSTANCE.wrap(this.longitude, -180.0d, 180.0d));
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        LatLng latLng = (LatLng) other;
        return Double.compare(latLng.altitude, this.altitude) == 0 && Double.compare(latLng.latitude, this.latitude) == 0 && Double.compare(latLng.longitude, this.longitude) == 0;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.latitude);
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        int i = (((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.altitude);
        return (i * 31) + ((int) ((jDoubleToLongBits3 >>> 32) ^ jDoubleToLongBits3));
    }

    public String toString() {
        return "LatLng [latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.latitude);
        out.writeDouble(this.longitude);
        out.writeDouble(this.altitude);
    }

    public final double distanceTo(LatLng other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return TurfMeasurement.distance(Point.fromLngLat(this.longitude, this.latitude), Point.fromLngLat(other.longitude, other.latitude), TurfConstants.UNIT_METRES);
    }

    /* compiled from: LatLng.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/maplibre/android/geometry/LatLng$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lorg/maplibre/android/geometry/LatLng;", "wrap", "", "value", MetricSummary.JsonKeys.MIN, MetricSummary.JsonKeys.MAX, "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final double wrap(double value, double min, double max) {
            double d = max - min;
            double d2 = (((value - min) % d) + d) % d;
            if (value >= max) {
                if (d2 == 0.0d) {
                    return max;
                }
            }
            return d2 + min;
        }

        private Companion() {
        }
    }
}
