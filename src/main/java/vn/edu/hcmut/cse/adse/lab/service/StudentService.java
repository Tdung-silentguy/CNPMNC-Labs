package vn.edu.hcmut.cse.adse.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.repository.StudentRepository;

import java.util.List;
import java.util.Random;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    public List<Student> getAll(){
        return repository.findAll();
    }
    public Student getById(String id){
        return repository.findById(id).orElse(null);
    }
    public List<Student> getByKeyword(String keyword){
        return repository.findByNameContaining(keyword);
    }
    
    private String generateUnique5DigitId() {
        Random random = new Random();
        String newId;
        do {
            int randomNumber = 10000 + random.nextInt(90000);
            newId = String.valueOf(randomNumber);
        } while (repository.existsById(newId)); 
        
        return newId;
    }

//    public void addStudent(Student student){
//        int maxId = repository.getCurrentMaxId();
//        student.setId(String.valueOf(maxId));
//        repository.save(student);
//    }
    public synchronized void addStudent(Student student){
        student.setId(generateUnique5DigitId());
        
        repository.save(student);
    }

    public String getNextId() {
        int nextId = 1;

        // Loop until repository.existsById returns false
        while (repository.existsById(String.valueOf(nextId))) {
            nextId++;
        }

        return String.valueOf(nextId);
    }

    public void editStudent(Student student){
        repository.save(student);
    }
    public void deleteStudent(String id){
        repository.deleteById(id);
    }
}
