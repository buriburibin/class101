package net.class101.homework1.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.class101.homework1.vo.ProductVO;

@Repository
public interface OrderRepository extends JpaRepository<ProductVO, Integer> {

	public List<ProductVO> findById(String id);

}
