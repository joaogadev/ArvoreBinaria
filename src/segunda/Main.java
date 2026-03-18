package segunda;

import java.util.Scanner;

class Nos {

    long item;
    Nos esq;
    Nos dir;

    // construtor do nó
    public Nos(long item) {
        this.item = item;
        this.esq = null;
        this.dir = null;
    }
}

class Tree {

    private Nos root;

    public Tree() {
        root = null;
    }

    public void inserir(long v) {

        Nos novo = new Nos(v);

        if (root == null) {
            root = novo;
            return;
        }

        Nos atual = root;
        Nos anterior;

        while (true) {

            anterior = atual;

            if (v <= atual.item) {

                atual = atual.esq;

                if (atual == null) {
                    anterior.esq = novo;
                    return;
                }

            } else {

                atual = atual.dir;

                if (atual == null) {
                    anterior.dir = novo;
                    return;
                }
            }
        }
    }

    public Nos buscar(long chave) {

        Nos atual = root;

        while (atual != null && atual.item != chave) {

            if (chave < atual.item)
                atual = atual.esq;
            else
                atual = atual.dir;
        }

        return atual;
    }

    public boolean remover(long v) {

        if (root == null) return false;

        Nos atual = root;
        Nos pai = root;
        boolean filhoEsq = true;

        while (atual != null && atual.item != v) {

            pai = atual;

            if (v < atual.item) {
                atual = atual.esq;
                filhoEsq = true;
            } else {
                atual = atual.dir;
                filhoEsq = false;
            }

            if (atual == null) return false;
        }

        // caso 1: folha
        if (atual.esq == null && atual.dir == null) {

            if (atual == root)
                root = null;
            else if (filhoEsq)
                pai.esq = null;
            else
                pai.dir = null;
        }

        // caso 2: só filho esquerdo
        else if (atual.dir == null) {

            if (atual == root)
                root = atual.esq;
            else if (filhoEsq)
                pai.esq = atual.esq;
            else
                pai.dir = atual.esq;
        }

        // caso 3: só filho direito
        else if (atual.esq == null) {

            if (atual == root)
                root = atual.dir;
            else if (filhoEsq)
                pai.esq = atual.dir;
            else
                pai.dir = atual.dir;
        }

        // caso 4: dois filhos
        else {

            Nos sucessor = noSucessor(atual);

            if (atual == root)
                root = sucessor;
            else if (filhoEsq)
                pai.esq = sucessor;
            else
                pai.dir = sucessor;

            sucessor.esq = atual.esq;
        }

        return true;
    }

    public Nos noSucessor(Nos apaga) {

        Nos paiSucessor = apaga;
        Nos sucessor = apaga;
        Nos atual = apaga.dir;

        while (atual != null) {

            paiSucessor = sucessor;
            sucessor = atual;
            atual = atual.esq;
        }

        if (sucessor != apaga.dir) {

            paiSucessor.esq = sucessor.dir;
            sucessor.dir = apaga.dir;
        }

        return sucessor;
    }

    public void caminhar() {

        if (root == null) {
            System.out.println("\nArvore vazia");
            return;
        }

        System.out.print("\nEm ordem: ");
        inOrder(root);

        System.out.print("\nPre ordem: ");
        preOrder(root);

        System.out.print("\nPos ordem: ");
        posOrder(root);

        System.out.println("\nAltura: " + altura(root));
        System.out.println("Folhas: " + folhas(root));
        System.out.println("Quantidade de nos: " + contarNos(root));

        System.out.println("Minimo: " + min().item);
        System.out.println("Maximo: " + max().item);
    }

    public void inOrder(Nos atual) {

        if (atual != null) {
            inOrder(atual.esq);
            System.out.print(atual.item + " ");
            inOrder(atual.dir);
        }
    }

    public void preOrder(Nos atual) {

        if (atual != null) {
            System.out.print(atual.item + " ");
            preOrder(atual.esq);
            preOrder(atual.dir);
        }
    }

    public void posOrder(Nos atual) {

        if (atual != null) {
            posOrder(atual.esq);
            posOrder(atual.dir);
            System.out.print(atual.item + " ");
        }
    }

    public int altura(Nos atual) {

        if (atual == null) return 0;

        int esq = altura(atual.esq);
        int dir = altura(atual.dir);

        return 1 + Math.max(esq, dir);
    }

    public int folhas(Nos atual) {

        if (atual == null) return 0;

        if (atual.esq == null && atual.dir == null)
            return 1;

        return folhas(atual.esq) + folhas(atual.dir);
    }

    public int contarNos(Nos atual) {

        if (atual == null) return 0;

        return 1 + contarNos(atual.esq) + contarNos(atual.dir);
    }

    public Nos min() {

        if (root == null) return null;

        Nos atual = root;

        while (atual.esq != null) {
            atual = atual.esq;
        }

        return atual;
    }

    public Nos max() {

        if (root == null) return null;

        Nos atual = root;

        while (atual.dir != null) {
            atual = atual.dir;
        }

        return atual;
    }
}

public class Main {

    public static void main(String[] args) {

        Scanner le = new Scanner(System.in);
        Tree arv = new Tree();

        int opcao;
        long x;

        do {

            System.out.println("\n1 - Inserir");
            System.out.println("2 - Remover");
            System.out.println("3 - Buscar");
            System.out.println("4 - Exibir arvore");
            System.out.println("5 - Sair");

            System.out.print("Opcao: ");
            opcao = le.nextInt();

            switch (opcao) {

                case 1:

                    System.out.print("Valor: ");
                    x = le.nextLong();
                    arv.inserir(x);
                    break;

                case 2:

                    System.out.print("Valor: ");
                    x = le.nextLong();

                    if (!arv.remover(x))
                        System.out.println("Valor nao encontrado");
                    break;

                case 3:

                    System.out.print("Valor: ");
                    x = le.nextLong();

                    if (arv.buscar(x) != null)
                        System.out.println("Valor encontrado");
                    else
                        System.out.println("Valor nao encontrado");

                    break;

                case 4:
                    arv.caminhar();
                    break;

            }

        } while (opcao != 5);

        le.close();
    }
}
