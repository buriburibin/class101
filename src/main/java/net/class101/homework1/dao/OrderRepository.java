package net.class101.homework1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.class101.homework1.vo.ProductVO;

@Repository
public interface OrderRepository extends JpaRepository<ProductVO, Integer> {

}
