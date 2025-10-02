package com.site360new;

import android.content.Context;
import android.util.Log;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import io.sentry.protocol.SentryStackFrame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.style.layers.Property;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000e\u001a\u00020\nJ\u0006\u0010\u000f\u001a\u00020\nJ@\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0016\u001a\u00020\n2\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0011J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\nH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/site360new/Logger;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dateFormatter", "Ljava/text/SimpleDateFormat;", "logFile", "Ljava/io/File;", "logFileName", "", "logQueue", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getLogFilePath", "getLogs", "log", "", "message", "level", "Lcom/site360new/LogLevel;", "category", SentryStackFrame.JsonKeys.FUNCTION, "file", Property.SYMBOL_PLACEMENT_LINE, "", "resetLogs", "setupLogFile", "writeToFile", "entry", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Logger {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile Logger INSTANCE = null;
    private static final String TAG = "Logger";
    private final Context context;
    private final SimpleDateFormat dateFormatter;
    private File logFile;
    private final String logFileName;
    private final ExecutorService logQueue;

    /* compiled from: Logger.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogLevel.values().length];
            try {
                iArr[LogLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LogLevel.INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LogLevel.WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[LogLevel.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ Logger(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private Logger(Context context) {
        this.context = context;
        this.logFileName = "site360_debug.log";
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        this.logQueue = Executors.newSingleThreadExecutor();
        setupLogFile();
    }

    /* compiled from: Logger.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/site360new/Logger$Companion;", "", "()V", "INSTANCE", "Lcom/site360new/Logger;", "TAG", "", "getInstance", "context", "Landroid/content/Context;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Logger getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Logger logger = Logger.INSTANCE;
            if (logger == null) {
                synchronized (this) {
                    logger = Logger.INSTANCE;
                    if (logger == null) {
                        Context applicationContext = context.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "context.applicationContext");
                        logger = new Logger(applicationContext, null);
                        Companion companion = Logger.INSTANCE;
                        Logger.INSTANCE = logger;
                    }
                }
            }
            return logger;
        }
    }

    private final void setupLogFile() {
        this.logFile = new File(this.context.getExternalFilesDir(null), this.logFileName);
    }

    public static /* synthetic */ void log$default(Logger logger, String str, LogLevel logLevel, String str2, String str3, String str4, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            logLevel = LogLevel.INFO;
        }
        LogLevel logLevel2 = logLevel;
        if ((i2 & 4) != 0) {
            str2 = "General";
        }
        String str5 = str2;
        String str6 = (i2 & 8) != 0 ? "" : str3;
        String str7 = (i2 & 16) != 0 ? "" : str4;
        if ((i2 & 32) != 0) {
            i = 0;
        }
        logger.log(str, logLevel2, str5, str6, str7, i);
    }

    public final void log(String message, LogLevel level, String category, String function, String file, int line) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(category, "category");
        Intrinsics.checkNotNullParameter(function, "function");
        Intrinsics.checkNotNullParameter(file, "file");
        if (this.context.getSharedPreferences("sharedPreferences", 0).getBoolean("logsEnabled", false)) {
            String str = this.dateFormatter.format(new Date());
            final String str2 = (!(file.length() > 0) || line <= 0) ? "[" + str + "] [" + level.getValue() + "] [" + category + "] " + message + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE : "[" + str + "] [" + level.getValue() + "] [" + category + "] [" + new File(file).getName() + ":" + line + " " + function + "] " + message + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            int i = WhenMappings.$EnumSwitchMapping$0[level.ordinal()];
            if (i == 1) {
                Log.d(TAG, "[" + category + "] " + message);
            } else if (i == 2) {
                Log.i(TAG, "[" + category + "] " + message);
            } else if (i == 3) {
                Log.w(TAG, "[" + category + "] " + message);
            } else if (i == 4) {
                Log.e(TAG, "[" + category + "] " + message);
            }
            this.logQueue.execute(new Runnable() { // from class: com.site360new.Logger$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws IOException {
                    Logger.log$lambda$0(this.f$0, str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void log$lambda$0(Logger this$0, String logEntry) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(logEntry, "$logEntry");
        this$0.writeToFile(logEntry);
    }

    private final void writeToFile(String entry) throws IOException {
        try {
            File file = this.logFile;
            if (file == null) {
                return;
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            try {
                fileWriter.write(entry);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileWriter, null);
            } finally {
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to write to log file: " + e.getMessage());
        }
    }

    public final void resetLogs() {
        this.logQueue.execute(new Runnable() { // from class: com.site360new.Logger$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws IOException {
                Logger.resetLogs$lambda$4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resetLogs$lambda$4(Logger this$0) throws IOException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            File file = this$0.logFile;
            if (file != null) {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                this$0.writeToFile("[" + this$0.dateFormatter.format(new Date()) + "] [INFO] [Logger] Log file reset\n");
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to reset log file: " + e.getMessage());
        }
    }

    public final String getLogs() {
        try {
            File file = this.logFile;
            if (file == null) {
                return "";
            }
            String text$default = FilesKt.readText$default(file, null, 1, null);
            return text$default == null ? "" : text$default;
        } catch (Exception e) {
            Log.e(TAG, "Failed to read log file: " + e.getMessage());
            return "";
        }
    }

    public final String getLogFilePath() {
        File file = this.logFile;
        String absolutePath = file != null ? file.getAbsolutePath() : null;
        return absolutePath == null ? "" : absolutePath;
    }
}
