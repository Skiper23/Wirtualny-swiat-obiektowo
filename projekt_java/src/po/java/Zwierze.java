package po.java;

import java.util.ArrayList;
import java.util.Random;

public abstract class Zwierze extends Organizm {
protected
    int xprev,yprev;

    @Override
    public int getXprev() {
        return xprev;
    }
    @Override
    public void setXprev(int xprev) {
        this.xprev = xprev;
    }
    @Override
    public int getYprev() {
        return yprev;
    }
    @Override
    public void setYprev(int yprev) {
        this.yprev = yprev;
    }
    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j)
    {
        int xact=x,yact=y;
        Random liczba=new Random();
        int kierunek;
        do {
            xact=x;
            yact=y;
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
            if (swiat.czyWPlanszy(swiat, xact, yact))  break;
        }while(true);
        x=xact;
        y=yact;
    }
    public void zwierzeRodzi(Organizm organizm, Swiat swiat)
    {
        int kierunek, xact = x, yact = y, sum = 0;
        Random liczba=new Random();
        Organizm org;
        int x1 = this.getX(), y1 = this.getY();
        organizm.setX(organizm.getXprev());
        organizm.setY(organizm.getYprev());
        organizm.setWykonalRuch(true);
        this.setWykonalRuch(true);
        swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(), organizm);
        if (swiat.czyWPlanszy(swiat, x1 + 1, y1))
        {
            if (swiat.getPlansza(x1 + 1, y1) != null)
                sum++;
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x1 - 1, y1))
        {
            if (swiat.getPlansza(x1 - 1, y1) != null)
                sum++;
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x1, y1 + 1))
        {
            if (swiat.getPlansza(x1, y1 + 1) != null)
                sum++;
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x1, y - 1))
        {
            if (swiat.getPlansza(x1, y1 - 1) != null)
                sum++;
        }
        else sum++;
        if (sum == 4) return;
        do
        {
            xact = x1;
            yact = y1;
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
        swiat.tworzZwierze(numer, xact, yact);
        swiat.setRaport("Rodzi sie "+symbol);
    }
    @Override
    public void kolizja(Organizm organizm, ArrayList organizmy, int j, Swiat swiat)
    {
        if(organizm.getSymbol()==symbol){
            zwierzeRodzi(organizm,swiat);
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
    @Override
    public String toString()
    {
        return symbol+" "+x+" "+y+" "+xprev+" "+yprev+" "+inicjatywa+" "+sila+" "+zycie+" "+numer;
    }
}