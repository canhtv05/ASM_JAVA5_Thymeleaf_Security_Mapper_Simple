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
@Table(name = "HoaDon", schema = "dbo")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "Ma", length = 20)
    private String ma;

    @ColumnDefault("NULL")
    @Column(name = "NgayTao")
    private LocalDate ngayTao;

    @ColumnDefault("NULL")
    @Column(name = "NgayThanhToan")
    private LocalDate ngayThanhToan;

    @ColumnDefault("NULL")
    @Column(name = "NgayShip")
    private LocalDate ngayShip;

    @ColumnDefault("NULL")
    @Column(name = "NgayNhan")
    private LocalDate ngayNhan;

    @ColumnDefault("0")
    @Column(name = "TinhTrang")
    private Integer tinhTrang;

    @Size(max = 50)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "TenNguoiNhan", length = 50)
    private String tenNguoiNhan;

    @Size(max = 100)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "DiaChi", length = 100)
    private String diaChi;

    @Size(max = 30)
    @ColumnDefault("NULL")
    @Column(name = "Sdt", length = 30)
    private String sdt;

    @ManyToOne
    @JoinColumn(name = "IdKH", referencedColumnName = "Id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IdNV", referencedColumnName = "Id")
    private NhanVien nhanVien;
}