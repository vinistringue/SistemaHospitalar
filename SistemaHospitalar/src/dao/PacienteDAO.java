/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Paciente;
import persistencia.ConexaoBanco;


/*A classe PacienteDAO é responsável pela comunicação entre a aplicação e o banco de dados, ou seja,
ela é responsável por realizar as operações de cadastro e busca de pacientes no banco de dados.
 */
public class PacienteDAO {

    private ConexaoBanco conexao;
    private Connection con;

    /*No construtor da classe, a instância de ConexaoBanco é criada e 
    armazenada no atributo conexao. 
    Essa instância será usada posteriormente para obter a conexão com o banco de dados.
     */
    public PacienteDAO() {
        this.conexao = new ConexaoBanco();
    }

    // método cadastrarPaciente
    public void cadastrarPaciente(Paciente pac) throws SQLException {

        try {

            con = conexao.getConexao();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            // String que receberá instrução SQL
            String sql = "insert into PACIENTE(NOME, ENDERECO, DATA_NASC, TELEFONE, CPF, RG, ID_CONVENIO_FK) values(?,?,?,?,?,?,?)";

            PreparedStatement pst = this.con.prepareStatement(sql);

            // Atribuindo valores aos parâmetros
            pst.setString(1, pac.getNome());
            pst.setString(2, pac.getEndereco());
            pst.setString(3, sdf.format(pac.getDataNascimento()));
            pst.setString(4, pac.getTelefone());
            pst.setString(5, pac.getCpf());
            pst.setString(6, pac.getRg());
            pst.setInt(7, pac.getIdConvenio());

            // Executando o PreparedStatement
            pst.execute();

        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir dados no Banco de Dados! " + se.getMessage());
        } finally {

            // Encerrando as conexões
            con.close();

        } // fecha finally
    }// fecha método cadastrarPaciente

    // Buscar pacientes com filtro
    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {
        try {
            String sql = "SELECT * FROM paciente " + query;
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Mapear resultados da consulta para objetos Paciente
            return mapearPacientes(rs);
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        }
    }

    // Buscar todos os pacientes
    public ArrayList<Paciente> buscarPaciente() throws SQLException {
        try {
            String sql = "SELECT * FROM PACIENTE";
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Mapear resultados da consulta para objetos Paciente
            return mapearPacientes(rs);
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        }
    }

    // Método privado para mapear resultados da consulta para objetos Paciente
    private ArrayList<Paciente> mapearPacientes(ResultSet rs) throws SQLException {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        while (rs.next()) {
            Paciente pac = new Paciente();

            // Preencher objeto Paciente com dados do ResultSet
            pac.setIdPaciente(rs.getInt("ID_PACIENTE"));
            pac.setNome(rs.getString("NOME"));
            pac.setEndereco(rs.getString("ENDERECO"));
            pac.setDataNascimento(rs.getDate("DATA_NASC"));
            pac.setTelefone(rs.getString("TELEFONE"));
            pac.setCpf(rs.getString("CPF"));
            pac.setRg(rs.getString("RG"));
            pac.setIdConvenio(rs.getInt("ID_CONVENIO_FK"));

            // Adicionar objeto Paciente à lista
            pacientes.add(pac);
        }

        return pacientes;
    }

}
