#include "Organizm.h"
#include "Czlowiek.h"

Czlowiek::Czlowiek(int xt, int yt)
{
	x = xt;
	y = yt;
	symbol = '#';
	sila =5;
	inicjatywa = 4;
	zycie = 0;
	wykonal_ruch = 1;
	numer_enum = 6;
	bron = -5;
}
void Czlowiek::setBron(int x)
{
	bron = x;
}
int Czlowiek::getBron()
{
	return bron;
}
void Czlowiek::setKierunek(int x)
{
	kierunek = x;
}
void Czlowiek::wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)
{
	if (kierunek == 0)//^
	{
		if(x>0)
		x--;
	}
	if (kierunek == 1)//>
	{
		if(y<swiat.getRozmiarY()-1)
		y++;

	}
	if (kierunek == 2)//v
	{
		if(x<swiat.getRozmiarX()-1)
		x++;
	}
	if (kierunek == 3)//<
	{
		if(y>0)
		y--;
	}
}
void Czlowiek::kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)
{
	if (bron > 0&&organizm->getSymbol()>='A'&&organizm->getSymbol()<='Z')
	{
		Organizm* org;
		int kierunek;
		organizm->setXprev(x);
		organizm->setYprev(y);
		org = swiat.getPlansza(x, y);

		do
		{

			kierunek = rand() % 4;
			if (y == 0) kierunek = 1;
			if (x == swiat.getRozmiarX()-1) kierunek = 0;
			if (y == swiat.getRozmiarY()-1) kierunek = 3;
			if (x == 0) kierunek = 2;
			organizm->setX(xprev);
			organizm->setY(yprev);
			if (kierunek == 0)//^
			{
				organizm->setX(organizm->getX()-1);
			}
			if (kierunek == 1)//>
			{
				organizm->setY(organizm->getY() + 1);
			}
			if (kierunek == 2)//v
			{
				organizm->setX(organizm->getX() + 1);
			}
			if (kierunek == 3)//<
			{
				organizm->setY(organizm->getY() - 1);
			}
			org = swiat.getPlansza(organizm->getX(), organizm->getY());

		} while (org != NULL);
		cout << "# odpycha " << organizm->getSymbol() << '\n';
		swiat.setPlansza(organizm->getX(), organizm->getY(), organizm);
		return;
	}
	if (organizm->getSymbol() == 'g')
	{
		organizm->kolizja(this, organizmy, j, swiat);
	}
	if (organizm->getSila()>sila)
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