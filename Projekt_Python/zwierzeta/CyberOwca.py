from Zwierze import Zwierze
import random
class CyberOwca(Zwierze):
    def __init__(self, x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._xprev = x
            self._yprev = y
            self._symbol = 'Q'
            self._sila = 11
            self._inicjatywa = 4
            self._zycie = 0
            self._wykonal_ruch = True
            self._numer = 5
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._xprev = dane[2]
            self._yprev = dane[3]
            self._inicjatywa = dane[4]
            self._symbol = 'Q'
            self._sila = dane[5]
            self._wykonal_ruch = False
            self._zycie = dane[6]
            self._numer = dane[7]
        self._kolor= (128,0,128)

    def wykonajRuch(self, swiat, organizmy, j):
        xbarszcz = swiat.getRozmiarx() + 1
        ybarszcz = swiat.getRozmiary() + 1
        odl = pow(xbarszcz, 2) + pow(ybarszcz, 2)
        for i in range(0, len(organizmy)):
            if organizmy[i].getSymbol()=='b':
                if pow((self._x - organizmy[i].getX()),2) + pow((self._y - organizmy[i].getY()),2) < odl:
                    odl = pow((self._x - organizmy[i].getX()),2) + pow((self._y - organizmy[i].getY()),2)
                    xbarszcz = organizmy[i].getX()
                    ybarszcz = organizmy[i].getY()
        print(self._x , self._y, xbarszcz, ybarszcz )

        if xbarszcz== 21 and ybarszcz==21 :
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

                if swiat.czyWPlanszy(xact, yact):  break

            self._x = xact
            self._y = yact
            return
        if xbarszcz > self._x:
            self._x += 1
        elif ybarszcz > self._y:
            self._y += 1
        elif xbarszcz < self._x:
            self._x -= 1
        else:
            self._y -= 1
