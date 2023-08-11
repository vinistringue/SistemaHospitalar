package servicos;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConvenioDAO;
import dao.DAOFactory;
import modelo.Convenio;

/*
 A classe ConvenioServicos representa a camada de serviços da aplicação, ela utiliza a classe ConvenioDAO para realizar
 operações de leitura e escrita no banco de dados.
*/
public class ConvenioServicos {

    // Método para buscar todos os Convenios
    public ArrayList<Convenio> buscarConvenio() throws SQLException {

        // Busca da Fábrica um obj. ConvenioDAO
        ConvenioDAO convenioDAO = DAOFactory.getConvenioDAO();

        // Chamando método buscarConvenios para buscar todos os Convenios
        return convenioDAO.buscarConvenios();
    }

    public Convenio buscarConvenioPorNome(String nomeConvenioSelecionado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
