package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.Logger;
import com.facebook.react.uimanager.ViewProps;

/* loaded from: classes.dex */
public class PackageManagerHelper {
    private static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    private PackageManagerHelper() {
    }

    public static void setComponentEnabled(Context context, Class<?> klazz, boolean enabled) {
        String str = ViewProps.ENABLED;
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, klazz.getName()), enabled ? 1 : 2, 1);
            Logger.get().debug(TAG, klazz.getName() + " " + (enabled ? ViewProps.ENABLED : "disabled"));
        } catch (Exception e) {
            Logger logger = Logger.get();
            String str2 = TAG;
            StringBuilder sbAppend = new StringBuilder().append(klazz.getName()).append("could not be ");
            if (!enabled) {
                str = "disabled";
            }
            logger.debug(str2, sbAppend.append(str).toString(), e);
        }
    }

    public static boolean isComponentExplicitlyEnabled(Context context, Class<?> klazz) {
        return isComponentExplicitlyEnabled(context, klazz.getName());
    }

    public static boolean isComponentExplicitlyEnabled(Context context, String className) {
        return context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, className)) == 1;
    }
}
