import random
from Zwierze import Zwierze
class Zolw (Zwierze):
    def  __init__(self, x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._xprev = x
            self._yprev = y
            self._symbol = 'Z'
            self._sila = 2
            self._inicjatywa = 1
            self._zycie = 0
            self._wykonal_ruch=True
            self._numer=3
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._xprev = dane[2]
            self._yprev = dane[3]
            self._inicjatywa = dane[4]
            self._symbol = 'Z'
            self._sila = dane[5]
            self._wykonal_ruch = False
            self._zycie = dane[6]
            self._numer = dane[7]
        self._kolor= (47,79,79)

    def wykonajRuch(self, swiat, organizmy, j):
        kierunek = random.randint(0,16)
        if kierunek < 4:
            while True:
                xact = self._x
                yact = self._y
                kierunek = random.randint(0,3)

                if kierunek == 0:#//^
                    xact -=1
                if kierunek == 1:#//>
                    yact +=1
                if kierunek == 2:#//v
                    xact +=1
                if kierunek == 3:#//<
                    yact -=1
                
                if swiat.czyWPlanszy(xact, yact):  break
            self._x = xact
            self._y = yact
        
    def kolizja(self, organizm, organizmy, j, swiat): 
        if isinstance(organizm, Zolw):
            self.zwierzeRodzi(organizm,swiat)
        if organizm.getSila()<5:
            organizm.setX(organizm.getXprev())
            organizm.setY(organizm.getYprev())
            swiat.setRaport("Z odpiera atak "+organizm.getSymbol())
            swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(), organizm)
            return
        elif organizm.getSila() >= 5:
            organizmy.remove(self)
            j-=1
            swiat.setPlanszaKolizja(organizm.getX(),organizm.getY(),organizm)
            swiat.setRaport("Z ginie od "+organizm.getSymbol())