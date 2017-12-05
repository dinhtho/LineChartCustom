package com.example.pcpv.chartcustom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.example.pcpv.chartcustom.custom.Data;
import com.example.pcpv.chartcustom.custom.markercustom.MarkerCustom;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LineChartActivity2";
    private ArrayList<Data> dataList = new ArrayList<>();
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mChart =  findViewById(R.id.chart1);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // draw lines for highlight or not
        mChart.setHighlightPerTapEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);

        // add data
        setData(50, 200, 600);

        // allow for scroll horizontally
        mChart.setVisibleXRangeMaximum(30); // allow 30 values to be displayed at once on the x-axis, not more
//        mChart.moveViewToX(10);

        // allow double taps for zooming
        mChart.setDoubleTapToZoomEnabled(true);

        // hide legend
        mChart.getLegend().setForm(Legend.LegendForm.NONE);

        // custom marker
        MarkerCustom mv = new MarkerCustom(this, R.layout.marker_custom);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv);



        // configure X row
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.GRAY);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        final HashMap<Integer, String> numMap = new HashMap<>();
        for (Data data : dataList) {
            numMap.put((int)data.getyVals1().getX(), data.getLable());
        }
        xAxis.setLabelCount(50, true);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return numMap.get((int) value);
            }
        });

        // configure left column
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.GRAY);
        leftAxis.setAxisMaximum(800f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.enableGridDashedLine(5f, 10f, 100f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setGranularityEnabled(true);

        // hide right column
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

    }


    private void setData(int count, int lowerLimit, int upperLimit) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        int year = 2013;

        for (int i = 0; i < count; i++) {
            Random r = new Random();
            int ra = r.nextInt(upperLimit - lowerLimit) + lowerLimit;
            if (i % 6 == 0) {
                dataList.add(new Data(new Entry(i, ra), String.valueOf(year)));
                year++;
            } else {
                dataList.add(new Data(new Entry(i, ra), ""));
            }
            yVals1.add(dataList.get(i).getyVals1());
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals1, "");


        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(Color.parseColor("#f28911"));
        set1.setLineWidth(4f);
        set1.setDrawCircles(true);
        set1.setCircleRadius(2f);
        set1.setDrawCircleHole(false);
        set1.setCircleColor(Color.parseColor("#f28911"));

//        set1.setFillAlpha(65);
//        set1.setFillColor(Color.parseColor("#f28911"));
//        set1.setHighLightColor(Color.rgb(244, 117, 117));
//        set1.setDrawCircleHole(false);


        //set1.setFillFormatter(new MyFillFormatter(0f));
        //set1.setDrawHorizontalHighlightIndicator(false);
        //set1.setVisible(false);
        //set1.setCircleHoleColor(Color.WHITE);


        // create a data object with the datasets
        LineData data = new LineData(set1);
        data.setDrawValues(false);
        // set data
        mChart.setData(data);

    }

}
