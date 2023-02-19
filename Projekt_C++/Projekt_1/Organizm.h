#pragma once
#include <iostream>
#include <string>
#include <vector>
using namespace std;
class Zwierze;
class Swiat;
class Organizm
{
protected:
	char symbol;
	//string nazwa;
	int x, y, sila, zycie;
	
	bool wykonal_ruch;
public:
	//Organizm(int x, int y);
	virtual int getX()=0;
	virtual int getY()=0;
	virtual int getSila()=0;
	virtual void setSila(int x) = 0;
	virtual int getInicjatywa()=0;
	virtual char getSymbol()=0;//zwraca symbol
	virtual void setX(int x)=0;
	virtual void setY(int y)=0;
	virtual int getXprev()=0;
	virtual int getYprev()=0;
	virtual void setXprev(int x) = 0;
	virtual void setYprev(int y) = 0;
	virtual bool getWykonalRuch() = 0;
	virtual void setWykonalRuch(bool r) = 0;
	virtual int getZycie() = 0;
	virtual void setZycie(int x) = 0;
	virtual void wykonajRuch(Swiat& swiat, vector<Organizm*>& organizmy) = 0;//akcja()
	virtual void kolizja(Organizm *organizm, vector<Organizm*>&organizmy, int& j, Swiat& swiat)=0;
};