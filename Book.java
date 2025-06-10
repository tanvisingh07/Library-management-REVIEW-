package com.libraryapp.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;
    private String field;
    private String borrowedBy;

    public Book(int id, String title, String author, boolean isAvailable, String field) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = isAvailable;
        this.field = field;
        this.borrowedBy = null;
    }

    // Getters and setters

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; } // Added getter
    public boolean isAvailable() { return isAvailable; }
    public String getField() { return field; }
    public String getBorrowedBy() { return borrowedBy; }

    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setBorrowedBy(String admNo) { this.borrowedBy = admNo; }

    // Save to file in CSV format
    public String toFileString() {
        return id + "," + title + "," + author + "," + isAvailable + "," + field + "," + (borrowedBy == null ? "" : borrowedBy);
    }

    @Override
    public String toString() {
        return "[" + id + "] 📚 " + title + " by " + author + " - " + field + " - " +
               (isAvailable ? "✅ Available" : "❌ Borrowed by " + borrowedBy);
    }
}