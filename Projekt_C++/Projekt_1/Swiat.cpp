#include <iostream>
#include <vector>
#include <fstream>
#include "Swiat.h"
#include "Organizm.h"
#include "Zwierze.h"
#include "Antylopa.h"
#include "Barszcz.h"
#include "Guarana.h"
#include "Jagoda.h"
#include "Lis.h"
#include "Mlecz.h"
#include "Owca.h"
#include "Trawa.h"
#include "Wilk.h"
#include "Zolw.h"
#include "Czlowiek.h"
#include "Roslina.h"
using namespace std;
enum Zwierzeta
{
	WILK = 0,
	OWCA,
	LIS,
	ZOLW,
	ANTYLOPA,
	CYBEROWCA,
	CZLOWIEK
};
enum Rosliny
{
	TRAWA = 0,
	MLECZ,
	GUARANA,
	JAGODA,
	BARSZCZ
};

Swiat::Swiat(int x,int y)
{
	rozmiarx = x;
	rozmiary = y;
	plansza = new Organizm * *[rozmiarx];
	plansza_znakow = new char*[rozmiarx];
	for (int i = 0; i < rozmiarx; i++)
	{
		plansza[i] = new Organizm * [rozmiary];
		plansza_znakow[i] = new char[rozmiary];
		for (int j = 0; j < rozmiary; j++)
		{
			plansza_znakow[i][j] = ' ';
			plansza[i][j] = NULL;
		}
	}
}
char Swiat::getPlanszaZnakow(int x, int y)
{
	return plansza_znakow[x][y];
}
Organizm* Swiat::getPlansza(int x, int y) 
{
	return plansza[x][y];
}
void Swiat::setPlansza(int x, int y,char znak,Organizm *organizm)
{
	plansza[x][y] =organizm;
	organizmy.push_back(organizm);
	organizm->setWykonalRuch(1);
}
void Swiat::setPlansza(int x, int y,Organizm* organizm)
{
	plansza[x][y] = organizm;
}
void Swiat::setPlansza(int x, int y)
{
	plansza[x][y] = NULL;
}
int Swiat::getRozmiarX()
{
	return rozmiarx;
}
int Swiat::getRozmiarY()
{
	return rozmiary;
}
bool Swiat::czyWPlanszy(Swiat swiat, int x, int y)
{
	if (x < 0 || y < 0 || x >= rozmiarx || y >= rozmiary) return 0;
	return 1;
}
void Swiat::setPlanszaZnakow(int x, int y, char znak)
{
	plansza_znakow[x][y] = znak;
}
vector<Organizm*> Swiat::getZwierzeta()
{
	return organizmy;
}
pair<int, int> Swiat::losujPozycje()
{
	pair<int, int> wsp;
	wsp.first = rand() % rozmiarx;
	wsp.second = rand() % rozmiary;
	while (plansza[wsp.first][wsp.second] != NULL)
	{
		wsp.first = rand() % rozmiarx;
		wsp.second = rand() % rozmiary;
	}
	return wsp;
}
void Swiat::rysujSwiat()
{
	cout << "Krystian Nowakowski 188728\n";
	cout << "Sterowanie - strzalki, Aktywacja mocy specjalnej - enter, Zapis do pliku - s, Wyjscie - Esc\n";
	bool flag = 0;
	for (int i = 0; i < organizmy.size(); i++)
	{
		plansza_znakow[organizmy[i]->getX()][organizmy[i]->getY()] = organizmy[i]->getSymbol();
	}
	for (int i = -1; i <= rozmiary; i++) cout << '_';
	cout << endl;
	for (int i = 0; i < rozmiarx; i++,cout<<endl)
	{ 
		if (i+1 < 10) cout << 0;
		cout << i+1;
	
		for (int j = 0; j < rozmiary; j++)
		{
			cout << plansza_znakow[i][j];
		}
		cout << '|';
	}
	for (int i = -1; i <= rozmiary; i++) cout << '-';
	cout << endl;
}
pair<int, int> Swiat::zwrocEnumOrganizm(char znak)
{
	if (znak == '#') return { CZLOWIEK, 0 };
	if (znak == 'W') return { WILK, 0 };
	if (znak == 'O') return { OWCA, 0 };
	if (znak == 'L') return { LIS, 0 };
	if (znak == 'Z') return { ZOLW, 0 };
	if (znak == 'A') return { ANTYLOPA, 0 };
	if (znak == 't') return { TRAWA, 1 };
	if (znak == 'm') return { MLECZ, 1 };
	if (znak == 'g') return { GUARANA, 1 };
	if (znak == 'j') return { JAGODA, 1 };
	if (znak == 'b') return { BARSZCZ, 1 };
	if (znak == ' ') return { -1,-1 };
}
void Swiat::zapisDoPliku()
{
	string nazwa;
	cout << "Podaj nazwe pliku: ";
	cin >> nazwa;
	ofstream out(nazwa);
	for (int i = 0; i < rozmiarx; i++, out << endl)
	{
		for (int j = 0; j < rozmiary; j++)
			out << plansza_znakow[i][j];
	}
		out.close();
}
void Swiat::odczytZPliku(string nazwa)
{
	fstream in;
	char znak;
	pair<int, int>org;
	string wiersz;
	in.open(nazwa);
	for (int i = 0; i < rozmiarx; i++)
	{
		getline(in, wiersz);

			for (int j = 0; j < rozmiary; j++)
			{
				org = zwrocEnumOrganizm(wiersz[j]);
				if (org.first == -1)
				{
					plansza[i][j] = NULL;
				}
				else if (org.second == 0)
				{
					if (org.first != CZLOWIEK)
						tworzZwierze(org.first, i, j);
				}
				else
					tworzRosline(org.first, i, j);
			}
	}
	in.close();
}
void Swiat::tworzZwierze(int zwierze, int x, int y)
{
	if (zwierze == WILK)
	{
		Wilk* wilk = new Wilk(x, y);
		setPlansza(x, y, wilk->getSymbol(), wilk);
	}
	else if (zwierze == OWCA)
	{
		Owca* owca = new Owca(x, y);
		setPlansza(x, y, owca->getSymbol(), owca);
	}
	else if (zwierze == LIS)
	{
		Lis* lis = new Lis(x, y);
		setPlansza(x, y, lis->getSymbol(), lis);
	}
	else if (zwierze == ZOLW)
	{
		Zolw* zolw = new Zolw(x, y);
		setPlansza(x, y, zolw->getSymbol(), zolw);
	}
	else if (zwierze == ANTYLOPA)
	{
		Antylopa* antylopa = new Antylopa(x, y);
		setPlansza(x, y, antylopa->getSymbol(), antylopa);
	}
	else if (zwierze == CZLOWIEK)
	{
		Czlowiek* czlowiek = new Czlowiek(x, y);
		setPlansza(x, y, czlowiek->getSymbol(), czlowiek);
	}
}
void Swiat::tworzRosline(int roslina, int x, int y)
{	
	if (roslina == TRAWA)
	{
		Trawa* trawa = new Trawa(x, y);
		setPlansza(x, y, trawa->getSymbol(), trawa);
	}
	else if (roslina == MLECZ)
	{
		Mlecz* mlecz = new Mlecz(x, y);
		setPlansza(x, y, mlecz->getSymbol(), mlecz);
	}
	else if (roslina == GUARANA)
	{
		Guarana* guarana = new Guarana(x, y);
		setPlansza(x, y, guarana->getSymbol(), guarana);
	}
	else if (roslina == JAGODA)
	{
		Jagoda* jagoda = new Jagoda(x, y);
		setPlansza(x, y, jagoda->getSymbol(), jagoda);
	}
	else if (roslina == BARSZCZ)
	{
		Barszcz* barszcz = new Barszcz(x, y);
		setPlansza(x, y, barszcz->getSymbol(), barszcz);
	}
}
void Swiat::wykonajTure()
{
	Organizm* tmp;
	Organizm *obronca;
	int k = 0, xprev, yprev, sizeprzed;
	bool flag_ginie,flag_kolizja=0;

	for (int j = 0; j < organizmy.size(); j++)
	{
		while (organizmy[k]->getWykonalRuch() != 0&&organizmy[k]->getZycie()>0)
		{
			k++;
			if (k >= organizmy.size())break;
		}
		if (k >= organizmy.size())
			break;

		tmp = organizmy[k];
		for (int i = k+1; i < organizmy.size(); i++)
		{
			if (organizmy[i]->getInicjatywa() > tmp->getInicjatywa() && organizmy[i]->getWykonalRuch() == 0)
			{
				tmp = organizmy[i];
			}
			else if (organizmy[i]->getInicjatywa() == tmp->getInicjatywa() && organizmy[i]->getWykonalRuch() == 0 && organizmy[i]->getZycie() > tmp->getZycie())
			{
				tmp = organizmy[i];
			}
		}
		if (tmp->getWykonalRuch() == 1)
			break;
		//cout << "Wykonuje ruch " << tmp->getSymbol() <<" "<<tmp->getWykonalRuch()<<" "<<tmp->getZycie()<< endl;
		tmp->setWykonalRuch(1);
		xprev = tmp->getX();
		yprev = tmp->getY();
		tmp->setXprev(xprev);
		tmp->setYprev(yprev);
		plansza_znakow[tmp->getX()][tmp->getY()] = ' ';
		plansza[tmp->getX()][tmp->getY()] =NULL;

		sizeprzed = organizmy.size();
		tmp->wykonajRuch(*this,organizmy);
		if (organizmy.size() > sizeprzed) j++;

		if (plansza[tmp->getX()][tmp->getY()] != NULL)
		{
			obronca = plansza[tmp->getX()][tmp->getY()];
			flag_kolizja = 1;
			tmp->kolizja(obronca, organizmy, j, *this);
		}
		if(flag_kolizja==0)
		plansza[tmp->getX()][tmp->getY()] = tmp;
		flag_kolizja = 0;
	}
	for (int j = 0; j < organizmy.size(); j++)
	{
		organizmy[j]->setWykonalRuch(0);
		organizmy[j]->setZycie(organizmy[j]->getZycie() + 1);
	}
}
/*Swiat::~Swiat()
{
	for (int i = 0; i < rozmiarx; i++)
	{
		delete[]plansza[i];
		delete[]plansza_znakow[i];
	}
	delete[] plansza;
	delete[] plansza_znakow;
}*/