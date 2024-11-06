package conectandoBanco;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class NavegaFilmes extends JFrame{
    private JLabel label1,label2,label3,label4,label5;
    private JTextField tfCodigo, tfTitulo, tfGenero, tfProdutora, tfdatCom;
    private JButton btProximo, btAnterior,btPrimeiro,btUltimo, btMais10,btMenos10,btSai;
    private BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public static void main(String[] args) {
        JFrame frame = new NavegaFilmes();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public NavegaFilmes(){
        inicializarComponetes();
        definirEventos();
    }

    public void inicializarComponetes() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        label1 = new JLabel("Código ");
        label2 = new JLabel("Titulo ");
        label3 = new JLabel("Gênero ");
        label4 = new JLabel("Produtora ");
        label5 = new JLabel("Data de Compra ");

        tfCodigo = new JTextField(10);
        tfTitulo = new JTextField(35);
        tfGenero = new JTextField(10);
        tfProdutora = new JTextField(15);
        tfdatCom = new JTextField(8);

        btProximo = new JButton(null,new ImageIcon("icones/proximo.gif"));
        btProximo.setToolTipText("Próximo");

        btAnterior = new JButton(null,new ImageIcon("icones/anterior.gif"));
        btAnterior.setToolTipText("Anterior");

        btPrimeiro = new JButton(null,new ImageIcon("icones/primeiro.gif"));
        btPrimeiro.setToolTipText("Primeiro");

        btUltimo = new JButton(null,new ImageIcon("icones/ultimo.gif"));
        btUltimo.setToolTipText("Ultimo");

        btMais10 =  new JButton(null,new ImageIcon("icones/mais.png"));
        btMais10.setToolTipText("+10");

        btMenos10 =  new JButton(null,new ImageIcon("icones/menos.png"));
        btMenos10.setToolTipText("-10");

        btSai =  new JButton(null,new ImageIcon("icones/sair.png"));
        btSai.setToolTipText("sair");

        add(label1);
        add(tfCodigo);
        add(label2);
        add(tfTitulo);
        add(label3);
        add(tfGenero);
        add(label4);
        add(tfProdutora);
        add(label5);
        add(tfdatCom);

        add(btPrimeiro);
        add(btAnterior);
        add(btProximo);
        add(btUltimo);
        add(btMais10);
        add(btMenos10);
        add(btSai);

        setTitle("Navegando na tabela de Filmes");

        setBounds(200, 400,620,120);
        setResizable(false);
        bd = new BD();

        if(!bd.getConnection()) {
            JOptionPane.showMessageDialog(null,"Falha ao conectar, o sistema será fechado!");
            System.exit(0);
        }
        carregarTabela();
        atualizarCampos();
    }

    public void definirEventos() {
        btProximo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.next();
                    atualizarCampos();
                } catch (SQLException ero){
                }
            }
        });
         btAnterior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.previous();
                    atualizarCampos();
                } catch (SQLException ero){
                }
            }
        });

       btPrimeiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.first();
                    atualizarCampos();
                } catch (SQLException ero){
                }
            }
        });

       btUltimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.last();
                    atualizarCampos();
                } catch (SQLException ero){
                }
            }
        });

       btMais10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.relative(10);
                    atualizarCampos();
                } catch (SQLException ero){
                }
            }
        });

       btMenos10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (resultSet.getRow() > 10){
                        resultSet.relative(-10);
                    }else {
                        resultSet.first();
                    }
                    atualizarCampos();
                } catch (SQLException ero){
                }
            }
        });

       btSai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resultSet.close();
                    statement.close();
                } catch (SQLException ero){
                }
                bd.close();
                System.exit(0);
            }
        });

    }

    public void carregarTabela() {
        String sql = "select * from filmes";
        try {
            statement = bd.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        }catch (SQLException er){
            JOptionPane.showMessageDialog(null,"Erro" + er.toString());
        }
    }

    public void atualizarCampos(){
        try {
            if (resultSet.isAfterLast()){
                resultSet.last();
            }
            if(resultSet.isBeforeFirst()){
                resultSet.first();
            }

            tfCodigo.setText(resultSet.getString("codigo"));
            tfTitulo.setText(resultSet.getString("titulo"));
            tfGenero.setText(resultSet.getString("genero"));
            tfProdutora.setText(resultSet.getString("produtora"));
            tfdatCom.setText("" + resultSet.getDate("datacompra"));
        } catch (SQLException er){
            
        }
    }
}
