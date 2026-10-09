package com.example.Demo.controller;

import com.example.Demo.model.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SinhVienController {

    @GetMapping("/sinhvien")
    public String hienFormThem(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("tieuDe", "Thêm sinh viên mới");
        return "sinhvien/them-moi";
    }
}