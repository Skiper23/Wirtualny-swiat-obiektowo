#pragma once
#include "Zwierze.h"
class Lis : public Zwierze
{
public:
	Lis(int x, int y);
	void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)override;
};

