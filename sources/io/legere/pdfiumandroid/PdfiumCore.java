package io.legere.pdfiumandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.ParcelFileDescriptor;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import com.reactnativedevicecountry.DeviceCountryModule;
import io.legere.pdfiumandroid.PdfDocument;
import io.legere.pdfiumandroid.util.Config;
import io.legere.pdfiumandroid.util.ConfigKt;
import io.legere.pdfiumandroid.util.InitLock;
import io.legere.pdfiumandroid.util.Size;
import io.sentry.Session;
import io.sentry.protocol.MetricSummary;
import io.sentry.protocol.SentryStackFrame;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.IOException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;

/* compiled from: PdfiumCore.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 Y2\u00020\u0001:\u0001YB\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\nH\u0007J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\nH\u0007J\u0018\u0010\u001f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\nH\u0007J\u0018\u0010 \u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00192\u0006\u0010\r\u001a\u00020\u000eH\u0007JP\u0010#\u001a\u00020$2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+H\u0007JH\u0010-\u001a\u00020.2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u001cH\u0007JH\u00100\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\n2\u0006\u0010/\u001a\u00020.H\u0007J\u0013\u00101\u001a\u0004\u0018\u00010\u001c2\u0006\u00102\u001a\u000203H\u0082 J\u001b\u00104\u001a\u0002032\u0006\u00105\u001a\u00020\n2\b\u00106\u001a\u0004\u0018\u000107H\u0082 J\u001d\u00108\u001a\u0002032\b\u00109\u001a\u0004\u0018\u00010:2\b\u00106\u001a\u0004\u0018\u000107H\u0082 J\u000e\u0010;\u001a\u00020\u000e2\u0006\u00105\u001a\u00020<J\u0018\u0010;\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020<2\b\u00106\u001a\u0004\u0018\u000107J\u0010\u0010;\u001a\u00020\u000e2\b\u00109\u001a\u0004\u0018\u00010:J\u001a\u0010;\u001a\u00020\u000e2\b\u00109\u001a\u0004\u0018\u00010:2\b\u00106\u001a\u0004\u0018\u000107J\u0018\u0010>\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J+\u0010>\u001a\b\u0012\u0004\u0012\u0002030?2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020\nH\u0007¢\u0006\u0002\u0010BJ\u0018\u0010C\u001a\u0002032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007JL\u0010D\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020\n2\b\b\u0002\u0010I\u001a\u00020JH\u0007JL\u0010K\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020\n2\b\b\u0002\u0010I\u001a\u00020JH\u0007JV\u0010K\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020\n2\b\b\u0002\u0010I\u001a\u00020J2\b\b\u0002\u0010N\u001a\u00020JH\u0007J\u0018\u0010O\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\nH\u0007J(\u0010P\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010Q\u001a\u00020\n2\u0006\u0010R\u001a\u00020\nH\u0007J*\u0010S\u001a\u0004\u0018\u0001072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010T\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020\nH\u0007J\"\u0010V\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0007J*\u0010W\u001a\u0004\u0018\u0001072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010X\u001a\u00020\n2\u0006\u0010R\u001a\u00020\nH\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lio/legere/pdfiumandroid/PdfiumCore;", "", "context", "Landroid/content/Context;", DeviceCountryModule.TYPE_CONFIGURATION, "Lio/legere/pdfiumandroid/util/Config;", "(Landroid/content/Context;Lio/legere/pdfiumandroid/util/Config;)V", "getConfig", "()Lio/legere/pdfiumandroid/util/Config;", "mCurrentDpi", "", "closeDocument", "", "pdfDocument", "Lio/legere/pdfiumandroid/PdfDocument;", "closePage", "pageIndex", "closeTextPage", "getDocumentMeta", "Lio/legere/pdfiumandroid/PdfDocument$Meta;", "getPageCount", "getPageHeight", "index", "getPageHeightPoint", "getPageLinks", "", "Lio/legere/pdfiumandroid/PdfDocument$Link;", "getPageMediaBox", "Landroid/graphics/RectF;", "getPageSize", "Lio/legere/pdfiumandroid/util/Size;", "getPageWidth", "getPageWidthPoint", "getTableOfContents", "Lio/legere/pdfiumandroid/PdfDocument$Bookmark;", "mapPageCoordsToDevice", "Landroid/graphics/Point;", "startX", "startY", "sizeX", "sizeY", "rotate", "pageX", "", "pageY", "mapRectToDevice", "Landroid/graphics/Rect;", "coords", "mapRectToPage", "nativeGetLinkRect", "linkPtr", "", "nativeOpenDocument", "fd", "password", "", "nativeOpenMemDocument", "data", "", "newDocument", "Landroid/os/ParcelFileDescriptor;", "parcelFileDescriptor", "openPage", "", "fromIndex", "toIndex", "(Lio/legere/pdfiumandroid/PdfDocument;II)[Ljava/lang/Long;", "openTextPage", "renderPage", "surface", "Landroid/view/Surface;", "drawSizeX", "drawSizeY", "renderAnnot", "", "renderPageBitmap", "bitmap", "Landroid/graphics/Bitmap;", "textMask", "textPageCountChars", "textPageCountRects", "startIndex", MetricSummary.JsonKeys.COUNT, "textPageGetBoundedText", "sourceRect", RRWebVideoEvent.JsonKeys.SIZE, "textPageGetRect", "textPageGetText", "start", "Companion", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfiumCore {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private static final InitLock isReady;
    private static final Object lock;
    private final Config config;
    private final int mCurrentDpi;

    public PdfiumCore() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    private final native RectF nativeGetLinkRect(long linkPtr);

    private final native long nativeOpenDocument(int fd, String password);

    private final native long nativeOpenMemDocument(byte[] data, String password);

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use page.close()", replaceWith = @ReplaceWith(expression = "page.close()", imports = {}))
    public final void closePage(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use textPage.close()", replaceWith = @ReplaceWith(expression = "textPage.close()", imports = {}))
    public final void closeTextPage(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfDocument.openPage()", replaceWith = @ReplaceWith(expression = "pdfDocument.openPage(pageIndex)", imports = {}))
    public final long openPage(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        return pageIndex;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfDocument.openTextPage()", replaceWith = @ReplaceWith(expression = "pdfDocument.openTextPage(pageIndex)", imports = {}))
    public final long openTextPage(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        return pageIndex;
    }

    public PdfiumCore(Context context, Config config) {
        Resources resources;
        DisplayMetrics displayMetrics;
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
        ConfigKt.setPdfiumConfig(config);
        Logger.INSTANCE.setLogger(config.getLogger());
        Logger logger = Logger.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        logger.d(TAG2, "Starting PdfiumAndroid ");
        this.mCurrentDpi = (context == null || (resources = context.getResources()) == null || (displayMetrics = resources.getDisplayMetrics()) == null) ? -1 : displayMetrics.densityDpi;
        isReady.waitForReady();
    }

    public /* synthetic */ PdfiumCore(Context context, Config config, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : context, (i & 2) != 0 ? new Config(null, null, 3, null) : config);
    }

    public final Config getConfig() {
        return this.config;
    }

    public final PdfDocument newDocument(ParcelFileDescriptor fd) throws IOException {
        Intrinsics.checkNotNullParameter(fd, "fd");
        return newDocument(fd, (String) null);
    }

    public final PdfDocument newDocument(ParcelFileDescriptor parcelFileDescriptor, String password) throws IOException {
        PdfDocument pdfDocument;
        Intrinsics.checkNotNullParameter(parcelFileDescriptor, "parcelFileDescriptor");
        synchronized (lock) {
            pdfDocument = new PdfDocument(nativeOpenDocument(parcelFileDescriptor.getFd(), password));
            pdfDocument.setParcelFileDescriptor(parcelFileDescriptor);
        }
        return pdfDocument;
    }

    public final PdfDocument newDocument(byte[] data) throws IOException {
        return newDocument(data, (String) null);
    }

    public final PdfDocument newDocument(byte[] data, String password) throws IOException {
        PdfDocument pdfDocument;
        synchronized (lock) {
            pdfDocument = new PdfDocument(nativeOpenMemDocument(data, password));
            pdfDocument.setParcelFileDescriptor(null);
        }
        return pdfDocument;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfDocument.getPageCount()", replaceWith = @ReplaceWith(expression = "pdfDocument.getPageCount()", imports = {}))
    public final void getPageCount(PdfDocument pdfDocument) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        pdfDocument.getPageCount();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfDocument.closeDocument()", replaceWith = @ReplaceWith(expression = "pdfDocument.close()", imports = {}))
    public final void closeDocument(PdfDocument pdfDocument) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        pdfDocument.close();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfDocument.getTableOfContents()", replaceWith = @ReplaceWith(expression = "pdfDocument.getTableOfContents()", imports = {}))
    public final List<PdfDocument.Bookmark> getTableOfContents(PdfDocument pdfDocument) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        return pdfDocument.getTableOfContents();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use Page.getPageMediaBox()", replaceWith = @ReplaceWith(expression = "page.getPageMediaBox()", imports = {}))
    public final RectF getPageMediaBox(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            RectF pageMediaBox = pdfPageOpenPage.getPageMediaBox();
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageMediaBox;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use textPage.textPageCountChars()", replaceWith = @ReplaceWith(expression = "textPage.textPageCountChars()", imports = {}))
    public final int textPageCountChars(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfTextPage pdfTextPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfTextPageOpenPage = pdfTextPageOpenPage.openTextPage();
            try {
                int iTextPageCountChars = pdfTextPageOpenPage.textPageCountChars();
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                return iTextPageCountChars;
            } finally {
            }
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use textPage.textPageGetText(start, count)", replaceWith = @ReplaceWith(expression = "textPage.textPageGetText(start, count)", imports = {}))
    public final String textPageGetText(PdfDocument pdfDocument, int pageIndex, int start, int count) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfTextPage pdfTextPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfTextPageOpenPage = pdfTextPageOpenPage.openTextPage();
            try {
                String strTextPageGetText = pdfTextPageOpenPage.textPageGetText(start, count);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                return strTextPageGetText;
            } finally {
            }
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use pdfDocument.getDocumentMeta()", replaceWith = @ReplaceWith(expression = "pdfDocument.getDocumentMeta()", imports = {}))
    public final PdfDocument.Meta getDocumentMeta(PdfDocument pdfDocument) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        return pdfDocument.getDocumentMeta();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.getPageWidthPoint()", replaceWith = @ReplaceWith(expression = "page.getPageWidthPoint()", imports = {}))
    public final int getPageWidthPoint(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            int pageWidthPoint = pdfPageOpenPage.getPageWidthPoint();
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageWidthPoint;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.getPageHeightPoint()", replaceWith = @ReplaceWith(expression = "page.getPageHeightPoint()", imports = {}))
    public final int getPageHeightPoint(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            int pageHeightPoint = pdfPageOpenPage.getPageHeightPoint();
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageHeightPoint;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.renderPageBitmap(bitmap, startX, startY, drawSizeX, drawSizeY, screenDpi, renderAnnot, textMask)", replaceWith = @ReplaceWith(expression = "page.renderPageBitmap(bitmap, startX, startY, drawSizeX, drawSizeY, screenDpi, renderAnnot, textMask)", imports = {}))
    public final void renderPageBitmap(PdfDocument pdfDocument, Bitmap bitmap, int pageIndex, int startX, int startY, int drawSizeX, int drawSizeY, boolean renderAnnot, boolean textMask) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfPageOpenPage.renderPageBitmap(bitmap, startX, startY, drawSizeX, drawSizeY, renderAnnot, textMask);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(pdfPageOpenPage, null);
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.textPageGetRect(index)", replaceWith = @ReplaceWith(expression = "page.textPageGetRect(index)", imports = {}))
    public final RectF textPageGetRect(PdfDocument pdfDocument, int pageIndex, int index) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfTextPage pdfTextPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfTextPageOpenPage = pdfTextPageOpenPage.openTextPage();
            try {
                RectF rectFTextPageGetRect = pdfTextPageOpenPage.textPageGetRect(index);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                return rectFTextPageGetRect;
            } finally {
            }
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.textPageGetBoundedText(sourceRect, size)", replaceWith = @ReplaceWith(expression = "page.textPageGetBoundedText(sourceRect, size)", imports = {}))
    public final String textPageGetBoundedText(PdfDocument pdfDocument, int pageIndex, RectF sourceRect, int size) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        Intrinsics.checkNotNullParameter(sourceRect, "sourceRect");
        PdfTextPage pdfTextPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfTextPageOpenPage = pdfTextPageOpenPage.openTextPage();
            try {
                String strTextPageGetBoundedText = pdfTextPageOpenPage.textPageGetBoundedText(sourceRect, size);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                return strTextPageGetBoundedText;
            } finally {
            }
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.mapRectToPage(startX, startY, sizeX, sizeY, rotate, coords)", replaceWith = @ReplaceWith(expression = "page.mapRectToPage(startX, startY, sizeX, sizeY, rotate, coords)", imports = {}))
    public final RectF mapRectToPage(PdfDocument pdfDocument, int pageIndex, int startX, int startY, int sizeX, int sizeY, int rotate, Rect coords) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        Intrinsics.checkNotNullParameter(coords, "coords");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            RectF rectFMapRectToPage = pdfPageOpenPage.mapRectToPage(startX, startY, sizeX, sizeY, rotate, coords);
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return rectFMapRectToPage;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfTextPage.textPageCountRects(startIndex, count)", replaceWith = @ReplaceWith(expression = "textPage.textPageCountRects(startIndex, count)", imports = {}))
    public final int textPageCountRects(PdfDocument pdfDocument, int pageIndex, int startIndex, int count) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfTextPage pdfTextPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfTextPageOpenPage = pdfTextPageOpenPage.openTextPage();
            try {
                int iTextPageCountRects = pdfTextPageOpenPage.textPageCountRects(startIndex, count);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                CloseableKt.closeFinally(pdfTextPageOpenPage, null);
                return iTextPageCountRects;
            } finally {
            }
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use PdfDocument.openPage(fromIndex, toIndex)", replaceWith = @ReplaceWith(expression = "pdfDocument.openPage(fromIndex, toIndex)", imports = {}))
    public final Long[] openPage(PdfDocument pdfDocument, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        return (Long[]) CollectionsKt.toList(new LongRange(fromIndex, toIndex)).toArray(new Long[0]);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.getPageWidth()", replaceWith = @ReplaceWith(expression = "page.getPageWidth()", imports = {}))
    public final int getPageWidth(PdfDocument pdfDocument, int index) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(index);
        try {
            int pageWidth = pdfPageOpenPage.getPageWidth(this.mCurrentDpi);
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageWidth;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.getPageHeight()", replaceWith = @ReplaceWith(expression = "page.getPageHeight()", imports = {}))
    public final int getPageHeight(PdfDocument pdfDocument, int index) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(index);
        try {
            int pageHeight = pdfPageOpenPage.getPageHeight(this.mCurrentDpi);
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageHeight;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.getPageSize()", replaceWith = @ReplaceWith(expression = "page.getPageSize()", imports = {}))
    public final Size getPageSize(PdfDocument pdfDocument, int index) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(index);
        try {
            Size pageSize = pdfPageOpenPage.getPageSize(this.mCurrentDpi);
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageSize;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.renderPage(surface, startX, startY, drawSizeX, drawSizeY)", replaceWith = @ReplaceWith(expression = "page.renderPage(surface, startX, startY, drawSizeX, drawSizeY)", imports = {}))
    public final void renderPage(PdfDocument pdfDocument, Surface surface, int pageIndex, int startX, int startY, int drawSizeX, int drawSizeY, boolean renderAnnot) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfPageOpenPage.renderPage(surface, startX, startY, drawSizeX, drawSizeY, false);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(pdfPageOpenPage, null);
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.renderPageBitmap(bitmap, startX, startY, drawSizeX, drawSizeY)", replaceWith = @ReplaceWith(expression = "page.renderPageBitmap(bitmap, startX, startY, drawSizeX, drawSizeY)", imports = {}))
    public final void renderPageBitmap(PdfDocument pdfDocument, Bitmap bitmap, int pageIndex, int startX, int startY, int drawSizeX, int drawSizeY, boolean renderAnnot) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            pdfPageOpenPage.renderPageBitmap(bitmap, startX, startY, drawSizeX, drawSizeY, (64 & 32) != 0 ? false : renderAnnot, (64 & 64) != 0 ? false : false);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(pdfPageOpenPage, null);
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.getPageLinks()", replaceWith = @ReplaceWith(expression = "page.getPageLinks()", imports = {}))
    public final List<PdfDocument.Link> getPageLinks(PdfDocument pdfDocument, int pageIndex) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            List<PdfDocument.Link> pageLinks = pdfPageOpenPage.getPageLinks();
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pageLinks;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.mapPageCoordsToDevice(startX, startY, sizeX, sizeY, rotate, pageX, pageY)", replaceWith = @ReplaceWith(expression = "page.mapPageCoordsToDevice(startX, startY, sizeX, sizeY, rotate, pageX, pageY)", imports = {}))
    public final Point mapPageCoordsToDevice(PdfDocument pdfDocument, int pageIndex, int startX, int startY, int sizeX, int sizeY, int rotate, double pageX, double pageY) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            Point pointMapPageCoordsToDevice = pdfPageOpenPage.mapPageCoordsToDevice(startX, startY, sizeX, sizeY, rotate, pageX, pageY);
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return pointMapPageCoordsToDevice;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use PdfPage.mapRectToDevice(startX, startY, sizeX, sizeY, rotate, coords)", replaceWith = @ReplaceWith(expression = "page.mapRectToDevice(startX, startY, sizeX, sizeY, rotate, coords)", imports = {}))
    public final Rect mapRectToDevice(PdfDocument pdfDocument, int pageIndex, int startX, int startY, int sizeX, int sizeY, int rotate, RectF coords) {
        Intrinsics.checkNotNullParameter(pdfDocument, "pdfDocument");
        Intrinsics.checkNotNullParameter(coords, "coords");
        PdfPage pdfPageOpenPage = pdfDocument.openPage(pageIndex);
        try {
            Rect rectMapRectToDevice = pdfPageOpenPage.mapRectToDevice(startX, startY, sizeX, sizeY, rotate, coords);
            CloseableKt.closeFinally(pdfPageOpenPage, null);
            return rectMapRectToDevice;
        } finally {
        }
    }

    /* compiled from: PdfiumCore.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\bR\u0011\u0010\t\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/legere/pdfiumandroid/PdfiumCore$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "isReady", "Lio/legere/pdfiumandroid/util/InitLock;", "()Lio/legere/pdfiumandroid/util/InitLock;", SentryStackFrame.JsonKeys.LOCK, "getLock", "()Ljava/lang/Object;", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getLock() {
            return PdfiumCore.lock;
        }

        public final InitLock isReady() {
            return PdfiumCore.isReady;
        }
    }

    static {
        String name = PdfiumCore.class.getName();
        TAG = name;
        lock = new Object();
        isReady = new InitLock();
        Log.d(name, Session.JsonKeys.INIT);
        new Thread(new Runnable() { // from class: io.legere.pdfiumandroid.PdfiumCore$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PdfiumCore._init_$lambda$28();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$28() {
        String str = TAG;
        Log.d(str, "init thread start");
        synchronized (lock) {
            Log.d(str, "init in lock");
            try {
                System.loadLibrary("absl.cr");
                System.loadLibrary("c++_chrome.cr");
                System.loadLibrary("chrome_zlib.cr");
                System.loadLibrary("icuuc.cr");
                System.loadLibrary("partition_alloc.cr");
                System.loadLibrary("pdfium.cr");
                System.loadLibrary("pdfiumandroid");
                isReady.markReady();
            } catch (UnsatisfiedLinkError e) {
                Logger logger = Logger.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                logger.e(TAG2, e, "Native libraries failed to load");
            }
            Log.d(TAG, "init in lock");
            Unit unit = Unit.INSTANCE;
        }
    }
}
