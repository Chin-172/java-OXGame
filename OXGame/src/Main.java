import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main {
    static JPanel homePanel = new JPanel();
    static JPanel gamePanel = new JPanel();

    private static void swingUI(){
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("OXGame");
        frame.setSize(1024, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        homeLayout(frame);
        homePanel.setVisible(true);
        gamePanel.setVisible(false);
        frame.add(gamePanel);
        frame.add(homePanel);
        frame.setVisible(true);
    }

    private static void homeLayout(JFrame frame){
        BoxLayout layout = new BoxLayout(homePanel, BoxLayout.Y_AXIS);

        homePanel.setLayout(layout);

        JLabel label = new JLabel("OX Game");
        label.setFont(new Font("Baloo",Font.BOLD,40));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(label);

        JButton startBtn = new JButton("Start");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        homePanel.add(startBtn);

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homePanel.setVisible(false);
                gameLayout(frame);
            }
        });

    }

    private static void gameLayout(JFrame frame){
        OXManager oxManager = new OXManager();
        GridLayout layout = new GridLayout(2,1);
        gamePanel = new JPanel();
        gamePanel.setLayout(layout);

        JLabel label = new JLabel("OX Game",SwingConstants.CENTER);
        label.setFont(new Font("Baloo",Font.BOLD,40));
        gamePanel.add(label);

        JPanel oxPanel = new JPanel();
        GridLayout oxLayout = new GridLayout(3,3);
        oxPanel.setMaximumSize(new Dimension(300, 300));
        oxPanel.setLayout(oxLayout);




        for(int i = 1 ; i <= 9 ; i++){
            JButton btn = new JButton(String.valueOf(i));
            btn.setPreferredSize(new Dimension(50, 50));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handling 'O' or 'X'
                    try{
                        int i = Integer.parseInt(e.getActionCommand())-1;

                        oxManager.setData(i);
                        btn.setText(oxManager.getPlayer());
                        oxManager.roundDone();

                        String winner = oxManager.getWinner();
                        if(!Objects.equals(winner, "")){
                            System.out.println("win: "+winner);
                            JOptionPane.showMessageDialog(frame, winner +" win!");
                            gamePanel.setVisible(false);
                            homePanel.setVisible(true);
                        }
                    }catch (Exception ignored){

                    }
                }
            });
            oxPanel.add(btn);
        }

        gamePanel.add(oxPanel);
        gamePanel.setVisible(true);
        frame.add(gamePanel);

    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingUI();
            }
        });

    }
}