package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaccion;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransaccionRepository transaccionRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long cbu) {
        return accountRepository.findById(cbu);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public void deleteById(Long cbu) {
        accountRepository.deleteById(cbu);
    }

    @Transactional
    public Account withdraw(Long cbu, Transaccion transaccion) {
        Account account = accountRepository.findAccountByCbu(cbu);

        if (account.getBalance() < transaccion.getMonto()) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        //Transaccion transaccion = new Transaccion(sum);
        account.setBalance(account.getBalance() - transaccion.getMonto());
        accountRepository.save(account);
        transaccion.setCbu(account.getCbu());
        transaccion.setTipo("Extracción");
        transaccionRepository.save(transaccion);

        return account;
    }

    @Transactional
    public Account deposit(Long cbu, Transaccion transaccion) {

        if (transaccion.getMonto() <= 0) {
            throw new DepositNegativeSumException("Cannot deposit negative sums");
        }

        Account account = accountRepository.findAccountByCbu(cbu);
        account.setBalance(account.getBalance() + transaccion.getMonto());
        accountRepository.save(account);
        transaccion.setCbu(account.getCbu());
        transaccion.setTipo("Depósito");
        transaccionRepository.save(transaccion);

        return account;
    }

    public Optional<Transaccion> EncontrarById(Long id) {
        return transaccionRepository.findById(id);
    }

    public Collection<Transaccion> EncontrarByCbu(Long cbu) {
        return transaccionRepository.findAllByCbu(cbu);
    }

}
