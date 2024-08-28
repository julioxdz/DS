import javax.swing.JOptionPane;
import java.util.concurrent.ExecutionException;

public class GetMessageEPrintstackTrace {
    public static void main(String args[]) {
        int x = Integer.parseInt(JOptionPane.showInputDialog("Forneça um número"));
        try {
            int y = x / 0;
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
            erro.printStackTrace();
        }
    }
}