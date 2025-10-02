package org.maplibre.android.plugins.annotation;

import androidx.core.util.Pair;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.CircleLayer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.layers.SymbolLayer;

/* loaded from: classes2.dex */
public class ClusterOptions {
    private Expression circleRadius;
    private Expression textColor;
    private Expression textField;
    private Expression textSize;
    private int clusterRadius = 50;
    private int clusterMaxZoom = 14;
    private Pair<Integer, Integer>[] colorLevels = {new Pair<>(0, -16776961)};

    public int getClusterRadius() {
        return this.clusterRadius;
    }

    public ClusterOptions withClusterRadius(int i) {
        this.clusterRadius = i;
        return this;
    }

    public int getClusterMaxZoom() {
        return this.clusterMaxZoom;
    }

    public ClusterOptions withClusterMaxZoom(int i) {
        this.clusterMaxZoom = i;
        return this;
    }

    public Pair<Integer, Integer>[] getColorLevels() {
        return this.colorLevels;
    }

    public ClusterOptions withColorLevels(Pair<Integer, Integer>[] pairArr) {
        this.colorLevels = pairArr;
        return this;
    }

    public Expression getCircleRadius() {
        return this.circleRadius;
    }

    public ClusterOptions withCircleRadius(Expression expression) {
        this.circleRadius = expression;
        return this;
    }

    public Expression getTextColor() {
        return this.textColor;
    }

    public ClusterOptions withTextColor(Expression expression) {
        this.textColor = expression;
        return this;
    }

    public Expression getTextSize() {
        return this.textSize;
    }

    public ClusterOptions withTextSize(Expression expression) {
        this.textSize = expression;
        return this;
    }

    public Expression getTextField() {
        return this.textField;
    }

    public ClusterOptions withTextField(Expression expression) {
        this.textField = expression;
        return this;
    }

    public void apply(Style style, String str) {
        int i = 0;
        while (true) {
            Pair<Integer, Integer>[] pairArr = this.colorLevels;
            if (i < pairArr.length) {
                style.addLayer(createClusterLevelLayer(i, pairArr, str));
                i++;
            } else {
                style.addLayer(createClusterTextLayer(str));
                return;
            }
        }
    }

    private CircleLayer createClusterLevelLayer(int i, Pair<Integer, Integer>[] pairArr, String str) {
        Expression expressionAll;
        CircleLayer circleLayer = new CircleLayer("mapbox-android-cluster-circle" + i, str);
        PropertyValue<?>[] propertyValueArr = new PropertyValue[2];
        propertyValueArr[0] = PropertyFactory.circleColor(pairArr[i].second.intValue());
        Expression expressionLiteral = this.circleRadius;
        if (expressionLiteral == null) {
            expressionLiteral = Expression.literal((Number) Float.valueOf(18.0f));
        }
        propertyValueArr[1] = PropertyFactory.circleRadius(expressionLiteral);
        circleLayer.setProperties(propertyValueArr);
        Expression number = Expression.toNumber(Expression.get("point_count"));
        if (i == 0) {
            expressionAll = Expression.all(Expression.has("point_count"), Expression.gte(number, Expression.literal((Number) pairArr[i].first)));
        } else {
            expressionAll = Expression.all(Expression.has("point_count"), Expression.gt(number, Expression.literal((Number) pairArr[i].first)), Expression.lt(number, Expression.literal((Number) pairArr[i - 1].first)));
        }
        circleLayer.setFilter(expressionAll);
        return circleLayer;
    }

    private SymbolLayer createClusterTextLayer(String str) {
        SymbolLayer symbolLayer = new SymbolLayer("mapbox-android-cluster-text", str);
        PropertyValue<?>[] propertyValueArr = new PropertyValue[5];
        Expression expression = this.textField;
        if (expression == null) {
            expression = Expression.get("point_count");
        }
        propertyValueArr[0] = PropertyFactory.textField(expression);
        Expression expressionLiteral = this.textSize;
        if (expressionLiteral == null) {
            expressionLiteral = Expression.literal((Number) Float.valueOf(12.0f));
        }
        propertyValueArr[1] = PropertyFactory.textSize(expressionLiteral);
        Expression expressionColor = this.textColor;
        if (expressionColor == null) {
            expressionColor = Expression.color(-1);
        }
        propertyValueArr[2] = PropertyFactory.textColor(expressionColor);
        propertyValueArr[3] = PropertyFactory.textIgnorePlacement((Boolean) true);
        propertyValueArr[4] = PropertyFactory.textAllowOverlap((Boolean) true);
        return symbolLayer.withProperties(propertyValueArr);
    }
}
