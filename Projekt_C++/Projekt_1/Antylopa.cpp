#include "Antylopa.h"
#include <iostream>
Antylopa::Antylopa(int xt, int yt)
{
	x = xt;
	y = yt;
	xprev = x;
	yprev = y;
	symbol = 'A';
	sila = 4;
	inicjatywa = 4;
	zycie = 0;
	wykonal_ruch = 1;
	numer_enum = 4;
}
void Antylopa::wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)
{
	int l = 0;
	bool flag = 0;
	int kierunek;
	int xact = x, yact = y;
	int sum = 0;
	Organizm* org;
	do
	{
		xact = x;
		yact = y;
		kierunek = rand() % 4;
		if (kierunek == 0)//^
		{
			xact-=2;
		}
		if (kierunek == 1)//>
		{
			yact+=2;
		}
		if (kierunek == 2)//v
		{
			xact+=2;
		}
		if (kierunek == 3)//<
		{
			yact-=2;
		}
		if (swiat.czyWPlanszy(swiat, xact, yact))  break;
	} while (1);
	x = xact;
	y = yact;
}
void Antylopa::kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)
{
	int l = rand() % 2;
	int xpom, ypom;
	if (organizm->getSymbol() > 'A' && organizm->getSymbol() <= 'Z'||organizm->getSymbol()=='#')
	{
		if (l == 0)
		{
			Organizm* org;
			int kierunek;
			xpom = x;
			ypom = y;
			org = swiat.getPlansza(x, y);

			do
			{
				kierunek = rand() % 4;
				x = xpom;
				y = ypom;
				if (kierunek == 0)//^
				{
					x--;
				}
				if (kierunek == 1)//>
				{
					y++;
				}
				if (kierunek == 2)//v
				{
					x++;
				}
				if (kierunek == 3)//<
				{
					y--;
				}
				if (!swiat.czyWPlanszy(swiat, x, y)) continue;
				org = swiat.getPlansza(x, y);
				if (org == NULL) break;
			} while (1);
			swiat.setPlansza(x, y, this);
			cout << "A ucieka przed " << organizm->getSymbol() << '\n';
			return;
		}
	}
	if (organizm->getSymbol() == 'Z')
	{
		organizm->kolizja(this, organizmy, j, swiat);
	}
	if (organizm->getSymbol() == 'g')
	{
		organizm->kolizja(this, organizmy, j, swiat);
	}
	if (organizm->getSila() > sila)
	{
		zwierzeGinie(organizmy, j);
		cout << symbol << " ginie od " << organizm->getSymbol() << '\n';
		swiat.setPlansza(x, y, organizm);
		//obecny ginie i jest usuwany z wektora i z planszy 
	}
	else if (organizm->getSymbol() == symbol)
	{
		zwierzeRodzi(organizm,swiat);
		cout << "Rodzi sie " << symbol << '\n';
	}
	else
	{
		zwierzeZabija(organizmy, j, swiat);
		cout << organizm->getSymbol() << " ginie od " << symbol << '\n';
		swiat.setPlansza(x, y, this);
	}
}