/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicos;

import dao.DAOFactory;
import dao.PacienteDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Paciente;

/**
 *
 * @author senacead
 */
/*
A classe PacienteServicos representa a camada de serviços da aplicação, ela utiliza a classe PacienteDAO para realizar
operações de leitura e escrita no banco de dados.
 */
public class PacienteServicos {

    // Método para cadastrar um paciente
    public void cadastrarPaciente(Paciente pac) throws SQLException {

        // Busca da Fábrica um obj. PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chamando método cadastrarPaciente para enviar o obj. pac
        pacDAO.cadastrarPaciente(pac);
    }

    // Método para buscar um paciente por ID
    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {
    try {
        // Busca da Fábrica um objeto PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chama o método buscarPacienteFiltro do PacienteDAO passando a consulta
        return pacDAO.buscarPacienteFiltro(query);
    } catch (SQLException e) {
        // Trata a exceção e lança novamente para a camada superior
        throw new SQLException("Erro ao buscar pacientes com filtro: " + e.getMessage());
    }
}

// Método para buscar todos os pacientes
public ArrayList<Paciente> buscarPaciente() throws SQLException {
    try {
        // Busca da Fábrica um objeto PacienteDAO
        PacienteDAO pacDAO = DAOFactory.getPacienteDAO();

        // Chama o método buscarPaciente do PacienteDAO para buscar todos os pacientes
        return pacDAO.buscarPaciente();
    } catch (SQLException e) {
        // Trata a exceção e lança novamente para a camada superior
        throw new SQLException("Erro ao buscar todos os pacientes: " + e.getMessage());
    }
}


}
