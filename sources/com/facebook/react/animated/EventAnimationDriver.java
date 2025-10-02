package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.UnexpectedNativeTypeException;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;

/* loaded from: classes.dex */
class EventAnimationDriver implements RCTEventEmitter {
    String mEventName;
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;
    int mViewTag;

    public EventAnimationDriver(String str, int i, List<String> list, ValueAnimatedNode valueAnimatedNode) {
        this.mEventName = str;
        this.mViewTag = i;
        this.mEventPath = list;
        this.mValueNode = valueAnimatedNode;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, WritableMap writableMap) throws NumberFormatException {
        ReadableArray array;
        WritableMap map;
        if (writableMap == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        int i2 = 0;
        ReadableArray readableArray = null;
        WritableMap writableMap2 = writableMap;
        while (i2 < this.mEventPath.size() - 1) {
            if (writableMap2 != null) {
                String str2 = this.mEventPath.get(i2);
                ReadableType type = writableMap2.getType(str2);
                if (type == ReadableType.Map) {
                    map = writableMap2.getMap(str2);
                    readableArray = null;
                } else if (type == ReadableType.Array) {
                    array = writableMap2.getArray(str2);
                    readableArray = array;
                    map = null;
                } else {
                    throw new UnexpectedNativeTypeException("Unexpected type " + type + " for key '" + str2 + "'");
                }
            } else {
                int i3 = Integer.parseInt(this.mEventPath.get(i2));
                ReadableType type2 = readableArray.getType(i3);
                if (type2 == ReadableType.Map) {
                    map = readableArray.getMap(i3);
                    readableArray = null;
                } else if (type2 == ReadableType.Array) {
                    array = readableArray.getArray(i3);
                    readableArray = array;
                    map = null;
                } else {
                    throw new UnexpectedNativeTypeException("Unexpected type " + type2 + " for index '" + i3 + "'");
                }
            }
            i2++;
            writableMap2 = map;
        }
        String str3 = this.mEventPath.get(r6.size() - 1);
        if (writableMap2 != null) {
            this.mValueNode.mValue = writableMap2.getDouble(str3);
        } else {
            this.mValueNode.mValue = readableArray.getDouble(Integer.parseInt(str3));
        }
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
