package ua.nure.cs.savenkov.usermanagement.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import ua.nure.cs.savenkov.usermanagement.db.DaoFactory;
import ua.nure.cs.savenkov.usermanagement.db.DataBaseException;

public class SearchAgent extends Agent {
	protected void setup() {
		super.setup();
		System.out.println(getAID().getName() + " started");
	}
	
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		super.takeDown();
	}
	
	public void search(String firstName, String lastName) throws SearchException{
		try {
			Collection users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			if (users.size() > 0) {
				showUsers(users);
			} else {
				addBehaviour(new SearchRequestBehaviour(new AID[] {}, firstName, lastName));
			}
			
		} catch (DataBaseException e ) {
			throw new SearchException(e);
		}
	}
	
	void showUsers(Collection user) {
		// TODO Отобразить найденных пользователей
	}
}
