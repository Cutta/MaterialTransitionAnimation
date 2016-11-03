package com.cunoraz.mta;

/**
 * Created by cuneytcarikci on 02/11/2016.
 * Scrolll oldugunda gondermemiz gereken margin degerine kaarr verdikten sonra bu event fırlatılacak
 */

class ScrollEvent {
    ScrollEvent(int margin,int position) {
        this.margin = margin;
        this.position = position;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    private int margin;

    public int getPosition() {
        return position;
    }

    private int position;

}
