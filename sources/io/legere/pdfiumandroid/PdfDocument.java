package io.legere.pdfiumandroid;

import android.graphics.RectF;
import android.os.ParcelFileDescriptor;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.oblador.keychain.KeychainModule;
import io.legere.pdfiumandroid.util.ConfigKt;
import io.sentry.protocol.MetricSummary;
import io.sentry.protocol.ViewHierarchyNode;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdfDocument.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\b\u0018\u0000 G2\u00020\u0001:\u0005FGHIJB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\rJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eJ\u0011\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0003H\u0082 J\u0019\u0010\"\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0003H\u0082 J\u0011\u0010$\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0003H\u0082 J\u0019\u0010&\u001a\u00020%2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010'\u001a\u00020%H\u0082 J\"\u0010(\u001a\u0004\u0018\u00010\u00032\u0006\u0010!\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0003H\u0082 ¢\u0006\u0002\u0010)J\u0011\u0010*\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u0003H\u0082 J\u0011\u0010+\u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0003H\u0082 J \u0010,\u001a\u0004\u0018\u00010\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0003H\u0082 ¢\u0006\u0002\u0010-J\u0019\u0010.\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\rH\u0082 J!\u00100\u001a\u0002012\u0006\u0010!\u001a\u00020\u00032\u0006\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\rH\u0082 J\u0019\u00104\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u00105\u001a\u00020\u0003H\u0082 J!\u00106\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00032\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\rH\u0082 J\u000e\u0010:\u001a\u00020;2\u0006\u0010/\u001a\u00020\rJ\u001c\u0010<\u001a\b\u0012\u0004\u0012\u00020;0\u001e2\u0006\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\rJ\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020;H\u0007J\u001c\u0010@\u001a\b\u0012\u0004\u0012\u00020>0\u001e2\u0006\u00102\u001a\u00020\r2\u0006\u00103\u001a\u00020\rJ&\u0010A\u001a\u00020\u00172\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u001f0C2\u0006\u0010#\u001a\u00020\u00032\u0006\u0010D\u001a\u00020\u0003H\u0002J\u0018\u0010E\u001a\u00020\u00062\u0006\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020\rR\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lio/legere/pdfiumandroid/PdfDocument;", "Ljava/io/Closeable;", "mNativeDocPtr", "", "(J)V", "<set-?>", "", "isClosed", "()Z", "getMNativeDocPtr", "()J", "pageMap", "", "", "Lio/legere/pdfiumandroid/PdfDocument$PageCount;", "parcelFileDescriptor", "Landroid/os/ParcelFileDescriptor;", "getParcelFileDescriptor", "()Landroid/os/ParcelFileDescriptor;", "setParcelFileDescriptor", "(Landroid/os/ParcelFileDescriptor;)V", "textPageMap", "close", "", "getDocumentMeta", "Lio/legere/pdfiumandroid/PdfDocument$Meta;", "getPageCharCounts", "", "getPageCount", "getTableOfContents", "", "Lio/legere/pdfiumandroid/PdfDocument$Bookmark;", "nativeCloseDocument", "docPtr", "nativeGetBookmarkDestIndex", "bookmarkPtr", "nativeGetBookmarkTitle", "", "nativeGetDocumentMetaText", "tag", "nativeGetFirstChildBookmark", "(JLjava/lang/Long;)Ljava/lang/Long;", "nativeGetPageCharCounts", "nativeGetPageCount", "nativeGetSiblingBookmark", "(JJ)Ljava/lang/Long;", "nativeLoadPage", "pageIndex", "nativeLoadPages", "", "fromIndex", "toIndex", "nativeLoadTextPage", "pagePtr", "nativeSaveAsCopy", "callback", "Lio/legere/pdfiumandroid/PdfWriteCallback;", "flags", "openPage", "Lio/legere/pdfiumandroid/PdfPage;", "openPages", "openTextPage", "Lio/legere/pdfiumandroid/PdfTextPage;", "page", "openTextPages", "recursiveGetBookmark", "tree", "", "level", "saveAsCopy", "Bookmark", "Companion", "Link", "Meta", "PageCount", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfDocument implements Closeable {
    public static final int FPDF_INCREMENTAL = 1;
    public static final int FPDF_NO_INCREMENTAL = 2;
    public static final int FPDF_REMOVE_SECURITY = 3;
    private boolean isClosed;
    private final long mNativeDocPtr;
    private ParcelFileDescriptor parcelFileDescriptor;
    private static final String TAG = PdfDocument.class.getName();
    private final Map<Integer, PageCount> pageMap = new LinkedHashMap();
    private final Map<Integer, PageCount> textPageMap = new LinkedHashMap();

    private final native void nativeCloseDocument(long docPtr);

    private final native long nativeGetBookmarkDestIndex(long docPtr, long bookmarkPtr);

    private final native String nativeGetBookmarkTitle(long bookmarkPtr);

    private final native String nativeGetDocumentMetaText(long docPtr, String tag);

    private final native Long nativeGetFirstChildBookmark(long docPtr, Long bookmarkPtr);

    private final native int[] nativeGetPageCharCounts(long docPtr);

    private final native int nativeGetPageCount(long docPtr);

    private final native Long nativeGetSiblingBookmark(long docPtr, long bookmarkPtr);

    private final native long nativeLoadPage(long docPtr, int pageIndex);

    private final native long[] nativeLoadPages(long docPtr, int fromIndex, int toIndex);

    private final native long nativeLoadTextPage(long docPtr, long pagePtr);

    private final native boolean nativeSaveAsCopy(long docPtr, PdfWriteCallback callback, int flags);

    public PdfDocument(long j) {
        this.mNativeDocPtr = j;
    }

    public final long getMNativeDocPtr() {
        return this.mNativeDocPtr;
    }

    /* renamed from: isClosed, reason: from getter */
    public final boolean getIsClosed() {
        return this.isClosed;
    }

    public final ParcelFileDescriptor getParcelFileDescriptor() {
        return this.parcelFileDescriptor;
    }

    public final void setParcelFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        this.parcelFileDescriptor = parcelFileDescriptor;
    }

    public final int getPageCount() {
        int iNativeGetPageCount;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return 0;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            iNativeGetPageCount = nativeGetPageCount(this.mNativeDocPtr);
        }
        return iNativeGetPageCount;
    }

    public final int[] getPageCharCounts() {
        int[] iArrNativeGetPageCharCounts;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return new int[0];
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            iArrNativeGetPageCharCounts = nativeGetPageCharCounts(this.mNativeDocPtr);
        }
        return iArrNativeGetPageCharCounts;
    }

    public final PdfPage openPage(int pageIndex) {
        PageCount pageCount;
        if (!(!this.isClosed)) {
            throw new IllegalStateException("Already closed".toString());
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            if (this.pageMap.containsKey(Integer.valueOf(pageIndex)) && (pageCount = this.pageMap.get(Integer.valueOf(pageIndex))) != null) {
                pageCount.setCount(pageCount.getCount() + 1);
                return new PdfPage(this, pageIndex, pageCount.getPagePtr(), this.pageMap);
            }
            long jNativeLoadPage = nativeLoadPage(this.mNativeDocPtr, pageIndex);
            this.pageMap.put(Integer.valueOf(pageIndex), new PageCount(jNativeLoadPage, 1));
            return new PdfPage(this, pageIndex, jNativeLoadPage, this.pageMap);
        }
    }

    public final List<PdfPage> openPages(int fromIndex, int toIndex) {
        ArrayList arrayList;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return CollectionsKt.emptyList();
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            long[] jArrNativeLoadPages = nativeLoadPages(this.mNativeDocPtr, fromIndex, toIndex);
            for (long j : jArrNativeLoadPages) {
                if (fromIndex > toIndex) {
                    break;
                }
                fromIndex++;
            }
            ArrayList arrayList2 = new ArrayList(jArrNativeLoadPages.length);
            for (long j2 : jArrNativeLoadPages) {
                arrayList2.add(new PdfPage(this, fromIndex, j2, this.pageMap));
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public final Meta getDocumentMeta() {
        Meta meta;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return new Meta();
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            meta = new Meta();
            meta.setTitle(nativeGetDocumentMetaText(this.mNativeDocPtr, "Title"));
            meta.setAuthor(nativeGetDocumentMetaText(this.mNativeDocPtr, "Author"));
            meta.setSubject(nativeGetDocumentMetaText(this.mNativeDocPtr, "Subject"));
            meta.setKeywords(nativeGetDocumentMetaText(this.mNativeDocPtr, "Keywords"));
            meta.setCreator(nativeGetDocumentMetaText(this.mNativeDocPtr, "Creator"));
            meta.setProducer(nativeGetDocumentMetaText(this.mNativeDocPtr, "Producer"));
            meta.setCreationDate(nativeGetDocumentMetaText(this.mNativeDocPtr, "CreationDate"));
            meta.setModDate(nativeGetDocumentMetaText(this.mNativeDocPtr, "ModDate"));
        }
        return meta;
    }

    private final void recursiveGetBookmark(List<Bookmark> tree, long bookmarkPtr, long level) {
        long j;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return;
        }
        Bookmark bookmark = new Bookmark();
        bookmark.setMNativePtr(bookmarkPtr);
        bookmark.setTitle(nativeGetBookmarkTitle(bookmarkPtr));
        bookmark.setPageIdx(nativeGetBookmarkDestIndex(this.mNativeDocPtr, bookmarkPtr));
        tree.add(bookmark);
        Long lNativeGetFirstChildBookmark = nativeGetFirstChildBookmark(this.mNativeDocPtr, Long.valueOf(bookmarkPtr));
        if (lNativeGetFirstChildBookmark == null || level >= 16) {
            j = level;
        } else {
            recursiveGetBookmark(bookmark.getChildren(), lNativeGetFirstChildBookmark.longValue(), level);
            j = level + 1;
        }
        Long lNativeGetSiblingBookmark = nativeGetSiblingBookmark(this.mNativeDocPtr, bookmarkPtr);
        if (lNativeGetSiblingBookmark == null || j >= 16) {
            return;
        }
        recursiveGetBookmark(tree, lNativeGetSiblingBookmark.longValue(), j);
    }

    public final List<Bookmark> getTableOfContents() {
        ArrayList arrayList;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return CollectionsKt.emptyList();
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            arrayList = new ArrayList();
            Long lNativeGetFirstChildBookmark = nativeGetFirstChildBookmark(this.mNativeDocPtr, null);
            if (lNativeGetFirstChildBookmark != null) {
                recursiveGetBookmark(arrayList, lNativeGetFirstChildBookmark.longValue(), 1L);
            }
        }
        return arrayList;
    }

    @Deprecated(message = "Use PdfPage.openTextPage instead", replaceWith = @ReplaceWith(expression = "page.openTextPage()", imports = {}))
    public final PdfTextPage openTextPage(PdfPage page) {
        PageCount pageCount;
        Intrinsics.checkNotNullParameter(page, "page");
        if (!(!this.isClosed)) {
            throw new IllegalStateException("Already closed".toString());
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            if (this.textPageMap.containsKey(Integer.valueOf(page.getPageIndex())) && (pageCount = this.textPageMap.get(Integer.valueOf(page.getPageIndex()))) != null) {
                pageCount.setCount(pageCount.getCount() + 1);
                return new PdfTextPage(this, page.getPageIndex(), pageCount.getPagePtr(), this.textPageMap);
            }
            long jNativeLoadTextPage = nativeLoadTextPage(this.mNativeDocPtr, page.getPagePtr());
            this.textPageMap.put(Integer.valueOf(page.getPageIndex()), new PageCount(jNativeLoadTextPage, 1));
            return new PdfTextPage(this, page.getPageIndex(), jNativeLoadTextPage, this.textPageMap);
        }
    }

    public final List<PdfTextPage> openTextPages(int fromIndex, int toIndex) {
        ArrayList arrayList;
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return CollectionsKt.emptyList();
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            long[] jArrNativeLoadPages = nativeLoadPages(this.mNativeDocPtr, fromIndex, toIndex);
            ArrayList arrayList2 = new ArrayList(jArrNativeLoadPages.length);
            int length = jArrNativeLoadPages.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                arrayList2.add(new PdfTextPage(this, fromIndex + i2, jArrNativeLoadPages[i], this.textPageMap));
                i++;
                i2++;
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public static /* synthetic */ boolean saveAsCopy$default(PdfDocument pdfDocument, PdfWriteCallback pdfWriteCallback, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 2;
        }
        return pdfDocument.saveAsCopy(pdfWriteCallback, i);
    }

    public final boolean saveAsCopy(PdfWriteCallback callback, int flags) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return false;
        }
        return nativeSaveAsCopy(this.mNativeDocPtr, callback, flags);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (ConfigKt.handleAlreadyClosed(this.isClosed)) {
            return;
        }
        Logger logger = Logger.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        logger.d(TAG2, "PdfDocument.close");
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            this.isClosed = true;
            nativeCloseDocument(this.mNativeDocPtr);
            ParcelFileDescriptor parcelFileDescriptor = this.parcelFileDescriptor;
            if (parcelFileDescriptor != null) {
                parcelFileDescriptor.close();
            }
            this.parcelFileDescriptor = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: PdfDocument.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lio/legere/pdfiumandroid/PdfDocument$Meta;", "", "()V", "author", "", "getAuthor", "()Ljava/lang/String;", "setAuthor", "(Ljava/lang/String;)V", "creationDate", "getCreationDate", "setCreationDate", "creator", "getCreator", "setCreator", "keywords", "getKeywords", "setKeywords", "modDate", "getModDate", "setModDate", "producer", "getProducer", "setProducer", "subject", "getSubject", "setSubject", KeychainModule.AuthPromptOptions.TITLE, "getTitle", "setTitle", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Meta {
        private String author;
        private String creationDate;
        private String creator;
        private String keywords;
        private String modDate;
        private String producer;
        private String subject;
        private String title;

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final String getAuthor() {
            return this.author;
        }

        public final void setAuthor(String str) {
            this.author = str;
        }

        public final String getSubject() {
            return this.subject;
        }

        public final void setSubject(String str) {
            this.subject = str;
        }

        public final String getKeywords() {
            return this.keywords;
        }

        public final void setKeywords(String str) {
            this.keywords = str;
        }

        public final String getCreator() {
            return this.creator;
        }

        public final void setCreator(String str) {
            this.creator = str;
        }

        public final String getProducer() {
            return this.producer;
        }

        public final void setProducer(String str) {
            this.producer = str;
        }

        public final String getCreationDate() {
            return this.creationDate;
        }

        public final void setCreationDate(String str) {
            this.creationDate = str;
        }

        public final String getModDate() {
            return this.modDate;
        }

        public final void setModDate(String str) {
            this.modDate = str;
        }
    }

    /* compiled from: PdfDocument.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/legere/pdfiumandroid/PdfDocument$Bookmark;", "", "()V", ViewHierarchyNode.JsonKeys.CHILDREN, "", "getChildren", "()Ljava/util/List;", "mNativePtr", "", "getMNativePtr", "()J", "setMNativePtr", "(J)V", "pageIdx", "getPageIdx", "setPageIdx", KeychainModule.AuthPromptOptions.TITLE, "", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Bookmark {
        private final List<Bookmark> children = new ArrayList();
        private long mNativePtr;
        private long pageIdx;
        private String title;

        public final List<Bookmark> getChildren() {
            return this.children;
        }

        public final String getTitle() {
            return this.title;
        }

        public final void setTitle(String str) {
            this.title = str;
        }

        public final long getPageIdx() {
            return this.pageIdx;
        }

        public final void setPageIdx(long j) {
            this.pageIdx = j;
        }

        public final long getMNativePtr() {
            return this.mNativePtr;
        }

        public final void setMNativePtr(long j) {
            this.mNativePtr = j;
        }
    }

    /* compiled from: PdfDocument.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/legere/pdfiumandroid/PdfDocument$Link;", "", "bounds", "Landroid/graphics/RectF;", "destPageIdx", "", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "", "(Landroid/graphics/RectF;Ljava/lang/Integer;Ljava/lang/String;)V", "getBounds", "()Landroid/graphics/RectF;", "getDestPageIdx", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUri", "()Ljava/lang/String;", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Link {
        private final RectF bounds;
        private final Integer destPageIdx;
        private final String uri;

        public Link(RectF bounds, Integer num, String str) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            this.bounds = bounds;
            this.destPageIdx = num;
            this.uri = str;
        }

        public final RectF getBounds() {
            return this.bounds;
        }

        public final Integer getDestPageIdx() {
            return this.destPageIdx;
        }

        public final String getUri() {
            return this.uri;
        }
    }

    /* compiled from: PdfDocument.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lio/legere/pdfiumandroid/PdfDocument$PageCount;", "", "pagePtr", "", MetricSummary.JsonKeys.COUNT, "", "(JI)V", "getCount", "()I", "setCount", "(I)V", "getPagePtr", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class PageCount {
        private int count;
        private final long pagePtr;

        public static /* synthetic */ PageCount copy$default(PageCount pageCount, long j, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j = pageCount.pagePtr;
            }
            if ((i2 & 2) != 0) {
                i = pageCount.count;
            }
            return pageCount.copy(j, i);
        }

        /* renamed from: component1, reason: from getter */
        public final long getPagePtr() {
            return this.pagePtr;
        }

        /* renamed from: component2, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        public final PageCount copy(long pagePtr, int count) {
            return new PageCount(pagePtr, count);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PageCount)) {
                return false;
            }
            PageCount pageCount = (PageCount) other;
            return this.pagePtr == pageCount.pagePtr && this.count == pageCount.count;
        }

        public int hashCode() {
            return (Long.hashCode(this.pagePtr) * 31) + Integer.hashCode(this.count);
        }

        public String toString() {
            return "PageCount(pagePtr=" + this.pagePtr + ", count=" + this.count + ')';
        }

        public PageCount(long j, int i) {
            this.pagePtr = j;
            this.count = i;
        }

        public final int getCount() {
            return this.count;
        }

        public final long getPagePtr() {
            return this.pagePtr;
        }

        public final void setCount(int i) {
            this.count = i;
        }
    }
}
