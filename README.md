# Waste Collection CRUD Application

## Student: Osmonov Emir Zhomartovich

## Description
This Java application is a simple waste collection management system that enables users to perform basic CRUD (Create, Read, Update, Delete) operations on waste records. The app features a graphical interface built with JavaFX and stores data in a MySQL database. It also supports exporting records to JSON and CSV formats.

## Objectives
- Implement a fully functional Java application with a graphical user interface using JavaFX.
- Provide the ability to create, read, update, and delete waste collection records.
- Ensure data persistence using PostgreSQL for long-term storage of records.
- Include input validation and error handling for a smooth user experience.
- Add export functionality to allow data extraction in JSON and CSV formats.
- Design the code in a modular and maintainable way for future feature expansion.


![image](https://github.com/user-attachments/assets/2a37cce6-5612-4ebb-b51d-6c3a65a58e79)



![image](https://github.com/user-attachments/assets/19975b08-8435-4dcf-90e7-295407adeb45)




![image](https://github.com/user-attachments/assets/e29ec84e-eeab-43af-8df0-5e7a5312185d)


# Project Requirements — Waste Collection CRUD Application

## Functional Requirements

1. **CRUD Operations**
   - Create new waste records with date, type, and disposal method.
   - Read and view all existing records in a graphical interface.
   - Update existing waste records.
   - Delete selected waste records.

2. **Graphical User Interface (GUI)**
   - User-friendly interface built with JavaFX.
   - Input fields for entering waste data.
   - List view to display current records.
   - Buttons for adding, deleting, and exporting data.

3. **Data Persistence**
   - Store records in a PostgreSQL database.
   - Load records from the database on application start.
   - Save new or updated records automatically.

4. **Export Functionality**
   - Export all records to a JSON file.
   - Export all records to a CSV file.

5. **Validation and Error Handling**
   - Validate that no fields are left empty.
   - Optionally validate date format (YYYY-MM-DD).
   - Alert user when operations fail (e.g., export error or no record selected).

6. **Optional Enhancements (for demonstration/future work)**
   - Filter/search records by keyword.
   - Edit records directly from the list.
   - Generate summary statistics (total records, most common waste type, etc.).
   - Import records from JSON or CSV.

---

## Non-Functional Requirements

- Modular code structure with reusable functions and clean design.
- Proper exception handling to avoid crashes and handle unexpected inputs.
- Source code documented and easy to read.
- Project must run on any system with Java and PostgreSQL installed.
- Use of `.gitignore` to avoid committing compiled or temporary files.

---

## Deliverables

- Full Java source code with GUI.
- SQL-based MySQL database integration.
- JSON and CSV export functionality.
- README file and project documentation.
- Git repository with clear commit history and version control.



# Project Documentation — Waste Collection CRUD Application

## Student: Osmonov Emir Zhomartovich

---

## Overview

This is a Java-based graphical application developed using **JavaFX**, which allows users to manage waste collection records. The data is stored in a **SQLite** database (`waste.db`), and users can create, read, and delete records. Additionally, users can export the data to **JSON** and **CSV** formats using the Gson and standard I/O libraries.

---

## Data Structures

### Class: `WasteRecord`

This class represents a single record in the system. It contains the following fields:
- `UUID id` – a unique identifier for each record.
- `String date` – the collection date in `YYYY-MM-DD` format.
- `String wasteType` – the type of waste (e.g., Plastic, Organic).
- `String disposalMethod` – how the waste was handled (e.g., Recycling, Landfill).

It includes:
- Getters and setters
- Constructors for new and loaded records
- `toString()` for display in the JavaFX `ListView`

---

## Components and Modules

### JavaFX GUI
The interface includes:
- A `ListView<WasteRecord>` to display all records.
- Three `TextField`s to input date, waste type, and disposal method.
- Buttons:
  - **Add Record**: Inserts a new record into the database.
  - **Delete Selected**: Removes a record from the database.
  - **Export to JSON**: Saves all records as a formatted `.json` file.
  - **Export to CSV**: Saves all records in `.csv` format.

---

## Core Functions

### `addRecord()`
- Validates inputs
- Creates a new `WasteRecord`
- Inserts the record into the SQLite database via `insertIntoDatabase()`
- Refreshes the list

### `deleteRecord()`
- Gets selected record from `ListView`
- Deletes it from the database using `deleteFromDatabase()`
- Refreshes the list

### `loadFromDatabase()`
- Connects to the SQLite database
- Loads all records into `records` list and displays them in the `ListView`

### `insertIntoDatabase(WasteRecord record)`
- Executes a parameterized SQL `INSERT` statement

### `deleteFromDatabase(WasteRecord record)`
- Executes a parameterized SQL `DELETE` statement using the record's UUID

### `exportToJson()` and `exportToCsv()`
- Write all current records to a file in `.json` or `.csv` format using `Gson` and `PrintWriter`

---

## Database Schema

**Database:** `waste.db` (SQLite)

**Table:** `waste_records`

| Column           | Type   | Description                  |
|------------------|--------|------------------------------|
| id               | TEXT   | Primary key (UUID)           |
| date             | TEXT   | Collection date              |
| waste_type       | TEXT   | Type of waste                |
| disposal_method  | TEXT   | Method of disposal           |

---

## Algorithms

This project uses basic algorithmic logic:
- **Input validation**: Regex for date format (`YYYY-MM-DD`).
- **Record insertion/deletion**: via SQL statements.
- **Data transformation**: converting records into JSON/CSV text format.
There are no complex algorithms, but good use of clean control structures and separation of concerns.

---

## Challenges Faced

1. **Database Integration**
   - Switching from file-based serialization to SQLite required understanding of JDBC and SQL queries.

2. **Validation and UX**
   - Ensuring proper input validation while maintaining a responsive interface.

3. **Serialization for Export**
   - Formatting data correctly for JSON and CSV exports using third-party (`Gson`) and built-in libraries.

4. **UUID Management**
   - Ensuring consistent record identity between GUI, database, and export formats.

---

## Possible Future Improvements

- Add **update/edit** functionality to modify existing records.
- Implement

# How to Compile and Run the Waste Management Application

## Requirements

Make sure you have the following:

1. **Java Development Kit (JDK)** — version 11 or higher.
2. **JavaFX SDK** — download from: https://gluonhq.com/products/javafx/
3. **Gson Library** — `gson-2.8.9.jar`
4. **SQLite JDBC Driver** — `sqlite-jdbc-3.49.1.0.jar`

---

## Compile the project
javac --module-path "javafx-sdk-21.0.1\lib" --add-modules javafx.controls -cp ".;gson-2.8.9.jar;sqlite-jdbc-3.49.1.0.jar" WasteManagementApp.java

## Run the program 
java --module-path "javafx-sdk-21.0.1\lib" --add-modules javafx.controls -cp ".;gson-2.8.9.jar;sqlite-jdbc-3.49.1.0.jar" WasteManagementApp




Presentation - https://docs.google.com/presentation/d/e/2PACX-1vSoBwUVOrMYU1gX0qzBDYAxDHHOtmVBzw2F9C2_rqfmL7YOri2bM7UIsFsEflQfojqZNWvAFbAt7Rcd/pub?start=false&loop=false&delayms=3000
