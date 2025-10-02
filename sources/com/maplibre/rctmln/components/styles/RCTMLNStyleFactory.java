package com.maplibre.rctmln.components.styles;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.soloader.Elf64_Ehdr;
import com.maplibre.rctmln.utils.DownloadMapImageTask;
import java.util.List;
import kotlin.text.Typography;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.maplibre.android.style.layers.BackgroundLayer;
import org.maplibre.android.style.layers.CircleLayer;
import org.maplibre.android.style.layers.FillExtrusionLayer;
import org.maplibre.android.style.layers.FillLayer;
import org.maplibre.android.style.layers.HeatmapLayer;
import org.maplibre.android.style.layers.HillshadeLayer;
import org.maplibre.android.style.layers.LineLayer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.RasterLayer;
import org.maplibre.android.style.layers.SymbolLayer;
import org.maplibre.android.style.layers.TransitionOptions;
import org.maplibre.android.style.light.Light;
import org.maplibre.android.style.light.Position;

/* loaded from: classes3.dex */
public class RCTMLNStyleFactory {
    public static final String SHOULD_ADD_IMAGE_KEY = "shouldAddImage";
    public static final String VALUE_KEY = "value";

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void setFillLayerStyle(final FillLayer fillLayer, RCTMLNStyle rCTMLNStyle) {
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            final RCTMLNStyleValue styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1984592619:
                    if (str.equals("fillColorTransition")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1730029888:
                    if (str.equals("fillTranslateTransition")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1635251751:
                    if (str.equals("fillOutlineColorTransition")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1141881952:
                    if (str.equals("fillColor")) {
                        c = 3;
                        break;
                    }
                    break;
                case -977559797:
                    if (str.equals("fillTranslate")) {
                        c = 4;
                        break;
                    }
                    break;
                case -811082530:
                    if (str.equals("fillSortKey")) {
                        c = 5;
                        break;
                    }
                    break;
                case -53677816:
                    if (str.equals("fillOpacity")) {
                        c = 6;
                        break;
                    }
                    break;
                case 103064002:
                    if (str.equals("fillPatternTransition")) {
                        c = 7;
                        break;
                    }
                    break;
                case 122984864:
                    if (str.equals("fillTranslateAnchor")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 145968619:
                    if (str.equals("fillAntialias")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 422438029:
                    if (str.equals("fillPattern")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1425616228:
                    if (str.equals("fillOutlineColor")) {
                        c = 11;
                        break;
                    }
                    break;
                case 1900449917:
                    if (str.equals("fillOpacityTransition")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 1941332754:
                    if (str.equals("visibility")) {
                        c = CharUtils.CR;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setFillColorTransition(fillLayer, styleValueForKey);
                    break;
                case 1:
                    setFillTranslateTransition(fillLayer, styleValueForKey);
                    break;
                case 2:
                    setFillOutlineColorTransition(fillLayer, styleValueForKey);
                    break;
                case 3:
                    setFillColor(fillLayer, styleValueForKey);
                    break;
                case 4:
                    setFillTranslate(fillLayer, styleValueForKey);
                    break;
                case 5:
                    setFillSortKey(fillLayer, styleValueForKey);
                    break;
                case 6:
                    setFillOpacity(fillLayer, styleValueForKey);
                    break;
                case 7:
                    setFillPatternTransition(fillLayer, styleValueForKey);
                    break;
                case '\b':
                    setFillTranslateAnchor(fillLayer, styleValueForKey);
                    break;
                case '\t':
                    setFillAntialias(fillLayer, styleValueForKey);
                    break;
                case '\n':
                    rCTMLNStyle.addImage(styleValueForKey, new DownloadMapImageTask.OnAllImagesLoaded() { // from class: com.maplibre.rctmln.components.styles.RCTMLNStyleFactory.1
                        @Override // com.maplibre.rctmln.utils.DownloadMapImageTask.OnAllImagesLoaded
                        public void onAllImagesLoaded() {
                            RCTMLNStyleFactory.setFillPattern(fillLayer, styleValueForKey);
                        }
                    });
                    break;
                case 11:
                    setFillOutlineColor(fillLayer, styleValueForKey);
                    break;
                case '\f':
                    setFillOpacityTransition(fillLayer, styleValueForKey);
                    break;
                case '\r':
                    setVisibility(fillLayer, styleValueForKey);
                    break;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void setLineLayerStyle(final LineLayer lineLayer, RCTMLNStyle rCTMLNStyle) {
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            final RCTMLNStyleValue styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1928202383:
                    if (str.equals("linePatternTransition")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1822070833:
                    if (str.equals("lineColor")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1803786702:
                    if (str.equals("lineWidth")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1762877983:
                    if (str.equals("lineRoundLimit")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1637568179:
                    if (str.equals("lineSortKey")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1111871207:
                    if (str.equals("lineGapWidthTransition")) {
                        c = 5;
                        break;
                    }
                    break;
                case -880163465:
                    if (str.equals("lineOpacity")) {
                        c = 6;
                        break;
                    }
                    break;
                case -796928188:
                    if (str.equals("lineGradient")) {
                        c = 7;
                        break;
                    }
                    break;
                case -771065212:
                    if (str.equals("lineColorTransition")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -661318726:
                    if (str.equals("lineTranslate")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -404047620:
                    if (str.equals("linePattern")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -314558041:
                    if (str.equals("lineOffset")) {
                        c = 11;
                        break;
                    }
                    break;
                case -130816468:
                    if (str.equals("lineOpacityTransition")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 176874302:
                    if (str.equals("lineCap")) {
                        c = CharUtils.CR;
                        break;
                    }
                    break;
                case 433093807:
                    if (str.equals("lineTranslateTransition")) {
                        c = 14;
                        break;
                    }
                    break;
                case 536864304:
                    if (str.equals("lineBlurTransition")) {
                        c = 15;
                        break;
                    }
                    break;
                case 734880970:
                    if (str.equals("lineMiterLimit")) {
                        c = 16;
                        break;
                    }
                    break;
                case 1188117115:
                    if (str.equals("lineBlur")) {
                        c = 17;
                        break;
                    }
                    break;
                case 1188357950:
                    if (str.equals("lineJoin")) {
                        c = 18;
                        break;
                    }
                    break;
                case 1336512271:
                    if (str.equals("lineTranslateAnchor")) {
                        c = 19;
                        break;
                    }
                    break;
                case 1532137587:
                    if (str.equals("lineDasharray")) {
                        c = 20;
                        break;
                    }
                    break;
                case 1712809124:
                    if (str.equals("lineGapWidth")) {
                        c = 21;
                        break;
                    }
                    break;
                case 1835866407:
                    if (str.equals("lineWidthTransition")) {
                        c = 22;
                        break;
                    }
                    break;
                case 1941332754:
                    if (str.equals("visibility")) {
                        c = 23;
                        break;
                    }
                    break;
                case 1948788264:
                    if (str.equals("lineDasharrayTransition")) {
                        c = 24;
                        break;
                    }
                    break;
                case 2051726940:
                    if (str.equals("lineOffsetTransition")) {
                        c = 25;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setLinePatternTransition(lineLayer, styleValueForKey);
                    break;
                case 1:
                    setLineColor(lineLayer, styleValueForKey);
                    break;
                case 2:
                    setLineWidth(lineLayer, styleValueForKey);
                    break;
                case 3:
                    setLineRoundLimit(lineLayer, styleValueForKey);
                    break;
                case 4:
                    setLineSortKey(lineLayer, styleValueForKey);
                    break;
                case 5:
                    setLineGapWidthTransition(lineLayer, styleValueForKey);
                    break;
                case 6:
                    setLineOpacity(lineLayer, styleValueForKey);
                    break;
                case 7:
                    setLineGradient(lineLayer, styleValueForKey);
                    break;
                case '\b':
                    setLineColorTransition(lineLayer, styleValueForKey);
                    break;
                case '\t':
                    setLineTranslate(lineLayer, styleValueForKey);
                    break;
                case '\n':
                    rCTMLNStyle.addImage(styleValueForKey, new DownloadMapImageTask.OnAllImagesLoaded() { // from class: com.maplibre.rctmln.components.styles.RCTMLNStyleFactory.2
                        @Override // com.maplibre.rctmln.utils.DownloadMapImageTask.OnAllImagesLoaded
                        public void onAllImagesLoaded() {
                            RCTMLNStyleFactory.setLinePattern(lineLayer, styleValueForKey);
                        }
                    });
                    break;
                case 11:
                    setLineOffset(lineLayer, styleValueForKey);
                    break;
                case '\f':
                    setLineOpacityTransition(lineLayer, styleValueForKey);
                    break;
                case '\r':
                    setLineCap(lineLayer, styleValueForKey);
                    break;
                case 14:
                    setLineTranslateTransition(lineLayer, styleValueForKey);
                    break;
                case 15:
                    setLineBlurTransition(lineLayer, styleValueForKey);
                    break;
                case 16:
                    setLineMiterLimit(lineLayer, styleValueForKey);
                    break;
                case 17:
                    setLineBlur(lineLayer, styleValueForKey);
                    break;
                case 18:
                    setLineJoin(lineLayer, styleValueForKey);
                    break;
                case 19:
                    setLineTranslateAnchor(lineLayer, styleValueForKey);
                    break;
                case 20:
                    setLineDasharray(lineLayer, styleValueForKey);
                    break;
                case 21:
                    setLineGapWidth(lineLayer, styleValueForKey);
                    break;
                case 22:
                    setLineWidthTransition(lineLayer, styleValueForKey);
                    break;
                case 23:
                    setVisibility(lineLayer, styleValueForKey);
                    break;
                case 24:
                    setLineDasharrayTransition(lineLayer, styleValueForKey);
                    break;
                case 25:
                    setLineOffsetTransition(lineLayer, styleValueForKey);
                    break;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void setSymbolLayerStyle(final SymbolLayer symbolLayer, RCTMLNStyle rCTMLNStyle) {
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            final RCTMLNStyleValue styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2107142626:
                    if (str.equals("textOpacity")) {
                        c = 0;
                        break;
                    }
                    break;
                case -2089418589:
                    if (str.equals("textJustify")) {
                        c = 1;
                        break;
                    }
                    break;
                case -2057882389:
                    if (str.equals("iconTextFit")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1979070486:
                    if (str.equals("iconTranslateAnchor")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1958433749:
                    if (str.equals("textColorTransition")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1907226286:
                    if (str.equals("iconOpacity")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1879178031:
                    if (str.equals("iconHaloBlurTransition")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1862421794:
                    if (str.equals("textVariableAnchor")) {
                        c = 7;
                        break;
                    }
                    break;
                case -1807506772:
                    if (str.equals("symbolZOrder")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1646276060:
                    if (str.equals("textPadding")) {
                        c = '\t';
                        break;
                    }
                    break;
                case -1480756154:
                    if (str.equals("textIgnorePlacement")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -1446359720:
                    if (str.equals("iconPadding")) {
                        c = 11;
                        break;
                    }
                    break;
                case -1416436118:
                    if (str.equals("iconColor")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -1410965406:
                    if (str.equals("iconImage")) {
                        c = CharUtils.CR;
                        break;
                    }
                    break;
                case -1336804080:
                    if (str.equals("textLetterSpacing")) {
                        c = 14;
                        break;
                    }
                    break;
                case -1199109335:
                    if (str.equals("symbolSortKey")) {
                        c = 15;
                        break;
                    }
                    break;
                case -1186657397:
                    if (str.equals("symbolSpacing")) {
                        c = 16;
                        break;
                    }
                    break;
                case -1158635046:
                    if (str.equals("textHaloColor")) {
                        c = 17;
                        break;
                    }
                    break;
                case -1140350915:
                    if (str.equals("textHaloWidth")) {
                        c = 18;
                        break;
                    }
                    break;
                case -1063571914:
                    if (str.equals("textColor")) {
                        c = 19;
                        break;
                    }
                    break;
                case -1060986931:
                    if (str.equals("textField")) {
                        c = 20;
                        break;
                    }
                    break;
                case -1018303346:
                    if (str.equals("iconAnchor")) {
                        c = 21;
                        break;
                    }
                    break;
                case -1004050660:
                    if (str.equals("textFont")) {
                        c = 22;
                        break;
                    }
                    break;
                case -1003668786:
                    if (str.equals("textSize")) {
                        c = 23;
                        break;
                    }
                    break;
                case -737956838:
                    if (str.equals("iconSize")) {
                        c = 24;
                        break;
                    }
                    break;
                case -726538404:
                    if (str.equals("iconPitchAlignment")) {
                        c = 25;
                        break;
                    }
                    break;
                case -699323086:
                    if (str.equals("textWritingMode")) {
                        c = 26;
                        break;
                    }
                    break;
                case -624783764:
                    if (str.equals("iconOffset")) {
                        c = 27;
                        break;
                    }
                    break;
                case -584404152:
                    if (str.equals("textLineHeight")) {
                        c = 28;
                        break;
                    }
                    break;
                case -530184396:
                    if (str.equals("iconRotate")) {
                        c = 29;
                        break;
                    }
                    break;
                case -347244627:
                    if (str.equals("textOptional")) {
                        c = 30;
                        break;
                    }
                    break;
                case -314502384:
                    if (str.equals("textHaloBlur")) {
                        c = 31;
                        break;
                    }
                    break;
                case -242244336:
                    if (str.equals("textPitchAlignment")) {
                        c = ' ';
                        break;
                    }
                    break;
                case -178346657:
                    if (str.equals("textRadialOffset")) {
                        c = '!';
                        break;
                    }
                    break;
                case -171596660:
                    if (str.equals("iconRotationAlignment")) {
                        c = Typography.quote;
                        break;
                    }
                    break;
                case -47001946:
                    if (str.equals("iconHaloWidthTransition")) {
                        c = '#';
                        break;
                    }
                    break;
                case 111988252:
                    if (str.equals("textMaxAngle")) {
                        c = '$';
                        break;
                    }
                    break;
                case 132154127:
                    if (str.equals("textMaxWidth")) {
                        c = '%';
                        break;
                    }
                    break;
                case 149143734:
                    if (str.equals("textTranslateAnchor")) {
                        c = Typography.amp;
                        break;
                    }
                    break;
                case 173788373:
                    if (str.equals("iconTranslate")) {
                        c = '\'';
                        break;
                    }
                    break;
                case 208319327:
                    if (str.equals("iconColorTransition")) {
                        c = '(';
                        break;
                    }
                    break;
                case 428573042:
                    if (str.equals("textHaloWidthTransition")) {
                        c = ')';
                        break;
                    }
                    break;
                case 459887687:
                    if (str.equals("iconOpacityTransition")) {
                        c = '*';
                        break;
                    }
                    break;
                case 530941997:
                    if (str.equals("symbolPlacement")) {
                        c = '+';
                        break;
                    }
                    break;
                case 637835864:
                    if (str.equals("textRotationAlignment")) {
                        c = ',';
                        break;
                    }
                    break;
                case 638968459:
                    if (str.equals("textAllowOverlap")) {
                        c = '-';
                        break;
                    }
                    break;
                case 685996922:
                    if (str.equals("iconIgnorePlacement")) {
                        c = ClassUtils.PACKAGE_SEPARATOR_CHAR;
                        break;
                    }
                    break;
                case 879628003:
                    if (str.equals("iconKeepUpright")) {
                        c = '/';
                        break;
                    }
                    break;
                case 926348697:
                    if (str.equals("symbolAvoidEdges")) {
                        c = '0';
                        break;
                    }
                    break;
                case 960251863:
                    if (str.equals("iconAllowOverlap")) {
                        c = '1';
                        break;
                    }
                    break;
                case 1164103690:
                    if (str.equals("iconTranslateTransition")) {
                        c = '2';
                        break;
                    }
                    break;
                case 1269320211:
                    if (str.equals("textOpacityTransition")) {
                        c = '3';
                        break;
                    }
                    break;
                case 1327548607:
                    if (str.equals(ReactBaseTextShadowNode.PROP_TEXT_TRANSFORM)) {
                        c = '4';
                        break;
                    }
                    break;
                case 1327713953:
                    if (str.equals("textTranslate")) {
                        c = '5';
                        break;
                    }
                    break;
                case 1330552386:
                    if (str.equals("textAnchor")) {
                        c = '6';
                        break;
                    }
                    break;
                case 1555194617:
                    if (str.equals("iconOptional")) {
                        c = '7';
                        break;
                    }
                    break;
                case 1587936860:
                    if (str.equals("iconHaloBlur")) {
                        c = '8';
                        break;
                    }
                    break;
                case 1639678678:
                    if (str.equals("textTranslateTransition")) {
                        c = '9';
                        break;
                    }
                    break;
                case 1641033731:
                    if (str.equals("iconHaloColorTransition")) {
                        c = ':';
                        break;
                    }
                    break;
                case 1700548015:
                    if (str.equals("textKeepUpright")) {
                        c = ';';
                        break;
                    }
                    break;
                case 1724071968:
                    if (str.equals("textOffset")) {
                        c = Typography.less;
                        break;
                    }
                    break;
                case 1724076198:
                    if (str.equals("iconTextFitPadding")) {
                        c = '=';
                        break;
                    }
                    break;
                case 1738393733:
                    if (str.equals("textHaloBlurTransition")) {
                        c = Typography.greater;
                        break;
                    }
                    break;
                case 1818671336:
                    if (str.equals("textRotate")) {
                        c = '?';
                        break;
                    }
                    break;
                case 1941332754:
                    if (str.equals("visibility")) {
                        c = '@';
                        break;
                    }
                    break;
                case 1982406670:
                    if (str.equals("iconHaloColor")) {
                        c = 'A';
                        break;
                    }
                    break;
                case 2000690801:
                    if (str.equals("iconHaloWidth")) {
                        c = 'B';
                        break;
                    }
                    break;
                case 2116608719:
                    if (str.equals("textHaloColorTransition")) {
                        c = 'C';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setTextOpacity(symbolLayer, styleValueForKey);
                    break;
                case 1:
                    setTextJustify(symbolLayer, styleValueForKey);
                    break;
                case 2:
                    setIconTextFit(symbolLayer, styleValueForKey);
                    break;
                case 3:
                    setIconTranslateAnchor(symbolLayer, styleValueForKey);
                    break;
                case 4:
                    setTextColorTransition(symbolLayer, styleValueForKey);
                    break;
                case 5:
                    setIconOpacity(symbolLayer, styleValueForKey);
                    break;
                case 6:
                    setIconHaloBlurTransition(symbolLayer, styleValueForKey);
                    break;
                case 7:
                    setTextVariableAnchor(symbolLayer, styleValueForKey);
                    break;
                case '\b':
                    setSymbolZOrder(symbolLayer, styleValueForKey);
                    break;
                case '\t':
                    setTextPadding(symbolLayer, styleValueForKey);
                    break;
                case '\n':
                    setTextIgnorePlacement(symbolLayer, styleValueForKey);
                    break;
                case 11:
                    setIconPadding(symbolLayer, styleValueForKey);
                    break;
                case '\f':
                    setIconColor(symbolLayer, styleValueForKey);
                    break;
                case '\r':
                    rCTMLNStyle.addImage(styleValueForKey, new DownloadMapImageTask.OnAllImagesLoaded() { // from class: com.maplibre.rctmln.components.styles.RCTMLNStyleFactory.3
                        @Override // com.maplibre.rctmln.utils.DownloadMapImageTask.OnAllImagesLoaded
                        public void onAllImagesLoaded() {
                            RCTMLNStyleFactory.setIconImage(symbolLayer, styleValueForKey);
                        }
                    });
                    break;
                case 14:
                    setTextLetterSpacing(symbolLayer, styleValueForKey);
                    break;
                case 15:
                    setSymbolSortKey(symbolLayer, styleValueForKey);
                    break;
                case 16:
                    setSymbolSpacing(symbolLayer, styleValueForKey);
                    break;
                case 17:
                    setTextHaloColor(symbolLayer, styleValueForKey);
                    break;
                case 18:
                    setTextHaloWidth(symbolLayer, styleValueForKey);
                    break;
                case 19:
                    setTextColor(symbolLayer, styleValueForKey);
                    break;
                case 20:
                    setTextField(symbolLayer, styleValueForKey);
                    break;
                case 21:
                    setIconAnchor(symbolLayer, styleValueForKey);
                    break;
                case 22:
                    setTextFont(symbolLayer, styleValueForKey);
                    break;
                case 23:
                    setTextSize(symbolLayer, styleValueForKey);
                    break;
                case 24:
                    setIconSize(symbolLayer, styleValueForKey);
                    break;
                case 25:
                    setIconPitchAlignment(symbolLayer, styleValueForKey);
                    break;
                case 26:
                    setTextWritingMode(symbolLayer, styleValueForKey);
                    break;
                case 27:
                    setIconOffset(symbolLayer, styleValueForKey);
                    break;
                case 28:
                    setTextLineHeight(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    setIconRotate(symbolLayer, styleValueForKey);
                    break;
                case 30:
                    setTextOptional(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_WIDTH_DEFAULT /* 31 */:
                    setTextHaloBlur(symbolLayer, styleValueForKey);
                    break;
                case ' ':
                    setTextPitchAlignment(symbolLayer, styleValueForKey);
                    break;
                case '!':
                    setTextRadialOffset(symbolLayer, styleValueForKey);
                    break;
                case '\"':
                    setIconRotationAlignment(symbolLayer, styleValueForKey);
                    break;
                case '#':
                    setIconHaloWidthTransition(symbolLayer, styleValueForKey);
                    break;
                case '$':
                    setTextMaxAngle(symbolLayer, styleValueForKey);
                    break;
                case '%':
                    setTextMaxWidth(symbolLayer, styleValueForKey);
                    break;
                case '&':
                    setTextTranslateAnchor(symbolLayer, styleValueForKey);
                    break;
                case '\'':
                    setIconTranslate(symbolLayer, styleValueForKey);
                    break;
                case '(':
                    setIconColorTransition(symbolLayer, styleValueForKey);
                    break;
                case ')':
                    setTextHaloWidthTransition(symbolLayer, styleValueForKey);
                    break;
                case '*':
                    setIconOpacityTransition(symbolLayer, styleValueForKey);
                    break;
                case '+':
                    setSymbolPlacement(symbolLayer, styleValueForKey);
                    break;
                case ',':
                    setTextRotationAlignment(symbolLayer, styleValueForKey);
                    break;
                case '-':
                    setTextAllowOverlap(symbolLayer, styleValueForKey);
                    break;
                case '.':
                    setIconIgnorePlacement(symbolLayer, styleValueForKey);
                    break;
                case '/':
                    setIconKeepUpright(symbolLayer, styleValueForKey);
                    break;
                case '0':
                    setSymbolAvoidEdges(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    setIconAllowOverlap(symbolLayer, styleValueForKey);
                    break;
                case '2':
                    setIconTranslateTransition(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    setTextOpacityTransition(symbolLayer, styleValueForKey);
                    break;
                case '4':
                    setTextTransform(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                    setTextTranslate(symbolLayer, styleValueForKey);
                    break;
                case '6':
                    setTextAnchor(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_GONE_MARGIN_BASELINE /* 55 */:
                    setIconOptional(symbolLayer, styleValueForKey);
                    break;
                case '8':
                    setIconHaloBlur(symbolLayer, styleValueForKey);
                    break;
                case '9':
                    setTextTranslateTransition(symbolLayer, styleValueForKey);
                    break;
                case Elf64_Ehdr.e_shentsize /* 58 */:
                    setIconHaloColorTransition(symbolLayer, styleValueForKey);
                    break;
                case ';':
                    setTextKeepUpright(symbolLayer, styleValueForKey);
                    break;
                case '<':
                    setTextOffset(symbolLayer, styleValueForKey);
                    break;
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    setIconTextFitPadding(symbolLayer, styleValueForKey);
                    break;
                case Elf64_Ehdr.e_shstrndx /* 62 */:
                    setTextHaloBlurTransition(symbolLayer, styleValueForKey);
                    break;
                case HtmlCompat.FROM_HTML_MODE_COMPACT /* 63 */:
                    setTextRotate(symbolLayer, styleValueForKey);
                    break;
                case '@':
                    setVisibility(symbolLayer, styleValueForKey);
                    break;
                case 'A':
                    setIconHaloColor(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT /* 66 */:
                    setIconHaloWidth(symbolLayer, styleValueForKey);
                    break;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL /* 67 */:
                    setTextHaloColorTransition(symbolLayer, styleValueForKey);
                    break;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void setCircleLayerStyle(CircleLayer circleLayer, RCTMLNStyle rCTMLNStyle) {
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            RCTMLNStyleValue styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1872906981:
                    if (str.equals("circleStrokeColor")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1854622850:
                    if (str.equals("circleStrokeWidth")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1844562061:
                    if (str.equals("circleStrokeWidthTransition")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1643662095:
                    if (str.equals("circleSortKey")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1111393961:
                    if (str.equals("circleBlur")) {
                        c = 4;
                        break;
                    }
                    break;
                case -982470989:
                    if (str.equals("circleTranslateAnchor")) {
                        c = 5;
                        break;
                    }
                    break;
                case -886257381:
                    if (str.equals("circleOpacity")) {
                        c = 6;
                        break;
                    }
                    break;
                case -555842701:
                    if (str.equals("circlePitchAlignment")) {
                        c = 7;
                        break;
                    }
                    break;
                case -156526384:
                    if (str.equals("circleStrokeColorTransition")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -92470157:
                    if (str.equals("circleColor")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 46416396:
                    if (str.equals("circleBlurTransition")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 301410899:
                    if (str.equals("circleTranslateTransition")) {
                        c = 11;
                        break;
                    }
                    break;
                case 414297296:
                    if (str.equals("circleOpacityTransition")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 1204918824:
                    if (str.equals("circleColorTransition")) {
                        c = CharUtils.CR;
                        break;
                    }
                    break;
                case 1230058202:
                    if (str.equals("circlePitchScale")) {
                        c = 14;
                        break;
                    }
                    break;
                case 1805905859:
                    if (str.equals("circleStrokeOpacity")) {
                        c = 15;
                        break;
                    }
                    break;
                case 1844656514:
                    if (str.equals("circleRadius")) {
                        c = 16;
                        break;
                    }
                    break;
                case 1915750519:
                    if (str.equals("circleRadiusTransition")) {
                        c = 17;
                        break;
                    }
                    break;
                case 1941332754:
                    if (str.equals("visibility")) {
                        c = 18;
                        break;
                    }
                    break;
                case 2030477688:
                    if (str.equals("circleStrokeOpacityTransition")) {
                        c = 19;
                        break;
                    }
                    break;
                case 2072362590:
                    if (str.equals("circleTranslate")) {
                        c = 20;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setCircleStrokeColor(circleLayer, styleValueForKey);
                    break;
                case 1:
                    setCircleStrokeWidth(circleLayer, styleValueForKey);
                    break;
                case 2:
                    setCircleStrokeWidthTransition(circleLayer, styleValueForKey);
                    break;
                case 3:
                    setCircleSortKey(circleLayer, styleValueForKey);
                    break;
                case 4:
                    setCircleBlur(circleLayer, styleValueForKey);
                    break;
                case 5:
                    setCircleTranslateAnchor(circleLayer, styleValueForKey);
                    break;
                case 6:
                    setCircleOpacity(circleLayer, styleValueForKey);
                    break;
                case 7:
                    setCirclePitchAlignment(circleLayer, styleValueForKey);
                    break;
                case '\b':
                    setCircleStrokeColorTransition(circleLayer, styleValueForKey);
                    break;
                case '\t':
                    setCircleColor(circleLayer, styleValueForKey);
                    break;
                case '\n':
                    setCircleBlurTransition(circleLayer, styleValueForKey);
                    break;
                case 11:
                    setCircleTranslateTransition(circleLayer, styleValueForKey);
                    break;
                case '\f':
                    setCircleOpacityTransition(circleLayer, styleValueForKey);
                    break;
                case '\r':
                    setCircleColorTransition(circleLayer, styleValueForKey);
                    break;
                case 14:
                    setCirclePitchScale(circleLayer, styleValueForKey);
                    break;
                case 15:
                    setCircleStrokeOpacity(circleLayer, styleValueForKey);
                    break;
                case 16:
                    setCircleRadius(circleLayer, styleValueForKey);
                    break;
                case 17:
                    setCircleRadiusTransition(circleLayer, styleValueForKey);
                    break;
                case 18:
                    setVisibility(circleLayer, styleValueForKey);
                    break;
                case 19:
                    setCircleStrokeOpacityTransition(circleLayer, styleValueForKey);
                    break;
                case 20:
                    setCircleTranslate(circleLayer, styleValueForKey);
                    break;
            }
        }
    }

    public static void setHeatmapLayerStyle(HeatmapLayer heatmapLayer, RCTMLNStyle rCTMLNStyle) {
        RCTMLNStyleValue styleValueForKey;
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            switch (str) {
                case "heatmapOpacityTransition":
                    setHeatmapOpacityTransition(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapColor":
                    setHeatmapColor(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapIntensityTransition":
                    setHeatmapIntensityTransition(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapIntensity":
                    setHeatmapIntensity(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapOpacity":
                    setHeatmapOpacity(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapRadiusTransition":
                    setHeatmapRadiusTransition(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapRadius":
                    setHeatmapRadius(heatmapLayer, styleValueForKey);
                    break;
                case "heatmapWeight":
                    setHeatmapWeight(heatmapLayer, styleValueForKey);
                    break;
                case "visibility":
                    setVisibility(heatmapLayer, styleValueForKey);
                    break;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void setFillExtrusionLayerStyle(final FillExtrusionLayer fillExtrusionLayer, RCTMLNStyle rCTMLNStyle) {
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            final RCTMLNStyleValue styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -2034325811:
                    if (str.equals("fillExtrusionTranslateTransition")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1357538158:
                    if (str.equals("fillExtrusionHeightTransition")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1312838507:
                    if (str.equals("fillExtrusionOpacity")) {
                        c = 2;
                        break;
                    }
                    break;
                case -987754225:
                    if (str.equals("fillExtrusionPatternTransition")) {
                        c = 3;
                        break;
                    }
                    break;
                case -836722662:
                    if (str.equals("fillExtrusionPattern")) {
                        c = 4;
                        break;
                    }
                    break;
                case -747740254:
                    if (str.equals("fillExtrusionColorTransition")) {
                        c = 5;
                        break;
                    }
                    break;
                case -486297977:
                    if (str.equals("fillExtrusionBase")) {
                        c = 6;
                        break;
                    }
                    break;
                case 149793624:
                    if (str.equals("fillExtrusionTranslate")) {
                        c = 7;
                        break;
                    }
                    break;
                case 809631690:
                    if (str.equals("fillExtrusionOpacityTransition")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 994255709:
                    if (str.equals("fillExtrusionHeight")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1097448252:
                    if (str.equals("fillExtrusionBaseTransition")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1289532284:
                    if (str.equals("fillExtrusionVerticalGradient")) {
                        c = 11;
                        break;
                    }
                    break;
                case 1359837229:
                    if (str.equals("fillExtrusionTranslateAnchor")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 1941332754:
                    if (str.equals("visibility")) {
                        c = CharUtils.CR;
                        break;
                    }
                    break;
                case 2105966189:
                    if (str.equals("fillExtrusionColor")) {
                        c = 14;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setFillExtrusionTranslateTransition(fillExtrusionLayer, styleValueForKey);
                    break;
                case 1:
                    setFillExtrusionHeightTransition(fillExtrusionLayer, styleValueForKey);
                    break;
                case 2:
                    setFillExtrusionOpacity(fillExtrusionLayer, styleValueForKey);
                    break;
                case 3:
                    setFillExtrusionPatternTransition(fillExtrusionLayer, styleValueForKey);
                    break;
                case 4:
                    rCTMLNStyle.addImage(styleValueForKey, new DownloadMapImageTask.OnAllImagesLoaded() { // from class: com.maplibre.rctmln.components.styles.RCTMLNStyleFactory.4
                        @Override // com.maplibre.rctmln.utils.DownloadMapImageTask.OnAllImagesLoaded
                        public void onAllImagesLoaded() {
                            RCTMLNStyleFactory.setFillExtrusionPattern(fillExtrusionLayer, styleValueForKey);
                        }
                    });
                    break;
                case 5:
                    setFillExtrusionColorTransition(fillExtrusionLayer, styleValueForKey);
                    break;
                case 6:
                    setFillExtrusionBase(fillExtrusionLayer, styleValueForKey);
                    break;
                case 7:
                    setFillExtrusionTranslate(fillExtrusionLayer, styleValueForKey);
                    break;
                case '\b':
                    setFillExtrusionOpacityTransition(fillExtrusionLayer, styleValueForKey);
                    break;
                case '\t':
                    setFillExtrusionHeight(fillExtrusionLayer, styleValueForKey);
                    break;
                case '\n':
                    setFillExtrusionBaseTransition(fillExtrusionLayer, styleValueForKey);
                    break;
                case 11:
                    setFillExtrusionVerticalGradient(fillExtrusionLayer, styleValueForKey);
                    break;
                case '\f':
                    setFillExtrusionTranslateAnchor(fillExtrusionLayer, styleValueForKey);
                    break;
                case '\r':
                    setVisibility(fillExtrusionLayer, styleValueForKey);
                    break;
                case 14:
                    setFillExtrusionColor(fillExtrusionLayer, styleValueForKey);
                    break;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void setRasterLayerStyle(RasterLayer rasterLayer, RCTMLNStyle rCTMLNStyle) {
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            RCTMLNStyleValue styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1918733362:
                    if (str.equals("rasterOpacity")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1916423978:
                    if (str.equals("rasterBrightnessMax")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1916423740:
                    if (str.equals("rasterBrightnessMin")) {
                        c = 2;
                        break;
                    }
                    break;
                case -1806875529:
                    if (str.equals("rasterResampling")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1650170165:
                    if (str.equals("rasterBrightnessMaxTransition")) {
                        c = 4;
                        break;
                    }
                    break;
                case -920959697:
                    if (str.equals("rasterSaturation")) {
                        c = 5;
                        break;
                    }
                    break;
                case 126079939:
                    if (str.equals("rasterOpacityTransition")) {
                        c = 6;
                        break;
                    }
                    break;
                case 172135993:
                    if (str.equals("rasterBrightnessMinTransition")) {
                        c = 7;
                        break;
                    }
                    break;
                case 710545311:
                    if (str.equals("rasterContrast")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 1598446454:
                    if (str.equals("rasterHueRotate")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1838358500:
                    if (str.equals("rasterSaturationTransition")) {
                        c = '\n';
                        break;
                    }
                    break;
                case 1919655508:
                    if (str.equals("rasterContrastTransition")) {
                        c = 11;
                        break;
                    }
                    break;
                case 1941332754:
                    if (str.equals("visibility")) {
                        c = '\f';
                        break;
                    }
                    break;
                case 1982444109:
                    if (str.equals("rasterFadeDuration")) {
                        c = CharUtils.CR;
                        break;
                    }
                    break;
                case 2093500779:
                    if (str.equals("rasterHueRotateTransition")) {
                        c = 14;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    setRasterOpacity(rasterLayer, styleValueForKey);
                    break;
                case 1:
                    setRasterBrightnessMax(rasterLayer, styleValueForKey);
                    break;
                case 2:
                    setRasterBrightnessMin(rasterLayer, styleValueForKey);
                    break;
                case 3:
                    setRasterResampling(rasterLayer, styleValueForKey);
                    break;
                case 4:
                    setRasterBrightnessMaxTransition(rasterLayer, styleValueForKey);
                    break;
                case 5:
                    setRasterSaturation(rasterLayer, styleValueForKey);
                    break;
                case 6:
                    setRasterOpacityTransition(rasterLayer, styleValueForKey);
                    break;
                case 7:
                    setRasterBrightnessMinTransition(rasterLayer, styleValueForKey);
                    break;
                case '\b':
                    setRasterContrast(rasterLayer, styleValueForKey);
                    break;
                case '\t':
                    setRasterHueRotate(rasterLayer, styleValueForKey);
                    break;
                case '\n':
                    setRasterSaturationTransition(rasterLayer, styleValueForKey);
                    break;
                case 11:
                    setRasterContrastTransition(rasterLayer, styleValueForKey);
                    break;
                case '\f':
                    setVisibility(rasterLayer, styleValueForKey);
                    break;
                case '\r':
                    setRasterFadeDuration(rasterLayer, styleValueForKey);
                    break;
                case 14:
                    setRasterHueRotateTransition(rasterLayer, styleValueForKey);
                    break;
            }
        }
    }

    public static void setHillshadeLayerStyle(HillshadeLayer hillshadeLayer, RCTMLNStyle rCTMLNStyle) {
        RCTMLNStyleValue styleValueForKey;
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            switch (str) {
                case "hillshadeHighlightColorTransition":
                    setHillshadeHighlightColorTransition(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeShadowColorTransition":
                    setHillshadeShadowColorTransition(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeAccentColorTransition":
                    setHillshadeAccentColorTransition(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeExaggeration":
                    setHillshadeExaggeration(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeHighlightColor":
                    setHillshadeHighlightColor(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeExaggerationTransition":
                    setHillshadeExaggerationTransition(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeIlluminationDirection":
                    setHillshadeIlluminationDirection(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeShadowColor":
                    setHillshadeShadowColor(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeAccentColor":
                    setHillshadeAccentColor(hillshadeLayer, styleValueForKey);
                    break;
                case "hillshadeIlluminationAnchor":
                    setHillshadeIlluminationAnchor(hillshadeLayer, styleValueForKey);
                    break;
                case "visibility":
                    setVisibility(hillshadeLayer, styleValueForKey);
                    break;
            }
        }
    }

    public static void setBackgroundLayerStyle(final BackgroundLayer backgroundLayer, RCTMLNStyle rCTMLNStyle) {
        final RCTMLNStyleValue styleValueForKey;
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            switch (str) {
                case "backgroundPattern":
                    rCTMLNStyle.addImage(styleValueForKey, new DownloadMapImageTask.OnAllImagesLoaded() { // from class: com.maplibre.rctmln.components.styles.RCTMLNStyleFactory.5
                        @Override // com.maplibre.rctmln.utils.DownloadMapImageTask.OnAllImagesLoaded
                        public void onAllImagesLoaded() {
                            RCTMLNStyleFactory.setBackgroundPattern(backgroundLayer, styleValueForKey);
                        }
                    });
                    break;
                case "backgroundPatternTransition":
                    setBackgroundPatternTransition(backgroundLayer, styleValueForKey);
                    break;
                case "backgroundColorTransition":
                    setBackgroundColorTransition(backgroundLayer, styleValueForKey);
                    break;
                case "backgroundOpacityTransition":
                    setBackgroundOpacityTransition(backgroundLayer, styleValueForKey);
                    break;
                case "backgroundColor":
                    setBackgroundColor(backgroundLayer, styleValueForKey);
                    break;
                case "visibility":
                    setVisibility(backgroundLayer, styleValueForKey);
                    break;
                case "backgroundOpacity":
                    setBackgroundOpacity(backgroundLayer, styleValueForKey);
                    break;
            }
        }
    }

    public static void setLightLayerStyle(Light light, RCTMLNStyle rCTMLNStyle) {
        RCTMLNStyleValue styleValueForKey;
        List<String> allStyleKeys = rCTMLNStyle.getAllStyleKeys();
        if (allStyleKeys.size() == 0) {
            return;
        }
        for (String str : allStyleKeys) {
            styleValueForKey = rCTMLNStyle.getStyleValueForKey(str);
            str.hashCode();
            switch (str) {
                case "anchor":
                    setAnchor(light, styleValueForKey);
                    break;
                case "positionTransition":
                    setPositionTransition(light, styleValueForKey);
                    break;
                case "colorTransition":
                    setColorTransition(light, styleValueForKey);
                    break;
                case "color":
                    setColor(light, styleValueForKey);
                    break;
                case "intensity":
                    setIntensity(light, styleValueForKey);
                    break;
                case "position":
                    setPosition(light, styleValueForKey);
                    break;
                case "intensityTransition":
                    setIntensityTransition(light, styleValueForKey);
                    break;
            }
        }
    }

    public static void setFillSortKey(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillSortKey(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillSortKey(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setVisibility(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        fillLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setFillAntialias(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillAntialias(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillAntialias(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setFillOpacity(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setFillOpacityTransition(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillLayer.setFillOpacityTransition(transition);
        }
    }

    public static void setFillColor(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillColor(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setFillColorTransition(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillLayer.setFillColorTransition(transition);
        }
    }

    public static void setFillOutlineColor(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillOutlineColor(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillOutlineColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setFillOutlineColorTransition(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillLayer.setFillOutlineColorTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setFillTranslate(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillTranslate(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillTranslate(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setFillTranslateTransition(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillLayer.setFillTranslateTransition(transition);
        }
    }

    public static void setFillTranslateAnchor(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillTranslateAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillTranslateAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setFillPattern(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (!rCTMLNStyleValue.isExpression()) {
            fillLayer.setProperties(PropertyFactory.fillPattern(rCTMLNStyleValue.getImageURI()));
        } else if (rCTMLNStyleValue.isImageStringValue().booleanValue()) {
            fillLayer.setProperties(PropertyFactory.fillPattern(rCTMLNStyleValue.getImageStringValue()));
        } else {
            fillLayer.setProperties(PropertyFactory.fillPattern(rCTMLNStyleValue.getExpression()));
        }
    }

    public static void setFillPatternTransition(FillLayer fillLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillLayer.setFillPatternTransition(transition);
        }
    }

    public static void setLineCap(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineCap(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineCap(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setLineJoin(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineJoin(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineJoin(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setLineMiterLimit(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineMiterLimit(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineMiterLimit(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineRoundLimit(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineRoundLimit(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineRoundLimit(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineSortKey(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineSortKey(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineSortKey(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setVisibility(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        lineLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setLineOpacity(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineOpacityTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineOpacityTransition(transition);
        }
    }

    public static void setLineColor(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineColor(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setLineColorTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineColorTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setLineTranslate(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineTranslate(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineTranslate(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setLineTranslateTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineTranslateTransition(transition);
        }
    }

    public static void setLineTranslateAnchor(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineTranslateAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineTranslateAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setLineWidth(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineWidth(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineWidth(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineWidthTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineWidthTransition(transition);
        }
    }

    public static void setLineGapWidth(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineGapWidth(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineGapWidth(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineGapWidthTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineGapWidthTransition(transition);
        }
    }

    public static void setLineOffset(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineOffset(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineOffset(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineOffsetTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineOffsetTransition(transition);
        }
    }

    public static void setLineBlur(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineBlur(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineBlur(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setLineBlurTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineBlurTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setLineDasharray(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineDasharray(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineDasharray(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setLineDasharrayTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLineDasharrayTransition(transition);
        }
    }

    public static void setLinePattern(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (!rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.linePattern(rCTMLNStyleValue.getImageURI()));
        } else if (rCTMLNStyleValue.isImageStringValue().booleanValue()) {
            lineLayer.setProperties(PropertyFactory.linePattern(rCTMLNStyleValue.getImageStringValue()));
        } else {
            lineLayer.setProperties(PropertyFactory.linePattern(rCTMLNStyleValue.getExpression()));
        }
    }

    public static void setLinePatternTransition(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            lineLayer.setLinePatternTransition(transition);
        }
    }

    public static void setLineGradient(LineLayer lineLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            lineLayer.setProperties(PropertyFactory.lineGradient(rCTMLNStyleValue.getExpression()));
        } else {
            lineLayer.setProperties(PropertyFactory.lineGradient(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setSymbolPlacement(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.symbolPlacement(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.symbolPlacement(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setSymbolSpacing(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.symbolSpacing(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.symbolSpacing(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setSymbolAvoidEdges(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.symbolAvoidEdges(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.symbolAvoidEdges(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setSymbolSortKey(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.symbolSortKey(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.symbolSortKey(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setSymbolZOrder(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.symbolZOrder(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.symbolZOrder(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setIconAllowOverlap(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconAllowOverlap(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconAllowOverlap(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setIconIgnorePlacement(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconIgnorePlacement(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconIgnorePlacement(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setIconOptional(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconOptional(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconOptional(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setIconRotationAlignment(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconRotationAlignment(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconRotationAlignment(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setIconSize(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconSize(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconSize(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setIconTextFit(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconTextFit(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconTextFit(rCTMLNStyleValue.getString("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setIconTextFitPadding(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconTextFitPadding(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconTextFitPadding(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setIconImage(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (!rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconImage(rCTMLNStyleValue.getImageURI()));
        } else if (rCTMLNStyleValue.isImageStringValue().booleanValue()) {
            symbolLayer.setProperties(PropertyFactory.iconImage(rCTMLNStyleValue.getImageStringValue()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconImage(rCTMLNStyleValue.getExpression()));
        }
    }

    public static void setIconRotate(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconRotate(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconRotate(rCTMLNStyleValue.getFloat("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setIconPadding(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconPadding(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconPadding(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setIconKeepUpright(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconKeepUpright(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconKeepUpright(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setIconOffset(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconOffset(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconOffset(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setIconAnchor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setIconPitchAlignment(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconPitchAlignment(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconPitchAlignment(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setTextPitchAlignment(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textPitchAlignment(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textPitchAlignment(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setTextRotationAlignment(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textRotationAlignment(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textRotationAlignment(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setTextField(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textField(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textField(rCTMLNStyleValue.getString("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setTextFont(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textFont(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textFont(rCTMLNStyleValue.getStringArray("value")));
        }
    }

    public static void setTextSize(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textSize(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textSize(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextMaxWidth(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textMaxWidth(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textMaxWidth(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextLineHeight(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textLineHeight(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textLineHeight(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextLetterSpacing(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textLetterSpacing(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textLetterSpacing(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextJustify(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textJustify(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textJustify(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setTextRadialOffset(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textRadialOffset(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textRadialOffset(rCTMLNStyleValue.getFloat("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setTextVariableAnchor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textVariableAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textVariableAnchor(rCTMLNStyleValue.getStringArray("value")));
        }
    }

    public static void setTextAnchor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setTextMaxAngle(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textMaxAngle(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textMaxAngle(rCTMLNStyleValue.getFloat("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setTextWritingMode(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textWritingMode(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textWritingMode(rCTMLNStyleValue.getStringArray("value")));
        }
    }

    public static void setTextRotate(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textRotate(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textRotate(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextPadding(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textPadding(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textPadding(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextKeepUpright(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textKeepUpright(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textKeepUpright(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setTextTransform(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textTransform(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textTransform(rCTMLNStyleValue.getString("value")));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setTextOffset(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textOffset(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textOffset(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setTextAllowOverlap(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textAllowOverlap(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textAllowOverlap(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setTextIgnorePlacement(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textIgnorePlacement(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textIgnorePlacement(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setTextOptional(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textOptional(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textOptional(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setVisibility(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        symbolLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setIconOpacity(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setIconOpacityTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setIconOpacityTransition(transition);
        }
    }

    public static void setIconColor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconColor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setIconColorTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setIconColorTransition(transition);
        }
    }

    public static void setIconHaloColor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconHaloColor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconHaloColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setIconHaloColorTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setIconHaloColorTransition(transition);
        }
    }

    public static void setIconHaloWidth(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconHaloWidth(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconHaloWidth(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setIconHaloWidthTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setIconHaloWidthTransition(transition);
        }
    }

    public static void setIconHaloBlur(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconHaloBlur(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconHaloBlur(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setIconHaloBlurTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setIconHaloBlurTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setIconTranslate(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconTranslate(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconTranslate(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setIconTranslateTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setIconTranslateTransition(transition);
        }
    }

    public static void setIconTranslateAnchor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.iconTranslateAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.iconTranslateAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setTextOpacity(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextOpacityTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setTextOpacityTransition(transition);
        }
    }

    public static void setTextColor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textColor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setTextColorTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setTextColorTransition(transition);
        }
    }

    public static void setTextHaloColor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textHaloColor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textHaloColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setTextHaloColorTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setTextHaloColorTransition(transition);
        }
    }

    public static void setTextHaloWidth(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textHaloWidth(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textHaloWidth(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextHaloWidthTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setTextHaloWidthTransition(transition);
        }
    }

    public static void setTextHaloBlur(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textHaloBlur(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textHaloBlur(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setTextHaloBlurTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setTextHaloBlurTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setTextTranslate(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textTranslate(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textTranslate(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setTextTranslateTransition(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            symbolLayer.setTextTranslateTransition(transition);
        }
    }

    public static void setTextTranslateAnchor(SymbolLayer symbolLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            symbolLayer.setProperties(PropertyFactory.textTranslateAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            symbolLayer.setProperties(PropertyFactory.textTranslateAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setCircleSortKey(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleSortKey(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleSortKey(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setVisibility(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        circleLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setCircleRadius(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleRadius(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleRadius(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setCircleRadiusTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleRadiusTransition(transition);
        }
    }

    public static void setCircleColor(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleColor(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setCircleColorTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleColorTransition(transition);
        }
    }

    public static void setCircleBlur(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleBlur(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleBlur(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setCircleBlurTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleBlurTransition(transition);
        }
    }

    public static void setCircleOpacity(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setCircleOpacityTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleOpacityTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setCircleTranslate(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleTranslate(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleTranslate(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setCircleTranslateTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleTranslateTransition(transition);
        }
    }

    public static void setCircleTranslateAnchor(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleTranslateAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleTranslateAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setCirclePitchScale(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circlePitchScale(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circlePitchScale(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setCirclePitchAlignment(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circlePitchAlignment(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circlePitchAlignment(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setCircleStrokeWidth(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleStrokeWidth(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleStrokeWidth(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setCircleStrokeWidthTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleStrokeWidthTransition(transition);
        }
    }

    public static void setCircleStrokeColor(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleStrokeColor(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleStrokeColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setCircleStrokeColorTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleStrokeColorTransition(transition);
        }
    }

    public static void setCircleStrokeOpacity(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            circleLayer.setProperties(PropertyFactory.circleStrokeOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            circleLayer.setProperties(PropertyFactory.circleStrokeOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setCircleStrokeOpacityTransition(CircleLayer circleLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            circleLayer.setCircleStrokeOpacityTransition(transition);
        }
    }

    public static void setVisibility(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        heatmapLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setHeatmapRadius(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            heatmapLayer.setProperties(PropertyFactory.heatmapRadius(rCTMLNStyleValue.getExpression()));
        } else {
            heatmapLayer.setProperties(PropertyFactory.heatmapRadius(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setHeatmapRadiusTransition(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            heatmapLayer.setHeatmapRadiusTransition(transition);
        }
    }

    public static void setHeatmapWeight(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            heatmapLayer.setProperties(PropertyFactory.heatmapWeight(rCTMLNStyleValue.getExpression()));
        } else {
            heatmapLayer.setProperties(PropertyFactory.heatmapWeight(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setHeatmapIntensity(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            heatmapLayer.setProperties(PropertyFactory.heatmapIntensity(rCTMLNStyleValue.getExpression()));
        } else {
            heatmapLayer.setProperties(PropertyFactory.heatmapIntensity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setHeatmapIntensityTransition(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            heatmapLayer.setHeatmapIntensityTransition(transition);
        }
    }

    public static void setHeatmapColor(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            heatmapLayer.setProperties(PropertyFactory.heatmapColor(rCTMLNStyleValue.getExpression()));
        } else {
            heatmapLayer.setProperties(PropertyFactory.heatmapColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setHeatmapOpacity(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            heatmapLayer.setProperties(PropertyFactory.heatmapOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            heatmapLayer.setProperties(PropertyFactory.heatmapOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setHeatmapOpacityTransition(HeatmapLayer heatmapLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            heatmapLayer.setHeatmapOpacityTransition(transition);
        }
    }

    public static void setVisibility(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        fillExtrusionLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setFillExtrusionOpacity(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setFillExtrusionOpacityTransition(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillExtrusionLayer.setFillExtrusionOpacityTransition(transition);
        }
    }

    public static void setFillExtrusionColor(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionColor(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setFillExtrusionColorTransition(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillExtrusionLayer.setFillExtrusionColorTransition(transition);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void setFillExtrusionTranslate(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionTranslate(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionTranslate(rCTMLNStyleValue.getFloatArray("value")));
        }
    }

    public static void setFillExtrusionTranslateTransition(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillExtrusionLayer.setFillExtrusionTranslateTransition(transition);
        }
    }

    public static void setFillExtrusionTranslateAnchor(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionTranslateAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionTranslateAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setFillExtrusionPattern(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (!rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionPattern(rCTMLNStyleValue.getImageURI()));
        } else if (rCTMLNStyleValue.isImageStringValue().booleanValue()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionPattern(rCTMLNStyleValue.getImageStringValue()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionPattern(rCTMLNStyleValue.getExpression()));
        }
    }

    public static void setFillExtrusionPatternTransition(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillExtrusionLayer.setFillExtrusionPatternTransition(transition);
        }
    }

    public static void setFillExtrusionHeight(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionHeight(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionHeight(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setFillExtrusionHeightTransition(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillExtrusionLayer.setFillExtrusionHeightTransition(transition);
        }
    }

    public static void setFillExtrusionBase(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionBase(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionBase(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setFillExtrusionBaseTransition(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            fillExtrusionLayer.setFillExtrusionBaseTransition(transition);
        }
    }

    public static void setFillExtrusionVerticalGradient(FillExtrusionLayer fillExtrusionLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionVerticalGradient(rCTMLNStyleValue.getExpression()));
        } else {
            fillExtrusionLayer.setProperties(PropertyFactory.fillExtrusionVerticalGradient(rCTMLNStyleValue.getBoolean("value")));
        }
    }

    public static void setVisibility(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        rasterLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setRasterOpacity(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setRasterOpacityTransition(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            rasterLayer.setRasterOpacityTransition(transition);
        }
    }

    public static void setRasterHueRotate(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterHueRotate(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterHueRotate(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setRasterHueRotateTransition(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            rasterLayer.setRasterHueRotateTransition(transition);
        }
    }

    public static void setRasterBrightnessMin(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterBrightnessMin(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterBrightnessMin(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setRasterBrightnessMinTransition(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            rasterLayer.setRasterBrightnessMinTransition(transition);
        }
    }

    public static void setRasterBrightnessMax(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterBrightnessMax(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterBrightnessMax(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setRasterBrightnessMaxTransition(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            rasterLayer.setRasterBrightnessMaxTransition(transition);
        }
    }

    public static void setRasterSaturation(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterSaturation(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterSaturation(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setRasterSaturationTransition(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            rasterLayer.setRasterSaturationTransition(transition);
        }
    }

    public static void setRasterContrast(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterContrast(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterContrast(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setRasterContrastTransition(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            rasterLayer.setRasterContrastTransition(transition);
        }
    }

    public static void setRasterResampling(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterResampling(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterResampling(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setRasterFadeDuration(RasterLayer rasterLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            rasterLayer.setProperties(PropertyFactory.rasterFadeDuration(rCTMLNStyleValue.getExpression()));
        } else {
            rasterLayer.setProperties(PropertyFactory.rasterFadeDuration(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setVisibility(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        hillshadeLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setHillshadeIlluminationDirection(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeIlluminationDirection(rCTMLNStyleValue.getExpression()));
        } else {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeIlluminationDirection(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setHillshadeIlluminationAnchor(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeIlluminationAnchor(rCTMLNStyleValue.getExpression()));
        } else {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeIlluminationAnchor(rCTMLNStyleValue.getString("value")));
        }
    }

    public static void setHillshadeExaggeration(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeExaggeration(rCTMLNStyleValue.getExpression()));
        } else {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeExaggeration(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setHillshadeExaggerationTransition(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            hillshadeLayer.setHillshadeExaggerationTransition(transition);
        }
    }

    public static void setHillshadeShadowColor(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeShadowColor(rCTMLNStyleValue.getExpression()));
        } else {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeShadowColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setHillshadeShadowColorTransition(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            hillshadeLayer.setHillshadeShadowColorTransition(transition);
        }
    }

    public static void setHillshadeHighlightColor(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeHighlightColor(rCTMLNStyleValue.getExpression()));
        } else {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeHighlightColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setHillshadeHighlightColorTransition(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            hillshadeLayer.setHillshadeHighlightColorTransition(transition);
        }
    }

    public static void setHillshadeAccentColor(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeAccentColor(rCTMLNStyleValue.getExpression()));
        } else {
            hillshadeLayer.setProperties(PropertyFactory.hillshadeAccentColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setHillshadeAccentColorTransition(HillshadeLayer hillshadeLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            hillshadeLayer.setHillshadeAccentColorTransition(transition);
        }
    }

    public static void setVisibility(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        backgroundLayer.setProperties(PropertyFactory.visibility(rCTMLNStyleValue.getString("value")));
    }

    public static void setBackgroundColor(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            backgroundLayer.setProperties(PropertyFactory.backgroundColor(rCTMLNStyleValue.getExpression()));
        } else {
            backgroundLayer.setProperties(PropertyFactory.backgroundColor(rCTMLNStyleValue.getInt("value")));
        }
    }

    public static void setBackgroundColorTransition(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            backgroundLayer.setBackgroundColorTransition(transition);
        }
    }

    public static void setBackgroundPattern(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (!rCTMLNStyleValue.isExpression()) {
            backgroundLayer.setProperties(PropertyFactory.backgroundPattern(rCTMLNStyleValue.getImageURI()));
        } else if (rCTMLNStyleValue.isImageStringValue().booleanValue()) {
            backgroundLayer.setProperties(PropertyFactory.backgroundPattern(rCTMLNStyleValue.getImageStringValue()));
        } else {
            backgroundLayer.setProperties(PropertyFactory.backgroundPattern(rCTMLNStyleValue.getExpression()));
        }
    }

    public static void setBackgroundPatternTransition(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            backgroundLayer.setBackgroundPatternTransition(transition);
        }
    }

    public static void setBackgroundOpacity(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        if (rCTMLNStyleValue.isExpression()) {
            backgroundLayer.setProperties(PropertyFactory.backgroundOpacity(rCTMLNStyleValue.getExpression()));
        } else {
            backgroundLayer.setProperties(PropertyFactory.backgroundOpacity(rCTMLNStyleValue.getFloat("value")));
        }
    }

    public static void setBackgroundOpacityTransition(BackgroundLayer backgroundLayer, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            backgroundLayer.setBackgroundOpacityTransition(transition);
        }
    }

    public static void setAnchor(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        light.setAnchor(rCTMLNStyleValue.getString("value"));
    }

    public static void setPosition(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        Float[] floatArray = rCTMLNStyleValue.getFloatArray("value");
        light.setPosition(Position.fromPosition(floatArray[0].floatValue(), floatArray[1].floatValue(), floatArray[2].floatValue()));
    }

    public static void setPositionTransition(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            light.setPositionTransition(transition);
        }
    }

    public static void setColor(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        light.setColor(rCTMLNStyleValue.getInt("value"));
    }

    public static void setColorTransition(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            light.setColorTransition(transition);
        }
    }

    public static void setIntensity(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        light.setIntensity(rCTMLNStyleValue.getFloat("value").floatValue());
    }

    public static void setIntensityTransition(Light light, RCTMLNStyleValue rCTMLNStyleValue) {
        TransitionOptions transition = rCTMLNStyleValue.getTransition();
        if (transition != null) {
            light.setIntensityTransition(transition);
        }
    }
}
