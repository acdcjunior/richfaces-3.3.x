package org.richfaces.demo.datatable;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import org.ajax4jsf.event.PushEventListener;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.datatablescroller.DataTableScrollerBean;

@Name("choicesBean")
@Scope(ScopeType.SESSION)
public class ChoicesBean implements Runnable {
	private List<Choice> choices;
	private List<Choice> lastVotes;
	private Set<Integer> keysSet;
	private Thread thread;
	private boolean enabled = false;
	private Date startDate;
	private String updateInfo;
	PushEventListener listener;

	public ChoicesBean() {
		keysSet = new HashSet<Integer>();
		choices = new ArrayList<Choice>();
		lastVotes = new ArrayList<Choice>();

		choices.add(new Choice("Orange"));
		choices.add(new Choice("Pineapple"));
		choices.add(new Choice("Banana"));
		choices.add(new Choice("Kiwifruit"));
		choices.add(new Choice("Apple"));

		lastVotes.add(new Choice("Orange"));
		lastVotes.add(new Choice("Pineapple"));
		lastVotes.add(new Choice("Banana"));
		lastVotes.add(new Choice("Kiwifruit"));
		lastVotes.add(new Choice("Apple"));
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Set<Integer> getKeysSet() {
		return keysSet;
	}

	public void setKeysSet(Set<Integer> keysSet) {
		this.keysSet = keysSet;
	}

	public void addListener(EventListener listener) {
		if (this.listener != listener) {
			this.listener = (PushEventListener) listener;
		}
	}

	public String getTimeStamp() {
		return new Date().toGMTString();
	}
	
	public synchronized void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setDaemon(true);
			thread.start();
			setStartDate(new Date());
			setEnabled(true);
		}
	}

	public synchronized void stop() {
		if (thread != null) {
			// thread.stop();
			setStartDate(null);
			setEnabled(false);
			thread = null;
		}
	}

	public void run() {
		while (thread != null) {
			try {
				if (((new Date()).getTime() - startDate.getTime()) >= 60000) {
					stop();
				}
				// changing votes count
				for (Choice choice : lastVotes) {
					choice.setVotesCount(DataTableScrollerBean.rand(0, 2));
				}
				listener.onEvent(new EventObject(this));
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void processUpdates() {
		Set<Integer> keysForUpdate = new HashSet<Integer>();
		for (Choice choice : lastVotes) {
			if (choice.getVotesCount() > 0) {
				int index = lastVotes.indexOf(choice);
				keysForUpdate.add(index);
				choices.get(index).increment(choice.getVotesCount());
			}
		}
		updateInfo="[ ";
		for (Choice choice : lastVotes) {
			updateInfo+=choice.getVotesCount()+" ";
		}
		updateInfo+="] ";
		keysSet = keysForUpdate;
	}

	public Thread getThread() {
		return thread;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public List<Choice> getLastVotes() {
		return lastVotes;
	}

}
