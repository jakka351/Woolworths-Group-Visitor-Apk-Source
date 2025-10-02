package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p000authapi.zbbb;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: com.google.android.gms:play-services-auth@@21.4.0 */
/* loaded from: classes3.dex */
public final class zbb implements Runnable {
    private static final Logger zba = new Logger("RevokeAccessOperation", new String[0]);
    private final String zbb;
    private final StatusPendingResult zbc = new StatusPendingResult((GoogleApiClient) null);

    public zbb(String str) {
        this.zbb = Preconditions.checkNotEmpty(str);
    }

    public static PendingResult zba(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), null);
        }
        zbb zbbVar = new zbb(str);
        new Thread(zbbVar).start();
        return zbbVar.zbc;
    }

    @Override // java.lang.Runnable
    public final void run() throws IOException {
        Status status = Status.RESULT_INTERNAL_ERROR;
        try {
            String str = this.zbb;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 50);
            sb.append("https://accounts.google.com/o/oauth2/revoke?token=");
            sb.append(str);
            URL url = new URL(sb.toString());
            int i = zbbb.zbb;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                status = Status.RESULT_SUCCESS;
            } else {
                zba.e("Unable to revoke access!", new Object[0]);
            }
            Logger logger = zba;
            StringBuilder sb2 = new StringBuilder(String.valueOf(responseCode).length() + 15);
            sb2.append("Response Code: ");
            sb2.append(responseCode);
            logger.d(sb2.toString(), new Object[0]);
        } catch (IOException e) {
            Logger logger2 = zba;
            String string = e.toString();
            String.valueOf(string);
            logger2.e("IOException when revoking access: ".concat(String.valueOf(string)), new Object[0]);
        } catch (Exception e2) {
            Logger logger3 = zba;
            String string2 = e2.toString();
            String.valueOf(string2);
            logger3.e("Exception when revoking access: ".concat(String.valueOf(string2)), new Object[0]);
        }
        this.zbc.setResult(status);
    }
}
