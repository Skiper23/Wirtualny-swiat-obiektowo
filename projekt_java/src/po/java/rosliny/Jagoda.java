package po.java.rosliny;

import po.java.Roslina;

import java.awt.*;

public class Jagoda extends Roslina {
    public Jagoda(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = 'j';
        this.sila = 0;
        this.wykonal_ruch = false;
        this.zycie = 0;
        this.numer=3;
        this.kolor= Color.BLUE;
    }
    public Jagoda(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.inicjatywa=dane[2];
        this.symbol = 'j';
        this.sila = dane[3];
        this.wykonal_ruch = false;
        this.zycie = dane[4];
        this.numer=dane[5];
        this.kolor= Color.BLUE;
    }
}
