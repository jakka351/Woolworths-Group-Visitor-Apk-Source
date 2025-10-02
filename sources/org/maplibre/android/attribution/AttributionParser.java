package org.maplibre.android.attribution;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.URLSpan;
import java.lang.ref.WeakReference;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class AttributionParser {
    private final String attributionData;
    private final Set<Attribution> attributions = new LinkedHashSet();
    private final WeakReference<Context> context;
    private final boolean withCopyrightSign;
    private final boolean withImproveMap;
    private final boolean withMapboxAttribution;

    AttributionParser(WeakReference<Context> weakReference, String str, boolean z, boolean z2, boolean z3) {
        this.context = weakReference;
        this.attributionData = str;
        this.withImproveMap = z;
        this.withCopyrightSign = z2;
        this.withMapboxAttribution = z3;
    }

    public Set<Attribution> getAttributions() {
        return this.attributions;
    }

    public String createAttributionString() {
        return createAttributionString(false);
    }

    public String createAttributionString(boolean z) {
        StringBuilder sb = new StringBuilder(this.withCopyrightSign ? "" : "© ");
        int i = 0;
        for (Attribution attribution : this.attributions) {
            i++;
            sb.append(!z ? attribution.getTitle() : attribution.getTitleAbbreviated());
            if (i != this.attributions.size()) {
                sb.append(" / ");
            }
        }
        return sb.toString();
    }

    protected void parse() {
        parseAttributions();
    }

    private void parseAttributions() {
        SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) fromHtml(this.attributionData);
        for (URLSpan uRLSpan : (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class)) {
            parseUrlSpan(spannableStringBuilder, uRLSpan);
        }
    }

    private void parseUrlSpan(SpannableStringBuilder spannableStringBuilder, URLSpan uRLSpan) {
        String url = uRLSpan.getURL();
        if (isUrlValid(url)) {
            this.attributions.add(new Attribution(parseAnchorValue(spannableStringBuilder, uRLSpan), url));
        }
    }

    private boolean isUrlValid(String str) {
        return isValidForImproveThisMap(str) && isValidForMapbox(str);
    }

    private boolean isValidForImproveThisMap(String str) {
        return this.withImproveMap || !Attribution.IMPROVE_MAP_URLS.contains(str);
    }

    private boolean isValidForMapbox(String str) {
        return this.withMapboxAttribution || !str.equals("https://www.mapbox.com/about/maps/");
    }

    private String parseAnchorValue(SpannableStringBuilder spannableStringBuilder, URLSpan uRLSpan) {
        int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
        int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
        char[] cArr = new char[spanEnd - spanStart];
        spannableStringBuilder.getChars(spanStart, spanEnd, cArr, 0);
        return stripCopyright(String.valueOf(cArr));
    }

    private String stripCopyright(String str) {
        return (this.withCopyrightSign || !str.startsWith("© ")) ? str : str.substring(2, str.length());
    }

    private static Spanned fromHtml(String str) {
        return Html.fromHtml(str, 0);
    }

    public static class Options {
        private String[] attributionDataStringArray;
        private final WeakReference<Context> context;
        private boolean withImproveMap = true;
        private boolean withCopyrightSign = true;
        private boolean withMapboxAttribution = true;

        public Options(Context context) {
            this.context = new WeakReference<>(context);
        }

        public Options withAttributionData(String... strArr) {
            this.attributionDataStringArray = strArr;
            return this;
        }

        public Options withImproveMap(boolean z) {
            this.withImproveMap = z;
            return this;
        }

        public Options withCopyrightSign(boolean z) {
            this.withCopyrightSign = z;
            return this;
        }

        public Options withMapboxAttribution(boolean z) {
            this.withMapboxAttribution = z;
            return this;
        }

        public AttributionParser build() {
            String[] strArr = this.attributionDataStringArray;
            if (strArr == null) {
                throw new IllegalStateException("Using builder without providing attribution data");
            }
            AttributionParser attributionParser = new AttributionParser(this.context, parseAttribution(strArr), this.withImproveMap, this.withCopyrightSign, this.withMapboxAttribution);
            attributionParser.parse();
            return attributionParser;
        }

        private String parseAttribution(String[] strArr) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                if (!str.isEmpty()) {
                    sb.append(str);
                }
            }
            return sb.toString();
        }
    }
}
