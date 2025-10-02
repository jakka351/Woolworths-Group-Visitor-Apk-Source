package org.maplibre.android.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.widget.ImageView;
import androidx.core.widget.ImageViewCompat;
import io.sentry.Session;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.maplibre.android.R;
import org.maplibre.android.exceptions.ConversionException;

/* loaded from: classes2.dex */
public class ColorUtils {
    public static float[] colorToGlRgbaArray(int i) {
        return new float[]{((i >> 16) & 255) / 255.0f, ((i >> 8) & 255) / 255.0f, (i & 255) / 255.0f, ((i >> 24) & 255) / 255.0f};
    }

    public static float[] colorToRgbaArray(int i) {
        return new float[]{(i >> 16) & 255, (i >> 8) & 255, i & 255, ((i >> 24) & 255) / 255.0f};
    }

    public static int getPrimaryColor(Context context) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(context.getResources().getIdentifier("colorPrimary", Session.JsonKeys.ATTRS, context.getPackageName()), typedValue, true);
            return typedValue.data;
        } catch (Exception unused) {
            return getColorCompat(context, R.color.maplibre_blue);
        }
    }

    public static int getPrimaryDarkColor(Context context) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(context.getResources().getIdentifier("colorPrimaryDark", Session.JsonKeys.ATTRS, context.getPackageName()), typedValue, true);
            return typedValue.data;
        } catch (Exception unused) {
            return getColorCompat(context, R.color.maplibre_blue);
        }
    }

    public static int getAccentColor(Context context) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(context.getResources().getIdentifier("colorAccent", Session.JsonKeys.ATTRS, context.getPackageName()), typedValue, true);
            return typedValue.data;
        } catch (Exception unused) {
            return getColorCompat(context, R.color.maplibre_gray);
        }
    }

    public static ColorStateList getSelector(int i) {
        return new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}, new int[0]}, new int[]{i, i});
    }

    public static void setTintList(ImageView imageView, int i) {
        ImageViewCompat.setImageTintList(imageView, getSelector(i));
    }

    public static int rgbaToColor(String str) {
        Matcher matcher = Pattern.compile("rgba?\\s*\\(\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*,\\s*(\\d+\\.?\\d*)\\s*,?\\s*(\\d+\\.?\\d*)?\\s*\\)").matcher(str);
        if (matcher.matches() && matcher.groupCount() == 3) {
            return Color.rgb((int) Float.parseFloat(matcher.group(1)), (int) Float.parseFloat(matcher.group(2)), (int) Float.parseFloat(matcher.group(3)));
        }
        if (matcher.matches() && matcher.groupCount() == 4) {
            return Color.argb((int) (Float.parseFloat(matcher.group(4)) * 255.0f), (int) Float.parseFloat(matcher.group(1)), (int) Float.parseFloat(matcher.group(2)), (int) Float.parseFloat(matcher.group(3)));
        }
        throw new ConversionException("Not a valid rgb/rgba value");
    }

    public static String colorToRgbaString(int i) {
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        decimalFormat.applyPattern("#.###");
        return String.format(Locale.US, "rgba(%d, %d, %d, %s)", Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf(i & 255), decimalFormat.format(((i >> 24) & 255) / 255.0f));
    }

    private static int getColorCompat(Context context, int i) {
        return context.getResources().getColor(i, context.getTheme());
    }
}
