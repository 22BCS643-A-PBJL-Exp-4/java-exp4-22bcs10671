import java.util.*;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "[" + value + " of " + symbol + "]";
    }
}

public class CardCollection {
    private static final Map<String, List<Card>> cardMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Card");
            System.out.println("2. Find Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCard(scanner);
                    break;
                case 2:
                    findCardsBySymbol(scanner);
                    break;
                case 3:
                    displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCard(Scanner scanner) {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Value (e.g., Ace, King, 10): ");
        String value = scanner.nextLine();

        Card card = new Card(symbol, value);
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
        System.out.println("Card added successfully.");
    }

    private static void findCardsBySymbol(Scanner scanner) {
        System.out.print("Enter Symbol to search: ");
        String symbol = scanner.nextLine();
        List<Card> cards = cardMap.get(symbol);
        
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards under symbol " + symbol + ": " + cards);
        } else {
            System.out.println("No cards found under this symbol.");
        }
    }

    private static void displayAllCards() {
        if (cardMap.isEmpty()) {
            System.out.println("No cards available.");
        } else {
            System.out.println("\nAll Cards:");
            for (Map.Entry<String, List<Card>> entry : cardMap.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
