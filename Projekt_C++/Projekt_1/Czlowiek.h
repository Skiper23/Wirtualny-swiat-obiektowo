#pragma once
#include "Zwierze.h"
class Czlowiek : public Zwierze
{
	int kierunek;
	int bron;
public:
	Czlowiek(int x, int y);
	void setKierunek(int x);
	void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy)override;
	void setBron(int x);
	int getBron();
	void kolizja(Organizm* organizm, vector<Organizm*>& zwierzeta, int& j, Swiat& swiat)override;
};