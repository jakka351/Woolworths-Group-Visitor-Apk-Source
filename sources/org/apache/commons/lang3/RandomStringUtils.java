package org.apache.commons.lang3;

import java.util.Random;

/* loaded from: classes2.dex */
public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int i) {
        return random(i, false, false);
    }

    public static String randomAscii(int i) {
        return random(i, 32, 127, false, false);
    }

    public static String randomAlphabetic(int i) {
        return random(i, true, false);
    }

    public static String randomAlphanumeric(int i) {
        return random(i, true, true);
    }

    public static String randomNumeric(int i) {
        return random(i, false, true);
    }

    public static String random(int i, boolean z, boolean z2) {
        return random(i, 0, 0, z, z2);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2) {
        return random(i, i2, i3, z, z2, null, RANDOM);
    }

    public static String random(int i, int i2, int i3, boolean z, boolean z2, char... cArr) {
        return random(i, i2, i3, z, z2, cArr, RANDOM);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String random(int r6, int r7, int r8, boolean r9, boolean r10, char[] r11, java.util.Random r12) {
        /*
            if (r6 != 0) goto L5
            java.lang.String r6 = ""
            return r6
        L5:
            if (r6 < 0) goto L91
            if (r7 != 0) goto L18
            if (r8 != 0) goto L18
            if (r9 != 0) goto L14
            if (r10 != 0) goto L14
            r7 = 0
            r8 = 2147483647(0x7fffffff, float:NaN)
            goto L18
        L14:
            r8 = 123(0x7b, float:1.72E-43)
            r7 = 32
        L18:
            char[] r0 = new char[r6]
            int r8 = r8 - r7
        L1b:
            int r1 = r6 + (-1)
            if (r6 == 0) goto L8b
            if (r11 != 0) goto L28
            int r6 = r12.nextInt(r8)
            int r6 = r6 + r7
            char r6 = (char) r6
            goto L2f
        L28:
            int r6 = r12.nextInt(r8)
            int r6 = r6 + r7
            char r6 = r11[r6]
        L2f:
            if (r9 == 0) goto L37
            boolean r2 = java.lang.Character.isLetter(r6)
            if (r2 != 0) goto L43
        L37:
            if (r10 == 0) goto L3f
            boolean r2 = java.lang.Character.isDigit(r6)
            if (r2 != 0) goto L43
        L3f:
            if (r9 != 0) goto L87
            if (r10 != 0) goto L87
        L43:
            r2 = 128(0x80, float:1.794E-43)
            r3 = 55296(0xd800, float:7.7486E-41)
            r4 = 56320(0xdc00, float:7.8921E-41)
            if (r6 < r4) goto L62
            r5 = 57343(0xdfff, float:8.0355E-41)
            if (r6 > r5) goto L62
            if (r1 != 0) goto L55
            goto L87
        L55:
            r0[r1] = r6
            int r1 = r1 + (-1)
            int r6 = r12.nextInt(r2)
            int r6 = r6 + r3
            char r6 = (char) r6
            r0[r1] = r6
            goto L89
        L62:
            if (r6 < r3) goto L79
            r3 = 56191(0xdb7f, float:7.874E-41)
            if (r6 > r3) goto L79
            if (r1 != 0) goto L6c
            goto L87
        L6c:
            int r2 = r12.nextInt(r2)
            int r2 = r2 + r4
            char r2 = (char) r2
            r0[r1] = r2
            int r1 = r1 + (-1)
            r0[r1] = r6
            goto L89
        L79:
            r2 = 56192(0xdb80, float:7.8742E-41)
            if (r6 < r2) goto L84
            r2 = 56319(0xdbff, float:7.892E-41)
            if (r6 > r2) goto L84
            goto L87
        L84:
            r0[r1] = r6
            goto L89
        L87:
            int r1 = r1 + 1
        L89:
            r6 = r1
            goto L1b
        L8b:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r0)
            return r6
        L91:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Requested random string length "
            r8.<init>(r9)
            java.lang.StringBuilder r6 = r8.append(r6)
            java.lang.String r8 = " is less than 0."
            java.lang.StringBuilder r6 = r6.append(r8)
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.RandomStringUtils.random(int, int, int, boolean, boolean, char[], java.util.Random):java.lang.String");
    }

    public static String random(int i, String str) {
        if (str == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, str.toCharArray());
    }

    public static String random(int i, char... cArr) {
        if (cArr == null) {
            return random(i, 0, 0, false, false, null, RANDOM);
        }
        return random(i, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
