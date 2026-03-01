package com.airtribe.parkinglot.util;

import java.util.concurrent.atomic.AtomicLong;

public class IDGenerator {
    private static final AtomicLong ticketIdCounter =new AtomicLong(1000);

    public static String generateTicketId()
    {
       return "TICKET-"+ticketIdCounter.incrementAndGet();
    }

}
