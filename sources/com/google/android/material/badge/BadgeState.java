package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public final class BadgeState {
    private static final String BADGE_RESOURCE_TAG = "badge";
    final float badgeHeight;
    final float badgeRadius;
    final float badgeWidth;
    final float badgeWithTextHeight;
    final float badgeWithTextRadius;
    final float badgeWithTextWidth;
    private final State currentState;
    final int horizontalInset;
    final int horizontalInsetWithText;
    int offsetAlignmentMode;
    private final State overridingState;

    BadgeState(Context context, int i, int i2, int i3, State state) throws XmlPullParserException, Resources.NotFoundException, IOException {
        CharSequence string;
        int i4;
        int i5;
        int i6;
        int i7;
        int iIntValue;
        int iIntValue2;
        int iIntValue3;
        int iIntValue4;
        int iIntValue5;
        int iIntValue6;
        int iIntValue7;
        int iIntValue8;
        int iIntValue9;
        int iIntValue10;
        int iIntValue11;
        int iIntValue12;
        int iIntValue13;
        int iIntValue14;
        boolean zBooleanValue;
        State state2 = new State();
        this.currentState = state2;
        state = state == null ? new State() : state;
        if (i != 0) {
            state.badgeResId = i;
        }
        TypedArray typedArrayGenerateTypedArray = generateTypedArray(context, state.badgeResId, i2, i3);
        Resources resources = context.getResources();
        this.badgeRadius = typedArrayGenerateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeRadius, -1);
        this.horizontalInset = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_horizontal_edge_offset);
        this.horizontalInsetWithText = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.badgeWithTextRadius = typedArrayGenerateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeWithTextRadius, -1);
        this.badgeWidth = typedArrayGenerateTypedArray.getDimension(R.styleable.Badge_badgeWidth, resources.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextWidth = typedArrayGenerateTypedArray.getDimension(R.styleable.Badge_badgeWithTextWidth, resources.getDimension(R.dimen.m3_badge_with_text_size));
        this.badgeHeight = typedArrayGenerateTypedArray.getDimension(R.styleable.Badge_badgeHeight, resources.getDimension(R.dimen.m3_badge_size));
        this.badgeWithTextHeight = typedArrayGenerateTypedArray.getDimension(R.styleable.Badge_badgeWithTextHeight, resources.getDimension(R.dimen.m3_badge_with_text_size));
        boolean z = true;
        this.offsetAlignmentMode = typedArrayGenerateTypedArray.getInt(R.styleable.Badge_offsetAlignmentMode, 1);
        state2.alpha = state.alpha == -2 ? 255 : state.alpha;
        if (state.number == -2) {
            if (typedArrayGenerateTypedArray.hasValue(R.styleable.Badge_number)) {
                state2.number = typedArrayGenerateTypedArray.getInt(R.styleable.Badge_number, 0);
            } else {
                state2.number = -1;
            }
        } else {
            state2.number = state.number;
        }
        if (state.text == null) {
            if (typedArrayGenerateTypedArray.hasValue(R.styleable.Badge_badgeText)) {
                state2.text = typedArrayGenerateTypedArray.getString(R.styleable.Badge_badgeText);
            }
        } else {
            state2.text = state.text;
        }
        state2.contentDescriptionForText = state.contentDescriptionForText;
        if (state.contentDescriptionNumberless == null) {
            string = context.getString(R.string.mtrl_badge_numberless_content_description);
        } else {
            string = state.contentDescriptionNumberless;
        }
        state2.contentDescriptionNumberless = string;
        if (state.contentDescriptionQuantityStrings == 0) {
            i4 = R.plurals.mtrl_badge_content_description;
        } else {
            i4 = state.contentDescriptionQuantityStrings;
        }
        state2.contentDescriptionQuantityStrings = i4;
        if (state.contentDescriptionExceedsMaxBadgeNumberRes == 0) {
            i5 = R.string.mtrl_exceed_max_badge_number_content_description;
        } else {
            i5 = state.contentDescriptionExceedsMaxBadgeNumberRes;
        }
        state2.contentDescriptionExceedsMaxBadgeNumberRes = i5;
        if (state.isVisible != null && !state.isVisible.booleanValue()) {
            z = false;
        }
        state2.isVisible = Boolean.valueOf(z);
        if (state.maxCharacterCount == -2) {
            i6 = typedArrayGenerateTypedArray.getInt(R.styleable.Badge_maxCharacterCount, -2);
        } else {
            i6 = state.maxCharacterCount;
        }
        state2.maxCharacterCount = i6;
        if (state.maxNumber == -2) {
            i7 = typedArrayGenerateTypedArray.getInt(R.styleable.Badge_maxNumber, -2);
        } else {
            i7 = state.maxNumber;
        }
        state2.maxNumber = i7;
        if (state.badgeShapeAppearanceResId == null) {
            iIntValue = typedArrayGenerateTypedArray.getResourceId(R.styleable.Badge_badgeShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
        } else {
            iIntValue = state.badgeShapeAppearanceResId.intValue();
        }
        state2.badgeShapeAppearanceResId = Integer.valueOf(iIntValue);
        if (state.badgeShapeAppearanceOverlayResId == null) {
            iIntValue2 = typedArrayGenerateTypedArray.getResourceId(R.styleable.Badge_badgeShapeAppearanceOverlay, 0);
        } else {
            iIntValue2 = state.badgeShapeAppearanceOverlayResId.intValue();
        }
        state2.badgeShapeAppearanceOverlayResId = Integer.valueOf(iIntValue2);
        if (state.badgeWithTextShapeAppearanceResId == null) {
            iIntValue3 = typedArrayGenerateTypedArray.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full);
        } else {
            iIntValue3 = state.badgeWithTextShapeAppearanceResId.intValue();
        }
        state2.badgeWithTextShapeAppearanceResId = Integer.valueOf(iIntValue3);
        if (state.badgeWithTextShapeAppearanceOverlayResId == null) {
            iIntValue4 = typedArrayGenerateTypedArray.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0);
        } else {
            iIntValue4 = state.badgeWithTextShapeAppearanceOverlayResId.intValue();
        }
        state2.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(iIntValue4);
        if (state.backgroundColor == null) {
            iIntValue5 = readColorFromAttributes(context, typedArrayGenerateTypedArray, R.styleable.Badge_backgroundColor);
        } else {
            iIntValue5 = state.backgroundColor.intValue();
        }
        state2.backgroundColor = Integer.valueOf(iIntValue5);
        if (state.badgeTextAppearanceResId == null) {
            iIntValue6 = typedArrayGenerateTypedArray.getResourceId(R.styleable.Badge_badgeTextAppearance, R.style.TextAppearance_MaterialComponents_Badge);
        } else {
            iIntValue6 = state.badgeTextAppearanceResId.intValue();
        }
        state2.badgeTextAppearanceResId = Integer.valueOf(iIntValue6);
        if (state.badgeTextColor == null) {
            if (typedArrayGenerateTypedArray.hasValue(R.styleable.Badge_badgeTextColor)) {
                state2.badgeTextColor = Integer.valueOf(readColorFromAttributes(context, typedArrayGenerateTypedArray, R.styleable.Badge_badgeTextColor));
            } else {
                state2.badgeTextColor = Integer.valueOf(new TextAppearance(context, state2.badgeTextAppearanceResId.intValue()).getTextColor().getDefaultColor());
            }
        } else {
            state2.badgeTextColor = state.badgeTextColor;
        }
        if (state.badgeGravity == null) {
            iIntValue7 = typedArrayGenerateTypedArray.getInt(R.styleable.Badge_badgeGravity, 8388661);
        } else {
            iIntValue7 = state.badgeGravity.intValue();
        }
        state2.badgeGravity = Integer.valueOf(iIntValue7);
        if (state.badgeHorizontalPadding == null) {
            iIntValue8 = typedArrayGenerateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding));
        } else {
            iIntValue8 = state.badgeHorizontalPadding.intValue();
        }
        state2.badgeHorizontalPadding = Integer.valueOf(iIntValue8);
        if (state.badgeVerticalPadding == null) {
            iIntValue9 = typedArrayGenerateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeVerticalPadding, resources.getDimensionPixelSize(R.dimen.m3_badge_with_text_vertical_padding));
        } else {
            iIntValue9 = state.badgeVerticalPadding.intValue();
        }
        state2.badgeVerticalPadding = Integer.valueOf(iIntValue9);
        if (state.horizontalOffsetWithoutText == null) {
            iIntValue10 = typedArrayGenerateTypedArray.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0);
        } else {
            iIntValue10 = state.horizontalOffsetWithoutText.intValue();
        }
        state2.horizontalOffsetWithoutText = Integer.valueOf(iIntValue10);
        if (state.verticalOffsetWithoutText == null) {
            iIntValue11 = typedArrayGenerateTypedArray.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0);
        } else {
            iIntValue11 = state.verticalOffsetWithoutText.intValue();
        }
        state2.verticalOffsetWithoutText = Integer.valueOf(iIntValue11);
        if (state.horizontalOffsetWithText == null) {
            iIntValue12 = typedArrayGenerateTypedArray.getDimensionPixelOffset(R.styleable.Badge_horizontalOffsetWithText, state2.horizontalOffsetWithoutText.intValue());
        } else {
            iIntValue12 = state.horizontalOffsetWithText.intValue();
        }
        state2.horizontalOffsetWithText = Integer.valueOf(iIntValue12);
        if (state.verticalOffsetWithText == null) {
            iIntValue13 = typedArrayGenerateTypedArray.getDimensionPixelOffset(R.styleable.Badge_verticalOffsetWithText, state2.verticalOffsetWithoutText.intValue());
        } else {
            iIntValue13 = state.verticalOffsetWithText.intValue();
        }
        state2.verticalOffsetWithText = Integer.valueOf(iIntValue13);
        if (state.largeFontVerticalOffsetAdjustment == null) {
            iIntValue14 = typedArrayGenerateTypedArray.getDimensionPixelOffset(R.styleable.Badge_largeFontVerticalOffsetAdjustment, 0);
        } else {
            iIntValue14 = state.largeFontVerticalOffsetAdjustment.intValue();
        }
        state2.largeFontVerticalOffsetAdjustment = Integer.valueOf(iIntValue14);
        state2.additionalHorizontalOffset = Integer.valueOf(state.additionalHorizontalOffset == null ? 0 : state.additionalHorizontalOffset.intValue());
        state2.additionalVerticalOffset = Integer.valueOf(state.additionalVerticalOffset == null ? 0 : state.additionalVerticalOffset.intValue());
        if (state.autoAdjustToWithinGrandparentBounds == null) {
            zBooleanValue = typedArrayGenerateTypedArray.getBoolean(R.styleable.Badge_autoAdjustToWithinGrandparentBounds, false);
        } else {
            zBooleanValue = state.autoAdjustToWithinGrandparentBounds.booleanValue();
        }
        state2.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(zBooleanValue);
        typedArrayGenerateTypedArray.recycle();
        if (state.numberLocale == null) {
            state2.numberLocale = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            state2.numberLocale = state.numberLocale;
        }
        this.overridingState = state;
    }

    private TypedArray generateTypedArray(Context context, int i, int i2, int i3) throws XmlPullParserException, Resources.NotFoundException, IOException {
        AttributeSet attributeSet;
        int styleAttribute;
        if (i != 0) {
            AttributeSet drawableXml = DrawableUtils.parseDrawableXml(context, i, BADGE_RESOURCE_TAG);
            styleAttribute = drawableXml.getStyleAttribute();
            attributeSet = drawableXml;
        } else {
            attributeSet = null;
            styleAttribute = 0;
        }
        return ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Badge, i2, styleAttribute == 0 ? i3 : styleAttribute, new int[0]);
    }

    State getOverridingState() {
        return this.overridingState;
    }

    boolean isVisible() {
        return this.currentState.isVisible.booleanValue();
    }

    void setVisible(boolean z) {
        this.overridingState.isVisible = Boolean.valueOf(z);
        this.currentState.isVisible = Boolean.valueOf(z);
    }

    boolean hasNumber() {
        return this.currentState.number != -1;
    }

    int getNumber() {
        return this.currentState.number;
    }

    void setNumber(int i) {
        this.overridingState.number = i;
        this.currentState.number = i;
    }

    void clearNumber() {
        setNumber(-1);
    }

    boolean hasText() {
        return this.currentState.text != null;
    }

    String getText() {
        return this.currentState.text;
    }

    void setText(String str) {
        this.overridingState.text = str;
        this.currentState.text = str;
    }

    void clearText() {
        setText(null);
    }

    int getAlpha() {
        return this.currentState.alpha;
    }

    void setAlpha(int i) {
        this.overridingState.alpha = i;
        this.currentState.alpha = i;
    }

    int getMaxCharacterCount() {
        return this.currentState.maxCharacterCount;
    }

    void setMaxCharacterCount(int i) {
        this.overridingState.maxCharacterCount = i;
        this.currentState.maxCharacterCount = i;
    }

    int getMaxNumber() {
        return this.currentState.maxNumber;
    }

    void setMaxNumber(int i) {
        this.overridingState.maxNumber = i;
        this.currentState.maxNumber = i;
    }

    int getBackgroundColor() {
        return this.currentState.backgroundColor.intValue();
    }

    void setBackgroundColor(int i) {
        this.overridingState.backgroundColor = Integer.valueOf(i);
        this.currentState.backgroundColor = Integer.valueOf(i);
    }

    int getBadgeTextColor() {
        return this.currentState.badgeTextColor.intValue();
    }

    void setBadgeTextColor(int i) {
        this.overridingState.badgeTextColor = Integer.valueOf(i);
        this.currentState.badgeTextColor = Integer.valueOf(i);
    }

    int getTextAppearanceResId() {
        return this.currentState.badgeTextAppearanceResId.intValue();
    }

    void setTextAppearanceResId(int i) {
        this.overridingState.badgeTextAppearanceResId = Integer.valueOf(i);
        this.currentState.badgeTextAppearanceResId = Integer.valueOf(i);
    }

    int getBadgeShapeAppearanceResId() {
        return this.currentState.badgeShapeAppearanceResId.intValue();
    }

    void setBadgeShapeAppearanceResId(int i) {
        this.overridingState.badgeShapeAppearanceResId = Integer.valueOf(i);
        this.currentState.badgeShapeAppearanceResId = Integer.valueOf(i);
    }

    int getBadgeShapeAppearanceOverlayResId() {
        return this.currentState.badgeShapeAppearanceOverlayResId.intValue();
    }

    void setBadgeShapeAppearanceOverlayResId(int i) {
        this.overridingState.badgeShapeAppearanceOverlayResId = Integer.valueOf(i);
        this.currentState.badgeShapeAppearanceOverlayResId = Integer.valueOf(i);
    }

    int getBadgeWithTextShapeAppearanceResId() {
        return this.currentState.badgeWithTextShapeAppearanceResId.intValue();
    }

    void setBadgeWithTextShapeAppearanceResId(int i) {
        this.overridingState.badgeWithTextShapeAppearanceResId = Integer.valueOf(i);
        this.currentState.badgeWithTextShapeAppearanceResId = Integer.valueOf(i);
    }

    int getBadgeWithTextShapeAppearanceOverlayResId() {
        return this.currentState.badgeWithTextShapeAppearanceOverlayResId.intValue();
    }

    void setBadgeWithTextShapeAppearanceOverlayResId(int i) {
        this.overridingState.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i);
        this.currentState.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i);
    }

    int getBadgeGravity() {
        return this.currentState.badgeGravity.intValue();
    }

    void setBadgeGravity(int i) {
        this.overridingState.badgeGravity = Integer.valueOf(i);
        this.currentState.badgeGravity = Integer.valueOf(i);
    }

    int getBadgeHorizontalPadding() {
        return this.currentState.badgeHorizontalPadding.intValue();
    }

    void setBadgeHorizontalPadding(int i) {
        this.overridingState.badgeHorizontalPadding = Integer.valueOf(i);
        this.currentState.badgeHorizontalPadding = Integer.valueOf(i);
    }

    int getBadgeVerticalPadding() {
        return this.currentState.badgeVerticalPadding.intValue();
    }

    void setBadgeVerticalPadding(int i) {
        this.overridingState.badgeVerticalPadding = Integer.valueOf(i);
        this.currentState.badgeVerticalPadding = Integer.valueOf(i);
    }

    int getHorizontalOffsetWithoutText() {
        return this.currentState.horizontalOffsetWithoutText.intValue();
    }

    void setHorizontalOffsetWithoutText(int i) {
        this.overridingState.horizontalOffsetWithoutText = Integer.valueOf(i);
        this.currentState.horizontalOffsetWithoutText = Integer.valueOf(i);
    }

    int getVerticalOffsetWithoutText() {
        return this.currentState.verticalOffsetWithoutText.intValue();
    }

    void setVerticalOffsetWithoutText(int i) {
        this.overridingState.verticalOffsetWithoutText = Integer.valueOf(i);
        this.currentState.verticalOffsetWithoutText = Integer.valueOf(i);
    }

    int getHorizontalOffsetWithText() {
        return this.currentState.horizontalOffsetWithText.intValue();
    }

    void setHorizontalOffsetWithText(int i) {
        this.overridingState.horizontalOffsetWithText = Integer.valueOf(i);
        this.currentState.horizontalOffsetWithText = Integer.valueOf(i);
    }

    int getVerticalOffsetWithText() {
        return this.currentState.verticalOffsetWithText.intValue();
    }

    void setVerticalOffsetWithText(int i) {
        this.overridingState.verticalOffsetWithText = Integer.valueOf(i);
        this.currentState.verticalOffsetWithText = Integer.valueOf(i);
    }

    int getLargeFontVerticalOffsetAdjustment() {
        return this.currentState.largeFontVerticalOffsetAdjustment.intValue();
    }

    void setLargeFontVerticalOffsetAdjustment(int i) {
        this.overridingState.largeFontVerticalOffsetAdjustment = Integer.valueOf(i);
        this.currentState.largeFontVerticalOffsetAdjustment = Integer.valueOf(i);
    }

    int getAdditionalHorizontalOffset() {
        return this.currentState.additionalHorizontalOffset.intValue();
    }

    void setAdditionalHorizontalOffset(int i) {
        this.overridingState.additionalHorizontalOffset = Integer.valueOf(i);
        this.currentState.additionalHorizontalOffset = Integer.valueOf(i);
    }

    int getAdditionalVerticalOffset() {
        return this.currentState.additionalVerticalOffset.intValue();
    }

    void setAdditionalVerticalOffset(int i) {
        this.overridingState.additionalVerticalOffset = Integer.valueOf(i);
        this.currentState.additionalVerticalOffset = Integer.valueOf(i);
    }

    CharSequence getContentDescriptionForText() {
        return this.currentState.contentDescriptionForText;
    }

    void setContentDescriptionForText(CharSequence charSequence) {
        this.overridingState.contentDescriptionForText = charSequence;
        this.currentState.contentDescriptionForText = charSequence;
    }

    CharSequence getContentDescriptionNumberless() {
        return this.currentState.contentDescriptionNumberless;
    }

    void setContentDescriptionNumberless(CharSequence charSequence) {
        this.overridingState.contentDescriptionNumberless = charSequence;
        this.currentState.contentDescriptionNumberless = charSequence;
    }

    int getContentDescriptionQuantityStrings() {
        return this.currentState.contentDescriptionQuantityStrings;
    }

    void setContentDescriptionQuantityStringsResource(int i) {
        this.overridingState.contentDescriptionQuantityStrings = i;
        this.currentState.contentDescriptionQuantityStrings = i;
    }

    int getContentDescriptionExceedsMaxBadgeNumberStringResource() {
        return this.currentState.contentDescriptionExceedsMaxBadgeNumberRes;
    }

    void setContentDescriptionExceedsMaxBadgeNumberStringResource(int i) {
        this.overridingState.contentDescriptionExceedsMaxBadgeNumberRes = i;
        this.currentState.contentDescriptionExceedsMaxBadgeNumberRes = i;
    }

    Locale getNumberLocale() {
        return this.currentState.numberLocale;
    }

    void setNumberLocale(Locale locale) {
        this.overridingState.numberLocale = locale;
        this.currentState.numberLocale = locale;
    }

    boolean isAutoAdjustedToGrandparentBounds() {
        return this.currentState.autoAdjustToWithinGrandparentBounds.booleanValue();
    }

    void setAutoAdjustToGrandparentBounds(boolean z) {
        this.overridingState.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z);
        this.currentState.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z);
    }

    private static int readColorFromAttributes(Context context, TypedArray typedArray, int i) {
        return MaterialResources.getColorStateList(context, typedArray, i).getDefaultColor();
    }

    public static final class State implements Parcelable {
        private static final int BADGE_NUMBER_NONE = -1;
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() { // from class: com.google.android.material.badge.BadgeState.State.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public State createFromParcel(Parcel parcel) {
                return new State(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public State[] newArray(int i) {
                return new State[i];
            }
        };
        private static final int NOT_SET = -2;
        private Integer additionalHorizontalOffset;
        private Integer additionalVerticalOffset;
        private int alpha;
        private Boolean autoAdjustToWithinGrandparentBounds;
        private Integer backgroundColor;
        private Integer badgeGravity;
        private Integer badgeHorizontalPadding;
        private int badgeResId;
        private Integer badgeShapeAppearanceOverlayResId;
        private Integer badgeShapeAppearanceResId;
        private Integer badgeTextAppearanceResId;
        private Integer badgeTextColor;
        private Integer badgeVerticalPadding;
        private Integer badgeWithTextShapeAppearanceOverlayResId;
        private Integer badgeWithTextShapeAppearanceResId;
        private int contentDescriptionExceedsMaxBadgeNumberRes;
        private CharSequence contentDescriptionForText;
        private CharSequence contentDescriptionNumberless;
        private int contentDescriptionQuantityStrings;
        private Integer horizontalOffsetWithText;
        private Integer horizontalOffsetWithoutText;
        private Boolean isVisible;
        private Integer largeFontVerticalOffsetAdjustment;
        private int maxCharacterCount;
        private int maxNumber;
        private int number;
        private Locale numberLocale;
        private String text;
        private Integer verticalOffsetWithText;
        private Integer verticalOffsetWithoutText;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public State() {
            this.alpha = 255;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = true;
        }

        State(Parcel parcel) {
            this.alpha = 255;
            this.number = -2;
            this.maxCharacterCount = -2;
            this.maxNumber = -2;
            this.isVisible = true;
            this.badgeResId = parcel.readInt();
            this.backgroundColor = (Integer) parcel.readSerializable();
            this.badgeTextColor = (Integer) parcel.readSerializable();
            this.badgeTextAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.alpha = parcel.readInt();
            this.text = parcel.readString();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.maxNumber = parcel.readInt();
            this.contentDescriptionForText = parcel.readString();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
            this.badgeGravity = (Integer) parcel.readSerializable();
            this.badgeHorizontalPadding = (Integer) parcel.readSerializable();
            this.badgeVerticalPadding = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithText = (Integer) parcel.readSerializable();
            this.largeFontVerticalOffsetAdjustment = (Integer) parcel.readSerializable();
            this.additionalHorizontalOffset = (Integer) parcel.readSerializable();
            this.additionalVerticalOffset = (Integer) parcel.readSerializable();
            this.isVisible = (Boolean) parcel.readSerializable();
            this.numberLocale = (Locale) parcel.readSerializable();
            this.autoAdjustToWithinGrandparentBounds = (Boolean) parcel.readSerializable();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.badgeResId);
            parcel.writeSerializable(this.backgroundColor);
            parcel.writeSerializable(this.badgeTextColor);
            parcel.writeSerializable(this.badgeTextAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceOverlayResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceOverlayResId);
            parcel.writeInt(this.alpha);
            parcel.writeString(this.text);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeInt(this.maxNumber);
            CharSequence charSequence = this.contentDescriptionForText;
            parcel.writeString(charSequence != null ? charSequence.toString() : null);
            CharSequence charSequence2 = this.contentDescriptionNumberless;
            parcel.writeString(charSequence2 != null ? charSequence2.toString() : null);
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeSerializable(this.badgeGravity);
            parcel.writeSerializable(this.badgeHorizontalPadding);
            parcel.writeSerializable(this.badgeVerticalPadding);
            parcel.writeSerializable(this.horizontalOffsetWithoutText);
            parcel.writeSerializable(this.verticalOffsetWithoutText);
            parcel.writeSerializable(this.horizontalOffsetWithText);
            parcel.writeSerializable(this.verticalOffsetWithText);
            parcel.writeSerializable(this.largeFontVerticalOffsetAdjustment);
            parcel.writeSerializable(this.additionalHorizontalOffset);
            parcel.writeSerializable(this.additionalVerticalOffset);
            parcel.writeSerializable(this.isVisible);
            parcel.writeSerializable(this.numberLocale);
            parcel.writeSerializable(this.autoAdjustToWithinGrandparentBounds);
        }
    }
}
