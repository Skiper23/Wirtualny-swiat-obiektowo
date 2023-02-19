from Roslina import Roslina

class Guarana (Roslina): 
    def __init__(self, x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._symbol = 'g'
            self._sila = 0
            self._wykonal_ruch = False
            self._inicjatywa = 0
            self._zycie = 0
            self._numer=2
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._inicjatywa = dane[2]
            self._symbol = 'g'
            self._sila = dane[3]
            self._wykonal_ruch = False
            self._zycie = dane[4]
            self._numer = dane[5]
        self._kolor= (255,0,0)

    def kolizja(self, organizm, organizmy, j, swiat):
        organizm.setSila(organizm.getSila()+3)
        swiat.setRaport(self._symbol+" ginie od "+ organizm.getSymbol())
        organizmy.remove(self)
        swiat.setPlanszaKolizja(self._x,self._y,organizm)