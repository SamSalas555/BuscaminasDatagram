import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    private static ObjectOutputStream outputStreamGame;
    private static ObjectInputStream inputStreamGame;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Game();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setUndecorated(false);
                frame.setVisible(true);
            }
        });
    }

    public static void initConecct() throws IOException, ClassNotFoundException {
        Socket cliente= new Socket("localhost",3000);
        System.out.println("Conexion con el servidor exitosa");
         setOutputStreamGame(new ObjectOutputStream(cliente.getOutputStream()));
         setInputStreamGame(new ObjectInputStream(cliente.getInputStream()));
        getOutputStreamGame().flush();
        Board b1 = (Board) getInputStreamGame().readObject();
        b1.printBoardCmd();
    }

    public static Board getBoard(String level) throws IOException, ClassNotFoundException {
        Socket cliente= new Socket("localhost",3000);
        System.out.println("Conexion con el servidor exitosa");
        setOutputStreamGame(new ObjectOutputStream(cliente.getOutputStream()));
        setInputStreamGame(new ObjectInputStream(cliente.getInputStream()));
        getOutputStreamGame().writeObject(level);
        getOutputStreamGame().flush();
        return (Board) getInputStreamGame().readObject();
    }


    public static ObjectOutputStream getOutputStreamGame() {
        return outputStreamGame;
    }

    public static void setOutputStreamGame(ObjectOutputStream outputStreamGame) {
        Cliente.outputStreamGame = outputStreamGame;
    }

    public static ObjectInputStream getInputStreamGame() {
        return inputStreamGame;
    }

    public static void setInputStreamGame(ObjectInputStream inputStreamGame) {
        Cliente.inputStreamGame = inputStreamGame;
    }
}
