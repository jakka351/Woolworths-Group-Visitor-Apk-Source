package org.maplibre.android.utils;

import android.content.Context;
import android.os.Bundle;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.maps.MapLibreMapOptions;

/* loaded from: classes2.dex */
public class MapFragmentUtils {
    public static Bundle createFragmentArgs(MapLibreMapOptions mapLibreMapOptions) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MapLibreConstants.FRAG_ARG_MAPLIBREMAPOPTIONS, mapLibreMapOptions);
        return bundle;
    }

    public static MapLibreMapOptions resolveArgs(Context context, Bundle bundle) {
        if (bundle != null && bundle.containsKey(MapLibreConstants.FRAG_ARG_MAPLIBREMAPOPTIONS)) {
            return (MapLibreMapOptions) bundle.getParcelable(MapLibreConstants.FRAG_ARG_MAPLIBREMAPOPTIONS);
        }
        return MapLibreMapOptions.createFromAttributes(context);
    }
}
