package select_equals_like;

public class Actor {
	private Integer actorId;
	private String firstName;
	private String lastName;
	private String lastUpdate;
	
	public Actor() {
	}
	
	public Actor(Integer actorId, String firstName, String lastName, String lastUpdate) {
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = lastUpdate;
	}

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@Override
	public String toString() {
		return "Actor[" + actorId + " , " + firstName + " , " + lastName + " , " + lastUpdate + "]" ;
	}
}
