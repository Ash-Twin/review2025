package a000_SpringBasics.service;

import a000_SpringBasics.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

@Service
public class TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;

}
