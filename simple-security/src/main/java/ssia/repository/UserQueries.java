//: ssia.repository.UserQueries.java


package ssia.repository;


public final class UserQueries {

    public static final String SQL_FIND_USER_BY_USERNAME =
            "SELECT name, pw, active FROM users WHERE name = ?";

    public static final String SQL_FIND_AUTHORITY_BY_USERNAME =
            "SELECT name, auth FROM authorities WHERE name = ?";

    private UserQueries() {}

}///:~