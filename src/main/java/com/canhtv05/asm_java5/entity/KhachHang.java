package com.canhtv05.asm_java5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "KhachHang", schema = "dbo")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "Ma", length = 20, unique = true)
    private String ma;

    @Size(max = 30)
    @Nationalized
    @Column(name = "Ten", length = 30)
    private String ten;

    @Size(max = 30)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "TenDem", length = 30)
    private String tenDem;

    @Size(max = 30)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "Ho", length = 30)
    private String ho;

    @ColumnDefault("NULL")
    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Size(max = 30)
    @ColumnDefault("NULL")
    @Column(name = "Sdt", length = 30)
    private String sdt;

    @Size(max = 100)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "DiaChi", length = 100)
    private String diaChi;

    @Size(max = 50)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "ThanhPho", length = 50)
    private String thanhPho;

    @Size(max = 50)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "QuocGia", length = 50)
    private String quocGia;

    @ColumnDefault("NULL")
    @Lob
    @Column(name = "MatKhau")
    private String matKhau;

}