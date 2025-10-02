package org.apache.commons.lang3.text;

import java.util.Map;

/* loaded from: classes2.dex */
public abstract class StrLookup<V> {
    private static final StrLookup<String> NONE_LOOKUP = new MapStrLookup(null);
    private static final StrLookup<String> SYSTEM_PROPERTIES_LOOKUP;

    public abstract String lookup(String str);

    static {
        StrLookup<String> mapStrLookup;
        try {
            mapStrLookup = new MapStrLookup<>(System.getProperties());
        } catch (SecurityException unused) {
            mapStrLookup = NONE_LOOKUP;
        }
        SYSTEM_PROPERTIES_LOOKUP = mapStrLookup;
    }

    public static StrLookup<?> noneLookup() {
        return NONE_LOOKUP;
    }

    public static StrLookup<String> systemPropertiesLookup() {
        return SYSTEM_PROPERTIES_LOOKUP;
    }

    public static <V> StrLookup<V> mapLookup(Map<String, V> map) {
        return new MapStrLookup(map);
    }

    protected StrLookup() {
    }

    static class MapStrLookup<V> extends StrLookup<V> {
        private final Map<String, V> map;

        MapStrLookup(Map<String, V> map) {
            this.map = map;
        }

        @Override // org.apache.commons.lang3.text.StrLookup
        public String lookup(String str) {
            V v;
            Map<String, V> map = this.map;
            if (map == null || (v = map.get(str)) == null) {
                return null;
            }
            return v.toString();
        }
    }
}
