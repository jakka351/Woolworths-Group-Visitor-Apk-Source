package com.maplibre.rctmln.location;

import io.sentry.ProfilingTraceData;

/* loaded from: classes3.dex */
public class UserTrackingMode {
    public static final int FOLLOW = 1;
    public static final int FollowWithCourse = 2;
    public static final int FollowWithHeading = 3;
    public static final int NONE = 0;

    public static int getCameraMode(int i) {
        if (i == 1) {
            return 24;
        }
        if (i != 2) {
            return i != 3 ? 8 : 32;
        }
        return 34;
    }

    public static boolean isUserGesture(int i) {
        return i == 1 || i == 2;
    }

    public static String toString(int i) {
        if (i == 1) {
            return ProfilingTraceData.TRUNCATION_REASON_NORMAL;
        }
        if (i == 2) {
            return "course";
        }
        if (i != 3) {
            return null;
        }
        return "compass";
    }

    public static int fromString(String str) {
        if (str == null) {
            str = "";
        }
        str.hashCode();
        if (str.equals("course")) {
            return 2;
        }
        return !str.equals("compass") ? 1 : 3;
    }
}
