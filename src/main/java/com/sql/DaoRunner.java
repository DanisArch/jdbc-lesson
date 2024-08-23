package com.sql;

import com.sql.dao.InvoiceDao;
import com.sql.entity.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
       InvoiceDao invoiceDao = InvoiceDao.getInstance();
//        List<Invoice> allInvoices = invoiceDao.findAll();
//
//        for (Invoice invoice: allInvoices) {
//            System.out.println(invoice);
//        }


//        Invoice invoice = new Invoice(20,"222-222-22",3, BigDecimal.valueOf(222.22),BigDecimal.valueOf(222.22),
//                LocalDate.of(2024,01,01),LocalDate.of(2024,02,02),LocalDate.of(2024,01,01));
//        invoiceDao.save(invoice);


//        Invoice invoice = new Invoice(2,"333-333-33",5, BigDecimal.valueOf(222.22),BigDecimal.valueOf(222.22),
//                LocalDate.of(2024,01,01),LocalDate.of(2024,02,02),LocalDate.of(2024,01,01));
//        invoiceDao.update(invoice);


//       invoiceDao.deleteById(20);


//        Optional<Invoice> invoice = invoiceDao.findById(3);  // Поиск записи с ID = 1
//
//        invoice.ifPresentOrElse(
//                inv -> System.out.println("Invoice found: " + inv),
//                () -> System.out.println("Invoice not found")
//        );


        invoiceDao.join(2);

    }
}
