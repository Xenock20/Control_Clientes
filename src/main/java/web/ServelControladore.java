
package web;

import datos.ClienteDAO;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/ServelControlador")
public class ServelControladore extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Cliente> clientes = new ClienteDAO().listar();
        req.setAttribute("clientes", clientes);
        req.setAttribute("totalClientes", clientes.size());
        req.setAttribute("saldoTotal", calcularSaldoTotal(clientes));
        req.getRequestDispatcher("clientes.jsp").forward(req, resp);
    }
    
    private double calcularSaldoTotal(List<Cliente> clientes){
        double saldoTotal = 0;
        
        for(Cliente cl: clientes){
            saldoTotal += cl.getSaldo();
        }
        
        return saldoTotal;
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        
    }
}
