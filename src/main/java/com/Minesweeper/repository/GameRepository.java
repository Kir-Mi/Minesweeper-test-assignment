package com.Minesweeper.repository;

import com.Minesweeper.dto.FieldDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<FieldDto, String> {
}
