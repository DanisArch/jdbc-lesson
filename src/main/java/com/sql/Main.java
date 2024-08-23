/*package com.sql;

import com.sql.config.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
/*
    private static final String CREATE_SQL = """
            create table jdbc1
            (
            id int auto_increment primary key,
            info text
            );
            """;

    private static final String INSERT_SQL = """
            INSERT INTO jdbc1(info)
            VALUES ("test1"),
                   ("test2"),
                   ("test3")
            """;

    private static final String UPDATE_SQL = """
            UPDATE jdbc1(info)
            set info = 'test32'
            where id = 3;
            """;

    private static final String DELETE_SQL = """
            DELETE FROM jdbc1
            where id = 3;
            """;

    private static final String SELECT_SQL = """
            SELECT id, info
            FROM jdbc1
            -- where id = 1;
            """;


    public static void main(String[] args) {
/*
    try (Connection connection = ConnectionManager.openConnection()) {
        System.out.println("connection.getTransactionIsolation() = " + connection.getTransactionIsolation());
        Statement statement = connection.createStatement();

        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("metaData.getDriverVersion() = " + metaData.getDriverVersion());
        System.out.println("metaData.getColumns() = " + metaData.getColumns("jdbc1", "jdbc1", "jdbc1", "jdbc1"));

/*        boolean execute = statement.execute(CREATE_SQL);
        System.out.println(execute);*/

 /*       int update  = statement.executeUpdate(INSERT_SQL);
        System.out.println(update);  */

 /*       int update  = statement.executeUpdate(UPDATE_SQL);
        System.out.println(update);*/
/*
        int update  = statement.executeUpdate(DELETE_SQL);
        System.out.println(update); */
/*
        ResultSet resultSet  = statement.executeQuery(SELECT_SQL);
        while (resultSet.next()) {
            int antInt = resultSet.getInt("id");
            String string = resultSet.getString("info");
            System.out.println(antInt);
            System.out.println(string);
        }

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
        /*
        String invoiceId = "1 or 1=1;";
        List<Integer> integers = sqlInjectionMethod(invoiceId);
        In

        static List<Integer> sqlInjectionMethod (String invoiceId){
            String sql_injection = """
                    select invoice_id
                    from invoices
                    where invoice_id = %s
                    """.formatted(invoiceId);

            try (Connection connection = ConnectionManager.openConnection()) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql_injection);
                List<Integer> invoiceIds = new ArrayList<>();
                while (resultSet.next()) {
                    int id = resultSet.getInt("invoice_id");
                    invoiceIds.add(id);
                }
                return invoiceIds;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

/*
    }
}
*/