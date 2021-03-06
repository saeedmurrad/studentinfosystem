package com.student.info.sys.repository;

import com.student.info.sys.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.openmbean.OpenDataException;
import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Created by Created by Saeed Murrad on 30/09/19.
 */
@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {
    Optional<Classes> findAllByName(String className);
}