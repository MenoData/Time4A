# Time4A
Advanced Date and Time Library for Android

## What is Time4A?
This project is a specialized version of Time4J-v3.x (originally using the branch level6) and distributes
an AAR-library suitable for the Android platform. More details and links can be found in the main project under:

https://github.com/MenoData/Time4J

The main difference compared with Time4J is how all resources are organized. Time4A does not use the service loader mechanism
since latter one is rather inefficient on Android. Instead Time4A uses the assets-directory to store all text resources,
calendar data and last but not least the latest available timezone data. If you want you can even override the standard
resources by defining your own assets using the same resource paths.

## Why not use other libraries?
While libraries like ThreetenABP or Joda-Time-Android care about holding the latest timezone data independent
from the Android platform they fail to hold their own i18n-resources. But real-world mobile devices using the
Android platform often use very different text resources and data for world languages, dependent on how up-to-date
the Android OS-version is. Time4A closes this big gap **supporting actually 92 languages** and has more features
than any other date and time library available for the Android platform. Of course, Time4A also supports the
**newest timezone data** available at [IANA](http://www.iana.org/time-zones). On the other side: If you really
want you can even use the platform timezone data in parallel. Time4A gives you the freedom which timezone data to use.

## Any disadvantages?
Yes, Time4A is rather big in terms of method count and size due to a lot of features (although still much smaller than ICU4J).
However, you can apply Proguard to strongly shrink the size.

## Usage
Only two steps are required before coding against the API of Time4A.

- First step: Insert following dependency to your build.gradle-file.

```groovy
dependencies {
    implementation group: 'net.time4j', name: 'time4j-android', version: '4.2-2018i'
}
```

- Second step: Initialize your application this way.
```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationStarter.initialize(this, true); // with prefetch on background thread
    }
}
```

## Android-specific topics
- Time4A registers a specialized `BroadcastReceiver` in order to track any change to the system timezone.
- The Android version of `TickProvider` deploys the best available clock using `android.os.SystemClock.elapsedRealtimeNanos()`.
- Time-specific format patterns are aware of the user-preference for 12/24-hour-format if the system locale is chosen.
- Proguard is strongly recommended to shrink the size of Time4A.

## Feedback
Feedback is welcome. You can best use the issue-tracker of the main project Time4J for feedback:

https://github.com/MenoData/Time4J/issues
