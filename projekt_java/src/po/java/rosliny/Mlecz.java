package po.java.rosliny;

import po.java.Organizm;
import po.java.Roslina;
import po.java.Swiat;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Mlecz extends Roslina {
    public Mlecz(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = 'm';
        this.sila = 0;
        this.wykonal_ruch = false;
        this.zycie = 0;
        this.numer=1;
        this.kolor= Color.YELLOW;
    }
    public Mlecz(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.inicjatywa=dane[2];
        this.symbol = 'm';
        this.sila = dane[3];
        this.wykonal_ruch = false;
        this.zycie = dane[4];
        this.numer=dane[5];
        this.kolor= Color.YELLOW;
    }

    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j) {
        Random liczba= new Random();
        int kierunek,sum=0,xact = x, yact = y;
        Organizm org;
        for(int i=0; i<3; i++) {
            sum=0;
           kierunek= liczba.nextInt(4);
           if(kierunek==0) {
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
    }
}
