#pragma once
#include <vector>
#include "Swiat.h"
#include "Organizm.h"
class Swiat;
class Zwierze : public Organizm
{
protected:
	int zycie,inicjatywa;
	int xprev, yprev;
	int numer_enum;
	bool wykonal_ruch;
public:
	int getX() override;
	int getY() override;
	int getSila() override;
	void setSila(int x);
	int getInicjatywa();
	char getSymbol() override;
	void setX(int x) override;
	void setY(int y)override;
	int getXprev()override;
	int getYprev()override;
	void setXprev(int x)override;
	void setYprev(int y)override;
	int getZycie();
	void setZycie(int x);
	bool getWykonalRuch();
	int getNumerEnum();
	void setWykonalRuch(bool r) override;
	virtual void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy);
	void zwierzeZabija(vector<Organizm*>&organizmy, int& j,Swiat& swiat);
	void zwierzeGinie(vector<Organizm*>&organizmy, int& j);
	void zwierzeRodzi(Organizm* organizmy, Swiat& swiat);
	void kolizja(Organizm *organizm, vector<Organizm*>&organizmy, int& j, Swiat& swiat);
};