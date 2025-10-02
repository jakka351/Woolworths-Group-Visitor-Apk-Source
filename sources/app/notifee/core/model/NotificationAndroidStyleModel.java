package app.notifee.core.model;

import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.text.HtmlCompat;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.oblador.keychain.KeychainModule;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.l;

/* loaded from: classes.dex */
public class NotificationAndroidStyleModel {
    private static final String TAG = "NotificationAndroidStyle";
    private Bundle mNotificationAndroidStyleBundle;

    private NotificationAndroidStyleModel(Bundle bundle) {
        this.mNotificationAndroidStyleBundle = bundle;
    }

    public static NotificationAndroidStyleModel fromBundle(Bundle bundle) {
        return new NotificationAndroidStyleModel(bundle);
    }

    private Task<NotificationCompat.Style> getBigPictureStyleTask(Executor executor) {
        return Tasks.call(executor, new Callable() { // from class: app.notifee.core.model.NotificationAndroidStyleModel$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m353xb4e8acab();
            }
        });
    }

    private NotificationCompat.BigTextStyle getBigTextStyle() {
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        if (this.mNotificationAndroidStyleBundle.containsKey("text")) {
            bigTextStyle = bigTextStyle.bigText(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("text"), 0));
        }
        if (this.mNotificationAndroidStyleBundle.containsKey(KeychainModule.AuthPromptOptions.TITLE)) {
            bigTextStyle = bigTextStyle.setBigContentTitle(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString(KeychainModule.AuthPromptOptions.TITLE), 0));
        }
        return this.mNotificationAndroidStyleBundle.containsKey("summary") ? bigTextStyle.setSummaryText(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("summary"), 0)) : bigTextStyle;
    }

    private NotificationCompat.InboxStyle getInboxStyle() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        if (this.mNotificationAndroidStyleBundle.containsKey(KeychainModule.AuthPromptOptions.TITLE)) {
            inboxStyle = inboxStyle.setBigContentTitle(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString(KeychainModule.AuthPromptOptions.TITLE), 0));
        }
        if (this.mNotificationAndroidStyleBundle.containsKey("summary")) {
            inboxStyle = inboxStyle.setSummaryText(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString("summary"), 0));
        }
        ArrayList<String> stringArrayList = this.mNotificationAndroidStyleBundle.getStringArrayList("lines");
        for (int i = 0; i < ((ArrayList) Objects.requireNonNull(stringArrayList)).size(); i++) {
            inboxStyle = inboxStyle.addLine(HtmlCompat.fromHtml(stringArrayList.get(i), 0));
        }
        return inboxStyle;
    }

    private Task<NotificationCompat.Style> getMessagingStyleTask(final Executor executor) {
        return Tasks.call(executor, new Callable() { // from class: app.notifee.core.model.NotificationAndroidStyleModel$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m354x25b4cf9e(executor);
            }
        });
    }

    private static Task<Person> getPerson(Executor executor, final Bundle bundle) {
        return Tasks.call(executor, new Callable() { // from class: app.notifee.core.model.NotificationAndroidStyleModel$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NotificationAndroidStyleModel.lambda$getPerson$0(bundle);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ androidx.core.app.Person lambda$getPerson$0(android.os.Bundle r7) throws java.lang.Exception {
        /*
            java.lang.String r0 = "NotificationAndroidStyle"
            androidx.core.app.Person$Builder r1 = new androidx.core.app.Person$Builder
            r1.<init>()
            java.lang.String r2 = "name"
            java.lang.String r2 = r7.getString(r2)
            r1.setName(r2)
            java.lang.String r2 = "id"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L1f
            java.lang.String r2 = r7.getString(r2)
            r1.setKey(r2)
        L1f:
            java.lang.String r2 = "bot"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L2e
            boolean r2 = r7.getBoolean(r2)
            r1.setBot(r2)
        L2e:
            java.lang.String r2 = "important"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L3d
            boolean r2 = r7.getBoolean(r2)
            r1.setImportant(r2)
        L3d:
            java.lang.String r2 = "icon"
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L8f
            java.lang.String r2 = r7.getString(r2)
            java.lang.Object r2 = java.util.Objects.requireNonNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            com.google.android.gms.tasks.Task r3 = n.o.t.i.f.e.e.n.a(r2)     // Catch: java.lang.Exception -> L5e java.util.concurrent.TimeoutException -> L72
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Exception -> L5e java.util.concurrent.TimeoutException -> L72
            r5 = 10
            java.lang.Object r3 = com.google.android.gms.tasks.Tasks.await(r3, r5, r4)     // Catch: java.lang.Exception -> L5e java.util.concurrent.TimeoutException -> L72
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch: java.lang.Exception -> L5e java.util.concurrent.TimeoutException -> L72
            goto L86
        L5e:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "An error occurred whilst trying to retrieve a person icon: "
            r4.<init>(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            app.notifee.core.Logger.e(r0, r2, r3)
            goto L85
        L72:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Timeout occurred whilst trying to retrieve a person icon: "
            r4.<init>(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            app.notifee.core.Logger.e(r0, r2, r3)
        L85:
            r3 = 0
        L86:
            if (r3 == 0) goto L8f
            androidx.core.graphics.drawable.IconCompat r0 = androidx.core.graphics.drawable.IconCompat.createWithAdaptiveBitmap(r3)
            r1.setIcon(r0)
        L8f:
            java.lang.String r0 = "uri"
            boolean r2 = r7.containsKey(r0)
            if (r2 == 0) goto L9f
            java.lang.String r7 = r7.getString(r0)
            r1.setUri(r7)
        L9f:
            androidx.core.app.Person r7 = r1.build()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.model.NotificationAndroidStyleModel.lambda$getPerson$0(android.os.Bundle):androidx.core.app.Person");
    }

    public Task<NotificationCompat.Style> getStyleTask(Executor executor) {
        int iA = l.a(this.mNotificationAndroidStyleBundle.get("type"));
        if (iA == 0) {
            return getBigPictureStyleTask(executor);
        }
        if (iA == 1) {
            return Tasks.forResult(getBigTextStyle());
        }
        if (iA == 2) {
            return Tasks.forResult(getInboxStyle());
        }
        if (iA != 3) {
            return null;
        }
        return getMessagingStyleTask(executor);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* renamed from: lambda$getBigPictureStyleTask$1$app-notifee-core-model-NotificationAndroidStyleModel, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.core.app.NotificationCompat.Style m353xb4e8acab() throws java.lang.Exception {
        /*
            r9 = this;
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = new androidx.core.app.NotificationCompat$BigPictureStyle
            r0.<init>()
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "picture"
            boolean r1 = r1.containsKey(r2)
            r3 = 10
            java.lang.String r5 = "NotificationAndroidStyle"
            r6 = 0
            if (r1 == 0) goto L5a
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            java.lang.Object r1 = java.util.Objects.requireNonNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            com.google.android.gms.tasks.Task r2 = n.o.t.i.f.e.e.n.a(r1)     // Catch: java.lang.Exception -> L2d java.util.concurrent.TimeoutException -> L41
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Exception -> L2d java.util.concurrent.TimeoutException -> L41
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r3, r7)     // Catch: java.lang.Exception -> L2d java.util.concurrent.TimeoutException -> L41
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch: java.lang.Exception -> L2d java.util.concurrent.TimeoutException -> L41
            goto L55
        L2d:
            r2 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "An error occurred whilst trying to retrieve a big picture style image: "
            r7.<init>(r8)
            java.lang.StringBuilder r1 = r7.append(r1)
            java.lang.String r1 = r1.toString()
            app.notifee.core.Logger.e(r5, r1, r2)
            goto L54
        L41:
            r2 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Timeout occurred whilst trying to retrieve a big picture style image: "
            r7.<init>(r8)
            java.lang.StringBuilder r1 = r7.append(r1)
            java.lang.String r1 = r1.toString()
            app.notifee.core.Logger.e(r5, r1, r2)
        L54:
            r2 = r6
        L55:
            if (r2 == 0) goto L5a
            r0.bigPicture(r2)
        L5a:
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "largeIcon"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L70
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            if (r1 != 0) goto L71
            r0.bigLargeIcon(r6)
            goto L71
        L70:
            r1 = r6
        L71:
            if (r1 == 0) goto Lad
            com.google.android.gms.tasks.Task r2 = n.o.t.i.f.e.e.n.a(r1)     // Catch: java.lang.Exception -> L81 java.util.concurrent.TimeoutException -> L95
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.Exception -> L81 java.util.concurrent.TimeoutException -> L95
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r3, r7)     // Catch: java.lang.Exception -> L81 java.util.concurrent.TimeoutException -> L95
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2     // Catch: java.lang.Exception -> L81 java.util.concurrent.TimeoutException -> L95
            r6 = r2
            goto La8
        L81:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "An error occurred whilst trying to retrieve a big picture style large icon: "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            app.notifee.core.Logger.e(r5, r1, r2)
            goto La8
        L95:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Timeout occurred whilst trying to retrieve a big picture style large icon: "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            app.notifee.core.Logger.e(r5, r1, r2)
        La8:
            if (r6 == 0) goto Lad
            r0.bigLargeIcon(r6)
        Lad:
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "title"
            boolean r1 = r1.containsKey(r2)
            r3 = 0
            if (r1 == 0) goto Lc7
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            android.text.Spanned r1 = androidx.core.text.HtmlCompat.fromHtml(r1, r3)
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = r0.setBigContentTitle(r1)
        Lc7:
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r2 = "summary"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto Le0
            android.os.Bundle r1 = r9.mNotificationAndroidStyleBundle
            java.lang.String r1 = r1.getString(r2)
            android.text.Spanned r1 = androidx.core.text.HtmlCompat.fromHtml(r1, r3)
            androidx.core.app.NotificationCompat$BigPictureStyle r0 = r0.setSummaryText(r1)
        Le0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: app.notifee.core.model.NotificationAndroidStyleModel.m353xb4e8acab():androidx.core.app.NotificationCompat$Style");
    }

    /* renamed from: lambda$getMessagingStyleTask$2$app-notifee-core-model-NotificationAndroidStyleModel, reason: not valid java name */
    NotificationCompat.Style m354x25b4cf9e(Executor executor) throws Exception {
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle((Person) Tasks.await(getPerson(executor, (Bundle) Objects.requireNonNull(this.mNotificationAndroidStyleBundle.getBundle("person"))), 20L, TimeUnit.SECONDS));
        if (this.mNotificationAndroidStyleBundle.containsKey(KeychainModule.AuthPromptOptions.TITLE)) {
            messagingStyle = messagingStyle.setConversationTitle(HtmlCompat.fromHtml(this.mNotificationAndroidStyleBundle.getString(KeychainModule.AuthPromptOptions.TITLE), 0));
        }
        if (this.mNotificationAndroidStyleBundle.containsKey("group")) {
            messagingStyle = messagingStyle.setGroupConversation(this.mNotificationAndroidStyleBundle.getBoolean("group"));
        }
        ArrayList parcelableArrayList = this.mNotificationAndroidStyleBundle.getParcelableArrayList("messages");
        for (int i = 0; i < ((ArrayList) Objects.requireNonNull(parcelableArrayList)).size(); i++) {
            Bundle bundle = (Bundle) parcelableArrayList.get(i);
            messagingStyle = messagingStyle.addMessage(HtmlCompat.fromHtml(bundle.getString("text"), 0), l.b(bundle.get("timestamp")), bundle.containsKey("person") ? (Person) Tasks.await(getPerson(executor, (Bundle) Objects.requireNonNull(bundle.getBundle("person"))), 20L, TimeUnit.SECONDS) : null);
        }
        return messagingStyle;
    }

    public Bundle toBundle() {
        return (Bundle) this.mNotificationAndroidStyleBundle.clone();
    }
}
