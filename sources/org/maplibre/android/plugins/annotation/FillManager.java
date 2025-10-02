package org.maplibre.android.plugins.annotation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.FillLayer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes2.dex */
public class FillManager extends AnnotationManager<FillLayer, Fill, FillOptions, OnFillDragListener, OnFillClickListener, OnFillLongClickListener> {
    private static final String PROPERTY_FILL_ANTIALIAS = "fill-antialias";
    private static final String PROPERTY_FILL_TRANSLATE = "fill-translate";
    private static final String PROPERTY_FILL_TRANSLATE_ANCHOR = "fill-translate-anchor";

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    String getAnnotationIdKey() {
        return "id";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FillManager(MapView mapView, MapLibreMap mapLibreMap, Style style) {
        this(mapView, mapLibreMap, style, null, null, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FillManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2) {
        this(mapView, mapLibreMap, style, str, str2, null);
    }

    public FillManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapLibreMap, style, new FillElementProvider(), str, str2, geoJsonOptions, DraggableAnnotationController.getInstance(mapView, mapLibreMap));
    }

    FillManager(MapView mapView, MapLibreMap mapLibreMap, Style style, CoreElementProvider<FillLayer> coreElementProvider, String str, String str2, GeoJsonOptions geoJsonOptions, DraggableAnnotationController draggableAnnotationController) {
        super(mapView, mapLibreMap, style, coreElementProvider, draggableAnnotationController, str, str2, geoJsonOptions);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    void initializeDataDrivenPropertyMap() {
        this.dataDrivenPropertyUsageMap.put("fill-opacity", false);
        this.dataDrivenPropertyUsageMap.put("fill-color", false);
        this.dataDrivenPropertyUsageMap.put("fill-outline-color", false);
        this.dataDrivenPropertyUsageMap.put("fill-pattern", false);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    protected void setDataDrivenPropertyIsUsed(String str) {
        str.hashCode();
        switch (str) {
            case "fill-color":
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillColor(Expression.get("fill-color")));
                break;
            case "fill-opacity":
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillOpacity(Expression.get("fill-opacity")));
                break;
            case "fill-pattern":
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillPattern(Expression.get("fill-pattern")));
                break;
            case "fill-outline-color":
                ((FillLayer) this.layer).setProperties(PropertyFactory.fillOutlineColor(Expression.get("fill-outline-color")));
                break;
        }
    }

    public List<Fill> create(String str) {
        return create(FeatureCollection.fromJson(str));
    }

    public List<Fill> create(FeatureCollection featureCollection) {
        List<Feature> listFeatures = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (listFeatures != null) {
            Iterator<Feature> it = listFeatures.iterator();
            while (it.hasNext()) {
                FillOptions fillOptionsFromFeature = FillOptions.fromFeature(it.next());
                if (fillOptionsFromFeature != null) {
                    arrayList.add(fillOptionsFromFeature);
                }
            }
        }
        return create(arrayList);
    }

    public Boolean getFillAntialias() {
        return ((FillLayer) this.layer).getFillAntialias().value;
    }

    public void setFillAntialias(Boolean bool) {
        PropertyValue<Boolean> propertyValueFillAntialias = PropertyFactory.fillAntialias(bool);
        this.constantPropertyUsageMap.put(PROPERTY_FILL_ANTIALIAS, propertyValueFillAntialias);
        ((FillLayer) this.layer).setProperties(propertyValueFillAntialias);
    }

    public Float[] getFillTranslate() {
        return ((FillLayer) this.layer).getFillTranslate().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setFillTranslate(Float[] fArr) {
        PropertyValue<Float[]> propertyValueFillTranslate = PropertyFactory.fillTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_FILL_TRANSLATE, propertyValueFillTranslate);
        ((FillLayer) this.layer).setProperties(propertyValueFillTranslate);
    }

    public String getFillTranslateAnchor() {
        return ((FillLayer) this.layer).getFillTranslateAnchor().value;
    }

    public void setFillTranslateAnchor(String str) {
        PropertyValue<String> propertyValueFillTranslateAnchor = PropertyFactory.fillTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_FILL_TRANSLATE_ANCHOR, propertyValueFillTranslateAnchor);
        ((FillLayer) this.layer).setProperties(propertyValueFillTranslateAnchor);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    public void setFilter(Expression expression) {
        this.layerFilter = expression;
        ((FillLayer) this.layer).setFilter(this.layerFilter);
    }

    public Expression getFilter() {
        return ((FillLayer) this.layer).getFilter();
    }
}
