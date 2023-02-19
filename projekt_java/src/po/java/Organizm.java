package po.java;

import java.awt.*;
import java.util.ArrayList;

public abstract class Organizm {
    protected char symbol;
    protected int x,y,sila,zycie,inicjatywa,numer;
    protected boolean wykonal_ruch;
    protected Color kolor;

    public Color getKolor() {
        return kolor;
    }
    public char getSymbol() {
        return symbol;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public int getZycie() {
        return zycie;
    }

    public void setZycie(int zycie) {
        this.zycie = zycie;
    }
    public int getInicjatywa() {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }
    public abstract void setXprev(int x);
    public abstract int getXprev();

    public abstract void setYprev(int y);

    public abstract int getYprev();
    public void setWykonalRuch(boolean wykonal_ruch) {
        this.wykonal_ruch = wykonal_ruch;
    }
    public abstract void wykonajRuch(Swiat swiat, ArrayList organizmy, int j);

    public abstract void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat);
    @Override
    public String toString()
    {
        return symbol+" "+x+" "+y+" "+inicjatywa+" "+sila+" "+zycie+" "+numer;
    }

}
