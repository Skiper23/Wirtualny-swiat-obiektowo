package po.java.rosliny;

import po.java.Organizm;
import po.java.Roslina;
import po.java.Swiat;

import java.awt.*;
import java.util.ArrayList;

public class Guarana extends Roslina {
    public Guarana(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = 'g';
        this.sila = 0;
        this.wykonal_ruch = false;
        this.zycie = 0;
        this.numer=2;
        this.kolor= Color.RED;
    }
    public Guarana(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.inicjatywa=dane[2];
        this.symbol = 'g';
        this.sila = dane[3];
        this.wykonal_ruch = false;
        this.zycie = dane[4];
        this.numer=dane[5];
        this.kolor= Color.RED;
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat)
    {
        organizm.setSila(organizm.getSila()+3);
        swiat.setRaport(symbol+" ginie od "+ organizm.getSymbol());
        organizmy.remove(this);
        swiat.setPlanszaKolizja(x,y,organizm);
    }
}
