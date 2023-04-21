import java.net.*;
import java.io.*;
import java.util.Objects;

public class Servidor {
    private static ObjectOutputStream outputStreamGame;
    private static ObjectInputStream inputStreamGame;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket socketGame = new ServerSocket(3000);
        System.out.println("Servidor de juego Iniciado");

        while (true){
            Socket cliente = socketGame.accept();
            setOutputStreamGame(new ObjectOutputStream(cliente.getOutputStream()));
            setInputStreamGame(new ObjectInputStream(cliente.getInputStream()));
           String level =(String) getInputStreamGame().readObject();
           System.out.println(level);
           if (Objects.equals(level, "Facil")){
                getOutputStreamGame().writeObject(new Board(9,9,10));
           } else if (Objects.equals(level, "Intermedio")) {
               getOutputStreamGame().writeObject(new Board(16,16,40));
           } else if (Objects.equals(level, "Dificil")) {
               getOutputStreamGame().writeObject(new Board(16,30,99));
           }else {
                System.out.println("No valido");
           }
           getOutputStreamGame().flush();
           System.out.println("Terminado");
        }
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
