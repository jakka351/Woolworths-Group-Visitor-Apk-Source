package org.maplibre.android.offline;

import kotlin.Metadata;

/* compiled from: OfflineRegionStatus.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\u00020\u0001BA\b\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016¨\u0006\u0018"}, d2 = {"Lorg/maplibre/android/offline/OfflineRegionStatus;", "", "downloadState", "", "completedResourceCount", "", "completedResourceSize", "completedTileCount", "completedTileSize", "requiredResourceCount", "isRequiredResourceCountPrecise", "", "<init>", "(IJJJJJZ)V", "getDownloadState", "()I", "getCompletedResourceCount", "()J", "getCompletedResourceSize", "getCompletedTileCount", "getCompletedTileSize", "getRequiredResourceCount", "()Z", "isComplete", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OfflineRegionStatus {
    private final long completedResourceCount;
    private final long completedResourceSize;
    private final long completedTileCount;
    private final long completedTileSize;
    private final int downloadState;
    private final boolean isRequiredResourceCountPrecise;
    private final long requiredResourceCount;

    private OfflineRegionStatus(int i, long j, long j2, long j3, long j4, long j5, boolean z) {
        this.downloadState = i;
        this.completedResourceCount = j;
        this.completedResourceSize = j2;
        this.completedTileCount = j3;
        this.completedTileSize = j4;
        this.requiredResourceCount = j5;
        this.isRequiredResourceCountPrecise = z;
    }

    public final int getDownloadState() {
        return this.downloadState;
    }

    public final long getCompletedResourceCount() {
        return this.completedResourceCount;
    }

    public final long getCompletedResourceSize() {
        return this.completedResourceSize;
    }

    public final long getCompletedTileCount() {
        return this.completedTileCount;
    }

    public final long getCompletedTileSize() {
        return this.completedTileSize;
    }

    public final long getRequiredResourceCount() {
        return this.requiredResourceCount;
    }

    /* renamed from: isRequiredResourceCountPrecise, reason: from getter */
    public final boolean getIsRequiredResourceCountPrecise() {
        return this.isRequiredResourceCountPrecise;
    }

    public final boolean isComplete() {
        return this.completedResourceCount >= this.requiredResourceCount;
    }
}
