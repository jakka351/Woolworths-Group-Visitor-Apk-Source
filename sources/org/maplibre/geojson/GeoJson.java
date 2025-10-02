package org.maplibre.geojson;

import java.io.Serializable;

/* loaded from: classes2.dex */
public interface GeoJson extends Serializable {
    BoundingBox bbox();

    String toJson();

    String type();
}
