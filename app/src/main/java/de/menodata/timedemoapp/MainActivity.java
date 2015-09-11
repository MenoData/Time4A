package de.menodata.timedemoapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.time4j.ClockUnit;
import net.time4j.Duration;
import net.time4j.Moment;
import net.time4j.PrettyTime;
import net.time4j.SystemClock;
import net.time4j.Weekmodel;
import net.time4j.calendar.HijriCalendar;
import net.time4j.engine.StartOfDay;
import net.time4j.format.TextWidth;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;
import net.time4j.tz.Timezone;
import net.time4j.tz.olson.EUROPE;

import java.util.Locale;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Time-Demo");
        final TextView tv = (TextView) this.findViewById(R.id.time_display);
        tv.setText("\n\n\nHello Android =>\n" + getTimeInfo());

        Button button = (Button) this.findViewById(R.id.refresh_button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv.setText("\n\n\nHello again Android =>\n" + getTimeInfo());
                    }
                }
        );
    }

    protected void onResume() {
        super.onResume();
        TextView tv = (TextView) this.findViewById(R.id.time_display);
        tv.setText("\n\n\nResumed Android =>\n" + getTimeInfo());
    }

    private static String getTimeInfo() {
        // PlainDate.of(2015, 0); // exception test observing debug output
        Duration<?> dur = Duration.of(337540, ClockUnit.SECONDS).with(Duration.STD_CLOCK_PERIOD);
        String formattedDuration = PrettyTime.of(Locale.FRANCE).print(dur, TextWidth.WIDE);

        ChronoFormatter<HijriCalendar> hf =
                ChronoFormatter.setUp(HijriCalendar.class, Locale.ENGLISH)
                .addPattern("MM/dd/yyyy", PatternType.NON_ISO_DATE).build();
        HijriCalendar hijriDate =
                SystemClock.inLocalView().now(
                        HijriCalendar.family(), HijriCalendar.VARIANT_ICU4J, StartOfDay.EVENING
                ).toDate();

        Moment moment = SystemClock.currentMoment();
        return "\tCurrent time (UTC): " + moment.toString()
                + "\n\tSystem timezone: " + Timezone.ofSystem().getID().canonical()
                + "\n\tLocal timestamp: " + moment.toLocalTimestamp()
                + "\n\tNext leap second: " + moment.with(Moment.nextLeapSecond())
                + "\n\tWeek model: " + Weekmodel.ofSystem()
                + "\n\tAktuelle Zeit: "
                + ChronoFormatter.ofMomentPattern(
                    "d. MMMM uuuu GGGG HH:mm z",
                    PatternType.CLDR,
                    Locale.GERMAN,
                    EUROPE.BERLIN
                ).withAlternativeEraNames().format(moment)
                + "\n\tTZDB-version: " + Timezone.getVersion("TZDB")
                + "\n\tZONE-PROVIDERS: " + Timezone.getProviderInfo()
                + "\n\tDuration=" + formattedDuration
                + "\n\tHijri-today=" + hijriDate
                + "\n\tHijri-formatted=" + hf.format(hijriDate);
    }

}
