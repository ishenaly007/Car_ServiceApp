package domain.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditService {
    private List<String> auditLog = new ArrayList<>();

    public void logAction(String action) {
        String logEntry = LocalDateTime.now() + " - Action: " + action;
        auditLog.add(logEntry);
    }

    public void logAction(String username, String action) {
        String logEntry = LocalDateTime.now() + " - User: " + username + " - Action: " + action;
        auditLog.add(logEntry);
    }

    public List<String> getAuditLog() {
        return auditLog;
    }

    public void exportAuditLog(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String logEntry : auditLog) {
                writer.write(logEntry + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while exporting the audit log: " + e.getMessage());
        }
    }
}