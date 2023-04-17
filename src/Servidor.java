import java.net.*;
import java.io.*;
public class Servidor {
    private static ObjectOutputStream outputStreamGame;
    private static ObjectInputStream inputStreamGame;
    public static void main(String[] args) throws IOException {
        ServerSocket socketGame = new ServerSocket(3000);
        System.out.println("Servidor de juego Iniciado");
        Socket cliente = socketGame.accept();
        setOutputStreamGame(new ObjectOutputStream(cliente.getOutputStream()));
        setInputStreamGame(new ObjectInputStream(cliente.getInputStream()));
        getOutputStreamGame().flush();
        getOutputStreamGame().writeObject(new Board(9,9,16));
    }


    public static ObjectOutputStream getOutputStreamGame() {
        return outputStreamGame;
    }

    public static void setOutputStreamGame(ObjectOutputStream outputStreamGame) {
        Servidor.outputStreamGame = outputStreamGame;
    }

    public static ObjectInputStream getInputStreamGame() {
        return inputStreamGame;
    }

    public static void setInputStreamGame(ObjectInputStream inputStreamGame) {
        Servidor.inputStreamGame = inputStreamGame;
    }
}
