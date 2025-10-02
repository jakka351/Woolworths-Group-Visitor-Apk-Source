package org.maplibre.android;

import org.maplibre.android.log.Logger;

/* loaded from: classes2.dex */
public abstract class LibraryLoader {
    private static final LibraryLoader DEFAULT;
    private static final String TAG = "Mbgl-LibraryLoader";
    private static boolean loaded;
    private static volatile LibraryLoader loader;

    public abstract void load(String str);

    static {
        LibraryLoader defaultLibraryLoader = MapLibre.getModuleProvider().createLibraryLoaderProvider().getDefaultLibraryLoader();
        DEFAULT = defaultLibraryLoader;
        loader = defaultLibraryLoader;
    }

    public static void setLibraryLoader(LibraryLoader libraryLoader) {
        loader = libraryLoader;
    }

    public static synchronized void load() {
        try {
        } catch (UnsatisfiedLinkError e) {
            loaded = false;
            Logger.e(TAG, "Failed to load native shared library.", e);
            MapStrictMode.strictModeViolation("Failed to load native shared library.", e);
        }
        if (!loaded) {
            loaded = true;
            loader.load("maplibre");
        }
    }
}
