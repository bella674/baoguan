package com.example.baoguan.controller;

import com.example.baoguan.model.Baoguan;
import com.example.baoguan.repository.BaoguanRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
public class BaoguanController {

    @Autowired
    private BaoguanRepository baoguanRepository;

    public BaoguanController(BaoguanRepository baoguanRepository) {
        this.baoguanRepository = baoguanRepository;
    }

    // 首頁
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 表單頁
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("baoguan", new Baoguan());
        return "form";
    }

    // 提交表單
    @PostMapping("/submit")
    public String submitForm(@ModelAttribute Baoguan baoguan) {
        baoguanRepository.save(baoguan);
        return "redirect:/list";
    }

    // 顯示列表
    @GetMapping("/list")
    public String list(Model model) {
        List<Baoguan> baoguans = baoguanRepository.findAll();  // 取得全部資料，不分頁
        model.addAttribute("baoguans", baoguans);
        return "list";
    }




    // 刪除單筆
    @PostMapping("/delete/{id}")
    public String deleteBaoguan(@PathVariable Long id) {
        baoguanRepository.deleteById(id);
        return "redirect:/list";
    }

    // 查詢頁面（列出可查詢櫃號）
    @GetMapping("/search")
    public String showSearchPage(Model model) {
        List<String> containerNos = baoguanRepository.findAllContainerNos();
        model.addAttribute("containerNos", containerNos);
        return "search";
    }

    // 查詢處理
    @PostMapping("/search")
    public String search(@RequestParam("number") String number, Model model) {
        Baoguan result = baoguanRepository.findByNumber(number);
        if (result == null) {
            model.addAttribute("notFound", true);
        } else {
            model.addAttribute("baoguan", result);
        }
        model.addAttribute("containerNos", baoguanRepository.findAllContainerNos());
        return "search";
    }
    
    // 顯示編輯表單
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Baoguan baoguan = baoguanRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("baoguan", baoguan);
        return "edit";  // 對應 edit.html 編輯頁面
    }

    // 編輯後提交更新
    @PostMapping("/edit/{id}")
    public String updateBaoguan(@PathVariable Long id, @ModelAttribute Baoguan baoguan) {
        baoguan.setId(id);  // 確保設定ID
        baoguanRepository.save(baoguan);
        return "redirect:/list";
    }

}
