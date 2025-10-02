package com.maplibre.rctmln.events;

import android.graphics.PointF;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.components.annotation.RCTMLNPointAnnotation;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import org.maplibre.android.geometry.LatLng;

/* loaded from: classes3.dex */
public class PointAnnotationClickEvent extends MapClickEvent {
    private PointF mScreenPoint;
    private LatLng mTouchedLatLng;
    private RCTMLNPointAnnotation mView;

    public PointAnnotationClickEvent(RCTMLNPointAnnotation rCTMLNPointAnnotation, LatLng latLng, PointF pointF, String str) {
        super(rCTMLNPointAnnotation, latLng, pointF, str);
        this.mView = rCTMLNPointAnnotation;
        this.mTouchedLatLng = latLng;
        this.mScreenPoint = pointF;
    }

    @Override // com.maplibre.rctmln.events.MapClickEvent, com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return getType().equals(EventTypes.ANNOTATION_SELECTED) ? EventKeys.POINT_ANNOTATION_SELECTED : EventKeys.POINT_ANNOTATION_DESELECTED;
    }

    @Override // com.maplibre.rctmln.events.MapClickEvent, com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("id", this.mView.getID());
        writableNativeMap.putDouble("screenPointX", this.mScreenPoint.x);
        writableNativeMap.putDouble("screenPointY", this.mScreenPoint.y);
        return GeoJSONUtils.toPointFeature(this.mTouchedLatLng, writableNativeMap);
    }
}
