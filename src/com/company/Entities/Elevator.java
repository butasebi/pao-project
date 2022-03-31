package com.company.Entities;

public class Elevator extends Workspace{

    int maxHeight;
    int length;
    int width;

    public Elevator() {
    }

    public Elevator(int maxHeight, int length, int width) {
        this.maxHeight = maxHeight;
        this.length = length;
        this.width = width;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public float getVolume() {
        return this.length * this.maxHeight * this.width;
    }
}
