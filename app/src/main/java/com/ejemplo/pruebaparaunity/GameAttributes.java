package com.ejemplo.pruebaparaunity;

import com.unity3d.player.UnityPlayer;

public class GameAttributes {
    int gifts, level;
    float time;
    String iduser;

    public GameAttributes(int gifts, String iduser, int level, float time) {
        this.gifts = gifts;
        this.level = level;
        this.iduser = iduser;
        this.time = time;
    }

    public GameAttributes() {}

    public int getGifts() { return gifts; }

    public void setGifts(int gifts) {this.gifts = gifts;}

    public int getLevel() {return level;}

    public void setLevel(int level) {this.level = level;}

    public float getTime() {return time;}

    public void setTime(float time) {this.time = time;}

    @Override
    public String toString() {
        return "GameAttributes{" +
                "time=" + time +
                ", level=" + level +
                ", gifts=" + gifts +
                '}';
    }
}
