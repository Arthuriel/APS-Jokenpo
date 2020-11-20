package cliente;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

import util.Comunicacao;
import util.Requisicao;
import util.Resposta;

public class Cliente_1 {
    final static int PORTA = 9876;

    public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Socket clienteSocket;
        int num;
        int opc;
        int rejoga = 1;

        System.out.println("Escolha uma das opções abaixo para jogar:");
        System.out.println("1-Player vs CPU");
        System.out.println("2-Player vs Player");
        System.out.println("3-Sair");
        System.out.println("======>");
        opc = teclado.nextInt();

        if (opc == 1) {

            while (rejoga != 2) {

                try {
                    System.out.println("Conectando ao servidor...");
                    clienteSocket = new Socket("127.0.1", PORTA);
                } catch (Exception e) {
                    System.out.println("Falha ao conectar com o servidor.");
                    teclado.close();
                    return;
                }

                System.out.println("Selecione sua jogada:");
                System.out.println("1-Pedra");
                System.out.println("2-Tesoura");
                System.out.println("3-Papel");
                System.out.println("======>");
                num = teclado.nextInt();

                Requisicao requisicao = new Requisicao(num, rejoga);
                Comunicacao comunicacao = new Comunicacao(clienteSocket);

                comunicacao.send(requisicao);
                Resposta resposta = (Resposta) comunicacao.receive();

                if (resposta.getStatus() == Resposta.Empatou) {
                    System.out.println("Você empatou");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                } else if (resposta.getStatus() == Resposta.Ganhou) {
                    System.out.println("Você ganhou.");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                } else if (resposta.getStatus() == Resposta.Perdeu) {
                    System.out.println("Você perdeu.");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                } else if (resposta.getStatus() == Resposta.operador_invalido) {
                    System.out.println("Dígito Inválido.");
                } else
                    System.out.println("Erro genérico.");

                do {
                    System.out.println("======================");
                    System.out.println("Deseja jogar novamente?");
                    System.out.println("1-Sim");
                    System.out.println("2-Não");
                    System.out.println("===========>");
                    rejoga = teclado.nextInt();
                    System.out.println("Valor2 de rejoga é" + rejoga);

                    // requisicao.setRejoga(rejoga);

                } while (rejoga < 1 || rejoga > 2);

                if (rejoga == 2) {
                    try {
                        Thread.sleep(5000);
                        // clienteSocket.close();
                        System.out.println("VAlor de rejoga é " + rejoga);
                    } catch (Exception e) {
                        System.out.println("Erro ao fechar conexão: " + e.getMessage());
                    }
                }

            }
            teclado.close();
        }

        else if (opc == 2) {
            int v1, v2;

            System.out.println("Escolha entre 0, 1 e 2 , sendo : ");
            System.out.println("Pedra = 0 ");
            System.out.println("Papel = 1  ");
            System.out.println("Tesoura = 2");

            System.out.println("Jogador 1: ");
            v1 = teclado.nextInt();

            System.out.println("Jogador 2: ");
            v2 = teclado.nextInt();

            switch (v1) {
                case 0:
                    switch (v2) {
                        case 0:
                            System.out.println("Empate");
                            break;
                        case 1:
                            System.out.println("Jogador 2 ganhou");
                            break;
                        case 2:
                            System.out.println("Jogador 1 ganhou");
                            break;
                        default:
                            System.out.println("USUARIO BURRO DO CARALHO");
                    }
                    break;
                case 1:
                    switch (v2) {
                        case 0:
                            System.out.println("Jogador 1 ganhou");
                            break;
                        case 1:
                            System.out.println(" Empatou");
                            break;
                        case 2:
                            System.out.println("Jogador 2 ganhou");
                            break;
                        default:
                            System.out.println("USUARIO BURRO DO CARALHO");
                    }
                    break;
                case 2:
                    switch (v2) {
                        case 0:
                            System.out.println("Jogador 2 ganhou");
                            break;
                        case 1:
                            System.out.println("Jogador 1 ganhou");
                            break;
                        case 2:
                            System.out.println("Empate");
                            break;
                        default:
                            System.out.println("USUARIO BURRO DO CARALHO");
                    }
                    break;
                default:
                    System.out.println("USUARIO BURRO DO CARALHO");
            }
        }
    }

}
