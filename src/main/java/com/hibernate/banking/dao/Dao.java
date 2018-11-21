package com.hibernate.banking.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hibernate.banking.beans.Beans;

public class Dao 
{

	public void createCustomer(Beans beans) 
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
        entityManager.persist(beans);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
	}

	public void depositAmount(long accountNumber, float amount) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
        Beans beans = null;
        Long primaryKey = accountNumber;
        try{
        	beans = entityManager.find(Beans.class, primaryKey);
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }        
        beans.setAmount(beans.getAmount()+amount);
        entityManager.merge(beans);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
		
	}

	public Beans find(long accountNumber) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
         Beans result = null;
        entityManager.getTransaction().begin();
        Long primaryKey = accountNumber;
        try{
        	result = entityManager.find(Beans.class, primaryKey);
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
        return result;
	}

	public void withdrawAmount(long accountNumber, float amount) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
        Beans beans = null;
        Long primaryKey = accountNumber;
        try{
        	beans = entityManager.find(Beans.class, primaryKey);
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }        
        beans.setAmount(beans.getAmount()-amount);
        entityManager.merge(beans);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
		
	}

	public void deleteAccount(long accountNumber) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
         Beans beans = null;
        entityManager.getTransaction().begin();
        Long primaryKey = accountNumber;
        try{
        	beans = entityManager.find(Beans.class, primaryKey);
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        entityManager.remove(beans);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
		
	}

	public void transferAmount(Long account1, Long account2, float amount)
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
        Beans beans = null;
        Beans beans2 = null;
        Long primaryKey = account1;
        try {
        beans = entityManager.find(Beans.class, primaryKey); 
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        }
        beans.setAmount(beans.getAmount()-amount);
        entityManager.merge(beans);
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Long primaryKey1 = account2;
        try {
        beans2 = entityManager.find(Beans.class, primaryKey1); 
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
        beans2.setAmount(beans2.getAmount()+amount);
        entityManager.merge(beans2);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
		
	}

	public Beans findAadhar(long aadharCard) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testing");
        EntityManager entityManager = factory.createEntityManager();
        Beans result = null;
        String sql = "SELECT u from Beans u where u.aadharCard = :aadharCard";
        Query query = entityManager.createQuery(sql);
        query.setParameter("aadharCard", aadharCard);
        try {
        result = (Beans) query.getSingleResult();
        }  
        catch(Exception e) {
        	System.out.println(e.getMessage());
        }
                
        entityManager.close();
        factory.close();
        return result;
	}

}
