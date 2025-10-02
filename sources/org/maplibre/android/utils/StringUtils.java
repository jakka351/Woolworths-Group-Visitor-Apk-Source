package org.maplibre.android.utils;

import java.text.Normalizer;

/* loaded from: classes2.dex */
class StringUtils {
    StringUtils() {
    }

    static String unaccent(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("(\\p{InCombiningDiacriticalMarks}|\\p{InCombiningDiacriticalMarksForSymbols}|\\p{InCombiningDiacriticalMarksSupplement})+", "");
    }
}
