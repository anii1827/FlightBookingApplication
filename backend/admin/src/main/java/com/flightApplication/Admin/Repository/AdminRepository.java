package com.flightApplication.Admin.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.flightApplication.Admin.Model.Flight;

@Repository
public interface AdminRepository extends CrudRepository<Flight, Long>{

}
