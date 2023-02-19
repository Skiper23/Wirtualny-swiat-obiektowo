#pragma once
#include "Roslina.h"
class Guarana : public Roslina
{
	const int zwieksz_sile=3;
public:
	Guarana(int x, int y);
	void kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)override;
};

