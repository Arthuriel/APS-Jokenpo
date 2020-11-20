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
        int rejoga = 0;
        int ganhos = 0;
        int perdas = 0;
        int empates = 0;

        System.out.println("========================================");
        System.out.println("Escolha uma das opções abaixo para jogar:");
        System.out.println("Player vs CPU = 1");
        System.out.println("Player vs Player = 2");
        System.out.println("Sair = 3");
        System.out.println("======>");
        opc = teclado.nextInt();

        if (opc == 1) {
            while (rejoga != 2) {
                try {
                    clienteSocket = new Socket("127.0.1", PORTA);
                } catch (Exception e) {
                    System.out.println("Falha ao conectar com o servidor.");
                    teclado.close();
                    return;
                }
                
                System.out.println("========================================");
                System.out.println("Selecione sua jogada:");
                System.out.println("Pedra = 1");
                System.out.println("Tesoura = 2");
                System.out.println("Papel = 3");
                System.out.println("======>");
                num = teclado.nextInt();

                Requisicao requisicao = new Requisicao(num);
                Comunicacao comunicacao = new Comunicacao(clienteSocket);

                comunicacao.send(requisicao);
                Resposta resposta = (Resposta) comunicacao.receive();

                if (resposta.getStatus() == Resposta.Empatou) {
                    empates++;
                    System.out.println("Você empatou");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                } else if (resposta.getStatus() == Resposta.Ganhou) {
                    ganhos++;
                    System.out.println("Você ganhou.");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                } else if (resposta.getStatus() == Resposta.Perdeu) {
                    perdas++;
                    System.out.println("Você perdeu.");
                    System.out.println("A máquina jogou: " + resposta.getResultado());
                } else if (resposta.getStatus() == Resposta.operador_invalido) {
                    System.out.println("Dígito Inválido.");
                } else
                    System.out.println("Erro genérico.");

                if (rejoga == 2) {
                    try {
                        clienteSocket.close();

                    } catch (Exception e) {
                        System.out.println("Erro ao fechar conexão: " + e.getMessage());
                    }
                }
                do {
                    System.out.println("========================================");
                    System.out.println("Deseja jogar novamente?");
                    System.out.println("1-Sim");
                    System.out.println("2-Não");
                    System.out.println("======>");
                    rejoga = teclado.nextInt();
                    if (rejoga < 1 || rejoga > 2) {
                        System.out.println("Digíto inválido, digite novamente!");
                    }
                } while (rejoga < 1 || rejoga > 2);

            }
            System.out.println("Você ganhou " + ganhos + " vezes!");
            System.out.println("Você perdeu " + perdas + " vezes!");
            System.out.println("Você empatou " + empates + " vezes!");
            teclado.close();
        }

        else if (opc == 2) {
            int v1, v2;

            System.out.println("Selecione sua jogada:");
            System.out.println("1-Pedra");
            System.out.println("2-Tesoura");
            System.out.println("3-Papel");
            System.out.println("======>");
            num = teclado.nextInt();

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
