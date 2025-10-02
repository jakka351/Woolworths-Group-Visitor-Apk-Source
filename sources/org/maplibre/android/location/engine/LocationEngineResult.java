package org.maplibre.android.location.engine;

import android.content.Intent;
import android.location.Location;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class LocationEngineResult {
    private final List<Location> locations;

    private LocationEngineResult(List<Location> list) {
        this.locations = Collections.unmodifiableList(list);
    }

    public static LocationEngineResult create(Location location) {
        ArrayList arrayList = new ArrayList();
        if (location != null) {
            arrayList.add(location);
        }
        return new LocationEngineResult(arrayList);
    }

    public static LocationEngineResult create(List<Location> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list);
            arrayList.removeAll(Collections.singleton(null));
            return new LocationEngineResult(arrayList);
        }
        return new LocationEngineResult(Collections.emptyList());
    }

    public Location getLastLocation() {
        if (this.locations.isEmpty()) {
            return null;
        }
        return this.locations.get(0);
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(this.locations);
    }

    public static LocationEngineResult extractResult(Intent intent) {
        return extractAndroidResult(intent);
    }

    private static LocationEngineResult extractAndroidResult(Intent intent) {
        if (hasResult(intent)) {
            return create((Location) intent.getExtras().getParcelable("location"));
        }
        return null;
    }

    private static boolean hasResult(Intent intent) {
        return intent != null && intent.hasExtra("location");
    }
}
