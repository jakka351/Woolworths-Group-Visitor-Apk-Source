package com.site360new;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import io.sentry.protocol.Device;
import java.util.Locale;
import kotlin.Metadata;

/* compiled from: LocaleService.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¨\u0006\u0006"}, d2 = {"Lcom/site360new/LocaleService;", "", "()V", "updateBaseContextLocale", "Landroid/content/Context;", "context", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LocaleService {
    public static final LocaleService INSTANCE = new LocaleService();

    private LocaleService() {
    }

    public final Context updateBaseContextLocale(Context context) {
        String string;
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences("sharedPreferences", 0) : null;
        if (sharedPreferences == null || (string = sharedPreferences.getString(Device.JsonKeys.LANGUAGE, "en")) == null) {
            return null;
        }
        Locale locale = new Locale(string);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration configuration = resources != null ? resources.getConfiguration() : null;
        if (configuration != null) {
            configuration.setLocale(locale);
        }
        if (configuration != null) {
            return context.createConfigurationContext(configuration);
        }
        return null;
    }
}
