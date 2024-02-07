//: ssia.domain.entity.CsrfToken.java


package ssia.domain.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "csrf_token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // In real product app, use session id to replace int

    private String identifier;

    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}///:~