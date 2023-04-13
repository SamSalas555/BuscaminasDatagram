import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket cliente = new Socket("localhost",3000);
        System.out.println("Conexion con el servidor exitosa");
        ObjectOutputStream outputStreamGame = new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream inputStreamGame = new ObjectInputStream(cliente.getInputStream());
        Board newGame = (Board)inputStreamGame.readObject();
        newGame.printBoardCmd();
    }
}
