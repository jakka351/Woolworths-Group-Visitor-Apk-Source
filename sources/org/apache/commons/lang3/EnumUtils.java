package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class EnumUtils {
    public static <E extends Enum<E>> Map<String, E> getEnumMap(Class<E> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (E e : cls.getEnumConstants()) {
            linkedHashMap.put(e.name(), e);
        }
        return linkedHashMap;
    }

    public static <E extends Enum<E>> List<E> getEnumList(Class<E> cls) {
        return new ArrayList(Arrays.asList(cls.getEnumConstants()));
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> cls, String str) {
        if (str == null) {
            return false;
        }
        try {
            Enum.valueOf(cls, str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str) {
        if (str == null) {
            return null;
        }
        try {
            return (E) Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
