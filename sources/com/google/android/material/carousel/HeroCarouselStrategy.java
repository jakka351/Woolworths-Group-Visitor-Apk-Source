package com.google.android.material.carousel;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public class HeroCarouselStrategy extends CarouselStrategy {
    private int keylineCount = 0;
    private static final int[] SMALL_COUNTS = {1};
    private static final int[] MEDIUM_COUNTS = {0, 1};

    @Override // com.google.android.material.carousel.CarouselStrategy
    KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        int[] iArrDoubleCounts;
        int i;
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        float f = layoutParams.topMargin + layoutParams.bottomMargin;
        float measuredWidth = view.getMeasuredWidth() * 2;
        if (carousel.isHorizontal()) {
            f = layoutParams.leftMargin + layoutParams.rightMargin;
            measuredWidth = view.getMeasuredHeight() * 2;
        }
        float smallItemSizeMin = getSmallItemSizeMin() + f;
        float fMax = Math.max(getSmallItemSizeMax() + f, smallItemSizeMin);
        float f2 = containerHeight;
        float fMin = Math.min(measuredWidth + f, f2);
        float fClamp = MathUtils.clamp((measuredWidth / 3.0f) + f, smallItemSizeMin + f, fMax + f);
        float f3 = (fMin + fClamp) / 2.0f;
        int[] iArr = f2 < 2.0f * smallItemSizeMin ? new int[]{0} : SMALL_COUNTS;
        int iMax = (int) Math.max(1.0d, Math.floor((f2 - (CarouselStrategyHelper.maxValue(r4) * fMax)) / fMin));
        int iCeil = (((int) Math.ceil(f2 / fMin)) - iMax) + 1;
        int[] iArr2 = new int[iCeil];
        for (int i2 = 0; i2 < iCeil; i2++) {
            iArr2[i2] = iMax + i2;
        }
        int i3 = carousel.getCarouselAlignment() == 1 ? 1 : 0;
        int[] iArrDoubleCounts2 = i3 != 0 ? doubleCounts(iArr) : iArr;
        if (i3 != 0) {
            iArrDoubleCounts = doubleCounts(MEDIUM_COUNTS);
        } else {
            iArrDoubleCounts = MEDIUM_COUNTS;
        }
        Arrangement arrangementFindLowestCostArrangement = Arrangement.findLowestCostArrangement(f2, fClamp, smallItemSizeMin, fMax, iArrDoubleCounts2, f3, iArrDoubleCounts, fMin, iArr2);
        this.keylineCount = arrangementFindLowestCostArrangement.getItemCount();
        if (arrangementFindLowestCostArrangement.getItemCount() > carousel.getItemCount()) {
            arrangementFindLowestCostArrangement = Arrangement.findLowestCostArrangement(f2, fClamp, smallItemSizeMin, fMax, iArr, f3, MEDIUM_COUNTS, fMin, iArr2);
            i = 0;
        } else {
            i = i3;
        }
        return CarouselStrategyHelper.createKeylineState(view.getContext(), f, f2, arrangementFindLowestCostArrangement, i);
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    boolean shouldRefreshKeylineState(Carousel carousel, int i) {
        if (carousel.getCarouselAlignment() == 1) {
            if (i < this.keylineCount && carousel.getItemCount() >= this.keylineCount) {
                return true;
            }
            if (i >= this.keylineCount && carousel.getItemCount() < this.keylineCount) {
                return true;
            }
        }
        return false;
    }
}
