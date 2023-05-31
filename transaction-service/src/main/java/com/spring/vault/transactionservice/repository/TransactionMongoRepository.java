package com.spring.vault.transactionservice.repository;

import com.spring.vault.transactionservice.entity.Payment;
import com.spring.vault.transactionservice.entity.TransactionMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionMongoRepository extends MongoRepository<TransactionMongo, String> {
    TransactionMongo save(TransactionMongo document);
    @Query("{'transaction.customerId': ?0, 'transaction.accountName': ?1}")
    List<TransactionMongo> findByCustomerIdAndAccountName(Long customerId, String accountName);

    @Query("{'payment.sourceId': ?0}")
    List<TransactionMongo> getPaymentsBySourceId(long id);

}
