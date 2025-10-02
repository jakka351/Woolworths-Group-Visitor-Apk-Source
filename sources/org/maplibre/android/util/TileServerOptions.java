package org.maplibre.android.util;

import android.os.Parcel;
import android.os.Parcelable;
import org.maplibre.android.WellKnownTileServer;
import org.maplibre.android.exceptions.MapLibreConfigurationException;

/* loaded from: classes2.dex */
public class TileServerOptions implements Parcelable {
    public static final Parcelable.Creator<TileServerOptions> CREATOR = new Parcelable.Creator<TileServerOptions>() { // from class: org.maplibre.android.util.TileServerOptions.1
        @Override // android.os.Parcelable.Creator
        public TileServerOptions createFromParcel(Parcel parcel) {
            return new TileServerOptions(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public TileServerOptions[] newArray(int i) {
            return new TileServerOptions[i];
        }
    };
    private String apiKeyParameterName;
    private boolean apiKeyRequired;
    private String baseURL;
    private String defaultStyle;
    private DefaultStyle[] defaultStyles;
    private String glyphsDomainName;
    private String glyphsTemplate;
    private String glyphsVersionPrefix;
    private String sourceDomainName;
    private String sourceTemplate;
    private String sourceVersionPrefix;
    private String spritesDomainName;
    private String spritesTemplate;
    private String spritesVersionPrefix;
    private String styleDomainName;
    private String styleTemplate;
    private String styleVersionPrefix;
    private String tileDomainName;
    private String tileTemplate;
    private String tileVersionPrefix;
    private String uriSchemeAlias;

    private static native TileServerOptions defaultConfiguration();

    private static native TileServerOptions mapLibreConfiguration();

    private static native TileServerOptions mapTilerConfiguration();

    private static native TileServerOptions mapboxConfiguration();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TileServerOptions(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, boolean z, String str19, DefaultStyle[] defaultStyleArr) {
        setBaseURL(str);
        setUriSchemeAlias(str2);
        setSourceTemplate(str3);
        setSourceDomainName(str4);
        setSourceVersionPrefix(str5);
        setStyleTemplate(str6);
        setStyleDomainName(str7);
        setStyleVersionPrefix(str8);
        setSpritesTemplate(str9);
        setSpritesDomainName(str10);
        setSpritesVersionPrefix(str11);
        setGlyphsTemplate(str12);
        setGlyphsDomainName(str13);
        setGlyphsVersionPrefix(str14);
        setTileTemplate(str15);
        setTileDomainName(str16);
        setTileVersionPrefix(str17);
        setApiKeyParameterName(str18);
        setDefaultStyles(defaultStyleArr);
        setDefaultStyle(str19);
        setApiKeyRequired(z);
    }

    public void setBaseURL(String str) {
        this.baseURL = str;
    }

    public String getBaseURL() {
        return this.baseURL;
    }

    public void setUriSchemeAlias(String str) {
        this.uriSchemeAlias = str;
    }

    public String getUriSchemeAlias() {
        return this.uriSchemeAlias;
    }

    public void setSourceTemplate(String str) {
        this.sourceTemplate = str;
    }

    public String getSourceTemplate() {
        return this.sourceTemplate;
    }

    public void setSourceDomainName(String str) {
        this.sourceDomainName = str;
    }

    public String getSourceDomainName() {
        return this.sourceDomainName;
    }

    public void setSourceVersionPrefix(String str) {
        this.sourceVersionPrefix = str;
    }

    public String getSourceVersionPrefix() {
        return this.sourceVersionPrefix;
    }

    public void setStyleTemplate(String str) {
        this.styleTemplate = str;
    }

    public String getStyleTemplate() {
        return this.styleTemplate;
    }

    public void setStyleDomainName(String str) {
        this.styleDomainName = str;
    }

    public String getStyleDomainName() {
        return this.styleDomainName;
    }

    public void setStyleVersionPrefix(String str) {
        this.styleVersionPrefix = str;
    }

    public String getStyleVersionPrefix() {
        return this.styleVersionPrefix;
    }

    public void setSpritesTemplate(String str) {
        this.spritesTemplate = str;
    }

    public String getSpritesTemplate() {
        return this.spritesTemplate;
    }

    public void setSpritesDomainName(String str) {
        this.spritesDomainName = str;
    }

    public String getSpritesDomainName() {
        return this.spritesDomainName;
    }

    public void setSpritesVersionPrefix(String str) {
        this.spritesVersionPrefix = str;
    }

    public String getSpritesVersionPrefix() {
        return this.spritesVersionPrefix;
    }

    public void setGlyphsTemplate(String str) {
        this.glyphsTemplate = str;
    }

    public String getGlyphsTemplate() {
        return this.glyphsTemplate;
    }

    public void setGlyphsDomainName(String str) {
        this.glyphsDomainName = str;
    }

    public String getGlyphsDomainName() {
        return this.glyphsDomainName;
    }

    public void setGlyphsVersionPrefix(String str) {
        this.glyphsVersionPrefix = str;
    }

    public String getGlyphsVersionPrefix() {
        return this.glyphsVersionPrefix;
    }

    public void setTileTemplate(String str) {
        this.tileTemplate = str;
    }

    public String getTileTemplate() {
        return this.tileTemplate;
    }

    public void setTileDomainName(String str) {
        this.tileDomainName = str;
    }

    public String getTileDomainName() {
        return this.tileDomainName;
    }

    public void setTileVersionPrefix(String str) {
        this.tileVersionPrefix = str;
    }

    public String getTileVersionPrefix() {
        return this.tileVersionPrefix;
    }

    public void setApiKeyParameterName(String str) {
        this.apiKeyParameterName = str;
    }

    public String getApiKeyParameterName() {
        return this.apiKeyParameterName;
    }

    public void setApiKeyRequired(boolean z) {
        this.apiKeyRequired = z;
    }

    public boolean getApiKeyRequired() {
        return this.apiKeyRequired;
    }

    public void setDefaultStyles(DefaultStyle[] defaultStyleArr) {
        this.defaultStyles = defaultStyleArr;
    }

    public DefaultStyle[] getDefaultStyles() {
        return this.defaultStyles;
    }

    public void setDefaultStyle(String str) {
        this.defaultStyle = str;
    }

    public String getDefaultStyle() {
        return this.defaultStyle;
    }

    protected TileServerOptions(Parcel parcel) {
        setBaseURL(parcel.readString());
        setUriSchemeAlias(parcel.readString());
        setSourceTemplate(parcel.readString());
        setSourceDomainName(parcel.readString());
        setSourceVersionPrefix(parcel.readString());
        setStyleTemplate(parcel.readString());
        setStyleDomainName(parcel.readString());
        setStyleVersionPrefix(parcel.readString());
        setSpritesTemplate(parcel.readString());
        setSpritesDomainName(parcel.readString());
        setSpritesVersionPrefix(parcel.readString());
        setGlyphsTemplate(parcel.readString());
        setGlyphsDomainName(parcel.readString());
        setGlyphsVersionPrefix(parcel.readString());
        setTileTemplate(parcel.readString());
        setTileDomainName(parcel.readString());
        setTileVersionPrefix(parcel.readString());
        setApiKeyParameterName(parcel.readString());
        setApiKeyRequired(parcel.readByte() != 0);
        setDefaultStyle(parcel.readString());
        parcel.createTypedArray(DefaultStyle.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.baseURL);
        parcel.writeString(this.uriSchemeAlias);
        parcel.writeString(this.sourceTemplate);
        parcel.writeString(this.sourceDomainName);
        parcel.writeString(this.sourceVersionPrefix);
        parcel.writeString(this.styleTemplate);
        parcel.writeString(this.styleDomainName);
        parcel.writeString(this.styleVersionPrefix);
        parcel.writeString(this.spritesTemplate);
        parcel.writeString(this.spritesDomainName);
        parcel.writeString(this.spritesVersionPrefix);
        parcel.writeString(this.glyphsTemplate);
        parcel.writeString(this.glyphsDomainName);
        parcel.writeString(this.glyphsVersionPrefix);
        parcel.writeString(this.tileTemplate);
        parcel.writeString(this.tileDomainName);
        parcel.writeString(this.tileVersionPrefix);
        parcel.writeString(this.apiKeyParameterName);
        parcel.writeByte(this.apiKeyRequired ? (byte) 1 : (byte) 0);
        parcel.writeString(this.defaultStyle);
        parcel.writeTypedArray(this.defaultStyles, 0);
    }

    /* renamed from: org.maplibre.android.util.TileServerOptions$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$maplibre$android$WellKnownTileServer;

        static {
            int[] iArr = new int[WellKnownTileServer.values().length];
            $SwitchMap$org$maplibre$android$WellKnownTileServer = iArr;
            try {
                iArr[WellKnownTileServer.Mapbox.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$maplibre$android$WellKnownTileServer[WellKnownTileServer.MapTiler.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$maplibre$android$WellKnownTileServer[WellKnownTileServer.MapLibre.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static TileServerOptions get(WellKnownTileServer wellKnownTileServer) {
        int i = AnonymousClass2.$SwitchMap$org$maplibre$android$WellKnownTileServer[wellKnownTileServer.ordinal()];
        if (i == 1) {
            return mapboxConfiguration();
        }
        if (i == 2) {
            return mapTilerConfiguration();
        }
        if (i == 3) {
            return mapLibreConfiguration();
        }
        throw new MapLibreConfigurationException("Unknown tile server");
    }
}
