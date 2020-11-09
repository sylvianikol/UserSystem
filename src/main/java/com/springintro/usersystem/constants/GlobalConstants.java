package com.springintro.usersystem.constants;

public class GlobalConstants {

    public static final String MAIN_MENU =
            "%n|--------------------------|%n" +
                    "|         MAIN MENU        |%n" +
                    "| Options:                 |%n" +
                    "|        1. Login          |%n" +
                    "|        2. Register       |%n" +
                    "|        3. Exit           |%n";

    public static final String USER_MENU =
            "%n|--------------------------|%n" +
            "|         USER MENU        |%n" +
            "| Options:                 |%n" +
            "|        1. Edit Profile   |%n" +
            "|        2. Create Album   |%n" +
            "|        3. Upload Photo   |%n" +
            "|        3. Exit           |%n";

    public static final String PROFILE_MENU =
            "%n|--------------------------|%n" +
                    "|       USER PROFILE       |%n" +
                    "| Options:                 |%n" +
                    "|        1. Edit Age       |%n" +
                    "|        2. Edit Email     |%n" +
                    "|        3. Edit Name      |%n" +
                    "|        4. Edit Username  |%n" +
                    "|        5. Edit Password  |%n" +
                    "|        6. Edit Address   |%n" +
                    "|        7. << Back        |%n";

    public static final String NAME_REGEX =
            "[A-Za-z]{1}[A-Za-z\\s\\.\\-]+";

    public static final String COUNTRY_REGEX =
            "[A-Za-z]{1}[A-Za-z\\s\\.\\-,()&]+";

    public static final String EMAIL_REGEX =
            "[a-z0-9][a-z0-9\\.\\-_]+[a-z0-9]@[a-z0-9][a-z0-9]+(\\.*[a-z]+[a-z])+";
}
