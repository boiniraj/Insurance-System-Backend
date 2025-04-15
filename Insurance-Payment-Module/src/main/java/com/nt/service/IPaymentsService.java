package com.nt.service;

import com.nt.entity.PaymentsEntity;
import com.nt.model.PaymentInputs;

public interface IPaymentsService {
	
	public PaymentsEntity registerPayment(PaymentInputs pinput);
	public PaymentsEntity showDetals(String  transactionId);
	

}
