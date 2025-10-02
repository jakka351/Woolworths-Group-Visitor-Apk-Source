package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.BackEventCompat;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.DefaultSpecialEffectsController;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: DefaultSpecialEffectsController.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\b\u001f !\"#$%&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0003J\u001e\u0010\n\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J2\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\fH\u0002J$\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u00162\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0016\u0010\u001a\u001a\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0002J&\u0010\u001b\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00170\u001eH\u0002¨\u0006'"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController;", "Landroidx/fragment/app/SpecialEffectsController;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "collectAnimEffects", "", "animationInfos", "", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "collectEffects", "operations", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "createTransitionEffect", "transitionInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "lastIn", "findNamedViews", "namedViews", "", "", "Landroid/view/View;", "view", "syncAnimations", "retainMatchingViews", "Landroidx/collection/ArrayMap;", "names", "", "AnimationEffect", "AnimationInfo", "AnimatorEffect", "Api24Impl", "Api26Impl", "SpecialEffectsInfo", "TransitionEffect", "TransitionInfo", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup container) {
        super(container);
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // androidx.fragment.app.SpecialEffectsController
    public void collectEffects(List<? extends SpecialEffectsController.Operation> operations, boolean isPop) throws Resources.NotFoundException {
        SpecialEffectsController.Operation operation;
        Object next;
        Intrinsics.checkNotNullParameter(operations, "operations");
        Iterator<T> it = operations.iterator();
        while (true) {
            operation = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) next;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.INSTANCE;
            View view = operation2.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            if (companion.asOperationState(view) == SpecialEffectsController.Operation.State.VISIBLE && operation2.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                break;
            }
        }
        SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) next;
        ListIterator<? extends SpecialEffectsController.Operation> listIterator = operations.listIterator(operations.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            SpecialEffectsController.Operation operationPrevious = listIterator.previous();
            SpecialEffectsController.Operation operation4 = operationPrevious;
            SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.INSTANCE;
            View view2 = operation4.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view2, "operation.fragment.mView");
            if (companion2.asOperationState(view2) != SpecialEffectsController.Operation.State.VISIBLE && operation4.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                operation = operationPrevious;
                break;
            }
        }
        SpecialEffectsController.Operation operation5 = operation;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + operation3 + " to " + operation5);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        syncAnimations(operations);
        Iterator<? extends SpecialEffectsController.Operation> it2 = operations.iterator();
        while (it2.hasNext()) {
            final SpecialEffectsController.Operation next2 = it2.next();
            arrayList.add(new AnimationInfo(next2, isPop));
            arrayList2.add(new TransitionInfo(next2, isPop, !isPop ? next2 != operation5 : next2 != operation3));
            next2.addCompletionListener(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultSpecialEffectsController.collectEffects$lambda$2(this.f$0, next2);
                }
            });
        }
        createTransitionEffect(arrayList2, isPop, operation3, operation5);
        collectAnimEffects(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void collectEffects$lambda$2(DefaultSpecialEffectsController this$0, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        this$0.applyContainerChangesToOperation$fragment_release(operation);
    }

    private final void syncAnimations(List<? extends SpecialEffectsController.Operation> operations) {
        Fragment fragment = ((SpecialEffectsController.Operation) CollectionsKt.last((List) operations)).getFragment();
        for (SpecialEffectsController.Operation operation : operations) {
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void collectAnimEffects(List<AnimationInfo> animationInfos) throws Resources.NotFoundException {
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = animationInfos.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList2, ((AnimationInfo) it.next()).getOperation().getEffects$fragment_release());
        }
        boolean z = !arrayList2.isEmpty();
        boolean z2 = false;
        for (AnimationInfo animationInfo : animationInfos) {
            Context context = getContainer().getContext();
            SpecialEffectsController.Operation operation = animationInfo.getOperation();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            if (animation != null) {
                if (animation.animator == null) {
                    arrayList.add(animationInfo);
                } else {
                    Fragment fragment = operation.getFragment();
                    if (!operation.getEffects$fragment_release().isEmpty()) {
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                        }
                    } else {
                        if (operation.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation.setAwaitingContainerChanges(false);
                        }
                        operation.addEffect(new AnimatorEffect(animationInfo));
                        z2 = true;
                    }
                }
            }
        }
        for (AnimationInfo animationInfo2 : arrayList) {
            SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
            } else {
                operation2.addEffect(new AnimationEffect(animationInfo2));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03f4 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void createTransitionEffect(java.util.List<androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo> r28, boolean r29, androidx.fragment.app.SpecialEffectsController.Operation r30, androidx.fragment.app.SpecialEffectsController.Operation r31) {
        /*
            Method dump skipped, instructions count: 1071
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.createTransitionEffect(java.util.List, boolean, androidx.fragment.app.SpecialEffectsController$Operation, androidx.fragment.app.SpecialEffectsController$Operation):void");
    }

    private final void retainMatchingViews(ArrayMap<String, View> arrayMap, final Collection<String> collection) {
        Set<Map.Entry<String, View>> entries = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entries, "entries");
        CollectionsKt.retainAll(entries, new Function1<Map.Entry<String, View>, Boolean>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.retainMatchingViews.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Map.Entry<String, View> entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return Boolean.valueOf(CollectionsKt.contains(collection, ViewCompat.getTransitionName(entry.getValue())));
            }
        });
    }

    private final void findNamedViews(Map<String, View> namedViews, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            namedViews.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (child.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(child, "child");
                    findNamedViews(namedViews, child);
                }
            }
        }
    }

    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "(Landroidx/fragment/app/SpecialEffectsController$Operation;)V", "isVisibilityUnchanged", "", "()Z", "getOperation", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation) {
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.operation = operation;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final boolean isVisibilityUnchanged() {
            View view = this.operation.getFragment().mView;
            SpecialEffectsController.Operation.State stateAsOperationState = view != null ? SpecialEffectsController.Operation.State.INSTANCE.asOperationState(view) : null;
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            return stateAsOperationState == finalState || !(stateAsOperationState == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Z)V", "animation", "Landroidx/fragment/app/FragmentAnim$AnimationOrAnimator;", "isAnimLoaded", "getAnimation", "context", "Landroid/content/Context;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, boolean z) {
            super(operation);
            Intrinsics.checkNotNullParameter(operation, "operation");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) throws Resources.NotFoundException {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = animationOrAnimatorLoadAnimation;
            this.isAnimLoaded = true;
            return animationOrAnimatorLoadAnimation;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000fH\u0002J\u0006\u0010\u0014\u001a\u00020\u0005R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0015"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "isPop", "", "providesSharedElementTransition", "(Landroidx/fragment/app/SpecialEffectsController$Operation;ZZ)V", "handlingImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "getHandlingImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "isOverlapAllowed", "()Z", "sharedElementTransition", "", "getSharedElementTransition", "()Ljava/lang/Object;", "transition", "getTransition", "hasSharedElementTransition", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, boolean z, boolean z2) {
            Object returnTransition;
            boolean allowEnterTransitionOverlap;
            Object sharedElementEnterTransition;
            super(operation);
            Intrinsics.checkNotNullParameter(operation, "operation");
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                Fragment fragment = operation.getFragment();
                returnTransition = z ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                returnTransition = z ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = returnTransition;
            if (operation.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                allowEnterTransitionOverlap = true;
            } else if (z) {
                allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
            } else {
                allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
            }
            this.isOverlapAllowed = allowEnterTransitionOverlap;
            if (!z2) {
                sharedElementEnterTransition = null;
            } else if (z) {
                sharedElementEnterTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                sharedElementEnterTransition = operation.getFragment().getSharedElementEnterTransition();
            }
            this.sharedElementTransition = sharedElementEnterTransition;
        }

        public final Object getTransition() {
            return this.transition;
        }

        /* renamed from: isOverlapAllowed, reason: from getter */
        public final boolean getIsOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object transition) {
            if (transition == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(transition)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(transition)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + transition + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "animationInfo", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "(Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;)V", "getAnimationInfo", "()Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "onCancel", "", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "onCommit", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class AnimationEffect extends SpecialEffectsController.Effect {
        private final AnimationInfo animationInfo;

        public AnimationEffect(AnimationInfo animationInfo) {
            Intrinsics.checkNotNullParameter(animationInfo, "animationInfo");
            this.animationInfo = animationInfo;
        }

        public final AnimationInfo getAnimationInfo() {
            return this.animationInfo;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCommit(ViewGroup container) throws Resources.NotFoundException {
            Intrinsics.checkNotNullParameter(container, "container");
            if (this.animationInfo.isVisibilityUnchanged()) {
                this.animationInfo.getOperation().completeEffect(this);
                return;
            }
            Context context = container.getContext();
            SpecialEffectsController.Operation operation = this.animationInfo.getOperation();
            View view = operation.getFragment().mView;
            AnimationInfo animationInfo = this.animationInfo;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            if (animation == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Animation animation2 = animation.animation;
            if (animation2 == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (operation.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                view.startAnimation(animation2);
                this.animationInfo.getOperation().completeEffect(this);
                return;
            }
            container.startViewTransition(view);
            FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view);
            endViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$AnimationEffect$onCommit$1(operation, container, view, this));
            view.startAnimation(endViewTransitionAnimation);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has started.");
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCancel(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            SpecialEffectsController.Operation operation = this.animationInfo.getOperation();
            View view = operation.getFragment().mView;
            view.clearAnimation();
            container.endViewTransition(view);
            this.animationInfo.getOperation().completeEffect(this);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has been cancelled.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f¨\u0006\u0019"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimatorEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "animatorInfo", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "(Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;)V", "animator", "Landroid/animation/AnimatorSet;", "getAnimator", "()Landroid/animation/AnimatorSet;", "setAnimator", "(Landroid/animation/AnimatorSet;)V", "getAnimatorInfo", "()Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "isSeekingSupported", "", "()Z", "onCancel", "", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "onCommit", "onProgress", "backEvent", "Landroidx/activity/BackEventCompat;", "onStart", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class AnimatorEffect extends SpecialEffectsController.Effect {
        private AnimatorSet animator;
        private final AnimationInfo animatorInfo;

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        /* renamed from: isSeekingSupported */
        public boolean getIsSeekingSupported() {
            return true;
        }

        public AnimatorEffect(AnimationInfo animatorInfo) {
            Intrinsics.checkNotNullParameter(animatorInfo, "animatorInfo");
            this.animatorInfo = animatorInfo;
        }

        public final AnimationInfo getAnimatorInfo() {
            return this.animatorInfo;
        }

        public final AnimatorSet getAnimator() {
            return this.animator;
        }

        public final void setAnimator(AnimatorSet animatorSet) {
            this.animator = animatorSet;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onStart(final ViewGroup container) throws Resources.NotFoundException {
            Intrinsics.checkNotNullParameter(container, "container");
            if (this.animatorInfo.isVisibilityUnchanged()) {
                return;
            }
            Context context = container.getContext();
            AnimationInfo animationInfo = this.animatorInfo;
            Intrinsics.checkNotNullExpressionValue(context, "context");
            FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
            this.animator = animation != null ? animation.animator : null;
            final SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            Fragment fragment = operation.getFragment();
            final boolean z = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
            final View view = fragment.mView;
            container.startViewTransition(view);
            AnimatorSet animatorSet = this.animator;
            if (animatorSet != null) {
                animatorSet.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$AnimatorEffect$onStart$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator anim) {
                        Intrinsics.checkNotNullParameter(anim, "anim");
                        container.endViewTransition(view);
                        if (z) {
                            SpecialEffectsController.Operation.State finalState = operation.getFinalState();
                            View viewToAnimate = view;
                            Intrinsics.checkNotNullExpressionValue(viewToAnimate, "viewToAnimate");
                            finalState.applyState(viewToAnimate, container);
                        }
                        this.getAnimatorInfo().getOperation().completeEffect(this);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has ended.");
                        }
                    }
                });
            }
            AnimatorSet animatorSet2 = this.animator;
            if (animatorSet2 != null) {
                animatorSet2.setTarget(view);
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onProgress(BackEventCompat backEvent, ViewGroup container) {
            Intrinsics.checkNotNullParameter(backEvent, "backEvent");
            Intrinsics.checkNotNullParameter(container, "container");
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            if (Build.VERSION.SDK_INT < 34 || !operation.getFragment().mTransitioning) {
                return;
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Adding BackProgressCallbacks for Animators to operation " + operation);
            }
            long j = Api24Impl.INSTANCE.totalDuration(animatorSet);
            long progress = (long) (backEvent.getProgress() * j);
            if (progress == 0) {
                progress = 1;
            }
            if (progress == j) {
                progress = j - 1;
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Setting currentPlayTime to " + progress + " for Animator " + animatorSet + " on operation " + operation);
            }
            Api26Impl.INSTANCE.setCurrentPlayTime(animatorSet, progress);
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCommit(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            animatorSet.start();
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has started.");
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCancel(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            AnimatorSet animatorSet = this.animator;
            if (animatorSet == null) {
                this.animatorInfo.getOperation().completeEffect(this);
                return;
            }
            SpecialEffectsController.Operation operation = this.animatorInfo.getOperation();
            if (operation.getIsSeeking()) {
                Api26Impl.INSTANCE.reverse(animatorSet);
            } else {
                animatorSet.end();
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled" + (operation.getIsSeeking() ? " with seeking." : ".") + ' ');
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001BÝ\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f\u0012\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f\u0012\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u0012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ(\u0010;\u001a\u00020<2\u0016\u0010=\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010>\u001a\u00020\u000eH\u0002J@\u0010?\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f\u0012\u0004\u0012\u00020\u000b0@2\u0006\u0010A\u001a\u00020B2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010C\u001a\u00020<2\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010D\u001a\u00020<2\u0006\u0010A\u001a\u00020BH\u0016J\u0018\u0010E\u001a\u00020<2\u0006\u0010F\u001a\u00020G2\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010H\u001a\u00020<2\u0006\u0010A\u001a\u00020BH\u0016J6\u0010I\u001a\u00020<2\u0016\u0010J\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010A\u001a\u00020B2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020<0LH\u0002R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR!\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R!\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00130\rj\b\u0012\u0004\u0012\u00020\u0013`\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001d\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010'R\u0014\u0010(\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010'R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010$R\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u000e0\u0012¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R!\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b+\u0010!R!\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f¢\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u00103\u001a\u000204¢\u0006\u000e\n\u0000\u0012\u0004\b5\u00106\u001a\u0004\b7\u00108R\u0011\u00109\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b:\u0010'¨\u0006M"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionEffect;", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "transitionInfos", "", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "lastIn", "transitionImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "sharedElementTransition", "", "sharedElementFirstOutViews", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "sharedElementLastInViews", "sharedElementNameMapping", "Landroidx/collection/ArrayMap;", "", "enteringNames", "exitingNames", "firstOutViews", "lastInViews", "isPop", "", "(Ljava/util/List;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/fragment/app/FragmentTransitionImpl;Ljava/lang/Object;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/collection/ArrayMap;Ljava/util/ArrayList;Ljava/util/ArrayList;Landroidx/collection/ArrayMap;Landroidx/collection/ArrayMap;Z)V", "controller", "getController", "()Ljava/lang/Object;", "setController", "(Ljava/lang/Object;)V", "getEnteringNames", "()Ljava/util/ArrayList;", "getExitingNames", "getFirstOut", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "getFirstOutViews", "()Landroidx/collection/ArrayMap;", "()Z", "isSeekingSupported", "getLastIn", "getLastInViews", "getSharedElementFirstOutViews", "getSharedElementLastInViews", "getSharedElementNameMapping", "getSharedElementTransition", "getTransitionImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "getTransitionInfos", "()Ljava/util/List;", "transitionSignal", "Landroidx/core/os/CancellationSignal;", "getTransitionSignal$annotations", "()V", "getTransitionSignal", "()Landroidx/core/os/CancellationSignal;", "transitioning", "getTransitioning", "captureTransitioningViews", "", "transitioningViews", "view", "createMergedTransition", "Lkotlin/Pair;", RRWebVideoEvent.JsonKeys.CONTAINER, "Landroid/view/ViewGroup;", "onCancel", "onCommit", "onProgress", "backEvent", "Landroidx/activity/BackEventCompat;", "onStart", "runTransition", "enteringViews", "executeTransition", "Lkotlin/Function0;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class TransitionEffect extends SpecialEffectsController.Effect {
        private Object controller;
        private final ArrayList<String> enteringNames;
        private final ArrayList<String> exitingNames;
        private final SpecialEffectsController.Operation firstOut;
        private final ArrayMap<String, View> firstOutViews;
        private final boolean isPop;
        private final SpecialEffectsController.Operation lastIn;
        private final ArrayMap<String, View> lastInViews;
        private final ArrayList<View> sharedElementFirstOutViews;
        private final ArrayList<View> sharedElementLastInViews;
        private final ArrayMap<String, String> sharedElementNameMapping;
        private final Object sharedElementTransition;
        private final FragmentTransitionImpl transitionImpl;
        private final List<TransitionInfo> transitionInfos;
        private final CancellationSignal transitionSignal;

        public static /* synthetic */ void getTransitionSignal$annotations() {
        }

        public final List<TransitionInfo> getTransitionInfos() {
            return this.transitionInfos;
        }

        public final SpecialEffectsController.Operation getFirstOut() {
            return this.firstOut;
        }

        public final SpecialEffectsController.Operation getLastIn() {
            return this.lastIn;
        }

        public final FragmentTransitionImpl getTransitionImpl() {
            return this.transitionImpl;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final ArrayList<View> getSharedElementFirstOutViews() {
            return this.sharedElementFirstOutViews;
        }

        public final ArrayList<View> getSharedElementLastInViews() {
            return this.sharedElementLastInViews;
        }

        public final ArrayMap<String, String> getSharedElementNameMapping() {
            return this.sharedElementNameMapping;
        }

        public final ArrayList<String> getEnteringNames() {
            return this.enteringNames;
        }

        public final ArrayList<String> getExitingNames() {
            return this.exitingNames;
        }

        public final ArrayMap<String, View> getFirstOutViews() {
            return this.firstOutViews;
        }

        public final ArrayMap<String, View> getLastInViews() {
            return this.lastInViews;
        }

        /* renamed from: isPop, reason: from getter */
        public final boolean getIsPop() {
            return this.isPop;
        }

        public TransitionEffect(List<TransitionInfo> transitionInfos, SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, FragmentTransitionImpl transitionImpl, Object obj, ArrayList<View> sharedElementFirstOutViews, ArrayList<View> sharedElementLastInViews, ArrayMap<String, String> sharedElementNameMapping, ArrayList<String> enteringNames, ArrayList<String> exitingNames, ArrayMap<String, View> firstOutViews, ArrayMap<String, View> lastInViews, boolean z) {
            Intrinsics.checkNotNullParameter(transitionInfos, "transitionInfos");
            Intrinsics.checkNotNullParameter(transitionImpl, "transitionImpl");
            Intrinsics.checkNotNullParameter(sharedElementFirstOutViews, "sharedElementFirstOutViews");
            Intrinsics.checkNotNullParameter(sharedElementLastInViews, "sharedElementLastInViews");
            Intrinsics.checkNotNullParameter(sharedElementNameMapping, "sharedElementNameMapping");
            Intrinsics.checkNotNullParameter(enteringNames, "enteringNames");
            Intrinsics.checkNotNullParameter(exitingNames, "exitingNames");
            Intrinsics.checkNotNullParameter(firstOutViews, "firstOutViews");
            Intrinsics.checkNotNullParameter(lastInViews, "lastInViews");
            this.transitionInfos = transitionInfos;
            this.firstOut = operation;
            this.lastIn = operation2;
            this.transitionImpl = transitionImpl;
            this.sharedElementTransition = obj;
            this.sharedElementFirstOutViews = sharedElementFirstOutViews;
            this.sharedElementLastInViews = sharedElementLastInViews;
            this.sharedElementNameMapping = sharedElementNameMapping;
            this.enteringNames = enteringNames;
            this.exitingNames = exitingNames;
            this.firstOutViews = firstOutViews;
            this.lastInViews = lastInViews;
            this.isPop = z;
            this.transitionSignal = new CancellationSignal();
        }

        public final CancellationSignal getTransitionSignal() {
            return this.transitionSignal;
        }

        public final Object getController() {
            return this.controller;
        }

        public final void setController(Object obj) {
            this.controller = obj;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        /* renamed from: isSeekingSupported */
        public boolean getIsSeekingSupported() {
            boolean z;
            if (!this.transitionImpl.isSeekingSupported()) {
                return false;
            }
            List<TransitionInfo> list = this.transitionInfos;
            if ((list instanceof Collection) && list.isEmpty()) {
                z = true;
            } else {
                for (TransitionInfo transitionInfo : list) {
                    if (!(Build.VERSION.SDK_INT >= 34 && transitionInfo.getTransition() != null && this.transitionImpl.isSeekingSupported(transitionInfo.getTransition()))) {
                        z = false;
                        break;
                    }
                }
                z = true;
            }
            if (!z) {
                return false;
            }
            Object obj = this.sharedElementTransition;
            return obj == null || this.transitionImpl.isSeekingSupported(obj);
        }

        public final boolean getTransitioning() {
            List<TransitionInfo> list = this.transitionInfos;
            if ((list instanceof Collection) && list.isEmpty()) {
                return true;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((TransitionInfo) it.next()).getOperation().getFragment().mTransitioning) {
                    return false;
                }
            }
            return true;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onStart(final ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            if (container.isLaidOut()) {
                if (getTransitioning() && this.sharedElementTransition != null && !getIsSeekingSupported()) {
                    Log.i(FragmentManager.TAG, "Ignoring shared elements transition " + this.sharedElementTransition + " between " + this.firstOut + " and " + this.lastIn + " as neither fragment has set a Transition. In order to run a SharedElementTransition, you must also set either an enter or exit transition on a fragment involved in the transaction. The sharedElementTransition will run after the back gesture has been committed.");
                }
                if (getIsSeekingSupported() && getTransitioning()) {
                    final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    Pair<ArrayList<View>, Object> pairCreateMergedTransition = createMergedTransition(container, this.lastIn, this.firstOut);
                    ArrayList<View> arrayListComponent1 = pairCreateMergedTransition.component1();
                    final Object objComponent2 = pairCreateMergedTransition.component2();
                    List<TransitionInfo> list = this.transitionInfos;
                    ArrayList<SpecialEffectsController.Operation> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((TransitionInfo) it.next()).getOperation());
                    }
                    for (final SpecialEffectsController.Operation operation : arrayList) {
                        this.transitionImpl.setListenerForTransitionEnd(operation.getFragment(), objComponent2, this.transitionSignal, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                DefaultSpecialEffectsController.TransitionEffect.onStart$lambda$6$lambda$4(objectRef);
                            }
                        }, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                DefaultSpecialEffectsController.TransitionEffect.onStart$lambda$6$lambda$5(operation, this);
                            }
                        });
                    }
                    runTransition(arrayListComponent1, container, new Function0<Unit>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Type inference failed for: r1v6, types: [T, androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$2] */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            DefaultSpecialEffectsController.TransitionEffect transitionEffect = this.this$0;
                            transitionEffect.setController(transitionEffect.getTransitionImpl().controlDelayedTransition(container, objComponent2));
                            boolean z = this.this$0.getController() != null;
                            Object obj = objComponent2;
                            ViewGroup viewGroup = container;
                            if (!z) {
                                throw new IllegalStateException(("Unable to start transition " + obj + " for container " + viewGroup + ClassUtils.PACKAGE_SEPARATOR_CHAR).toString());
                            }
                            objectRef.element = new AnonymousClass2(this.this$0, container);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Started executing operations from " + this.this$0.getFirstOut() + " to " + this.this$0.getLastIn());
                            }
                        }

                        /* compiled from: DefaultSpecialEffectsController.kt */
                        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
                        /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$2, reason: invalid class name */
                        static final class AnonymousClass2 extends Lambda implements Function0<Unit> {
                            final /* synthetic */ ViewGroup $container;
                            final /* synthetic */ DefaultSpecialEffectsController.TransitionEffect this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(DefaultSpecialEffectsController.TransitionEffect transitionEffect, ViewGroup viewGroup) {
                                super(0);
                                this.this$0 = transitionEffect;
                                this.$container = viewGroup;
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                if (FragmentManager.isLoggingEnabled(2)) {
                                    Log.v(FragmentManager.TAG, "Animating to start");
                                }
                                FragmentTransitionImpl transitionImpl = this.this$0.getTransitionImpl();
                                Object controller = this.this$0.getController();
                                Intrinsics.checkNotNull(controller);
                                final DefaultSpecialEffectsController.TransitionEffect transitionEffect = this.this$0;
                                final ViewGroup viewGroup = this.$container;
                                transitionImpl.animateToStart(controller, 
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0026: INVOKE 
                                      (r0v3 'transitionImpl' androidx.fragment.app.FragmentTransitionImpl)
                                      (r1v1 'controller' java.lang.Object)
                                      (wrap:java.lang.Runnable:0x0023: CONSTRUCTOR 
                                      (r2v0 'transitionEffect' androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect A[DONT_INLINE])
                                      (r3v0 'viewGroup' android.view.ViewGroup A[DONT_INLINE])
                                     A[MD:(androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect, android.view.ViewGroup):void (m), WRAPPED] call: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$2$$ExternalSyntheticLambda0.<init>(androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect, android.view.ViewGroup):void type: CONSTRUCTOR)
                                     VIRTUAL call: androidx.fragment.app.FragmentTransitionImpl.animateToStart(java.lang.Object, java.lang.Runnable):void A[MD:(java.lang.Object, java.lang.Runnable):void (m)] in method: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4.2.invoke():void, file: classes.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:298)
                                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:277)
                                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:410)
                                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
                                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$2$$ExternalSyntheticLambda0, state: NOT_LOADED
                                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                    	... 15 more
                                    */
                                /*
                                    this = this;
                                    r0 = 2
                                    boolean r0 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r0)
                                    if (r0 == 0) goto Le
                                    java.lang.String r0 = "FragmentManager"
                                    java.lang.String r1 = "Animating to start"
                                    android.util.Log.v(r0, r1)
                                Le:
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r0 = r5.this$0
                                    androidx.fragment.app.FragmentTransitionImpl r0 = r0.getTransitionImpl()
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r1 = r5.this$0
                                    java.lang.Object r1 = r1.getController()
                                    kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect r2 = r5.this$0
                                    android.view.ViewGroup r3 = r5.$container
                                    androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$2$$ExternalSyntheticLambda0 r4 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4$2$$ExternalSyntheticLambda0
                                    r4.<init>(r2, r3)
                                    r0.animateToStart(r1, r4)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onStart$4.AnonymousClass2.invoke2():void");
                            }

                            /* JADX INFO: Access modifiers changed from: private */
                            public static final void invoke$lambda$1(DefaultSpecialEffectsController.TransitionEffect this$0, ViewGroup container) {
                                Intrinsics.checkNotNullParameter(this$0, "this$0");
                                Intrinsics.checkNotNullParameter(container, "$container");
                                Iterator<T> it = this$0.getTransitionInfos().iterator();
                                while (it.hasNext()) {
                                    SpecialEffectsController.Operation operation = ((DefaultSpecialEffectsController.TransitionInfo) it.next()).getOperation();
                                    View view = operation.getFragment().getView();
                                    if (view != null) {
                                        operation.getFinalState().applyState(view, container);
                                    }
                                }
                            }
                        }
                    });
                    return;
                }
                return;
            }
            Iterator<T> it2 = this.transitionInfos.iterator();
            while (it2.hasNext()) {
                SpecialEffectsController.Operation operation2 = ((TransitionInfo) it2.next()).getOperation();
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "SpecialEffectsController: Container " + container + " has not been laid out. Skipping onStart for operation " + operation2);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onStart$lambda$6$lambda$4(Ref.ObjectRef seekCancelLambda) {
            Intrinsics.checkNotNullParameter(seekCancelLambda, "$seekCancelLambda");
            Function0 function0 = (Function0) seekCancelLambda.element;
            if (function0 != null) {
                function0.invoke();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onStart$lambda$6$lambda$5(SpecialEffectsController.Operation operation, TransitionEffect this$0) {
            Intrinsics.checkNotNullParameter(operation, "$operation");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
            }
            operation.completeEffect(this$0);
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onProgress(BackEventCompat backEvent, ViewGroup container) {
            Intrinsics.checkNotNullParameter(backEvent, "backEvent");
            Intrinsics.checkNotNullParameter(container, "container");
            Object obj = this.controller;
            if (obj != null) {
                this.transitionImpl.setCurrentPlayTime(obj, backEvent.getProgress());
            }
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCommit(final ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            if (container.isLaidOut()) {
                Object obj = this.controller;
                if (obj != null) {
                    FragmentTransitionImpl fragmentTransitionImpl = this.transitionImpl;
                    Intrinsics.checkNotNull(obj);
                    fragmentTransitionImpl.animateToEnd(obj);
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Ending execution of operations from " + this.firstOut + " to " + this.lastIn);
                        return;
                    }
                    return;
                }
                Pair<ArrayList<View>, Object> pairCreateMergedTransition = createMergedTransition(container, this.lastIn, this.firstOut);
                ArrayList<View> arrayListComponent1 = pairCreateMergedTransition.component1();
                final Object objComponent2 = pairCreateMergedTransition.component2();
                List<TransitionInfo> list = this.transitionInfos;
                ArrayList<SpecialEffectsController.Operation> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((TransitionInfo) it.next()).getOperation());
                }
                for (final SpecialEffectsController.Operation operation : arrayList) {
                    this.transitionImpl.setListenerForTransitionEnd(operation.getFragment(), objComponent2, this.transitionSignal, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.TransitionEffect.onCommit$lambda$11$lambda$10(operation, this);
                        }
                    });
                }
                runTransition(arrayListComponent1, container, new Function0<Unit>() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$onCommit$4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        this.this$0.getTransitionImpl().beginDelayedTransition(container, objComponent2);
                    }
                });
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Completed executing operations from " + this.firstOut + " to " + this.lastIn);
                    return;
                }
                return;
            }
            for (TransitionInfo transitionInfo : this.transitionInfos) {
                SpecialEffectsController.Operation operation2 = transitionInfo.getOperation();
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "SpecialEffectsController: Container " + container + " has not been laid out. Completing operation " + operation2);
                }
                transitionInfo.getOperation().completeEffect(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onCommit$lambda$11$lambda$10(SpecialEffectsController.Operation operation, TransitionEffect this$0) {
            Intrinsics.checkNotNullParameter(operation, "$operation");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
            }
            operation.completeEffect(this$0);
        }

        private final Pair<ArrayList<View>, Object> createMergedTransition(ViewGroup container, SpecialEffectsController.Operation lastIn, final SpecialEffectsController.Operation firstOut) {
            Iterator<TransitionInfo> it;
            final SpecialEffectsController.Operation operation = lastIn;
            View view = new View(container.getContext());
            final Rect rect = new Rect();
            Iterator<TransitionInfo> it2 = this.transitionInfos.iterator();
            boolean z = false;
            View view2 = null;
            while (it2.hasNext()) {
                if (it2.next().hasSharedElementTransition() && firstOut != null && operation != null && (!this.sharedElementNameMapping.isEmpty()) && this.sharedElementTransition != null) {
                    FragmentTransition.callSharedElementStartEnd(lastIn.getFragment(), firstOut.getFragment(), this.isPop, this.firstOutViews, true);
                    ViewGroup viewGroup = container;
                    OneShotPreDrawListener.add(viewGroup, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$12(operation, firstOut, this);
                        }
                    });
                    this.sharedElementFirstOutViews.addAll(this.firstOutViews.values());
                    if (!this.exitingNames.isEmpty()) {
                        String str = this.exitingNames.get(0);
                        Intrinsics.checkNotNullExpressionValue(str, "exitingNames[0]");
                        view2 = this.firstOutViews.get(str);
                        this.transitionImpl.setEpicenter(this.sharedElementTransition, view2);
                    }
                    this.sharedElementLastInViews.addAll(this.lastInViews.values());
                    if (!this.enteringNames.isEmpty()) {
                        String str2 = this.enteringNames.get(0);
                        Intrinsics.checkNotNullExpressionValue(str2, "enteringNames[0]");
                        final View view3 = this.lastInViews.get(str2);
                        if (view3 != null) {
                            final FragmentTransitionImpl fragmentTransitionImpl = this.transitionImpl;
                            OneShotPreDrawListener.add(viewGroup, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda4
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$13(fragmentTransitionImpl, view3, rect);
                                }
                            });
                            z = true;
                        }
                    }
                    this.transitionImpl.setSharedElementTargets(this.sharedElementTransition, view, this.sharedElementFirstOutViews);
                    FragmentTransitionImpl fragmentTransitionImpl2 = this.transitionImpl;
                    Object obj = this.sharedElementTransition;
                    fragmentTransitionImpl2.scheduleRemoveTargets(obj, null, null, null, null, obj, this.sharedElementLastInViews);
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator<TransitionInfo> it3 = this.transitionInfos.iterator();
            Object objMergeTransitionsTogether = null;
            Object objMergeTransitionsTogether2 = null;
            while (it3.hasNext()) {
                TransitionInfo next = it3.next();
                SpecialEffectsController.Operation operation2 = next.getOperation();
                Object objCloneTransition = this.transitionImpl.cloneTransition(next.getTransition());
                if (objCloneTransition != null) {
                    final ArrayList<View> arrayList2 = new ArrayList<>();
                    it = it3;
                    View view4 = operation2.getFragment().mView;
                    Object obj2 = objMergeTransitionsTogether2;
                    Intrinsics.checkNotNullExpressionValue(view4, "operation.fragment.mView");
                    captureTransitioningViews(arrayList2, view4);
                    if (this.sharedElementTransition != null && (operation2 == firstOut || operation2 == operation)) {
                        if (operation2 == firstOut) {
                            arrayList2.removeAll(CollectionsKt.toSet(this.sharedElementFirstOutViews));
                        } else {
                            arrayList2.removeAll(CollectionsKt.toSet(this.sharedElementLastInViews));
                        }
                    }
                    if (arrayList2.isEmpty()) {
                        this.transitionImpl.addTarget(objCloneTransition, view);
                    } else {
                        this.transitionImpl.addTargets(objCloneTransition, arrayList2);
                        this.transitionImpl.scheduleRemoveTargets(objCloneTransition, objCloneTransition, arrayList2, null, null, null, null);
                        if (operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            operation2.setAwaitingContainerChanges(false);
                            ArrayList<View> arrayList3 = new ArrayList<>(arrayList2);
                            arrayList3.remove(operation2.getFragment().mView);
                            this.transitionImpl.scheduleHideFragmentView(objCloneTransition, operation2.getFragment().mView, arrayList3);
                            OneShotPreDrawListener.add(container, new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda5
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DefaultSpecialEffectsController.TransitionEffect.createMergedTransition$lambda$14(arrayList2);
                                }
                            });
                        }
                    }
                    if (operation2.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList.addAll(arrayList2);
                        if (z) {
                            this.transitionImpl.setEpicenter(objCloneTransition, rect);
                        }
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Entering Transition: " + objCloneTransition);
                            Log.v(FragmentManager.TAG, ">>>>> EnteringViews <<<<<");
                            Iterator<View> it4 = arrayList2.iterator();
                            while (it4.hasNext()) {
                                View transitioningViews = it4.next();
                                Intrinsics.checkNotNullExpressionValue(transitioningViews, "transitioningViews");
                                Log.v(FragmentManager.TAG, "View: " + transitioningViews);
                            }
                        }
                    } else {
                        this.transitionImpl.setEpicenter(objCloneTransition, view2);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Exiting Transition: " + objCloneTransition);
                            Log.v(FragmentManager.TAG, ">>>>> ExitingViews <<<<<");
                            Iterator<View> it5 = arrayList2.iterator();
                            while (it5.hasNext()) {
                                View transitioningViews2 = it5.next();
                                Intrinsics.checkNotNullExpressionValue(transitioningViews2, "transitioningViews");
                                Log.v(FragmentManager.TAG, "View: " + transitioningViews2);
                            }
                        }
                    }
                    if (next.getIsOverlapAllowed()) {
                        objMergeTransitionsTogether = this.transitionImpl.mergeTransitionsTogether(objMergeTransitionsTogether, objCloneTransition, null);
                        operation = lastIn;
                        it3 = it;
                        objMergeTransitionsTogether2 = obj2;
                    } else {
                        objMergeTransitionsTogether2 = this.transitionImpl.mergeTransitionsTogether(obj2, objCloneTransition, null);
                    }
                } else {
                    it = it3;
                }
                operation = lastIn;
                it3 = it;
            }
            Object objMergeTransitionsInSequence = this.transitionImpl.mergeTransitionsInSequence(objMergeTransitionsTogether, objMergeTransitionsTogether2, this.sharedElementTransition);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, "Final merged transition: " + objMergeTransitionsInSequence);
            }
            return new Pair<>(arrayList, objMergeTransitionsInSequence);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void createMergedTransition$lambda$12(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, TransitionEffect this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), this$0.isPop, this$0.lastInViews, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void createMergedTransition$lambda$13(FragmentTransitionImpl impl, View view, Rect lastInEpicenterRect) {
            Intrinsics.checkNotNullParameter(impl, "$impl");
            Intrinsics.checkNotNullParameter(lastInEpicenterRect, "$lastInEpicenterRect");
            impl.getBoundsOnScreen(view, lastInEpicenterRect);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void createMergedTransition$lambda$14(ArrayList transitioningViews) {
            Intrinsics.checkNotNullParameter(transitioningViews, "$transitioningViews");
            FragmentTransition.setViewVisibility(transitioningViews, 4);
        }

        private final void runTransition(ArrayList<View> enteringViews, ViewGroup container, Function0<Unit> executeTransition) {
            ArrayList<View> arrayList = enteringViews;
            FragmentTransition.setViewVisibility(arrayList, 4);
            ArrayList<String> arrayListPrepareSetNameOverridesReordered = this.transitionImpl.prepareSetNameOverridesReordered(this.sharedElementLastInViews);
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v(FragmentManager.TAG, ">>>>> Beginning transition <<<<<");
                Log.v(FragmentManager.TAG, ">>>>> SharedElementFirstOutViews <<<<<");
                Iterator<View> it = this.sharedElementFirstOutViews.iterator();
                while (it.hasNext()) {
                    View sharedElementFirstOutViews = it.next();
                    Intrinsics.checkNotNullExpressionValue(sharedElementFirstOutViews, "sharedElementFirstOutViews");
                    View view = sharedElementFirstOutViews;
                    Log.v(FragmentManager.TAG, "View: " + view + " Name: " + ViewCompat.getTransitionName(view));
                }
                Log.v(FragmentManager.TAG, ">>>>> SharedElementLastInViews <<<<<");
                Iterator<View> it2 = this.sharedElementLastInViews.iterator();
                while (it2.hasNext()) {
                    View sharedElementLastInViews = it2.next();
                    Intrinsics.checkNotNullExpressionValue(sharedElementLastInViews, "sharedElementLastInViews");
                    View view2 = sharedElementLastInViews;
                    Log.v(FragmentManager.TAG, "View: " + view2 + " Name: " + ViewCompat.getTransitionName(view2));
                }
            }
            executeTransition.invoke();
            this.transitionImpl.setNameOverridesReordered(container, this.sharedElementFirstOutViews, this.sharedElementLastInViews, arrayListPrepareSetNameOverridesReordered, this.sharedElementNameMapping);
            FragmentTransition.setViewVisibility(arrayList, 0);
            this.transitionImpl.swapSharedElementTargets(this.sharedElementTransition, this.sharedElementFirstOutViews, this.sharedElementLastInViews);
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Effect
        public void onCancel(ViewGroup container) {
            Intrinsics.checkNotNullParameter(container, "container");
            this.transitionSignal.cancel();
        }

        private final void captureTransitioningViews(ArrayList<View> transitioningViews, View view) {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                    if (transitioningViews.contains(view)) {
                        return;
                    }
                    transitioningViews.add(view);
                    return;
                }
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = viewGroup.getChildAt(i);
                    if (child.getVisibility() == 0) {
                        Intrinsics.checkNotNullExpressionValue(child, "child");
                        captureTransitioningViews(transitioningViews, child);
                    }
                }
                return;
            }
            if (transitioningViews.contains(view)) {
                return;
            }
            transitioningViews.add(view);
        }
    }

    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$Api24Impl;", "", "()V", "totalDuration", "", "animatorSet", "Landroid/animation/AnimatorSet;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        public final long totalDuration(AnimatorSet animatorSet) {
            Intrinsics.checkNotNullParameter(animatorSet, "animatorSet");
            return animatorSet.getTotalDuration();
        }
    }

    /* compiled from: DefaultSpecialEffectsController.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$Api26Impl;", "", "()V", "reverse", "", "animatorSet", "Landroid/animation/AnimatorSet;", "setCurrentPlayTime", "time", "", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Api26Impl {
        public static final Api26Impl INSTANCE = new Api26Impl();

        private Api26Impl() {
        }

        public final void reverse(AnimatorSet animatorSet) {
            Intrinsics.checkNotNullParameter(animatorSet, "animatorSet");
            animatorSet.reverse();
        }

        public final void setCurrentPlayTime(AnimatorSet animatorSet, long time) {
            Intrinsics.checkNotNullParameter(animatorSet, "animatorSet");
            animatorSet.setCurrentPlayTime(time);
        }
    }
}
