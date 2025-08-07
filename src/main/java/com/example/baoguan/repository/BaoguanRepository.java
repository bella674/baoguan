package com.example.baoguan.repository;

import com.example.baoguan.model.Baoguan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaoguanRepository extends JpaRepository<Baoguan, Long> {

    // 用櫃號查單筆資料
    Baoguan findByNumber(String number);

    // 查全部櫃號（只抓出欄位 number）
    @Query("SELECT b.number FROM Baoguan b")
    List<String> findAllContainerNos();
}
