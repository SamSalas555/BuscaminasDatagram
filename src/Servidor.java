import java.net.*;
import java.io.*;
public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket socketGame = new ServerSocket(3000);
        System.out.println("Servidor de juego Iniciado");
        Socket cliente = socketGame.accept();
        ObjectOutputStream outputStreamGame = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream inputStreamGame = new ObjectInputStream(cliente.getInputStream());
        Board newGame = new Board(9,9,10);
        outputStreamGame.writeObject(newGame);
    }
}
