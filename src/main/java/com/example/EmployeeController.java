/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example;

import com.example.model.Employee;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EmployeeController {
     @Autowired
    SessionFactory sessionFactory;

    @PostMapping(value = "/employee/saveEmployee", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> save(@RequestBody Employee entity) {
        try {
            Session session = sessionFactory.openSession();
            session.save(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Save failed");
        }
    }
    
    @PostMapping(value = "/employee/getAll", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> getAll() {
        try {
            Session session = sessionFactory.openSession();
            List<Employee> entityList = session.createQuery("From Employee").list();
            session.flush();
            session.close();
            return ResponseEntity.ok(entityList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
    
    @GetMapping(value = "/employee/getOne/{id}")
    @Transactional
    public ResponseEntity<?> getOne(@PathVariable(value = "id") int id) {
        try {
            Session session = sessionFactory.openSession();
            Employee entity = session.get(Employee.class, id);
            session.flush();
            session.close();
            return ResponseEntity.ok(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
    
    @PostMapping(value = "/employee/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> update(@RequestBody Employee entity) {
        try {
            Session session = sessionFactory.openSession();
            session.saveOrUpdate(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Data updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Save failed");
        }
    }
    
    @GetMapping(value = "/employee/delete/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Transactional
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        try {
            Session session = sessionFactory.openSession();
            Employee entity = session.get(Employee.class, id);
            session.delete(entity);
            session.flush();
            session.close();
            return ResponseEntity.ok("Delete successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Data fetch failed!");
        }
    }
    
    
    
    }
