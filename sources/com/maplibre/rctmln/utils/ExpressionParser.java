package com.maplibre.rctmln.utils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.gson.JsonArray;
import java.util.Locale;
import javax.annotation.Nullable;
import org.maplibre.android.style.expressions.Expression;

/* loaded from: classes3.dex */
public class ExpressionParser {
    static final String TYPE_ARRAY = "array";
    static final String TYPE_BOOL = "boolean";
    static final String TYPE_MAP = "hashmap";
    static final String TYPE_NUMBER = "number";
    static final String TYPE_STRING = "string";

    @Nullable
    public static Expression from(@Nullable ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        return Expression.Converter.convert(ConvertUtils.toJsonArray(readableArray));
    }

    public static Expression fromTyped(ReadableMap readableMap) {
        return Expression.Converter.convert((JsonArray) ConvertUtils.typedToJsonElement(readableMap));
    }

    public static Expression from(ReadableMap readableMap) {
        return Expression.raw("[" + stringExpression(readableMap) + "]");
    }

    private static String stringExpression(ReadableMap readableMap) {
        String string = readableMap.getString("type");
        if ("string".equals(string)) {
            return String.format(Locale.ENGLISH, "\"%s\"", readableMap.getString("value"));
        }
        if (TYPE_NUMBER.equals(string)) {
            return String.format(Locale.ENGLISH, "%f", Double.valueOf(readableMap.getDouble("value")));
        }
        if ("boolean".equals(string)) {
            return String.format(Locale.ENGLISH, "%b", Boolean.valueOf(readableMap.getBoolean("value")));
        }
        if (!TYPE_ARRAY.equals(string)) {
            return "";
        }
        ReadableArray array = readableMap.getArray("value");
        String str = "[";
        for (int i = 0; i < array.size(); i++) {
            str = str + stringExpression(array.getMap(i));
            if (i < array.size() - 1) {
                str = str + ",";
            }
        }
        return str + "]";
    }
}
