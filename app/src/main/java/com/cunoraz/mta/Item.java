package com.cunoraz.mta;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cuneytcarikci on 27/10/2016.
 */

public class Item implements Parcelable {
    private String text;
    private String color;

    public Item(String text,String color){
        this.text = text;
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.color);
    }

    protected Item(Parcel in) {
        this.text = in.readString();
        this.color = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
