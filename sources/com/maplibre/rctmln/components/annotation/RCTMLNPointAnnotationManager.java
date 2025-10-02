package com.maplibre.rctmln.components.annotation;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import java.util.Map;

/* loaded from: classes3.dex */
public class RCTMLNPointAnnotationManager extends AbstractEventEmitter<RCTMLNPointAnnotation> {
    public static final int METHOD_REFRESH = 2;
    public static final String REACT_CLASS = "RCTMLNPointAnnotation";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNPointAnnotationManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return MapBuilder.builder().put(EventKeys.POINT_ANNOTATION_SELECTED, "onMapboxPointAnnotationSelected").put(EventKeys.POINT_ANNOTATION_DESELECTED, "onMapboxPointAnnotationDeselected").put(EventKeys.POINT_ANNOTATION_DRAG_START, "onMapboxPointAnnotationDragStart").put(EventKeys.POINT_ANNOTATION_DRAG, "onMapboxPointAnnotationDrag").put(EventKeys.POINT_ANNOTATION_DRAG_END, "onMapboxPointAnnotationDragEnd").build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.builder().put("refresh", 2).build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNPointAnnotation createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNPointAnnotation(themedReactContext, this);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNPointAnnotation rCTMLNPointAnnotation, String str) {
        rCTMLNPointAnnotation.setID(str);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(RCTMLNPointAnnotation rCTMLNPointAnnotation, String str) {
        rCTMLNPointAnnotation.setCoordinate(GeoJSONUtils.toPointGeometry(str));
    }

    @ReactProp(name = "anchor")
    public void setAnchor(RCTMLNPointAnnotation rCTMLNPointAnnotation, ReadableMap readableMap) {
        rCTMLNPointAnnotation.setAnchor((float) readableMap.getDouble("x"), (float) readableMap.getDouble("y"));
    }

    @ReactProp(name = "draggable")
    public void setDraggable(RCTMLNPointAnnotation rCTMLNPointAnnotation, Boolean bool) {
        rCTMLNPointAnnotation.setDraggable(bool);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(RCTMLNPointAnnotation rCTMLNPointAnnotation, int i, ReadableArray readableArray) {
        if (i != 2) {
            return;
        }
        rCTMLNPointAnnotation.refresh();
    }
}
