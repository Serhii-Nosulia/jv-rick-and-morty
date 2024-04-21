package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;

import mate.academy.rickandmorty.model.Character;
import org.hibernate.annotations.SQLSelect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM characters ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Character>  getRandomCharacter();
}
