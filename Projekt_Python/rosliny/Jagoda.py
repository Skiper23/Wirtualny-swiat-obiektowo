from Roslina import Roslina

class Jagoda (Roslina): 
    def __init__(self, x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._symbol = 'j'
            self._sila = 99
            self._wykonal_ruch = False
            self._inicjatywa = 0
            self._zycie = 0
            self._numer=3
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._inicjatywa=dane[2]
            self._symbol = 'j'
            self._sila = dane[3]
            self._wykonal_ruch = False
            self._zycie = dane[4]
            self._numer=dane[5]
        self._kolor= (0,0,255)

