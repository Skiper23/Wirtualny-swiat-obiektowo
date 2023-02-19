package po.java.zwierzeta;

import po.java.Organizm;
import po.java.Swiat;
import po.java.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Zolw extends Zwierze {
    public Zolw(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xprev = x;
        this.yprev = y;
        this.symbol = 'Z';
        this.sila = 2;
        this.inicjatywa = 1;
        this.zycie = 0;
        this.wykonal_ruch=true;
        this.numer=3;
        this.kolor= Color.ORANGE;
    }
    public Zolw(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.xprev = dane[2];
        this.yprev = dane[3];
        this.inicjatywa=dane[4];
        this.symbol = 'Z';
        this.sila = dane[5];
        this.wykonal_ruch = false;
        this.zycie = dane[6];
        this.numer=dane[7];
        this.kolor= Color.ORANGE;
    }
    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j) {
        int kierunek,xact = x, yact = y;
        Random liczba=new Random();
        Organizm org;
        kierunek = liczba.nextInt(16);
        if (kierunek < 4)
        {
            do
            {
                xact = x;
                yact = y;
                kierunek = liczba.nextInt(4);

                if (kierunek == 0)//^
                {
                    xact --;
                }
                if (kierunek == 1)//>
                {
                    yact ++;
                }
                if (kierunek == 2)//v
                {
                    xact ++;
                }
                if (kierunek == 3)//<
                {
                    yact --;
                }
                if (swiat.czyWPlanszy(swiat, xact, yact))  break;
            } while (true);
            x = xact;
            y = yact;
        }
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat) {
        if(organizm instanceof Zolw)
        {
            this.zwierzeRodzi(organizm,swiat);
        }
        if (organizm.getSila()<5)
        {
            organizm.setX(organizm.getXprev());
            organizm.setY(organizm.getYprev());
            swiat.setRaport("Z odpiera atak "+organizm.getSymbol());
            swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(), organizm);
            return;
        }
        else if (organizm.getSila() >= 5)
        {
            organizmy.remove(this);
            j--;
            swiat.setPlanszaKolizja(organizm.getX(),organizm.getY(),organizm);
            swiat.setRaport("Z ginie od "+organizm.getSymbol());
        }
    }
}
