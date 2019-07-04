package ru.parhomych.spring01.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.parhomych.spring01.dao.KnockToBDRepository;
import ru.parhomych.spring01.model.Transaction;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionsRestController {
    public static final Logger logger = LoggerFactory.getLogger(TransactionsRestController.class);

    @Autowired
    KnockToBDRepository knockToBDRepository;


    //------------- All users --------------------
    @RequestMapping(value = "/all/", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> listAllTransactions() {
        List<Transaction> transactions = knockToBDRepository.findAllTransactions();
        if(transactions.isEmpty()){
            logger.info("Base is empty");
            return new ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT);
        }
        logger.info("Base is loaded");
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    //------------- Users by ID --------------------
    @RequestMapping(value = "/transactions/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> listTransactionsById() {
        return null;
    }



}
