import random
from Roslina import Roslina
from Zwierze import Zwierze
from zwierzeta.CyberOwca import CyberOwca


class Barszcz(Roslina):
    def __init__(self, x, y, dane):
        if dane == None:
            self._x = x
            self._y = y
            self._symbol = 'b'
            self._sila = 0
            self._wykonal_ruch = False
            self._zycie = 0
            self._inicjatywa = 0
            self._numer = 4
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._inicjatywa = dane[2]
            self._symbol = 'b'
            self._sila = dane[3]
            self._wykonal_ruch = False
            self._zycie = dane[4]
            self._numer = dane[5]
        self._kolor = (0, 0, 0)

    def kolizja(self, organizm, organizmy, j, swiat):
        if isinstance(organizm, Zwierze):
            if organizm.getSymbol() == 'Q' and organizm.getX()==self._x and organizm.getY()== self._y:
                swiat.setRaport("b ginie od "+organizm.getSymbol())
                swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(),organizm)
                organizmy.remove(self)
            elif organizm.getSymbol() != 'Q':
                swiat.setRaport(organizm.getSymbol() + " ginie od b ")
                swiat.setPlanszaNone(organizm.getX(), organizm.getY())
                organizmy.remove(organizm)
            j-=1

    def wykonajRuch(self, swiat, organizmy, j):
        kierunek = random.randint(0, 3)
        sum = 0
        if swiat.czyWPlanszy(self._x + 1, self._y):
            if swiat.getPlansza(self._x + 1, self._y) != None:
                self.kolizja(swiat.getPlansza(self._x + 1, self._y), organizmy, j, swiat)
                sum += 1
        else:
            sum += 1
        if swiat.czyWPlanszy(self._x - 1, self._y):
            if swiat.getPlansza(self._x - 1, self._y) != None:
                self.kolizja(swiat.getPlansza(self._x - 1, self._y), organizmy, j, swiat)
                sum += 1
        else:
            sum += 1
        if swiat.czyWPlanszy(self._x, self._y + 1):
            if swiat.getPlansza(self._x, self._y + 1) != None:
                self.kolizja(swiat.getPlansza(self._x, self._y + 1), organizmy, j, swiat)
                sum += 1
        else:
            sum += 1
        if swiat.czyWPlanszy(self._x, self._y - 1):
            if swiat.getPlansza(self._x, self._y - 1) != None:
                self.kolizja(swiat.getPlansza(self._x, self._y - 1), organizmy, j, swiat)
                sum += 1
        else:
            sum += 1
        if sum == 4: return

        if kierunek == 0:
            while True:
                xact = self._x
                yact = self._y
                kierunek = random.randint(0, 3)
                if kierunek == 0:  # //^
                    xact -= 1
                if kierunek == 1:  # //>
                    yact += 1
                if kierunek == 2:  # //v
                    xact += 1
                if kierunek == 3:  # //<
                    yact -= 1

                if swiat.czyWPlanszy(xact, yact) == False: continue
                org = swiat.getPlansza(xact, yact)
                if org == None:
                    if swiat.czyWPlanszy(self._x + 1, self._y):
                        if swiat.getPlansza(self._x + 1, self._y) != None:
                            self.kolizja(swiat.getPlansza(self._x + 1, self._y), organizmy, j, swiat)

                    if swiat.czyWPlanszy(self._x - 1, self._y):
                        if swiat.getPlansza(self._x - 1, self._y) != None:
                            self.kolizja(swiat.getPlansza(self._x - 1, self._y), organizmy, j, swiat)

                    if swiat.czyWPlanszy(self._x, self._y + 1):
                        if swiat.getPlansza(self._x, self._y + 1) != None:
                            self.kolizja(swiat.getPlansza(self._x, self._y + 1), organizmy, j, swiat)

                    if swiat.czyWPlanszy(self._x, self._y - 1):
                        if swiat.getPlansza(self._x, self._y - 1) != None:
                            self.kolizja(swiat.getPlansza(self._x, self._y - 1), organizmy, j, swiat)
                    break

            swiat.tworzRosline(self._numer, xact, yact)
            swiat.setRaport("Rozrasta " + self._symbol)
