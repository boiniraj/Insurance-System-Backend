package com.nt.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.nt.entity.CitizenAppRegistrationEntity;
import com.nt.entity.DcCaseEntity;
import com.nt.entity.PaymentsEntity;
import com.nt.model.PaymentInputs;
import com.nt.repositoy.ICaseRepository;
import com.nt.repositoy.ICitizenRegistrationRepositoty;
import com.nt.repositoy.IPaymentRepository;
import com.nt.utils.EmailUtils;

@Service
public class PaymentService implements IPaymentsService {
    @Autowired
    private IPaymentRepository prepo;
    
    @Autowired
    private ICaseRepository caseRepo;
    
    @Autowired
    private ICitizenRegistrationRepositoty citizenRepo;
    
    @Autowired
    private EmailUtils emailUtils;

    @Override
    @Cacheable(value = "paymentsByCaseNo", key = "#pinput.caseNo")
    public PaymentsEntity registerPayment(PaymentInputs pinput) {
        Optional<PaymentsEntity> optPayment = prepo.findByCaseNo(pinput.getCaseNo());
        
        if (optPayment.isPresent()) {
            // Return existing payment details if already recorded
            return optPayment.get();
        }
        
        PaymentsEntity pentity = new PaymentsEntity();
        BeanUtils.copyProperties(pinput, pentity);
        pentity.setDate(LocalDate.now());
        pentity.setTime(LocalTime.now());
        prepo.save(pentity);
        
        Optional<DcCaseEntity> optCase = caseRepo.findById(pentity.getCaseNo());
        Integer appId = optCase.map(DcCaseEntity::getAppId).orElse(0);
        
        Optional<CitizenAppRegistrationEntity> optCitizen = citizenRepo.findById(appId);
        
        if (optCitizen.isPresent()) {
            CitizenAppRegistrationEntity citizen = optCitizen.get();
            
            String subject = "Transaction ID";
            String body = "Dear " + citizen.getFullName() + ",<br><br>" +
                "Your account <b>" + pentity.getAccNo() + "</b> has been debited with <b>₹" + pentity.getBeneficiaryAmt() +
                "</b> on <b>" + pentity.getDate() + "</b> at <b>" + pentity.getTime() + "</b>.<br><br>" +
                "Transaction ID: <b>" + pentity.getTransactionId() + "</b>.<br><br>" +
                "Thank you for using our services.<br>Best Regards,<br>Your Bank Team";
            
            try {
                emailUtils.sendEmailMessage(citizen.getEmail(), subject, body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return pentity;
    }

	@Override
	@Cacheable(value = "paymentsByTransactionId", key = "#transactionId")
	public PaymentsEntity showDetals(String transactionId) {
		  Optional<PaymentsEntity> ent= prepo.findById(transactionId);
		  PaymentsEntity ent1=null;
		  if(ent.isPresent())
		  {
			  ent1= ent.get();
		  }
		return ent1;
	}
}
