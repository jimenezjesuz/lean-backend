package com.example.UNIVERCIDAD.controller;

import com.example.UNIVERCIDAD.common.LoanDTO;
import com.example.UNIVERCIDAD.service.ILoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final ILoanService iLoanService;

    public LoanController(ILoanService iLoanService) {
        this.iLoanService = iLoanService;
    }

    @GetMapping("/identification/{identification}")
    public ResponseEntity<?> findLastLoanByIdentification(@PathVariable("identification") String identification) {
        return ResponseEntity.ok().body(iLoanService.findLastLoanByIdentification(identification));
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok().body(iLoanService.findAll());
    }

    @GetMapping("/availableLoans")
    public ResponseEntity<?> getAvailableLoans() {
        return ResponseEntity.ok().body(iLoanService.getAvailableLoans());
    }

    @GetMapping("/today")
    public ResponseEntity<?> findLoansByTodayDate() {
        return ResponseEntity.ok().body(iLoanService.findLoansByTodayDate());
    }

    @PostMapping()
    public ResponseEntity<?> createLoan(@RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok().body(iLoanService.createLoan(loanDTO));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<?> cancelLoan(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(iLoanService.cancelLoan(id));
    }
}