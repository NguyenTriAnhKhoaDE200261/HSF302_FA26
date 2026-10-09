package com.example.Demo.controller;

import com.example.Demo.model.SinhVien;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SinhVienController {

    // Danh sách tạm lưu trong bộ nhớ
    private List<SinhVien> danhSach = new ArrayList<>(List.of(
            new SinhVien("SV001", "Nguyễn Văn An", 8.5),
            new SinhVien("SV002", "Trần Thị Bình", 6.2),
            new SinhVien("SV003", "Lê Hoàng Cường", 7.0)
    ));

    // Hiển thị danh sách sinh viên (Bài 1)
    @GetMapping("/sinhvien")
    public String danhSach(Model model) {
        model.addAttribute("sinhViens", danhSach);
        model.addAttribute("tieuDe", "Danh sách sinh viên");
        return "sinhvien/danh-sach";
    }

    // Hiển thị form thêm mới (TODO 2.1)
    @GetMapping("/sinhvien/them")
    public String hienFormThem(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        model.addAttribute("tieuDe", "Thêm sinh viên mới");
        return "sinhvien/them-moi";
    }

    // Xử lý POST dữ liệu thêm mới và áp dụng PRG Pattern (TODO 2.2 & 2.3)
    @PostMapping("/sinhvien/them")
    public String xuLyThem(@ModelAttribute("sinhVien") SinhVien sinhVien) {
        // Thêm sinh viên vừa nhập vào danh sách tạm
        danhSach.add(sinhVien);

        // Điều hướng chuyển hướng về trang danh sách
        return "redirect:/sinhvien";
    }
}