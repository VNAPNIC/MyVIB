package com.vnapnic.myvib.model;

/**
 * Created by hnc on 16/08/2016.
 */
public class CurrentVsChipModel {

    private int style ;
    private String title ;
    private String content ;
    private String txt_current;

    public CurrentVsChipModel(int style, String title, String content,String txt_current) {
        this.style = style;
        this.title = title;
        this.content = content;
        this.txt_current = txt_current;
    }

    public String getTxt_current() {
        return txt_current;
    }

    public int getStyle() {
        return style;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
