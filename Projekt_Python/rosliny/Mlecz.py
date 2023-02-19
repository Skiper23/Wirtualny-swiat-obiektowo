import random
from Roslina import Roslina

class Mlecz (Roslina): 
    def __init__(self, x, y,dane):
        if dane==None:
            self._x = x
            self._y = y
            self._symbol = 'm'
            self._sila = 0
            self._wykonal_ruch = False
            self._inicjatywa = 0
            self._zycie = 0
            self._numer=1
        else:
            self._x = dane[0]
            self._y = dane[1]
            self._inicjatywa = dane[2]
            self._symbol = 'm'
            self._sila = dane[3]
            self._wykonal_ruch = False
            self._zycie = dane[4]
            self._numer = dane[5]
        self._kolor= (255,255,0)

    def wykonajRuch(self, swiat, organizmy, j):
        for i in range(0,3):
            sum=0
            kierunek= random.randint(0,3)
            if kierunek==0:
                if swiat.czyWPlanszy(self._x + 1, self._y):
                    if swiat.getPlansza(self._x + 1, self._y) != None: sum+=1
                else: sum+=1
                if swiat.czyWPlanszy(self._x - 1, self._y):
                    if swiat.getPlansza(self._x - 1, self._y) != None: sum+=1
                else: sum+=1
                if swiat.czyWPlanszy(self._x, self._y + 1):
                    if swiat.getPlansza(self._x, self._y + 1) != None: sum+=1
                else: sum+=1
                if swiat.czyWPlanszy(self._x, self._y - 1):
                    if swiat.getPlansza(self._x, self._y - 1) != None: sum+=1
                else: sum+=1
                if sum==4: return
               
                while True:
                    xact = self._x
                    yact = self._y
                    kierunek = random.randint(0,3)
                    if kierunek == 0:#//^
                       xact-=1
                    if kierunek == 1:#//>
                       yact+=1
                    if kierunek == 2:#//v
                       xact+=1
                    if kierunek == 3:#//<
                       yact-=1
                   
                    if swiat.czyWPlanszy(xact, yact)==False: continue
                    org = swiat.getPlansza(xact, yact)
                    if org == None: break

                swiat.tworzRosline(self._numer,xact,yact)
                swiat.setRaport("Rozrasta "+self._symbol)
           
       
    

