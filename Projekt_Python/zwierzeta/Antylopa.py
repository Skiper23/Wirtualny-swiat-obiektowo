import random
from Zwierze import Zwierze
class Antylopa (Zwierze):
    def __init__(self,x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._xprev = x
            self._yprev = y
            self._symbol = 'A'
            self._sila = 4
            self._inicjatywa = 4
            self._zycie = 0
            self._wykonal_ruch=True
            self._numer=4
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._xprev = dane[2]
            self._yprev = dane[3]
            self._inicjatywa = dane[4]
            self._symbol = 'A'
            self._sila = dane[5]
            self._wykonal_ruch = False
            self._zycie = dane[6]
            self._numer = dane[7]
        self._kolor=(184,134,11)

    def wykonajRuch(self, swiat, organizmy, j) :
        while True:
            xact = self._x
            yact = self._y
            kierunek = random.randint(0,3)
            if kierunek == 0:#//^
                xact-=2
            if kierunek == 1:#//>
                yact+=2
            if kierunek == 2:#//v
                xact+=2
            if kierunek == 3:#//<
                yact-=2
            
            if swiat.czyWPlanszy(xact, yact):  break
        self._x = xact
        self._y = yact
    
    def kolizja(self, organizm, organizmy, j, swiat): 
        kierunek = random.randint(0,1)
        if isinstance(organizm, Antylopa):
            if kierunek == 0:
                xpom = self._x
                ypom = self._y

                while True:
                    kierunek = random.randint(0,3)
                    x = xpom
                    y = ypom
                    if kierunek == 0:#//^
                        x-=1
                    if kierunek == 1:#//>
                        y+=1
                    if kierunek == 2:#//v
                        x+=1
                    if kierunek == 3:#//<
                        y-=1
                    
                    if swiat.czyWPlanszy(x, y)==False: continue
                    org = swiat.getPlansza(x, y)
                    if org == None: break

                swiat.setPlanszaKolizja(x, y, self)
                swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(),organizm)
                swiat.setRaport("A ucieka przed "+ organizm.getSymbol())
                return
            
        
        if isinstance(organizm, Antylopa):
            self.zwierzeRodzi(organizm,swiat)
        elif organizm.getSila()>= self._sila:
            swiat.setRaport( self._symbol+" ginie od "+organizm.getSymbol() )
            organizmy.remove(self)
            swiat.setPlanszaKolizja(self._x, self._y, organizm)
            j-=1
        elif self._sila>organizm.getSila():
            swiat.setRaport(organizm.getSymbol()+" ginie od "+ self._symbol )
            organizmy.remove(organizm)
            swiat.setPlanszaKolizja(self._x, self._y, self)
            j-=1