package org.maplibre.android.location;

import android.graphics.Bitmap;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;

/* loaded from: classes2.dex */
interface LocationLayerRenderer {
    void addBitmaps(int i, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6);

    void addLayers(LocationComponentPositionManager locationComponentPositionManager);

    void adjustPulsingCircleLayerVisibility(boolean z);

    void cameraBearingUpdated(double d);

    void cameraTiltUpdated(double d);

    void hide();

    void initializeComponents(Style style);

    void removeLayers();

    void setAccuracyRadius(Float f);

    void setCompassBearing(Float f);

    void setGpsBearing(Float f);

    void setLatLng(LatLng latLng);

    void setLocationStale(boolean z, int i);

    void show(int i, boolean z);

    void styleAccuracy(float f, int i);

    void stylePulsingCircle(LocationComponentOptions locationComponentOptions);

    void styleScaling(Expression expression);

    void updateIconIds(String str, String str2, String str3, String str4, String str5);

    void updatePulsingUi(float f, Float f2);
}
