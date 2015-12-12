package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.GameRuleDomainModel;
import base.GameRuleDAL;

public class GameRuleBLL 
{
	/**
	 * Return the rules from the DB. 
	 * @return ArrayList<GameRuleDomainModel>
	 */
	public static ArrayList<GameRuleDomainModel> getRules() 
	{	
		return GameRuleDAL.getRules();
	}
	/**
	 * Returns a hashmap with game names as keys and GameRuleDomainModels as the values.
	 * 
	 * Create a new empty hashmap. Get the rules from the DB through the DAL then iterate 
	 * through that ArrayList using a for-each loop. Then add the rule to the hashmap. 
	 * @return HashMap<String, GameRuleDomainModel> 
	 */
	public static HashMap<String, GameRuleDomainModel> getRuleHashSet()
	{
		HashMap<String, GameRuleDomainModel> HashRuleSet = new HashMap();
		
		for (GameRuleDomainModel gr: getRules())
		{
			HashRuleSet.put(gr.getRULENAME(), gr);
		}
		return HashRuleSet;
	}
}
