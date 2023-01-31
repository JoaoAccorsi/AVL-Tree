public class Nodo {

    private int valor;
    private int fatorBalanceamento;
    private Nodo nodoEsquerdo;
    private Nodo nodoDireito;
    private Nodo nodoPai;

    public Nodo(int valor) {
        this.valor = valor;
        fatorBalanceamento = 0;
        nodoEsquerdo = null;
        nodoDireito = null;
        nodoPai = null;
    }

    public int getValor() {
        return valor;
    }

    public int getFatorBalanceamento() {
        return fatorBalanceamento;
    }

    public Nodo getNodoEsquerdo() { return this.nodoEsquerdo; }

    public Nodo getNodoDireito() {
        return this.nodoDireito;
    }

    public Nodo getNodoPai() {
        return this.nodoPai;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setFatorBalanceamento(int fatorBalanceamento) {
        this.fatorBalanceamento = fatorBalanceamento;
    }

    public void setNodoEsquerdo(Nodo nodoEsquerdo) {
        this.nodoEsquerdo = nodoEsquerdo;
    }

    public void setNodoDireito(Nodo nodoDireito) {
        this.nodoDireito = nodoDireito;
    }

    public void setNodoPai(Nodo nodoPai) {
        this.nodoPai = nodoPai;
    }
}
