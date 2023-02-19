#pragma once
#include "Swiat.h"
#include "Organizm.h"
class Roslina :public Organizm
{
protected:
	int numer_enum;
	bool wykonal_ruch;
	int const inicjatywa = 0;
	int zycie;
public:
	int getX() override;
	int getY() override;
	int getSila() override;
	void setSila(int x)override;
	int getInicjatywa()override;
	char getSymbol() override;
	void setX(int x) override;
	void setY(int y)override;
	void setXprev(int x) override;
	void setYprev(int y)override;
	bool getWykonalRuch() override;
	void setWykonalRuch(bool r) override;
	int getZycie()override;
	void setZycie(int x) override;
	int getXprev()override;
	int getYprev()override;
	int getNumerEnum();
	virtual void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy);
	void kolizja(Organizm* organizm, vector<Organizm*> &organizmy, int& j, Swiat& swiat);
};