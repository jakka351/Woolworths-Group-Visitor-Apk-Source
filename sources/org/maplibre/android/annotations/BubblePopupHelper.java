package org.maplibre.android.annotations;

import android.R;
import android.content.Context;
import android.widget.PopupWindow;

@Deprecated
/* loaded from: classes2.dex */
class BubblePopupHelper {
    BubblePopupHelper() {
    }

    static PopupWindow create(Context context, BubbleLayout bubbleLayout) {
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(bubbleLayout);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth(-2);
        popupWindow.setHeight(-2);
        popupWindow.setAnimationStyle(R.style.Animation.Dialog);
        popupWindow.setBackgroundDrawable(context.getDrawable(org.maplibre.android.R.drawable.maplibre_popup_window_transparent));
        return popupWindow;
    }
}
