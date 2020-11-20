package servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    final static int PORTA = 9876;
    public static void main(String[] args) {
        ServerSocket serverSocket;
        //Socket clienteSocket;

        try {
            serverSocket = new ServerSocket(PORTA);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        while(true){
            try {
                System.out.println("Aguardando conexão...");
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Conectado com " + clienteSocket.getInetAddress().getHostAddress());
                
                Atende atende = new Atende(clienteSocket);
                atende.start();

            } catch (Exception e) {
                System.out.println("Erro na conexão:" + e.getMessage());
            }

        }
    }
}
