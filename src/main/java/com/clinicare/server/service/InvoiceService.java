package com.clinicare.server.service;


import com.clinicare.server.domain.db.Invoice;

import java.util.List;

public interface InvoiceService {
    // Method to retrieve an invoice by ID
    Invoice getInvoiceById(Long invoiceId);

    // Method to create a new invoice
    Invoice createInvoice(Invoice invoice);

    // Method to update an existing invoice
    Invoice updateInvoice(Long invoiceId, Invoice invoiceDetails);

    // Method to delete an invoice
    void deleteInvoice(Long invoiceId);

    Invoice getInvoiceByAppointmentId(long Id);

    List<Invoice> getInvoiceByPatientId(long Id);
}
