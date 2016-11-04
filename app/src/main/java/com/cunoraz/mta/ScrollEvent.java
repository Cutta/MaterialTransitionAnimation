package com.cunoraz.mta;

/**
 * Created by cuneytcarikci on 02/11/2016.
 * When list is scrolling this event will fire
 */

class ScrollEvent {
    private int margin;

    ScrollEvent(int margin) {
        this.margin = margin;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }




}
