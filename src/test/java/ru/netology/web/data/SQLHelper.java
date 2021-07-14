package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.val;

import java.sql.DriverManager;

public class SQLHelper {
     public static final String APPROVED = "APPROVED";
     public static final String DECLINED = "DECLINED";
     private static final String url = "jdbc:mysql://localhost:3306/app"; // для MySQL
     //для Postgres
     //private static final String url = "jdbc:postgresql:5432/app";
     private static final String user = "app";
     private static final String password = "pass";
     public static final String REQUEST_ORDER_ENTITY = "SELECT payment_id FROM order_entity";
     public static final String COLUMN_PAYMENT_ID = "payment_id";
     public static final String REQUEST_STATUS_CREDIT = "SELECT status FROM credit_request_entity";
     public static final String COLUMN_STATUS_CREDIT = "status";
     public static final String REQUEST_BANK_ID_CREDIT = "SELECT bank_id FROM credit_request_entity";
     public static final String COLUMN_BANK_ID_CREDIT = "bank_id";
     public static final String REQUEST_STATUS_PAYMENT = "SELECT status FROM payment_entity";
     public static final String COLUMN_STATUS_PAYMENT = "status";
     public static final String REQUEST_TRANSACTION_ID_PAYMENT = "SELECT transaction_id FROM payment_entity";
     public static final String COLUMN_TRANSACTION_ID_PAYMENT = "transaction_id";

     @SneakyThrows
     public static void clearTables() {
          val dataSQL1 = "DELETE FROM credit_request_entity";
          val dataSQL2 = "DELETE FROM order_entity";
          val dataSQL3 = "DELETE FROM payment_entity";

          try (val conn = DriverManager.getConnection(
                  url, user, password);
               val dataStmt1 = conn.prepareStatement(dataSQL1);
               val dataStmt2 = conn.prepareStatement(dataSQL2);
               val dataStmt3 = conn.prepareStatement(dataSQL3);
          ) {
               dataStmt1.executeUpdate();
               dataStmt2.executeUpdate();
               dataStmt3.executeUpdate();
          }

     }

     @SneakyThrows
     public static String getStatusPayment(String dataRequest, String nameColumn) {
          String dataSQL = null;
          try (val conn = DriverManager.getConnection(
                  url, user, password);
               val dataStmt1 = conn.prepareStatement(dataRequest);

          ) {

               try (val rs = dataStmt1.executeQuery(dataRequest)) {
                    while (rs.next()) {
                         dataSQL = rs.getString(nameColumn);
                    }

               } catch (Exception exception) {
                    System.out.println("ОШИБКА! Столбца с названием " + nameColumn + " не существует!");
               }
          }
          return dataSQL;
     }
}






