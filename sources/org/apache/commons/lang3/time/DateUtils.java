package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: classes2.dex */
public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        return isSameDay(calendar, calendar2);
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return date.getTime() == date2.getTime();
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.getTime().getTime() == calendar2.getTime().getTime();
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(11) == calendar2.get(11) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
    }

    public static Date parseDate(String str, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, true);
    }

    public static Date parseDateStrictly(String str, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, false);
    }

    private static Date parseDateWithLeniency(String str, String[] strArr, boolean z) throws ParseException {
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setLenient(z);
        ParsePosition parsePosition = new ParsePosition(0);
        for (String str2 : strArr) {
            simpleDateFormat.applyPattern(str2.endsWith("ZZ") ? str2.substring(0, str2.length() - 1) : str2);
            parsePosition.setIndex(0);
            String strReplaceAll = str2.endsWith("ZZ") ? str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2") : str;
            Date date = simpleDateFormat.parse(strReplaceAll, parsePosition);
            if (date != null && parsePosition.getIndex() == strReplaceAll.length()) {
                return date;
            }
        }
        throw new ParseException("Unable to parse the date: " + str, -1);
    }

    public static Date addYears(Date date, int i) {
        return add(date, 1, i);
    }

    public static Date addMonths(Date date, int i) {
        return add(date, 2, i);
    }

    public static Date addWeeks(Date date, int i) {
        return add(date, 3, i);
    }

    public static Date addDays(Date date, int i) {
        return add(date, 5, i);
    }

    public static Date addHours(Date date, int i) {
        return add(date, 11, i);
    }

    public static Date addMinutes(Date date, int i) {
        return add(date, 12, i);
    }

    public static Date addSeconds(Date date, int i) {
        return add(date, 13, i);
    }

    public static Date addMilliseconds(Date date, int i) {
        return add(date, 14, i);
    }

    private static Date add(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(i, i2);
        return calendar.getTime();
    }

    public static Date setYears(Date date, int i) {
        return set(date, 1, i);
    }

    public static Date setMonths(Date date, int i) {
        return set(date, 2, i);
    }

    public static Date setDays(Date date, int i) {
        return set(date, 5, i);
    }

    public static Date setHours(Date date, int i) {
        return set(date, 11, i);
    }

    public static Date setMinutes(Date date, int i) {
        return set(date, 12, i);
    }

    public static Date setSeconds(Date date, int i) {
        return set(date, 13, i);
    }

    public static Date setMilliseconds(Date date, int i) {
        return set(date, 14, i);
    }

    private static Date set(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.setTime(date);
        calendar.set(i, i2);
        return calendar.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date round(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 1);
        return calendar.getTime();
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 1);
        return calendar2;
    }

    public static Date round(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return round((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return round((Calendar) obj, i).getTime();
        }
        throw new ClassCastException("Could not round " + obj);
    }

    public static Date truncate(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 0);
        return calendar.getTime();
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 0);
        return calendar2;
    }

    public static Date truncate(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return truncate((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return truncate((Calendar) obj, i).getTime();
        }
        throw new ClassCastException("Could not truncate " + obj);
    }

    public static Date ceiling(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        modify(calendar, i, 2);
        return calendar.getTime();
    }

    public static Calendar ceiling(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 2);
        return calendar2;
    }

    public static Date ceiling(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return ceiling((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return ceiling((Calendar) obj, i).getTime();
        }
        throw new ClassCastException("Could not find ceiling of for type: " + obj.getClass());
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void modify(java.util.Calendar r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.modify(java.util.Calendar, int, int):void");
    }

    public static Iterator<Calendar> iterator(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return iterator(calendar, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007b A[LOOP:0: B:30:0x0075->B:32:0x007b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0085 A[LOOP:1: B:33:0x007f->B:35:0x0085, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Iterator<java.util.Calendar> iterator(java.util.Calendar r8, int r9) {
        /*
            if (r8 == 0) goto L8f
            r0 = -1
            r1 = 2
            r2 = 5
            r3 = 1
            r4 = 7
            switch(r9) {
                case 1: goto L41;
                case 2: goto L41;
                case 3: goto L41;
                case 4: goto L41;
                case 5: goto L25;
                case 6: goto L25;
                default: goto La;
            }
        La:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The range style "
            r0.<init>(r1)
            java.lang.StringBuilder r9 = r0.append(r9)
            java.lang.String r0 = " is not valid."
            java.lang.StringBuilder r9 = r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L25:
            java.util.Calendar r8 = truncate(r8, r1)
            java.lang.Object r5 = r8.clone()
            java.util.Calendar r5 = (java.util.Calendar) r5
            r5.add(r1, r3)
            r5.add(r2, r0)
            r6 = 6
            if (r9 != r6) goto L3c
            r6 = r5
            r5 = r8
        L3a:
            r8 = r3
            goto L65
        L3c:
            r1 = r3
            r6 = r5
            r5 = r8
        L3f:
            r8 = r4
            goto L65
        L41:
            java.util.Calendar r5 = truncate(r8, r2)
            java.util.Calendar r6 = truncate(r8, r2)
            if (r9 == r1) goto L3a
            r1 = 3
            if (r9 == r1) goto L5f
            r7 = 4
            if (r9 == r7) goto L53
            r1 = r3
            goto L3f
        L53:
            int r9 = r8.get(r4)
            int r9 = r9 - r1
            int r8 = r8.get(r4)
            int r8 = r8 + r1
            r1 = r9
            goto L65
        L5f:
            int r1 = r8.get(r4)
            int r8 = r1 + (-1)
        L65:
            if (r1 >= r3) goto L69
            int r1 = r1 + 7
        L69:
            if (r1 <= r4) goto L6d
            int r1 = r1 + (-7)
        L6d:
            if (r8 >= r3) goto L71
            int r8 = r8 + 7
        L71:
            if (r8 <= r4) goto L75
            int r8 = r8 + (-7)
        L75:
            int r9 = r5.get(r4)
            if (r9 == r1) goto L7f
            r5.add(r2, r0)
            goto L75
        L7f:
            int r9 = r6.get(r4)
            if (r9 == r8) goto L89
            r6.add(r2, r3)
            goto L7f
        L89:
            org.apache.commons.lang3.time.DateUtils$DateIterator r8 = new org.apache.commons.lang3.time.DateUtils$DateIterator
            r8.<init>(r5, r6)
            return r8
        L8f:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "The date must not be null"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.iterator(java.util.Calendar, int):java.util.Iterator");
    }

    public static Iterator<?> iterator(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        if (obj instanceof Date) {
            return iterator((Date) obj, i);
        }
        if (obj instanceof Calendar) {
            return iterator((Calendar) obj, i);
        }
        throw new ClassCastException("Could not iterate based on " + obj);
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, 14);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, 13);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, 12);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, 11);
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, 6);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 14);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 13);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, 12);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, 11);
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, 6);
    }

    private static long getFragment(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFragment(calendar, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static long getFragment(java.util.Calendar r11, int r12, int r13) {
        /*
            if (r11 == 0) goto L7b
            long r0 = getMillisPerUnit(r13)
            r2 = 86400000(0x5265c00, double:4.2687272E-316)
            r13 = 6
            r4 = 5
            r5 = 2
            r6 = 1
            r7 = 0
            if (r12 == r6) goto L1c
            if (r12 == r5) goto L14
            goto L24
        L14:
            int r9 = r11.get(r4)
            long r9 = (long) r9
            long r9 = r9 * r2
            long r9 = r9 / r0
            goto L23
        L1c:
            int r9 = r11.get(r13)
            long r9 = (long) r9
            long r9 = r9 * r2
            long r9 = r9 / r0
        L23:
            long r7 = r7 + r9
        L24:
            if (r12 == r6) goto L4a
            if (r12 == r5) goto L4a
            if (r12 == r4) goto L4a
            if (r12 == r13) goto L4a
            switch(r12) {
                case 11: goto L57;
                case 12: goto L64;
                case 13: goto L70;
                case 14: goto L7a;
                default: goto L2f;
            }
        L2f:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "The fragment "
            r13.<init>(r0)
            java.lang.StringBuilder r12 = r13.append(r12)
            java.lang.String r13 = " is not supported"
            java.lang.StringBuilder r12 = r12.append(r13)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L4a:
            r12 = 11
            int r12 = r11.get(r12)
            long r12 = (long) r12
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            long r12 = r12 * r2
            long r12 = r12 / r0
            long r7 = r7 + r12
        L57:
            r12 = 12
            int r12 = r11.get(r12)
            long r12 = (long) r12
            r2 = 60000(0xea60, double:2.9644E-319)
            long r12 = r12 * r2
            long r12 = r12 / r0
            long r7 = r7 + r12
        L64:
            r12 = 13
            int r12 = r11.get(r12)
            long r12 = (long) r12
            r2 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 * r2
            long r12 = r12 / r0
            long r7 = r7 + r12
        L70:
            r12 = 14
            int r11 = r11.get(r12)
            int r11 = r11 * r6
            long r11 = (long) r11
            long r11 = r11 / r0
            long r7 = r7 + r11
        L7a:
            return r7
        L7b:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "The date must not be null"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.getFragment(java.util.Calendar, int, int):long");
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static boolean truncatedEquals(Date date, Date date2, int i) {
        return truncatedCompareTo(date, date2, i) == 0;
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).compareTo(truncate(calendar2, i));
    }

    public static int truncatedCompareTo(Date date, Date date2, int i) {
        return truncate(date, i).compareTo(truncate(date2, i));
    }

    private static long getMillisPerUnit(int i) {
        if (i == 5 || i == 6) {
            return MILLIS_PER_DAY;
        }
        switch (i) {
            case 11:
                return MILLIS_PER_HOUR;
            case 12:
                return MILLIS_PER_MINUTE;
            case 13:
                return 1000L;
            case 14:
                return 1L;
            default:
                throw new IllegalArgumentException("The unit " + i + " cannot be represented is milleseconds");
        }
    }

    static class DateIterator implements Iterator<Calendar> {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            calendar.add(5, -1);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        @Override // java.util.Iterator
        public Calendar next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return (Calendar) this.spot.clone();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
