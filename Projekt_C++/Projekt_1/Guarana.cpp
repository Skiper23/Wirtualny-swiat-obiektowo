#include"Guarana.h"
Guarana::Guarana(int xt, int yt)
{
	x = xt;
	y = yt;
	symbol = 'g';
	sila = 0;
	numer_enum = 2;
	wykonal_ruch = 1;
	zycie = 0;
}
void Guarana::kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)
{
	organizm->setSila(3);
}