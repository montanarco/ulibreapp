package model;

public class Value {
	private Long id;
    private String quote;

    public Value() {
    }
    
    public Value(Long id, String quote) {
		super();
		this.id = id;
		this.quote = quote;
	}



	public Long getId() {
        return this.id;
    }

    public String getQuote() {
        return this.quote;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }

}
