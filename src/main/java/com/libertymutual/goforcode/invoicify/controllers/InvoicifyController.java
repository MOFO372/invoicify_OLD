package com.libertymutual.goforcode.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoices")
public class InvoicifyController {
	
	@GetMapping("/") 
	public String invoiceRecords() {
		return "invoice/list";
	}

}
