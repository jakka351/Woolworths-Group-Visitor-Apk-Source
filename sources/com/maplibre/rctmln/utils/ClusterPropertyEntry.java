package com.maplibre.rctmln.utils;

import org.maplibre.android.style.expressions.Expression;

/* loaded from: classes3.dex */
public class ClusterPropertyEntry {
    public Expression mapping;
    public Expression operator;

    public ClusterPropertyEntry(Expression expression, Expression expression2) {
        this.operator = expression;
        this.mapping = expression2;
    }
}
