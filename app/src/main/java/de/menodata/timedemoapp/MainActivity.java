package de.menodata.timedemoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.time4j.ClockUnit;
import net.time4j.Duration;
import net.time4j.Moment;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.PrettyTime;
import net.time4j.SystemClock;
import net.time4j.Weekmodel;
import net.time4j.calendar.EthiopianCalendar;
import net.time4j.calendar.HijriCalendar;
import net.time4j.calendar.PersianCalendar;
import net.time4j.engine.StartOfDay;
import net.time4j.format.CalendarText;
import net.time4j.format.DisplayMode;
import net.time4j.format.TextWidth;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;
import net.time4j.tz.Timezone;
import net.time4j.tz.olson.EUROPE;

import java.text.ParseException;
import java.util.Locale;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Time-Demo");
        final TextView tv = (TextView) this.findViewById(R.id.time_display);
        final Context context = this.getApplication();

        Button button = (Button) this.findViewById(R.id.refresh_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv.setText(getTimeInfo(context));
                    }
                }
        );
    }

    protected void onResume() {
        super.onResume();
        TextView tv = (TextView) this.findViewById(R.id.time_display);
        tv.setText(getTimeInfo(this.getApplication()));
    }

    private static String getTimeInfo(Context context) {

        Duration<?> dur = Duration.of(337540, ClockUnit.SECONDS).with(Duration.STD_CLOCK_PERIOD);
        String formattedDuration = PrettyTime.of(Locale.FRANCE).print(dur, TextWidth.WIDE);

        ChronoFormatter<PersianCalendar> pf =
                ChronoFormatter.setUp(PersianCalendar.class, Locale.ENGLISH)
                        .addPattern("MMMM d, yyyy G", PatternType.NON_ISO_DATE).build();
        ChronoFormatter<HijriCalendar> hf =
                ChronoFormatter.setUp(HijriCalendar.class, Locale.ENGLISH)
                .addPattern("MM/dd/yyyy", PatternType.NON_ISO_DATE).build();
        HijriCalendar hijriDate =
                SystemClock.inLocalView().now(
                        HijriCalendar.family(), HijriCalendar.VARIANT_ICU4J, StartOfDay.EVENING
                ).toDate();
        String germanTime =
                PlainTime.formatter(DisplayMode.FULL, Locale.GERMANY).format(
                        SystemClock.inLocalView().now().toTime());
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

        Moment moment = SystemClock.currentMoment();

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
                + "\n"
                + "\n=> Duration=" + formattedDuration
                + "\n"
                + "\n=> Hijri-toString=" + hijriDate
                + "\n=> Hijri-formatted=" + hf.format(hijriDate)
                + "\n=> Persian-today=" + pf.format(hijriDate.transform(PersianCalendar.class))
                + "\n=> Ethiopian moment"
                + "\n(parsed from 'Amete Mihret, 2008-03-09 03:45 PM +03:00')=" + ethio;
    }

}
