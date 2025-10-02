package com.maplibre.rctmln.components.styles;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.android.style.layers.PropertyValue;

/* loaded from: classes3.dex */
public abstract class RCTMLNStyleFunctionParser<T, V> {
    private RCTMLNStyleValue mStyleValue;

    protected abstract T getRawStopValue(RCTMLNStyleValue rCTMLNStyleValue);

    protected abstract PropertyValue<V> getStopValue(T t);

    public RCTMLNStyleFunctionParser(RCTMLNStyleValue rCTMLNStyleValue) {
        this.mStyleValue = rCTMLNStyleValue;
    }

    public List<RCTMLNStyleFunctionParser<T, V>.StopConfig> getRawStops() {
        Object objAsString;
        StopConfig stopConfig;
        ReadableArray array = this.mStyleValue.getArray("stops");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            ReadableArray array2 = array.getArray(i);
            ReadableMap map = array2.getMap(0);
            RCTMLNStyleValue rCTMLNStyleValue = new RCTMLNStyleValue(array2.getMap(1));
            try {
                Dynamic dynamic = rCTMLNStyleValue.getDynamic("propertyValue");
                ReadableType type = dynamic.getType();
                if (type.equals(ReadableType.Number)) {
                    objAsString = Double.valueOf(dynamic.asDouble());
                } else if (type.equals(ReadableType.Boolean)) {
                    objAsString = Boolean.valueOf(dynamic.asBoolean());
                } else {
                    objAsString = dynamic.asString();
                }
            } catch (NoSuchKeyException unused) {
                objAsString = null;
            }
            if (objAsString != null) {
                stopConfig = new StopConfig(getStopKey(map), getRawStopValue(rCTMLNStyleValue), objAsString);
            } else {
                stopConfig = new StopConfig(this, getStopKey(map), getRawStopValue(rCTMLNStyleValue));
            }
            arrayList.add(stopConfig);
        }
        return arrayList;
    }

    private Object getStopKey(ReadableMap readableMap) {
        String string = readableMap.getString("type");
        string.hashCode();
        if (string.equals("number")) {
            return Double.valueOf(readableMap.getDouble("value"));
        }
        if (string.equals(TypedValues.Custom.S_BOOLEAN)) {
            return Boolean.valueOf(readableMap.getBoolean("value"));
        }
        return readableMap.getString("value");
    }

    private class StopConfig {
        Object key;
        Object propertyValue;
        T value;

        StopConfig(RCTMLNStyleFunctionParser rCTMLNStyleFunctionParser, Object obj, T t) {
            this(obj, t, null);
        }

        StopConfig(Object obj, T t, Object obj2) {
            this.key = obj;
            this.value = t;
            this.propertyValue = obj2;
        }
    }
}
