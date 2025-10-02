package org.maplibre.android.plugins.localization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;

/* loaded from: classes2.dex */
public final class MapLocale {
    public static final String ARABIC = "name_ar";
    public static final MapLocale BRAZIL;
    static final LatLngBounds BRAZIL_BBOX;
    public static final MapLocale CANADA;
    static final LatLngBounds CANADA_BBOX;
    public static final MapLocale CANADA_FRENCH;
    public static final MapLocale CHINA;
    static final LatLngBounds CHINA_BBOX;
    public static final String CHINESE = "name_zh";
    public static final MapLocale CHINESE_HANS;
    public static final MapLocale CHINESE_HANT;
    public static final String ENGLISH = "name_en";
    public static final MapLocale FRANCE;
    static final LatLngBounds FRANCE_BBOX;
    public static final String FRENCH = "name_fr";
    public static final String GERMAN = "name_de";
    public static final MapLocale GERMANY;
    static final LatLngBounds GERMANY_BBOX;
    public static final String ITALIAN = "name_it";
    public static final MapLocale ITALY;
    static final LatLngBounds ITALY_BBOX;
    public static final MapLocale JAPAN;
    public static final String JAPANESE = "name_ja";
    static final LatLngBounds JAPAN_BBOX;
    public static final MapLocale KOREA;
    public static final String KOREAN = "name_ko";
    static final LatLngBounds KOREA_BBOX;
    private static final Map<Locale, MapLocale> LOCALE_SET;
    public static final String LOCAL_NAME = "name";
    public static final MapLocale PORTUGAL;
    static final LatLngBounds PORTUGAL_BBOX;
    public static final String PORTUGUESE = "name_pt";
    public static final MapLocale RUSSIA;
    public static final String RUSSIAN = "name_ru";
    static final LatLngBounds RUSSIA_BBOX;
    public static final String SIMPLIFIED_CHINESE = "name_zh-Hans";
    public static final MapLocale SPAIN;
    static final LatLngBounds SPAIN_BBOX;
    public static final String SPANISH = "name_es";
    public static final MapLocale TAIWAN;
    static final LatLngBounds TAIWAN_BBOX;
    static final String TRADITIONAL_CHINESE = "name_zh-Hant";
    public static final MapLocale UK;
    static final LatLngBounds UK_BBOX;
    public static final MapLocale US;
    static final LatLngBounds USA_BBOX;
    public static final MapLocale VIETNAM;
    public static final String VIETNAMESE = "name_vi";
    static final LatLngBounds VIETNAM_BBOX;
    private final LatLngBounds countryBounds;
    private final String mapLanguage;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Languages {
    }

    static {
        LatLngBounds latLngBoundsBuild = new LatLngBounds.Builder().include(new LatLng(49.388611d, -124.733253d)).include(new LatLng(24.544245d, -66.954811d)).build();
        USA_BBOX = latLngBoundsBuild;
        LatLngBounds latLngBoundsBuild2 = new LatLngBounds.Builder().include(new LatLng(59.360249d, -8.623555d)).include(new LatLng(49.906193d, 1.759d)).build();
        UK_BBOX = latLngBoundsBuild2;
        LatLngBounds latLngBoundsBuild3 = new LatLngBounds.Builder().include(new LatLng(83.110626d, -141.0d)).include(new LatLng(41.67598d, -52.636291d)).build();
        CANADA_BBOX = latLngBoundsBuild3;
        LatLngBounds latLngBoundsBuild4 = new LatLngBounds.Builder().include(new LatLng(53.56086d, 73.557693d)).include(new LatLng(15.775416d, 134.773911d)).build();
        CHINA_BBOX = latLngBoundsBuild4;
        LatLngBounds latLngBoundsBuild5 = new LatLngBounds.Builder().include(new LatLng(26.389444d, 118.115255566105d)).include(new LatLng(21.733333d, 122.107778d)).build();
        TAIWAN_BBOX = latLngBoundsBuild5;
        LatLngBounds latLngBoundsBuild6 = new LatLngBounds.Builder().include(new LatLng(55.055637d, 5.865639d)).include(new LatLng(47.275776d, 15.039889d)).build();
        GERMANY_BBOX = latLngBoundsBuild6;
        LatLngBounds latLngBoundsBuild7 = new LatLngBounds.Builder().include(new LatLng(38.612446d, 125.887108d)).include(new LatLng(33.190945d, 129.584671d)).build();
        KOREA_BBOX = latLngBoundsBuild7;
        LatLngBounds latLngBoundsBuild8 = new LatLngBounds.Builder().include(new LatLng(45.52314d, 122.93853d)).include(new LatLng(24.249472d, 145.820892d)).build();
        JAPAN_BBOX = latLngBoundsBuild8;
        LatLngBounds latLngBoundsBuild9 = new LatLngBounds.Builder().include(new LatLng(51.092804d, -5.142222d)).include(new LatLng(41.371582d, 9.561556d)).build();
        FRANCE_BBOX = latLngBoundsBuild9;
        LatLngBounds latLngBoundsBuild10 = new LatLngBounds.Builder().include(new LatLng(81.856903d, -168.997849d)).include(new LatLng(41.185902d, 19.638861d)).build();
        RUSSIA_BBOX = latLngBoundsBuild10;
        LatLngBounds latLngBoundsBuild11 = new LatLngBounds.Builder().include(new LatLng(27.4335426d, -18.3936845d)).include(new LatLng(43.9933088d, 4.5918885d)).build();
        SPAIN_BBOX = latLngBoundsBuild11;
        LatLngBounds latLngBoundsBuild12 = new LatLngBounds.Builder().include(new LatLng(27.4335426d, -18.3936845d)).include(new LatLng(42.280468655d, -6.3890876937d)).build();
        PORTUGAL_BBOX = latLngBoundsBuild12;
        LatLngBounds latLngBoundsBuild13 = new LatLngBounds.Builder().include(new LatLng(5.2842873d, -33.8689056d)).include(new LatLng(-28.6341164d, -73.9830625d)).build();
        BRAZIL_BBOX = latLngBoundsBuild13;
        LatLngBounds latLngBoundsBuild14 = new LatLngBounds.Builder().include(new LatLng(8.383333d, 102.216667d)).include(new LatLng(23.666667d, 109.466667d)).build();
        VIETNAM_BBOX = latLngBoundsBuild14;
        LatLngBounds latLngBoundsBuild15 = new LatLngBounds.Builder().include(new LatLng(36.619987291d, 6.7499552751d)).include(new LatLng(47.1153931748d, 18.4802470232d)).build();
        ITALY_BBOX = latLngBoundsBuild15;
        MapLocale mapLocale = new MapLocale(FRENCH, latLngBoundsBuild9);
        FRANCE = mapLocale;
        MapLocale mapLocale2 = new MapLocale(GERMAN, latLngBoundsBuild6);
        GERMANY = mapLocale2;
        MapLocale mapLocale3 = new MapLocale(JAPANESE, latLngBoundsBuild8);
        JAPAN = mapLocale3;
        MapLocale mapLocale4 = new MapLocale(KOREAN, latLngBoundsBuild7);
        KOREA = mapLocale4;
        CHINA = new MapLocale(SIMPLIFIED_CHINESE, latLngBoundsBuild4);
        MapLocale mapLocale5 = new MapLocale(TRADITIONAL_CHINESE, latLngBoundsBuild5);
        TAIWAN = mapLocale5;
        MapLocale mapLocale6 = new MapLocale(SIMPLIFIED_CHINESE);
        CHINESE_HANS = mapLocale6;
        MapLocale mapLocale7 = new MapLocale(TRADITIONAL_CHINESE);
        CHINESE_HANT = mapLocale7;
        MapLocale mapLocale8 = new MapLocale(ENGLISH, latLngBoundsBuild2);
        UK = mapLocale8;
        MapLocale mapLocale9 = new MapLocale(ENGLISH, latLngBoundsBuild);
        US = mapLocale9;
        MapLocale mapLocale10 = new MapLocale(ENGLISH, latLngBoundsBuild3);
        CANADA = mapLocale10;
        MapLocale mapLocale11 = new MapLocale(FRENCH, latLngBoundsBuild3);
        CANADA_FRENCH = mapLocale11;
        MapLocale mapLocale12 = new MapLocale(RUSSIAN, latLngBoundsBuild10);
        RUSSIA = mapLocale12;
        MapLocale mapLocale13 = new MapLocale(SPANISH, latLngBoundsBuild11);
        SPAIN = mapLocale13;
        MapLocale mapLocale14 = new MapLocale(PORTUGUESE, latLngBoundsBuild12);
        PORTUGAL = mapLocale14;
        MapLocale mapLocale15 = new MapLocale(PORTUGUESE, latLngBoundsBuild13);
        BRAZIL = mapLocale15;
        MapLocale mapLocale16 = new MapLocale(VIETNAMESE, latLngBoundsBuild14);
        VIETNAM = mapLocale16;
        MapLocale mapLocale17 = new MapLocale(ITALIAN, latLngBoundsBuild15);
        ITALY = mapLocale17;
        HashMap map = new HashMap();
        LOCALE_SET = map;
        map.put(Locale.US, mapLocale9);
        map.put(Locale.CANADA_FRENCH, mapLocale11);
        map.put(Locale.CANADA, mapLocale10);
        map.put(Locale.CHINA, mapLocale6);
        map.put(Locale.TAIWAN, mapLocale5);
        map.put(Locale.UK, mapLocale8);
        map.put(Locale.JAPAN, mapLocale3);
        map.put(Locale.KOREA, mapLocale4);
        map.put(Locale.GERMANY, mapLocale2);
        map.put(Locale.FRANCE, mapLocale);
        map.put(new Locale("ru", "RU"), mapLocale12);
        map.put(new Locale("es", "ES"), mapLocale13);
        map.put(new Locale("pt", "PT"), mapLocale14);
        map.put(new Locale("pt", "BR"), mapLocale15);
        map.put(new Locale("vi", "VN"), mapLocale16);
        map.put(Locale.ITALY, mapLocale17);
        Locale localeBuild = new Locale.Builder().setLanguage("zh").setRegion("CN").setScript("Hans").build();
        Locale localeBuild2 = new Locale.Builder().setLanguage("zh").setRegion("HK").setScript("Hans").build();
        Locale localeBuild3 = new Locale.Builder().setLanguage("zh").setRegion("MO").setScript("Hans").build();
        Locale localeBuild4 = new Locale.Builder().setLanguage("zh").setRegion("SG").setScript("Hans").build();
        Locale localeBuild5 = new Locale.Builder().setLanguage("zh").setRegion("TW").setScript("Hant").build();
        Locale localeBuild6 = new Locale.Builder().setLanguage("zh").setRegion("HK").setScript("Hant").build();
        Locale localeBuild7 = new Locale.Builder().setLanguage("zh").setRegion("MO").setScript("Hant").build();
        map.put(localeBuild, mapLocale6);
        map.put(localeBuild2, mapLocale6);
        map.put(localeBuild3, mapLocale6);
        map.put(localeBuild4, mapLocale6);
        map.put(localeBuild5, mapLocale5);
        map.put(localeBuild6, mapLocale7);
        map.put(localeBuild7, mapLocale7);
    }

    public MapLocale(String str) {
        this(str, null);
    }

    public MapLocale(LatLngBounds latLngBounds) {
        this("name", latLngBounds);
    }

    public MapLocale(String str, LatLngBounds latLngBounds) {
        this.countryBounds = latLngBounds;
        this.mapLanguage = str;
    }

    public String getMapLanguage() {
        return this.mapLanguage;
    }

    public LatLngBounds getCountryBounds() {
        return this.countryBounds;
    }

    public static void addMapLocale(Locale locale, MapLocale mapLocale) {
        LOCALE_SET.put(locale, mapLocale);
    }

    public static MapLocale getMapLocale(Locale locale) {
        return getMapLocale(locale, false);
    }

    public static MapLocale getMapLocale(Locale locale, boolean z) {
        MapLocale mapLocale = LOCALE_SET.get(locale);
        return (z && mapLocale == null) ? getMapLocaleFallback(locale) : mapLocale;
    }

    private static MapLocale getMapLocaleFallback(Locale locale) {
        String strSubstring = locale.getLanguage().substring(0, 2);
        for (Locale locale2 : LOCALE_SET.keySet()) {
            if (locale2.getLanguage().equals(strSubstring)) {
                return LOCALE_SET.get(locale2);
            }
        }
        return null;
    }
}
