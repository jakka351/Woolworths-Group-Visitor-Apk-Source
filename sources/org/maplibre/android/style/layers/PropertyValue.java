package org.maplibre.android.style.layers;

import com.google.gson.JsonArray;
import java.util.Arrays;
import org.maplibre.android.MapStrictMode;
import org.maplibre.android.exceptions.ConversionException;
import org.maplibre.android.log.Logger;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.utils.ColorUtils;

/* loaded from: classes2.dex */
public class PropertyValue<T> {
    private static final String TAG = "Mbgl-PropertyValue";
    public final String name;
    public final T value;

    public PropertyValue(String str, T t) {
        this.name = str;
        this.value = t;
    }

    public boolean isNull() {
        return this.value == null;
    }

    public boolean isExpression() {
        if (!isNull()) {
            T t = this.value;
            if ((t instanceof JsonArray) || (t instanceof Expression)) {
                return true;
            }
        }
        return false;
    }

    public Expression getExpression() {
        if (isExpression()) {
            T t = this.value;
            if (t instanceof JsonArray) {
                return Expression.Converter.convert((JsonArray) t);
            }
            return (Expression) t;
        }
        Logger.w(TAG, String.format("%s not an expression, try PropertyValue#getValue()", this.name));
        return null;
    }

    public boolean isValue() {
        return (isNull() || isExpression()) ? false : true;
    }

    public T getValue() {
        if (isValue()) {
            return this.value;
        }
        Logger.w(TAG, String.format("%s not a value, try PropertyValue#getExpression()", this.name));
        return null;
    }

    public Integer getColorInt() {
        if (isValue()) {
            T t = this.value;
            if (t instanceof String) {
                try {
                    return Integer.valueOf(ColorUtils.rgbaToColor((String) t));
                } catch (ConversionException e) {
                    Logger.e(TAG, String.format("%s could not be converted to a Color int: %s", this.name, e.getMessage()));
                    MapStrictMode.strictModeViolation(e);
                    return null;
                }
            }
        }
        Logger.e(TAG, String.format("%s is not a String value and can not be converted to a color it", this.name));
        return null;
    }

    public String toString() {
        return String.format("%s: %s", this.name, this.value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PropertyValue propertyValue = (PropertyValue) obj;
        if (!this.name.equals(propertyValue.name)) {
            return false;
        }
        T t = this.value;
        if (t == null) {
            return propertyValue.value == null;
        }
        if (t instanceof Object[]) {
            return Arrays.deepEquals((Object[]) t, (Object[]) propertyValue.value);
        }
        return t.equals(propertyValue.value);
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        T t = this.value;
        return iHashCode + (t != null ? t.hashCode() : 0);
    }
}
