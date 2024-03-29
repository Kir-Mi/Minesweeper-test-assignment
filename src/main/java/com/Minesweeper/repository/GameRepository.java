package com.Minesweeper.repository;

import com.Minesweeper.model.FieldModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<FieldModel, String> {
}
