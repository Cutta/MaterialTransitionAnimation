package com.cunoraz.mta;

/**
 * Created by cuneytcarikci on 02/11/2016.
 */

class ScrollEvent {
    ScrollEvent(int margin) {
        this.margin = margin;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    private int margin;

}
