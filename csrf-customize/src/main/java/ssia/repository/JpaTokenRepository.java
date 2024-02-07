//: ssia.repository.JpaTokenRepository.java


package ssia.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssia.domain.entity.Token;

import java.util.Optional;


@Repository
public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findTokenByIdentifier(String identifier);

}///:~