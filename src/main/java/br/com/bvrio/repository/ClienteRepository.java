package br.com.bvrio.repository;

import br.com.bvrio.domain.Cliente;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.util.List;


/**
 * Spring Data JPA repository for the Cliente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    @Query(value = "select * from cliente c  where c.email = :param", nativeQuery = true)
    Cliente findByFilter(@Param("param") String param);
}
