package com.student.info.sys.repository;

import com.student.info.sys.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Created by Saeed Murrad on 30/09/19.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
