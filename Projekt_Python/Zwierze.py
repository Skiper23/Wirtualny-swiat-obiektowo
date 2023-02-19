import random
from Organizm import Organizm


class Zwierze (Organizm):
    def wykonajRuch(self,swiat, organizmy, j):
        xact=self._x
        yact=self._y
        while True:
            xact=self._x
            yact=self._y
            kierunek = random.randint (0,3)
            if kierunek == 0:#//^
                xact-=1
            if kierunek == 1:#//>
                yact+=1
            if kierunek == 2:#//v
                xact+=1
            if kierunek == 3:#//<
                yact-=1
            
            if swiat.czyWPlanszy(xact, yact):  break

        self._x=xact
        self._y=yact
    
    def zwierzeRodzi(self,organizm,swiat):
        sum = 0
        x1 = self._x
        y1 = self._y
        organizm.setX(organizm.getXprev())
        organizm.setY(organizm.getYprev())
        organizm.setWykonalRuch(True)
        self.setWykonalRuch(True)
        swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(), organizm)
        if swiat.czyWPlanszy(x1 + 1, y1):
            if swiat.getPlansza(x1 + 1, y1) != None:
                sum+=1
        else: sum+=1

        if swiat.czyWPlanszy(x1 - 1, y1):
            if swiat.getPlansza(x1 - 1, y1) != None:
                sum+=1
        else: sum+=1

        if swiat.czyWPlanszy(x1, y1 + 1):
            if swiat.getPlansza(x1, y1 + 1) != None:
                sum+=1
        else: sum+=1

        if swiat.czyWPlanszy(x1, y1 - 1):
            if swiat.getPlansza(x1, y1 - 1) != None:
                sum+=1
        else: sum+=1

        if sum == 4: return

        while True:
            xact = x1
            yact = y1
            kierunek = random.randint(0,3)
            if kierunek == 0:#//^
                xact-=1
            if kierunek == 1:#//>
                yact+=1
            if kierunek == 2:#//v
                xact+=1
            if kierunek == 3:#//<
                yact-=1
            
            if swiat.czyWPlanszy(xact, yact)==False: continue
            org = swiat.getPlansza(xact, yact)
            if org == None: break

        swiat.tworzZwierze(self._numer, xact, yact)
        swiat.setRaport("Rodzi sie "+self._symbol)
    

    def kolizja(self,organizm, organizmy, j, swiat):
        if organizm.getSymbol()==self._symbol:
            self.zwierzeRodzi(organizm,swiat)
        elif organizm.getSila()>=self._sila:
            swiat.setRaport(self._symbol+" ginie od "+organizm.getSymbol())
            organizmy.remove(self)
            swiat.setPlanszaKolizja(self._x, self._y, organizm)
            j-=1
        elif self._sila>organizm.getSila():
            swiat.setRaport(organizm.getSymbol()+" ginie od "+self._symbol)
            organizmy.remove(organizm)
            swiat.setPlanszaKolizja(self._x, self._y, self)
            j-=1

    def __str__(self):
        return "%r, %r, %r, %r, %r, %r, %r, %r, %r" %(self._symbol, self._x, self._y, self._xprev, self._yprev, self._inicjatywa, self._sila, self._zycie, self._numer)

