#pragma once
#include "Zwierze.h"
class Zolw : public Zwierze
{
public:
	Zolw(int x, int y);
	void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)override;
	void kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)override;
};

