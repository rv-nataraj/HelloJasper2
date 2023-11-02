package com.example;

import net.sf.jasperreports.engine.*;
import java.util.*;
import java.sql.*;

public class App 
{
    public static void main( String[] args )
    {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3310/test", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select host,user from  mysql.user");
            while (rs.next()) {
                System.out.println( rs.getString("host") + "  " + rs.getString("user") );
            }

            Map<String, Object> parameters = new HashMap<String, Object>();
            //parameters.put("REPORT_CONNECTION", con);
            parameters.put("attr1","jana babu");
            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/hellodb.jrxml");      
            JasperPrint jasperPrint =  JasperFillManager.fillReport(jasperReport, parameters, con);      
            JasperExportManager.exportReportToPdfFile(jasperPrint, "HelloJasperDb1.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}