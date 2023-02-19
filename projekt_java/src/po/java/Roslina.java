package po.java;

import java.util.ArrayList;
import java.util.Random;

public abstract class Roslina extends Organizm{
    @Override
    public void setXprev(int x) {
        return;
    }
    @Override
    public int getXprev() {
        return -1;
    }
    @Override
    public void setYprev(int y) {
    return;
    }
    @Override
    public int getYprev() {
        return -1;
    }
    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j)
    {
        Random liczba= new Random();
        int kierunek = liczba.nextInt(4);
        int xact = x, yact = y;
        int sum = 0;
        Organizm org;
        if (kierunek == 0)
        {
            if (swiat.czyWPlanszy(swiat, x + 1, y)) { if (swiat.getPlansza(x + 1, y) != null) sum++; }
            else sum++;
            if (swiat.czyWPlanszy(swiat, x - 1, y)) { if (swiat.getPlansza(x - 1, y) != null) sum++; }
            else sum++;
            if (swiat.czyWPlanszy(swiat, x, y+1)) { if (swiat.getPlansza(x, y+1) != null) sum++; }
            else sum++;
            if (swiat.czyWPlanszy(swiat, x, y-1)) { if (swiat.getPlansza(x, y-1) != null) sum++; }
            else sum++;
            if (sum==4) return;
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
                if (org == null) break;

            } while (true);
            swiat.tworzRosline(numer,xact,yact);
            swiat.setRaport("Rozrasta "+symbol);
        }
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat)
    {
        if(organizm.getSila()>=sila)
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
