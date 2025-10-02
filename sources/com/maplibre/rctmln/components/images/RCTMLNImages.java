package com.maplibre.rctmln.components.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.core.content.res.ResourcesCompat;
import com.maplibre.rctmln.R;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.events.ImageMissingEvent;
import com.maplibre.rctmln.utils.DownloadMapImageTask;
import com.maplibre.rctmln.utils.ImageEntry;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.utils.BitmapUtils;

/* loaded from: classes3.dex */
public class RCTMLNImages extends AbstractMapFeature {
    private static Bitmap mImagePlaceholder;
    Set<String> mCurrentImages;
    protected String mID;
    private Map<String, ImageEntry> mImages;
    private RCTMLNImagesManager mManager;
    private MapLibreMap mMap;
    private Map<String, BitmapDrawable> mNativeImages;
    private boolean mSendMissingImageEvents;

    public String getID() {
        return this.mID;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public RCTMLNImages(Context context, RCTMLNImagesManager rCTMLNImagesManager) {
        super(context);
        this.mSendMissingImageEvents = false;
        this.mManager = rCTMLNImagesManager;
        this.mCurrentImages = new HashSet();
        this.mImages = new HashMap();
        this.mNativeImages = new HashMap();
        if (mImagePlaceholder == null) {
            mImagePlaceholder = BitmapUtils.getBitmapFromDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.empty_drawable, null));
        }
    }

    public void setImages(List<Map.Entry<String, ImageEntry>> list) {
        HashMap map = new HashMap();
        for (Map.Entry<String, ImageEntry> entry : list) {
            String key = entry.getKey();
            ImageEntry value = entry.getValue();
            if (this.mImages.put(key, value) == null) {
                map.put(key, value);
            }
        }
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null || mapLibreMap.getStyle() == null) {
            return;
        }
        addImagesToStyle(map, this.mMap);
    }

    public void setNativeImages(List<Map.Entry<String, BitmapDrawable>> list) {
        HashMap map = new HashMap();
        for (Map.Entry<String, BitmapDrawable> entry : list) {
            String key = entry.getKey();
            BitmapDrawable value = entry.getValue();
            if (this.mNativeImages.put(key, value) == null) {
                map.put(key, value);
            }
        }
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null || mapLibreMap.getStyle() == null) {
            return;
        }
        addNativeImagesToStyle(map, this.mMap);
    }

    public void setHasOnImageMissing(boolean z) {
        this.mSendMissingImageEvents = z;
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
        removeImages(rCTMLNMapView);
        this.mMap = null;
        this.mNativeImages = new HashMap();
        this.mImages = new HashMap();
        this.mCurrentImages = new HashSet();
    }

    private void removeImages(RCTMLNMapView rCTMLNMapView) {
        rCTMLNMapView.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.images.RCTMLNImages.1
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                if (RCTMLNImages.this.hasImages()) {
                    Iterator it = RCTMLNImages.this.mImages.entrySet().iterator();
                    while (it.hasNext()) {
                        style.removeImage((String) ((Map.Entry) it.next()).getKey());
                    }
                }
                if (RCTMLNImages.this.hasNativeImages()) {
                    Iterator it2 = RCTMLNImages.this.mNativeImages.entrySet().iterator();
                    while (it2.hasNext()) {
                        style.removeImage((String) ((Map.Entry) it2.next()).getKey());
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasImages() {
        Map<String, ImageEntry> map = this.mImages;
        return map != null && map.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasNativeImages() {
        Map<String, BitmapDrawable> map = this.mNativeImages;
        return map != null && map.size() > 0;
    }

    static <K, V> List<Map.Entry<K, V>> entry(K k, V v) {
        return Collections.singletonList(new AbstractMap.SimpleEntry(k, v));
    }

    public boolean addMissingImageToStyle(String str, MapLibreMap mapLibreMap) {
        ImageEntry imageEntry;
        BitmapDrawable bitmapDrawable;
        Map<String, BitmapDrawable> map = this.mNativeImages;
        if (map != null && (bitmapDrawable = map.get(str)) != null) {
            addNativeImages(entry(str, bitmapDrawable), mapLibreMap);
            return true;
        }
        Map<String, ImageEntry> map2 = this.mImages;
        if (map2 == null || (imageEntry = map2.get(str)) == null) {
            return false;
        }
        addRemoteImages(entry(str, imageEntry), mapLibreMap);
        return true;
    }

    public void addImagesToStyle(Map<String, ImageEntry> map, MapLibreMap mapLibreMap) {
        if (map != null) {
            addRemoteImages(new ArrayList(map.entrySet()), mapLibreMap);
        }
    }

    public void addNativeImagesToStyle(Map<String, BitmapDrawable> map, MapLibreMap mapLibreMap) {
        if (map != null) {
            addNativeImages(new ArrayList(map.entrySet()), mapLibreMap);
        }
    }

    public void sendImageMissingEvent(String str, MapLibreMap mapLibreMap) {
        if (this.mSendMissingImageEvents) {
            this.mManager.handleEvent(ImageMissingEvent.makeImageMissingEvent(this, str));
        }
    }

    private boolean hasImage(String str, MapLibreMap mapLibreMap) {
        Style style = mapLibreMap.getStyle();
        return (style == null || style.getImage(str) == null) ? false : true;
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(final RCTMLNMapView rCTMLNMapView) {
        rCTMLNMapView.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.images.RCTMLNImages.2
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                MapLibreMap mapboxMap = rCTMLNMapView.getMapboxMap();
                RCTMLNImages.this.mMap = mapboxMap;
                RCTMLNImages rCTMLNImages = RCTMLNImages.this;
                rCTMLNImages.addNativeImagesToStyle(rCTMLNImages.mNativeImages, mapboxMap);
                RCTMLNImages rCTMLNImages2 = RCTMLNImages.this;
                rCTMLNImages2.addImagesToStyle(rCTMLNImages2.mImages, mapboxMap);
            }
        });
    }

    private void addNativeImages(List<Map.Entry<String, BitmapDrawable>> list, MapLibreMap mapLibreMap) {
        Style style = mapLibreMap.getStyle();
        if (style == null || list == null) {
            return;
        }
        for (Map.Entry<String, BitmapDrawable> entry : list) {
            if (!hasImage(entry.getKey(), mapLibreMap)) {
                style.addImage(entry.getKey(), entry.getValue());
                this.mCurrentImages.add(entry.getKey());
            }
        }
    }

    private void addRemoteImages(List<Map.Entry<String, ImageEntry>> list, MapLibreMap mapLibreMap) {
        Style style = mapLibreMap.getStyle();
        if (style == null || list == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ImageEntry> entry : list) {
            if (!hasImage(entry.getKey(), mapLibreMap)) {
                style.addImage(entry.getKey(), mImagePlaceholder);
                arrayList.add(entry);
                this.mCurrentImages.add(entry.getKey());
            }
        }
        if (arrayList.size() > 0) {
            new DownloadMapImageTask(getContext(), mapLibreMap, null).execute((Map.Entry[]) arrayList.toArray(new Map.Entry[arrayList.size()]));
        }
    }
}
