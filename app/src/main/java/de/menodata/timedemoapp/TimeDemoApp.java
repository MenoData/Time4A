package de.menodata.timedemoapp;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import net.time4j.ClockUnit;
import net.time4j.Duration;
import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.PrettyTime;
import net.time4j.SystemClock;
import net.time4j.Weekmodel;
import net.time4j.android.TimeApplication;
import net.time4j.calendar.ChineseCalendar;
import net.time4j.calendar.EthiopianCalendar;
import net.time4j.calendar.HebrewCalendar;
import net.time4j.calendar.HijriCalendar;
import net.time4j.calendar.JapaneseCalendar;
import net.time4j.calendar.KoreanCalendar;
import net.time4j.calendar.PersianCalendar;
import net.time4j.calendar.PersianMonth;
import net.time4j.calendar.astro.LunarTime;
import net.time4j.calendar.astro.SolarTime;
import net.time4j.calendar.frenchrev.FrenchRepublicanCalendar;
import net.time4j.engine.CalendarDays;
import net.time4j.engine.StartOfDay;
import net.time4j.format.CalendarText;
import net.time4j.format.DisplayMode;
import net.time4j.format.TextWidth;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;
import net.time4j.tz.TZID;
import net.time4j.tz.Timezone;
import net.time4j.tz.olson.EUROPE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Locale;

public class TimeDemoApp
    extends TimeApplication {

    @Override
    public void onCreate() {
        try {
            super.onCreate();
            String timeInfo = getTimeInfo(this);
            Log.i("TIME4A", timeInfo);
        } catch (Throwable th) {
            Log.e("TIME4A-ERROR-ON-INIT", th.getMessage(), th);
            throw th;
        }
    }

    // called by MainActivity
    static String getTimeInfo(Context context) {

        Duration<?> dur = Duration.of(337540, ClockUnit.SECONDS).with(Duration.STD_CLOCK_PERIOD);
        String formattedDuration = PrettyTime.of(Locale.FRANCE).print(dur, TextWidth.WIDE);

        int dangiYear = PlainDate.of(2018, 1, 1).get(KoreanCalendar.YEAR_OF_ERA);
        ChronoFormatter<PlainDate> gregKorean =
                ChronoFormatter.setUp(PlainDate.axis(), Locale.ROOT)
                        .addPattern("dd.MM.yyyy (", PatternType.CLDR)
                        .addFixedInteger(KoreanCalendar.YEAR_OF_ERA, 4) // extension test
                        .addLiteral(")")
                        .build();
        ChronoFormatter<PersianCalendar> pf =
                ChronoFormatter.setUp(PersianCalendar.class, Locale.ENGLISH)
                        .addPattern("MMMM d, yyyy G", PatternType.CLDR_DATE).build();
        ChronoFormatter<JapaneseCalendar> jf =
                ChronoFormatter.setUp(JapaneseCalendar.class, Locale.ENGLISH)
                        .addPattern("MMMM d, G y", PatternType.CLDR_DATE).build();
        ChronoFormatter<HijriCalendar> hf =
                ChronoFormatter.setUp(HijriCalendar.class, Locale.ENGLISH)
                        .addPattern("MM/dd/yyyy", PatternType.CLDR_DATE).build();
        HijriCalendar hijriDate =
                SystemClock.inLocalView().now(
                        HijriCalendar.family(), HijriCalendar.VARIANT_ICU4J, StartOfDay.EVENING
                ).toDate();
        String germanTime =
                SystemClock.inLocalView().now().toTime().print(
                        ChronoFormatter.ofTimeStyle(DisplayMode.FULL, Locale.GERMANY));
        ChronoFormatter<Moment> ef =
                ChronoFormatter.setUpWithOverride(Locale.ENGLISH, EthiopianCalendar.axis())
                        .addPattern("G, yyyy-MM-dd hh:mm a XXX", PatternType.CLDR)
                        .build();
        Moment ethio;
        try {
            ethio = ef.parse("Amete Mihret, 2008-03-09 03:45 PM +03:00");
        } catch (ParseException pe) {
            throw new IllegalStateException(pe);
        }
        ChronoFormatter<FrenchRepublicanCalendar> ff =
                ChronoFormatter.ofPattern(
                        "D. MMMM', an 'Y|SSSS', an 'Y",
                        PatternType.DYNAMIC,
                        Locale.FRENCH,
                        FrenchRepublicanCalendar.axis());
        FrenchRepublicanCalendar fcal =
                PlainDate.of(2018, 9, 23).transform(FrenchRepublicanCalendar.class);

        ChronoFormatter<HebrewCalendar> fh =
                ChronoFormatter.ofPattern(
                        "d. MMMM, yyyy",
                        PatternType.CLDR_DATE,
                        Locale.US,
                        HebrewCalendar.axis());

        ChronoFormatter<ChineseCalendar> fc = // example: 2018戊戌年正月15星期五
                ChronoFormatter.ofStyle(DisplayMode.FULL, Locale.CHINESE, ChineseCalendar.axis());

        Moment moment = SystemClock.currentMoment();
        TZID hhZone = Timezone.of("Europe/Berlin").getID();
        PlainDate today = moment.toZonalTimestamp(hhZone).getCalendarDate();
        SolarTime.Sunshine hhSun =
                SolarTime.ofLocation()
                        .northernLatitude(53, 33, 0)
                        .easternLongitude(10, 0, 0)
                        .build()
                        .sunshine(hhZone).apply(today);
        LunarTime.Moonlight hhMoon =
                LunarTime.ofLocation(hhZone)
                        .northernLatitude(53, 33, 0)
                        .easternLongitude(10, 0, 0)
                        .build()
                        .on(today);

        return "\n\n\n\n"
                + "=> Current time (UTC): " + moment.toString()
                + "\n"
                + "\n=> System locale: " + Locale.getDefault()
                + "\n=> System timezone: " + Timezone.ofSystem().getID().canonical()
                + "\n=> Use 24-hour-format: " + DateFormat.is24HourFormat(context)
                + "\n=> Time pattern for system locale: "
                + CalendarText.patternForTime(DisplayMode.SHORT, Locale.getDefault())
                + "\n=> Noon for system locale: "
                + ChronoFormatter.ofTimeStyle(DisplayMode.SHORT, Locale.getDefault()).format(PlainTime.of(12))
                + "\n=> Evening for system locale: "
                + ChronoFormatter.ofTimeStyle(DisplayMode.SHORT, Locale.getDefault()).format(PlainTime.of(19))
                + "\n"
                + "\n=> Local timestamp: " + moment.toLocalTimestamp()
                + "\n=> Next leap second: " + moment.with(Moment.nextLeapSecond())
                + "\n"
                + "\n=> Week model (system): " + Weekmodel.ofSystem()
                + "\n=> Week model (Afghanistan): " + Weekmodel.of(new Locale("fa", "AF"))
                + "\n"
                + "\n=> Example for Austrian timestamp: "
                + ChronoFormatter.ofMomentPattern(
                        "d. MMMM uuuu GGGG h:mm BBBB z",
                        PatternType.CLDR,
                        new Locale("de", "AT"),
                        EUROPE.BERLIN
                )
                .withAlternativeEraNames()
                .format(PlainTimestamp.of(2015, 1, 27, 14, 15).inStdTimezone())
                + "\n"
                + "\n=> Current German time (full style): " + germanTime
                + "\n"
                + "\n=> TZDB-version: " + Timezone.getVersion("TZDB")
                + "\n=> ZONE-PROVIDERS: " + Timezone.getProviderInfo()
                + "\n=> Europe/Dublin-DST: " + Timezone.of("Europe/Dublin").getDaylightSavingOffset(moment)
                + "\n=> sunrise (HH): " + hhSun.startLocal()
                + "\n=> sunset (HH): " + hhSun.endLocal()
                + "\n=> moonrise (HH): " + hhMoon.moonriseLocal()
                + "\n=> moonset (HH): " + hhMoon.moonsetLocal()
                + "\n"
                + "\n=> Duration=" + formattedDuration
                + "\n"
                + "\n=> Hijri-toString=" + hijriDate
                + "\n=> Hijri-formatted=" + hf.format(hijriDate)
                + "\n=> Persian-today=" + pf.format(hijriDate.transform(PersianCalendar.class))
                + "\n=> Persian-serialized=" + createSerialized()
                + "\n=> Japanese-today=" + jf.format(hijriDate.transform(JapaneseCalendar.class))
                + "\n=> Hebrew-today=" + fh.format(hijriDate.transform(HebrewCalendar.class))
                + "\n=> Ethiopian moment"
                + "\n(parsed from 'Amete Mihret, 2008-03-09 03:45 PM +03:00')=" + ethio
                + "\n=> French-revolutionary (2018-09-23)=" + ff.format(fcal)
                + "\n=> French-revolutionary (2018-09-22)=" + ff.format(fcal.minus(CalendarDays.ONE))
                + "\n=> Dangi year (2018)=" + dangiYear
                + "\n=> Gregorian/Dangi date=" + gregKorean.format(PlainDate.nowInSystemTime())
                + "\n=> Chinese-today=" + fc.format(ChineseCalendar.nowInSystemTime())
                ;
    }

    private static PersianCalendar createSerialized() {
        PersianCalendar pcal = PersianCalendar.of(1395, PersianMonth.DEY, 15);
        return roundtrip(pcal);
    }

    private static PersianCalendar roundtrip(Object obj) {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                byte[] data = baos.toByteArray();
                ByteArrayInputStream bais = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bais);
                Object result = ois.readObject();
                if (!result.equals(obj)) {
                    throw new AssertionError("Serialization error in Time4A.");
                }
                return PersianCalendar.class.cast(result);
            } finally {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            }
        } catch (IOException ioe) {
            throw new AssertionError(ioe);
        } catch (ClassNotFoundException cnfe) {
            throw new AssertionError(cnfe);
        }
    }

}
