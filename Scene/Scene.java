import javax.swing.*;

public class Scene {
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setBounds(f.getBounds());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}