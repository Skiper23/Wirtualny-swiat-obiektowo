#include "Zolw.h"

Zolw::Zolw(int xt, int yt)
{
	x = xt;
	y = yt;
	xprev = x;
	yprev = y;
	symbol = 'Z';
	sila = 2;
	inicjatywa = 1;
	zycie = 0;
	wykonal_ruch = 1;
	numer_enum = 3;
}
void Zolw::wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)
{
	int kierunek;
	int xact = x, yact = y;
	Organizm* org;
	kierunek = rand() % 16;
	if (kierunek < 4)
	{
		do
		{
			xact = x;
			yact = y;
			kierunek = rand() % 4;

			if (kierunek == 0)//^
			{
				xact --;
			}
			if (kierunek == 1)//>
			{
				yact ++;
			}
			if (kierunek == 2)//v
			{
				xact ++;
			}
			if (kierunek == 3)//<
			{
				yact --;
			}
			if (swiat.czyWPlanszy(swiat, xact, yact))  break;
		} while (1);
		x = xact;
		y = yact;
	}
}
void Zolw::kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)
{
	if (organizm->getSila()<5&&organizm->getSymbol()>='A'&&organizm->getSymbol()<='Z')
	{
		organizm->setX(organizm->getXprev());
		organizm->setY(organizm->getYprev());
		cout << "Z odpiera atak " << organizm->getSymbol() << '\n';
		swiat.setPlansza(organizm->getX(), organizm->getY(), organizm);
		return;	
	}
	if (organizm->getSymbol() == 'g')
	{
		organizm->kolizja(this, organizmy, j, swiat);
	}
	if (organizm->getSymbol() == '#')
	{
		organizm->kolizja(this, organizmy, j, swiat);
		return;
	}
	if (organizm->getSymbol() == 'A')
	{
		organizm->kolizja(this, organizmy, j, swiat);
		return;
	}
	if (organizm->getSila() > sila)
	{
		zwierzeGinie(organizmy, j);
		cout << symbol << " ginie od " << organizm->getSymbol() << '\n';
		swiat.setPlansza(x, y, organizm);
		//obecny ginie i jest usuwany z wektora i z planszy 
	}
	else
	{
		zwierzeZabija(organizmy, j, swiat);
		cout << organizm->getSymbol() << " ginie od " << symbol << '\n';
		swiat.setPlansza(x, y, this);
	}
}