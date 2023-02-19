#include "Organizm.h"
#include "Wilk.h"
#include "Zwierze.h"

Wilk::Wilk(int xt, int yt)
{
	x = xt;
	y = yt;
	xprev = x;
	yprev = y;
	symbol = 'W';
	sila = 9;
	inicjatywa = 5;
	zycie = 0;
	wykonal_ruch=1;
	numer_enum = 0;
}
