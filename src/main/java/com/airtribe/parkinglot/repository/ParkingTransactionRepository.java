package com.airtribe.parkinglot.repository;

import com.airtribe.parkinglot.entity.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction,Long> {
   public Optional<ParkingTransaction> findByTicketId(String ticketId);
    boolean existsByVehicle_LicensePlateAndExitTimeIsNull(String licensePlate);


}
