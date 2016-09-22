package com.vnapnic.myvib.model;

/**
 * Created by Nankai on 9/6/2016.
 */
public class PayAnyone {

    private int style ;
    private String title ;
    private String content ;

    public PayAnyone(int style, String title, String content) {
        this.style = style;
        this.title = title;
        this.content = content;
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
