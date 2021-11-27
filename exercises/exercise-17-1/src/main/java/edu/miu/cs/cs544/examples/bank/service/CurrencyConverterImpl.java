package edu.miu.cs.cs544.examples.bank.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterImpl implements CurrencyConverter{
    public double euroToDollars (double amount){
		System.out.println("CurrencyConverter: converting "+amount+" dollars to euros");
        return 1.57 * amount;
    }
    
    public double dollarsToEuros (double amount){
        return 0.637 * amount;
    }
}
