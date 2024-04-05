import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class QuoteClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8000);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Подключено к серверу. Получение цитат:");

            while (true) {
                String quote = in.readLine();
                System.out.println("Цитата: " + quote);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}