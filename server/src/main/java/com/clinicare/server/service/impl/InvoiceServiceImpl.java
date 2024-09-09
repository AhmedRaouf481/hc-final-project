package com.clinicare.server.service.impl;



import com.clinicare.server.domain.db.Invoice;
import com.clinicare.server.exception.ResourceNotFoundException;
import com.clinicare.server.exception.ValidationException;

import com.clinicare.server.repository.InvoiceRepository;
import com.clinicare.server.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice getInvoiceById(Long invoiceId) {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if (invoice.isEmpty()) {
            throw new ResourceNotFoundException("Invoice");
        }
        return invoice.get();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        validateInvoice(invoice);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Long invoiceId, Invoice invoiceDetails) {
        Invoice existingInvoice = getInvoiceById(invoiceId);

        if (invoiceDetails.getAmount() <= 0) {
            throw new ValidationException("Amount must be greater than zero.");
        }

        existingInvoice.setAmount(invoiceDetails.getAmount());
        existingInvoice.setStatus(invoiceDetails.getStatus());
        return invoiceRepository.save(existingInvoice);
    }

    @Override
    public void deleteInvoice(Long invoiceId) {
        Invoice invoice = getInvoiceById(invoiceId);  // Ensure the invoice exists before deleting
        invoiceRepository.delete(invoice);
    }

    private void validateInvoice(Invoice invoice) {
        if (invoice.getAmount() <= 0) {
            throw new ValidationException("Invoice amount must be greater than zero.");
        }
        if (invoice.getStatus() == null || invoice.getStatus().isEmpty()) {
            throw new ValidationException("Invoice status cannot be empty.");
        }


    }
}
