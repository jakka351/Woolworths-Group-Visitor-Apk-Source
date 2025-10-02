package n.o.t.i.f.e.e;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import app.notifee.core.Logger;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.Map;
import org.maplibre.android.BuildConfig;

/* loaded from: classes2.dex */
public class n {
    public static volatile Map<String, Integer> a;

    public class a extends BaseBitmapDataSubscriber {
        public final /* synthetic */ TaskCompletionSource a;
        public final /* synthetic */ String b;

        public a(TaskCompletionSource taskCompletionSource, String str) {
            this.a = taskCompletionSource;
            this.b = str;
        }

        @Override // com.facebook.datasource.BaseDataSubscriber
        public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            Logger.e("ResourceUtils", "Failed to load an image: " + this.b, dataSource.getFailureCause());
            this.a.setResult(null);
        }

        @Override // com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
        public void onNewResultImpl(Bitmap bitmap) {
            this.a.setResult(bitmap);
        }
    }

    public static Map<String, Integer> a() {
        if (a == null) {
            synchronized (n.class) {
                if (a == null) {
                    a = new HashMap();
                }
            }
        }
        return a;
    }

    public static Uri b(String str) {
        int iA = a(str, BuildConfig.FLAVOR);
        return iA > 0 ? new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(iA)).build() : Uri.EMPTY;
    }

    public static Uri c(String str) {
        Context context = e.a;
        if (str == null) {
            return null;
        }
        if (str.contains("://")) {
            return Uri.parse(str);
        }
        if (str.equalsIgnoreCase(Constants.COLLATION_DEFAULT)) {
            return RingtoneManager.getDefaultUri(2);
        }
        int iA = a(str, "raw");
        if (iA == 0 && str.contains(".")) {
            iA = a(str.substring(0, str.lastIndexOf(46)), "raw");
        }
        if (iA == 0) {
            return null;
        }
        return Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + str);
    }

    public static Task<Bitmap> a(String str) throws NumberFormatException {
        Uri uriB;
        String string;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task<Bitmap> task = taskCompletionSource.getTask();
        if (!str.contains("/")) {
            int iA = a(str, "mipmap");
            if (iA == 0) {
                iA = a(str, BuildConfig.FLAVOR);
            }
            if (iA == 0) {
                string = null;
            } else if (iA > 0) {
                string = new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(iA)).build().toString();
            } else {
                string = Uri.EMPTY.toString();
            }
            if (string == null) {
                taskCompletionSource.setResult(null);
                return task;
            }
            try {
                uriB = Uri.parse(string);
                if (uriB.getScheme() == null) {
                    uriB = b(string);
                }
            } catch (Exception unused) {
                uriB = b(string);
            }
        } else {
            try {
                Uri uriB2 = Uri.parse(str);
                if (uriB2.getScheme() == null) {
                    uriB2 = b(str);
                }
                uriB = uriB2;
            } catch (Exception unused2) {
                uriB = b(str);
            }
        }
        ImageRequest imageRequestBuild = ImageRequestBuilder.newBuilderWithSource(uriB).build();
        if (!Fresco.hasBeenInitialized()) {
            Logger.w("ResourceUtils", "Fresco initializing natively by Notifee");
            Fresco.initialize(e.a);
        }
        Fresco.getImagePipeline().fetchDecodedImage(imageRequestBuild, e.a).subscribe(new a(taskCompletionSource, str), CallerThreadExecutor.getInstance());
        return task;
    }

    public static int a(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        String strReplace = str.toLowerCase().replace("-", "_");
        String str3 = strReplace + "_" + str2;
        synchronized (n.class) {
            if (a().containsKey(str3)) {
                return a().get(str3).intValue();
            }
            Context context = e.a;
            int identifier = context.getResources().getIdentifier(strReplace, str2, context.getPackageName());
            a().put(str3, Integer.valueOf(identifier));
            return identifier;
        }
    }
}
