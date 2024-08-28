import javax.swing.JOptionPane;
public class TryCatch {
    public static void main(String[] args) {
        try {
            String aux1 = JOptionPane.showInputDialog("Forneça o valor do 1º número!");
            int num1 = Integer.parseInt(aux1);
            String aux2 = JOptionPane.showInputDialog("Forneça o valor do 2º número!");
            int num2 = Integer.parseInt(aux2);
            JOptionPane.showMessageDialog(null, "Soma = " + (num1 + num2));
            JOptionPane.showMessageDialog(null, "Subtração = " + (num1 - num2));
            JOptionPane.showMessageDialog(null, "Multiplicação = " + num1 * num2);
            JOptionPane.showMessageDialog(null, "Divisão = " + (num1 / num2));
        } catch (ArithmeticException erro) {
            JOptionPane.showMessageDialog(null, "Erro de divisão de Zero'\n" + erro,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conversão\n" + erro,
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException erro) {
            JOptionPane.showMessageDialog(null, "Tecla Cancel pressionada\n" + erro,
                    "Cancelado pelo usuário", JOptionPane.ERROR_MESSAGE);
        } finally {
            JOptionPane.showMessageDialog(null, "Final da execução!");
        }
        System.exit(0);
    }
}