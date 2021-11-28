package com.ute.auctionwebapp.models;

import com.ute.auctionwebapp.beans.Product;
import com.ute.auctionwebapp.utills.DbUtills;
import org.sql2o.Connection;

import java.util.List;

public class ProductModel {
    public static List<Product> findAll(){
        final String query = "select * from products";
        try (Connection con = DbUtills.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findTop8End(){
        final String query = "SELECT * from products where TIMESTAMPDIFF(SECOND,NOW(),products.end_day)>0 order by products.end_day asc limit 8";
        try (Connection con = DbUtills.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findTop8Price(){
        final String query = "SELECT * from products where TIMESTAMPDIFF(SECOND,NOW(),products.end_day)>0 order by products.price_current desc limit 8";
        try (Connection con = DbUtills.getConnection()) {
            return con.createQuery(query)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findByCatId(int catId){
        final String query = "select * from products where catid = :catid";
        try (Connection con = DbUtills.getConnection()) {
            return con.createQuery(query)
                    .addParameter("catid",catId)
                    .executeAndFetch(Product.class);
        }
    }
    public static List<Product> findByCatPid(String catname){
        final String query = "select products.proid,products.proname,products.price_start\n" +
                "from auction.products , auction.categories\n" +
                "where categories.pid=(select catid from auction.categories where catname=:catname)\n" +
                "  and categories.catid=products.catid";
        try (Connection con = DbUtills.getConnection()) {
            return con.createQuery(query)
                    .addParameter("catname",catname)
                    .executeAndFetch(Product.class);
        }
    }
    public static Product findByID(int id){
        final String query = "select * from products where proid=:proid";
        try (Connection con = DbUtills.getConnection()) {
            List<Product> list = con.createQuery(query)
                    .addParameter("proid",id)
                    .executeAndFetch(Product.class);
            if(list.size()==0)
            {
                return null;
            }
            return list.get(0);
        }
    }

}