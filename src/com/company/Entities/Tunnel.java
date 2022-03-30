package com.company.Entities;

public class Tunnel extends Workspace{

    int depth;
    int width;
    int length;
    Boolean covered;

    public Tunnel() {
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Boolean getCovered() {
        return covered;
    }

    public void setCovered(Boolean covered) {
        this.covered = covered;
    }

    public Tunnel(int depth, int width, int length, Boolean covered) {
        this.depth = depth;
        this.width = width;
        this.length = length;
        this.covered = covered;
    }

    @Override
    public float getVolume() {
        return 0;
    }
}
