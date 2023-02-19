#include"Barszcz.h"
Barszcz::Barszcz(int xt, int yt)
{
	x = xt;
	y = yt;
	symbol = 'b';
	sila = 10;
	numer_enum = 4;
	zycie = 0;
	wykonal_ruch = 1;
}
void Barszcz::zabijaZwierze(Organizm* tmp, vector<Organizm*>& organizmy,Swiat& swiat,int x,int y)
{
	if (tmp->getSymbol() >= 'A' && tmp->getSymbol() <= 'Z'|| tmp->getSymbol()=='#')
	{
		int l = 0;
		while (tmp != organizmy[l]) l++;
		organizmy.erase(organizmy.begin() + l);
		swiat.setPlansza(x, y);
		cout << "b zabija " << tmp->getSymbol() << '\n';
	}
}
void Barszcz::wykonajRuch(Swiat &swiat, vector<Organizm*>& organizmy)
{
	int kierunek = rand() % 4;
	Organizm* tmp;
		if (swiat.czyWPlanszy(swiat,x+1,y))
		{
			if (swiat.getPlansza(x + 1,y) != NULL)
			{
				zabijaZwierze(swiat.getPlansza(x + 1,y), organizmy,swiat,x+1,y);
			}
		}
		if (swiat.czyWPlanszy(swiat, x - 1, y))
		{
			if (swiat.getPlansza(x - 1,y) != NULL)
			{
				zabijaZwierze(swiat.getPlansza(x - 1,y), organizmy,swiat,x-1,y);
			}
		}
		if (swiat.czyWPlanszy(swiat, x, y+1))
		{
			if (swiat.getPlansza(x,y + 1) != NULL)
			{
				zabijaZwierze(swiat.getPlansza(x,y + 1), organizmy,swiat,x,y+1);
			}
		}
		if (swiat.czyWPlanszy(swiat, x, y-1))
		{
			if (swiat.getPlansza(x,y - 1) != NULL)
			{
				zabijaZwierze(swiat.getPlansza(x,y - 1), organizmy,swiat,x,y-1);
			}
		}
}