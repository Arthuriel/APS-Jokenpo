package servidor;

import java.net.Socket;

import util.Comunicacao;
import util.Requisicao;
import util.Resposta;
import java.util.Random;

public class Atende extends Thread {
    private Socket cliente;

    public Atende(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() { // método principal, equivale ao main
        Comunicacao comunicacao = new Comunicacao(cliente);

        Requisicao requisicao = (Requisicao) comunicacao.receive();
        Resposta resposta = new Resposta();

        int operacao = requisicao.getRequisicao();
        int numAleatorio = aleatorizar();
        int rejoga = requisicao.getRejoga();

        System.out.println("Rejoga no servidor é " + rejoga);
        if(rejoga==1 || rejoga==0){
            if(operacao<1 || operacao>3){
                resposta.setStatus(Resposta.operador_invalido);
            }else
            if(operacao == numAleatorio){
                resposta.setStatus(Resposta.Empatou);
                resposta.setResultado(numAleatorio);
            }else
            if(operacao==1 && numAleatorio==2){
                resposta.setStatus(Resposta.Ganhou);
                resposta.setResultado(numAleatorio);
            }else
            if(operacao==2 && numAleatorio==3){
                resposta.setStatus(Resposta.Ganhou);
                resposta.setResultado(numAleatorio);
            }else
            if(operacao==3 && numAleatorio==1){
                resposta.setStatus(Resposta.Ganhou);
                resposta.setResultado(numAleatorio);
            }else
                resposta.setStatus(Resposta.Perdeu);
                resposta.setResultado(numAleatorio);

            comunicacao.send(resposta);
            System.out.println("Operacao realizada:");
            System.out.println("rejoga é " + rejoga);
        }else{
            try {
                cliente.close();
                System.out.println("Conexão encerrada.");
            } catch (Exception e) {
                System.out.println("Erro ao fechar a conexão.");
            }
        }  
    }

    public int aleatorizar() {
        Random random = new Random();
        return (random.nextInt(3)+1);
    }
}

