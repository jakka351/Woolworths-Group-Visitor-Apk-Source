package org.maplibre.android.plugins.annotation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.LineLayer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes2.dex */
public class LineManager extends AnnotationManager<LineLayer, Line, LineOptions, OnLineDragListener, OnLineClickListener, OnLineLongClickListener> {
    private static final String PROPERTY_LINE_CAP = "line-cap";
    private static final String PROPERTY_LINE_DASHARRAY = "line-dasharray";
    private static final String PROPERTY_LINE_MITER_LIMIT = "line-miter-limit";
    private static final String PROPERTY_LINE_ROUND_LIMIT = "line-round-limit";
    private static final String PROPERTY_LINE_TRANSLATE = "line-translate";
    private static final String PROPERTY_LINE_TRANSLATE_ANCHOR = "line-translate-anchor";

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    String getAnnotationIdKey() {
        return "id";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LineManager(MapView mapView, MapLibreMap mapLibreMap, Style style) {
        this(mapView, mapLibreMap, style, null, null, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LineManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2) {
        this(mapView, mapLibreMap, style, str, str2, null);
    }

    public LineManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapLibreMap, style, new LineElementProvider(), str, str2, geoJsonOptions, DraggableAnnotationController.getInstance(mapView, mapLibreMap));
    }

    LineManager(MapView mapView, MapLibreMap mapLibreMap, Style style, CoreElementProvider<LineLayer> coreElementProvider, String str, String str2, GeoJsonOptions geoJsonOptions, DraggableAnnotationController draggableAnnotationController) {
        super(mapView, mapLibreMap, style, coreElementProvider, draggableAnnotationController, str, str2, geoJsonOptions);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    void initializeDataDrivenPropertyMap() {
        this.dataDrivenPropertyUsageMap.put("line-join", false);
        this.dataDrivenPropertyUsageMap.put("line-opacity", false);
        this.dataDrivenPropertyUsageMap.put("line-color", false);
        this.dataDrivenPropertyUsageMap.put("line-width", false);
        this.dataDrivenPropertyUsageMap.put("line-gap-width", false);
        this.dataDrivenPropertyUsageMap.put("line-offset", false);
        this.dataDrivenPropertyUsageMap.put("line-blur", false);
        this.dataDrivenPropertyUsageMap.put("line-pattern", false);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    protected void setDataDrivenPropertyIsUsed(String str) {
        str.hashCode();
        switch (str) {
            case "line-blur":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineBlur(Expression.get("line-blur")));
                break;
            case "line-join":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineJoin(Expression.get("line-join")));
                break;
            case "line-gap-width":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineGapWidth(Expression.get("line-gap-width")));
                break;
            case "line-color":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineColor(Expression.get("line-color")));
                break;
            case "line-width":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineWidth(Expression.get("line-width")));
                break;
            case "line-opacity":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineOpacity(Expression.get("line-opacity")));
                break;
            case "line-offset":
                ((LineLayer) this.layer).setProperties(PropertyFactory.lineOffset(Expression.get("line-offset")));
                break;
            case "line-pattern":
                ((LineLayer) this.layer).setProperties(PropertyFactory.linePattern(Expression.get("line-pattern")));
                break;
        }
    }

    public List<Line> create(String str) {
        return create(FeatureCollection.fromJson(str));
    }

    public List<Line> create(FeatureCollection featureCollection) {
        List<Feature> listFeatures = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (listFeatures != null) {
            Iterator<Feature> it = listFeatures.iterator();
            while (it.hasNext()) {
                LineOptions lineOptionsFromFeature = LineOptions.fromFeature(it.next());
                if (lineOptionsFromFeature != null) {
                    arrayList.add(lineOptionsFromFeature);
                }
            }
        }
        return create(arrayList);
    }

    public String getLineCap() {
        return ((LineLayer) this.layer).getLineCap().value;
    }

    public void setLineCap(String str) {
        PropertyValue<String> propertyValueLineCap = PropertyFactory.lineCap(str);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_CAP, propertyValueLineCap);
        ((LineLayer) this.layer).setProperties(propertyValueLineCap);
    }

    public Float getLineMiterLimit() {
        return ((LineLayer) this.layer).getLineMiterLimit().value;
    }

    public void setLineMiterLimit(Float f) {
        PropertyValue<Float> propertyValueLineMiterLimit = PropertyFactory.lineMiterLimit(f);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_MITER_LIMIT, propertyValueLineMiterLimit);
        ((LineLayer) this.layer).setProperties(propertyValueLineMiterLimit);
    }

    public Float getLineRoundLimit() {
        return ((LineLayer) this.layer).getLineRoundLimit().value;
    }

    public void setLineRoundLimit(Float f) {
        PropertyValue<Float> propertyValueLineRoundLimit = PropertyFactory.lineRoundLimit(f);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_ROUND_LIMIT, propertyValueLineRoundLimit);
        ((LineLayer) this.layer).setProperties(propertyValueLineRoundLimit);
    }

    public Float[] getLineTranslate() {
        return ((LineLayer) this.layer).getLineTranslate().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLineTranslate(Float[] fArr) {
        PropertyValue<Float[]> propertyValueLineTranslate = PropertyFactory.lineTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_TRANSLATE, propertyValueLineTranslate);
        ((LineLayer) this.layer).setProperties(propertyValueLineTranslate);
    }

    public String getLineTranslateAnchor() {
        return ((LineLayer) this.layer).getLineTranslateAnchor().value;
    }

    public void setLineTranslateAnchor(String str) {
        PropertyValue<String> propertyValueLineTranslateAnchor = PropertyFactory.lineTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_TRANSLATE_ANCHOR, propertyValueLineTranslateAnchor);
        ((LineLayer) this.layer).setProperties(propertyValueLineTranslateAnchor);
    }

    public Float[] getLineDasharray() {
        return ((LineLayer) this.layer).getLineDasharray().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setLineDasharray(Float[] fArr) {
        PropertyValue<Float[]> propertyValueLineDasharray = PropertyFactory.lineDasharray(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_LINE_DASHARRAY, propertyValueLineDasharray);
        ((LineLayer) this.layer).setProperties(propertyValueLineDasharray);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    public void setFilter(Expression expression) {
        this.layerFilter = expression;
        ((LineLayer) this.layer).setFilter(this.layerFilter);
    }

    public Expression getFilter() {
        return ((LineLayer) this.layer).getFilter();
    }
}
