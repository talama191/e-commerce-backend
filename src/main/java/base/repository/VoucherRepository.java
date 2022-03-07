package base.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import base.model.entity.User;
import base.model.entity.Voucher;


public interface VoucherRepository extends JpaRepository<Voucher, Integer>{
	Optional<Voucher> findByCode(String code);
       
}
