import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EventListener;
import java.util.List;
import java.util.function.Consumer;

public class Game extends JFrame implements EventListener {
    private JPanel wprin;
    private JTextField textField1;
    private JButton conectarseButton;
    private JComboBox comboBox1;
    private JButton jugarButton;
    private JPanel Board;
    JButton[][] buttonsBoard;
    Board gameBoard;

    public Game() {
        super("Buscaminas");
        Board.setLayout(null);
        setContentPane(wprin);

        conectarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tryConnection(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startGame(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void tryConnection(ActionEvent e) throws IOException, ClassNotFoundException {
        Cliente.initConecct();
    }

    private void startGame(ActionEvent e) throws IOException, ClassNotFoundException {
        Board.removeAll();
        if (comboBox1.getSelectedItem().toString() == "Facil") {
            GenerateBoard(9, 9);
            createBoardB("Facil");
            gameBoard.printBoardCmd();
        } else if (comboBox1.getSelectedItem().toString() == "Intermedio") {
            GenerateBoard(16, 16);
            createBoardB("Intermedio");
            gameBoard.printBoardCmd();
        } else {
            GenerateBoard(16, 30);
            createBoardB("Dificil");
            gameBoard.printBoardCmd();
        }

        Board.updateUI();
    }

    private void createBoardB(String level) throws IOException, ClassNotFoundException {
        gameBoard = Cliente.getBoard(level);
        gameBoard.printBoardCmd();
        gameBoard.setEventLostGame(new Consumer<List<Tile>>() {
            @Override
            public void accept(List<Tile> tiles) {
                for (Tile tileMined : tiles) {
                    buttonsBoard[tileMined.getPosRow()][tileMined.getPosCol()].setText("X");
                    buttonsBoard[tileMined.getPosRow()][tileMined.getPosCol()].setEnabled(false);
                }
                for (JButton[] lostB : buttonsBoard) {
                    for (JButton selected : lostB) {
                        selected.setEnabled(false);
                    }
                }
                JOptionPane.showMessageDialog(null, "Has perdido");
            }
        });

        gameBoard.setEventTileOpenned(new Consumer<Tile>() {
            @Override
            public void accept(Tile tile) {
                buttonsBoard[tile.getPosRow()][tile.getPosCol()].setEnabled(false);
                if (tile.getNumMines() == 0) {
                    buttonsBoard[tile.getPosRow()][tile.getPosCol()].setText("");
                } else {
                    buttonsBoard[tile.getPosRow()][tile.getPosCol()].setText(tile.getNumMines() + "");
                }
            }
        });
    }

    public void GenerateBoard(int numRow, int numCol) {
        int xRef = 20;
        int yRef = 40;
        int widthB = 30;
        int heightB = 30;
        buttonsBoard = new JButton[numRow][numCol];
        for (int i = 0; i < buttonsBoard.length; i++) {
            for (int j = 0; j < buttonsBoard[i].length; j++) {
                buttonsBoard[i][j] = new JButton();
                buttonsBoard[i][j].setName(i + "," + j);
                buttonsBoard[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                buttonsBoard[i][j].setBorder(null);
                if (i == 0 && j == 0) {
                    buttonsBoard[i][j].setBounds(xRef, yRef, widthB, heightB);
                } else if (i == 0 && j != 0) {
                    buttonsBoard[i][j].setBounds(buttonsBoard[i][j - 1].getX() + buttonsBoard[i][j - 1].getWidth() + 2, yRef, widthB, heightB);
                } else {
                    buttonsBoard[i][j].setBounds(buttonsBoard[i - 1][j].getX(), buttonsBoard[i - 1][j].getY() + buttonsBoard[i - 1][j].getWidth() + 2, widthB, heightB);
                }
                buttonsBoard[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        btnClick(e);
                    }
                });
                Board.add(buttonsBoard[i][j]);
            }
        }
    }

    private void btnClick(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String[] cordenada = btn.getName().split(",");
        int posRow = Integer.parseInt(cordenada[0]);
        int posCol = Integer.parseInt(cordenada[1]);
        //JOptionPane.showMessageDialog(rootPane, posRow + "," + posCol);Solo para debugg
        gameBoard.selectTile(posRow, posCol);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        wprin = new JPanel();
        wprin.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        wprin.setAutoscrolls(false);
        wprin.setMaximumSize(new Dimension(100, 200));
        wprin.setMinimumSize(new Dimension(50, 100));
        wprin.setPreferredSize(new Dimension(465, 100));
        wprin.setBorder(BorderFactory.createTitledBorder(null, "Nombre", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Label");
        wprin.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        wprin.add(textField1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        conectarseButton = new JButton();
        conectarseButton.setText("Conectarse");
        wprin.add(conectarseButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Facil");
        defaultComboBoxModel1.addElement("Intermedio");
        defaultComboBoxModel1.addElement("Dificil");
        comboBox1.setModel(defaultComboBoxModel1);
        wprin.add(comboBox1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jugarButton = new JButton();
        jugarButton.setText("Jugar");
        wprin.add(jugarButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Board = new JPanel();
        Board.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        wprin.add(Board, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return wprin;
    }

}
