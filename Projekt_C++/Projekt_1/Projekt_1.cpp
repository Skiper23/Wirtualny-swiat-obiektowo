#pragma warning( disable : 4996 )
#include <iostream>
#include <time.h>
#include <conio.h>
#include <string>
#include <fstream>
#include "Swiat.h"
#include "Czlowiek.h"
using namespace std;
int main()
{
	unsigned char znak;
	pair<int, int> wsp;
	int x, y,rozx=0,rozy, opcja,xc,yc;
	bool flag_tura = 0;
	char o;
	string nazwa,wiersz;
	Czlowiek* czlowiek;
	srand(time(NULL));
	cout << "Odczyt danych z pliku - O, Generowanie nowej symultacji G\n";
	cin >> o;
	if (o == 'O')
	{
		cout << "Podaj nazwe pliku z rozszerzeniem (upewnij sie ze taki plik istnieje):";
		cin >> nazwa;
		fstream in;
		in.open(nazwa);
		if (in.is_open())
		{
			while (getline(in, wiersz))
			{
				rozy = wiersz.size();
				for (int i = 0; i < rozy; i++)
				{
					if (wiersz[i] == '#')
					{
						wsp = make_pair(rozx, i);
					}
				}
				rozx++;
			}
		}
		else
		{
			cout << "Nastepnym razem sprawdz czy plik istnieje!";
			return 0;
		}
		in.close();	
	}
	else
	{
		cout << "Podaj rozmiary planszy x y:";
		cin >> rozx >> rozy;
	}
	Swiat swiat(rozx, rozy);
	if (o == 'G')
	{
		wsp = swiat.losujPozycje();
	}

	czlowiek = new Czlowiek(wsp.first, wsp.second);
	swiat.setPlansza(wsp.first, wsp.second, czlowiek->getSymbol(), czlowiek);
	
	if (o == 'G')
	{
		for (int i = 0; i < swiat.getRozmiarX() - 1; i++)
		{
			opcja = rand() % 5;
			wsp = swiat.losujPozycje();
			x = wsp.first;
			y = wsp.second;
			swiat.tworzZwierze(opcja, x, y);
		}
		for (int i = 0; i < swiat.getRozmiarY(); i++)
		{
			opcja = rand() % 5;
			wsp = swiat.losujPozycje();
			x = wsp.first;
			y = wsp.second;
			swiat.tworzRosline(opcja, x, y);
		}
	}
	else
	{
		swiat.odczytZPliku(nazwa);
	}
	swiat.rysujSwiat();
	swiat.wykonajTure();
	znak = getch();
	while (znak!=27)
	{
		system("cls");
		switch (znak)
		{
			case 224:
				znak = getch();
				switch (znak)
				{
				case 72: //strzałka w górę
					czlowiek->setKierunek(0);
					break;
				case 80: //strzałka w dół
					czlowiek->setKierunek(2);
					break;
				case 75: //strzałka w 
					czlowiek->setKierunek(3);
					break;
				case 77: //strzałka w prawo
					czlowiek->setKierunek(1);
					break;
				}
				break;
			case 13:
			{
				if (czlowiek->getBron() > 0)
					cout << "Moc specjalna jest aktywna\n";
				else if (czlowiek->getBron() > -4)
					cout << "Nie mozesz jeszce aktywowac mocy specjalnej \n";
				else
				{
					cout << "Moc specjalna aktywowana\n";
					czlowiek->setBron(5);
				}
				break;
			}
			case 115:
			{
				flag_tura = 1;
				swiat.zapisDoPliku();
				break;
			}
		}
		if (flag_tura == 0)
		{
			swiat.wykonajTure();
		}
		else
		{
			cout << "Zapisany stan \n";
			flag_tura = 0;
		}
		swiat.rysujSwiat();
		znak = getch();
		if (czlowiek->getBron() > -5)
			czlowiek->setBron(czlowiek->getBron() - 1);
	}
	return 0;
}

