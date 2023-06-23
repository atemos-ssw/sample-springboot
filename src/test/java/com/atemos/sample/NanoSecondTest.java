package com.atemos.sample;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class NanoSecondTest {

	@Test
	public void generateNano() {
		Instant end_ts = Instant.now();
		Instant start_ts = end_ts.minusSeconds(60);
        long endNT = end_ts.getEpochSecond() * 1_000_000_000L + end_ts.getNano();
        long startNT = start_ts.getEpochSecond() * 1_000_000_000L + start_ts.getNano();
  
        // Convert nanoseconds to milliseconds
        long endMillis = endNT / 1_000_000L;
        long startMillis = startNT / 1_000_000L;

        // Create date object from the milliseconds
        Date endDt = new Date(endMillis);
        Date startDt = new Date(startMillis);
         
        // Create a formatter
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        // Format the date
        String endTString = formatter.format(endDt);
        String startTString = formatter.format(startDt);
        System.out.println(String.format("endTString(%s) %d, startTString(%s) %d", endTString,endNT, startTString, startNT));
	}
}
