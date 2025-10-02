package com.maplibre.rctmln.components.styles.sources;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import java.net.URL;
import org.maplibre.android.geometry.LatLngQuad;
import org.maplibre.android.style.sources.ImageSource;

/* loaded from: classes3.dex */
public class RCTMLNImageSource extends RCTSource<ImageSource> {
    public static final String LOG_TAG = "RCTMLNImageSource";
    private LatLngQuad mCoordQuad;
    private int mResourceId;
    private URL mURL;

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public void onPress(RCTSource.OnPressEvent onPressEvent) {
    }

    public RCTMLNImageSource(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public ImageSource makeSource() {
        if (this.mURL == null) {
            return new ImageSource(this.mID, this.mCoordQuad, this.mResourceId);
        }
        return new ImageSource(this.mID, this.mCoordQuad, this.mURL);
    }

    public void setURL(String str) {
        try {
            if (Uri.parse(str).getScheme() == null) {
                this.mResourceId = ResourceDrawableIdHelper.getInstance().getResourceDrawableId(getContext(), str);
                if (this.mSource != 0) {
                    ((ImageSource) this.mSource).setImage(this.mResourceId);
                }
            } else {
                this.mURL = new URL(str);
                if (this.mSource != 0) {
                    ((ImageSource) this.mSource).setUri(this.mURL.toURI());
                }
            }
        } catch (Exception e) {
            Log.w("RCTMLNImageSource", e.getLocalizedMessage());
        }
    }

    public void setCoordinates(LatLngQuad latLngQuad) {
        this.mCoordQuad = latLngQuad;
        try {
            if (this.mSource != 0) {
                ((ImageSource) this.mSource).setCoordinates(this.mCoordQuad);
            }
        } catch (Exception e) {
            Log.w("RCTMLNImageSource", e.getLocalizedMessage());
        }
    }
}
