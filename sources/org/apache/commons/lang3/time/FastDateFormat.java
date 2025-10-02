package org.apache.commons.lang3.time;

import com.facebook.imagepipeline.common.RotationOptions;
import com.maplibre.rctmln.components.styles.sources.RCTMLNShapeSourceManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.Validate;

/* loaded from: classes2.dex */
public class FastDateFormat extends Format {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;
    private static final FormatCache<FastDateFormat> cache = new FormatCache<FastDateFormat>() { // from class: org.apache.commons.lang3.time.FastDateFormat.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.lang3.time.FormatCache
        public FastDateFormat createInstance(String str, TimeZone timeZone, Locale locale) {
            return new FastDateFormat(str, timeZone, locale);
        }
    };
    private static ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);

    private interface NumberRule extends Rule {
        void appendTo(StringBuffer stringBuffer, int i);
    }

    private interface Rule {
        void appendTo(StringBuffer stringBuffer, Calendar calendar);

        int estimateLength();
    }

    public static FastDateFormat getInstance() {
        return (FastDateFormat) cache.getDateTimeInstance(3, 3, null, null);
    }

    public static FastDateFormat getInstance(String str) {
        return (FastDateFormat) cache.getInstance(str, null, null);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone) {
        return (FastDateFormat) cache.getInstance(str, timeZone, null);
    }

    public static FastDateFormat getInstance(String str, Locale locale) {
        return (FastDateFormat) cache.getInstance(str, null, locale);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getInstance(str, timeZone, locale);
    }

    public static FastDateFormat getDateInstance(int i) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, null, null);
    }

    public static FastDateFormat getDateInstance(int i, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, null, locale);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, timeZone, null);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, timeZone, locale);
    }

    public static FastDateFormat getTimeInstance(int i) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), null, null);
    }

    public static FastDateFormat getTimeInstance(int i, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), null, locale);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), timeZone, null);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), timeZone, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), null, null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), null, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone) {
        return getDateTimeInstance(i, i2, timeZone, null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), timeZone, locale);
    }

    static String getTimeZoneDisplay(TimeZone timeZone, boolean z, int i, Locale locale) {
        TimeZoneDisplayKey timeZoneDisplayKey = new TimeZoneDisplayKey(timeZone, z, i, locale);
        String str = cTimeZoneDisplayCache.get(timeZoneDisplayKey);
        if (str != null) {
            return str;
        }
        String displayName = timeZone.getDisplayName(z, i, locale);
        String strPutIfAbsent = cTimeZoneDisplayCache.putIfAbsent(timeZoneDisplayKey, displayName);
        return strPutIfAbsent != null ? strPutIfAbsent : displayName;
    }

    protected FastDateFormat(String str, TimeZone timeZone, Locale locale) {
        this.mPattern = str;
        this.mTimeZone = timeZone;
        this.mLocale = locale;
        init();
    }

    private void init() {
        List<Rule> pattern = parsePattern();
        Rule[] ruleArr = (Rule[]) pattern.toArray(new Rule[pattern.size()]);
        this.mRules = ruleArr;
        int length = ruleArr.length;
        int iEstimateLength = 0;
        while (true) {
            length--;
            if (length >= 0) {
                iEstimateLength += this.mRules[length].estimateLength();
            } else {
                this.mMaxLengthEstimate = iEstimateLength;
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v13, types: [org.apache.commons.lang3.time.FastDateFormat$StringLiteral] */
    /* JADX WARN: Type inference failed for: r9v14, types: [org.apache.commons.lang3.time.FastDateFormat$CharacterLiteral] */
    /* JADX WARN: Type inference failed for: r9v20, types: [org.apache.commons.lang3.time.FastDateFormat$TextField] */
    /* JADX WARN: Type inference failed for: r9v21, types: [org.apache.commons.lang3.time.FastDateFormat$TextField] */
    /* JADX WARN: Type inference failed for: r9v26, types: [org.apache.commons.lang3.time.FastDateFormat$TimeZoneNumberRule] */
    /* JADX WARN: Type inference failed for: r9v27, types: [org.apache.commons.lang3.time.FastDateFormat$TimeZoneNumberRule] */
    /* JADX WARN: Type inference failed for: r9v28, types: [org.apache.commons.lang3.time.FastDateFormat$TextField] */
    /* JADX WARN: Type inference failed for: r9v43, types: [org.apache.commons.lang3.time.FastDateFormat$TextField] */
    /* JADX WARN: Type inference failed for: r9v46, types: [org.apache.commons.lang3.time.FastDateFormat$TextField] */
    /* JADX WARN: Type inference failed for: r9v8, types: [org.apache.commons.lang3.time.FastDateFormat$TimeZoneNameRule] */
    /* JADX WARN: Type inference failed for: r9v9, types: [org.apache.commons.lang3.time.FastDateFormat$TimeZoneNameRule] */
    protected List<Rule> parsePattern() {
        int i;
        NumberRule numberRuleSelectNumberRule;
        TwoDigitYearField timeZoneNameRule;
        NumberRule stringLiteral;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.mLocale);
        ArrayList arrayList = new ArrayList();
        String[] eras = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.mPattern.length();
        int[] iArr = new int[1];
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            iArr[i2] = i3;
            String token = parseToken(this.mPattern, iArr);
            int i4 = iArr[i2];
            int length2 = token.length();
            if (length2 == 0) {
                return arrayList;
            }
            char cCharAt = token.charAt(i2);
            if (cCharAt == 'y') {
                i = 0;
                if (length2 == 2) {
                    timeZoneNameRule = TwoDigitYearField.INSTANCE;
                    numberRuleSelectNumberRule = timeZoneNameRule;
                } else {
                    if (length2 < 4) {
                        length2 = 4;
                    }
                    numberRuleSelectNumberRule = selectNumberRule(1, length2);
                }
            } else if (cCharAt != 'z') {
                switch (cCharAt) {
                    case '\'':
                        String strSubstring = token.substring(1);
                        if (strSubstring.length() == 1) {
                            stringLiteral = new CharacterLiteral(strSubstring.charAt(0));
                        } else {
                            stringLiteral = new StringLiteral(strSubstring);
                        }
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'K':
                        stringLiteral = selectNumberRule(10, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'M':
                        if (length2 >= 4) {
                            stringLiteral = new TextField(2, months);
                        } else if (length2 == 3) {
                            stringLiteral = new TextField(2, shortMonths);
                        } else if (length2 == 2) {
                            stringLiteral = TwoDigitMonthField.INSTANCE;
                        } else {
                            stringLiteral = UnpaddedMonthField.INSTANCE;
                        }
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'S':
                        stringLiteral = selectNumberRule(14, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'W':
                        stringLiteral = selectNumberRule(4, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case RotationOptions.ROTATE_90 /* 90 */:
                        if (length2 == 1) {
                            stringLiteral = TimeZoneNumberRule.INSTANCE_NO_COLON;
                        } else {
                            stringLiteral = TimeZoneNumberRule.INSTANCE_COLON;
                        }
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'a':
                        stringLiteral = new TextField(9, amPmStrings);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'd':
                        stringLiteral = selectNumberRule(5, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'h':
                        stringLiteral = new TwelveHourField(selectNumberRule(10, length2));
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case RCTMLNShapeSourceManager.METHOD_GET_CLUSTER_EXPANSION_ZOOM_BY_ID /* 107 */:
                        numberRuleSelectNumberRule = new TwentyFourHourField(selectNumberRule(11, length2));
                        i = 0;
                        break;
                    case 'm':
                        stringLiteral = selectNumberRule(12, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 's':
                        stringLiteral = selectNumberRule(13, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    case 'w':
                        stringLiteral = selectNumberRule(3, length2);
                        numberRuleSelectNumberRule = stringLiteral;
                        i = 0;
                        break;
                    default:
                        switch (cCharAt) {
                            case 'D':
                                stringLiteral = selectNumberRule(6, length2);
                                numberRuleSelectNumberRule = stringLiteral;
                                i = 0;
                                break;
                            case 'E':
                                stringLiteral = new TextField(7, length2 < 4 ? shortWeekdays : weekdays);
                                numberRuleSelectNumberRule = stringLiteral;
                                i = 0;
                                break;
                            case 'F':
                                stringLiteral = selectNumberRule(8, length2);
                                numberRuleSelectNumberRule = stringLiteral;
                                i = 0;
                                break;
                            case 'G':
                                i = 0;
                                timeZoneNameRule = new TextField(0, eras);
                                numberRuleSelectNumberRule = timeZoneNameRule;
                                break;
                            case 'H':
                                stringLiteral = selectNumberRule(11, length2);
                                numberRuleSelectNumberRule = stringLiteral;
                                i = 0;
                                break;
                            default:
                                throw new IllegalArgumentException("Illegal pattern component: " + token);
                        }
                }
            } else if (length2 >= 4) {
                numberRuleSelectNumberRule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 1);
                i = 0;
            } else {
                i = 0;
                timeZoneNameRule = new TimeZoneNameRule(this.mTimeZone, this.mLocale, 0);
                numberRuleSelectNumberRule = timeZoneNameRule;
            }
            arrayList.add(numberRuleSelectNumberRule);
            i3 = i4 + 1;
            i2 = i;
        }
        return arrayList;
    }

    protected String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char cCharAt = str.charAt(i);
        if ((cCharAt >= 'A' && cCharAt <= 'Z') || (cCharAt >= 'a' && cCharAt <= 'z')) {
            sb.append(cCharAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != cCharAt) {
                    break;
                }
                sb.append(cCharAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (i < length) {
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 != '\'') {
                    if (!z && ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || (cCharAt2 >= 'a' && cCharAt2 <= 'z'))) {
                        i--;
                        break;
                    }
                    sb.append(cCharAt2);
                } else {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(cCharAt2);
                        i = i3;
                    }
                }
                i++;
            }
        }
        iArr[0] = i;
        return sb.toString();
    }

    protected NumberRule selectNumberRule(int i, int i2) {
        if (i2 == 1) {
            return new UnpaddedNumberField(i);
        }
        if (i2 == 2) {
            return new TwoDigitNumberField(i);
        }
        return new PaddedNumberField(i, i2);
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        throw new IllegalArgumentException("Unknown class: " + (obj == null ? "<null>" : obj.getClass().getName()));
    }

    public String format(long j) {
        return format(new Date(j));
    }

    public String format(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone, this.mLocale);
        gregorianCalendar.setTime(date);
        return applyRules(gregorianCalendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public String format(Calendar calendar) {
        return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public StringBuffer format(long j, StringBuffer stringBuffer) {
        return format(new Date(j), stringBuffer);
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone, this.mLocale);
        gregorianCalendar.setTime(date);
        return applyRules(gregorianCalendar, stringBuffer);
    }

    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return applyRules(calendar, stringBuffer);
    }

    protected StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        for (Rule rule : this.mRules) {
            rule.appendTo(stringBuffer, calendar);
        }
        return stringBuffer;
    }

    @Override // java.text.Format
    public Object parseObject(String str, ParsePosition parsePosition) {
        parsePosition.setIndex(0);
        parsePosition.setErrorIndex(0);
        return null;
    }

    public String getPattern() {
        return this.mPattern;
    }

    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        FastDateFormat fastDateFormat = (FastDateFormat) obj;
        return this.mPattern.equals(fastDateFormat.mPattern) && this.mTimeZone.equals(fastDateFormat.mTimeZone) && this.mLocale.equals(fastDateFormat.mLocale);
    }

    public int hashCode() {
        return this.mPattern.hashCode() + ((this.mTimeZone.hashCode() + (this.mLocale.hashCode() * 13)) * 13);
    }

    public String toString() {
        return "FastDateFormat[" + this.mPattern + "]";
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        init();
    }

    private static class CharacterLiteral implements Rule {
        private final char mValue;

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.mValue = c;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValue);
        }
    }

    private static class StringLiteral implements Rule {
        private final String mValue;

        StringLiteral(String str) {
            this.mValue = str;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return this.mValue.length();
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValue);
        }
    }

    private static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        TextField(int i, String[] strArr) {
            this.mField = i;
            this.mValues = strArr;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            int length = this.mValues.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                int length2 = this.mValues[length].length();
                if (length2 > i) {
                    i = length2;
                }
            }
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValues[calendar.get(this.mField)]);
        }
    }

    private static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 4;
        }

        UnpaddedNumberField(int i) {
            this.mField = i;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            } else {
                stringBuffer.append(Integer.toString(i));
            }
        }
    }

    private static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 2;
        }

        UnpaddedMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            }
        }
    }

    private static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 4;
        }

        PaddedNumberField(int i, int i2) {
            if (i2 < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = i;
            this.mSize = i2;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i) {
            int length;
            if (i < 100) {
                int i2 = this.mSize;
                while (true) {
                    i2--;
                    if (i2 >= 2) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append((char) ((i / 10) + 48));
                        stringBuffer.append((char) ((i % 10) + 48));
                        return;
                    }
                }
            } else {
                if (i < 1000) {
                    length = 3;
                } else {
                    Validate.isTrue(i > -1, "Negative values should not be possible", i);
                    length = Integer.toString(i).length();
                }
                int i3 = this.mSize;
                while (true) {
                    i3--;
                    if (i3 >= length) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append(Integer.toString(i));
                        return;
                    }
                }
            }
        }
    }

    private static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitNumberField(int i) {
            this.mField = i;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i) {
            if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            } else {
                stringBuffer.append(Integer.toString(i));
            }
        }
    }

    private static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitYearField() {
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(1) % 100);
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 2;
        }

        TwoDigitMonthField() {
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(2) + 1);
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public final void appendTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        TwelveHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int leastMaximum = calendar.get(10);
            if (leastMaximum == 0) {
                leastMaximum = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(stringBuffer, leastMaximum);
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public void appendTo(StringBuffer stringBuffer, int i) {
            this.mRule.appendTo(stringBuffer, i);
        }
    }

    private static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int maximum = calendar.get(11);
            if (maximum == 0) {
                maximum = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(stringBuffer, maximum);
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.NumberRule
        public void appendTo(StringBuffer stringBuffer, int i) {
            this.mRule.appendTo(stringBuffer, i);
        }
    }

    private static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final String mStandard;
        private final TimeZone mTimeZone;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int i) {
            this.mTimeZone = timeZone;
            this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, i, locale);
            this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, i, locale);
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            if (this.mTimeZone.useDaylightTime() && calendar.get(16) != 0) {
                stringBuffer.append(this.mDaylight);
            } else {
                stringBuffer.append(this.mStandard);
            }
        }
    }

    private static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        final boolean mColon;

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public int estimateLength() {
            return 5;
        }

        TimeZoneNumberRule(boolean z) {
            this.mColon = z;
        }

        @Override // org.apache.commons.lang3.time.FastDateFormat.Rule
        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                stringBuffer.append('-');
                i = -i;
            } else {
                stringBuffer.append('+');
            }
            int i2 = i / 3600000;
            stringBuffer.append((char) ((i2 / 10) + 48));
            stringBuffer.append((char) ((i2 % 10) + 48));
            if (this.mColon) {
                stringBuffer.append(':');
            }
            int i3 = (i / 60000) - (i2 * 60);
            stringBuffer.append((char) ((i3 / 10) + 48));
            stringBuffer.append((char) ((i3 % 10) + 48));
        }
    }

    private static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.mTimeZone = timeZone;
            this.mStyle = z ? i | Integer.MIN_VALUE : i;
            this.mLocale = locale;
        }

        public int hashCode() {
            return (((this.mStyle * 31) + this.mLocale.hashCode()) * 31) + this.mTimeZone.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeZoneDisplayKey)) {
                return false;
            }
            TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
            return this.mTimeZone.equals(timeZoneDisplayKey.mTimeZone) && this.mStyle == timeZoneDisplayKey.mStyle && this.mLocale.equals(timeZoneDisplayKey.mLocale);
        }
    }
}
