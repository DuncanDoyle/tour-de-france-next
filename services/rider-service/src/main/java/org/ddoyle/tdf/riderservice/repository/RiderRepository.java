package org.ddoyle.tdf.riderservice.repository;

import java.util.Collection;

import org.ddoyle.tdf.riderservice.model.Rider;

public interface RiderRepository {
	
	Rider get(int id);
	
	Rider save(Rider rider);
	
	Collection<Rider> getAll();
	
	
}
