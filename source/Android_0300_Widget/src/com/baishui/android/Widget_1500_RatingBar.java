package com.baishui.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;

public class Widget_1500_RatingBar extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingbar);
        RatingBar ratingBar = (RatingBar) this.findViewById(R.id.ratingbarId);

        ratingBar.setOnRatingBarChangeListener(new RatingBarListener());
    }

    private class RatingBarListener implements
            RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating,
                boolean fromUser) {
            if (fromUser) {
                System.out.println("chang--->>> " + rating);
            }
        }
    }
}