package io.legere.pdfiumandroid.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Config.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\t"}, d2 = {"pdfiumConfig", "Lio/legere/pdfiumandroid/util/Config;", "getPdfiumConfig", "()Lio/legere/pdfiumandroid/util/Config;", "setPdfiumConfig", "(Lio/legere/pdfiumandroid/util/Config;)V", "handleAlreadyClosed", "", "isClosed", "pdfiumandroid_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConfigKt {
    private static Config pdfiumConfig = new Config(null, null, 3, null);

    /* compiled from: Config.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AlreadyClosedBehavior.values().length];
            try {
                iArr[AlreadyClosedBehavior.EXCEPTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AlreadyClosedBehavior.IGNORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Config getPdfiumConfig() {
        return pdfiumConfig;
    }

    public static final void setPdfiumConfig(Config config) {
        Intrinsics.checkNotNullParameter(config, "<set-?>");
        pdfiumConfig = config;
    }

    public static final boolean handleAlreadyClosed(boolean z) {
        if (z) {
            int i = WhenMappings.$EnumSwitchMapping$0[pdfiumConfig.getAlreadyClosedBehavior().ordinal()];
            if (i == 1) {
                throw new IllegalStateException("Already closed".toString());
            }
            if (i == 2) {
                pdfiumConfig.getLogger().d("PdfiumCore", "Already closed");
            }
        }
        return z;
    }
}
