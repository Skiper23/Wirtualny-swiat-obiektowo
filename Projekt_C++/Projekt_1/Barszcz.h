#pragma once
#include "Roslina.h"
class Barszcz : public Roslina
{
public:
	Barszcz(int x, int y);
	void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)override;
	void zabijaZwierze(Organizm* tmp, vector<Organizm*>& organizmy, Swiat& swiat, int x, int y);
};
