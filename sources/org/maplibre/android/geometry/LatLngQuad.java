package org.maplibre.android.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LatLngQuad.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0016J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u001b"}, d2 = {"Lorg/maplibre/android/geometry/LatLngQuad;", "Landroid/os/Parcelable;", "topLeft", "Lorg/maplibre/android/geometry/LatLng;", "topRight", "bottomRight", "bottomLeft", "<init>", "(Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLng;Lorg/maplibre/android/geometry/LatLng;)V", "getTopLeft", "()Lorg/maplibre/android/geometry/LatLng;", "getTopRight", "getBottomRight", "getBottomLeft", "describeContents", "", "writeToParcel", "", "out", "Landroid/os/Parcel;", "arg1", "equals", "", "other", "", "hashCode", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LatLngQuad implements Parcelable {
    private final LatLng bottomLeft;
    private final LatLng bottomRight;
    private final LatLng topLeft;
    private final LatLng topRight;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final Parcelable.Creator<LatLngQuad> CREATOR = new Parcelable.Creator<LatLngQuad>() { // from class: org.maplibre.android.geometry.LatLngQuad$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public LatLngQuad createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return LatLngQuad.INSTANCE.readFromParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public LatLngQuad[] newArray(int size) {
            return new LatLngQuad[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLngQuad(LatLng topLeft, LatLng topRight, LatLng bottomRight, LatLng bottomLeft) {
        Intrinsics.checkNotNullParameter(topLeft, "topLeft");
        Intrinsics.checkNotNullParameter(topRight, "topRight");
        Intrinsics.checkNotNullParameter(bottomRight, "bottomRight");
        Intrinsics.checkNotNullParameter(bottomLeft, "bottomLeft");
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomRight = bottomRight;
        this.bottomLeft = bottomLeft;
    }

    public final LatLng getBottomLeft() {
        return this.bottomLeft;
    }

    public final LatLng getBottomRight() {
        return this.bottomRight;
    }

    public final LatLng getTopLeft() {
        return this.topLeft;
    }

    public final LatLng getTopRight() {
        return this.topRight;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int arg1) {
        Intrinsics.checkNotNullParameter(out, "out");
        this.topLeft.writeToParcel(out, arg1);
        this.topRight.writeToParcel(out, arg1);
        this.bottomRight.writeToParcel(out, arg1);
        this.bottomLeft.writeToParcel(out, arg1);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type org.maplibre.android.geometry.LatLngQuad");
        LatLngQuad latLngQuad = (LatLngQuad) other;
        return Intrinsics.areEqual(this.topLeft, latLngQuad.topLeft) && Intrinsics.areEqual(this.topRight, latLngQuad.topRight) && Intrinsics.areEqual(this.bottomRight, latLngQuad.bottomRight) && Intrinsics.areEqual(this.bottomLeft, latLngQuad.bottomLeft);
    }

    public int hashCode() {
        return (((((this.topLeft.hashCode() * 31) + this.topRight.hashCode()) * 31) + this.bottomRight.hashCode()) * 31) + this.bottomLeft.hashCode();
    }

    /* compiled from: LatLngQuad.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/maplibre/android/geometry/LatLngQuad$Companion;", "", "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lorg/maplibre/android/geometry/LatLngQuad;", "readFromParcel", "parcel", "Landroid/os/Parcel;", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final LatLngQuad readFromParcel(Parcel parcel) {
            return new LatLngQuad(new LatLng(parcel), new LatLng(parcel), new LatLng(parcel), new LatLng(parcel));
        }
    }
}
