package guisimple;

import java.io.*;

public class AuthManager {

    private static final String FILE_PATH = "accounts.txt";

    // ================= REGISTER =================
    public static boolean register(String id, String username, String password) {
        try {
            File file = new File(FILE_PATH);

            // Create file if not exists
            if (!file.exists()) {
                file.createNewFile();
            }

            // CHECK IF ID ALREADY EXISTS
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 3) {
                    if (data[0].equals(id)) {
                        reader.close();
                        return false; // ID already exists
                    }
                }
            }
            reader.close();

            // SAVE NEW ACCOUNT
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(id + "," + username + "," + encrypt(password));
            writer.newLine();
            writer.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= LOGIN =================
    public static boolean login(String id, String password) {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                return false;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length >= 3) {
                    String savedID = data[0];
                    String savedPassword = decrypt(data[2]);

                    if (savedID.equals(id) && savedPassword.equals(password)) {
                        reader.close();
                        return true; // login success
                    }
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================= ENCRYPT =================
    public static String encrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append((char) (c + 3)); // simple shift
        }

        return result.toString();
    }

    // ================= DECRYPT =================
    public static String decrypt(String text) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append((char) (c - 3));
        }

        return result.toString();
    }
}