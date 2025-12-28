package com.kul.pack1;

import java.util.Random;

public class AccountNumberGenerator {

    public static synchronized String generateAccountNo() {
    	 Random random = new Random();

     
         String  accountNumber = 1000000000L + (long)(random.nextDouble() * 9000000000L)+"";
         return accountNumber + "SBI";        // append SBI
    }
}
