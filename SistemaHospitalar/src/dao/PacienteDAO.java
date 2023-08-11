package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modelo.Paciente;
import persistencia.ConexaoBanco;

public class PacienteDAO {

    private ConexaoBanco conexao;
    private Connection con;

    public PacienteDAO() {
        this.conexao = new ConexaoBanco();
    }

    public void cadastrarPaciente(Paciente pac) throws SQLException {
        try {
            con = conexao.getConexao();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String sql = "INSERT INTO PACIENTE(NOME, ENDERECO, DATA_NASC, TELEFONE, CPF, RG, ID_CONVENIO_FK) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, pac.getNome());
            pst.setString(2, pac.getEndereco());
            pst.setString(3, sdf.format(pac.getDataNascimento()));
            pst.setString(4, pac.getTelefone());
            pst.setString(5, pac.getCpf());
            pst.setString(6, pac.getRg());
            pst.setInt(7, pac.getIdConvenio());
            pst.executeUpdate(); // Utilize executeUpdate para INSERT

        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir dados no Banco de Dados! " + se.getMessage());
        } finally {
            con.close();
        }
    }

    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {
        try {
            String sql = "SELECT * FROM PACIENTE " + query;
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            return mapearPacientes(rs);
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        }
    }

    public ArrayList<Paciente> buscarPaciente() throws SQLException {
        try {
            String sql = "SELECT * FROM PACIENTE";
            PreparedStatement pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            return mapearPacientes(rs);
        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        }
    }

    private ArrayList<Paciente> mapearPacientes(ResultSet rs) throws SQLException {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        while (rs.next()) {
            Paciente pac = new Paciente();
            pac.setIdPaciente(rs.getInt("ID_PACIENTE"));
            pac.setNome(rs.getString("NOME"));
            pac.setEndereco(rs.getString("ENDERECO"));
            pac.setDataNascimento(rs.getDate("DATA_NASC"));
            pac.setTelefone(rs.getString("TELEFONE"));
            pac.setCpf(rs.getString("CPF"));
            pac.setRg(rs.getString("RG"));
            pac.setIdConvenio(rs.getInt("ID_CONVENIO_FK"));
            pacientes.add(pac);
        }
        return pacientes;
    }
}
