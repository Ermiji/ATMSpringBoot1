package me.ermias.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

        List<Transaction> findByActNo(long actNo);

}
