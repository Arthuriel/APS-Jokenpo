package util;

import java.io.Serializable;

/**
 * Requisicao: Dados enviados para o servidor
 */

public class Requisicao implements Serializable {

    //opcional para controle de versão
    private static final long serialVersionUID = 1L;

    private int num;

    public Requisicao(int num) {
        this.num = num;
    }


    public int getRequisicao() {
        return num;
    }
}