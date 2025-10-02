package org.maplibre.android.plugins.localization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.layers.SymbolLayer;
import org.maplibre.android.style.sources.Source;
import org.maplibre.android.style.sources.VectorSource;
import org.maplibre.android.style.types.Formatted;
import timber.log.Timber;

/* loaded from: classes2.dex */
public final class LocalizationPlugin {
    private static final String EXPRESSION_REGEX = "\\b(name|name_.{2,7})\\b";
    private static final String EXPRESSION_V8_REGEX_BASE = "\\[\"get\", \"name_en\"], \\[\"get\", \"name\"]";
    private static final String EXPRESSION_V8_REGEX_LOCALIZED = "\\[\"match\", \"(name|name_.{2,7})\", \"name_zh-Hant\", \\[\"coalesce\", \\[\"get\", \"name_zh-Hant\"], \\[\"get\", \"name_zh-Hans\"], \\[\"match\", \\[\"get\", \"name_script\"], \"Latin\", \\[\"get\", \"name\"], \\[\"get\", \"name_en\"]], \\[\"get\", \"name\"]], \\[\"coalesce\", \\[\"get\", \"(name|name_.{2,7})\"], \\[\"match\", \\[\"get\", \"name_script\"], \"Latin\", \\[\"get\", \"name\"], \\[\"get\", \"name_en\"]], \\[\"get\", \"name\"]]]";
    private static final String EXPRESSION_V8_TEMPLATE_BASE = "[\"get\", \"name_en\"], [\"get\", \"name\"]";
    private static final String EXPRESSION_V8_TEMPLATE_LOCALIZED = "[\"match\", \"%s\", \"name_zh-Hant\", [\"coalesce\", [\"get\", \"name_zh-Hant\"], [\"get\", \"name_zh-Hans\"], [\"match\", [\"get\", \"name_script\"], \"Latin\", [\"get\", \"name\"], [\"get\", \"name_en\"]], [\"get\", \"name\"]], [\"coalesce\", [\"get\", \"%s\"], [\"match\", [\"get\", \"name_script\"], \"Latin\", [\"get\", \"name\"], [\"get\", \"name_en\"]], [\"get\", \"name\"]]]";
    private static final String STEP_REGEX = "\\[\"zoom\"], ";
    private static final String STEP_TEMPLATE = "[\"zoom\"], \"\", ";
    private static final List<String> SUPPORTED_SOURCES;
    private MapLocale mapLocale;
    private final MapLibreMap maplibreMap;
    private Style style;

    static {
        ArrayList arrayList = new ArrayList();
        SUPPORTED_SOURCES = arrayList;
        arrayList.add("mapbox.mapbox-streets-v6");
        arrayList.add("mapbox.mapbox-streets-v7");
        arrayList.add("mapbox.mapbox-streets-v8");
    }

    public LocalizationPlugin(MapView mapView, final MapLibreMap mapLibreMap, Style style) {
        this.maplibreMap = mapLibreMap;
        this.style = style;
        if (!style.isFullyLoaded()) {
            throw new RuntimeException("The style has to be non-null and fully loaded.");
        }
        mapView.addOnDidFinishLoadingStyleListener(new MapView.OnDidFinishLoadingStyleListener() { // from class: org.maplibre.android.plugins.localization.LocalizationPlugin.1
            @Override // org.maplibre.android.maps.MapView.OnDidFinishLoadingStyleListener
            public void onDidFinishLoadingStyle() {
                mapLibreMap.getStyle(new Style.OnStyleLoaded() { // from class: org.maplibre.android.plugins.localization.LocalizationPlugin.1.1
                    @Override // org.maplibre.android.maps.Style.OnStyleLoaded
                    public void onStyleLoaded(Style style2) {
                        LocalizationPlugin.this.style = style2;
                        if (LocalizationPlugin.this.mapLocale != null) {
                            LocalizationPlugin.this.setMapLanguage(LocalizationPlugin.this.mapLocale);
                        }
                    }
                });
            }
        });
    }

    private MapLocale getChineseMapLocale(MapLocale mapLocale, boolean z) {
        if (z) {
            return mapLocale.equals(MapLocale.CHINESE_HANS) ? mapLocale : MapLocale.CHINA;
        }
        return MapLocale.CHINA;
    }

    public void matchMapLanguageWithDeviceDefault() {
        setMapLanguage(Locale.getDefault(), false);
    }

    public void matchMapLanguageWithDeviceDefault(boolean z) {
        setMapLanguage(Locale.getDefault(), z);
    }

    public void setMapLanguage(String str) {
        setMapLanguage(new MapLocale(str));
    }

    public void setMapLanguage(Locale locale) {
        setMapLanguage(locale, false);
    }

    public void setMapLanguage(Locale locale, boolean z) {
        MapLocale mapLocale = MapLocale.getMapLocale(locale, z);
        if (mapLocale != null) {
            Timber.d("Locale: %s, set MapLocale: %s", locale.toString(), mapLocale.getMapLanguage());
            setMapLanguage(mapLocale);
        } else {
            Timber.d("Couldn't match Locale %s %s to a MapLocale", locale.toString(), locale.getDisplayName());
        }
    }

    public void setMapLanguage(MapLocale mapLocale) {
        this.mapLocale = mapLocale;
        if (this.style.isFullyLoaded()) {
            List<Layer> layers = this.style.getLayers();
            for (Source source : this.style.getSources()) {
                if (sourceIsFromMapbox(source)) {
                    boolean zSourceIsStreetsV8 = sourceIsStreetsV8(source);
                    for (Layer layer : layers) {
                        if (layer instanceof SymbolLayer) {
                            PropertyValue<Formatted> textField = ((SymbolLayer) layer).getTextField();
                            if (textField.isExpression()) {
                                if (zSourceIsStreetsV8) {
                                    convertExpressionV8(mapLocale, layer, textField);
                                } else {
                                    convertExpression(mapLocale, layer, textField, sourceIsStreetsV7(source));
                                }
                            }
                        }
                    }
                } else {
                    String url = source instanceof VectorSource ? ((VectorSource) source).getUrl() : null;
                    if (url == null) {
                        url = "not found";
                    }
                    Timber.d("The %s (%s) source is not based on Mapbox Vector Tiles. Supported sources:\n %s", source.getId(), url, SUPPORTED_SOURCES);
                }
            }
        }
    }

    private void convertExpression(MapLocale mapLocale, Layer layer, PropertyValue<?> propertyValue, boolean z) {
        Expression expression = propertyValue.getExpression();
        if (expression != null) {
            if (mapLocale.getMapLanguage().startsWith(MapLocale.CHINESE)) {
                mapLocale = getChineseMapLocale(mapLocale, z);
                Timber.d("reset mapLocale to: %s", mapLocale.getMapLanguage());
            }
            String strReplaceAll = expression.toString().replaceAll(EXPRESSION_REGEX, mapLocale.getMapLanguage());
            if (strReplaceAll.startsWith("[\"step") && expression.toArray().length % 2 == 0) {
                strReplaceAll = strReplaceAll.replaceAll(STEP_REGEX, STEP_TEMPLATE);
            }
            layer.setProperties(PropertyFactory.textField(Expression.raw(strReplaceAll)));
        }
    }

    private void convertExpressionV8(MapLocale mapLocale, Layer layer, PropertyValue<?> propertyValue) {
        Expression expression = propertyValue.getExpression();
        if (expression != null) {
            String strReplaceAll = expression.toString().replaceAll(EXPRESSION_V8_REGEX_LOCALIZED, EXPRESSION_V8_TEMPLATE_BASE);
            String mapLanguage = mapLocale.getMapLanguage();
            if (!mapLanguage.equals(MapLocale.ENGLISH)) {
                if (mapLanguage.equals(MapLocale.CHINESE)) {
                    mapLanguage = MapLocale.SIMPLIFIED_CHINESE;
                }
                strReplaceAll = strReplaceAll.replaceAll(EXPRESSION_V8_REGEX_BASE, String.format(Locale.US, EXPRESSION_V8_TEMPLATE_LOCALIZED, mapLanguage, mapLanguage));
            }
            layer.setProperties(PropertyFactory.textField(Expression.raw(strReplaceAll)));
        }
    }

    public void setCameraToLocaleCountry(int i) {
        setCameraToLocaleCountry(Locale.getDefault(), i);
    }

    public void setCameraToLocaleCountry(Locale locale, int i) {
        MapLocale mapLocale = MapLocale.getMapLocale(locale, false);
        if (mapLocale != null) {
            setCameraToLocaleCountry(mapLocale, i);
        } else {
            Timber.d("Couldn't match Locale %s to a MapLocale", locale.getDisplayName());
        }
    }

    public void setCameraToLocaleCountry(MapLocale mapLocale, int i) {
        LatLngBounds countryBounds = mapLocale.getCountryBounds();
        if (countryBounds == null) {
            throw new NullPointerException("Expected a LatLngBounds object but received null instead. Make sure your MapLocale instance also has a country bounding box defined.");
        }
        this.maplibreMap.moveCamera(CameraUpdateFactory.newLatLngBounds(countryBounds, i));
    }

    private boolean sourceIsFromMapbox(Source source) {
        String url;
        if (!(source instanceof VectorSource) || (url = ((VectorSource) source).getUrl()) == null) {
            return false;
        }
        Iterator<String> it = SUPPORTED_SOURCES.iterator();
        while (it.hasNext()) {
            if (url.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    private boolean sourceIsStreetsV7(Source source) {
        String url;
        return (source instanceof VectorSource) && (url = ((VectorSource) source).getUrl()) != null && url.contains("mapbox.mapbox-streets-v7");
    }

    private boolean sourceIsStreetsV8(Source source) {
        String url;
        return (source instanceof VectorSource) && (url = ((VectorSource) source).getUrl()) != null && url.contains("mapbox.mapbox-streets-v8");
    }
}
