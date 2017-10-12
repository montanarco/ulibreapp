package model;


public class jsonEnvio {
	
	private String to;
	private Notification notification;
	
	public jsonEnvio(String to, Notification notification) {
		this.to = to;
		this.notification = notification;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	
}	

	
	