package po.java.rosliny;

import po.java.Roslina;

import java.awt.*;

public class Trawa extends Roslina {
    public Trawa (int x, int y)
    {
        this.x = x;
        this.y = y;
        this.symbol = 't';
        this.sila = 0;
        this.wykonal_ruch = false;
        this.zycie = 0;
        this.numer=0;
        this.kolor= Color.GREEN;
    }
    public Trawa(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.inicjatywa=dane[2];
        this.symbol = 't';
        this.sila = dane[3];
        this.wykonal_ruch = false;
        this.zycie = dane[4];
        this.numer=dane[5];
        this.kolor= Color.GREEN;
    }
}
