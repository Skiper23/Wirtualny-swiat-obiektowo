package po.java.rosliny;

import po.java.Organizm;
import po.java.Roslina;
import po.java.Swiat;
import po.java.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Barszcz extends Roslina {
    public Barszcz(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = 'b';
        this.sila = 0;
        this.wykonal_ruch = false;
        this.zycie = 0;
        this.numer=4;
       // this.kolor="black";
        this.kolor= Color.BLACK;
    }
    public Barszcz(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.inicjatywa=dane[2];
        this.symbol = 'b';
        this.sila = dane[3];
        this.wykonal_ruch = false;
        this.zycie = dane[4];
        this.numer=dane[5];
        this.kolor= Color.BLACK;
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat) {
        if (organizm instanceof Zwierze)
        {
            swiat.setRaport(organizm.getSymbol()+" ginie od b ");
            swiat.setPlansza(organizm.getX(), organizm.getY());
            organizmy.remove(organizm);
            j--;
        }
    }
    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j) {
        Random liczba= new Random();
        int kierunek = liczba.nextInt(4);
        int xact = x, yact = y;
        int sum = 0;
        Organizm org;
        if (swiat.czyWPlanszy(swiat, x + 1, y)) {
            if (swiat.getPlansza(x + 1, y) != null)
            {
                kolizja(swiat.getPlansza(x+1,y ), organizmy,j,swiat);
                sum++;
            }
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x - 1, y))
        {
            if (swiat.getPlansza(x - 1, y) != null) {
                kolizja(swiat.getPlansza(x-1,y ), organizmy,j,swiat);
                sum++;
            }
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x, y+1)) {
            if (swiat.getPlansza(x, y+1) != null) {
                kolizja(swiat.getPlansza(x,y+1 ), organizmy,j,swiat);
                sum++;
            }
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x, y-1)) {
            if (swiat.getPlansza(x, y-1) != null) {
                kolizja(swiat.getPlansza(x,y-1 ), organizmy,j,swiat);
                sum++;
            }
        }
        else sum++;
        if (sum==4) return;

        if (kierunek == 0)
        {
            do
            {
                xact = x;
                yact = y;
                kierunek = liczba.nextInt(4);
                if (kierunek == 0)//^
                {
                    xact--;
                }
                if (kierunek == 1)//>
                {
                    yact++;
                }
                if (kierunek == 2)//v
                {
                    xact++;
                }
                if (kierunek == 3)//<
                {
                    yact--;
                }
                if (!swiat.czyWPlanszy(swiat, xact, yact)) continue;
                org = swiat.getPlansza(xact, yact);
                if (org == null) {
                    if (swiat.czyWPlanszy(swiat,x+1,y))
                    {
                        if (swiat.getPlansza(x + 1,y) != null)
                        {
                            kolizja(swiat.getPlansza(x + 1,y), organizmy,j,swiat);
                        }
                    }
                    if (swiat.czyWPlanszy(swiat, x - 1, y))
                    {
                        if (swiat.getPlansza(x - 1,y) != null)
                        {
                           kolizja(swiat.getPlansza(x - 1,y), organizmy,j,swiat);
                        }
                    }
                    if (swiat.czyWPlanszy(swiat, x, y+1))
                    {
                        if (swiat.getPlansza(x,y + 1) != null)
                        {
                            kolizja(swiat.getPlansza(x,y + 1),organizmy,j,swiat);
                        }
                    }
                    if (swiat.czyWPlanszy(swiat, x, y-1))
                    {
                        if (swiat.getPlansza(x,y - 1) != null)
                        {
                            kolizja(swiat.getPlansza(x,y - 1), organizmy,j,swiat);
                        }
                    }
                    break;
                }
            } while (true);
            swiat.tworzRosline(numer,xact,yact);
            swiat.setRaport("Rozrasta "+symbol);
        }
    }
}
