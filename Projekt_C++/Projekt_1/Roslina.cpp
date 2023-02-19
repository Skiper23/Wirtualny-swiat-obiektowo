#include "Roslina.h"
#include "Swiat.h"
#include <vector>
int Roslina::getNumerEnum()
{
	return numer_enum;
}
void Roslina::setWykonalRuch(bool r)
{
	wykonal_ruch = r;
}
bool Roslina::getWykonalRuch()
{
	return wykonal_ruch;
}
int Roslina::getZycie()
{
	return zycie;
}
void Roslina::setZycie(int x)
{
	zycie = x;
}
int Roslina::getX()
{
	return x;
}
int Roslina::getY()
{
	return y;
}
int Roslina::getXprev()
{
	return -1;
}
int Roslina::getYprev()
{
	return -1;
}
void Roslina::setX(int xt)
{
	x = xt;
}
void Roslina::setY(int yt)
{
	y = yt;
}
void Roslina::setXprev(int xt)
{
	return;
}
void Roslina::setYprev(int yt)
{
	return;
}
int Roslina::getSila()
{
	return sila;
}
void Roslina::setSila(int x)
{
	sila += x;
}
char Roslina::getSymbol()
{
	return symbol;
}
int Roslina::getInicjatywa()
{
	return inicjatywa;
}
void Roslina::wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)
{
	int kierunek = rand() % 4;
	int xact = x, yact = y;
	int sum = 0;
	Organizm* org;
	if (kierunek == 0)
	{
		if (swiat.czyWPlanszy(swiat, x + 1, y)) { if (swiat.getPlansza(x + 1, y) != NULL) sum++; }
		else sum++;
		if (swiat.czyWPlanszy(swiat, x - 1, y)) { if (swiat.getPlansza(x - 1, y) != NULL) sum++; }
		else sum++;
		if (swiat.czyWPlanszy(swiat, x, y+1)) { if (swiat.getPlansza(x, y+1) != NULL) sum++; }
		else sum++;
		if (swiat.czyWPlanszy(swiat, x, y-1)) { if (swiat.getPlansza(x, y-1) != NULL) sum++; }
		else sum++;
		if (sum==4) return;
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
			if (!swiat.czyWPlanszy(swiat, xact, yact)) continue;
			org = swiat.getPlansza(xact, yact);
			if (org == NULL) break;

		} while (1);
		swiat.tworzRosline(this->getNumerEnum(),xact,yact);
		cout << "rozrasta sie " << symbol << endl;
	}
}

void Roslina::kolizja(Organizm* organizm, vector<Organizm*> &organizmy, int& j, Swiat& swiat)
{
}