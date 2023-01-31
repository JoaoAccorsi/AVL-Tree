public class ArvoreAVL {

    static final int cont = 10;
    public boolean switchLogs = false;
    static final String ANSI_RED = "\033[031m";
    static final String ANSI_GREEN = "\033[032m";
    static final String ANSI_RESET = "\u001B[0m";

    Nodo noRaiz;

    //------------------------------------------------------//
    // Construtor

    public ArvoreAVL() {
        noRaiz = null;
    }

    //------------------------------------------------------//
    // Inserir o elemento na árvore

    public void inserir(int inteiro) {
        Nodo noInserir = new Nodo(inteiro);
        inserir(noRaiz, noInserir);
    }

    public void inserir(Nodo noReferencia, Nodo noInserir) {
        if (noReferencia == null)
            noRaiz = noInserir;
        else if (noInserir.getValor() < noReferencia.getValor())
            if (noReferencia.getNodoEsquerdo() == null) {
                if (switchLogs)
                    System.out.println("[LOG] Inserido " + noInserir.getValor() + " a esquerda de " + noReferencia.getValor());
                noReferencia.setNodoEsquerdo(noInserir);
                noInserir.setNodoPai(noReferencia);
                verificarBalanceamento(noReferencia);
            } else {
                if (switchLogs)
                    System.out.println("[LOG] Inserindo " + noInserir.getValor() + " a esquerda de " + noReferencia.getValor());
                inserir(noReferencia.getNodoEsquerdo(), noInserir);
            }
        else if (noInserir.getValor() > noReferencia.getValor())
            if (noReferencia.getNodoDireito() == null) {
                if (switchLogs)
                    System.out.println("[LOG] Inserido " + noInserir.getValor() + " a direita de " + noReferencia.getValor());
                noReferencia.setNodoDireito(noInserir);
                noInserir.setNodoPai(noReferencia);
                verificarBalanceamento(noReferencia);
            } else {
                if (switchLogs)
                    System.out.println("[LOG] Inserindo " + noInserir.getValor() + " a direita de " + noReferencia.getValor());
                inserir(noReferencia.getNodoDireito(), noInserir);
            }
        else
            System.out.println(ANSI_RED + "[LOG] O nodo " + noInserir.getValor() + " já existe na árvore!" + ANSI_RESET);
    }

    //------------------------------------------------------//
    // Verifica balanceamento do nó

    private void verificarBalanceamento(Nodo no) {
        setBalanceamento(no);
        int fatorBalanceamento = no.getFatorBalanceamento();

        if (fatorBalanceamento == 2)

            if (altura(no.getNodoEsquerdo().getNodoEsquerdo()) >= altura(no.getNodoEsquerdo().getNodoDireito()))
                no = rotacaoDireita(no);
            else
                no = rotacaoDuplaDireita(no);

        else if (fatorBalanceamento == -2)

            if (altura(no.getNodoDireito().getNodoDireito()) >= altura(no.getNodoDireito().getNodoEsquerdo()))
                no = rotacaoEsquerda(no);
            else
                no = rotacaoDuplaEsquerda(no);

        if (no.getNodoPai() != null)
            verificarBalanceamento(no.getNodoPai());
        else
            noRaiz = no;
    }

    private void setBalanceamento(Nodo no) {
        if (switchLogs)
            System.out.println("[LOG] Nodo: " + no.getValor()
                    + "\n[LOG] Bal: " + altura(no.getNodoEsquerdo()) + " - " + altura(no.getNodoDireito()));
        no.setFatorBalanceamento(altura(no.getNodoEsquerdo()) - altura(no.getNodoDireito()));
    }

    //------------------------------------------------------//
    // Altura do nó

    private int altura(Nodo no) {
        if (no == null)
            return 0;

        else if (no.getNodoEsquerdo() == null && no.getNodoDireito() == null)
            return 1;

        else if (no.getNodoEsquerdo() == null)
            return 1 + altura(no.getNodoDireito());

        else if (no.getNodoDireito() == null)
            return 1 + altura(no.getNodoEsquerdo());

        else
            return 1 + Math.max(altura(no.getNodoEsquerdo()), altura(no.getNodoDireito()));
    }

    //------------------------------------------------------//
    // Rotações Direita

    private Nodo rotacaoDireita(Nodo inicial) {
        Nodo esquerdo = inicial.getNodoEsquerdo();
        esquerdo.setNodoPai(inicial.getNodoPai());

        inicial.setNodoEsquerdo(esquerdo.getNodoDireito());

        if (inicial.getNodoEsquerdo() != null)
            inicial.getNodoEsquerdo().setNodoPai(inicial);

        esquerdo.setNodoDireito(inicial);
        inicial.setNodoPai(esquerdo);

        if (esquerdo.getNodoPai() != null)
            if (esquerdo.getNodoPai().getNodoDireito() == inicial)
                esquerdo.getNodoPai().setNodoDireito(esquerdo);
            else if (esquerdo.getNodoPai().getNodoEsquerdo() == inicial)
                esquerdo.getNodoPai().setNodoEsquerdo(esquerdo);

        setBalanceamento(inicial);
        setBalanceamento(esquerdo);

        return esquerdo;
    }

    private Nodo rotacaoDuplaDireita(Nodo inicial) {
        inicial.setNodoEsquerdo(rotacaoEsquerda(inicial.getNodoEsquerdo()));
        return rotacaoDireita(inicial);
    }

    //------------------------------------------------------//
    // Rotações Esquerda

    private Nodo rotacaoEsquerda(Nodo inicial) {
        Nodo direita = inicial.getNodoDireito();
        direita.setNodoPai(inicial.getNodoPai());

        inicial.setNodoDireito(direita.getNodoEsquerdo());

        if (inicial.getNodoDireito() != null)
            inicial.getNodoDireito().setNodoPai(inicial);

        direita.setNodoEsquerdo(inicial);
        inicial.setNodoPai(direita);

        if (direita.getNodoPai() != null)
            if (direita.getNodoPai().getNodoDireito() == inicial)
                direita.getNodoPai().setNodoDireito(direita);
            else if (direita.getNodoPai().getNodoEsquerdo() == inicial)
                direita.getNodoPai().setNodoEsquerdo(direita);

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    private Nodo rotacaoDuplaEsquerda(Nodo inicial) {
        inicial.setNodoDireito(rotacaoDireita(inicial.getNodoDireito()));
        return rotacaoEsquerda(inicial);
    }

    //------------------------------------------------------//
    // Busca um elemento na árvore

    public void buscar(int inteiro) {
        buscar(noRaiz, inteiro);
    }

    public void buscar(Nodo no, int inteiro) {
        if (no.getValor() == inteiro) {
            System.out.println(ANSI_GREEN + "[LOG] Número econtrado, " + inteiro + " está na árvore!" + ANSI_RESET);
        }
        else if (inteiro < no.getValor() && no.getNodoEsquerdo() != null) {
            System.out.println("Buscando a esquerda de " + no.getValor());
            buscar(no.getNodoEsquerdo(), inteiro);
        }
        else if (inteiro > no.getValor() && no.getNodoDireito() != null) {
            System.out.println("Buscando a direita de " + no.getValor());
            buscar(no.getNodoDireito(), inteiro);
        } else {
            System.out.println(ANSI_RED + "[LOG] Número " + inteiro + " não está na árvore!" + ANSI_RESET);
        }
    }

    //------------------------------------------------------//
    // Remove um elemento da árvore

    public void remover(int inteiro) {
        remover(noRaiz, inteiro);
    }

    private void remover(Nodo noReferencia, int inteiro) {
        if (noReferencia == null)
            System.out.println(ANSI_RED + "[LOG] Inteiro não está na árvore!" + ANSI_RESET);
        else if (inteiro < noReferencia.getValor())
            remover(noReferencia.getNodoEsquerdo(), inteiro);
        else if (inteiro > noReferencia.getValor())
            remover(noReferencia.getNodoDireito(), inteiro);
        else if (noReferencia.getValor() == inteiro) {
            removerNoEncontrado(noReferencia);
            System.out.println(ANSI_GREEN + "[LOG] Inteiro removido com sucesso!" + ANSI_RESET);
        }
    }

    private void removerNoEncontrado(Nodo noRemover) {
        Nodo raiz;
        // Caso em que o nó não possui dois filhos
        if (noRemover.getNodoEsquerdo() == null || noRemover.getNodoDireito() == null) {
            if (noRemover.getNodoPai() == null) {
                noRaiz = null;
                noRemover = null;
                return;
            }
            raiz = noRemover;
        } else {
            // Caso em que o nó possui dois filhos
            raiz = fusao(noRemover);
            noRemover.setValor(raiz.getValor());
        }

        Nodo pai;
        if (raiz.getNodoEsquerdo() != null)
            pai = raiz.getNodoEsquerdo();
        else
            pai = raiz.getNodoDireito();

        if (pai != null)
            pai.setNodoPai(raiz.getNodoPai());

        if (raiz.getNodoPai() == null)
            noRaiz = pai;
        else {
            if (raiz == raiz.getNodoPai().getNodoEsquerdo())
                raiz.getNodoPai().setNodoEsquerdo(pai);
            else
                raiz.getNodoPai().setNodoDireito(pai);

            verificarBalanceamento(raiz.getNodoPai());
        }
        raiz = null;
    }

    //------------------------------------------------------//
    // Fusão

    private Nodo fusao(Nodo noRemover) {
        // Exclusão por Fusão, procurando o nó com menor valor da sub-árvore direita
        Nodo r = noRemover.getNodoDireito();
        while (r.getNodoEsquerdo() != null) {
            r = r.getNodoEsquerdo();
        }
        return r;
    }

    //------------------------------------------------------//
    // Exibe a estrutura da árvore de lado ---> https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/

    public void exibeArvore(Nodo no, int space) {
        if (no == null)
            return;

        space += cont;

        exibeArvore(no.getNodoDireito(), space);

        // Imprimi o no
        System.out.print("\n");
        for (int i = cont; i < space; i++)
            System.out.print(" ");

        System.out.print(ANSI_GREEN + no.getValor() + "\n" + ANSI_RESET);

        exibeArvore(no.getNodoEsquerdo(), space);
    }

    //------------------------------------------------------//
    // Impressões na tela

    public void exibePreOrdem() {
        if (noRaiz != null) {
            exibePreOrdem(noRaiz);
            System.out.println();
        } else
            System.out.println(ANSI_RED + "[LOG] Árvore vazia!" + ANSI_RESET);
    }

    public void exibePreOrdem(Nodo no) {
        if (no != null) {
            System.out.print(ANSI_GREEN + no.getValor() + " " + ANSI_RESET);
            exibePreOrdem(no.getNodoEsquerdo());
            exibePreOrdem(no.getNodoDireito());
        }
    }

    public void exibePosOrdem() {
        if (noRaiz != null) {
            exibePosOrdem(noRaiz);
            System.out.println();
        } else
            System.out.println(ANSI_RED + "[LOG] Árvore vazia!" + ANSI_RESET);
    }

    public void exibePosOrdem(Nodo no) {
        if (no != null) {
            exibePosOrdem(no.getNodoEsquerdo());
            exibePosOrdem(no.getNodoDireito());
            System.out.print(no.getValor() + " ");
        }
    }

    public void exibeEmOrdem() {
        if (noRaiz != null) {
            exibeEmOrdem(noRaiz);
            System.out.println();
        } else
            System.out.println(ANSI_RED + "[LOG] Árvore vazia!" + ANSI_RESET);
    }

    public void exibeEmOrdem(Nodo no) {
        if (no != null) {
            exibeEmOrdem(no.getNodoEsquerdo());
            System.out.print(ANSI_GREEN + no.getValor() + " " + ANSI_RESET);
            exibeEmOrdem(no.getNodoDireito());
        }
    }

    public void exibeFatorBalanceamento() {
        if (noRaiz != null) {
            exibeFatorBalanceamento(noRaiz);
            System.out.println();
        } else
            System.out.println(ANSI_RED + "[LOG] Árvore vazia!" + ANSI_RESET);
    }

    public void exibeFatorBalanceamento(Nodo no) {
        if (no != null) {
            exibeFatorBalanceamento(no.getNodoEsquerdo());
            System.out.print(ANSI_GREEN + "\n" + no.getValor() + " Fator Balanceamento: " + no.getFatorBalanceamento() + ANSI_RESET);
            exibeFatorBalanceamento(no.getNodoDireito());
        }
    }
}
