package org.maplibre.android;

/* loaded from: classes2.dex */
public class MapStrictModeException extends RuntimeException {
    MapStrictModeException(String str) {
        super(String.format("Map detected an error that would fail silently otherwise: %s", str));
    }
}
