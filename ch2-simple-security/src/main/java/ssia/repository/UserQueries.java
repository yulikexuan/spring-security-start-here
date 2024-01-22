//: ssia.repository.UserQueries.java


package ssia.repository;


public final class UserQueries {

    public static final String SQL_FIND_USER_BY_USERNAME =
            "SELECT username, password, enabled FROM users WHERE username = ?";

    public static final String SQL_FIND_AUTHORITY_BY_USERNAME =
            "SELECT username, authority FROM authorities WHERE username = ?";

    private UserQueries() {}

}///:~