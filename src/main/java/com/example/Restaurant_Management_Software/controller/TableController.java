package com.example.Restaurant_Management_Software.controller;


import com.example.Restaurant_Management_Software.model.RestaurantTable;
import com.example.Restaurant_Management_Software.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RestaurantTable>> getTablesByStatus(@PathVariable RestaurantTable.TableStatus status) {
        return ResponseEntity.ok(tableService.getTablesByStatus(status));
    }

    @PostMapping
    public ResponseEntity<RestaurantTable> createTable(@RequestBody RestaurantTable table) {
        return ResponseEntity.ok(tableService.createTable(table));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RestaurantTable> updateTableStatus(
            @PathVariable Long id,
            @RequestParam RestaurantTable.TableStatus status) {
        return ResponseEntity.ok(tableService.updateTableStatus(id, status));
    }
}
