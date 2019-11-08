

import java.util.List;
import org.hibernate.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;



public class StockDao {

	private EntityManagerFactory entityManagerFactory;

	public void create(Stock s) {

		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.persist(s);

		em.getTransaction().commit();
	}

	public List<Stock> findAll() {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		List<Stock> stocks = em.createQuery("select s from Stock s", Stock.class).getResultList();

		return stocks;
	}

	public Stock find(String id) {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		
		Stock s = em.find(Stock.class, id);
		
		return s;
	}
	
	public void update(Stock s) {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		
		em.merge(s);
		
		em.getTransaction().commit();
		
	}
	
	public void delete(Stock s) {
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		
		em.remove(s);
		
		em.getTransaction().commit();
	}
	
	public List<Stock> findById(String n) {
		
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		
		List<Stock> stocks = em.createQuery("Select e from stock e"+"where e.id= :id ",Stock.class).setParameter("id", n).getResultList();
		
		return stocks;
			
	}
	
	public void updateById(String id,String name, String des, String price, String quant) {
		
		entityManagerFactory = HibernateOGMUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Stock b = (Stock)findById(id);
		b.setName(name);
		b.setDes(des);
		b.setPrice(price);
		b.setQuant(quant);
		tx.commit();
		

		
	
	}
	
}
