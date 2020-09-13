package nl.dagobank.webapp.domain;
import javax.persistence.*;


@Entity
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Embedded
    private UserFullName userFullName;
    @Embedded
    private UserAddress userAddress;
    @Embedded
    private UserContactDetails userContactDetails;
    @Embedded
/*    @Column(unique = true, nullable = false)*/
    private UserPersonalDetails userPersonalDetails;
    @Embedded
    private UserInlogCredentials userInlogCredentials;

    public User(){}

    public User(UserFullName userFullName, UserAddress userAddress, UserContactDetails userContactDetails, UserPersonalDetails userPersonalDetails, UserInlogCredentials userInlogCredentials) {
        this.userFullName = userFullName;
        this.userAddress = userAddress;
        this.userContactDetails = userContactDetails;
        this.userPersonalDetails = userPersonalDetails;
        this.userInlogCredentials = userInlogCredentials;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserFullName getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(UserFullName userFullName) {
        this.userFullName = userFullName;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public UserContactDetails getUserContactDetails() {
        return userContactDetails;
    }

    public void setUserContactDetails(UserContactDetails userContactDetails) {
        this.userContactDetails = userContactDetails;
    }

    public UserPersonalDetails getUserPersonalDetails() {
        return userPersonalDetails;
    }

    public void setUserPersonalDetails(UserPersonalDetails userPersonalDetails) {
        this.userPersonalDetails = userPersonalDetails;
    }

    public UserInlogCredentials getUserInlogCredentials() {
        return userInlogCredentials;
    }

    public void setUserInlogCredentials(UserInlogCredentials userInlogCredentials) {
        this.userInlogCredentials = userInlogCredentials;
    }
}
