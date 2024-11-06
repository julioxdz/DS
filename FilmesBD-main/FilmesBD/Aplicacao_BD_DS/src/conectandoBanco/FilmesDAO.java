package conectandoBanco;

import java.sql .*;

public class FilmesDAO {
    public Filmes filme;
    public BD bd;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String men, sql;
    public static final byte INCLUSAO=1;
    public static final byte ALTERACAO=2;
    public static final byte EXCLUSAO=3;

    public FilmesDAO(){
        bd= new BD();
        filme= new Filmes();
    }
    public boolean localizar() {
        sql = "select * from filmes where codigo = ?";

    try{
        statement = bd.connection.prepareStatement(sql);
        statement.setString(1, filme.getCodigo());
        resultSet = statement.executeQuery();
        resultSet.next();
        filme.setCodigo(resultSet.getString(1));
        filme.setTitulo(resultSet.getString(2));
        filme.setGenero(resultSet.getString(3));
        filme.setProdutora(resultSet.getString(4));
        filme.setDataCompra("" + resultSet.getString(5));
        return true;
    } catch (SQLException erro){
        return false;
    }
}
public String atualizar (int operacao) {
    men = "Operação realizada com sucesso!";
    try {
        if (operacao == INCLUSAO) {

            sql = "insert into filmes (codigo, titulo, genero, produtora, datacompra) values (?,?,?,?,?)";

            statement = bd.connection.prepareStatement(sql);

            statement.setString(1, filme.getCodigo());
            statement.setString(2, filme.getTitulo());
            statement.setString(3, filme.getGenero());
            statement.setString(4, filme.getProdutora());
            statement.setString(5, filme.getDataCompra());

        } else if (operacao == ALTERACAO) {
            sql = "uptade filmes set titulo = ?, genero = ?, produtora = ?,"
                    + "datacaompra = ? where codigo = ?";

            statement = bd.connection.prepareStatement(sql);

            statement.setString(5, filme.getCodigo());
            statement.setString(1, filme.getTitulo());
            statement.setString(2, filme.getGenero());
            statement.setString(3, filme.getProdutora());
            statement.setString(4, filme.getDataCompra());

        } else if (operacao == EXCLUSAO) {
            sql = "delete from filmes where codigo = ?";

            statement = bd.connection.prepareStatement(sql);

            statement.setString(1, filme.getCodigo());
        }
        if (statement.executeUpdate() == 0) {
            men = "Falhe na operacao!";
        }
    } catch (SQLException erro) {
        men = "Falha na operacao " + erro.toString();
    }
    return men;
}}

