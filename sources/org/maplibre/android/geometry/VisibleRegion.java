package org.maplibre.android.geometry;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.imagepipeline.common.RotationOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

/* compiled from: VisibleRegion.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0011\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B1\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0004\u0010\rJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0015H\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/maplibre/android/geometry/VisibleRegion;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "<init>", "(Landroid/os/Parcel;)V", "farLeft", "Lorg/maplibre/android/geometry/LatLng;", "farRight", "nearLeft", "nearRight", "latLngBounds", "Lorg/maplibre/android/geometry/LatLngBounds;", "(Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLngBounds;)V", "equals", "", "other", "", "toString", "", "hashCode", "", "describeContents", "writeToParcel", "", "out", "flags", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class VisibleRegion implements Parcelable {
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;
    public static final Parcelable.Creator<VisibleRegion> CREATOR = new Parcelable.Creator<VisibleRegion>() { // from class: org.maplibre.android.geometry.VisibleRegion$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public VisibleRegion createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new VisibleRegion(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        public VisibleRegion[] newArray(int size) {
            return new VisibleRegion[size];
        }
    };

    public /* synthetic */ VisibleRegion(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private VisibleRegion(Parcel parcel) {
        if (Build.VERSION.SDK_INT >= 33) {
            this.farLeft = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader(), LatLng.class);
            this.farRight = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader(), LatLng.class);
            this.nearLeft = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader(), LatLng.class);
            this.nearRight = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader(), LatLng.class);
            Object parcelable = parcel.readParcelable(LatLngBounds.class.getClassLoader(), LatLngBounds.class);
            Intrinsics.checkNotNull(parcelable);
            this.latLngBounds = (LatLngBounds) parcelable;
            return;
        }
        this.farLeft = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.farRight = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.nearLeft = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.nearRight = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        Parcelable parcelable2 = parcel.readParcelable(LatLngBounds.class.getClassLoader());
        Intrinsics.checkNotNull(parcelable2);
        this.latLngBounds = (LatLngBounds) parcelable2;
    }

    public VisibleRegion(LatLng farLeft, LatLng farRight, LatLng nearLeft, LatLng nearRight, LatLngBounds latLngBounds) {
        Intrinsics.checkNotNullParameter(farLeft, "farLeft");
        Intrinsics.checkNotNullParameter(farRight, "farRight");
        Intrinsics.checkNotNullParameter(nearLeft, "nearLeft");
        Intrinsics.checkNotNullParameter(nearRight, "nearRight");
        Intrinsics.checkNotNullParameter(latLngBounds, "latLngBounds");
        this.farLeft = farLeft;
        this.farRight = farRight;
        this.nearLeft = nearLeft;
        this.nearRight = nearRight;
        this.latLngBounds = latLngBounds;
    }

    public boolean equals(Object other) {
        if (!(other instanceof VisibleRegion)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        VisibleRegion visibleRegion = (VisibleRegion) other;
        return Intrinsics.areEqual(this.farLeft, visibleRegion.farLeft) && Intrinsics.areEqual(this.farRight, visibleRegion.farRight) && Intrinsics.areEqual(this.nearLeft, visibleRegion.nearLeft) && Intrinsics.areEqual(this.nearRight, visibleRegion.nearRight) && Intrinsics.areEqual(this.latLngBounds, visibleRegion.latLngBounds);
    }

    public String toString() {
        return "[farLeft [" + this.farLeft + "], farRight [" + this.farRight + "], nearLeft [" + this.nearLeft + "], nearRight [" + this.nearRight + "], latLngBounds [" + this.latLngBounds + "]]";
    }

    public int hashCode() {
        LatLng latLng = this.farLeft;
        int iHashCode = (latLng != null ? latLng.hashCode() : 0) + 90;
        LatLng latLng2 = this.farRight;
        int iHashCode2 = iHashCode + (((latLng2 != null ? latLng2.hashCode() : 0) + 90) * 1000);
        LatLng latLng3 = this.nearLeft;
        int iHashCode3 = iHashCode2 + (((latLng3 != null ? latLng3.hashCode() : 0) + RotationOptions.ROTATE_180) * DurationKt.NANOS_IN_MILLIS);
        LatLng latLng4 = this.nearRight;
        return iHashCode3 + (((latLng4 != null ? latLng4.hashCode() : 0) + RotationOptions.ROTATE_180) * 1000000000);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeParcelable(this.farLeft, flags);
        out.writeParcelable(this.farRight, flags);
        out.writeParcelable(this.nearLeft, flags);
        out.writeParcelable(this.nearRight, flags);
        out.writeParcelable(this.latLngBounds, flags);
    }
}
