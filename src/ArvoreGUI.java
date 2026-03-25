import javax.swing.*;
import java.awt.*;

public class ArvoreGUI extends JFrame {

    JTextField campoNumero;
    JTextArea areaResultado;

    JLabel labelRaiz;
    JLabel labelAltura;
    JLabel labelFolhas;
    JLabel labelInternos;

    JButton botaoInserir;

    Arvore arvore = new Arvore();
    PainelArvore painel;

    public ArvoreGUI() {

        setTitle("Árvore Binária");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Topo do pop-up
        JPanel topo = new JPanel();

        campoNumero = new JTextField(10);
        botaoInserir = new JButton("Inserir");

        topo.add(new JLabel("Digite um número:"));
        topo.add(campoNumero);
        topo.add(botaoInserir);

        add(topo, BorderLayout.NORTH);

        // Informações
        JPanel info = new JPanel();
        areaResultado = new JTextArea(5,30);

        labelRaiz = new JLabel("Raiz: ");
        labelAltura = new JLabel("Altura: ");
        labelFolhas = new JLabel("Folhas: ");
        labelInternos = new JLabel("Nós internos: ");

        info.add(new JScrollPane(areaResultado));
        info.add(labelRaiz);
        info.add(labelAltura);
        info.add(labelFolhas);
        info.add(labelInternos);

        add(info, BorderLayout.SOUTH);

        // Painel estrutural da árvore
        painel = new PainelArvore(arvore);
        painel.setPreferredSize(new Dimension(200, 100));

        //Scroll envolvendo o painel da árvore
        JScrollPane scroll = new JScrollPane(painel);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        add(scroll, BorderLayout.CENTER);

        botaoInserir.addActionListener(e -> {

            int valor = Integer.parseInt(campoNumero.getText());

            arvore.raiz = arvore.inserir(arvore.raiz, valor);

            StringBuilder resultado = new StringBuilder();
            arvore.emOrdem(arvore.raiz, resultado);

            areaResultado.setText(resultado.toString());

            if (arvore.raiz != null)
                labelRaiz.setText("Raiz: " + arvore.raiz.valor);

            labelAltura.setText("Altura: " + arvore.altura(arvore.raiz));
            labelFolhas.setText("Folhas: " + arvore.contarFolhas(arvore.raiz));
            labelInternos.setText("Nós internos: " + arvore.contarInternos(arvore.raiz));
            campoNumero.setText("");

            //Faz o painel crescer com base aumentam os nós
            Dimension d = painel.getPreferredSize();
            painel.setPreferredSize(
                    new Dimension(
                            d.width + 100,
                            d.height + 100
                    )
            );

            painel.revalidate(); //atualiza o scroll
            painel.repaint(); //Redesenha a árvore após inserir
        });
    }

    public static void main(String[] args) {
        new ArvoreGUI().setVisible(true);
    }
}