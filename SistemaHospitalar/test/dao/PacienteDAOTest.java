/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import modelo.Paciente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vinic
 */
public class PacienteDAOTest {
    
    public PacienteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cadastrarPaciente method, of class PacienteDAO.
     */
    @Test
    public void testCadastrarPaciente() throws Exception {
        System.out.println("cadastrarPaciente");
        Paciente pac = null;
        PacienteDAO instance = new PacienteDAO();
        instance.cadastrarPaciente(pac);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPacienteFiltro method, of class PacienteDAO.
     */
    @Test
    public void testBuscarPacienteFiltro() throws Exception {
        System.out.println("buscarPacienteFiltro");
        String query = "";
        PacienteDAO instance = new PacienteDAO();
        ArrayList<Paciente> expResult = null;
        ArrayList<Paciente> result = instance.buscarPacienteFiltro(query);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPaciente method, of class PacienteDAO.
     */
    @Test
    public void testBuscarPaciente() throws Exception {
        System.out.println("buscarPaciente");
        PacienteDAO instance = new PacienteDAO();
        ArrayList<Paciente> expResult = null;
        ArrayList<Paciente> result = instance.buscarPaciente();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
