package org.maplibre.android.plugins.annotation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.CircleLayer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes2.dex */
public class CircleManager extends AnnotationManager<CircleLayer, Circle, CircleOptions, OnCircleDragListener, OnCircleClickListener, OnCircleLongClickListener> {
    private static final String PROPERTY_CIRCLE_PITCH_ALIGNMENT = "circle-pitch-alignment";
    private static final String PROPERTY_CIRCLE_PITCH_SCALE = "circle-pitch-scale";
    private static final String PROPERTY_CIRCLE_TRANSLATE = "circle-translate";
    private static final String PROPERTY_CIRCLE_TRANSLATE_ANCHOR = "circle-translate-anchor";

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    String getAnnotationIdKey() {
        return "id";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CircleManager(MapView mapView, MapLibreMap mapLibreMap, Style style) {
        this(mapView, mapLibreMap, style, null, null, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CircleManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2) {
        this(mapView, mapLibreMap, style, str, str2, null);
    }

    public CircleManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapLibreMap, style, new CircleElementProvider(), str, str2, geoJsonOptions, DraggableAnnotationController.getInstance(mapView, mapLibreMap));
    }

    CircleManager(MapView mapView, MapLibreMap mapLibreMap, Style style, CoreElementProvider<CircleLayer> coreElementProvider, String str, String str2, GeoJsonOptions geoJsonOptions, DraggableAnnotationController draggableAnnotationController) {
        super(mapView, mapLibreMap, style, coreElementProvider, draggableAnnotationController, str, str2, geoJsonOptions);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    void initializeDataDrivenPropertyMap() {
        this.dataDrivenPropertyUsageMap.put("circle-radius", false);
        this.dataDrivenPropertyUsageMap.put("circle-color", false);
        this.dataDrivenPropertyUsageMap.put("circle-blur", false);
        this.dataDrivenPropertyUsageMap.put("circle-opacity", false);
        this.dataDrivenPropertyUsageMap.put("circle-stroke-width", false);
        this.dataDrivenPropertyUsageMap.put("circle-stroke-color", false);
        this.dataDrivenPropertyUsageMap.put("circle-stroke-opacity", false);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    protected void setDataDrivenPropertyIsUsed(String str) {
        str.hashCode();
        switch (str) {
            case "circle-opacity":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleOpacity(Expression.get("circle-opacity")));
                break;
            case "circle-radius":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleRadius(Expression.get("circle-radius")));
                break;
            case "circle-stroke-color":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleStrokeColor(Expression.get("circle-stroke-color")));
                break;
            case "circle-stroke-width":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleStrokeWidth(Expression.get("circle-stroke-width")));
                break;
            case "circle-blur":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleBlur(Expression.get("circle-blur")));
                break;
            case "circle-color":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleColor(Expression.get("circle-color")));
                break;
            case "circle-stroke-opacity":
                ((CircleLayer) this.layer).setProperties(PropertyFactory.circleStrokeOpacity(Expression.get("circle-stroke-opacity")));
                break;
        }
    }

    public List<Circle> create(String str) {
        return create(FeatureCollection.fromJson(str));
    }

    public List<Circle> create(FeatureCollection featureCollection) {
        List<Feature> listFeatures = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (listFeatures != null) {
            Iterator<Feature> it = listFeatures.iterator();
            while (it.hasNext()) {
                CircleOptions circleOptionsFromFeature = CircleOptions.fromFeature(it.next());
                if (circleOptionsFromFeature != null) {
                    arrayList.add(circleOptionsFromFeature);
                }
            }
        }
        return create(arrayList);
    }

    public Float[] getCircleTranslate() {
        return ((CircleLayer) this.layer).getCircleTranslate().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setCircleTranslate(Float[] fArr) {
        PropertyValue<Float[]> propertyValueCircleTranslate = PropertyFactory.circleTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_TRANSLATE, propertyValueCircleTranslate);
        ((CircleLayer) this.layer).setProperties(propertyValueCircleTranslate);
    }

    public String getCircleTranslateAnchor() {
        return ((CircleLayer) this.layer).getCircleTranslateAnchor().value;
    }

    public void setCircleTranslateAnchor(String str) {
        PropertyValue<String> propertyValueCircleTranslateAnchor = PropertyFactory.circleTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_TRANSLATE_ANCHOR, propertyValueCircleTranslateAnchor);
        ((CircleLayer) this.layer).setProperties(propertyValueCircleTranslateAnchor);
    }

    public String getCirclePitchScale() {
        return ((CircleLayer) this.layer).getCirclePitchScale().value;
    }

    public void setCirclePitchScale(String str) {
        PropertyValue<String> propertyValueCirclePitchScale = PropertyFactory.circlePitchScale(str);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_PITCH_SCALE, propertyValueCirclePitchScale);
        ((CircleLayer) this.layer).setProperties(propertyValueCirclePitchScale);
    }

    public String getCirclePitchAlignment() {
        return ((CircleLayer) this.layer).getCirclePitchAlignment().value;
    }

    public void setCirclePitchAlignment(String str) {
        PropertyValue<String> propertyValueCirclePitchAlignment = PropertyFactory.circlePitchAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_CIRCLE_PITCH_ALIGNMENT, propertyValueCirclePitchAlignment);
        ((CircleLayer) this.layer).setProperties(propertyValueCirclePitchAlignment);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    public void setFilter(Expression expression) {
        this.layerFilter = expression;
        ((CircleLayer) this.layer).setFilter(this.layerFilter);
    }

    public Expression getFilter() {
        return ((CircleLayer) this.layer).getFilter();
    }
}
