package com.maplibre.rctmln.components.styles;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.utils.ExpressionParser;
import io.sentry.protocol.SentryStackFrame;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.TransitionOptions;

/* loaded from: classes3.dex */
public class RCTMLNStyleValue {
    public static final int InterpolationModeCategorical = 102;
    public static final int InterpolationModeExponential = 100;
    public static final int InterpolationModeIdentity = 103;
    public static final int InterpolationModeInterval = 101;
    private Double imageScale;
    private String imageURI;
    private boolean isAddImage;
    private boolean isExpression;
    private Expression mExpression;
    private ReadableMap mPayload;
    private String mType;

    public RCTMLNStyleValue(ReadableMap readableMap) {
        ReadableMap map;
        this.imageURI = "";
        Double dValueOf = Double.valueOf(0.0d);
        this.imageScale = dValueOf;
        this.mType = readableMap.getString("styletype");
        this.mPayload = readableMap.getMap("stylevalue");
        if ("image".equals(this.mType)) {
            this.imageScale = dValueOf;
            if ("hashmap".equals(this.mPayload.getString("type"))) {
                ReadableMap map2 = getMap();
                this.imageURI = map2.getMap(ReactNativeBlobUtilConst.DATA_ENCODE_URI).getString("value");
                if (map2.getMap("scale") != null) {
                    this.imageScale = Double.valueOf(map2.getMap("scale").getDouble("value"));
                }
            } else if (TypedValues.Custom.S_STRING.equals(this.mPayload.getString("type"))) {
                String string = this.mPayload.getString("value");
                if (string.contains("://")) {
                    this.imageURI = string;
                } else {
                    this.imageURI = null;
                    this.isExpression = true;
                    this.mExpression = Expression.literal(string);
                }
            } else {
                this.imageURI = null;
            }
            boolean z = this.imageURI != null;
            this.isAddImage = z;
            if (z) {
                return;
            }
        }
        Dynamic dynamic = this.mPayload.getDynamic("value");
        if (dynamic.getType().equals(ReadableType.Array)) {
            ReadableArray readableArrayAsArray = dynamic.asArray();
            if (readableArrayAsArray.size() <= 0 || !this.mPayload.getString("type").equals("array") || (map = readableArrayAsArray.getMap(0)) == null || !map.getString("type").equals(TypedValues.Custom.S_STRING)) {
                return;
            }
            this.isExpression = true;
            this.mExpression = ExpressionParser.fromTyped(this.mPayload);
        }
    }

    private boolean isTokenizedValue(String str) {
        return str.startsWith("{") && str.endsWith("}");
    }

    public String getType() {
        return this.mType;
    }

    public boolean isFunction() {
        return this.mType.equals(SentryStackFrame.JsonKeys.FUNCTION);
    }

    public int getInt(String str) {
        return this.mPayload.getInt(str);
    }

    public String getString(String str) {
        return this.mPayload.getString(str);
    }

    public Double getDouble(String str) {
        return Double.valueOf(this.mPayload.getDouble(str));
    }

    public Float getFloat(String str) {
        return Float.valueOf(getDouble(str).floatValue());
    }

    public Dynamic getDynamic(String str) {
        return this.mPayload.getDynamic(str);
    }

    public ReadableArray getArray(String str) {
        return this.mPayload.getArray(str);
    }

    public Boolean getBoolean(String str) {
        return Boolean.valueOf(this.mPayload.getBoolean(str));
    }

    public Float[] getFloatArray(String str) {
        ReadableArray array = getArray(str);
        Float[] fArr = new Float[array.size()];
        for (int i = 0; i < array.size(); i++) {
            fArr[i] = Float.valueOf((float) array.getMap(i).getDouble("value"));
        }
        return fArr;
    }

    public String[] getStringArray(String str) {
        ReadableArray array = getArray(str);
        String[] strArr = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            strArr[i] = array.getMap(i).getString("value");
        }
        return strArr;
    }

    public ReadableMap getMap() {
        if (!"hashmap".equals(this.mPayload.getString("type"))) {
            return null;
        }
        ReadableArray array = this.mPayload.getArray("value");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        for (int i = 0; i < array.size(); i++) {
            ReadableArray array2 = array.getArray(i);
            String string = array2.getMap(0).getString("value");
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            writableNativeMap2.merge(array2.getMap(1));
            writableNativeMap.putMap(string, writableNativeMap2);
        }
        return writableNativeMap;
    }

    public ReadableMap getMap(String str) {
        return getMap();
    }

    public Expression getExpression() {
        return this.mExpression;
    }

    public boolean isExpression() {
        return this.isExpression;
    }

    public boolean shouldAddImage() {
        return this.isAddImage;
    }

    public Boolean isImageStringValue() {
        return Boolean.valueOf(TypedValues.Custom.S_STRING.equals(this.mPayload.getString("type")));
    }

    public String getImageStringValue() {
        return this.mPayload.getString("value");
    }

    public String getImageURI() {
        return this.imageURI;
    }

    public double getImageScale() {
        return this.imageScale.doubleValue();
    }

    public TransitionOptions getTransition() {
        if (!this.mType.equals("transition")) {
            return null;
        }
        ReadableMap map = getMap("value");
        return new TransitionOptions((map.hasKey("duration") && ReadableType.Map.equals(map.getType("duration"))) ? map.getMap("duration").getInt("value") : 300, (map.hasKey("delay") && ReadableType.Map.equals(map.getType("delay"))) ? map.getMap("delay").getInt("value") : 0, map.hasKey("enablePlacementTransitions") ? map.getMap("enablePlacementTransitions").getBoolean("value") : true);
    }
}
