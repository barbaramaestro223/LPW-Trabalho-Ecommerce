/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Item;
import modelos.Produto;

/**
 *
 * @author Desenvolvimento
 */
public class AddCarrinho extends HttpServlet {


   /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

           int idProduto = Integer.parseInt(request.getParameter("id"));
           int quantidade = Integer.parseInt(request.getParameter("quantidade"));

           Item item = (Item)Produto.lista.get(idProduto);
           item.setQuantidade(quantidade);

           HttpSession session = request.getSession();

           //cria um carrinho vazio
           ArrayList<Item> carrinho;

           //se a lista esta vazia
           if(session != null && session.getAttribute("carrinho") != null){
               carrinho = (ArrayList<Item>)session.getAttribute("carrinho");
           }else{
               carrinho = new ArrayList();
           }

           carrinho.add(item);
           session.setAttribute("carrinho", carrinho);

           response.sendRedirect("carrinho.jsp");
   }


}
