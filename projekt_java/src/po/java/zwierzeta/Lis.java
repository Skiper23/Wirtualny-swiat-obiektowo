package po.java.zwierzeta;

import po.java.Organizm;
import po.java.Swiat;
import po.java.Zwierze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Lis extends Zwierze {
    public Lis(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xprev = x;
        this.yprev = y;
        this.symbol = 'L';
        this.sila = 3;
        this.inicjatywa = 7;
        this.zycie = 0;
        this.wykonal_ruch=true;
        this.numer=2;
        this.kolor= Color.LIGHT_GRAY;
    }
    public Lis(int []dane)
    {
        this.x = dane[0];
        this.y = dane[1];
        this.xprev = dane[2];
        this.yprev = dane[3];
        this.inicjatywa=dane[4];
        this.symbol = 'L';
        this.sila = dane[5];
        this.wykonal_ruch = false;
        this.zycie = dane[6];
        this.numer=dane[7];
        this.kolor= Color.LIGHT_GRAY;
    }

    @Override
    public void wykonajRuch(Swiat swiat, ArrayList organizmy, int j) {
        int kierunek,sum=0, xact = x, yact = y;
        Organizm org;
        Random liczba=new Random();
        if (swiat.czyWPlanszy(swiat, x + 1, y))
        {
            if (swiat.getPlansza(x + 1, y) != null)
            {
                org = swiat.getPlansza(x + 1, y);
                if(org.getSila() > sila)
                    sum++;
            }
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x - 1, y))
        {
            if (swiat.getPlansza(x - 1, y) != null)
            {
                org = swiat.getPlansza(x - 1, y);
                if (org.getSila() > sila)
                    sum++;
            }
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x, y + 1))
        {
            if (swiat.getPlansza(x, y + 1) != null)
            {
                org = swiat.getPlansza(x, y+1);
                if (org.getSila() > sila)
                    sum++;
            }
        }
        else sum++;
        if (swiat.czyWPlanszy(swiat, x, y - 1))
        {
            if (swiat.getPlansza(x, y - 1) != null)
            {
                org = swiat.getPlansza(x, y-1);
                if (org.getSila() > sila)
                    sum++;
            }
        }
        else sum++;
        if (sum == 4) return;
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
            if (swiat.czyWPlanszy(swiat, xact, yact))
            {
                org = swiat.getPlansza(xact, yact);
                if (org == null) break;
                if (org.getSila() <= sila) break;
            }

        } while (true);
        x = xact;
        y = yact;    }
}
