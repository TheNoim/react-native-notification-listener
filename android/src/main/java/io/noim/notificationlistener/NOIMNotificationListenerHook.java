
package io.noim.notificationlistener;

import com.facebook.react.bridge.Callback;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import java.util.ArrayList;
import java.util.List;

public class NOIMNotificationListenerHook extends NotificationListenerService {

    private List<Callback> callbacks = new ArrayList<Callback>();

    public void addCallback(Callback callback) {
        callbacks.add(callback);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        for (Callback callback : callbacks) {
            callback.invoke(sbn);
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Nothing to see here but required
    }
}