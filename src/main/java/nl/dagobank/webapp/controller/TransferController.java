package nl.dagobank.webapp.controller;

import nl.dagobank.webapp.backingbeans.TransferForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TransferController {

    public TransferController() {super();}


    @GetMapping("/Transfer")
    public String transactionHandler(){
        return "Transfer";
    }

    @PostMapping("/ExecuteTransfer")
    public String executeTransferHandler(@ModelAttribute TransferForm transferForm) {
        return "ExecuteTransfer";
    }

}