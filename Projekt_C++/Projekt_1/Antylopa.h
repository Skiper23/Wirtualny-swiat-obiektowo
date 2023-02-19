#pragma once
#include "Zwierze.h"
class Antylopa : public Zwierze
{
public:
	Antylopa(int x, int y);
	void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)override;
	void kolizja(Organizm* organizm, vector<Organizm*>& organizmy, int& j, Swiat& swiat)override;
};

