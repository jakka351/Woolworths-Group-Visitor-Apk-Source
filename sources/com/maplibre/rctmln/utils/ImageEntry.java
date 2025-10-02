package com.maplibre.rctmln.utils;

/* loaded from: classes3.dex */
public class ImageEntry {
    public static final double defaultScale = 0.0d;
    public double scale;
    public String uri;

    public ImageEntry(String str, Double d) {
        this.scale = 1.0d;
        this.uri = str;
        this.scale = d.doubleValue();
    }

    public ImageEntry(String str) {
        this.uri = str;
        this.scale = 0.0d;
    }

    public double getScaleOr(double d) {
        double d2 = this.scale;
        return d2 == 0.0d ? d : d2;
    }
}
