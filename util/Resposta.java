package util;

import java.io.Serializable;

/**
 * Resposta: Dados retornados para o cliente
 */

public class Resposta implements Serializable{

    public final static int operador_invalido = 0;
    public final static int Empatou = 1;
    public final static int Ganhou = 2;
    public final static int Perdeu = 3;    

    //opcional apenas para controle de versão
    private static final long serialVersionUID = 1L;

    private int status;
    private String resultado;

    public Resposta() {
    }
    
    public Resposta(int status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        if (resultado == 1){
        this.resultado = "Pedra";
        }else if(resultado == 2){
        this.resultado = "Tesoura";
        }else if (resultado == 3){
        this.resultado = "Papel";
        }else 
        this.resultado = "Erro na randomização " + resultado;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    
}
