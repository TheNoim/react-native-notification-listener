
package io.noim.notificationlistener;

import android.annotation.SuppressLint;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.util.ArrayList;
import java.util.List;
import io.noim.notificationlistener.NOIMNotificationListenerHook;

public class NOIMNotificationListenerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public NOIMNotificationListenerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "NOIMNotificationListener";
    }

    @ReactMethod
    public void registerNotificationEvent(Callback callback, Callback successfullyCallback) {
        NOIMNotificationListenerHook service = new NOIMNotificationListenerHook(callback);
        successfullyCallback.invoke();
    }
}