package gg.stephen.pt.gui;

import gg.stephen.pt.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URI;

public class GUI extends JFrame {

    private Game game;

    public GUI(Game game) throws IOException {
        super("Password Trainer");

        this.game = game;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 140);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setContentPane(panel);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        setIconImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("icon.png")));

        DragListener frameDragListener = new DragListener(this);
        addMouseListener(frameDragListener);
        addMouseMotionListener(frameDragListener);

        setVisible(true);
    }

    private JPanel panel, bottomSpacing, topSpacing, instructionPanel;
    private JTextField input;
    private JButton inputButton;
    private JLabel instruction, instructionTooltip;

    private void createUIComponents() {
        bottomSpacing = new JPanel();
        bottomSpacing.setLayout(new BoxLayout(bottomSpacing, BoxLayout.Y_AXIS));

        topSpacing = new JPanel();
        topSpacing.setBackground(Color.decode("#292929"));
        topSpacing.setForeground(Color.decode("#292929"));

        ActionListener myActionListener = e -> {
            if (input.getText().length() == 0) return;
            String[] response = game.next(input.getText());
            input.setText("");
            if (response.length == 1) {
                instruction.setText(response[0]);
                instructionTooltip.setText(" ");
                return;
            }
            instruction.setText(response[1]);
            instructionTooltip.setText(response[0]);
        };

        input = new JPasswordField();
        input.setBackground(Color.decode("#292929"));
        input.setBorder(new LineBorder(Color.decode("#292929"), 1));
        input.setPreferredSize(new Dimension(getWidth(), 30));
        input.setFont(new Font("Ariel", Font.PLAIN, 23));
        input.addActionListener(myActionListener);

        inputButton = new JButton();
        inputButton.addActionListener(myActionListener);

        instructionPanel = new JPanel();
        instructionPanel.setLayout(new BoxLayout(instructionPanel, BoxLayout.Y_AXIS));

        instructionTooltip = new JLabel("                        ");
        instructionTooltip.setFont(new Font("Ariel", Font.BOLD, 13));
        instructionTooltip.setForeground(Color.decode("#bababa"));
        instructionPanel.add(instructionTooltip);

        instruction = new JLabel("Enter password to start training.");
        instruction.setFont(new Font("Ariel", Font.BOLD, 13));
        instruction.setForeground(Color.decode("#bababa"));
        instructionPanel.add(instruction);
        instructionPanel.setBackground(Color.decode("#292929"));

        JLabel opensrc = new JLabel("Your password is never stored. Click to view source.");
        opensrc.setFont(new Font("Ariel", Font.PLAIN, 13));
        opensrc.setForeground(Color.decode("#bababa"));
        opensrc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        opensrc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://github.com/sttephen/password-trainer"));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        bottomSpacing.add(opensrc);
    }

}