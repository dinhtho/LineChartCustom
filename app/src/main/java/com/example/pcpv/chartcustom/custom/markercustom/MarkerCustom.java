package com.example.pcpv.chartcustom.custom.markercustom;

import android.content.Context;
import android.widget.TextView;

import com.example.pcpv.chartcustom.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

/**
 * Created by PCPV on 12/01/2017.
 */

public class MarkerCustom extends MarkerView {
    private TextView tvAmount;
    private TextView tvDate;

    public MarkerCustom(Context context, int layoutResource) {
        super(context, layoutResource);

        tvAmount = (TextView) findViewById(R.id.tvAmount);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setText("Tháng 7 2016");
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvAmount.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {

            tvAmount.setText("" + Utils.formatNumber(e.getY(), 0, true));
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
