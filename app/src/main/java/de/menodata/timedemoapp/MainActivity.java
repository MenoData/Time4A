package de.menodata.timedemoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
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
                            try {
                                tv.setText(TimeDemoApp.getTimeInfo(context));
                            } catch (Throwable th) {
                                Log.e("TIME4A-ERROR-ON-CLICK", th.getMessage(), th);
                                throw th;
                            }
                        }
                    }
            );
        } catch (Throwable th) {
            Log.e("TIME4A-ERROR-ON-CREATE", th.getMessage(), th);
            throw th;
        }
    }

    protected void onResume() {
        try {
            super.onResume();
            TextView tv = (TextView) this.findViewById(R.id.time_display);
            tv.setText(TimeDemoApp.getTimeInfo(this.getApplication()));
        } catch (Throwable th) {
            Log.e("TIME4A-ERROR-ON-RESUME", th.getMessage(), th);
            throw th;
        }
    }

}
