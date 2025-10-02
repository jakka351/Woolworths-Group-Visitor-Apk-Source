package org.maplibre.android.location;

/* loaded from: classes2.dex */
public interface CompassEngine {
    void addCompassListener(CompassListener compassListener);

    int getLastAccuracySensorStatus();

    float getLastHeading();

    void removeCompassListener(CompassListener compassListener);
}
