package io.sentry;

/* loaded from: classes3.dex */
public interface JsonDeserializer<T> {
    T deserialize(ObjectReader objectReader, ILogger iLogger) throws Exception;
}
