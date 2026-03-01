package com.example.parkinglot.repository;

import com.example.parkinglot.entity.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction,Long> {
   public Optional<ParkingTransaction> findByTicketNumber(String ticketId);
}
