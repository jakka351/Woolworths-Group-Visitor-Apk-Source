package okio;

import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import okio.internal.FixedLengthSource;
import okio.internal.ZipEntry;
import okio.internal.ZipFilesKt;

/* compiled from: ZipFileSystem.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 '2\u00020\u0001:\u0001'B5\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J \u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001d2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u000fH\u0002J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001d2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0015\u001a\u00020\u0003H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\r\u001a\u00020\u0003H\u0016J \u0010$\u001a\u00020#2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010%\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020&2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lokio/ZipFileSystem;", "Lokio/FileSystem;", "zipPath", "Lokio/Path;", "fileSystem", "entries", "", "Lokio/internal/ZipEntry;", "comment", "", "(Lokio/Path;Lokio/FileSystem;Ljava/util/Map;Ljava/lang/String;)V", "appendingSink", "Lokio/Sink;", "file", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "canonicalizeInternal", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "throwOnFailure", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ZipFileSystem extends FileSystem {
    private static final Companion Companion = new Companion(null);
    private static final Path ROOT = Path.Companion.get$default(Path.INSTANCE, "/", false, 1, (Object) null);
    private final String comment;
    private final Map<Path, ZipEntry> entries;
    private final FileSystem fileSystem;
    private final Path zipPath;

    public ZipFileSystem(Path zipPath, FileSystem fileSystem, Map<Path, ZipEntry> entries, String str) {
        Intrinsics.checkNotNullParameter(zipPath, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(entries, "entries");
        this.zipPath = zipPath;
        this.fileSystem = fileSystem;
        this.entries = entries;
        this.comment = str;
    }

    @Override // okio.FileSystem
    public Path canonicalize(Path path) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(path, "path");
        Path pathCanonicalizeInternal = canonicalizeInternal(path);
        if (this.entries.containsKey(pathCanonicalizeInternal)) {
            return pathCanonicalizeInternal;
        }
        throw new FileNotFoundException(String.valueOf(path));
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0087 A[Catch: all -> 0x0093, TRY_LEAVE, TryCatch #1 {all -> 0x0093, blocks: (B:13:0x0056, B:29:0x0087, B:35:0x0092, B:26:0x0080, B:14:0x0067, B:23:0x007b), top: B:49:0x0056, inners: #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0092 A[Catch: all -> 0x0093, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0093, blocks: (B:13:0x0056, B:29:0x0087, B:35:0x0092, B:26:0x0080, B:14:0x0067, B:23:0x007b), top: B:49:0x0056, inners: #3, #5 }] */
    @Override // okio.FileSystem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public okio.FileMetadata metadataOrNull(okio.Path r14) throws java.lang.Throwable {
        /*
            r13 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            okio.Path r14 = r13.canonicalizeInternal(r14)
            java.util.Map<okio.Path, okio.internal.ZipEntry> r0 = r13.entries
            java.lang.Object r14 = r0.get(r14)
            okio.internal.ZipEntry r14 = (okio.internal.ZipEntry) r14
            r0 = 0
            if (r14 != 0) goto L15
            return r0
        L15:
            okio.FileMetadata r12 = new okio.FileMetadata
            boolean r1 = r14.getIsDirectory()
            r2 = r1 ^ 1
            boolean r3 = r14.getIsDirectory()
            r4 = 0
            boolean r1 = r14.getIsDirectory()
            if (r1 == 0) goto L2a
            r5 = r0
            goto L33
        L2a:
            long r5 = r14.getSize()
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            r5 = r1
        L33:
            r6 = 0
            java.lang.Long r7 = r14.getLastModifiedAtMillis()
            r8 = 0
            r9 = 0
            r10 = 128(0x80, float:1.794E-43)
            r11 = 0
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            long r1 = r14.getOffset()
            r3 = -1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L4c
            return r12
        L4c:
            okio.FileSystem r1 = r13.fileSystem
            okio.Path r2 = r13.zipPath
            okio.FileHandle r1 = r1.openReadOnly(r2)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            okio.FileHandle r2 = (okio.FileHandle) r2     // Catch: java.lang.Throwable -> L93
            long r3 = r14.getOffset()     // Catch: java.lang.Throwable -> L93
            okio.Source r14 = r2.source(r3)     // Catch: java.lang.Throwable -> L93
            okio.BufferedSource r14 = okio.Okio.buffer(r14)     // Catch: java.lang.Throwable -> L93
            java.io.Closeable r14 = (java.io.Closeable) r14     // Catch: java.lang.Throwable -> L93
            r2 = r14
            okio.BufferedSource r2 = (okio.BufferedSource) r2     // Catch: java.lang.Throwable -> L78
            okio.FileMetadata r2 = okio.internal.ZipFilesKt.readLocalHeader(r2, r12)     // Catch: java.lang.Throwable -> L78
            if (r14 == 0) goto L76
            r14.close()     // Catch: java.lang.Throwable -> L74
            goto L76
        L74:
            r14 = move-exception
            goto L85
        L76:
            r14 = r0
            goto L85
        L78:
            r2 = move-exception
            if (r14 == 0) goto L83
            r14.close()     // Catch: java.lang.Throwable -> L7f
            goto L83
        L7f:
            r14 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r2, r14)     // Catch: java.lang.Throwable -> L93
        L83:
            r14 = r2
            r2 = r0
        L85:
            if (r14 != 0) goto L92
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Throwable -> L93
            if (r1 == 0) goto La0
            r1.close()     // Catch: java.lang.Throwable -> L90
            goto La0
        L90:
            r0 = move-exception
            goto La0
        L92:
            throw r14     // Catch: java.lang.Throwable -> L93
        L93:
            r14 = move-exception
            if (r1 == 0) goto L9e
            r1.close()     // Catch: java.lang.Throwable -> L9a
            goto L9e
        L9a:
            r1 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r14, r1)
        L9e:
            r2 = r0
            r0 = r14
        La0:
            if (r0 != 0) goto La6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            return r2
        La6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ZipFileSystem.metadataOrNull(okio.Path):okio.FileMetadata");
    }

    @Override // okio.FileSystem
    public FileHandle openReadOnly(Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    @Override // okio.FileSystem
    public FileHandle openReadWrite(Path file, boolean mustCreate, boolean mustExist) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("zip entries are not writable");
    }

    @Override // okio.FileSystem
    public List<Path> list(Path dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        List<Path> list = list(dir, true);
        Intrinsics.checkNotNull(list);
        return list;
    }

    @Override // okio.FileSystem
    public List<Path> listOrNull(Path dir) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        return list(dir, false);
    }

    private final List<Path> list(Path dir, boolean throwOnFailure) throws IOException {
        ZipEntry zipEntry = this.entries.get(canonicalizeInternal(dir));
        if (zipEntry != null) {
            return CollectionsKt.toList(zipEntry.getChildren());
        }
        if (throwOnFailure) {
            throw new IOException("not a directory: " + dir);
        }
        return null;
    }

    @Override // okio.FileSystem
    public Source source(Path file) throws Throwable {
        BufferedSource bufferedSourceBuffer;
        Intrinsics.checkNotNullParameter(file, "file");
        ZipEntry zipEntry = this.entries.get(canonicalizeInternal(file));
        if (zipEntry == null) {
            throw new FileNotFoundException("no such file: " + file);
        }
        FileHandle fileHandleOpenReadOnly = this.fileSystem.openReadOnly(this.zipPath);
        Throwable th = null;
        try {
            bufferedSourceBuffer = Okio.buffer(fileHandleOpenReadOnly.source(zipEntry.getOffset()));
            if (fileHandleOpenReadOnly != null) {
                try {
                    fileHandleOpenReadOnly.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (fileHandleOpenReadOnly != null) {
                try {
                    fileHandleOpenReadOnly.close();
                } catch (Throwable th4) {
                    ExceptionsKt.addSuppressed(th3, th4);
                }
            }
            bufferedSourceBuffer = null;
            th = th3;
        }
        if (th != null) {
            throw th;
        }
        Intrinsics.checkNotNull(bufferedSourceBuffer);
        ZipFilesKt.skipLocalHeader(bufferedSourceBuffer);
        if (zipEntry.getCompressionMethod() == 0) {
            return new FixedLengthSource(bufferedSourceBuffer, zipEntry.getSize(), true);
        }
        return new FixedLengthSource(new InflaterSource(new FixedLengthSource(bufferedSourceBuffer, zipEntry.getCompressedSize(), true), new Inflater(true)), zipEntry.getSize(), false);
    }

    @Override // okio.FileSystem
    public Sink sink(Path file, boolean mustCreate) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public Sink appendingSink(Path file, boolean mustExist) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void createDirectory(Path dir, boolean mustCreate) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void atomicMove(Path source, Path target) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void delete(Path path, boolean mustExist) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        throw new IOException("zip file systems are read-only");
    }

    @Override // okio.FileSystem
    public void createSymlink(Path source, Path target) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(target, "target");
        throw new IOException("zip file systems are read-only");
    }

    /* compiled from: ZipFileSystem.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lokio/ZipFileSystem$Companion;", "", "()V", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Path getROOT() {
            return ZipFileSystem.ROOT;
        }
    }
}
