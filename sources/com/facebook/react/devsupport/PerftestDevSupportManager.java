package com.facebook.react.devsupport;

import android.content.Context;
import com.facebook.react.devsupport.DevInternalSettings;
import com.facebook.react.devsupport.InspectorPackagerConnection;

/* loaded from: classes.dex */
public final class PerftestDevSupportManager extends DisabledDevSupportManager {
    private final InspectorPackagerConnection.BundleStatus mBundleStatus;
    private final DevServerHelper mDevServerHelper;
    private final DevInternalSettings mDevSettings;

    public PerftestDevSupportManager(Context context) {
        DevInternalSettings devInternalSettings = new DevInternalSettings(context, new DevInternalSettings.Listener() { // from class: com.facebook.react.devsupport.PerftestDevSupportManager.1
            @Override // com.facebook.react.devsupport.DevInternalSettings.Listener
            public void onInternalSettingsChanged() {
            }
        });
        this.mDevSettings = devInternalSettings;
        this.mBundleStatus = new InspectorPackagerConnection.BundleStatus();
        this.mDevServerHelper = new DevServerHelper(devInternalSettings, context.getPackageName(), new InspectorPackagerConnection.BundleStatusProvider() { // from class: com.facebook.react.devsupport.PerftestDevSupportManager$$ExternalSyntheticLambda0
            @Override // com.facebook.react.devsupport.InspectorPackagerConnection.BundleStatusProvider
            public final InspectorPackagerConnection.BundleStatus getBundleStatus() {
                return this.f$0.lambda$new$0();
            }
        }, devInternalSettings.getPackagerConnectionSettings());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ InspectorPackagerConnection.BundleStatus lambda$new$0() {
        return this.mBundleStatus;
    }

    @Override // com.facebook.react.devsupport.DisabledDevSupportManager, com.facebook.react.devsupport.interfaces.DevSupportManager
    public DevInternalSettings getDevSettings() {
        return this.mDevSettings;
    }

    @Override // com.facebook.react.devsupport.DisabledDevSupportManager, com.facebook.react.devsupport.interfaces.DevSupportManager
    public void startInspector() {
        this.mDevServerHelper.openInspectorConnection();
    }

    @Override // com.facebook.react.devsupport.DisabledDevSupportManager, com.facebook.react.devsupport.interfaces.DevSupportManager
    public void stopInspector() {
        this.mDevServerHelper.closeInspectorConnection();
    }
}
