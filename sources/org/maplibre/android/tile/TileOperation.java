package org.maplibre.android.tile;

/* loaded from: classes2.dex */
public enum TileOperation {
    RequestedFromCache,
    RequestedFromNetwork,
    LoadFromNetwork,
    LoadFromCache,
    StartParse,
    EndParse,
    Error,
    Cancelled,
    NullOp
}
