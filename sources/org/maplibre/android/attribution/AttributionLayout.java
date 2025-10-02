package org.maplibre.android.attribution;

import android.graphics.Bitmap;
import android.graphics.PointF;

/* loaded from: classes2.dex */
public class AttributionLayout {
    private PointF anchorPoint;
    private Bitmap logo;
    private boolean shortText;

    public AttributionLayout(Bitmap bitmap, PointF pointF, boolean z) {
        this.logo = bitmap;
        this.anchorPoint = pointF;
        this.shortText = z;
    }

    public Bitmap getLogo() {
        return this.logo;
    }

    public PointF getAnchorPoint() {
        return this.anchorPoint;
    }

    public boolean isShortText() {
        return this.shortText;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AttributionLayout attributionLayout = (AttributionLayout) obj;
        Bitmap bitmap = this.logo;
        if (bitmap == null ? attributionLayout.logo != null : !bitmap.equals(attributionLayout.logo)) {
            return false;
        }
        PointF pointF = this.anchorPoint;
        PointF pointF2 = attributionLayout.anchorPoint;
        return pointF != null ? pointF.equals(pointF2) : pointF2 == null;
    }

    public int hashCode() {
        Bitmap bitmap = this.logo;
        int iHashCode = (bitmap != null ? bitmap.hashCode() : 0) * 31;
        PointF pointF = this.anchorPoint;
        return iHashCode + (pointF != null ? pointF.hashCode() : 0);
    }

    public String toString() {
        return "AttributionLayout{logo=" + this.logo + ", anchorPoint=" + this.anchorPoint + '}';
    }
}
