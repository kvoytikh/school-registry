package com.test.repository.school;

import com.test.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {
  Page<School> findAll(Pageable pageable);

  Page<School> findAll(Specification<School> spec, Pageable pageable);
}
