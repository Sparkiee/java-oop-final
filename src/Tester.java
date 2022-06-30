import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Charts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 500);
        NatalityPanel pan = new NatalityPanel();
        frame.add(pan);
        frame.setVisible(true);

    }
}
