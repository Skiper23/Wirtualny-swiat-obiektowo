#include "Zwierze.h"
#include "Swiat.h"
#include <vector>
int Zwierze::getNumerEnum()
{
	return numer_enum;
}
bool Zwierze::getWykonalRuch()
{
	return wykonal_ruch;
}
void Zwierze::setWykonalRuch(bool r)
{
	wykonal_ruch = r;
}
int Zwierze::getZycie()
{
	return zycie;
}
void Zwierze::setZycie(int x)
{
	zycie = x;
}
int Zwierze::getX()
{
	return x;
}
int Zwierze::getY()
{
	return y;
}int Zwierze::getXprev()
{
	return xprev;
}
int Zwierze::getYprev()
{
	return yprev;
}
void Zwierze::setX(int xt)
{
	x = xt;
}
void Zwierze::setY(int yt)
{
	y = yt;
}
void Zwierze::setXprev(int xt)
{
	xprev = xt;
}
void Zwierze::setYprev(int yt)
{
	yprev = yt;
}
int Zwierze::getSila()
{
	return sila;
}
void Zwierze::setSila(int x)
{
	sila += x;
}
int Zwierze::getInicjatywa()
{
	return inicjatywa;
}
char Zwierze::getSymbol()
{
	return symbol;
}
void Zwierze::wykonajRuch(Swiat& swiat, vector<Organizm*>&organizmy)
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
			xact--;
		}
		if (kierunek == 1)//>
		{
			yact++;
		}
		if (kierunek == 2)//v
		{
			xact++;
		}
		if (kierunek == 3)//<
		{
			yact--;
		}
		if (swiat.czyWPlanszy(swiat, xact, yact))  break;
	} while (1);
	x = xact;
	y = yact;
}
void Zwierze::zwierzeZabija(vector<Organizm*>& organizmy, int& j,Swiat& swiat)
{
	Organizm* ginie;
	int l = 0;
	ginie = swiat.getPlansza(x,y);
	while (ginie != organizmy[l]) l++;
	organizmy.erase(organizmy.begin() + l);
	j--;
}
void Zwierze::zwierzeGinie(vector<Organizm*>& organizmy, int& j)
{
	int l = 0;
	while (this != organizmy[l]) l++;
	organizmy.erase(organizmy.begin() + l);
	j--;
}
void Zwierze::zwierzeRodzi(Organizm* organizm, Swiat& swiat)
{
	int l = 0;
	bool flag = 0;
	int kierunek;
	int xact = x, yact = y;
	int sum = 0;
	Organizm* org;
	int x1 = this->getX(), y1 = this->getY();
	swiat.setPlanszaZnakow(xprev,yprev, this->getSymbol());
	this->setX(xprev);
	this->setY(yprev);
	this->setWykonalRuch(1);
	organizm->setWykonalRuch(1);
	swiat.setPlansza(xprev, yprev, this);
	if (swiat.czyWPlanszy(swiat, x1 + 1, y1)) 
	{ 
		org = swiat.getPlansza(x1 + 1, y1);
		if (swiat.getPlansza(x1 + 1, y1) != NULL) 
			sum++; 
	}
	else sum++;
	if (swiat.czyWPlanszy(swiat, x1 - 1, y1)) 
	{ 
		org = swiat.getPlansza(x1 - 1, y1);
		if (swiat.getPlansza(x1 - 1, y1) != NULL) 
			sum++; 
	}
	else sum++;
	if (swiat.czyWPlanszy(swiat, x1, y1 + 1)) 
	{ 
		org = swiat.getPlansza(x1, y1+1);
		if (swiat.getPlansza(x1, y1 + 1) != NULL) 
			sum++; 
	}
	else sum++;
	if (swiat.czyWPlanszy(swiat, x1, y - 1)) 
	{ 
		org = swiat.getPlansza(x1, y1-1);
		if (swiat.getPlansza(x1, y1 - 1) != NULL) 
			sum++; 
	}
	else sum++;
	if (sum == 4) return;
	do
	{
		xact = x1;
		yact = y1;
		kierunek = rand() % 4;
		if (kierunek == 0)//^
		{
			xact--;
		}
		if (kierunek == 1)//>
		{
			yact++;
		}
		if (kierunek == 2)//v
		{
			xact++;
		}
		if (kierunek == 3)//<
		{
			yact--;
		}
		if (!swiat.czyWPlanszy(swiat, xact, yact)) continue;
		org = swiat.getPlansza(xact, yact);
		if (org == NULL) break;

	} while (1);
	swiat.tworzZwierze(this->getNumerEnum(), xact, yact);
	cout << "Rodzi sie " << symbol << '\n';
}
void Zwierze::kolizja(Organizm* organizm,vector<Organizm*>&organizmy, int &j, Swiat &swiat)
{
	if (organizm->getSymbol() == 'Z')
	{
		organizm->kolizja(this, organizmy, j, swiat);
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
	else if (organizm->getSymbol() == symbol)
	{
		zwierzeRodzi(organizm, swiat);
	}
	else
	{
		zwierzeZabija(organizmy, j, swiat);
		cout << organizm->getSymbol() << " ginie od " << symbol << '\n';
		swiat.setPlansza(x, y, this);

	}
}