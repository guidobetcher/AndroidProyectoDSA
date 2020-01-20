package com.ejemplo.pruebaparaunity;

public class Jugador {
    private float time;
    private int level;
    private int gifts;
    private int renos;

    public int getRenos() {
        return renos;
    }

    public void setRenos(int renos) {
        this.renos = renos;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGifts() {
        return gifts;
    }

    public void setGifts(int gifts) {
        this.gifts = gifts;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }
}
