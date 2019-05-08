-useuniqueclassmembernames

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

-keep class net.time4j.SPX
-keep class net.time4j.tz.SPX
-keep class net.time4j.tz.model.SPX

-keepnames class net.time4j.calendar.ChineseCalendar$SPX
-keepnames class net.time4j.calendar.CopticCalendar$SPX
-keepnames class net.time4j.calendar.EthiopianCalendar$SPX
-keepnames class net.time4j.calendar.EthiopianTime$SPX
-keepnames class net.time4j.calendar.HebrewCalendar$SPX
-keepnames class net.time4j.calendar.HebrewTime$SPX
-keepnames class net.time4j.calendar.HijriCalendar$SPX
-keepnames class net.time4j.calendar.HistoricCalendar$SPX
-keepnames class net.time4j.calendar.IndianCalendar$SPX
-keepnames class net.time4j.calendar.JapaneseCalendar$SPX
-keepnames class net.time4j.calendar.JucheCalendar$SPX
-keepnames class net.time4j.calendar.JulianCalendar$SPX
-keepnames class net.time4j.calendar.KoreanCalendar$SPX
-keepnames class net.time4j.calendar.MinguoCalendar$SPX
-keepnames class net.time4j.calendar.PersianCalendar$SPX
-keepnames class net.time4j.calendar.ThaiSolarCalendar$SPX
-keepnames class net.time4j.calendar.VietnameseCalendar$SPX
-keepnames class net.time4j.calendar.bahai.SPX
-keepnames class net.time4j.calendar.frenchrev.SPX
-keepnames class net.time4j.history.SPX

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
