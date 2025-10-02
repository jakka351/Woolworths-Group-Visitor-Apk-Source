package org.maplibre.android.exceptions;

/* loaded from: classes2.dex */
public class IconBitmapChangedException extends RuntimeException {
    public IconBitmapChangedException() {
        super("The added Marker has an Icon with a bitmap that has been modified. An Icon cannot be modifiedafter it has been added to the map in a Marker.");
    }
}
