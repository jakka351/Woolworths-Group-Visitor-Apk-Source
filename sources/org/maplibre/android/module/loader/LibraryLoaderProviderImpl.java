package org.maplibre.android.module.loader;

import org.maplibre.android.LibraryLoader;
import org.maplibre.android.LibraryLoaderProvider;

/* loaded from: classes2.dex */
public class LibraryLoaderProviderImpl implements LibraryLoaderProvider {
    @Override // org.maplibre.android.LibraryLoaderProvider
    public LibraryLoader getDefaultLibraryLoader() {
        return new SystemLibraryLoader();
    }

    private static class SystemLibraryLoader extends LibraryLoader {
        private SystemLibraryLoader() {
        }

        @Override // org.maplibre.android.LibraryLoader
        public void load(String str) {
            System.loadLibrary(str);
        }
    }
}
