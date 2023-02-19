import os
import random
from Organizm import Organizm
from zwierzeta.Antylopa import Antylopa
from zwierzeta.CyberOwca import CyberOwca
from zwierzeta.Czlowiek import Czlowiek
from zwierzeta.Lis import Lis
from zwierzeta.Owca import Owca
from zwierzeta.Wilk import Wilk
from zwierzeta.Zolw import Zolw

from rosliny.Barszcz import Barszcz
from rosliny.Guarana import Guarana
from rosliny.Jagoda import Jagoda
from rosliny.Mlecz import Mlecz
from rosliny.Trawa import Trawa


class Swiat:

    def __init__(self, x, y):
        self.__rozmiarx = x
        self.__rozmiary = y
        self.__plansza = [[0] * x for i in range(y)]
        self.__organizmy = []
        self.__raport = []
        for i in range(0, x):
            for j in range(0, y):
                self.__plansza[i][j] = None

    def getRaport(self, i):
        return self.__raport[i]

    def setRaport(self, tekst):
        self.__raport.append(tekst)

    def setRaportClear(self):
        self.__raport.clear()

    def getRaportSize(self):
        return len(self.__raport)

    def getPlansza(self, x, y):
        return self.__plansza[x][y]

    def setPlanszaKolizja(self, x, y, organizm):
        self.__plansza[x][y] = organizm
        organizm.setWykonalRuch(True)

    def setPlansza(self, x, y, organizm):
        self.__plansza[int(x)][int(y)] = organizm
        self.__organizmy.append(organizm)

    def setPlanszaNone(self, x, y):
        self.__plansza[x][y] = None

    def getRozmiarx(self):
        return self.__rozmiarx

    def getRozmiary(self):
        return self.__rozmiary

    def getOrganizmy(self):
        return self.__organizmy

    def czyWPlanszy(self, x, y):
        if x < 0 or y < 0 or x >= self.__rozmiarx or y >= self.__rozmiary: return False
        return True

    def tworzZwierze(self, symbol, x, y):
        if symbol == 0:
            wilk = Wilk(x, y, None)
            self.setPlansza(x, y, wilk)
        elif symbol == 1:
            owca = Owca(x, y, None)
            self.setPlansza(x, y, owca)
        elif symbol == 2:
            lis = Lis(x, y, None)
            self.setPlansza(x, y, lis)
        elif symbol == 3:
            zolw = Zolw(x, y, None)
            self.setPlansza(x, y, zolw)
        elif symbol == 4:
            antylopa = Antylopa(x, y, None)
            self.setPlansza(x, y, antylopa)
        elif symbol == 6:
            czlowiek = Czlowiek(x, y, None)
            self.setPlansza(x, y, czlowiek)
        elif symbol == 5:
            cyber_owca = CyberOwca(x, y, None)
            self.setPlansza(x, y, cyber_owca)

    def tworzRosline(self, symbol, x, y):
        if symbol == 0:
            trawa = Trawa(x, y, None)
            self.setPlansza(x, y, trawa)
        elif symbol == 1:
            mlecz = Mlecz(x, y, None)
            self.setPlansza(x, y, mlecz)
        elif symbol == 2:
            guarana = Guarana(x, y, None)
            self.setPlansza(x, y, guarana)
        elif symbol == 3:
            jagoda = Jagoda(x, y, None)
            self.setPlansza(x, y, jagoda)
        elif symbol == 4:
            barszcz = Barszcz(x, y, None)
            self.setPlansza(x, y, barszcz)

    def tworzZwierzeOdczyt(self, symbol, dane):
        if symbol == 'W':
            wilk = Wilk(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], wilk)
        elif symbol == 'O':
            owca = Owca(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], owca)
        elif symbol == 'L':
            lis = Lis(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], lis)
        elif symbol == 'Z':
            zolw = Zolw(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], zolw)
        elif symbol == 'A':
            antylopa = Antylopa(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], antylopa)
        elif symbol == 'Q':
            cyber_owca = CyberOwca(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], cyber_owca)
        elif symbol == 'C':
            czlowiek = Czlowiek(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], czlowiek)

    def tworzRoslineOdczyt(self, symbol, dane):
        if symbol == 't':
            trawa = Trawa(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], trawa)
        elif symbol == 'm':
            mlecz = Mlecz(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], mlecz)
        elif symbol == 'g':
            guarana = Guarana(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], guarana)
        elif symbol == 'j':
            jagoda = Jagoda(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], jagoda)
        elif symbol == 'b':
            barszcz = Barszcz(dane[0], dane[1], dane)
            self.setPlansza(dane[0], dane[1], barszcz)

    def wyoknajTure(self):
        k = 0
        self.__organizmy.sort(key=Organizm.getInicjatywa)
        for j in range(0, len(self.__organizmy)):
            if j >= len(self.__organizmy): break
            tmp = self.__organizmy[j]
            if tmp.getZycie() == 0: break
            if tmp._wykonal_ruch == True: continue
            tmp.setWykonalRuch(True)
            xprev = tmp.getX()
            yprev = tmp.getY()
            tmp.setXprev(xprev)
            tmp.setYprev(yprev)
            self.setPlanszaNone(tmp.getX(),tmp.getY())

            tmp.wykonajRuch(self, self.__organizmy, j)
            if self.__plansza[tmp.getX()][tmp.getY()] != None:
                obronca = self.__plansza[tmp.getX()][tmp.getY()]
                obronca.kolizja(tmp, self.__organizmy, j, self)
            else:
                self.__plansza[tmp.getX()][tmp.getY()] = tmp

        for j in range(0, len(self.__organizmy)):
            self.__organizmy[j].setWykonalRuch(False)
            self.__organizmy[j].setZycie(self.__organizmy[j].getZycie() + 1)


    def generujSwiat(self):  # ,okno):
        while True:
            x = random.randint(0, self.getRozmiarx() - 1)
            y = random.randint(0, self.getRozmiary() - 1)
            if self.getPlansza(x, y) == None: break

        self.tworzZwierze(6, x, y)

        for i in range(0, self.getRozmiarx() - 1):
            organizm = random.randint(0, 5)
            while True:
                x = random.randint(0, self.getRozmiarx() - 1)
                y = random.randint(0, self.getRozmiary() - 1)
                if self.getPlansza(x, y) == None: break
            self.tworzZwierze(organizm, x, y)

        for i in range(0, self.getRozmiary() - 1):
            organizm = random.randint(0, 5)
            while True:
                x = random.randint(0, self.getRozmiarx() - 1)
                y = random.randint(0, self.getRozmiary() - 1)
                if self.getPlansza(x, y) == None: break
            self.tworzRosline(organizm, x, y)

        self.wyoknajTure()

    def zapisDoPliku(self):
        plik = open("zapis.txt", "w+")
        # plik.write("20 " + " " + "20\n")

        for i in range(0, len(self.__organizmy)):
            organizm = self.__organizmy[i]
            plik.write(str(organizm) + '\n')

        plik.close()

    def odczytZPliku(self):
        self.__organizmy.clear()
        for i in range(0,20):
            for j in range(0,20):
                self.setPlanszaNone(i,j)
        plik = open("zapis.txt", "r")
        for line in plik:
            dane = line.split(',')
            znak = dane[0][1]
            del dane[0]
            for i in range(0,len(dane)):
                dane[i]=int(dane[i])

            if znak >= 'A' and znak <= 'Z':
                self.tworzZwierzeOdczyt(znak, dane)
            else:
                self.tworzRoslineOdczyt(znak, dane)
