import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

class WasteRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String date;
    private String wasteType;
    private String disposalMethod;

    public WasteRecord(String date, String wasteType, String disposalMethod) {
        this(UUID.randomUUID(), date, wasteType, disposalMethod);
    }

    public WasteRecord(UUID id, String date, String wasteType, String disposalMethod) {
        this.id = id;
        this.date = date;
        this.wasteType = wasteType;
        this.disposalMethod = disposalMethod;
    }

    public UUID getId() { return id; }
    public String getDate() { return date; }
    public String getWasteType() { return wasteType; }
    public String getDisposalMethod() { return disposalMethod; }

    public void setDate(String date) { this.date = date; }
    public void setWasteType(String wasteType) { this.wasteType = wasteType; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s", date, wasteType, disposalMethod);
    }
}

public class WasteManagementApp extends Application {
    private static final String DB_URL = "jdbc:sqlite:waste.db";
    private static final String JSON_FILE = "waste_records.json";
    private static final String CSV_FILE = "waste_records.csv";

    private static List<WasteRecord> records = new ArrayList<>();
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private ListView<WasteRecord> listView;
    private TextField dateField, typeField, methodField;

    public static void main(String[] args) {
        createTableIfNotExists();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Waste Management GUI");

        listView = new ListView<>();
        loadFromDatabase();

        dateField = new TextField();
        dateField.setPromptText("Date (YYYY-MM-DD)");

        typeField = new TextField();
        typeField.setPromptText("Waste Type");

        methodField = new TextField();
        methodField.setPromptText("Disposal Method");

        Button addButton = new Button("Add Record");
        addButton.setOnAction(e -> addRecord());

        Button deleteButton = new Button("Delete Selected");
        deleteButton.setOnAction(e -> deleteRecord());

        Button exportJsonButton = new Button("Export to JSON");
        exportJsonButton.setOnAction(e -> exportToJson());

        Button exportCsvButton = new Button("Export to CSV");
        exportCsvButton.setOnAction(e -> exportToCsv());

        VBox inputBox = new VBox(8, dateField, typeField, methodField, addButton, deleteButton, exportJsonButton, exportCsvButton);
        inputBox.setPrefWidth(250);

        HBox layout = new HBox(10, listView, inputBox);
        layout.setPrefSize(750, 400);
        layout.setStyle("-fx-padding: 10;");

        primaryStage.setScene(new Scene(layout));
        primaryStage.setOnCloseRequest(e -> {});
        primaryStage.show();
    }

    private void addRecord() {
        String date = dateField.getText().trim();
        String type = typeField.getText().trim();
        String method = methodField.getText().trim();

        if (date.isEmpty() || type.isEmpty() || method.isEmpty()) {
            showAlert("All fields must be filled.");
            return;
        }

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showAlert("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        WasteRecord record = new WasteRecord(date, type, method);
        insertIntoDatabase(record);
        refreshList();
        clearInputs();
    }

    private void deleteRecord() {
        WasteRecord selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            deleteFromDatabase(selected);
            refreshList();
        } else {
            showAlert("No record selected to delete.");
        }
    }

    private void exportToJson() {
        try (Writer writer = new FileWriter(JSON_FILE)) {
            gson.toJson(records, writer);
            showAlert("Exported to JSON successfully.");
        } catch (IOException e) {
            showAlert("Failed to export to JSON.\n" + e.getMessage());
        }
    }

    private void exportToCsv() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            writer.println("Date,WasteType,DisposalMethod");
            for (WasteRecord record : records) {
                writer.printf("%s,%s,%s%n", record.getDate(), record.getWasteType(), record.getDisposalMethod());
            }
            showAlert("Exported to CSV successfully.");
        } catch (IOException e) {
            showAlert("Failed to export to CSV.\n" + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void createTableIfNotExists() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS waste_records (
                    id TEXT PRIMARY KEY,
                    date TEXT,
                    waste_type TEXT,
                    disposal_method TEXT
                )
            """);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    private void loadFromDatabase() {
        records.clear();
        listView.getItems().clear();

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM waste_records");

            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                String date = rs.getString("date");
                String wasteType = rs.getString("waste_type");
                String disposalMethod = rs.getString("disposal_method");

                WasteRecord record = new WasteRecord(id, date, wasteType, disposalMethod);
                records.add(record);
                listView.getItems().add(record);
            }
        } catch (SQLException e) {
            showAlert("Database error: " + e.getMessage());
        }
    }

    private void insertIntoDatabase(WasteRecord record) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO waste_records (id, date, waste_type, disposal_method) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, record.getId().toString());
            pstmt.setString(2, record.getDate());
            pstmt.setString(3, record.getWasteType());
            pstmt.setString(4, record.getDisposalMethod());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showAlert("Failed to insert into database.\n" + e.getMessage());
        }
    }

    private void deleteFromDatabase(WasteRecord record) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "DELETE FROM waste_records WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, record.getId().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            showAlert("Failed to delete from database.\n" + e.getMessage());
        }
    }

    private void refreshList() {
        loadFromDatabase(); 
    }

    private void clearInputs() {
        dateField.clear();
        typeField.clear();
        methodField.clear();
    }
}
