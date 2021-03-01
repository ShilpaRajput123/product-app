package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Customer;

@Repository
@Transactional
public class CustomerDAOJpaImpl implements CustomerDAO {
    
	@Autowired
	EntityManager em;
	@Override
	public Customer save(Customer toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Customer findById(int id) {
		
		return em.find(Customer.class, id);
	}

	@Override
	public List<Customer> findAll() {
		Query q=em.createQuery("Select c from Customer as c");
		List<Customer> all=q.getResultList();
		return all;
	}

	@Override
	public List<Customer> findByFirstName(String firstName) {
		Query q=em.createQuery("Select c from Customer c where c.firstName=:fnParam");
		q.setParameter("fnParam", firstName);
		return q.getResultList();
	}

	@Override
	public void deleteById(int id) {
		
		Customer c= em.find(Customer.class,id);
		em.remove(c);
		
	}
	
}
