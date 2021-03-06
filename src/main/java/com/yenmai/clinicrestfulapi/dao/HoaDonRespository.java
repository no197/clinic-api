package com.yenmai.clinicrestfulapi.dao;

import com.yenmai.clinicrestfulapi.entity.HoaDon;
import com.yenmai.clinicrestfulapi.report.model.GroupByResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HoaDonRespository extends JpaRepository<HoaDon, Integer> {

    @Query( value = "select concat('Tháng ', month(ngaythem)) as name, sum(ThanhTien) as value "
            + "from HoaDon "
            + "where year(ngaythem) = :namThem "
            + "group by name "
            + "order by month(ngaythem) ", nativeQuery = true)
    List<GroupByResult> doanhThuTheoThangTrongNam(int namThem);

    @Query( value = "select * from HoaDon "
            + "where year(ngaythem) = :namThem "
            + "and month(ngaythem) = :thangThem "
            + "order by ngaythem", nativeQuery = true)
    List<HoaDon> findByThangThem(int thangThem, int namThem);


    @Query( value = "SELECT  concat(month(ngaythem), '-', year(ngaythem)) as name, SUM(thanhtien) as value\n" +
            " FROM HoaDon " +
            " where ngaythem > DATE_SUB( LAST_DAY(NOW()), INTERVAL 12 MONTH) " +
            " group by  month(ngaythem), year(ngaythem) " +
            " order by ngaythem;", nativeQuery = true)
    List<GroupByResult> doanhThuMuoiHaiThangQua();
}
