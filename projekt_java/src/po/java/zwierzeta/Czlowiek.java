package po.java.zwierzeta;

import po.java.Organizm;
import po.java.Swiat;
import po.java.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Czlowiek extends Zwierze {
    private int kierunek,bron;
    public void setKierunek(int kierunek) {
        this.kierunek = kierunek;
    }
    public int getBron() {
        return bron;
    }
    public void setBron(int bron) {
        this.bron = bron;
    }
    public Czlowiek(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xprev = x;
        this.yprev = y;
        this.symbol = 'C';
        this.sila = 5;
        this.inicjatywa = 4;
        this.zycie = 0;
        this.wykonal_ruch=true;
        this.numer=5;
        this.bron=-5;
        this.kolor= Color.PINK;
    }
    public Czlowiek(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.xprev = dane[2];
        this.yprev = dane[3];
        this.inicjatywa=dane[4];
        this.symbol = 'C';
        this.sila = dane[5];
        this.wykonal_ruch = false;
        this.zycie = dane[6];
        this.numer=dane[7];
        this.bron=dane[8];
        this.kolor= Color.PINK;
    }

    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j) {
        if (kierunek == 0)//^
        {
            if(x>0)
                x--;
        }
        if (kierunek == 1)//>
        {
            if(y<swiat.getRozmiary()-1)
                y++;
        }
        if (kierunek == 2)//v
        {
            if(x<swiat.getRozmiarx()-1)
                x++;
        }
        if (kierunek == 3)//<
        {
            if(y>0)
                y--;
        }
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat) {
        if (bron > 0&&organizm instanceof Zwierze)
        {
            int kierunek,xact,yact;
            Random liczba=new Random();
                do
                {
                    xact = organizm.getX();
                    yact = organizm.getY();
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
                    if (swiat.czyWPlanszy(swiat, xact, yact)&&swiat.getPlansza(xact,yact)==null)  break;
                } while (true);

            swiat.setRaport("C odpycha "+organizm.getSymbol());
            organizm.setX(xact);
            organizm.setY(yact);
            swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(), organizm);
            return;
        }
        if (organizm.getSila()>sila)
        {
            organizmy.remove(this);
            swiat.setRaport("C ginie od "+organizm.getSymbol());
            swiat.setPlanszaKolizja(x, y, organizm);
            j--;
        }
        else
        {
            organizmy.remove(organizm);
            swiat.setRaport(organizm.getSymbol()+" ginie od C");
            j--;
        }
    }
    @Override
    public String toString()
    {
        return symbol+" "+x+" "+y+" "+xprev+" "+yprev+" "+inicjatywa+" "+sila+" "+zycie+" "+numer+" "+bron;
    }
}
