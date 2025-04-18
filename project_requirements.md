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

