import javax.swing.*;

public class Cliente {
    public static void main(String[] args){
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
}
