package po.java.zwierzeta;

import po.java.Zwierze;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(int x,int y) {
        this.x=x;
        this.y=y;
        this.xprev=x;
        this.yprev=y;
        this.symbol='O';
        this.sila=4;
        this.inicjatywa=4;
        this.zycie=0;
        this.wykonal_ruch=true;
        this.numer=1;
        this.kolor= Color.CYAN;
    }
    public Owca(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.xprev = dane[2];
        this.yprev = dane[3];
        this.inicjatywa=dane[4];
        this.symbol = 'O';
        this.sila = dane[5];
        this.wykonal_ruch = false;
        this.zycie = dane[6];
        this.numer=dane[7];
        this.kolor= Color.CYAN;
    }
}
