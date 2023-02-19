#pragma once
#include <vector>
#include "Organizm.h"
#include "Zwierze.h"
#include "Roslina.h"
class Zwierze;
class Roslina;
class Swiat
{
	Organizm*** plansza;
	char** plansza_znakow;
	int rozmiarx, rozmiary;
	vector <Organizm* > organizmy;
public:
	Swiat(int rozx,int rozy);
	Organizm* getPlansza(int x, int y);
	vector<Organizm*> getZwierzeta();
	pair<int, int> losujPozycje();
	void wykonajTure();
	void rysujSwiat();
	int getRozmiarX();
	int getRozmiarY();
	void setPlansza(int x, int y, char znak,Organizm *organizm);
	void setPlansza(int x, int y);
	void setPlansza(int x, int y, Organizm* organizm);
	bool czyWPlanszy(Swiat swiat, int x, int y);
	void setPlanszaZnakow(int x, int y, char znak);
	char getPlanszaZnakow(int x, int y);
	void tworzZwierze(int zwierze, int x, int y);
	void tworzRosline(int roslina, int x, int y);
	void zapisDoPliku();
	void odczytZPliku(string nazwa);
	pair<int, int> zwrocEnumOrganizm(char znak);
	//~Swiat();
};