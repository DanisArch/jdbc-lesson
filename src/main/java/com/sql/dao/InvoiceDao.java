package com.sql.dao;

import com.sql.config.ConnectionManager;
import com.sql.entity.Invoice;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceDao implements Dao<Integer,Invoice>{

    private static final InvoiceDao INSTANCE = new InvoiceDao();

    private static final String FIND_ALL_SQL = """
            SELECT *
            FROM invoices;
            """;


    private static final String SAVE_SQL = """
            INSERT  INTO invoices(INVOICE_ID, NUMBER, CLIENT_ID,INVOICE_TOTAL,PAYMENT_TOTAL, INVOICE_DATE, DUE_DATE, PAYMENT_DATE)
            VALUES(?,?,?,?,?,?,?,?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE invoices
            SET NUMBER=?, CLIENT_ID=?, INVOICE_TOTAL=?,PAYMENT_TOTAL=?,INVOICE_DATE=?,DUE_DATE=?,PAYMENT_DATE=?
            WHERE INVOICE_ID=?;
            """;

    private static final String DELETE_BY_ID_SQL = """
    DELETE FROM invoices 
    WHERE INVOICE_ID =?;
    """;

    private static final String FIND_BY_ID_SQL = """
    SELECT * 
    FROM invoices 
    WHERE INVOICE_ID =?;
    """;

    private static final String JOIN_SQL = """
    SELECT invoice_id,number, c.name, c.client_id
    FROM invoices 
    JOIN  invoicing.clients c on c.client_id = invoices.client_id;
    """;

    private InvoiceDao() {}

    @Override
    public List<Invoice> findAll() {
        try (Connection connection = ConnectionManager.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            Invoice invoice = null;
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                invoice = new Invoice(
                        resultSet.getInt("invoice_id"),
                        resultSet.getString("number"),
                        resultSet.getInt("client_id"),
                        resultSet.getBigDecimal("invoice_total"),
                        resultSet.getBigDecimal("payment_total"),
                        resultSet.getObject("invoice_date", LocalDate.class),
                        resultSet.getObject("due_date", LocalDate.class),
                        resultSet.getObject("payment_date", LocalDate.class)
                );
                invoices.add(invoice);

            }

        return invoices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Invoice entity) {
        try (Connection connection = ConnectionManager.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL);
            preparedStatement.setInt(1,entity.getInvoiceId());
            preparedStatement.setString(2,entity.getNumber());
            preparedStatement.setInt(3,entity.getClientId());
            preparedStatement.setBigDecimal(4,entity.getInvoiceTotal());
            preparedStatement.setBigDecimal(5,entity.getPaymentTotal());
            preparedStatement.setDate(6,Date.valueOf(entity.getInvoiceDate()));
            preparedStatement.setDate(7,Date.valueOf(entity.getDueDate()));
            preparedStatement.setDate(8,Date.valueOf(entity.getPaymentDate()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Invoice entity) {
        try (Connection connection = ConnectionManager.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setInt(8,entity.getInvoiceId());
            preparedStatement.setString(1,entity.getNumber());
            preparedStatement.setInt(2,entity.getClientId());
            preparedStatement.setBigDecimal(3,entity.getInvoiceTotal());
            preparedStatement.setBigDecimal(4,entity.getPaymentTotal());
            preparedStatement.setDate(5,Date.valueOf(entity.getInvoiceDate()));
            preparedStatement.setDate(6,Date.valueOf(entity.getDueDate()));
            preparedStatement.setDate(7,Date.valueOf(entity.getPaymentDate()));
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
          /*  throw new RuntimeException(e);*/
        }

    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = ConnectionManager.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Invoice с ID " + id + " удален успешно.");
            } else {
                System.out.println("Invoice не найден с ID " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Invoice> findById(Integer id) {
        try (Connection connection = ConnectionManager.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("INVOICE_ID"));
                invoice.setNumber(resultSet.getString("NUMBER"));
                invoice.setClientId(resultSet.getInt("CLIENT_ID"));
                invoice.setInvoiceTotal(resultSet.getBigDecimal("INVOICE_TOTAL"));
                invoice.setPaymentTotal(resultSet.getBigDecimal("PAYMENT_TOTAL"));
                invoice.setInvoiceDate(resultSet.getObject("INVOICE_DATE", LocalDate.class));
                invoice.setDueDate(resultSet.getObject("DUE_DATE", LocalDate.class));
                invoice.setPaymentDate(resultSet.getObject("PAYMENT_DATE", LocalDate.class));

//                invoice.setDueDate(resultSet.getDate("DUE_DATE"));
//                invoice.setPaymentDate(resultSet.getDate("PAYMENT_DATE"));

                return Optional.of(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

  //Специфический метод, который реализован только в этом класе
    public void join(int id) {
        try (Connection connection = ConnectionManager.openConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(JOIN_SQL);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String invoiceId = resultSet.getString("invoice_id");
                String number = resultSet.getString("number");
                String name = resultSet.getString("name");
               int clientId = resultSet.getInt("client_id");
                System.out.println(invoiceId+ " " +number+" "+name+" "+clientId );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static InvoiceDao getInstance() {
        return INSTANCE;
    }
}
