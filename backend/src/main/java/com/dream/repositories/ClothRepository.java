package com.dream.repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.models.Cloth;

public interface ClothRepository extends JpaRepository<Cloth, Long> {

	Page<Cloth> findByClothTypeIdAndColorIdAndRegisteredDateBetween(long clothTypeId, long colorId, Date time,
			Date time2, Pageable pageable);

	Page<Cloth> findByClothTypeIdAndRegisteredDateBetween(long clothTypeId, Date time, Date time2, Pageable pageable);

	Page<Cloth> findByColorIdAndRegisteredDateBetween(long colorId, Date time, Date time2, Pageable pageable);

	Page<Cloth> findByRegisteredDateBetween(Date time, Date time2, Pageable pageable);

	Page<Cloth> findByClothTypeIdAndColorId(long clothTypeId, long colorId, Pageable pageable);

	Page<Cloth> findByClothTypeId(long clothTypeId, Pageable pageable);

	Page<Cloth> findByColorId(long colorId, Pageable pageable);


}
