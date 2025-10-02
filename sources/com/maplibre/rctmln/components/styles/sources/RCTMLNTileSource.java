package com.maplibre.rctmln.components.styles.sources;

import android.content.Context;
import java.util.Collection;
import org.maplibre.android.style.sources.Source;
import org.maplibre.android.style.sources.TileSet;

/* loaded from: classes3.dex */
public abstract class RCTMLNTileSource<T extends Source> extends RCTSource<T> {
    static final String TILE_SPEC_VERSION = "2.1.0";
    private String mAttribution;
    private boolean mIsTmsSource;
    private Integer mMaxZoomLevel;
    private Integer mMinZoomLevel;
    private Collection<String> mTileUrlTemplates;
    private String mURL;

    public RCTMLNTileSource(Context context) {
        super(context);
    }

    public String getURL() {
        return this.mURL;
    }

    public void setURL(String str) {
        this.mURL = str;
    }

    public String getAttribution() {
        return this.mAttribution;
    }

    public void setAttribution(String str) {
        this.mAttribution = str;
    }

    public Integer getMinZoomLevel() {
        return this.mMinZoomLevel;
    }

    public void setMinZoomLevel(Integer num) {
        this.mMinZoomLevel = num;
    }

    public Integer getMaxZoomLevel() {
        return this.mMaxZoomLevel;
    }

    public void setMaxZoomLevel(Integer num) {
        this.mMaxZoomLevel = num;
    }

    public boolean getTMS() {
        return this.mIsTmsSource;
    }

    public void setTMS(boolean z) {
        this.mIsTmsSource = z;
    }

    public Collection<String> getTileUrlTemplates() {
        return this.mTileUrlTemplates;
    }

    public void setTileUrlTemplates(Collection<String> collection) {
        this.mTileUrlTemplates = collection;
    }

    public TileSet buildTileset() {
        Collection<String> collection = this.mTileUrlTemplates;
        TileSet tileSet = new TileSet(TILE_SPEC_VERSION, (String[]) collection.toArray(new String[collection.size()]));
        Integer num = this.mMinZoomLevel;
        if (num != null) {
            tileSet.setMinZoom(num.floatValue());
        }
        Integer num2 = this.mMaxZoomLevel;
        if (num2 != null) {
            tileSet.setMaxZoom(num2.floatValue());
        }
        if (this.mIsTmsSource) {
            tileSet.setScheme("tms");
        }
        String str = this.mAttribution;
        if (str != null) {
            tileSet.setAttribution(str);
        }
        return tileSet;
    }
}
