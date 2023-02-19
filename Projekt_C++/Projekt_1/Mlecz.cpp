#include"Mlecz.h"
Mlecz::Mlecz(int xt, int yt)
{
	x = xt;
	y = yt;
	symbol = 'm';
	sila = 0;
	numer_enum = 1;
	wykonal_ruch = 1;
	zycie = 0;
}
void Mlecz::wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)
{
	int kierunek;
	int xact = x, yact = y;
	int sum = 0;
	Organizm* org;
	for (int i = 0; i < 3; i++)
	{
		kierunek = rand() % 4;
		if (kierunek == 0)
		{
			if (swiat.czyWPlanszy(swiat, x + 1, y)) { if (swiat.getPlansza(x + 1, y) != NULL) sum++; }
			else sum++;
			if (swiat.czyWPlanszy(swiat, x - 1, y)) { if (swiat.getPlansza(x - 1, y) != NULL) sum++; }
			else sum++;
			if (swiat.czyWPlanszy(swiat, x, y + 1)) { if (swiat.getPlansza(x, y + 1) != NULL) sum++; }
			else sum++;
			if (swiat.czyWPlanszy(swiat, x, y - 1)) { if (swiat.getPlansza(x, y - 1) != NULL) sum++; }
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
				if (!swiat.czyWPlanszy(swiat, xact, yact)) continue;
				org = swiat.getPlansza(xact, yact);
				if (org == NULL) break;

			} while (1);
			swiat.tworzRosline(this->getNumerEnum(), xact, yact);
			cout << "rozrasta sie m\n";
		}
		sum = 0;
	}

}
