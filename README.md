# Library-management-REVIEW-
# 📚 Library Management System – Team Hacktivator

Welcome to our **console-based Library Management System**, a Java application developed by **Team Hacktivator** as part of our academic project.

This system allows students to **view, borrow, return, search, and manage books** in a simple and user-friendly way — all through the terminal.

---

## 🧑‍💻 Team Members
- 👩‍💻 Tanvi Singh  
- 👩‍💻 Bhavya Mishra  
- 👩‍💻 Kashish Gupta

---

## 💡 Project Overview

This Library App helps manage books in a digital library. It's designed for students to interact with library records using their **admission number** and allows admins to add or delete books.

---

## ✨ Key Features

- 📖 View All Books  
- ➕ Add New Book  
- ❌ Delete Book  
- 📗 Borrow Book (based on student’s field)  
- 📕 Return Book  
- 🔍 Search by Title  
- 📌 View My Borrowed Book  

---

## 🛡 Admission No. Format Validation

Students must log in with a valid admission number in the format:  
`GUYYYYNNNNN`  
✅ Example: `GU202412345`  
❌ Invalid: `GURandom123`, `2024GU12345`

---

## 📚 Book Categories

Books are categorized into the following fields:
- Forensic  
- Law  
- Programming  
- Maths  
- Engineering  
- Nursing  
- Commerce  

Students can only borrow books **from their own field**.

---

## 💾 File Handling

All book records are saved in a file named `books.txt` for **persistent storage**, meaning data remains saved even after the program exits.

Each book entry stores:
- ID  
- Title  
- Author  
- Availability status  
- Field  
- BorrowedBy (admission number)

---

## 🧱 Code Structure

- `LibraryApp.java`: The main file with all logic  
- `Book` (inner class): Represents a book object  
- Modular methods like `addBookMenu()`, `borrowBook()`, `searchBookByTitle()` ensure clean and layered design

---

## ⚙ How to Run

1. Open a terminal or IDE (like IntelliJ or VS Code)  
2. Compile the code:  
   ```bash
   javac LibraryApp.java
  java LibraryApp
✅ Sample Books Included
The system loads with a set of sample books across all fields when first run — no need to add books manually unless desired.

📌 Notes
A student can borrow only one book at a time

Book ID must be unique

Deleted books must not be borrowed

System uses emoji prompts for better UX 😄

🏁 Conclusion
This project showcases a complete Java application with file handling, input validation, logic control, and clean output formatting — built with teamwork, learning, and practical design in mind.

Thanks for checking it out! 🙏
– Team Hacktivator
