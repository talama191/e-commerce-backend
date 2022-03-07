package base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import base.exception.ResourceNotFoundException;
import base.model.entity.Voucher;
import base.repository.VoucherRepository;
@Service
public class  VoucherService {
	@Autowired
	private VoucherRepository voucherRepo;
	
	public Optional<Voucher> applyVoucher(String code) {
	    Optional<Voucher> voucher = voucherRepo.findByCode(code);
	    	return voucher;

	}
	
	public List<Voucher> findAll(){
		return voucherRepo.findAll();
	}
	
	
	public Voucher addCoupon(Voucher voucher) {
		return voucherRepo.save(voucher);
	}
}
