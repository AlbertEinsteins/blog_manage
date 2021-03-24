package com.item.javaee.repository;

import com.item.javaee.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictRepository extends JpaRepository<Dict, Integer> {
}
