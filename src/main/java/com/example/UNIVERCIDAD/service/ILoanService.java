package com.example.UNIVERCIDAD.service;

import com.example.UNIVERCIDAD.common.LoanDTO;

import java.util.List;

public interface ILoanService {
    LoanDTO findLastLoanByIdentification(String identification);

    List<LoanDTO> findLoansByTodayDate();

    List<LoanDTO> findAll();

    LoanDTO createLoan(LoanDTO loanDTO);

    List<LoanDTO> getAvailableLoans();

    LoanDTO cancelLoan(long id);

}
