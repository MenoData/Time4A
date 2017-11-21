-keep class net.time4j.android.spi.AndroidResourceLoader

-keepnames class net.time4j.tz.olson.AFRICA
-keepnames class net.time4j.tz.olson.AMERICA
-keepnames class net.time4j.tz.olson.AMERICA$ARGENTINA
-keepnames class net.time4j.tz.olson.AMERICA$INDIANA
-keepnames class net.time4j.tz.olson.AMERICA$KENTUCKY
-keepnames class net.time4j.tz.olson.AMERICA$NORTH_DAKOTA
-keepnames class net.time4j.tz.olson.ANTARCTICA
-keepnames class net.time4j.tz.olson.ASIA
-keepnames class net.time4j.tz.olson.ATLANTIC
-keepnames class net.time4j.tz.olson.AUSTRALIA
-keepnames class net.time4j.tz.olson.EUROPE
-keepnames class net.time4j.tz.olson.INDIAN
-keepnames class net.time4j.tz.olson.PACIFIC

-keep class **.SPX
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keepclassmembers class **.SPX implements java.io.Externalizable {
    static final long serialVersionUID;
    <init>();
    public void writeExternal(java.io.ObjectOutput);
    public void readExternal(java.io.ObjectInput);
    java.lang.Object readResolve();
}
