package org.maplibre.android.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.content.res.ResourcesCompat;
import java.util.Arrays;
import org.maplibre.android.R;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.utils.BitmapUtils;
import org.maplibre.android.utils.FontUtils;

/* loaded from: classes2.dex */
public class MapLibreMapOptions implements Parcelable {
    public static final Parcelable.Creator<MapLibreMapOptions> CREATOR = new Parcelable.Creator<MapLibreMapOptions>() { // from class: org.maplibre.android.maps.MapLibreMapOptions.1
        @Override // android.os.Parcelable.Creator
        public MapLibreMapOptions createFromParcel(Parcel parcel) {
            return new MapLibreMapOptions(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public MapLibreMapOptions[] newArray(int i) {
            return new MapLibreMapOptions[i];
        }
    };
    private static final float FOUR_DP = 4.0f;
    private static final int LIGHT_GRAY = -988703;
    private static final float NINETY_TWO_DP = 92.0f;
    private static final int UNDEFINED_COLOR = -1;
    private String apiBaseUri;
    private boolean attributionEnabled;
    private int attributionGravity;
    private int[] attributionMargins;
    private int attributionTintColor;
    private CameraPosition cameraPosition;
    private boolean compassEnabled;
    private int compassGravity;
    private Drawable compassImage;
    private int[] compassMargins;
    private boolean crossSourceCollisions;
    private boolean debugActive;
    private boolean doubleTapGesturesEnabled;
    private boolean fadeCompassFacingNorth;
    private int foregroundLoadColor;
    private boolean horizontalScrollGesturesEnabled;
    private String[] localIdeographFontFamilies;
    private String localIdeographFontFamily;
    private boolean localIdeographFontFamilyEnabled;
    private boolean logoEnabled;
    private int logoGravity;
    private int[] logoMargins;
    private double maxPitch;
    private double maxZoom;
    private double minPitch;
    private double minZoom;
    private float pixelRatio;
    private int prefetchZoomDelta;
    private boolean prefetchesTiles;
    private boolean quickZoomGesturesEnabled;
    private boolean rotateGesturesEnabled;
    private boolean scrollGesturesEnabled;
    private boolean textureMode;
    private boolean tiltGesturesEnabled;
    private boolean translucentTextureSurface;
    private boolean zMediaOverlay;
    private boolean zoomGesturesEnabled;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public MapLibreMapOptions() {
        this.compassEnabled = true;
        this.fadeCompassFacingNorth = true;
        this.compassGravity = 8388661;
        this.logoEnabled = true;
        this.logoGravity = 8388691;
        this.attributionTintColor = -1;
        this.attributionEnabled = true;
        this.attributionGravity = 8388691;
        this.minZoom = 0.0d;
        this.maxZoom = 25.5d;
        this.minPitch = 0.0d;
        this.maxPitch = 60.0d;
        this.rotateGesturesEnabled = true;
        this.scrollGesturesEnabled = true;
        this.horizontalScrollGesturesEnabled = true;
        this.tiltGesturesEnabled = true;
        this.zoomGesturesEnabled = true;
        this.doubleTapGesturesEnabled = true;
        this.quickZoomGesturesEnabled = true;
        this.prefetchesTiles = true;
        this.prefetchZoomDelta = 4;
        this.zMediaOverlay = false;
        this.localIdeographFontFamilyEnabled = true;
        this.crossSourceCollisions = true;
    }

    private MapLibreMapOptions(Parcel parcel) {
        this.compassEnabled = true;
        this.fadeCompassFacingNorth = true;
        this.compassGravity = 8388661;
        this.logoEnabled = true;
        this.logoGravity = 8388691;
        this.attributionTintColor = -1;
        this.attributionEnabled = true;
        this.attributionGravity = 8388691;
        this.minZoom = 0.0d;
        this.maxZoom = 25.5d;
        this.minPitch = 0.0d;
        this.maxPitch = 60.0d;
        this.rotateGesturesEnabled = true;
        this.scrollGesturesEnabled = true;
        this.horizontalScrollGesturesEnabled = true;
        this.tiltGesturesEnabled = true;
        this.zoomGesturesEnabled = true;
        this.doubleTapGesturesEnabled = true;
        this.quickZoomGesturesEnabled = true;
        this.prefetchesTiles = true;
        this.prefetchZoomDelta = 4;
        this.zMediaOverlay = false;
        this.localIdeographFontFamilyEnabled = true;
        this.crossSourceCollisions = true;
        this.cameraPosition = (CameraPosition) parcel.readParcelable(CameraPosition.class.getClassLoader());
        this.debugActive = parcel.readByte() != 0;
        this.compassEnabled = parcel.readByte() != 0;
        this.compassGravity = parcel.readInt();
        this.compassMargins = parcel.createIntArray();
        this.fadeCompassFacingNorth = parcel.readByte() != 0;
        Bitmap bitmap = (Bitmap) parcel.readParcelable(getClass().getClassLoader());
        if (bitmap != null) {
            this.compassImage = new BitmapDrawable(bitmap);
        }
        this.logoEnabled = parcel.readByte() != 0;
        this.logoGravity = parcel.readInt();
        this.logoMargins = parcel.createIntArray();
        this.attributionEnabled = parcel.readByte() != 0;
        this.attributionGravity = parcel.readInt();
        this.attributionMargins = parcel.createIntArray();
        this.attributionTintColor = parcel.readInt();
        this.minZoom = parcel.readDouble();
        this.maxZoom = parcel.readDouble();
        this.minPitch = parcel.readDouble();
        this.maxPitch = parcel.readDouble();
        this.rotateGesturesEnabled = parcel.readByte() != 0;
        this.scrollGesturesEnabled = parcel.readByte() != 0;
        this.horizontalScrollGesturesEnabled = parcel.readByte() != 0;
        this.tiltGesturesEnabled = parcel.readByte() != 0;
        this.zoomGesturesEnabled = parcel.readByte() != 0;
        this.doubleTapGesturesEnabled = parcel.readByte() != 0;
        this.quickZoomGesturesEnabled = parcel.readByte() != 0;
        this.apiBaseUri = parcel.readString();
        this.textureMode = parcel.readByte() != 0;
        this.translucentTextureSurface = parcel.readByte() != 0;
        this.prefetchesTiles = parcel.readByte() != 0;
        this.prefetchZoomDelta = parcel.readInt();
        this.zMediaOverlay = parcel.readByte() != 0;
        this.localIdeographFontFamilyEnabled = parcel.readByte() != 0;
        this.localIdeographFontFamily = parcel.readString();
        this.localIdeographFontFamilies = parcel.createStringArray();
        this.pixelRatio = parcel.readFloat();
        this.foregroundLoadColor = parcel.readInt();
        this.crossSourceCollisions = parcel.readByte() != 0;
    }

    public static MapLibreMapOptions createFromAttributes(Context context) {
        return createFromAttributes(context, null);
    }

    public static MapLibreMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        return createFromAttributes(new MapLibreMapOptions(), context, context.obtainStyledAttributes(attributeSet, R.styleable.maplibre_MapView, 0, 0));
    }

    static MapLibreMapOptions createFromAttributes(MapLibreMapOptions mapLibreMapOptions, Context context, TypedArray typedArray) {
        float f = context.getResources().getDisplayMetrics().density;
        try {
            mapLibreMapOptions.camera(new CameraPosition.Builder(typedArray).build());
            mapLibreMapOptions.apiBaseUrl(typedArray.getString(R.styleable.maplibre_MapView_maplibre_apiBaseUrl));
            String string = typedArray.getString(R.styleable.maplibre_MapView_maplibre_apiBaseUri);
            if (!TextUtils.isEmpty(string)) {
                mapLibreMapOptions.apiBaseUri(string);
            }
            mapLibreMapOptions.zoomGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiZoomGestures, true));
            mapLibreMapOptions.scrollGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiScrollGestures, true));
            mapLibreMapOptions.horizontalScrollGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiHorizontalScrollGestures, true));
            mapLibreMapOptions.rotateGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiRotateGestures, true));
            mapLibreMapOptions.tiltGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiTiltGestures, true));
            mapLibreMapOptions.doubleTapGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiDoubleTapGestures, true));
            mapLibreMapOptions.quickZoomGesturesEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiQuickZoomGestures, true));
            mapLibreMapOptions.maxZoomPreference(typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraZoomMax, 25.5f));
            mapLibreMapOptions.minZoomPreference(typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraZoomMin, 0.0f));
            mapLibreMapOptions.maxPitchPreference(typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraPitchMax, 60.0f));
            mapLibreMapOptions.minPitchPreference(typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraPitchMin, 0.0f));
            mapLibreMapOptions.compassEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiCompass, true));
            mapLibreMapOptions.compassGravity(typedArray.getInt(R.styleable.maplibre_MapView_maplibre_uiCompassGravity, 8388661));
            int i = R.styleable.maplibre_MapView_maplibre_uiCompassMarginLeft;
            float f2 = FOUR_DP * f;
            mapLibreMapOptions.compassMargins(new int[]{(int) typedArray.getDimension(i, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiCompassMarginTop, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiCompassMarginRight, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiCompassMarginBottom, f2)});
            mapLibreMapOptions.compassFadesWhenFacingNorth(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiCompassFadeFacingNorth, true));
            Drawable drawable = typedArray.getDrawable(R.styleable.maplibre_MapView_maplibre_uiCompassDrawable);
            if (drawable == null) {
                drawable = ResourcesCompat.getDrawable(context.getResources(), R.drawable.maplibre_compass_icon, null);
            }
            mapLibreMapOptions.compassImage(drawable);
            mapLibreMapOptions.logoEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiLogo, true));
            mapLibreMapOptions.logoGravity(typedArray.getInt(R.styleable.maplibre_MapView_maplibre_uiLogoGravity, 8388691));
            mapLibreMapOptions.logoMargins(new int[]{(int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiLogoMarginLeft, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiLogoMarginTop, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiLogoMarginRight, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiLogoMarginBottom, f2)});
            mapLibreMapOptions.attributionTintColor(typedArray.getColor(R.styleable.maplibre_MapView_maplibre_uiAttributionTintColor, -1));
            mapLibreMapOptions.attributionEnabled(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_uiAttribution, true));
            mapLibreMapOptions.attributionGravity(typedArray.getInt(R.styleable.maplibre_MapView_maplibre_uiAttributionGravity, 8388691));
            mapLibreMapOptions.attributionMargins(new int[]{(int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiAttributionMarginLeft, f * NINETY_TWO_DP), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiAttributionMarginTop, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiAttributionMarginRight, f2), (int) typedArray.getDimension(R.styleable.maplibre_MapView_maplibre_uiAttributionMarginBottom, f2)});
            mapLibreMapOptions.textureMode(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_renderTextureMode, false));
            mapLibreMapOptions.translucentTextureSurface(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_renderTextureTranslucentSurface, false));
            mapLibreMapOptions.setPrefetchesTiles(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_enableTilePrefetch, true));
            mapLibreMapOptions.setPrefetchZoomDelta(typedArray.getInt(R.styleable.maplibre_MapView_maplibre_prefetchZoomDelta, 4));
            mapLibreMapOptions.renderSurfaceOnTop(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_enableZMediaOverlay, false));
            mapLibreMapOptions.localIdeographFontFamilyEnabled = typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_localIdeographEnabled, true);
            int resourceId = typedArray.getResourceId(R.styleable.maplibre_MapView_maplibre_localIdeographFontFamilies, 0);
            if (resourceId != 0) {
                mapLibreMapOptions.localIdeographFontFamily(context.getResources().getStringArray(resourceId));
            } else {
                String string2 = typedArray.getString(R.styleable.maplibre_MapView_maplibre_localIdeographFontFamily);
                if (string2 == null) {
                    string2 = MapLibreConstants.DEFAULT_FONT;
                }
                mapLibreMapOptions.localIdeographFontFamily(string2);
            }
            mapLibreMapOptions.pixelRatio(typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_pixelRatio, 0.0f));
            mapLibreMapOptions.foregroundLoadColor(typedArray.getInt(R.styleable.maplibre_MapView_maplibre_foregroundLoadColor, LIGHT_GRAY));
            mapLibreMapOptions.crossSourceCollisions(typedArray.getBoolean(R.styleable.maplibre_MapView_maplibre_cross_source_collisions, true));
            return mapLibreMapOptions;
        } finally {
            typedArray.recycle();
        }
    }

    @Deprecated
    public MapLibreMapOptions apiBaseUrl(String str) {
        this.apiBaseUri = str;
        return this;
    }

    public MapLibreMapOptions apiBaseUri(String str) {
        this.apiBaseUri = str;
        return this;
    }

    public MapLibreMapOptions camera(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
        return this;
    }

    public MapLibreMapOptions debugActive(boolean z) {
        this.debugActive = z;
        return this;
    }

    public MapLibreMapOptions minZoomPreference(double d) {
        this.minZoom = d;
        return this;
    }

    public MapLibreMapOptions maxZoomPreference(double d) {
        this.maxZoom = d;
        return this;
    }

    public MapLibreMapOptions minPitchPreference(double d) {
        this.minPitch = d;
        return this;
    }

    public MapLibreMapOptions maxPitchPreference(double d) {
        this.maxPitch = d;
        return this;
    }

    public MapLibreMapOptions compassEnabled(boolean z) {
        this.compassEnabled = z;
        return this;
    }

    public MapLibreMapOptions compassGravity(int i) {
        this.compassGravity = i;
        return this;
    }

    public MapLibreMapOptions compassMargins(int[] iArr) {
        this.compassMargins = iArr;
        return this;
    }

    public MapLibreMapOptions compassFadesWhenFacingNorth(boolean z) {
        this.fadeCompassFacingNorth = z;
        return this;
    }

    public MapLibreMapOptions compassImage(Drawable drawable) {
        this.compassImage = drawable;
        return this;
    }

    public MapLibreMapOptions logoEnabled(boolean z) {
        this.logoEnabled = z;
        return this;
    }

    public MapLibreMapOptions logoGravity(int i) {
        this.logoGravity = i;
        return this;
    }

    public MapLibreMapOptions logoMargins(int[] iArr) {
        this.logoMargins = iArr;
        return this;
    }

    public MapLibreMapOptions attributionEnabled(boolean z) {
        this.attributionEnabled = z;
        return this;
    }

    public MapLibreMapOptions attributionGravity(int i) {
        this.attributionGravity = i;
        return this;
    }

    public MapLibreMapOptions attributionMargins(int[] iArr) {
        this.attributionMargins = iArr;
        return this;
    }

    public MapLibreMapOptions attributionTintColor(int i) {
        this.attributionTintColor = i;
        return this;
    }

    public MapLibreMapOptions rotateGesturesEnabled(boolean z) {
        this.rotateGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions scrollGesturesEnabled(boolean z) {
        this.scrollGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions horizontalScrollGesturesEnabled(boolean z) {
        this.horizontalScrollGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions tiltGesturesEnabled(boolean z) {
        this.tiltGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions zoomGesturesEnabled(boolean z) {
        this.zoomGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions doubleTapGesturesEnabled(boolean z) {
        this.doubleTapGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions quickZoomGesturesEnabled(boolean z) {
        this.quickZoomGesturesEnabled = z;
        return this;
    }

    public MapLibreMapOptions textureMode(boolean z) {
        this.textureMode = z;
        return this;
    }

    public MapLibreMapOptions translucentTextureSurface(boolean z) {
        this.translucentTextureSurface = z;
        return this;
    }

    public MapLibreMapOptions foregroundLoadColor(int i) {
        this.foregroundLoadColor = i;
        return this;
    }

    @Deprecated
    public MapLibreMapOptions setPrefetchesTiles(boolean z) {
        this.prefetchesTiles = z;
        return this;
    }

    public MapLibreMapOptions setPrefetchZoomDelta(int i) {
        this.prefetchZoomDelta = i;
        return this;
    }

    public MapLibreMapOptions crossSourceCollisions(boolean z) {
        this.crossSourceCollisions = z;
        return this;
    }

    public MapLibreMapOptions localIdeographFontFamilyEnabled(boolean z) {
        this.localIdeographFontFamilyEnabled = z;
        return this;
    }

    public MapLibreMapOptions localIdeographFontFamily(String str) {
        this.localIdeographFontFamily = FontUtils.extractValidFont(str);
        return this;
    }

    public MapLibreMapOptions localIdeographFontFamily(String... strArr) {
        this.localIdeographFontFamily = FontUtils.extractValidFont(strArr);
        return this;
    }

    public MapLibreMapOptions pixelRatio(float f) {
        this.pixelRatio = f;
        return this;
    }

    @Deprecated
    public boolean getPrefetchesTiles() {
        return this.prefetchesTiles;
    }

    public int getPrefetchZoomDelta() {
        return this.prefetchZoomDelta;
    }

    public boolean getCrossSourceCollisions() {
        return this.crossSourceCollisions;
    }

    public void renderSurfaceOnTop(boolean z) {
        this.zMediaOverlay = z;
    }

    public boolean getRenderSurfaceOnTop() {
        return this.zMediaOverlay;
    }

    @Deprecated
    public String getApiBaseUrl() {
        return this.apiBaseUri;
    }

    public String getApiBaseUri() {
        return this.apiBaseUri;
    }

    public CameraPosition getCamera() {
        return this.cameraPosition;
    }

    public double getMinZoomPreference() {
        return this.minZoom;
    }

    public double getMaxZoomPreference() {
        return this.maxZoom;
    }

    public double getMinPitchPreference() {
        return this.minPitch;
    }

    public double getMaxPitchPreference() {
        return this.maxPitch;
    }

    public boolean getCompassEnabled() {
        return this.compassEnabled;
    }

    public int getCompassGravity() {
        return this.compassGravity;
    }

    public int[] getCompassMargins() {
        return this.compassMargins;
    }

    public boolean getCompassFadeFacingNorth() {
        return this.fadeCompassFacingNorth;
    }

    public Drawable getCompassImage() {
        return this.compassImage;
    }

    public boolean getLogoEnabled() {
        return this.logoEnabled;
    }

    public int getLogoGravity() {
        return this.logoGravity;
    }

    public int[] getLogoMargins() {
        return this.logoMargins;
    }

    public boolean getRotateGesturesEnabled() {
        return this.rotateGesturesEnabled;
    }

    public boolean getScrollGesturesEnabled() {
        return this.scrollGesturesEnabled;
    }

    public boolean getHorizontalScrollGesturesEnabled() {
        return this.horizontalScrollGesturesEnabled;
    }

    public boolean getTiltGesturesEnabled() {
        return this.tiltGesturesEnabled;
    }

    public boolean getZoomGesturesEnabled() {
        return this.zoomGesturesEnabled;
    }

    public boolean getDoubleTapGesturesEnabled() {
        return this.doubleTapGesturesEnabled;
    }

    public boolean getQuickZoomGesturesEnabled() {
        return this.quickZoomGesturesEnabled;
    }

    public boolean getAttributionEnabled() {
        return this.attributionEnabled;
    }

    public int getAttributionGravity() {
        return this.attributionGravity;
    }

    public int[] getAttributionMargins() {
        return this.attributionMargins;
    }

    public int getAttributionTintColor() {
        return this.attributionTintColor;
    }

    public boolean getDebugActive() {
        return this.debugActive;
    }

    public boolean getTextureMode() {
        return this.textureMode;
    }

    public boolean getTranslucentTextureSurface() {
        return this.translucentTextureSurface;
    }

    public int getForegroundLoadColor() {
        return this.foregroundLoadColor;
    }

    public String getLocalIdeographFontFamily() {
        if (this.localIdeographFontFamilyEnabled) {
            return this.localIdeographFontFamily;
        }
        return null;
    }

    public boolean isLocalIdeographFontFamilyEnabled() {
        return this.localIdeographFontFamilyEnabled;
    }

    public float getPixelRatio() {
        return this.pixelRatio;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.cameraPosition, i);
        parcel.writeByte(this.debugActive ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.compassEnabled ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.compassGravity);
        parcel.writeIntArray(this.compassMargins);
        parcel.writeByte(this.fadeCompassFacingNorth ? (byte) 1 : (byte) 0);
        Drawable drawable = this.compassImage;
        parcel.writeParcelable(drawable != null ? BitmapUtils.getBitmapFromDrawable(drawable) : null, i);
        parcel.writeByte(this.logoEnabled ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.logoGravity);
        parcel.writeIntArray(this.logoMargins);
        parcel.writeByte(this.attributionEnabled ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.attributionGravity);
        parcel.writeIntArray(this.attributionMargins);
        parcel.writeInt(this.attributionTintColor);
        parcel.writeDouble(this.minZoom);
        parcel.writeDouble(this.maxZoom);
        parcel.writeDouble(this.minPitch);
        parcel.writeDouble(this.maxPitch);
        parcel.writeByte(this.rotateGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.scrollGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.horizontalScrollGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.tiltGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.zoomGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.doubleTapGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.quickZoomGesturesEnabled ? (byte) 1 : (byte) 0);
        parcel.writeString(this.apiBaseUri);
        parcel.writeByte(this.textureMode ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.translucentTextureSurface ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.prefetchesTiles ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.prefetchZoomDelta);
        parcel.writeByte(this.zMediaOverlay ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.localIdeographFontFamilyEnabled ? (byte) 1 : (byte) 0);
        parcel.writeString(this.localIdeographFontFamily);
        parcel.writeStringArray(this.localIdeographFontFamilies);
        parcel.writeFloat(this.pixelRatio);
        parcel.writeInt(this.foregroundLoadColor);
        parcel.writeByte(this.crossSourceCollisions ? (byte) 1 : (byte) 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            MapLibreMapOptions mapLibreMapOptions = (MapLibreMapOptions) obj;
            if (this.debugActive != mapLibreMapOptions.debugActive || this.compassEnabled != mapLibreMapOptions.compassEnabled || this.fadeCompassFacingNorth != mapLibreMapOptions.fadeCompassFacingNorth) {
                return false;
            }
            Drawable drawable = this.compassImage;
            if (drawable == null ? mapLibreMapOptions.compassImage != null : !drawable.equals(mapLibreMapOptions.compassImage)) {
                return false;
            }
            if (this.compassGravity != mapLibreMapOptions.compassGravity || this.logoEnabled != mapLibreMapOptions.logoEnabled || this.logoGravity != mapLibreMapOptions.logoGravity || this.attributionTintColor != mapLibreMapOptions.attributionTintColor || this.attributionEnabled != mapLibreMapOptions.attributionEnabled || this.attributionGravity != mapLibreMapOptions.attributionGravity || Double.compare(mapLibreMapOptions.minZoom, this.minZoom) != 0 || Double.compare(mapLibreMapOptions.maxZoom, this.maxZoom) != 0 || Double.compare(mapLibreMapOptions.minPitch, this.minPitch) != 0 || Double.compare(mapLibreMapOptions.maxPitch, this.maxPitch) != 0 || this.rotateGesturesEnabled != mapLibreMapOptions.rotateGesturesEnabled || this.scrollGesturesEnabled != mapLibreMapOptions.scrollGesturesEnabled || this.horizontalScrollGesturesEnabled != mapLibreMapOptions.horizontalScrollGesturesEnabled || this.tiltGesturesEnabled != mapLibreMapOptions.tiltGesturesEnabled || this.zoomGesturesEnabled != mapLibreMapOptions.zoomGesturesEnabled || this.doubleTapGesturesEnabled != mapLibreMapOptions.doubleTapGesturesEnabled || this.quickZoomGesturesEnabled != mapLibreMapOptions.quickZoomGesturesEnabled) {
                return false;
            }
            CameraPosition cameraPosition = this.cameraPosition;
            if (cameraPosition == null ? mapLibreMapOptions.cameraPosition != null : !cameraPosition.equals(mapLibreMapOptions.cameraPosition)) {
                return false;
            }
            if (!Arrays.equals(this.compassMargins, mapLibreMapOptions.compassMargins) || !Arrays.equals(this.logoMargins, mapLibreMapOptions.logoMargins) || !Arrays.equals(this.attributionMargins, mapLibreMapOptions.attributionMargins)) {
                return false;
            }
            String str = this.apiBaseUri;
            if (str == null ? mapLibreMapOptions.apiBaseUri != null : !str.equals(mapLibreMapOptions.apiBaseUri)) {
                return false;
            }
            if (this.prefetchesTiles != mapLibreMapOptions.prefetchesTiles || this.prefetchZoomDelta != mapLibreMapOptions.prefetchZoomDelta || this.zMediaOverlay != mapLibreMapOptions.zMediaOverlay || this.localIdeographFontFamilyEnabled != mapLibreMapOptions.localIdeographFontFamilyEnabled || !this.localIdeographFontFamily.equals(mapLibreMapOptions.localIdeographFontFamily) || !Arrays.equals(this.localIdeographFontFamilies, mapLibreMapOptions.localIdeographFontFamilies) || this.pixelRatio != mapLibreMapOptions.pixelRatio) {
                return false;
            }
            boolean z = mapLibreMapOptions.crossSourceCollisions;
        }
        return false;
    }

    public int hashCode() {
        CameraPosition cameraPosition = this.cameraPosition;
        int iHashCode = (((((((((cameraPosition != null ? cameraPosition.hashCode() : 0) * 31) + (this.debugActive ? 1 : 0)) * 31) + (this.compassEnabled ? 1 : 0)) * 31) + (this.fadeCompassFacingNorth ? 1 : 0)) * 31) + this.compassGravity) * 31;
        Drawable drawable = this.compassImage;
        int iHashCode2 = ((((((((((((((((iHashCode + (drawable != null ? drawable.hashCode() : 0)) * 31) + Arrays.hashCode(this.compassMargins)) * 31) + (this.logoEnabled ? 1 : 0)) * 31) + this.logoGravity) * 31) + Arrays.hashCode(this.logoMargins)) * 31) + this.attributionTintColor) * 31) + (this.attributionEnabled ? 1 : 0)) * 31) + this.attributionGravity) * 31) + Arrays.hashCode(this.attributionMargins);
        long jDoubleToLongBits = Double.doubleToLongBits(this.minZoom);
        int i = (iHashCode2 * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.maxZoom);
        int i2 = (i * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.minPitch);
        int i3 = (i2 * 31) + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)));
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.maxPitch);
        int i4 = ((((((((((((((((i3 * 31) + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)))) * 31) + (this.rotateGesturesEnabled ? 1 : 0)) * 31) + (this.scrollGesturesEnabled ? 1 : 0)) * 31) + (this.horizontalScrollGesturesEnabled ? 1 : 0)) * 31) + (this.tiltGesturesEnabled ? 1 : 0)) * 31) + (this.zoomGesturesEnabled ? 1 : 0)) * 31) + (this.doubleTapGesturesEnabled ? 1 : 0)) * 31) + (this.quickZoomGesturesEnabled ? 1 : 0)) * 31;
        String str = this.apiBaseUri;
        int iHashCode3 = (((((((((((((i4 + (str != null ? str.hashCode() : 0)) * 31) + (this.textureMode ? 1 : 0)) * 31) + (this.translucentTextureSurface ? 1 : 0)) * 31) + (this.prefetchesTiles ? 1 : 0)) * 31) + this.prefetchZoomDelta) * 31) + (this.zMediaOverlay ? 1 : 0)) * 31) + (this.localIdeographFontFamilyEnabled ? 1 : 0)) * 31;
        String str2 = this.localIdeographFontFamily;
        return ((((((iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31) + Arrays.hashCode(this.localIdeographFontFamilies)) * 31) + ((int) this.pixelRatio)) * 31) + (this.crossSourceCollisions ? 1 : 0);
    }
}
