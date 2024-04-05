import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class QuoteServer {

    private static final String[] quotes = {
            "Успех - это идти от неудачи к неудаче, не теряя энтузиазма. | Уинстон Черчилль",
            "Не считай дни, извлекай из них пользу. | Мухаммед Али",
            "Не ждите. Время никогда не будет подходящим. | Наполеон Хилл",
            "Неисследованная жизнь не стоит того, чтобы ее жить. | Сократ",
            "Усердно работайте, мечтайте по-крупному.",
            "Я не потерпел неудачу. Я просто нашел 10 000 способов, которые не работают. | Томас Эдисон",
            "Мотивация - это то, что заставляет вас начать. Привычка - это то, что заставляет вас продолжать. | Джим Рюн",
            "Вы должны выучить правила игры. А затем вы должны играть лучше, чем кто-либо другой. | Альберт Эйнштейн",
            "Если вы потратите свою жизнь на то, чтобы быть лучшим во всем, вы никогда не станете великим ни в чем. | Том Рат",
            "Сначала они не замечают тебя, затем смеются над тобой, потом борются с тобой, а потом ты побеждаешь. | Махатма Ганди",
            "Мечтатели - это спасители мира. | Джеймс Аллен",

    };

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Сервер запущен. Ожидание подключений...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + clientSocket);

                new Thread(() -> {
                    try {
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                        while (true) {
                            String randomQuote = getRandomQuote();
                            out.println(randomQuote);
                            Thread.sleep(1000); // Задержка перед отправкой следующей цитаты
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            clientSocket.close();
                            System.out.println("Клиент отключился: " + clientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomQuote() {
        Random random = new Random();
        int index = random.nextInt(quotes.length);
        return quotes[index];
    }
}