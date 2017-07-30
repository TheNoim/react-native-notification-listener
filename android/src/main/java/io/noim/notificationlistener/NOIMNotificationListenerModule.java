
package io.noim.notificationlistener;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class NOIMNotificationListenerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    public static Hook h;

    public NOIMNotificationListenerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.h = new Hook();
    }

    @Override
    public String getName() {
        return "NOIMNotificationListener";
    }

    public void sendEvent(ReactContext reactContext, String eventName, @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, params);
    }

    class Hook extends NotificationListenerService {

        @Override
        public void onNotificationPosted(StatusBarNotification sbn) {
            sendEvent(reactContext, "received");
            JSONObject notification = new JSONObject();
            try {
                notification.put("title", sbn.getNotification().extras.get("android.title"));
                notification.put("pkg", sbn.getPackageName());
                notification.put("text", sbn.getNotification().extras.get("android.text"));
                notification.put("textLines", sbn.getNotification().extras.get("android.textLines"));
                sendEvent(reactContext, "notification", convertJsonToMap(notification));
            } catch (JSONException e) {
                sendEvent(reactContext, "error");
                e.printStackTrace();
            }
        }

        @Override
        public void onNotificationRemoved(StatusBarNotification sbn) {
            sendEvent(reactContext, "removed");
        }

        private static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
            WritableMap map = new WritableNativeMap();

            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject) {
                    map.putMap(key, convertJsonToMap((JSONObject) value));
                } else if (value instanceof JSONArray) {
                    map.putArray(key, convertJsonToArray((JSONArray) value));
                } else if (value instanceof  Boolean) {
                    map.putBoolean(key, (Boolean) value);
                } else if (value instanceof  Integer) {
                    map.putInt(key, (Integer) value);
                } else if (value instanceof  Double) {
                    map.putDouble(key, (Double) value);
                } else if (value instanceof String)  {
                    map.putString(key, (String) value);
                } else {
                    map.putString(key, value.toString());
                }
            }
            return map;
        }

        private static WritableArray convertJsonToArray(JSONArray jsonArray) throws JSONException {
            WritableArray array = new WritableNativeArray();

            for (int i = 0; i < jsonArray.length(); i++) {
                Object value = jsonArray.get(i);
                if (value instanceof JSONObject) {
                    array.pushMap(convertJsonToMap((JSONObject) value));
                } else if (value instanceof  JSONArray) {
                    array.pushArray(convertJsonToArray((JSONArray) value));
                } else if (value instanceof  Boolean) {
                    array.pushBoolean((Boolean) value);
                } else if (value instanceof  Integer) {
                    array.pushInt((Integer) value);
                } else if (value instanceof  Double) {
                    array.pushDouble((Double) value);
                } else if (value instanceof String)  {
                    array.pushString((String) value);
                } else {
                    array.pushString(value.toString());
                }
            }
            return array;
        }

        private static JSONObject convertMapToJson(ReadableMap readableMap) throws JSONException {
            JSONObject object = new JSONObject();
            ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                switch (readableMap.getType(key)) {
                    case Null:
                        object.put(key, JSONObject.NULL);
                        break;
                    case Boolean:
                        object.put(key, readableMap.getBoolean(key));
                        break;
                    case Number:
                        object.put(key, readableMap.getDouble(key));
                        break;
                    case String:
                        object.put(key, readableMap.getString(key));
                        break;
                    case Map:
                        object.put(key, convertMapToJson(readableMap.getMap(key)));
                        break;
                    case Array:
                        object.put(key, convertArrayToJson(readableMap.getArray(key)));
                        break;
                }
            }
            return object;
        }

        private static JSONArray convertArrayToJson(ReadableArray readableArray) throws JSONException {
            JSONArray array = new JSONArray();
            for (int i = 0; i < readableArray.size(); i++) {
                switch (readableArray.getType(i)) {
                    case Null:
                        break;
                    case Boolean:
                        array.put(readableArray.getBoolean(i));
                        break;
                    case Number:
                        array.put(readableArray.getDouble(i));
                        break;
                    case String:
                        array.put(readableArray.getString(i));
                        break;
                    case Map:
                        array.put(convertMapToJson(readableArray.getMap(i)));
                        break;
                    case Array:
                        array.put(convertArrayToJson(readableArray.getArray(i)));
                        break;
                }
            }
            return array;
        }

    }
}