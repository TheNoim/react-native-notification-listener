
# io.noim-notification-listener

## Getting started

`$ npm install io.noim-notification-listener --save`

### Mostly automatic installation

`$ react-native link io.noim-notification-listener`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `io.noim-notification-listener` and add `NOIMNotificationListener.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libNOIMNotificationListener.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import io.noim.notificationlistener.NOIMNotificationListenerPackage;` to the imports at the top of the file
  - Add `new NOIMNotificationListenerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':io.noim-notification-listener'
  	project(':io.noim-notification-listener').projectDir = new File(rootProject.projectDir, 	'../node_modules/io.noim-notification-listener/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':io.noim-notification-listener')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `NOIMNotificationListener.sln` in `node_modules/io.noim-notification-listener/windows/NOIMNotificationListener.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Com.Reactlibrary.NOIMNotificationListener;` to the usings at the top of the file
  - Add `new NOIMNotificationListenerPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import NOIMNotificationListener from 'io.noim-notification-listener';

// TODO: What to do with the module?
NOIMNotificationListener;
```
  