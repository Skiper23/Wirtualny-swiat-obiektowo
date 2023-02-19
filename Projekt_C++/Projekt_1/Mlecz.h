#pragma once
#include "Roslina.h"
class Mlecz : public Roslina
{
public:
	Mlecz(int x, int y);
	void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)override;
};
