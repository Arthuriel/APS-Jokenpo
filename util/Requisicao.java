package util;

import java.io.Serializable;

/**
 * Requisicao: Dados enviados para o servidor
 */

public class Requisicao implements Serializable {

    //opcional para controle de versão
    private static final long serialVersionUID = 1L;

    private int num;
    private int rejoga;

    public Requisicao(int num, int rejoga) {
        this.num = num;
        this.rejoga = rejoga;
    }

    public Requisicao(int rejoga) {
        this.rejoga = rejoga;
    }
    
    public int getRequisicao() {
        return num;
    }
   
    public int getRejoga() {
        return rejoga;
    }
}