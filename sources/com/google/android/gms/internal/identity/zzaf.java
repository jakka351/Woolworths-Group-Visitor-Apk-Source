package com.google.android.gms.internal.identity;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionApi;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
public final class zzaf implements ActivityRecognitionApi {
    @Override // com.google.android.gms.location.ActivityRecognitionApi
    public final PendingResult<Status> removeActivityUpdates(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzad(this, googleApiClient, pendingIntent));
    }

    @Override // com.google.android.gms.location.ActivityRecognitionApi
    public final PendingResult<Status> requestActivityUpdates(GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        return googleApiClient.execute(new zzac(this, googleApiClient, j, pendingIntent));
    }
}
