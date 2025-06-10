package com.libraryapp.util;

import com.libraryapp.model.Book;

import java.io.*;
import java.util.*;

public class FileUtil {

    private static final String FILE_NAME = "src/main/resources/books.txt";

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            // Could create sample books here or return empty list
            return books;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length < 6) continue;

                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                boolean isAvailable = Boolean.parseBoolean(parts[3].trim());
                String field = parts[4].trim();
                String borrowedBy = parts[5].isEmpty() ? null : parts[5].trim();

                Book b = new Book(id, title, author, isAvailable, field);
                b.setBorrowedBy(borrowedBy);
                books.add(b);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return books;
    }

    public static void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book b : books) {
                bw.write(b.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}