package conectandoBanco;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
public class ConsultaSQL extends JFrame{
    private JLabel label1;
    private JTextField tfSQL;
    private JButton btexecutar;
    private JScrollPane scrollTable;
    private JTable table;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public static void main(String[] args) {
        JFrame frame = new ConsultaSQL();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public ConsultaSQL(){
        inicializarComponetes();
        definirEventos();
    }
    public void inicializarComponetes(){
        setLayout(null);
        setTitle("Aprendo consultas em SQL");
        setBounds(200,200,600,600);
        setResizable(false);
        label1 = new JLabel("Digite o camando SQL");
        label1.setBounds(50,10,200,25);

        tfSQL = new JTextField(50);
        tfSQL.setBounds(50,70,100,25);

        btexecutar = new JButton("Executar");
        btexecutar.setBounds(50,70,100,25);

        scrollTable = new JScrollPane();
        scrollTable.setBounds(50, 100, 500, 300);

        add(scrollTable);
        add(label1);
        add(tfSQL);
        add(btexecutar);

        bd = new BD();
    }

    public void definirEventos(){
        btexecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfSQL.getText().equals("")) {
                    return;
                }
                try {
                    if (!bd.getConnection()){
                        JOptionPane.showMessageDialog(null, "Falha na conexão, o sistema será fechado!");
                        System.exit(0);
                    }
                    statement = bd.connection.prepareStatement(tfSQL.getText());
                    resultSet = statement.executeQuery();
                    DefaultTableModel tableModel = new DefaultTableModel(
                            new String[] {},0) {
                            };
                    int qtdColunas = resultSet.getMetaData().getColumnCount();
                    for (int indice = 1; indice <= qtdColunas; indice++) {
                        tableModel.addColumn(resultSet.getMetaData().getColumnName(indice));
                    }

                    table = new JTable(tableModel);
                    DefaultTableModel dtm = (DefaultTableModel) table.getModel();

                    while(resultSet.next()){
                        try {
                            String[] dados = new String[qtdColunas];
                            for (int i = 1; i<= qtdColunas; i++){
                                dados[i - 1] = resultSet.getString(i);
                            }

                            dtm.addRow(dados);
                            System.out.println();
                        }catch (SQLException erro){
                        }
                        scrollTable.setViewportView(table);
                    }
                    resultSet.close();
                    statement.close();
                    bd.close();
                }catch (Exception err){
                    JOptionPane.showMessageDialog(null,"Comando SQL invalido !" + err.toString());
                }
            }
        });
    }
}
