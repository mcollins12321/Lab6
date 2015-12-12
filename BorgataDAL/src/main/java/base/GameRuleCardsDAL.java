package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.GameRuleCardsDomainModel;
import util.HibernateUtil;

public class GameRuleCardsDAL   {

	public static ArrayList<GameRuleCardsDomainModel> getCardsRules() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ArrayList<GameRuleCardsDomainModel> RuleCards = new ArrayList<GameRuleCardsDomainModel>();

		try 
		{
			tx = session.beginTransaction();
			
			/* 
			 * Create a query. GameRuleCardsDomainModel specifies the table to use as well as the columns to 
			 * include in results. Then the results are ordered by RuleID and within each of those sets by 
			 * the Pickorder. 
			*/
			List rules = session.createQuery("FROM GameRuleCardsDomainModel ORDER BY RULEID, PICKORDER").list();
			
			/* 
			 * Creates an iterator that goes through the list from the database. Each DB entry exists as an 
			 * element in the list. One by one they are cast to a GameRuleCardsDomain model and then 
			 * added to the array that will be returned. s
			 */
			for (Iterator iterator = rules.iterator(); iterator.hasNext();) 
			{
				GameRuleCardsDomainModel rle = (GameRuleCardsDomainModel) iterator.next();
				RuleCards.add(rle);
			}

			tx.commit();
		} 
		catch (HibernateException e) 
		{
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		finally 
		{
			session.close();
		}
		return RuleCards;

	}
	
}
