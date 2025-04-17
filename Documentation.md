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
