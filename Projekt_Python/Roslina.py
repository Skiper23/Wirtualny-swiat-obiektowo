import random
from Organizm import Organizm
class Roslina(Organizm):

    def wykonajRuch(self, swiat, organizmy, j):
        kierunek = random.randint(0, 3)
        sum = 0
        if kierunek == 0:
            if swiat.czyWPlanszy(self._x + 1, self._y):
                if swiat.getPlansza(self._x + 1, self._y) != None: sum+=1
            else: sum+=1
            if swiat.czyWPlanszy(self._x - 1, self._y):
                if swiat.getPlansza(self._x - 1, self._y) != None: sum+=1
            else: sum+=1
            if swiat.czyWPlanszy(self._x, self._y + 1):
                if swiat.getPlansza(self._x, self._y + 1) !=None: sum+=1
            else: sum+=1
            if swiat.czyWPlanszy(self._x, self._y - 1):
                if swiat.getPlansza(self._x, self._y - 1) != None: sum+=1
            else: sum+=1
            if sum==4: return

            while True:
                xact =self._x
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
                org = swiat.getPlansza( xact, yact)
                if org == None: break

            swiat.tworzRosline(self._numer, xact,yact)
            swiat.setRaport("Rozrasta "+self._symbol)
     
    def kolizja(self,organizm, organizmy, j, swiat):
     
        if organizm.getSila()>=self._sila:
            swiat.setRaport(self._symbol+" ginie od "+organizm.getSymbol() )
            organizmy.remove(self)
            swiat.setPlanszaKolizja( self._x, self._y, organizm)
            j-=1
        elif self._sila>organizm.getSila():
            swiat.setRaport(organizm.getSymbol()+" ginie od "+self._symbol )
            organizmy.remove(organizm)
            swiat.setPlanszaKolizja( self._x, self._y, self)
            j-=1

    def setXprev(self,x):
        pass

    def getXprev(self):
        pass

    def setYprev(self,y):
        pass

    def getYprev(self):
        pass

 
