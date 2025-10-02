package app.notifee.core.event;

import android.os.Bundle;
import app.notifee.core.interfaces.MethodCallResult;

/* loaded from: classes.dex */
public class BlockStateEvent {
    public static final int TYPE_APP_BLOCKED = 4;
    public static final int TYPE_CHANNEL_BLOCKED = 5;
    public static final int TYPE_CHANNEL_GROUP_BLOCKED = 6;
    public int a;
    public boolean b;
    public Bundle c;
    public MethodCallResult<Void> d;
    public boolean e = false;

    public BlockStateEvent(int i, Bundle bundle, boolean z, MethodCallResult<Void> methodCallResult) {
        this.a = i;
        this.d = methodCallResult;
        this.b = z;
        this.c = bundle;
    }

    public Bundle getChannelOrGroupBundle() {
        return this.c;
    }

    public int getType() {
        return this.a;
    }

    public boolean isBlocked() {
        return this.b;
    }

    public void setCompletionResult() {
        if (this.e) {
            return;
        }
        this.e = true;
        this.d.onComplete(null, null);
    }
}
