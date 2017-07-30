
package io.noim.notificationlistener;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import java.util.ArrayList;
import java.util.List;

public class NOIMNotificationListenerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private NotificationHookService = new NOIMNotificationListenerHook();

    public NOIMNotificationListenerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "NOIMNotificationListener";
    }

    @ReactMethod
    public void registerNotificationEvent(Callback callback) {
        NotificationHookService.addCallback(callback);
    }
}