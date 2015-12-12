package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.GameRuleDomainModel;
import util.HibernateUtil;

public class GameRuleDAL {

	public static ArrayList<GameRuleDomainModel> getRules() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ArrayList<GameRuleDomainModel> Rules = new ArrayList<GameRuleDomainModel>();

		try {
			tx = session.beginTransaction();
			/*
			 * This returns all the entries in the GameRule DB. There's no order. 
			 */
			List rules = session.createQuery("FROM GameRuleDomainModel").list();
			/*
			 * Iterates through, casts each entry to the GameRuleDomainModel and then 
			 * adds the instance to the arraylist to return. 
			 */
			for (Iterator iterator = rules.iterator(); iterator.hasNext();) {
				GameRuleDomainModel rle = (GameRuleDomainModel) iterator.next();
				Rules.add(rle);

			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return Rules;

	}

	
}
