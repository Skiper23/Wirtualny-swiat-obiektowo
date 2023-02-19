package po.java.zwierzeta;

import po.java.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Antylopa extends Zwierze {
    public Antylopa(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xprev = x;
        this.yprev = y;
        this.symbol = 'A';
        this.sila = 4;
        this.inicjatywa = 4;
        this.zycie = 0;
        this.wykonal_ruch=true;
        this.numer=4;
        this.kolor= Color.GRAY;
    }
    public Antylopa(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.xprev = dane[2];
        this.yprev = dane[3];
        this.inicjatywa=dane[4];
        this.symbol = 'A';
        this.sila = dane[5];
        this.wykonal_ruch = false;
        this.zycie = dane[6];
        this.numer=dane[7];
        this.kolor= Color.GRAY;
    }
    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j) {
        int kierunek, xact = x, yact = y;
        Random liczba=new Random();
        do
        {
            xact = x;
            yact = y;
            kierunek = liczba.nextInt(4);
            if (kierunek == 0)//^
            {
                xact-=2;
            }
            if (kierunek == 1)//>
            {
                yact+=2;
            }
            if (kierunek == 2)//v
            {
                xact+=2;
            }
            if (kierunek == 3)//<
            {
                yact-=2;
            }
            if (swiat.czyWPlanszy(swiat, xact, yact))  break;
        } while (true);
        x = xact;
        y = yact;
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat) {
        Random liczba=new Random();
        int kierunek = liczba.nextInt(2), xpom, ypom;
        Organizm org;
        if(!(organizm instanceof Antylopa))
        {
            if (kierunek == 0)
            {
                xpom = x;
                ypom = y;

                do
                {
                    kierunek = liczba.nextInt(4);
                    x = xpom;
                    y = ypom;
                    if (kierunek == 0)//^
                    {
                        x--;
                    }
                    if (kierunek == 1)//>
                    {
                        y++;
                    }
                    if (kierunek == 2)//v
                    {
                        x++;
                    }
                    if (kierunek == 3)//<
                    {
                        y--;
                    }
                    if (!swiat.czyWPlanszy(swiat, x, y)) continue;
                    org = swiat.getPlansza(x, y);
                    if (org == null) break;
                } while (true);
                swiat.setPlanszaKolizja(x, y, this);
                swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(),organizm);
                swiat.setRaport("A ucieka przed "+ organizm.getSymbol());
                return;
            }
        }
        if(organizm instanceof Antylopa){
            this.zwierzeRodzi(organizm,swiat);
        }
        else if(organizm.getSila()>=sila )
        {
            swiat.setRaport(symbol+" ginie od "+organizm.getSymbol() );
            organizmy.remove(this);
            swiat.setPlanszaKolizja(x, y, organizm);
            j--;
        }
        else if(sila>organizm.getSila())
        {
            swiat.setRaport(organizm.getSymbol()+" ginie od "+symbol );
            organizmy.remove(organizm);
            swiat.setPlanszaKolizja(x, y, this);
            j--;
        }
    }
}
