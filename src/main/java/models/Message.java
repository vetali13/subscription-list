package models;

public class Message {
	
	private Integer id;
	private String content;
	
	public Message() {
		super();
	}

	public Message(String content) {
		super();
		this.content = content;
	}

	public Message(Integer id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Message [content=" + content + "]";
	}
	
	
	
}