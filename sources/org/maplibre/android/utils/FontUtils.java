package org.maplibre.android.utils;

import android.graphics.Typeface;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.maplibre.android.MapStrictMode;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.log.Logger;

/* loaded from: classes2.dex */
public class FontUtils {
    private static final List<String> DEFAULT_FONT_STACKS;
    private static final String TAG = "Mbgl-FontUtils";
    private static final String TYPEFACE_FONTMAP_FIELD_NAME = "sSystemFontMap";

    static {
        ArrayList arrayList = new ArrayList();
        DEFAULT_FONT_STACKS = arrayList;
        arrayList.add(MapLibreConstants.DEFAULT_FONT);
        arrayList.add("serif");
        arrayList.add("monospace");
    }

    private FontUtils() {
    }

    public static String extractValidFont(String... strArr) throws NoSuchFieldException {
        if (strArr.length == 0) {
            return null;
        }
        List<String> deviceFonts = getDeviceFonts();
        for (String str : strArr) {
            if (deviceFonts.contains(str)) {
                return str;
            }
        }
        Logger.i(TAG, String.format("Couldn't map font family for local ideograph, using %s instead", MapLibreConstants.DEFAULT_FONT));
        return MapLibreConstants.DEFAULT_FONT;
    }

    private static List<String> getDeviceFonts() throws NoSuchFieldException {
        ArrayList arrayList = new ArrayList();
        try {
            Typeface typefaceCreate = Typeface.create(Typeface.DEFAULT, 0);
            Field declaredField = Typeface.class.getDeclaredField(TYPEFACE_FONTMAP_FIELD_NAME);
            declaredField.setAccessible(true);
            arrayList.addAll(((Map) declaredField.get(typefaceCreate)).keySet());
        } catch (Exception e) {
            Logger.e(TAG, "Couldn't load fonts from Typeface", e);
            MapStrictMode.strictModeViolation("Couldn't load fonts from Typeface", e);
        }
        return arrayList;
    }
}
