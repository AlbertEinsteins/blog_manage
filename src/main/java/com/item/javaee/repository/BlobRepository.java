package com.item.javaee.repository;

import com.item.javaee.entity.Blob;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: EsArticleRepository
 * @Description TODO
 * @Author: jff
 * @Date: 2019-11-04 20:59
 * @Version: 1.0
 **/
public interface BlobRepository extends JpaRepository<Blob, Integer> {

}
