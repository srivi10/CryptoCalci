package org.srivi.Trading.QE;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {
    public static Map<String, String[]> readAccountData(InputStream inputStream) throws IOException {
        Map<String, String[]> accounts = new HashMap<>();

        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                // Read cell values
                String accountSet = row.getCell(0).getStringCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                String holder = row.getCell(3).getStringCellValue();
                String type = row.getCell(4).getStringCellValue();
                String transfer = row.getCell(5).getStringCellValue();
                String offers = row.getCell(6).getStringCellValue();
                String paymentPlan = row.getCell(7).getStringCellValue();
                String creditLimit =row.getCell(8).getStringCellValue();

                // Store in map with account set as key
                accounts.put(accountSet, new String[]{username, password, holder, type, transfer, offers, paymentPlan,creditLimit});
            }
        }

        return accounts;
    }
}
