package cliente;

import java.net.Socket;
import java.util.Scanner;

import util.Comunicacao;
import util.Requisicao;
import util.Resposta;

public class Cliente_1{
    final static int PORTA = 9876;
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Socket clienteSocket;
        int num;

        try {
            clienteSocket = new Socket("127.0.1", PORTA);
        } catch (Exception e) {
            System.out.println("Falha ao conectar com o servidor.");
            teclado.close();
            return;
        }

        System.out.println("Escolha uma das opções abaixo para jogar:");
            System.out.println("1-Pedra");
            System.out.println("2-Tesoura");      
            System.out.println("3-Papel");
            System.out.println("======>");
            num = teclado.nextInt();

        Requisicao requisicao = new Requisicao(num);
        Comunicacao comunicacao = new Comunicacao(clienteSocket);

        comunicacao.send(requisicao);
        Resposta resposta = (Resposta) comunicacao.receive();

        if(resposta.getStatus() == Resposta.Empatou){
            System.out.println("Você empatou");
            System.out.println("A máquina jogou: " + resposta.getResultado());
        }else
            if(resposta.getStatus() == Resposta.Ganhou){
                System.out.println("Você ganhou.");
                System.out.println("A máquina jogou: " + resposta.getResultado());
            }else
                if(resposta.getStatus() == Resposta.Perdeu){
                    System.out.println("Você perdeu.");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                }else
                    if(resposta.getStatus() == Resposta.operador_invalido){
                        System.out.println("Dígito Inválido.");
                    }else
                        System.out.println("Erro genérico.");                            
    
        try {
            clienteSocket.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: "+ e.getMessage());
        }
        teclado.close();
    }
}
