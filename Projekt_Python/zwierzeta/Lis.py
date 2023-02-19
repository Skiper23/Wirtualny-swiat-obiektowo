import random
from Zwierze import Zwierze


class Lis(Zwierze):
    def __init__(self, x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._xprev = x
            self._yprev = y
            self._symbol = 'L'
            self._sila = 3
            self._inicjatywa = 7
            self._zycie = 0
            self._wykonal_ruch = True
            self._numer = 2
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._xprev = dane[2]
            self._yprev = dane[3]
            self._inicjatywa = dane[4]
            self._symbol = 'L'
            self._sila = dane[5]
            self._wykonal_ruch = False
            self._zycie = dane[6]
            self._numer = dane[7]
        self._kolor= (255,165,0)

    def wykonajRuch(self, swiat, organizmy, j):
        sum = 0
        if swiat.czyWPlanszy(self._x + 1, self._y):
            if swiat.getPlansza(self._x + 1, self._y) != None:
                org = swiat.getPlansza(self._x + 1, self._y)
                if org.getSila() > self._sila:
                    sum += 1
        else:
            sum += 1
        if swiat.czyWPlanszy(self._x - 1, self._y):
            if swiat.getPlansza(self._x - 1, self._y) != None:
                org = swiat.getPlansza(self._x - 1, self._y)
                if org.getSila() > self._sila:
                    sum += 1
        else:
            sum += 1
        if swiat.czyWPlanszy(self._x, self._y + 1):
            if swiat.getPlansza(self._x, self._y + 1) != None:
                org = swiat.getPlansza(self._x, self._y + 1)
                if org.getSila() > self._sila:
                    sum += 1
        else:
            sum += 1
        if swiat.czyWPlanszy(self._x, self._y - 1):
            if swiat.getPlansza(self._x, self._y - 1) != None:
                org = swiat.getPlansza(self._x, self._y - 1)
                if org.getSila() > self._sila:
                    sum += 1
        else:
            sum += 1
        if sum == 4: return

        while True:
            xact = self._x
            yact = self._y
            kierunek = random.randint(0,3)
            if kierunek == 0:  # //^
                xact -= 1
            if kierunek == 1:  # //>
                yact += 1
            if kierunek == 2:  # //v
                xact += 1
            if kierunek == 3:  # //<
                yact -= 1

            if swiat.czyWPlanszy(xact, yact):
                org = swiat.getPlansza(xact, yact)
                if org == None: break
                if org.getSila() <= self._sila: break

        self._x = xact
        self._y = yact
