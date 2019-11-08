

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateOGMUtil {

	private static EntityManagerFactory entityManagerFactory = null;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("myPu");
		} catch (Exception e) {
			System.err.println("Initial EntityManagerFactory creation failed." );
			
			e.printStackTrace();
		}
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void closeEntityManagerFactory() {
		entityManagerFactory.close();
	}

}