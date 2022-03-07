package base.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import base.service.VoucherService;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
	  @Autowired
      private  VoucherService couponService;
  
      
      @GetMapping("code")
      public ResponseEntity<?> applyCoupon(@RequestParam String code){
    	  
    	 return ResponseEntity.ok(couponService.applyVoucher(code));   	  
      }
      
    
}
