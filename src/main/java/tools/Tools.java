package tools;

import java.security.SecureRandom;

public class Tools {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final String PASSWORD_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
    private static SecureRandom random = new SecureRandom();
    private static final String[] NAMES = {
            "Алексей", "Анна", "Борис", "Виктория", "Григорий", "Дмитрий", "Екатерина", "Иван", "Мария", "Николай"
    };;

    public static String generateRandomEmail(String domain, int length) {
        StringBuilder email = new StringBuilder(length + domain.length() + 1);
        for (int i = 0; i < length; i++) {
            email.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        email.append("@").append(domain);
        return email.toString();
    }

    public static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(PASSWORD_CHARACTERS.charAt(random.nextInt(PASSWORD_CHARACTERS.length())));
        }
        return password.toString();
    }

    public static String generateRandomName() {
        return NAMES[random.nextInt(NAMES.length)];
    }
}
