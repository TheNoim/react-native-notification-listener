
package io.noim.notificationlistener;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import java.util.ArrayList;
import java.util.List;

public class NOIMNotificationListenerModule extends ReactContextBaseJavaModule, NotificationListenerService {

    private final ReactApplicationContext reactContext;

    private List<Callback> callbacks = new ArrayList<Callback>();

    public NOIMNotificationListenerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "NOIMNotificationListener";
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        for (Callback callback : callbacks) {
            callback.invoke(sbn);
        }
    }

    @ReactMethod
    public void registerNotificationEvent(Callback callback) {
        callback.add(callback);
    }
}