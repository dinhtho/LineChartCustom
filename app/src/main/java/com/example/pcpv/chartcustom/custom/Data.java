package com.example.pcpv.chartcustom.custom;

import com.github.mikephil.charting.data.Entry;

/**
 * Created by PCPV on 11/30/2017.
 */

public class Data {
    private Entry yVals1;
    private String lable;

    public Data(Entry yVals1, String lable) {
        this.yVals1 = yVals1;
        this.lable = lable;
    }

    public Entry getyVals1() {
        return yVals1;
    }

    public String getLable() {
        return lable;
    }

    public void setyVals1(Entry yVals1) {
        this.yVals1 = yVals1;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
