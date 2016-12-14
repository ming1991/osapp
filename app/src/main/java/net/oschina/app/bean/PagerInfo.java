package net.oschina.app.bean;

import android.os.Bundle;

public class PagerInfo {
    public String title;
    public Bundle bundle;
    public Class clz;

    public PagerInfo(String title, Bundle bundle, Class clz) {
        this.title = title;
        this.bundle = bundle;
        this.clz = clz;
    }
}