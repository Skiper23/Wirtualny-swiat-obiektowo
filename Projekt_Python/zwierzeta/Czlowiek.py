import random
from Zwierze import Zwierze


class Czlowiek(Zwierze):

    def __init__(self, x, y, dane):
        if dane == None:
            self._x = x
            self._y = y
            self._xprev = x
            self._yprev = y
            self._sila = 5
            self._inicjatywa = 4
            self._zycie = 0
            self._wykonal_ruch = True
            self._numer = 6
            self.__bron = -5
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._xprev = dane[2]
            self._yprev = dane[3]
            self._inicjatywa = dane[4]
            self._sila = dane[5]
            self._wykonal_ruch = False
            self._zycie = dane[6]
            self._numer = dane[7]
            self.__bron = dane[8]
        self.__kierunek = -1
        self._symbol = 'C'
        self._kolor = (250, 128, 114)

    def setKierunek(self, kierunek):
        self.__kierunek = kierunek

    def getKierunek(self):
        return self.__kierunek

    def getBron(self):
        return self.__bron

    def setBron(self, bron):
        self.__bron = bron

    def wykonajRuch(self, swiat, organizmy, j):
        if self.__kierunek == 0:  # //^
            if self._x > 0:
                self._x -= 1
        if self.__kierunek == 1:  # //>
            if self._y < swiat.getRozmiary() - 1:
                self._y += 1
        if self.__kierunek == 2:  # //v
            if self._x < swiat.getRozmiarx() - 1:
                self._x += 1
        if self.__kierunek == 3:  # //<
            if self._y > 0:
                self._y -= 1

    def kolizja(self, organizm, organizmy, j, swiat):
        if self.__bron > 0 and isinstance(organizm, Zwierze):
            while True:
                xact = organizm.getX()
                yact = organizm.getY()
                kierunek = random.randint(0, 3)

                if kierunek == 0:  # //^
                    xact -= 1
                if kierunek == 1:  # //>
                    yact += 1
                if kierunek == 2:  # //v
                    xact += 1
                if kierunek == 3:  # //<
                    yact -= 1
                if swiat.czyWPlanszy(xact, yact) and swiat.getPlansza(xact, yact) == None:  break

            swiat.setRaport("C odpycha " + organizm.getSymbol())
            organizm.setX(xact)
            organizm.setY(yact)
            swiat.setPlanszaKolizja(organizm.getX(), organizm.getY(), organizm)
            return

        if organizm.getSila() > self._sila:
            organizmy.remove(self)
            swiat.setRaport("C ginie od " + organizm.getSymbol())
            swiat.setPlanszaKolizja(self._x, self._y, organizm)
            j -= 1
        else:
            organizmy.remove(organizm)
            swiat.setRaport(organizm.getSymbol() + " ginie od C")
            j -= 1

    def __str__(self):
        return "%r, %r, %r,%r, %r, %r, %r, %r, %r, %r" % (
            self._symbol, self._x, self._y, self._xprev, self._yprev, self._inicjatywa, self._sila, self._zycie,
            self._numer, self.__bron)
