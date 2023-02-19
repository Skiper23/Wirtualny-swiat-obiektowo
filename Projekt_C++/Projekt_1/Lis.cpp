#include "Lis.h"

Lis::Lis(int xt, int yt)
{
	x = xt;
	y = yt;
	xprev = x;
	yprev = y;
	symbol = 'L';
	sila = 3;
	inicjatywa = 7;
	zycie = 0;
	wykonal_ruch = 1;
	numer_enum = 2;
}
void Lis::wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)
{
	int kierunek,sum=0, xact = x, yact = y;
	Organizm* org;
	if (swiat.czyWPlanszy(swiat, x + 1, y))
	{
		if (swiat.getPlansza(x + 1, y) != NULL)
		{
			org = swiat.getPlansza(x + 1, y);
			if(org->getSila() > sila)
				sum++;
		}
	}
	else sum++;
	if (swiat.czyWPlanszy(swiat, x - 1, y))
	{
		if (swiat.getPlansza(x - 1, y) != NULL)
		{
			org = swiat.getPlansza(x - 1, y);
			if (org->getSila() > sila)
				sum++;
		}
	}
	else sum++;
	if (swiat.czyWPlanszy(swiat, x, y + 1))
	{ 
		if (swiat.getPlansza(x, y + 1) != NULL)
		{
			org = swiat.getPlansza(x, y+1);
			if (org->getSila() > sila)
				sum++;
		}
	}
	else sum++;
	if (swiat.czyWPlanszy(swiat, x, y - 1)) 
	{ 
		if (swiat.getPlansza(x, y - 1) != NULL)
		{
			org = swiat.getPlansza(x, y-1);
			if (org->getSila() > sila)
				sum++;
		}
	}
	else sum++;
	if (sum == 4) return;
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
		if (swiat.czyWPlanszy(swiat, xact, yact))
		{
			org = swiat.getPlansza(xact, yact);
			if (org == NULL) break;
			if (org->getSila() < sila) break;
		}

	} while (1);
		x = xact;
		y = yact;
}
