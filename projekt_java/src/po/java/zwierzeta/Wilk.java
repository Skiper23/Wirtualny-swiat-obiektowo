package po.java.zwierzeta;

import po.java.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xprev = x;
        this.yprev = y;
        this.symbol = 'W';
        this.sila = 9;
        this.inicjatywa = 5;
        this.zycie = 0;
        this.wykonal_ruch=true;
        this.numer=0;
        this.kolor= Color.DARK_GRAY;
    }
    public Wilk(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.xprev = dane[2];
        this.yprev = dane[3];
        this.inicjatywa=dane[4];
        this.symbol = 'W';
        this.sila = dane[5];
        this.wykonal_ruch = false;
        this.zycie = dane[6];
        this.numer=dane[7];
        this.kolor= Color.DARK_GRAY;
    }
}
