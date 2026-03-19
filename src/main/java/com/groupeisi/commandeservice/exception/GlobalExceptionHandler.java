package com.groupeisi.commandeservice.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException ex) {
        Map<String,Object> e = new HashMap<>();
        e.put("timestamp", LocalDateTime.now().toString()); e.put("status", 404); e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequest(IllegalArgumentException ex) {
        Map<String,Object> e = new HashMap<>();
        e.put("timestamp", LocalDateTime.now().toString()); e.put("status", 400); e.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
}
