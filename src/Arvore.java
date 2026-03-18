public class Arvore {

    No raiz;

    No inserir(No raiz, int valor) {

        if (raiz == null) {
            return new No(valor);
        }

        if (valor < raiz.valor) {
            raiz.esquerda = inserir(raiz.esquerda, valor);
        } else {
            raiz.direita = inserir(raiz.direita, valor);
        }

        return raiz;
    }

    void emOrdem(No raiz, StringBuilder resultado) {

        if (raiz != null) {

            emOrdem(raiz.esquerda, resultado);

            resultado.append(raiz.valor).append(" ");

            emOrdem(raiz.direita, resultado);
        }
    }

    int altura(No raiz) {
        if (raiz == null) {
            return -1;
        }
        int esquerda = altura(raiz.esquerda);
        int direita = altura(raiz.direita);
        return Math.max(esquerda, direita) + 1;
    }

    int contarFolhas(No raiz) {
        if (raiz == null)
            return 0;
        if (raiz.esquerda == null && raiz.direita == null)
            return 1;
        return contarFolhas(raiz.esquerda) + contarFolhas(raiz.direita);
    }
    int contarInternos(No raiz) {
        if (raiz == null || (raiz.esquerda == null && raiz.direita == null))
            return 0;
        return 1 + contarInternos(raiz.esquerda) + contarInternos(raiz.direita);
    }
}