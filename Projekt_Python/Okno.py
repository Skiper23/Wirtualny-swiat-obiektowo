import pygame, sys
from pygame.locals import *
from Swiat import Swiat
from zwierzeta.Czlowiek import Czlowiek


class Okno:
    pygame.init()

    def __init__(self):
        self.swiat = Swiat(20, 20)
        self.window = pygame.display.set_mode([1000, 800])
        self.window.fill((128, 128, 128))
        pygame.display.set_caption("Wirtualny swiat")
        self.color_przyciski = (249, 224, 75)
        self.czlowiek_copy = None

    def generujOkienko(self):

        pygame.draw.rect(self.window, [128, 128, 128],
                         (self.window.get_width() * 0.7, 0, self.window.get_width() * 0.3, self.window.get_height()))

        pygame.draw.rect(self.window, self.color_przyciski, (self.window.get_width() * 0.72, 20, 260, 50))
        font = pygame.font.SysFont('comicsans', 30)
        tekst = font.render('NOWA TURA ', True, (0, 0, 0))
        self.window.blit(tekst, (self.window.get_width() * 0.75, 20))
        pygame.draw.rect(self.window, self.color_przyciski, (self.window.get_width() * 0.72, 90, 260, 50))

        tekst = font.render('ZAPISZ GRĘ ', True, (0, 0, 0))
        self.window.blit(tekst, (self.window.get_width() * 0.76, 90))
        pygame.draw.rect(self.window, self.color_przyciski, (self.window.get_width() * 0.72, 160, 260, 50))

        tekst = font.render('WCZYTAJ GRĘ ', True, (0, 0, 0))
        self.window.blit(tekst, (self.window.get_width() * 0.74, 160))

        pygame.draw.rect(self.window, [192, 192, 192], (self.window.get_width() * 0.72, 220, 260, 270))

        pygame.draw.rect(self.window, [192, 192, 192], (self.window.get_width() * 0.72, 510, 260, 280))

        font = pygame.font.SysFont('comicsans', 20)
        pozycjax_napisu = self.window.get_width() * 0.77
        self.window.blit(font.render('Wilk ', True, (0, 0, 0)), (pozycjax_napisu, 223))
        pygame.draw.rect(self.window, (105, 105, 105), (self.window.get_width() * 0.90, 225, 20, 20))

        self.window.blit(font.render('Owca ', True, (0, 0, 0)), (pozycjax_napisu, 245))
        pygame.draw.rect(self.window, (224, 255, 255), (self.window.get_width() * 0.90, 247, 20, 20))

        self.window.blit(font.render('Lis ', True, (0, 0, 0)), (pozycjax_napisu, 267))
        pygame.draw.rect(self.window, (255, 165, 0), (self.window.get_width() * 0.90, 269, 20, 20))

        self.window.blit(font.render('Żółw ', True, (0, 0, 0)), (pozycjax_napisu, 289))
        pygame.draw.rect(self.window, (47, 79, 79), (self.window.get_width() * 0.90, 291, 20, 20))

        self.window.blit(font.render('Antylopa ', True, (0, 0, 0)), (pozycjax_napisu, 311))
        pygame.draw.rect(self.window, (184, 134, 11), (self.window.get_width() * 0.90, 313, 20, 20))

        self.window.blit(font.render('CyberOwca ', True, (0, 0, 0)), (pozycjax_napisu, 333))
        pygame.draw.rect(self.window, (128, 0, 128), (self.window.get_width() * 0.90, 335, 20, 20))

        self.window.blit(font.render('Czlowiek ', True, (0, 0, 0)), (pozycjax_napisu, 355))
        pygame.draw.rect(self.window, (250, 128, 114), (self.window.get_width() * 0.90, 357, 20, 20))

        self.window.blit(font.render('Trawa ', True, (0, 0, 0)), (pozycjax_napisu, 377))
        pygame.draw.rect(self.window, (0, 255, 0), (self.window.get_width() * 0.90, 379, 20, 20))

        self.window.blit(font.render('Mlecz ', True, (0, 0, 0)), (pozycjax_napisu, 399))
        pygame.draw.rect(self.window, (255, 255, 0), (self.window.get_width() * 0.90, 401, 20, 20))

        self.window.blit(font.render('Guarana ', True, (0, 0, 0)), (pozycjax_napisu, 421))
        pygame.draw.rect(self.window, (255, 0, 0), (self.window.get_width() * 0.90, 423, 20, 20))

        self.window.blit(font.render('Jagoda ', True, (0, 0, 0)), (pozycjax_napisu, 443))
        pygame.draw.rect(self.window, (0, 0, 255), (self.window.get_width() * 0.90, 445, 20, 20))

        self.window.blit(font.render('Barszcz ', True, (0, 0, 0)), (pozycjax_napisu, 465))
        pygame.draw.rect(self.window, (0, 0, 0), (self.window.get_width() * 0.90, 467, 20, 20))

        self.swiat.generujSwiat()
        self.tworzPlansze()

    def tworzPlansze(self):
        x = 31
        y = 75
        for i in range(0, 20):
            for j in range(0, 20):
                organizm = self.swiat.getPlansza(i, j)

                if organizm == None:
                    pygame.draw.rect(self.window, [255, 255, 255], (x, y, 31, 31))
                else:
                    if isinstance(organizm, Czlowiek):
                        self.czlowiek_copy = organizm
                    pygame.draw.rect(self.window, organizm._kolor, (x, y, 31, 31))
                x += 32
            y += 32
            x = 31
        font = pygame.font.SysFont('comicsans', 12)
        pozycjax_raportu = self.window.get_width() * 0.73
        pozycjay_raportu = 515
        pygame.draw.rect(self.window, [192, 192, 192], (self.window.get_width() * 0.72, 510, 260, 280))

        for i in range(0, self.swiat.getRaportSize()):
            if i == 18:
                pozycjax_raportu = self.window.get_width() * 0.82
                pozycjay_raportu = 515
            elif i == 36:
                pozycjax_raportu = self.window.get_width() * 0.91
                pozycjay_raportu = 515
            self.window.blit(font.render(self.swiat.getRaport(i), True, (0, 0, 0)),
                             (pozycjax_raportu, pozycjay_raportu))
            pozycjay_raportu += 15

        self.swiat.setRaportClear()
        self.symuluj()

    def symuluj(self):
        print()
        self.czlowiek_copy.setBron(self.czlowiek_copy.getBron() - 1)
        while True:
            for event in pygame.event.get():  # iterujemy się po wszystkich zebranych zdarzeniach
                if event.type == pygame.QUIT:  # sprawdzamy czy podane zdarzenie dotyczy zamknięcia okna
                    pygame.quit()
                    sys.exit()
                key = pygame.key.get_pressed()  # zbieramy wszystkie naciśnięte klawisze
                if key[pygame.K_UP]:
                    self.czlowiek_copy.setKierunek(0)
                    self.swiat.wyoknajTure()
                    self.tworzPlansze()
                elif key[pygame.K_RIGHT]:
                    self.czlowiek_copy.setKierunek(1)
                    self.swiat.wyoknajTure()
                    self.tworzPlansze()
                elif key[pygame.K_DOWN]:
                    self.czlowiek_copy.setKierunek(2)
                    self.swiat.wyoknajTure()
                    self.tworzPlansze()
                elif key[pygame.K_LEFT]:
                    self.czlowiek_copy.setKierunek(3)
                    self.swiat.wyoknajTure()
                    self.tworzPlansze()
                elif key[pygame.K_SPACE]:
                    print(self.czlowiek_copy.getBron())
                    if self.czlowiek_copy.getBron() > 0:
                        self.swiat.setRaport("Moc specjalna jest aktywna")
                    elif self.czlowiek_copy.getBron() > -4:
                        self.swiat.setRaport("Nie mozesz aktywowac mocy specjalnej")
                    else:
                        self.swiat.setRaport("Moc specjalna aktywowana")
                        self.czlowiek_copy.setBron(5)
                    self.tworzPlansze()

                if event.type == pygame.MOUSEBUTTONDOWN:
                    mouse = pygame.mouse.get_pos()
                    if 720 <= mouse[0] <= 980 and 20 <= mouse[1] <= 70:
                        self.swiat.wyoknajTure()
                        self.tworzPlansze()
                    if 720 <= mouse[0] <= 980 and 90 <= mouse[1] <= 140:
                        self.swiat.zapisDoPliku()
                    if 720 <= mouse[0] <= 980 and 160 <= mouse[1] <= 210:
                        self.swiat.odczytZPliku()
                        self.tworzPlansze()

                pygame.display.update()
