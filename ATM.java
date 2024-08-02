import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize some accounts for testing
        accounts.put("12345", new Account("12345", 1000.0));
        accounts.put("67890", new Account("67890", 500.0));

        while (true) {
            System.out.println("Welcome to the ATM");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Deposit Money");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    transferMoney();
                    break;
                case 4:
                    depositMoney();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.println("Current balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();

            if (amount <= account.getBalance()) {
                account.withdraw(amount);
                System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferMoney() {
        System.out.print("Enter your account number: ");
        String fromAccountNumber = scanner.nextLine();
        Account fromAccount = accounts.get(fromAccountNumber);

        if (fromAccount != null) {
            System.out.print("Enter recipient account number: ");
            String toAccountNumber = scanner.nextLine();
            Account toAccount = accounts.get(toAccountNumber);

            if (toAccount != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();

                double transferFee = 0;
                if (!fromAccountNumber.equals(toAccountNumber)) {
                    transferFee = amount * 0.001;
                }

                double totalAmount = amount + transferFee;

                if (totalAmount <= fromAccount.getBalance()) {
                    fromAccount.withdraw(totalAmount);
                    toAccount.deposit(amount);
                    System.out.println("Transfer successful. New balance: $" + fromAccount.getBalance());
                } else {
                    System.out.println("Insufficient funds.");
                }
            } else {
                System.out.println("Recipient account not found.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = accounts.get(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    static class Account {
        private String accountNumber;
        private double balance;

        public Account(String accountNumber, double balance) {
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void withdraw(double amount) {
            if (amount <= balance) {
                balance -= amount;
            }
        }

        public void deposit(double amount) {
            balance += amount;
        }
    }
}
