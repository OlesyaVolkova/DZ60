import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuoteClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in)) ) {

            System.out.println("Подключено к серверу. Получение цитат:");

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);

                if (response.startsWith("Введите имя пользователя:")) {
                    String username = consoleIn.readLine();
                    out.println(username);
                } else if (response.startsWith("Введите пароль:")) {
                    String password = consoleIn.readLine();
                    out.println(password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}