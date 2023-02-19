package po.java;
import po.java.zwierzeta.Czlowiek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Okienko extends JFrame implements ActionListener {

    private Swiat swiat;
    private final JPanel legenda;
    private final JPanel pole;
    private final JFrame tlo;
    private final JPanel plansza;
    private final JButton przycisk;

    private final JPanel menu;
    private final JPanel  raport;
    private final JFrame tlom;

    private final JTextField rozx;
    private final JTextField rozy;

    private Czlowiek czlowiekcopy;
    Action prawoRuch;
    Action lewoRuch;
    Action goraRuch;
    Action dolRuch;
    Action aktywacjaUmiejetnosci;

    public Okienko ()
    {
        tlo=new JFrame();
        legenda= new JPanel();
        pole=new JPanel();
        przycisk=new JButton("Rozpocznij!");
        tlom=new JFrame();
        rozx=new JTextField();
        rozy=new JTextField();
        plansza=new JPanel();
        menu= new JPanel();
        raport= new JPanel();
        raport.setLayout(new BorderLayout());

        prawoRuch= new PrawoRuch();
        lewoRuch= new LewoRuch();
        goraRuch= new GoraRuch();
        dolRuch= new DolRuch();
        aktywacjaUmiejetnosci=new AktywacjaUmiejetnosci();

        plansza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0),"prawoRuch");
        plansza.getActionMap().put("prawoRuch",prawoRuch);
        plansza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0),"lewoRuch");
        plansza.getActionMap().put("lewoRuch",lewoRuch);
        plansza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP,0),"goraRuch");
        plansza.getActionMap().put("goraRuch",goraRuch);
        plansza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0),"dolRuch");
        plansza.getActionMap().put("dolRuch",dolRuch);
        plansza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER ,0),"aktywacjaUmiejetnosci");
        plansza.getActionMap().put("aktywacjaUmiejetnosci",aktywacjaUmiejetnosci);
    }
    public JPanel getPole() {
        return pole;
    }
    void addRozmiarPanel(String lab, JTextField tf,JPanel cp)
    {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel l = new JLabel(lab,SwingConstants.LEFT);
        l.setPreferredSize(new Dimension(100,20));
        l.setHorizontalAlignment(JLabel.RIGHT);
        tf.setPreferredSize(new Dimension(180,20));
        p.add(l);
        p.add(tf);
        cp.add(p);
    }
    public void createMenu()
    {
        GridBagConstraints rozmieszczenie= new GridBagConstraints();
        tlom.setTitle("Menu");
        tlom.setSize(400,200);
        JPanel wartosc=new JPanel();

        wartosc.setLayout(new BoxLayout(wartosc, BoxLayout.Y_AXIS));
        wartosc.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        addRozmiarPanel("Podaj rozmiar X: ",rozx,wartosc);
        addRozmiarPanel("Podaj rozmiar Y: ",rozy,wartosc);

        przycisk.addActionListener(this);
        wartosc.add(przycisk);

        tlom.setLayout(new GridBagLayout());
        rozmieszczenie.weightx=1;
        rozmieszczenie.weighty=1;
        rozmieszczenie.insets=new Insets(20,20,20,20);
        rozmieszczenie.fill=GridBagConstraints.BOTH;
        tlom.add(wartosc,rozmieszczenie);

        tlom.setVisible(true);
    }
    private void stworzPole()
    {
        for(int i=0; i<swiat.getRozmiarx(); i++)
        {
            for(int j=0; j<swiat.getRozmiary(); j++) {
                JButton p= new JButton();
                if(swiat.getPlansza(i,j) instanceof Czlowiek)
                    czlowiekcopy=(Czlowiek)swiat.getPlansza(i,j);
                if (swiat.getPlansza(i,j)!=null)
                    p.setBackground(swiat.getPlansza(i,j).getKolor());
                else   p.setBackground(Color.WHITE);

                p.setPreferredSize(new Dimension(10,10));
                p.addActionListener(this);
                pole.add(p);
            }
        }
    }
    public void rysujPlanszeOkno(Swiat swiat)
    {

        raport.removeAll();
        TextArea tx = new TextArea();
        tx.setSize(new Dimension(600,500));

        Component[] p = getPole().getComponents();
        int q = 0;
        for (int k = 0; k < swiat.getRozmiarx(); k++) {
            for (int j = 0; j < swiat.getRozmiary(); j++) {
                if (swiat.getPlansza(k, j) != null) {
                    p[q].setBackground(swiat.getPlansza(k, j).getKolor());
                }
                else p[q].setBackground(Color.WHITE);
                q++;
            }
        }
        String tekst="";
        for(int i=0; i<swiat.getRaportSize();i++)
            tekst=tekst+swiat.getRaport(i)+'\n';

        raport.add(tx,BorderLayout.CENTER);
        tx.setText(tekst);

        swiat.setRaportClear();
        revalidate();
        repaint();
        czlowiekcopy.setBron(czlowiekcopy.getBron()-1);
    }
    void addLegenda(String lab, JPanel cp, Color kolor)
    {
        JLabel p = new JLabel(lab,SwingConstants.CENTER);
        JPanel panel=new JPanel();
        JPanel l = new JPanel();
        p.setPreferredSize(new Dimension(60,20));
        p.setHorizontalAlignment(JLabel.RIGHT);
        l.setBackground(kolor);
        panel.add(p);
        panel.add(l);
        cp.add(panel);
    }
    public void createOkienko(Swiat swiat)
    {

        GridBagConstraints rozmieszczenie= new GridBagConstraints();
        GridBagConstraints rozmieszczenie1= new GridBagConstraints();
        tlo.setTitle("TEST");
        tlo.setSize(1200,800);
        plansza.setBackground(Color.ORANGE);
        legenda.setBackground(Color.BLUE);
        plansza.setLayout(new GridBagLayout());
        pole.setLayout(new GridLayout(swiat.getRozmiarx(), swiat.getRozmiary()));
        rozmieszczenie1.weightx=1;
        rozmieszczenie1.weighty=1;
        rozmieszczenie1.insets=new Insets(20,20,20,20);
        rozmieszczenie1.fill=GridBagConstraints.BOTH;
        plansza.add(pole,rozmieszczenie1);
        stworzPole();
        menu.setLayout(new GridLayout(2,1));
        legenda.setLayout(new GridLayout(14,1));
        rozmieszczenie.weightx=1;

        legenda.add(new JButton("Kolejna tura"));
        legenda.add(new JButton("Zapisz grę"));
        legenda.add(new JButton("Wczytaj grę"));

        addLegenda("Wilk",legenda, Color.DARK_GRAY);
        addLegenda("Owca",legenda, Color.CYAN);
        addLegenda("Lis",legenda, Color.lightGray);
        addLegenda("Żółw",legenda, Color.orange);
        addLegenda("Antylopa",legenda, Color.gray);
        addLegenda("Czlowiek",legenda, Color.pink);
        addLegenda("Trawa",legenda, Color.green);
        addLegenda("Mlecz",legenda, Color.yellow);
        addLegenda("Guarana",legenda, Color.red);
        addLegenda("Jagoda",legenda, Color.blue);
        addLegenda("Barszcz",legenda, Color.black);
        Component[] leg=legenda.getComponents();

        for(int j=0;j<3;j++) {
           JButton k ;
           k=(JButton) leg[j];
            k.addActionListener(this);
        }

        rozmieszczenie.fill=GridBagConstraints.BOTH;
        rozmieszczenie.weighty=0.7;
        rozmieszczenie.weightx=0.7;

        menu.add(legenda,rozmieszczenie);
        rozmieszczenie.weighty=0.3;

        menu.add(raport,rozmieszczenie);
        tlo.setLayout(new GridBagLayout());
        tlo.setFocusable(true);

        rozmieszczenie.weighty=1;
        rozmieszczenie.weightx=0.7;
        tlo.add(plansza,rozmieszczenie);

        rozmieszczenie.weightx=0.3;
        tlo.add(menu,rozmieszczenie);

        tlo.setDefaultCloseOperation(tlo.EXIT_ON_CLOSE);
        tlo.setVisible(true);
        requestFocusInWindow();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Component[] p= legenda.getComponents();
        Component[] przyciski= pole.getComponents();
        tlo.setFocusable(true);
        int y;
        int x1;
        if(p.length>0) {

            if (source == p[0]) {
                swiat.wyoknajTure();
                rysujPlanszeOkno(swiat);
            }
            if (source == p[1]) {
                try {
                    swiat.zapisDoPliku();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (source == p[2]) {
                try {
                    File file= new File("organizmy.txt");
                    Scanner in = new Scanner(file);
                    String zdanie=in.nextLine();
                    String []wyrazy=zdanie.split(" ");
                    x1 =Integer.parseInt(wyrazy[0]);
                    y =Integer.parseInt(wyrazy[1]);
                    swiat=null;
                    swiat=new Swiat(x1, y);
                    swiat.odczytZPliku(file,in);
                    pole.setLayout(new GridLayout(swiat.getRozmiarx(), swiat.getRozmiary()));
                    stworzPole();
                    rysujPlanszeOkno(swiat);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {
                int xt,yt;

                for (int x = 0; x < przyciski.length; x++) {
                    if (source == przyciski[x]) {
                        xt=x/ swiat.getRozmiarx();
                        yt=x%swiat.getRozmiarx();

                        String[] choices = { "1.Wilk","2.Owca","3.Lis","4.Zolw","5.Antylopa","1.trawa","2.mlecz","3.guarana","4.jagoda","5.barszcz"};

                        String input = (String) JOptionPane.showInputDialog(null, "Wybierz", "Stwórz organizm", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
                        char zw=input.charAt(2),zwi=input.charAt(0);
                        int zn;
                        if(zw>='a'&&zw<='z') {
                            zn=(int)zwi-'1';
                            swiat.tworzRosline(zn, xt, yt);
                        }
                        else {
                            zn=(int)zwi-'1';
                            swiat.tworzZwierze(zn, xt, yt);
                        }
                        rysujPlanszeOkno(swiat);
                        break;
                    }
                }
            }
        }
        if (source==przycisk)
        {
            x1 =Integer.parseInt( rozx.getText());
            y =Integer.parseInt( rozy.getText());
            swiat=new Swiat(x1, y);
            swiat.generujSwiat(this);
            tlom.dispose();
        }
    }
    private class PrawoRuch extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            czlowiekcopy.setKierunek(1);
            swiat.wyoknajTure();
            rysujPlanszeOkno(swiat);
        }
    }
    private class LewoRuch extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            czlowiekcopy.setKierunek(3);
            swiat.wyoknajTure();
            rysujPlanszeOkno(swiat);
        }
    }
    private class GoraRuch extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            czlowiekcopy.setKierunek(0);
            swiat.wyoknajTure();
            rysujPlanszeOkno(swiat);
        }
    }
    private class DolRuch extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            czlowiekcopy.setKierunek(2);
            swiat.wyoknajTure();
            rysujPlanszeOkno(swiat);
        }
    }
    private class AktywacjaUmiejetnosci extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (czlowiekcopy.getBron() > 0)
                swiat.setRaport("Moc specjalna jest aktywna");
            else if (czlowiekcopy.getBron() > -4)
                swiat.setRaport("Nie mozesz jeszce aktywowac mocy specjalnej");
            else
            {
                swiat.setRaport("Moc specjalna aktywowana");
                czlowiekcopy.setBron(5);
            }
        }
    }
}