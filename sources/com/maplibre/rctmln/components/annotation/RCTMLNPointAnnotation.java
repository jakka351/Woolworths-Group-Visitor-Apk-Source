package com.maplibre.rctmln.components.annotation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.view.View;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.events.PointAnnotationClickEvent;
import com.maplibre.rctmln.events.PointAnnotationDragEvent;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.utils.BitmapUtils;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.plugins.annotation.Symbol;
import org.maplibre.android.plugins.annotation.SymbolManager;
import org.maplibre.android.plugins.annotation.SymbolOptions;
import org.maplibre.geojson.Point;

/* loaded from: classes3.dex */
public class RCTMLNPointAnnotation extends AbstractMapFeature implements View.OnLayoutChangeListener {
    private static final String MARKER_IMAGE_ID = "MARKER_IMAGE_ID";
    private Float[] mAnchor;
    private Symbol mAnnotation;
    private Bitmap mCalloutBitmap;
    private String mCalloutBitmapId;
    private Symbol mCalloutSymbol;
    private View mCalloutView;
    private Bitmap mChildBitmap;
    private String mChildBitmapId;
    private View mChildView;
    private Context mContext;
    private Point mCoordinate;
    private boolean mDraggable;
    private boolean mHasChildren;
    private String mID;
    private boolean mIsSelected;
    private RCTMLNPointAnnotationManager mManager;
    private MapLibreMap mMap;
    private RCTMLNMapView mMapView;
    private String mSnippet;
    private String mTitle;

    public RCTMLNPointAnnotation(Context context, RCTMLNPointAnnotationManager rCTMLNPointAnnotationManager) {
        super(context);
        this.mContext = context;
        this.mManager = rCTMLNPointAnnotationManager;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        if (view instanceof RCTMLNCallout) {
            this.mCalloutView = view;
        } else {
            this.mChildView = view;
        }
        view.addOnLayoutChangeListener(this);
        RCTMLNMapView rCTMLNMapView = this.mMapView;
        if (rCTMLNMapView != null) {
            rCTMLNMapView.offscreenAnnotationViewContainer().addView(view);
        }
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mChildView != null) {
            this.mMap.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.annotation.RCTMLNPointAnnotation.1
                @Override // org.maplibre.android.maps.Style.OnStyleLoaded
                public void onStyleLoaded(Style style) {
                    style.removeImage(RCTMLNPointAnnotation.this.mChildBitmapId);
                    RCTMLNPointAnnotation.this.mChildView = null;
                    RCTMLNPointAnnotation.this.mCalloutView = null;
                    RCTMLNPointAnnotation.this.mChildBitmap = null;
                    RCTMLNPointAnnotation.this.mChildBitmapId = null;
                    RCTMLNPointAnnotation.this.updateOptions();
                }
            });
        }
        RCTMLNMapView rCTMLNMapView = this.mMapView;
        if (rCTMLNMapView != null) {
            rCTMLNMapView.offscreenAnnotationViewContainer().removeView(view);
        }
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        this.mMapView = rCTMLNMapView;
        this.mMap = rCTMLNMapView.getMapboxMap();
        makeMarker();
        View view = this.mChildView;
        if (view != null) {
            if (!view.isAttachedToWindow()) {
                this.mMapView.offscreenAnnotationViewContainer().addView(this.mChildView);
            }
            addBitmapToStyle(this.mChildBitmap, this.mChildBitmapId);
            updateOptions();
        }
        View view2 = this.mCalloutView;
        if (view2 != null) {
            if (!view2.isAttachedToWindow()) {
                this.mMapView.offscreenAnnotationViewContainer().addView(this.mCalloutView);
            }
            addBitmapToStyle(this.mCalloutBitmap, this.mCalloutBitmapId);
        }
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
        RCTMLNMapView rCTMLNMapView2 = this.mMapView;
        if (rCTMLNMapView2 != null) {
            rCTMLNMapView = rCTMLNMapView2;
        }
        if (rCTMLNMapView == null) {
            return;
        }
        if (this.mAnnotation != null) {
            rCTMLNMapView.getSymbolManager().delete((SymbolManager) this.mAnnotation);
        }
        if (this.mChildView != null) {
            rCTMLNMapView.offscreenAnnotationViewContainer().removeView(this.mChildView);
        }
        if (this.mCalloutView != null) {
            rCTMLNMapView.offscreenAnnotationViewContainer().removeView(this.mCalloutView);
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return;
        }
        if (i == i5 && i3 == i7 && i2 == i6 && i4 == i8) {
            return;
        }
        refreshBitmap(view, i, i2, i3, i4);
    }

    private void refreshBitmap(View view, int i, int i2, int i3, int i4) {
        Bitmap bitmapViewToBitmap = BitmapUtils.viewToBitmap(view, i, i2, i3, i4);
        String string = Integer.toString(view.getId());
        addBitmapToStyle(bitmapViewToBitmap, string);
        if (view instanceof RCTMLNCallout) {
            this.mCalloutBitmap = bitmapViewToBitmap;
            this.mCalloutBitmapId = string;
        } else if (bitmapViewToBitmap != null) {
            this.mChildBitmap = bitmapViewToBitmap;
            this.mChildBitmapId = string;
            updateOptions();
        }
    }

    private void refreshBitmap(View view) {
        refreshBitmap(view, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }

    public LatLng getLatLng() {
        return GeoJSONUtils.toLatLng(this.mCoordinate);
    }

    public long getMapboxID() {
        Symbol symbol = this.mAnnotation;
        if (symbol == null) {
            return -1L;
        }
        return symbol.getId();
    }

    public String getID() {
        return this.mID;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public View getCalloutView() {
        return this.mCalloutView;
    }

    public void setCoordinate(Point point) {
        this.mCoordinate = point;
        Symbol symbol = this.mAnnotation;
        if (symbol != null) {
            symbol.setLatLng(GeoJSONUtils.toLatLng(point));
            this.mMapView.getSymbolManager().update((SymbolManager) this.mAnnotation);
        }
        Symbol symbol2 = this.mCalloutSymbol;
        if (symbol2 != null) {
            symbol2.setLatLng(GeoJSONUtils.toLatLng(point));
            this.mMapView.getSymbolManager().update((SymbolManager) this.mCalloutSymbol);
        }
    }

    public void setAnchor(float f, float f2) {
        this.mAnchor = new Float[]{Float.valueOf(f), Float.valueOf(f2)};
        if (this.mAnnotation != null) {
            updateAnchor();
            this.mMapView.getSymbolManager().update((SymbolManager) this.mAnnotation);
        }
    }

    public void setDraggable(Boolean bool) {
        this.mDraggable = bool.booleanValue();
        Symbol symbol = this.mAnnotation;
        if (symbol != null) {
            symbol.setDraggable(bool.booleanValue());
            this.mMapView.getSymbolManager().update((SymbolManager) this.mAnnotation);
        }
    }

    public Symbol getMarker() {
        return this.mAnnotation;
    }

    public void onSelect(boolean z) {
        if (this.mCalloutView != null) {
            makeCallout();
        }
        if (z) {
            this.mManager.handleEvent(makeEvent(true));
        }
    }

    public void onDeselect() {
        this.mManager.handleEvent(makeEvent(false));
        if (this.mCalloutSymbol != null) {
            this.mMapView.getSymbolManager().delete((SymbolManager) this.mCalloutSymbol);
        }
    }

    public void onDragStart() {
        LatLng latLng = this.mAnnotation.getLatLng();
        this.mCoordinate = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
        this.mManager.handleEvent(makeDragEvent(EventTypes.ANNOTATION_DRAG_START));
    }

    public void onDrag() {
        LatLng latLng = this.mAnnotation.getLatLng();
        this.mCoordinate = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
        this.mManager.handleEvent(makeDragEvent(EventTypes.ANNOTATION_DRAG));
    }

    public void onDragEnd() {
        LatLng latLng = this.mAnnotation.getLatLng();
        this.mCoordinate = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
        this.mManager.handleEvent(makeDragEvent(EventTypes.ANNOTATION_DRAG_END));
    }

    public void makeMarker() {
        SymbolOptions symbolOptionsWithSymbolSortKey = new SymbolOptions().withLatLng(GeoJSONUtils.toLatLng(this.mCoordinate)).withDraggable(this.mDraggable).withIconSize(Float.valueOf(1.0f)).withSymbolSortKey(Float.valueOf(10.0f));
        SymbolManager symbolManager = this.mMapView.getSymbolManager();
        if (symbolManager != null) {
            this.mAnnotation = symbolManager.create((SymbolManager) symbolOptionsWithSymbolSortKey);
            updateOptions();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOptions() {
        if (this.mAnnotation != null) {
            updateIconImage();
            updateAnchor();
            this.mMapView.getSymbolManager().update((SymbolManager) this.mAnnotation);
        }
    }

    private void updateIconImage() {
        if (this.mChildView != null) {
            String str = this.mChildBitmapId;
            if (str != null) {
                this.mAnnotation.setIconImage(str);
                return;
            }
            return;
        }
        this.mAnnotation.setIconImage(MARKER_IMAGE_ID);
        this.mAnnotation.setIconAnchor("bottom");
    }

    private void updateAnchor() {
        Bitmap bitmap;
        if (this.mAnchor == null || this.mChildView == null || (bitmap = this.mChildBitmap) == null) {
            return;
        }
        int width = bitmap.getWidth();
        int height = this.mChildBitmap.getHeight();
        float f = getResources().getDisplayMetrics().density;
        this.mAnnotation.setIconAnchor("top-left");
        this.mAnnotation.setIconOffset(new PointF(((int) (width / f)) * this.mAnchor[0].floatValue() * (-1.0f), ((int) (height / f)) * this.mAnchor[1].floatValue() * (-1.0f)));
    }

    private void makeCallout() {
        SymbolOptions symbolOptionsWithDraggable = new SymbolOptions().withLatLng(GeoJSONUtils.toLatLng(this.mCoordinate)).withIconImage(this.mCalloutBitmapId).withIconSize(Float.valueOf(1.0f)).withIconAnchor("bottom").withIconOffset(new Float[]{Float.valueOf(0.0f), Float.valueOf((this.mChildView == null || this.mChildBitmap == null) ? -28.0f : ((int) ((this.mChildBitmap.getHeight() / 2) / getResources().getDisplayMetrics().density)) * (-1.0f))}).withSymbolSortKey(Float.valueOf(11.0f)).withDraggable(false);
        SymbolManager symbolManager = this.mMapView.getSymbolManager();
        if (symbolManager != null) {
            this.mCalloutSymbol = symbolManager.create((SymbolManager) symbolOptionsWithDraggable);
        }
    }

    private void addBitmapToStyle(final Bitmap bitmap, final String str) {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null || str == null || bitmap == null) {
            return;
        }
        mapLibreMap.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.annotation.RCTMLNPointAnnotation.2
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                style.addImage(str, bitmap);
            }
        });
    }

    private PointAnnotationClickEvent makeEvent(boolean z) {
        String str = z ? EventTypes.ANNOTATION_SELECTED : EventTypes.ANNOTATION_DESELECTED;
        LatLng latLng = GeoJSONUtils.toLatLng(this.mCoordinate);
        return new PointAnnotationClickEvent(this, latLng, getScreenPosition(latLng), str);
    }

    private PointAnnotationDragEvent makeDragEvent(String str) {
        LatLng latLng = GeoJSONUtils.toLatLng(this.mCoordinate);
        return new PointAnnotationDragEvent(this, latLng, getScreenPosition(latLng), str);
    }

    private float getDisplayDensity() {
        return this.mContext.getResources().getDisplayMetrics().density;
    }

    private PointF getScreenPosition(LatLng latLng) {
        PointF screenLocation = this.mMap.getProjection().toScreenLocation(latLng);
        float displayDensity = getDisplayDensity();
        screenLocation.x /= displayDensity;
        screenLocation.y /= displayDensity;
        return screenLocation;
    }

    public void refresh() {
        View view = this.mChildView;
        if (view != null) {
            refreshBitmap(view);
        }
    }
}
