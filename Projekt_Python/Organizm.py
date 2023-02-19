from abc import abstractmethod


class Organizm: 

    def __init__(self, x,y,symbol,sila,inicjatywa,numer):
        self._x = x
        self._y = y
        self._xprev = x
        self._yprev = y
        self._symbol = symbol
        self._sila = sila
        self._inicjatywa = inicjatywa
        self._zycie = 0
        self._wykonal_ruch=True
        self._numer=numer
        self.kolor= (0,0,0)

    def getSymbol(self):
        return self._symbol

    def getNumer(self):
        return self._numer

    def setNumer(self, numer):
        self._numer = numer

    def setSymbol(self, symbol):
        self._symbol = symbol

    def getX(self):
        return self._x

    def setX(self,  x):
        self._x =  x

    def getY(self):
        return self._y

    def setY(self, y):
        self._y = y

    def getXprev(self):
        return self._xprev

    def setXprev(self, xprev): 
        self.xprev = xprev
    
    def getYprev(self): 
        return self._yprev
    
    def setYprev(self,yprev): 
        self._yprev = yprev

    def getSila(self):
        return self._sila

    def setSila(self, sila):
        self._sila = sila

    def getZycie(self):
        return self._zycie

    def setZycie(self, zycie):
        self._zycie = zycie
    
    def getInicjatywa(self):
        return self._inicjatywa
    

    def setInicjatywa(self, inicjatywa):
        self.inicjatywa = inicjatywa

    def setWykonalRuch(self,wykonal_ruch):
        self._wykonal_ruch = wykonal_ruch
    
    @abstractmethod
    def wykonajRuch(self, swiat, organizmy, j):
        pass
    @abstractmethod
    def kolizja(self, organizm, organizmy, j, swiat):
        pass

    def __str__(self):
        return "%r, %r, %r, %r, %r, %r, %r" %(self._symbol, self._x, self._y, self._inicjatywa, self._sila, self._zycie, self._numer)

