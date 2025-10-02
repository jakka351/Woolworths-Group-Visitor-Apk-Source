package com.maplibre.rctmln.modules;

import android.location.Location;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.maplibre.rctmln.events.EventEmitter;
import com.maplibre.rctmln.events.LocationEvent;
import com.maplibre.rctmln.location.LocationManager;
import org.maplibre.android.location.engine.LocationEngineCallback;
import org.maplibre.android.location.engine.LocationEngineResult;

@ReactModule(name = RCTMLNLocationModule.REACT_CLASS)
/* loaded from: classes3.dex */
public class RCTMLNLocationModule extends ReactContextBaseJavaModule {
    public static final String LOCATION_UPDATE = "MapboxUserLocationUpdate";
    public static final String REACT_CLASS = "RCTMLNLocationModule";
    private boolean isEnabled;
    private boolean isPaused;
    private LifecycleEventListener lifecycleEventListener;
    private LocationManager locationManager;
    private float mMinDisplacement;
    private LocationManager.OnUserLocationChange onUserLocationChangeCallback;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RCTMLNLocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.lifecycleEventListener = new LifecycleEventListener() { // from class: com.maplibre.rctmln.modules.RCTMLNLocationModule.1
            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostResume() throws SecurityException {
                if (RCTMLNLocationModule.this.isEnabled) {
                    RCTMLNLocationModule.this.startLocationManager();
                }
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostPause() {
                RCTMLNLocationModule.this.pauseLocationManager();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostDestroy() {
                RCTMLNLocationModule.this.stopLocationManager();
            }
        };
        this.onUserLocationChangeCallback = new LocationManager.OnUserLocationChange() { // from class: com.maplibre.rctmln.modules.RCTMLNLocationModule.2
            @Override // com.maplibre.rctmln.location.LocationManager.OnUserLocationChange
            public void onLocationChange(Location location) {
                LocationEvent locationEvent = new LocationEvent(location);
                RCTNativeAppEventEmitter moduleEmitter = EventEmitter.getModuleEmitter(RCTMLNLocationModule.this.getReactApplicationContext());
                if (moduleEmitter != null) {
                    moduleEmitter.emit(RCTMLNLocationModule.LOCATION_UPDATE, locationEvent.getPayload());
                }
            }
        };
        this.locationManager = LocationManager.getInstance(reactApplicationContext);
        reactApplicationContext.addLifecycleEventListener(this.lifecycleEventListener);
    }

    @ReactMethod
    public void start(float f) throws SecurityException {
        this.isEnabled = true;
        this.mMinDisplacement = f;
        startLocationManager();
    }

    @ReactMethod
    public void setMinDisplacement(float f) throws SecurityException {
        if (this.mMinDisplacement == f) {
            return;
        }
        this.mMinDisplacement = f;
        if (this.isEnabled) {
            this.locationManager.setMinDisplacement(f);
            this.locationManager.enable();
        }
    }

    @ReactMethod
    public void stop() {
        stopLocationManager();
    }

    @ReactMethod
    public void pause() {
        pauseLocationManager();
    }

    @ReactMethod
    public void getLastKnownLocation(final Promise promise) {
        this.locationManager.getLastKnownLocation(new LocationEngineCallback<LocationEngineResult>() { // from class: com.maplibre.rctmln.modules.RCTMLNLocationModule.3
            @Override // org.maplibre.android.location.engine.LocationEngineCallback
            public void onSuccess(LocationEngineResult locationEngineResult) {
                Location lastLocation = locationEngineResult.getLastLocation();
                if (locationEngineResult.getLastLocation() != null) {
                    promise.resolve(new LocationEvent(lastLocation).getPayload());
                } else {
                    promise.resolve(null);
                }
            }

            @Override // org.maplibre.android.location.engine.LocationEngineCallback
            public void onFailure(Exception exc) {
                promise.reject(exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLocationManager() throws SecurityException {
        this.locationManager.addLocationListener(this.onUserLocationChangeCallback);
        this.locationManager.setMinDisplacement(this.mMinDisplacement);
        this.locationManager.enable();
        this.isPaused = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pauseLocationManager() {
        if (this.isPaused) {
            return;
        }
        this.locationManager.disable();
        this.isPaused = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopLocationManager() {
        if (this.isEnabled) {
            this.locationManager.removeLocationListener(this.onUserLocationChangeCallback);
            this.locationManager.dispose();
            this.isEnabled = false;
            this.isPaused = false;
        }
    }
}
