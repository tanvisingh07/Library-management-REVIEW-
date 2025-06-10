package com.libraryapp.service;

import com.libraryapp.model.Book;
import com.libraryapp.util.FileUtil;

import java.util.*;
import java.util.regex.Pattern;

public class LibraryService {

    private List<Book> books;
    private Map<String, Integer> borrowedMap;

    public LibraryService() {
        this.books = FileUtil.loadBooks();
        this.borrowedMap = new HashMap<>();
        for (Book b : books) {
            if (!b.isAvailable() && b.getBorrowedBy() != null) {
                borrowedMap.put(b.getBorrowedBy(), b.getId());
            }
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public boolean addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            return false;
        }
        books.add(book);
        FileUtil.saveBooks(books);
        return true;
    }

    public boolean deleteBook(int id) {
        Book book = findBookById(id);
        if (book == null || !book.isAvailable()) {
            return false;
        }
        books.remove(book);
        FileUtil.saveBooks(books);
        return true;
    }

    public Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    public boolean borrowBook(String admNo, String field, int bookId) {
        if (!Pattern.matches("GU\\d{4}\\d{5}", admNo)) {
            return false;
        }
        if (borrowedMap.containsKey(admNo)) {
            return false;
        }
        Book book = findBookById(bookId);
        if (book == null || !book.getField().equalsIgnoreCase(field) || !book.isAvailable()) {
            return false;
        }
        book.setAvailable(false);
        book.setBorrowedBy(admNo);
        borrowedMap.put(admNo, bookId);
        FileUtil.saveBooks(books);
        return true;
    }

    public boolean returnBook(String admNo) {
        if (!borrowedMap.containsKey(admNo)) {
            return false;
        }
        int bookId = borrowedMap.get(admNo);
        Book book = findBookById(bookId);
        if (book == null) return false;

        book.setAvailable(true);
        book.setBorrowedBy(null);
        borrowedMap.remove(admNo);
        FileUtil.saveBooks(books);
        return true;
    }

    public List<Book> searchBooksByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        String key = keyword.toLowerCase();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(key)) {
                results.add(b);
            }
        }
        return results;
    }

    public Book getBorrowedBookByAdmission(String admNo) {
        Integer bookId = borrowedMap.get(admNo);
        if (bookId == null) return null;
        return findBookById(bookId);
    }
}