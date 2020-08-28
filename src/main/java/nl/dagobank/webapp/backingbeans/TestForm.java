package nl.dagobank.webapp.backingbeans;

import java.util.Date;

public class TestForm {

    private int id;
    private String naam;
    private Date date;

    public TestForm() {
        super();
    }

    public TestForm( int id, String naam, Date date ) {
        super();
        this.id = id;
        this.naam = naam;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam( String naam ) {
        this.naam = naam;
    }

    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }
}
