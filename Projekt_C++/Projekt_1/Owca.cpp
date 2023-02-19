#include "Organizm.h"
#include "Owca.h"
#include "Zwierze.h"

Owca::Owca(int xt, int yt)
{
	x = xt;
	y = yt;
	xprev = x;
	yprev = y;
	symbol = 'O';
	sila = 4;
	inicjatywa = 4;
	zycie = 0;
	wykonal_ruch = 1;
	numer_enum = 1;
}