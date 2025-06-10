package com.libraryapp;

import com.libraryapp.model.Book;
import com.libraryapp.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    private static final Scanner sc = new Scanner(System.in);
    private static LibraryService service = new LibraryService();

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("  📚 Welcome to the Library Management Console!  ");
        System.out.println("=============================================");

        while (true) {
            System.out.println("\n=== 📖 Library Menu ===");
            System.out.println("1️⃣ View All Books");
            System.out.println("2️⃣ Add Book");
            System.out.println("3️⃣ Delete Book");
            System.out.println("4️⃣ Borrow Book");
            System.out.println("5️⃣ Return Book");
            System.out.println("6️⃣ Search Book by Title");
            System.out.println("7️⃣ View My Borrowed Book");
            System.out.println("8️⃣ Exit");
            System.out.print("👉 Enter choice (1-8): ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": viewBooks(); break;
                    case "2": addBookMenu(); break;
                    case "3": deleteBookMenu(); break;
                    case "4": borrowBookMenu(); break;
                    case "5": returnBookMenu(); break;
                    case "6": searchBookMenu(); break;
                    case "7": viewMyBorrowedBookMenu(); break;
                    case "8":
                        System.out.println("\n🙏 Thank you for using Library Management App! 👋");
                        System.exit(0);
                    default:
                        System.out.println("⚠ Invalid choice. Please enter a valid option (1-8).");
                }
            } catch (Exception e) {
                System.out.println("❗ An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void viewBooks() {
        List<Book> books = service.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("😔 No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void addBookMenu() {
        try {
            System.out.print("Enter new book ID (number): ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter book title: ");
            String title = sc.nextLine().trim();

            System.out.print("Enter author name: ");
            String author = sc.nextLine().trim();

            String field = selectField();
            if (field == null) return;

            Book book = new Book(id, title, author, true, field);
            boolean added = service.addBook(book);

            System.out.println(added ? "✅ Book added successfully." : "⚠ Book ID already exists.");

        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid input. ID must be a number.");
        }
    }

    private static void deleteBookMenu() {
        try {
            System.out.print("Enter book ID to delete: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean deleted = service.deleteBook(id);
            System.out.println(deleted ? "✅ Book deleted successfully." : "⚠ Cannot delete book (may not exist or is borrowed).");
        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid input. ID must be a number.");
        }
    }

    private static void borrowBookMenu() {
        System.out.print("Enter your admission number (Format: GUYYYYNNNNN): ");
        String admNo = sc.nextLine().trim();

        String field = selectField();
        if (field == null) return;

        System.out.println("\nAvailable books in " + field + ":");
        boolean hasAvailable = false;
        for (Book b : service.getAllBooks()) {
            if (b.getField().equalsIgnoreCase(field) && b.isAvailable()) {
                System.out.println(b);
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("😔 No available books in this field.");
            return;
        }

        try {
            System.out.print("Enter book ID to borrow: ");
            int bookId = Integer.parseInt(sc.nextLine().trim());
            boolean success = service.borrowBook(admNo, field, bookId);
            System.out.println(success ? "✅ Book borrowed successfully." : "⚠ Borrowing failed. Check admission number or book availability.");
        } catch (NumberFormatException e) {
            System.out.println("⚠ Invalid input. Book ID must be a number.");
        }
    }

    private static void returnBookMenu() {
        System.out.print("Enter your admission number (Format: GUYYYYNNNNN): ");
        String admNo = sc.nextLine().trim();

        boolean success = service.returnBook(admNo);
        System.out.println(success ? "✅ Book returned successfully." : "⚠ Return failed. You may not have borrowed any book.");
    }

    private static void searchBookMenu() {
        System.out.print("Enter book title or keyword to search: ");
        String keyword = sc.nextLine().trim();
        List<Book> results = service.searchBooksByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("😔 No books found matching: " + keyword);
        } else {
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    private static void viewMyBorrowedBookMenu() {
        System.out.print("Enter your admission number (Format: GUYYYYNNNNN): ");
        String admNo = sc.nextLine().trim();
        Book b = service.getBorrowedBookByAdmission(admNo);
        if (b != null) {
            System.out.println("📖 You have borrowed: " + b);
        } else {
            System.out.println("⚠ You have not borrowed any book.");
        }
    }

    private static String selectField() {
        System.out.println("\nSelect field/category of the book:");
        System.out.println("1. Forensic");
        System.out.println("2. Law");
        System.out.println("3. Programming");
        System.out.println("4. Maths");
        System.out.println("5. Engineering");
        System.out.println("6. Nursing");
        System.out.println("7. Commerce");
        System.out.print("👉 Enter choice (1-7): ");
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1": return "Forensic";
            case "2": return "Law";
            case "3": return "Programming";
            case "4": return "Maths";
            case "5": return "Engineering";
            case "6": return "Nursing";
            case "7": return "Commerce";
            default:
                System.out.println("⚠ Invalid field choice.");
                return null;
        }
    }
}