
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
        req.getRequestDispatcher("clientes.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        
    }
}
