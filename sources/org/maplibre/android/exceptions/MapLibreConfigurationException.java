package org.maplibre.android.exceptions;

/* loaded from: classes2.dex */
public class MapLibreConfigurationException extends RuntimeException {
    public MapLibreConfigurationException() {
        super("\nUsing MapView requires calling MapLibre.getInstance(Context context, String apiKey, WellKnownTileServer wellKnownTileServer) before inflating or creating the view.");
    }

    public MapLibreConfigurationException(String str) {
        super(str);
    }
}
