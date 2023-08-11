package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Convenio;
import persistencia.ConexaoBanco;

/**
 * A classe ConvenioDAO é responsável pela comunicação entre a aplicação e o banco de dados, ou seja,
 * ela é responsável por realizar as operações de cadastro e busca de convênios no banco de dados.
 */
public class ConvenioDAO {

    private ConexaoBanco conexao;
    private Connection con;
    
    public Convenio buscarConvenioPorNome(String nomeConvenio) throws SQLException {
        ResultSet rs;

        try {
            String sql = "SELECT * FROM CONVENIO WHERE NOME_CONVENIO LIKE '%" + nomeConvenio + "%';";
            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                Convenio convenio = new Convenio();
                convenio.setIdConvenio(rs.getInt("ID_CONVENIO"));
                convenio.setNomeConvenio(rs.getString("NOME_CONVENIO"));
                convenio.setTempoCarencia(rs.getString("TEMPO_CARENCIA"));
                convenio.setCnpj(rs.getString("CNPJ"));
                return convenio;
            }

            return null;
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar convênio no Banco! " + se.getMessage());
        } finally {
            con.close();
        }
    }
    

    /* No construtor da classe, a instância de ConexaoBanco é criada e 
       armazenada no atributo conexao. 
       Essa instância será usada posteriormente para obter a conexão com o banco de dados.
    */
    public ConvenioDAO() {
        this.conexao = new ConexaoBanco();
        
    }

    /* Método para buscar todos os convênios no banco de dados.
       Retorna um ArrayList contendo os convênios encontrados.
    */
    public ArrayList<Convenio> buscarConvenios() throws SQLException {
        ResultSet rs;

        try {
            String sql = "SELECT * FROM CONVENIO";
            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            ArrayList<Convenio> listaConvenios = new ArrayList<Convenio>();

            /* Enquanto houver uma próxima linha no banco de dados, o while roda */
            while (rs.next()) {
                Convenio convenio = new Convenio();

                /* Mapeando a tabela do banco para o objeto convenio */
                convenio.setIdConvenio(rs.getInt("ID_CONVENIO"));
                convenio.setNomeConvenio(rs.getString("NOME_CONVENIO"));
                convenio.setTempoCarencia(rs.getString("TEMPO_CARENCIA"));
                convenio.setCnpj(rs.getString("CNPJ"));

                /* Adicionando o objeto convenio no ArrayList listaConvenios */
                listaConvenios.add(convenio);
            }

            // Retorna o resultado da consulta ao banco de dados em forma de um ArrayList contendo objetos do tipo Convenio
            return listaConvenios;
        } catch (SQLException se) {
            // Se houver algum erro na busca, lança uma exceção SQLException
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            // Fechando a conexão com o banco de dados
            con.close();
        }
    }

    /* Método para buscar um convênio pelo nome (filtro).
       Retorna um objeto do tipo Convenio ou null se não encontrado.
    */
    public Convenio buscarConvenioFiltro(String query) throws SQLException {
        ResultSet rs;

        try {
            String sql = "SELECT * FROM CONVENIO WHERE NOME_CONVENIO LIKE '%" + query + "%';";
            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            // Verifica se a consulta retornou algum resultado
            if (rs.next()) {
                Convenio convenio = new Convenio();

                /* Mapeando a tabela do banco para o objeto convenio */
                convenio.setIdConvenio(rs.getInt("ID_CONVENIO"));
                convenio.setNomeConvenio(rs.getString("NOME_CONVENIO"));
                convenio.setTempoCarencia(rs.getString("TEMPO_CARENCIA"));
                convenio.setCnpj(rs.getString("CNPJ"));

                // Retorna o objeto Convenio criado
                return convenio;
            }

            // Se não houver resultado na consulta, retorna null
            return null;
        } catch (SQLException se) {
            // Se houver algum erro na busca, lança uma exceção SQLException
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            con.close();
        }
    }
}
