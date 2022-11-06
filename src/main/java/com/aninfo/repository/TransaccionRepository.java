package com.aninfo.repository;

import com.aninfo.model.Transaccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {
    Collection<Transaccion> findAllByCbu(Long cbu);

}
