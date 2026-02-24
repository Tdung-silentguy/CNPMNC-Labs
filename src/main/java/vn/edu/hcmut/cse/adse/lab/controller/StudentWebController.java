package vn.edu.hcmut.cse.adse.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model){
        // The url should be: GET /students/search?keyword=SOMETHING
        List<Student> students = service.getAll();
        model.addAttribute("dsSinhVien", students);
        return "students"; // "students" here means "students.html"
    }

    @GetMapping("/search")
    public String getStudentByKeyword(@RequestParam(required = false) String keyword, Model model){
        // The url should be: GET /students/search?keyword=SOMETHING
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()){
            students = service.getByKeyword(keyword);

        }
        else {
            students = service.getAll();
            model.addAttribute("emptyKeyword", true);
        }
        model.addAttribute("dsSinhVien", students);
        return "students"; // "students" here means "students.html"
    }
}
