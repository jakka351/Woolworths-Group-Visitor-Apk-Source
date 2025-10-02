package io.sentry.android.replay.util;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Views.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006*\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001H\u0000\u001a\u000e\u0010\f\u001a\u00020\r*\u0004\u0018\u00010\u000eH\u0001\u001a\u001a\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010*\u00020\u0011H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0012"}, d2 = {"totalPaddingTopSafe", "", "Landroid/widget/TextView;", "getTotalPaddingTopSafe", "(Landroid/widget/TextView;)I", "getVisibleRects", "", "Landroid/graphics/Rect;", "Landroid/text/Layout;", "globalRect", ViewProps.PADDING_LEFT, ViewProps.PADDING_TOP, "isRedactable", "", "Landroid/graphics/drawable/Drawable;", "isVisibleToUser", "Lkotlin/Pair;", "Landroid/view/View;", "sentry-android-replay_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ViewsKt {
    public static final Pair<Boolean, Rect> isVisibleToUser(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (view.isAttachedToWindow()) {
            if (view.getWindowVisibility() != 0) {
                return TuplesKt.to(false, null);
            }
            Object parent = view;
            while (parent instanceof View) {
                float transitionAlpha = Build.VERSION.SDK_INT >= 29 ? ((View) parent).getTransitionAlpha() : 1.0f;
                View view2 = (View) parent;
                if (view2.getAlpha() <= 0.0f || transitionAlpha <= 0.0f || view2.getVisibility() != 0) {
                    return TuplesKt.to(false, null);
                }
                parent = view2.getParent();
            }
            Rect rect = new Rect();
            return TuplesKt.to(Boolean.valueOf(view.getGlobalVisibleRect(rect, new Point())), rect);
        }
        return TuplesKt.to(false, null);
    }

    public static final boolean isRedactable(Drawable drawable) {
        if (drawable instanceof InsetDrawable ? true : drawable instanceof ColorDrawable ? true : drawable instanceof VectorDrawable ? true : drawable instanceof GradientDrawable) {
            return false;
        }
        if (!(drawable instanceof BitmapDrawable)) {
            return true;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null) {
            return false;
        }
        return !bitmap.isRecycled() && bitmap.getHeight() > 10 && bitmap.getWidth() > 10;
    }

    public static final List<Rect> getVisibleRects(Layout layout, Rect globalRect, int i, int i2) {
        Intrinsics.checkNotNullParameter(globalRect, "globalRect");
        if (layout == null) {
            return CollectionsKt.listOf(globalRect);
        }
        ArrayList arrayList = new ArrayList();
        int lineCount = layout.getLineCount();
        for (int i3 = 0; i3 < lineCount; i3++) {
            int primaryHorizontal = (int) layout.getPrimaryHorizontal(layout.getLineStart(i3));
            int ellipsisCount = layout.getEllipsisCount(i3);
            int primaryHorizontal2 = (int) layout.getPrimaryHorizontal((layout.getLineVisibleEnd(i3) - ellipsisCount) + (ellipsisCount > 0 ? 1 : 0));
            if (primaryHorizontal2 == 0) {
                primaryHorizontal2 = ((int) layout.getPrimaryHorizontal(layout.getLineVisibleEnd(i3) - 1)) + 1;
            }
            int lineTop = layout.getLineTop(i3);
            int lineBottom = layout.getLineBottom(i3);
            Rect rect = new Rect();
            rect.left = globalRect.left + i + primaryHorizontal;
            rect.right = rect.left + (primaryHorizontal2 - primaryHorizontal);
            rect.top = globalRect.top + i2 + lineTop;
            rect.bottom = rect.top + (lineBottom - lineTop);
            arrayList.add(rect);
        }
        return arrayList;
    }

    public static final int getTotalPaddingTopSafe(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        try {
            return textView.getTotalPaddingTop();
        } catch (NullPointerException unused) {
            return textView.getExtendedPaddingTop();
        }
    }
}
