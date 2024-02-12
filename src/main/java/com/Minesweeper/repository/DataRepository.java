package com.Minesweeper.repository;

import com.Minesweeper.model.FieldData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<FieldData, String> {
}
