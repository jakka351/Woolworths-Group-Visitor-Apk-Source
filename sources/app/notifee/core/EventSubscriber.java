package app.notifee.core;

import android.content.Context;
import app.notifee.core.event.BlockStateEvent;
import app.notifee.core.event.ForegroundServiceEvent;
import app.notifee.core.event.LogEvent;
import app.notifee.core.event.NotificationEvent;
import app.notifee.core.interfaces.EventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import n.o.t.i.f.e.e.e;
import n.o.t.i.f.e.e.f;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class EventSubscriber {
    public static final EventSubscriber b = new EventSubscriber();
    public final Set<EventListener> a = new HashSet();

    private EventSubscriber() {
        f.c(this);
    }

    public static Context getContext() {
        return e.a;
    }

    public static void register(EventListener eventListener) {
        b.a.add(eventListener);
    }

    public static void unregister(EventListener eventListener) {
        b.a.remove(eventListener);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBlockStateEvent(BlockStateEvent blockStateEvent) {
        Iterator<EventListener> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().onBlockStateEvent(blockStateEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onForegroundServiceEvent(ForegroundServiceEvent foregroundServiceEvent) {
        Iterator<EventListener> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().onForegroundServiceEvent(foregroundServiceEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogEvent(LogEvent logEvent) {
        Iterator<EventListener> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().onLogEvent(logEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNotificationEvent(NotificationEvent notificationEvent) {
        Iterator<EventListener> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().onNotificationEvent(notificationEvent);
        }
    }
}
