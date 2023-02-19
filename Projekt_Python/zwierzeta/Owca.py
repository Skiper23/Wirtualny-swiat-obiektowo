from Zwierze import Zwierze

class Owca(Zwierze): 
    def __init__(self, x, y,dane):
        if dane==None:
            self._x=x
            self._y=y
            self._xprev=x
            self._yprev=y
            self._symbol='O'
            self._sila=4
            self._inicjatywa=4
            self._zycie=0
            self._wykonal_ruch=True
            self._numer=1
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._xprev = dane[2]
            self._yprev = dane[3]
            self._inicjatywa = dane[4]
            self._symbol = 'O'
            self._sila = dane[5]
            self._wykonal_ruch = False
            self._zycie = dane[6]
            self._numer = dane[7]
        self._kolor= (224,255,255)
