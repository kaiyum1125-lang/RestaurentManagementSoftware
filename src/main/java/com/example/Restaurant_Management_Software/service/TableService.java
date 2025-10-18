package com.example.Restaurant_Management_Software.service;

import com.example.Restaurant_Management_Software.model.RestaurantTable;
import com.example.Restaurant_Management_Software.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }

    public List<RestaurantTable> getTablesByStatus(RestaurantTable.TableStatus status) {
        return tableRepository.findByStatus(status);
    }

    public RestaurantTable createTable(RestaurantTable table) {
        return tableRepository.save(table);
    }

    public RestaurantTable updateTableStatus(Long tableId, RestaurantTable.TableStatus status) {
        RestaurantTable table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        table.setStatus(status);
        return tableRepository.save(table);
    }
}