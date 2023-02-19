package po.java;

import po.java.rosliny.*;
import po.java.zwierzeta.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Swiat {
    private
    Organizm[][] plansza;
    ArrayList<Organizm> organizmy= new ArrayList<Organizm>();



    public ArrayList <String> raport= new ArrayList<String>();

    public String getRaport(int i) {
        return raport.get(i);
    }

    public void setRaport(String tekst) {
        this.raport.add(tekst);
    }
    public void setRaportClear() {
        this.raport.clear();
    }
    public int getRaportSize() {
        return raport.size();
    }

    int rozmiarx,rozmiary;
    public Swiat(int x,int y)
    {
        this.rozmiarx=x;
        this.rozmiary=y;
        this.plansza=new Organizm[x][y];
        for(int i=0; i<rozmiarx; i++) {
            for (int j = 0; j < rozmiary; j++) {
                plansza[i][j]=null;
            }
        }
    }

    public Organizm getPlansza(int x, int y) {
        return plansza[x][y];
    }
    public void setPlanszaKolizja(int x, int y, Organizm organizm)
    {
        this.plansza[x][y] = organizm;
        organizm.setWykonalRuch(true);
    }
    public void setPlansza(int x,int y, Organizm organizm) {
        this.plansza[x][y] = organizm;
        this.organizmy.add(organizm);
    }
    public void setPlansza(int x, int y)
    {
        this.plansza[x][y]=null;
    }

    public int getRozmiarx() {
        return rozmiarx;
    }

    public int getRozmiary() {
        return rozmiary;
    }

    public ArrayList<Organizm> getOrganizmy() {
        return organizmy;
    }

    public boolean czyWPlanszy(Swiat swiat, int x, int y) {
        if (x < 0 || y < 0 || x >=rozmiarx  || y >= rozmiary) return false;
        return true;
    }
    public void tworzZwierze(int symbol , int x, int y)
    {
        if (symbol == 0)
        {
            Wilk wilk = new Wilk(x, y);
            setPlansza(x, y, wilk);
        }
        else if (symbol == 1)
        {
            Owca owca = new Owca(x, y);
            setPlansza(x, y, owca);
        }
        else if (symbol == 2)
        {
            Lis lis = new Lis(x, y);
            setPlansza(x, y, lis);
        }
        else if (symbol == 3)
        {
            Zolw zolw = new Zolw(x, y);
            setPlansza(x, y, zolw);
        }
        else if (symbol == 4)
        {
            Antylopa antylopa = new Antylopa(x, y);
            setPlansza(x, y, antylopa);
        }
        else if (symbol == 5)
        {
            Czlowiek czlowiek = new Czlowiek(x, y);
            setPlansza(x, y, czlowiek);
        }
    }
    public void tworzRosline(int symbol , int x, int y)
    {
        if (symbol == 0)
        {
            Trawa trawa = new Trawa(x, y);
            setPlansza(x, y, trawa);
        }
        else if (symbol == 1)
        {
            Mlecz mlecz = new Mlecz(x, y);
            setPlansza(x, y, mlecz);
        }
        else if (symbol == 2)
        {
            Guarana guarana = new Guarana(x, y);
            setPlansza(x, y, guarana);
        }
        else if (symbol == 3)
        {
            Jagoda jagoda = new Jagoda(x, y);
            setPlansza(x, y, jagoda);
        }
        else if (symbol == 4)
        {
            Barszcz barszcz = new Barszcz(x, y);
            setPlansza(x, y, barszcz);
        }
    }
    public void tworzZwierzeOdczyt(char symbol , int []dane)
    {
        if (symbol == 'W')
        {
            Wilk wilk = new Wilk(dane);
            setPlansza(dane[0], dane[1], wilk);
        }
        else if (symbol == 'O')
        {
            Owca owca = new Owca(dane);
            setPlansza(dane[0], dane[1], owca);
        }
        else if (symbol == 'L')
        {
            Lis lis = new Lis(dane);
            setPlansza(dane[0], dane[1], lis);
        }
        else if (symbol == 'Z')
        {
            Zolw zolw = new Zolw(dane);
            setPlansza(dane[0], dane[1], zolw);
        }
        else if (symbol == 'A')
        {
            Antylopa antylopa = new Antylopa(dane);
            setPlansza(dane[0], dane[1], antylopa);
        }
        else if (symbol == 'C')
        {
            Czlowiek czlowiek = new Czlowiek(dane);
            setPlansza(dane[0], dane[1], czlowiek);
        }
    }
    public void tworzRoslineOdczyt(char symbol , int []dane)
    {
        if (symbol == 't')
        {
            Trawa trawa = new Trawa(dane);
            setPlansza(dane[0], dane[1], trawa);
        }
        else if (symbol == 'm')
        {
            Mlecz mlecz = new Mlecz(dane);
            setPlansza(dane[0], dane[1], mlecz);
        }
        else if (symbol == 'g')
        {
            Guarana guarana = new Guarana(dane);
            setPlansza(dane[0], dane[1], guarana);
        }
        else if (symbol == 'j')
        {
            Jagoda jagoda = new Jagoda(dane);
            setPlansza(dane[0], dane[1], jagoda);
        }
        else if (symbol =='b')
        {
            Barszcz barszcz = new Barszcz(dane);
            setPlansza(dane[0], dane[1], barszcz);
        }
    }
    public void wyoknajTure()
    {
        int k=0,xprev,yprev;
        Organizm tmp,obronca;

        Comparator<Organizm> comparator= (o1,o2)->
        {
            if(o1.getInicjatywa()==o2.getInicjatywa())
            {
                return o2.getZycie()-o1.getZycie();
            }
            else return o2.getInicjatywa()-o1.getInicjatywa();
        };
        Collections.sort(organizmy,comparator);

        for( int j=0; j<organizmy.size(); j++)
        {
            tmp = organizmy.get(j);
            if(tmp.getZycie()==0) break;
            if(tmp.wykonal_ruch==true) continue;
            tmp.setWykonalRuch(true);
            xprev = tmp.getX();
            yprev = tmp.getY();
            tmp.setXprev(xprev);
            tmp.setYprev(yprev);
            plansza[tmp.getX()][tmp.getY()] =null;

            tmp.wykonajRuch(this,organizmy,j);

            if (plansza[tmp.getX()][tmp.getY()] != null)
            {
                obronca = plansza[tmp.getX()][tmp.getY()];
                obronca.kolizja(tmp, organizmy, j, this);
            }
            else
                plansza[tmp.getX()][tmp.getY()] = tmp;
        }
        for (int j = 0; j < organizmy.size(); j++)
        {
            organizmy.get(j).setWykonalRuch(false);
            organizmy.get(j).setZycie(organizmy.get(j).getZycie() + 1);
        }
    }

    public void generujSwiat(Okienko okno)
    {
        Random liczba=new Random();
        int x,y,organizm;
        do {
            x = liczba.nextInt(getRozmiarx());
            y = liczba.nextInt(getRozmiary());
        } while(getPlansza(x,y)!=null);
        tworzZwierze(5, x, y);
        for(int i=0; i<getRozmiarx()-1; i++)
        {
            organizm=liczba.nextInt(5);
            do {
                x = liczba.nextInt(getRozmiarx());
                y = liczba.nextInt(getRozmiary());
            } while(getPlansza(x,y)!=null);
            tworzZwierze(organizm,x,y);
        }
        for(int i=0; i<getRozmiary()-1; i++)
        {
            organizm=liczba.nextInt(5);
            do {
                x = liczba.nextInt(getRozmiarx());
                y = liczba.nextInt(getRozmiary());
            } while(getPlansza(x,y)!=null);
            tworzRosline(organizm,x,y);
        }
        okno.createOkienko(this);
        wyoknajTure();
    }
    public void zapisDoPliku() throws FileNotFoundException {
        PrintWriter zapis= new PrintWriter("organizmy.txt");
        zapis.println(rozmiarx+" "+rozmiary);
        for(Organizm organizm: organizmy)
        {
            zapis.println(organizm.toString());
        }
        zapis.close();
    }
    public void odczytZPliku(File file, Scanner in) throws FileNotFoundException {
        String zdanie,wyrazy[];
        char znak;
        organizmy.clear();
        while(in.hasNextLine())
        {
            zdanie=in.nextLine();
            wyrazy=zdanie.split(" ");
            znak=wyrazy[0].charAt(0);

            int []dane= new int[ wyrazy.length-1];

            for(int i=1; i<wyrazy.length; i++)
            {
                dane[i-1]=Integer.parseInt(wyrazy[i]);
            }

            if(znak>='A'&&znak<='Z')
                tworzZwierzeOdczyt(znak,dane);
            else
                tworzRoslineOdczyt(znak,dane);
        }
    }
}
