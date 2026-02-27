package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmut.cse.adse.lab.entity.Student;
import vn.edu.hcmut.cse.adse.lab.service.StudentService;

import java.util.List;

@Controller // lưu ý không dùng @RestController
// @Controller returns a View (HTML), @RestController returns data (JSON)
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    @GetMapping
    public String getAllStudents(Model model){
        // The url should be: GET /students/search?keyword=SOMETHING
        List<Student> students = service.getAll();
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("isSearchResult", false);
        return "students"; // "students" here means "students.html"
    }

    @GetMapping("/search")
    public String getStudentByKeyword(@RequestParam(required = false) String keyword, Model model){
        // The url should be: GET /students/search?keyword=SOMETHING
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()){
            students = service.getByKeyword(keyword);
            model.addAttribute("isSearchResult", true);

        }
        else {
            students = service.getAll();
            model.addAttribute("isSearchResult", false);
            model.addAttribute("emptyKeyword", true);
        }
        model.addAttribute("dsSinhVien", students);
        return "students"; // "students" here means "students.html"
    }

    @GetMapping("/info/{id}")
    public String getStudentById(@PathVariable("id") String id, Model model){
        Student student = service.getById(id);
        model.addAttribute("student", student);
        return "student-detail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("student", new Student());
        return "student-add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student){
        service.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model){
        model.addAttribute("student", service.getById(id));
        return "student-edit";
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@ModelAttribute("student") Student student){
        service.editStudent(student);
        return "redirect:/students";
    }
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id){
        service.deleteStudent(id);
        return "redirect:/students";
    }

}
