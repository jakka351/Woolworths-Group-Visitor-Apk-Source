package org.maplibre.android;

import android.content.Context;
import android.content.res.AssetManager;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.exceptions.MapLibreConfigurationException;
import org.maplibre.android.net.ConnectivityReceiver;
import org.maplibre.android.storage.FileSource;
import org.maplibre.android.util.DefaultStyle;
import org.maplibre.android.util.TileServerOptions;
import org.maplibre.android.utils.ThreadUtils;
import timber.log.Timber;

/* loaded from: classes2.dex */
public final class MapLibre {
    private static MapLibre INSTANCE = null;
    private static final String TAG = "Mbgl-MapLibre";
    private static ModuleProvider moduleProvider;
    private String apiKey;
    private Context context;
    private TileServerOptions tileServerOptions;

    public static synchronized MapLibre getInstance(Context context) {
        ThreadUtils.init(context);
        ThreadUtils.checkThread(TAG);
        if (INSTANCE == null) {
            Context applicationContext = context.getApplicationContext();
            FileSource.initializeFileDirsPaths(applicationContext);
            INSTANCE = new MapLibre(applicationContext, null);
            ConnectivityReceiver.instance(applicationContext);
        }
        TileServerOptions tileServerOptions = TileServerOptions.get(WellKnownTileServer.MapLibre);
        MapLibre mapLibre = INSTANCE;
        mapLibre.tileServerOptions = tileServerOptions;
        mapLibre.apiKey = null;
        FileSource fileSource = FileSource.getInstance(context);
        fileSource.setTileServerOptions(tileServerOptions);
        fileSource.setApiKey(null);
        return INSTANCE;
    }

    public static synchronized MapLibre getInstance(Context context, String str, WellKnownTileServer wellKnownTileServer) {
        ThreadUtils.init(context);
        ThreadUtils.checkThread(TAG);
        MapLibre mapLibre = INSTANCE;
        if (mapLibre == null) {
            Timber.plant(new Timber.Tree[0]);
            Context applicationContext = context.getApplicationContext();
            FileSource.initializeFileDirsPaths(applicationContext);
            INSTANCE = new MapLibre(applicationContext, str);
            ConnectivityReceiver.instance(applicationContext);
        } else {
            mapLibre.apiKey = str;
        }
        TileServerOptions tileServerOptions = TileServerOptions.get(wellKnownTileServer);
        INSTANCE.tileServerOptions = tileServerOptions;
        FileSource fileSource = FileSource.getInstance(context);
        fileSource.setTileServerOptions(tileServerOptions);
        fileSource.setApiKey(str);
        return INSTANCE;
    }

    MapLibre(Context context, String str) {
        this.context = context;
        this.apiKey = str;
    }

    MapLibre(Context context, String str, TileServerOptions tileServerOptions) {
        this.context = context;
        this.apiKey = str;
        this.tileServerOptions = tileServerOptions;
    }

    public static String getApiKey() {
        validateMapLibre();
        return INSTANCE.apiKey;
    }

    public static void setApiKey(String str) {
        validateMapLibre();
        throwIfApiKeyInvalid(str);
        INSTANCE.apiKey = str;
        FileSource.getInstance(getApplicationContext()).setApiKey(str);
    }

    public static TileServerOptions getTileServerOptions() {
        validateMapLibre();
        return INSTANCE.tileServerOptions;
    }

    public static DefaultStyle[] getPredefinedStyles() {
        validateMapLibre();
        TileServerOptions tileServerOptions = INSTANCE.tileServerOptions;
        if (tileServerOptions != null) {
            return tileServerOptions.getDefaultStyles();
        }
        return null;
    }

    public static DefaultStyle getPredefinedStyle(String str) {
        validateMapLibre();
        TileServerOptions tileServerOptions = INSTANCE.tileServerOptions;
        if (tileServerOptions == null) {
            return null;
        }
        for (DefaultStyle defaultStyle : tileServerOptions.getDefaultStyles()) {
            if (defaultStyle.getName().equalsIgnoreCase(str)) {
                return defaultStyle;
            }
        }
        return null;
    }

    public static Context getApplicationContext() {
        validateMapLibre();
        return INSTANCE.context;
    }

    public static synchronized void setConnected(Boolean bool) {
        validateMapLibre();
        ConnectivityReceiver.instance(INSTANCE.context).setConnected(bool);
    }

    public static synchronized Boolean isConnected() {
        validateMapLibre();
        return Boolean.valueOf(ConnectivityReceiver.instance(INSTANCE.context).isConnected());
    }

    public static ModuleProvider getModuleProvider() {
        if (moduleProvider == null) {
            moduleProvider = new ModuleProviderImpl();
        }
        return moduleProvider;
    }

    public static void setModuleProvider(ModuleProvider moduleProvider2) {
        moduleProvider = moduleProvider2;
    }

    private static void validateMapLibre() {
        if (INSTANCE == null) {
            throw new MapLibreConfigurationException();
        }
    }

    static boolean isApiKeyValid(String str) {
        if (str == null) {
            return false;
        }
        return !str.trim().toLowerCase(MapLibreConstants.MAPLIBRE_LOCALE).isEmpty();
    }

    public static void throwIfApiKeyInvalid(String str) {
        if (!isApiKeyValid(str)) {
            throw new MapLibreConfigurationException("A valid API key is required, currently provided key is: " + str);
        }
    }

    public static boolean hasInstance() {
        return INSTANCE != null;
    }

    private static AssetManager getAssetManager() {
        return getApplicationContext().getResources().getAssets();
    }
}
