package com.ute.auctionwebapp.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ute.auctionwebapp.beans.History;
import com.ute.auctionwebapp.beans.Product;
import com.ute.auctionwebapp.beans.User;
import com.ute.auctionwebapp.beans.WatchList;
import com.ute.auctionwebapp.models.HistoryModel;
import com.ute.auctionwebapp.models.ProductModel;
import com.ute.auctionwebapp.models.UserModel;
import com.ute.auctionwebapp.models.WatchListModel;
import com.ute.auctionwebapp.utills.MailUtills;
import com.ute.auctionwebapp.utills.ServletUtills;
import org.sql2o.data.Table;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/Product/*")
@MultipartConfig(
        fileSizeThreshold = 2 * 1024 * 1024,
        maxFileSize = 50 * 1024 * 1024,
        maxRequestSize = 50 * 1024 * 1024
)
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/AddWatchList":
                int proid = Integer.parseInt(request.getParameter("proid"),10);
                String proname = request.getParameter("proname");
                int price_start=Integer.parseInt(request.getParameter("price_start"));
                int uid =Integer.parseInt(request.getParameter("uid"),10);
                int catid =Integer.parseInt(request.getParameter("catid"),10);
                boolean check = WatchListModel.findByProduct(proid,uid);
                if(check == true)
                 {
                     boolean add= WatchListModel.addWatchList(proid,proname,price_start,uid,catid);
                     if( add==true){
                         PrintWriter out = response.getWriter();
                         response.setContentType("application/json");
                         response.setCharacterEncoding("utf-8");
                         out.print(add);
                         out.flush();
                     }
                 }
                break;
            case "/List":
                int catId = Integer.parseInt(request.getParameter("id"));
                List<Product> list = ProductModel.findByCatId(catId);
                request.setAttribute("products",list);
                ServletUtills.forward("/views/vwProduct/ListProduct.jsp", request, response);
                break;
            case "/ListPid":
                String catname = (request.getParameter("name"));
                List<Product> list3 = ProductModel.findByCatPid(catname);
                request.setAttribute("products",list3);
                ServletUtills.forward("/views/vwProduct/ListProductPid.jsp", request, response);
                break;
            case "/Detail":
                int proId = Integer.parseInt(request.getParameter("id"));
                catid = Integer.parseInt(request.getParameter("catid"));
                Product product = ProductModel.findByID(proId);
                List<Product> list4 = ProductModel.findNear(catid,proId);
                List<History> list5 =HistoryModel.findByProduct(proId);
                if(product==null)
                {
                    ServletUtills.redirect("/Home",request,response);
                }
                else {
                    request.setAttribute("product",product);
                    request.setAttribute("products",list4);
                    request.setAttribute("histories",list5);
                    ServletUtills.forward("/views/vwProduct/Detail.jsp", request, response);
                    break;
                }
            case"/Bidding":
                proid  = Integer.parseInt(request.getParameter("proid"),10);
                int new_price = Integer.parseInt(request.getParameter("price"));
                uid =Integer.parseInt(request.getParameter("uid"),10);
                Product product1 = ProductModel.findByID(proid);
                int max = product1.getPrice_max();
                String renew = product1.getRenew();
                int price_step = Integer.parseInt(request.getParameter("step"));
                proname = request.getParameter("proname");
                int sell_id = product1.getSell_id();
                if(max == 0 )
                {
                    boolean update = ProductModel.updatePriceMax(proid,product1.getPrice_start(),new_price,uid,renew) ;
                    PrintWriter out = response.getWriter();
                    out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");

                    out.print(update);
                    out.flush();
                    String email = request.getParameter("email");
                    MailUtills.sendNotify(email,new_price,proname);
                    //Add history
                    LocalDateTime buy_date = LocalDateTime.now();
                    HistoryModel.addHistory(proid,proname,sell_id,uid,buy_date,(product1.getPrice_start()));
                } else {
                    if (max >= new_price) {
                        boolean update = ProductModel.updatePriceCur(proid, (new_price),renew);
                        PrintWriter out = response.getWriter();
                        out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");

                        out.print(update);
                        out.flush();
                        String email = request.getParameter("email");
                        MailUtills.sendNotify(email, new_price, proname);
                        //Add history
                        LocalDateTime buy_date = LocalDateTime.now();
                        HistoryModel.addHistory(proid,proname,sell_id,uid,buy_date,new_price);
                    }
                    if (max < new_price) {
                        boolean update = ProductModel.updatePriceMax(proid, (max + price_step), new_price, uid,renew);
                        PrintWriter out = response.getWriter();
                        out = response.getWriter();
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");

                        out.print(update);
                        out.flush();
                        String email = request.getParameter("email");
                        MailUtills.sendNotify(email, new_price, proname);
                        //Add history
                        LocalDateTime buy_date = LocalDateTime.now();
                        HistoryModel.addHistory(proid,proname,sell_id,uid,buy_date,(max+price_step));
                    }
                }

                break;
            case "/WatchList":
                ServletUtills.forward("/views/vwWatchList/WatchList.jsp", request, response);
                break;
            case "/Add":
                ServletUtills.forward("/views/vwProduct/Add.jsp", request, response);
                break;
            default:
                ServletUtills.forward("/views/404.jsp", request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        request.setCharacterEncoding("UTF-8");
        switch (path) {
            case "/Add":
                add(request, response);
                break;

            default:
                ServletUtills.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strEnd = request.getParameter("end_day") + " 00:00:01";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime end_day = LocalDateTime.parse(strEnd, df);
        LocalDateTime start_day = LocalDateTime.now();

        String proname = request.getParameter("proname");
        String auto = request.getParameter("auto");
        String tinydes = request.getParameter("tinydes");
        String fulldes = request.getParameter("fulldes");
        String status = "Now";
        int sell_id = Integer.parseInt(request.getParameter("uid"));
        int catid = Integer.parseInt(request.getParameter("catid"));
        int start_price = Integer.parseInt(request.getParameter("start_price"));
        int step_price = Integer.parseInt(request.getParameter("step_price"));
        int price_cur = 0;
        int price_max = 0;
        int price_payment = 0;
        int quantity = 1;
        int buy_price = 0;
        if (!request.getParameter("buy_price").equals("") ) {
             buy_price = Integer.parseInt(request.getParameter("buy_price"));
        }

        Product pro = new Product(proname,tinydes,fulldes,quantity,start_price,price_payment,step_price,buy_price,price_cur,start_day,end_day,catid,status,price_max,sell_id,auto);
        int lastid = ProductModel.add(pro);

        int i = 0;
        for (Part part : request.getParts()) {
            if (part.getName().equals("pics[]")) {
                String targetDir = this.getServletContext().getRealPath("public/imgs/products/"+lastid);
                File dir = new File(targetDir);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                String destination;
                if (i==0) {
                    destination = targetDir + "/main.jpg";
                }
                else {
                    destination = targetDir + "/sub"+i+".jpg";
                }
                i ++;

                part.write(destination);

            }
        }

        ServletUtills.forward("/views/vwProduct/Add.jsp", request, response);
    }
}
