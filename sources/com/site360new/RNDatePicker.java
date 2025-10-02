package com.site360new;

import android.widget.DatePicker;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNDatePicker.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J,\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/site360new/RNDatePicker;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "dateFormatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "getName", "", "pickDate", "", "minDate", "maxDate", "currentDate", "callback", "Lcom/facebook/react/bridge/Callback;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNDatePicker extends ReactContextBaseJavaModule {
    private final DateTimeFormatter dateFormatter;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNDatePicker";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RNDatePicker(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void pickDate(java.lang.String r12, java.lang.String r13, java.lang.String r14, final com.facebook.react.bridge.Callback r15) {
        /*
            r11 = this;
            java.lang.String r0 = "currentDate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "callback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            java.time.LocalDate r0 = java.time.LocalDate.now()
            r1 = r14
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            int r1 = r1.length()
            r2 = 0
            r3 = 1
            if (r1 <= 0) goto L1b
            r1 = r3
            goto L1c
        L1b:
            r1 = r2
        L1c:
            if (r1 == 0) goto L2e
            java.lang.String r0 = "T"
            r1 = 2
            r4 = 0
            java.lang.String r14 = kotlin.text.StringsKt.substringBefore$default(r14, r0, r4, r1, r4)
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            java.time.format.DateTimeFormatter r0 = r11.dateFormatter
            java.time.LocalDate r0 = java.time.LocalDate.parse(r14, r0)
        L2e:
            android.app.Activity r14 = r11.getCurrentActivity()
            if (r14 == 0) goto Lbb
            android.app.DatePickerDialog r1 = new android.app.DatePickerDialog
            r5 = r14
            android.content.Context r5 = (android.content.Context) r5
            int r6 = com.site360new.R.style.DialogTheme
            com.site360new.RNDatePicker$$ExternalSyntheticLambda0 r7 = new com.site360new.RNDatePicker$$ExternalSyntheticLambda0
            r7.<init>()
            int r8 = r0.getYear()
            int r14 = r0.getMonthValue()
            int r9 = r14 + (-1)
            int r10 = r0.getDayOfMonth()
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)
            if (r12 == 0) goto L64
            r14 = r12
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            int r14 = r14.length()
            if (r14 <= 0) goto L5f
            r14 = r3
            goto L60
        L5f:
            r14 = r2
        L60:
            if (r14 != r3) goto L64
            r14 = r3
            goto L65
        L64:
            r14 = r2
        L65:
            if (r14 == 0) goto L86
            android.widget.DatePicker r14 = r1.getDatePicker()
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            java.time.format.DateTimeFormatter r15 = r11.dateFormatter
            java.time.LocalDate r12 = java.time.LocalDate.parse(r12, r15)
            java.time.ZoneId r15 = java.time.ZoneId.systemDefault()
            java.time.ZonedDateTime r12 = r12.atStartOfDay(r15)
            java.time.Instant r12 = r12.toInstant()
            long r4 = r12.toEpochMilli()
            r14.setMinDate(r4)
        L86:
            if (r13 == 0) goto L97
            r12 = r13
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            int r12 = r12.length()
            if (r12 <= 0) goto L93
            r12 = r3
            goto L94
        L93:
            r12 = r2
        L94:
            if (r12 != r3) goto L97
            r2 = r3
        L97:
            if (r2 == 0) goto Lb8
            android.widget.DatePicker r12 = r1.getDatePicker()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            java.time.format.DateTimeFormatter r14 = r11.dateFormatter
            java.time.LocalDate r13 = java.time.LocalDate.parse(r13, r14)
            java.time.ZoneId r14 = java.time.ZoneId.systemDefault()
            java.time.ZonedDateTime r13 = r13.atStartOfDay(r14)
            java.time.Instant r13 = r13.toInstant()
            long r13 = r13.toEpochMilli()
            r12.setMaxDate(r13)
        Lb8:
            r1.show()
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.site360new.RNDatePicker.pickDate(java.lang.String, java.lang.String, java.lang.String, com.facebook.react.bridge.Callback):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pickDate$lambda$1$lambda$0(Callback callback, RNDatePicker this$0, DatePicker datePicker, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        callback.invoke(LocalDate.of(i, i2 + 1, i3).format(this$0.dateFormatter));
    }
}
