package org.maplibre.android.plugins.annotation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.CharUtils;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.layers.SymbolLayer;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes2.dex */
public class SymbolManager extends AnnotationManager<SymbolLayer, Symbol, SymbolOptions, OnSymbolDragListener, OnSymbolClickListener, OnSymbolLongClickListener> {
    private static final String PROPERTY_ICON_ALLOW_OVERLAP = "icon-allow-overlap";
    private static final String PROPERTY_ICON_IGNORE_PLACEMENT = "icon-ignore-placement";
    private static final String PROPERTY_ICON_KEEP_UPRIGHT = "icon-keep-upright";
    private static final String PROPERTY_ICON_OPTIONAL = "icon-optional";
    private static final String PROPERTY_ICON_PADDING = "icon-padding";
    private static final String PROPERTY_ICON_PITCH_ALIGNMENT = "icon-pitch-alignment";
    private static final String PROPERTY_ICON_ROTATION_ALIGNMENT = "icon-rotation-alignment";
    private static final String PROPERTY_ICON_TEXT_FIT = "icon-text-fit";
    private static final String PROPERTY_ICON_TEXT_FIT_PADDING = "icon-text-fit-padding";
    private static final String PROPERTY_ICON_TRANSLATE = "icon-translate";
    private static final String PROPERTY_ICON_TRANSLATE_ANCHOR = "icon-translate-anchor";
    private static final String PROPERTY_SYMBOL_AVOID_EDGES = "symbol-avoid-edges";
    private static final String PROPERTY_SYMBOL_PLACEMENT = "symbol-placement";
    private static final String PROPERTY_SYMBOL_SPACING = "symbol-spacing";
    private static final String PROPERTY_TEXT_ALLOW_OVERLAP = "text-allow-overlap";
    private static final String PROPERTY_TEXT_IGNORE_PLACEMENT = "text-ignore-placement";
    private static final String PROPERTY_TEXT_KEEP_UPRIGHT = "text-keep-upright";
    private static final String PROPERTY_TEXT_LINE_HEIGHT = "text-line-height";
    private static final String PROPERTY_TEXT_MAX_ANGLE = "text-max-angle";
    private static final String PROPERTY_TEXT_OPTIONAL = "text-optional";
    private static final String PROPERTY_TEXT_PADDING = "text-padding";
    private static final String PROPERTY_TEXT_PITCH_ALIGNMENT = "text-pitch-alignment";
    private static final String PROPERTY_TEXT_ROTATION_ALIGNMENT = "text-rotation-alignment";
    private static final String PROPERTY_TEXT_TRANSLATE = "text-translate";
    private static final String PROPERTY_TEXT_TRANSLATE_ANCHOR = "text-translate-anchor";
    private static final String PROPERTY_TEXT_VARIABLE_ANCHOR = "text-variable-anchor";

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    String getAnnotationIdKey() {
        return "id";
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SymbolManager(MapView mapView, MapLibreMap mapLibreMap, Style style) {
        this(mapView, mapLibreMap, style, (String) null, (String) null, (GeoJsonOptions) null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SymbolManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2) {
        this(mapView, mapLibreMap, style, str, str2, (GeoJsonOptions) null);
    }

    public SymbolManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2, GeoJsonOptions geoJsonOptions) {
        this(mapView, mapLibreMap, style, new SymbolElementProvider(), str, str2, geoJsonOptions, DraggableAnnotationController.getInstance(mapView, mapLibreMap));
    }

    public SymbolManager(MapView mapView, MapLibreMap mapLibreMap, Style style, String str, String str2, ClusterOptions clusterOptions) {
        this(mapView, mapLibreMap, style, new SymbolElementProvider(), str, str2, new GeoJsonOptions().withCluster(true).withClusterRadius(clusterOptions.getClusterRadius()).withClusterMaxZoom(clusterOptions.getClusterMaxZoom()), DraggableAnnotationController.getInstance(mapView, mapLibreMap));
        clusterOptions.apply(style, this.coreElementProvider.getSourceId());
    }

    SymbolManager(MapView mapView, MapLibreMap mapLibreMap, Style style, CoreElementProvider<SymbolLayer> coreElementProvider, String str, String str2, GeoJsonOptions geoJsonOptions, DraggableAnnotationController draggableAnnotationController) {
        super(mapView, mapLibreMap, style, coreElementProvider, draggableAnnotationController, str, str2, geoJsonOptions);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    void initializeDataDrivenPropertyMap() {
        this.dataDrivenPropertyUsageMap.put("symbol-sort-key", false);
        this.dataDrivenPropertyUsageMap.put("icon-size", false);
        this.dataDrivenPropertyUsageMap.put("icon-image", false);
        this.dataDrivenPropertyUsageMap.put("icon-rotate", false);
        this.dataDrivenPropertyUsageMap.put("icon-offset", false);
        this.dataDrivenPropertyUsageMap.put("icon-anchor", false);
        this.dataDrivenPropertyUsageMap.put("text-field", false);
        this.dataDrivenPropertyUsageMap.put("text-font", false);
        this.dataDrivenPropertyUsageMap.put("text-size", false);
        this.dataDrivenPropertyUsageMap.put("text-max-width", false);
        this.dataDrivenPropertyUsageMap.put("text-letter-spacing", false);
        this.dataDrivenPropertyUsageMap.put("text-justify", false);
        this.dataDrivenPropertyUsageMap.put("text-radial-offset", false);
        this.dataDrivenPropertyUsageMap.put("text-anchor", false);
        this.dataDrivenPropertyUsageMap.put("text-rotate", false);
        this.dataDrivenPropertyUsageMap.put("text-transform", false);
        this.dataDrivenPropertyUsageMap.put("text-offset", false);
        this.dataDrivenPropertyUsageMap.put("icon-opacity", false);
        this.dataDrivenPropertyUsageMap.put("icon-color", false);
        this.dataDrivenPropertyUsageMap.put("icon-halo-color", false);
        this.dataDrivenPropertyUsageMap.put("icon-halo-width", false);
        this.dataDrivenPropertyUsageMap.put("icon-halo-blur", false);
        this.dataDrivenPropertyUsageMap.put("text-opacity", false);
        this.dataDrivenPropertyUsageMap.put("text-color", false);
        this.dataDrivenPropertyUsageMap.put("text-halo-color", false);
        this.dataDrivenPropertyUsageMap.put("text-halo-width", false);
        this.dataDrivenPropertyUsageMap.put("text-halo-blur", false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    protected void setDataDrivenPropertyIsUsed(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        str.hashCode();
        String str6 = "icon-halo-color";
        String str7 = "text-radial-offset";
        String str8 = "icon-rotate";
        switch (str.hashCode()) {
            case -2146810373:
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                if (str.equals(str3)) {
                    c = 0;
                    break;
                }
                break;
            case -2041493401:
                str2 = "icon-halo-width";
                str4 = "icon-color";
                str3 = "text-rotate";
                if (str.equals("icon-offset")) {
                    c = 1;
                    break;
                }
                break;
            case -1946894033:
                str2 = "icon-halo-width";
                str4 = "icon-color";
                c = str.equals(str8) ? (char) 2 : (char) 65535;
                str8 = str8;
                str3 = "text-rotate";
                break;
            case -1717422239:
                str2 = "icon-halo-width";
                str4 = "icon-color";
                c = str.equals(str7) ? (char) 3 : (char) 65535;
                str7 = str7;
                str3 = "text-rotate";
                break;
            case -1708933018:
                str2 = "icon-halo-width";
                str4 = "icon-color";
                c = str.equals(str6) ? (char) 4 : (char) 65535;
                str6 = str6;
                str3 = "text-rotate";
                break;
            case -1690648887:
                str5 = "icon-color";
                str2 = "icon-halo-width";
                if (str.equals(str2)) {
                    c = 5;
                }
                str4 = str5;
                str3 = "text-rotate";
                break;
            case -1600683761:
                str5 = "icon-color";
                c = str.equals(str5) ? (char) 6 : (char) 65535;
                str2 = "icon-halo-width";
                str4 = str5;
                str3 = "text-rotate";
                break;
            case -1595213049:
                if (str.equals("icon-image")) {
                    c = 7;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -1436636971:
                if (str.equals("icon-size")) {
                    c = '\b';
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -1336352187:
                if (str.equals("symbol-sort-key")) {
                    c = '\t';
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -1262567732:
                if (str.equals("text-transform")) {
                    c = '\n';
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -1084154641:
                if (str.equals("text-font")) {
                    c = 11;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -1083772767:
                if (str.equals("text-size")) {
                    c = '\f';
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -888013006:
                if (str.equals("text-halo-color")) {
                    c = CharUtils.CR;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -886443260:
                if (str.equals("icon-halo-blur")) {
                    c = 14;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -869728875:
                if (str.equals("text-halo-width")) {
                    c = 15;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -483024021:
                if (str.equals("text-opacity")) {
                    c = 16;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case -465299984:
                if (str.equals("text-justify")) {
                    c = 17;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 317300605:
                if (str.equals("text-max-width")) {
                    c = 18;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 428355132:
                if (str.equals("text-letter-spacing")) {
                    c = 19;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 525511352:
                if (str.equals("text-halo-blur")) {
                    c = 20;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 748171971:
                if (str.equals("text-color")) {
                    c = 21;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 750756954:
                if (str.equals("text-field")) {
                    c = 22;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 1419415223:
                if (str.equals("icon-opacity")) {
                    c = 23;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 1660037973:
                if (str.equals("text-anchor")) {
                    c = 24;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 1859954313:
                if (str.equals("icon-anchor")) {
                    c = 25;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            case 2053557555:
                if (str.equals("text-offset")) {
                    c = 26;
                }
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
            default:
                str2 = "icon-halo-width";
                str3 = "text-rotate";
                str4 = "icon-color";
                break;
        }
        switch (c) {
            case 0:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textRotate(Expression.get(str3)));
                break;
            case 1:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconOffset(Expression.get("icon-offset")));
                break;
            case 2:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconRotate(Expression.get(str8)));
                break;
            case 3:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textRadialOffset(Expression.get(str7)));
                break;
            case 4:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconHaloColor(Expression.get(str6)));
                break;
            case 5:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconHaloWidth(Expression.get(str2)));
                break;
            case 6:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconColor(Expression.get(str4)));
                break;
            case 7:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconImage(Expression.get("icon-image")));
                break;
            case '\b':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconSize(Expression.get("icon-size")));
                break;
            case '\t':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.symbolSortKey(Expression.get("symbol-sort-key")));
                break;
            case '\n':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textTransform(Expression.get("text-transform")));
                break;
            case 11:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textFont(Expression.get("text-font")));
                break;
            case '\f':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textSize(Expression.get("text-size")));
                break;
            case '\r':
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textHaloColor(Expression.get("text-halo-color")));
                break;
            case 14:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconHaloBlur(Expression.get("icon-halo-blur")));
                break;
            case 15:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textHaloWidth(Expression.get("text-halo-width")));
                break;
            case 16:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textOpacity(Expression.get("text-opacity")));
                break;
            case 17:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textJustify(Expression.get("text-justify")));
                break;
            case 18:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textMaxWidth(Expression.get("text-max-width")));
                break;
            case 19:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textLetterSpacing(Expression.get("text-letter-spacing")));
                break;
            case 20:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textHaloBlur(Expression.get("text-halo-blur")));
                break;
            case 21:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textColor(Expression.get("text-color")));
                break;
            case 22:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textField(Expression.get("text-field")));
                break;
            case 23:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconOpacity(Expression.get("icon-opacity")));
                break;
            case 24:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textAnchor(Expression.get("text-anchor")));
                break;
            case 25:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.iconAnchor(Expression.get("icon-anchor")));
                break;
            case 26:
                ((SymbolLayer) this.layer).setProperties(PropertyFactory.textOffset(Expression.get("text-offset")));
                break;
        }
    }

    public List<Symbol> create(String str) {
        return create(FeatureCollection.fromJson(str));
    }

    public List<Symbol> create(FeatureCollection featureCollection) {
        List<Feature> listFeatures = featureCollection.features();
        ArrayList arrayList = new ArrayList();
        if (listFeatures != null) {
            Iterator<Feature> it = listFeatures.iterator();
            while (it.hasNext()) {
                SymbolOptions symbolOptionsFromFeature = SymbolOptions.fromFeature(it.next());
                if (symbolOptionsFromFeature != null) {
                    arrayList.add(symbolOptionsFromFeature);
                }
            }
        }
        return create(arrayList);
    }

    public String getSymbolPlacement() {
        return ((SymbolLayer) this.layer).getSymbolPlacement().value;
    }

    public void setSymbolPlacement(String str) {
        PropertyValue<String> propertyValueSymbolPlacement = PropertyFactory.symbolPlacement(str);
        this.constantPropertyUsageMap.put(PROPERTY_SYMBOL_PLACEMENT, propertyValueSymbolPlacement);
        ((SymbolLayer) this.layer).setProperties(propertyValueSymbolPlacement);
    }

    public Float getSymbolSpacing() {
        return ((SymbolLayer) this.layer).getSymbolSpacing().value;
    }

    public void setSymbolSpacing(Float f) {
        PropertyValue<Float> propertyValueSymbolSpacing = PropertyFactory.symbolSpacing(f);
        this.constantPropertyUsageMap.put(PROPERTY_SYMBOL_SPACING, propertyValueSymbolSpacing);
        ((SymbolLayer) this.layer).setProperties(propertyValueSymbolSpacing);
    }

    public Boolean getSymbolAvoidEdges() {
        return ((SymbolLayer) this.layer).getSymbolAvoidEdges().value;
    }

    public void setSymbolAvoidEdges(Boolean bool) {
        PropertyValue<Boolean> propertyValueSymbolAvoidEdges = PropertyFactory.symbolAvoidEdges(bool);
        this.constantPropertyUsageMap.put(PROPERTY_SYMBOL_AVOID_EDGES, propertyValueSymbolAvoidEdges);
        ((SymbolLayer) this.layer).setProperties(propertyValueSymbolAvoidEdges);
    }

    public Boolean getIconAllowOverlap() {
        return ((SymbolLayer) this.layer).getIconAllowOverlap().value;
    }

    public void setIconAllowOverlap(Boolean bool) {
        PropertyValue<Boolean> propertyValueIconAllowOverlap = PropertyFactory.iconAllowOverlap(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_ALLOW_OVERLAP, propertyValueIconAllowOverlap);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconAllowOverlap);
    }

    public Boolean getIconIgnorePlacement() {
        return ((SymbolLayer) this.layer).getIconIgnorePlacement().value;
    }

    public void setIconIgnorePlacement(Boolean bool) {
        PropertyValue<Boolean> propertyValueIconIgnorePlacement = PropertyFactory.iconIgnorePlacement(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_IGNORE_PLACEMENT, propertyValueIconIgnorePlacement);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconIgnorePlacement);
    }

    public Boolean getIconOptional() {
        return ((SymbolLayer) this.layer).getIconOptional().value;
    }

    public void setIconOptional(Boolean bool) {
        PropertyValue<Boolean> propertyValueIconOptional = PropertyFactory.iconOptional(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_OPTIONAL, propertyValueIconOptional);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconOptional);
    }

    public String getIconRotationAlignment() {
        return ((SymbolLayer) this.layer).getIconRotationAlignment().value;
    }

    public void setIconRotationAlignment(String str) {
        PropertyValue<String> propertyValueIconRotationAlignment = PropertyFactory.iconRotationAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_ROTATION_ALIGNMENT, propertyValueIconRotationAlignment);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconRotationAlignment);
    }

    public String getIconTextFit() {
        return ((SymbolLayer) this.layer).getIconTextFit().value;
    }

    public void setIconTextFit(String str) {
        PropertyValue<String> propertyValueIconTextFit = PropertyFactory.iconTextFit(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TEXT_FIT, propertyValueIconTextFit);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconTextFit);
    }

    public Float[] getIconTextFitPadding() {
        return ((SymbolLayer) this.layer).getIconTextFitPadding().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIconTextFitPadding(Float[] fArr) {
        PropertyValue<Float[]> propertyValueIconTextFitPadding = PropertyFactory.iconTextFitPadding(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TEXT_FIT_PADDING, propertyValueIconTextFitPadding);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconTextFitPadding);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Float getIconPadding() {
        return (Float) ((SymbolLayer) this.layer).getIconPadding().value;
    }

    public void setIconPadding(Float f) {
        PropertyValue<?> propertyValueIconPadding = PropertyFactory.iconPadding(f);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_PADDING, propertyValueIconPadding);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconPadding);
    }

    public Boolean getIconKeepUpright() {
        return ((SymbolLayer) this.layer).getIconKeepUpright().value;
    }

    public void setIconKeepUpright(Boolean bool) {
        PropertyValue<Boolean> propertyValueIconKeepUpright = PropertyFactory.iconKeepUpright(bool);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_KEEP_UPRIGHT, propertyValueIconKeepUpright);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconKeepUpright);
    }

    public String getIconPitchAlignment() {
        return ((SymbolLayer) this.layer).getIconPitchAlignment().value;
    }

    public void setIconPitchAlignment(String str) {
        PropertyValue<String> propertyValueIconPitchAlignment = PropertyFactory.iconPitchAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_PITCH_ALIGNMENT, propertyValueIconPitchAlignment);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconPitchAlignment);
    }

    public String getTextPitchAlignment() {
        return ((SymbolLayer) this.layer).getTextPitchAlignment().value;
    }

    public void setTextPitchAlignment(String str) {
        PropertyValue<String> propertyValueTextPitchAlignment = PropertyFactory.textPitchAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_PITCH_ALIGNMENT, propertyValueTextPitchAlignment);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextPitchAlignment);
    }

    public String getTextRotationAlignment() {
        return ((SymbolLayer) this.layer).getTextRotationAlignment().value;
    }

    public void setTextRotationAlignment(String str) {
        PropertyValue<String> propertyValueTextRotationAlignment = PropertyFactory.textRotationAlignment(str);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_ROTATION_ALIGNMENT, propertyValueTextRotationAlignment);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextRotationAlignment);
    }

    public Float getTextLineHeight() {
        return ((SymbolLayer) this.layer).getTextLineHeight().value;
    }

    public void setTextLineHeight(Float f) {
        PropertyValue<Float> propertyValueTextLineHeight = PropertyFactory.textLineHeight(f);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_LINE_HEIGHT, propertyValueTextLineHeight);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextLineHeight);
    }

    public String[] getTextVariableAnchor() {
        return ((SymbolLayer) this.layer).getTextVariableAnchor().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTextVariableAnchor(String[] strArr) {
        PropertyValue<String[]> propertyValueTextVariableAnchor = PropertyFactory.textVariableAnchor(strArr);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_VARIABLE_ANCHOR, propertyValueTextVariableAnchor);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextVariableAnchor);
    }

    public Float getTextMaxAngle() {
        return ((SymbolLayer) this.layer).getTextMaxAngle().value;
    }

    public void setTextMaxAngle(Float f) {
        PropertyValue<Float> propertyValueTextMaxAngle = PropertyFactory.textMaxAngle(f);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_MAX_ANGLE, propertyValueTextMaxAngle);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextMaxAngle);
    }

    public Float getTextPadding() {
        return ((SymbolLayer) this.layer).getTextPadding().value;
    }

    public void setTextPadding(Float f) {
        PropertyValue<Float> propertyValueTextPadding = PropertyFactory.textPadding(f);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_PADDING, propertyValueTextPadding);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextPadding);
    }

    public Boolean getTextKeepUpright() {
        return ((SymbolLayer) this.layer).getTextKeepUpright().value;
    }

    public void setTextKeepUpright(Boolean bool) {
        PropertyValue<Boolean> propertyValueTextKeepUpright = PropertyFactory.textKeepUpright(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_KEEP_UPRIGHT, propertyValueTextKeepUpright);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextKeepUpright);
    }

    public Boolean getTextAllowOverlap() {
        return ((SymbolLayer) this.layer).getTextAllowOverlap().value;
    }

    public void setTextAllowOverlap(Boolean bool) {
        PropertyValue<Boolean> propertyValueTextAllowOverlap = PropertyFactory.textAllowOverlap(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_ALLOW_OVERLAP, propertyValueTextAllowOverlap);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextAllowOverlap);
    }

    public Boolean getTextIgnorePlacement() {
        return ((SymbolLayer) this.layer).getTextIgnorePlacement().value;
    }

    public void setTextIgnorePlacement(Boolean bool) {
        PropertyValue<Boolean> propertyValueTextIgnorePlacement = PropertyFactory.textIgnorePlacement(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_IGNORE_PLACEMENT, propertyValueTextIgnorePlacement);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextIgnorePlacement);
    }

    public Boolean getTextOptional() {
        return ((SymbolLayer) this.layer).getTextOptional().value;
    }

    public void setTextOptional(Boolean bool) {
        PropertyValue<Boolean> propertyValueTextOptional = PropertyFactory.textOptional(bool);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_OPTIONAL, propertyValueTextOptional);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextOptional);
    }

    public Float[] getIconTranslate() {
        return ((SymbolLayer) this.layer).getIconTranslate().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setIconTranslate(Float[] fArr) {
        PropertyValue<Float[]> propertyValueIconTranslate = PropertyFactory.iconTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TRANSLATE, propertyValueIconTranslate);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconTranslate);
    }

    public String getIconTranslateAnchor() {
        return ((SymbolLayer) this.layer).getIconTranslateAnchor().value;
    }

    public void setIconTranslateAnchor(String str) {
        PropertyValue<String> propertyValueIconTranslateAnchor = PropertyFactory.iconTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_ICON_TRANSLATE_ANCHOR, propertyValueIconTranslateAnchor);
        ((SymbolLayer) this.layer).setProperties(propertyValueIconTranslateAnchor);
    }

    public Float[] getTextTranslate() {
        return ((SymbolLayer) this.layer).getTextTranslate().value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTextTranslate(Float[] fArr) {
        PropertyValue<Float[]> propertyValueTextTranslate = PropertyFactory.textTranslate(fArr);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_TRANSLATE, propertyValueTextTranslate);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextTranslate);
    }

    public String getTextTranslateAnchor() {
        return ((SymbolLayer) this.layer).getTextTranslateAnchor().value;
    }

    public void setTextTranslateAnchor(String str) {
        PropertyValue<String> propertyValueTextTranslateAnchor = PropertyFactory.textTranslateAnchor(str);
        this.constantPropertyUsageMap.put(PROPERTY_TEXT_TRANSLATE_ANCHOR, propertyValueTextTranslateAnchor);
        ((SymbolLayer) this.layer).setProperties(propertyValueTextTranslateAnchor);
    }

    @Override // org.maplibre.android.plugins.annotation.AnnotationManager
    public void setFilter(Expression expression) {
        this.layerFilter = expression;
        ((SymbolLayer) this.layer).setFilter(this.layerFilter);
    }

    public Expression getFilter() {
        return ((SymbolLayer) this.layer).getFilter();
    }
}
