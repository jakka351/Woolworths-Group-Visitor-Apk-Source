package com.maplibre.rctmln.utils;

import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class ConvertUtils {
    public static final String LOG_TAG = "ConvertUtils";

    public static JsonObject toJsonObject(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        JsonObject jsonObject = new JsonObject();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(strNextKey).ordinal()]) {
                case 1:
                    jsonObject.add(strNextKey, toJsonObject(readableMap.getMap(strNextKey)));
                    break;
                case 2:
                    jsonObject.add(strNextKey, toJsonArray(readableMap.getArray(strNextKey)));
                    break;
                case 3:
                    jsonObject.add(strNextKey, null);
                    break;
                case 4:
                    jsonObject.addProperty(strNextKey, Double.valueOf(readableMap.getDouble(strNextKey)));
                    break;
                case 5:
                    jsonObject.addProperty(strNextKey, readableMap.getString(strNextKey));
                    break;
                case 6:
                    jsonObject.addProperty(strNextKey, Boolean.valueOf(readableMap.getBoolean(strNextKey)));
                    break;
            }
        }
        return jsonObject;
    }

    /* renamed from: com.maplibre.rctmln.utils.ConvertUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] iArr = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = iArr;
            try {
                iArr[ReadableType.Map.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Null.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static JsonArray toJsonArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        JsonArray jsonArray = new JsonArray(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (AnonymousClass1.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()]) {
                case 1:
                    jsonArray.add(toJsonObject(readableArray.getMap(i)));
                    break;
                case 2:
                    jsonArray.add(toJsonArray(readableArray.getArray(i)));
                    break;
                case 3:
                    jsonArray.add((JsonElement) null);
                    break;
                case 4:
                    jsonArray.add(Double.valueOf(readableArray.getDouble(i)));
                    break;
                case 5:
                    jsonArray.add(readableArray.getString(i));
                    break;
                case 6:
                    jsonArray.add(Boolean.valueOf(readableArray.getBoolean(i)));
                    break;
            }
        }
        return jsonArray;
    }

    public static JsonElement typedToJsonElement(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        String string = readableMap.getString("type");
        if (string.equals("hashmap")) {
            JsonObject jsonObject = new JsonObject();
            ReadableArray array = readableMap.getArray("value");
            for (int i = 0; i < array.size(); i++) {
                ReadableArray array2 = array.getArray(i);
                jsonObject.add(array2.getMap(0).getString("value"), typedToJsonElement(array2.getMap(1)));
            }
            return jsonObject;
        }
        if (string.equals("array")) {
            ReadableArray array3 = readableMap.getArray("value");
            JsonArray jsonArray = new JsonArray(array3.size());
            for (int i2 = 0; i2 < array3.size(); i2++) {
                jsonArray.add(typedToJsonElement(array3.getMap(i2)));
            }
            return jsonArray;
        }
        if (string.equals(TypedValues.Custom.S_BOOLEAN)) {
            return new JsonPrimitive(Boolean.valueOf(readableMap.getBoolean("value")));
        }
        if (string.equals("number")) {
            return new JsonPrimitive(Double.valueOf(readableMap.getDouble("value")));
        }
        if (string.equals(TypedValues.Custom.S_STRING)) {
            return new JsonPrimitive(readableMap.getString("value"));
        }
        throw new RuntimeException(String.format("Unrecognized type {}", readableMap.getString("type")));
    }

    public static WritableArray toWritableArray(JsonArray jsonArray) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement.isJsonArray()) {
                writableArrayCreateArray.pushArray(toWritableArray(jsonElement.getAsJsonArray()));
            } else if (jsonElement.isJsonObject()) {
                writableArrayCreateArray.pushMap(toWritableMap(jsonElement.getAsJsonObject()));
            } else if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isBoolean()) {
                    writableArrayCreateArray.pushBoolean(asJsonPrimitive.getAsBoolean());
                } else if (asJsonPrimitive.isNumber()) {
                    writableArrayCreateArray.pushDouble(asJsonPrimitive.getAsDouble());
                } else {
                    writableArrayCreateArray.pushString(asJsonPrimitive.getAsString());
                }
            }
        }
        return writableArrayCreateArray;
    }

    public static WritableMap toWritableMap(JsonObject jsonObject) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            JsonElement value = entry.getValue();
            if (value.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = value.getAsJsonPrimitive();
                if (asJsonPrimitive.isBoolean()) {
                    writableMapCreateMap.putBoolean(key, asJsonPrimitive.getAsBoolean());
                } else if (asJsonPrimitive.isNumber()) {
                    writableMapCreateMap.putDouble(key, asJsonPrimitive.getAsDouble());
                } else {
                    writableMapCreateMap.putString(key, asJsonPrimitive.getAsString());
                }
            } else if (value.isJsonArray()) {
                writableMapCreateMap.putArray(key, toWritableArray(value.getAsJsonArray()));
            } else if (value.isJsonObject()) {
                writableMapCreateMap.putMap(key, toWritableMap(value.getAsJsonObject()));
            }
        }
        return writableMapCreateMap;
    }

    public static Object getObjectFromString(String str) {
        try {
            return NumberFormat.getNumberInstance().parse(str);
        } catch (ParseException unused) {
            return str;
        }
    }

    public static List<String> toStringList(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray == null) {
            return arrayList;
        }
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(readableArray.getString(i));
        }
        return arrayList;
    }

    public static PointF toPointF(ReadableArray readableArray) {
        PointF pointF = new PointF();
        if (readableArray == null) {
            return pointF;
        }
        pointF.set((float) readableArray.getDouble(0), (float) readableArray.getDouble(1));
        return pointF;
    }

    public static RectF toRectF(ReadableArray readableArray) {
        RectF rectF = new RectF();
        if (readableArray == null) {
            return rectF;
        }
        rectF.set((float) readableArray.getDouble(3), (float) readableArray.getDouble(0), (float) readableArray.getDouble(1), (float) readableArray.getDouble(2));
        return rectF;
    }

    public static double getDouble(String str, ReadableMap readableMap, double d) {
        try {
            return readableMap.getDouble(str);
        } catch (NoSuchKeyException unused) {
            Log.d(LOG_TAG, String.format("No key found for %s, using default value %f", str, Double.valueOf(d)));
            return d;
        }
    }

    public static String getString(String str, ReadableMap readableMap, String str2) {
        try {
            return readableMap.getString(str);
        } catch (NoSuchKeyException unused) {
            Log.d(LOG_TAG, String.format("No key found for %s, using default value %s", str, str2));
            return str2;
        }
    }
}
