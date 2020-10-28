import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    protected JFrame frame;
    protected JPanel panel;
    protected JPanel footer;
    protected Font font;
    protected Puzzle puzzle;

    public GUI() {
        this.puzzle = new Puzzle();
        this.puzzle.shuffle();
        this.font = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.frame = new JFrame("15 Puzzle");
        this.frame.setSize(300, 300);
        this.frame.setLocation(1000, 500);
        this.panel = new JPanel();
        this.footer = new JPanel();
        this.frame.add(this.panel, BorderLayout.NORTH);
        JButton newGameBtn = new JButton("New Game");
        newGameBtn.addActionListener(this);
        this.footer.add(newGameBtn, BorderLayout.SOUTH);
        this.frame.add(this.footer);
        this.frame.setVisible(true);
    }

    public void render() {
        this.panel.removeAll();
        this.panel.revalidate();
        this.panel.repaint();
        this.panel.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < puzzle.getTiles().length; i++) {
            JButton button;
            if(puzzle.getTiles()[i][0] != 16) button = new JButton(String.valueOf(puzzle.getTiles()[i][0]));
            else button = new JButton("");
            button.setPreferredSize(new Dimension(40, 40));
            button.setBackground(Color.WHITE);
            button.setFont(this.font);
            button.addActionListener(this);
            this.panel.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getActionCommand() == "New Game") {
                this.puzzle.shuffle();
            } else {
                this.puzzle.moveTile(Integer.parseInt(e.getActionCommand()));
            }
            this.render();
        } catch (Exception err) {
            System.out.println("There is no tile here!");
        }
    }

}
