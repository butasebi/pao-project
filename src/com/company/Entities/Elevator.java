package com.company.Entities;

public class Elevator extends Workspace{

    int max_height;
    int length;
    int width;

    public Elevator() {
    }

    public Elevator(int max_height, int length, int width) {
        this.max_height = max_height;
        this.length = length;
        this.width = width;
    }

    public int getMax_height() {
        return max_height;
    }

    public void setMax_height(int max_height) {
        this.max_height = max_height;
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
        return this.length * this.max_height * this.width;
    }
}
