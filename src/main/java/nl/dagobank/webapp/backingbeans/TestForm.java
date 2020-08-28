package nl.dagobank.webapp.backingbeans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TestForm {

    private int id;
    private String naam;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public TestForm() {
        super();
    }

    public TestForm( int id, String naam, Date date ) {
        super();
        this.id = id;
        this.naam = naam;
        this.date = date;
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
