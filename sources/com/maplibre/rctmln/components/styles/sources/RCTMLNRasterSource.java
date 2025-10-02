package com.maplibre.rctmln.components.styles.sources;

import android.content.Context;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import org.maplibre.android.style.sources.RasterSource;

/* loaded from: classes3.dex */
public class RCTMLNRasterSource extends RCTMLNTileSource<RasterSource> {
    private Integer mTileSize;

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public boolean hasPressListener() {
        return false;
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public void onPress(RCTSource.OnPressEvent onPressEvent) {
    }

    public RCTMLNRasterSource(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public RasterSource makeSource() {
        String url = getURL();
        Integer num = this.mTileSize;
        int iIntValue = num == null ? 512 : num.intValue();
        if (url != null) {
            return new RasterSource(this.mID, url, iIntValue);
        }
        return new RasterSource(this.mID, buildTileset(), iIntValue);
    }

    public void setTileSize(int i) {
        this.mTileSize = Integer.valueOf(i);
    }
}
