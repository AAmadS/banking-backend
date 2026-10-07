package com.example.banking_backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking_backend.dto.CreateTransferRequest;
import com.example.banking_backend.dto.TransferResponse;
import com.example.banking_backend.model.Transfer;
import com.example.banking_backend.service.TransferService;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public TransferResponse createTransfer(@RequestBody CreateTransferRequest request){

        Transfer transfer = transferService.createTransfer(request);
        
        return new TransferResponse(transfer);
    }
}
