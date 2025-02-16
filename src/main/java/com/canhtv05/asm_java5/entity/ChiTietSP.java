package com.canhtv05.asm_java5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "ChiTietSP", schema = "dbo")
public class ChiTietSP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ColumnDefault("NULL")
    @Column(name = "NamBH")
    private Integer namBH;

    @Size(max = 50)
    @Nationalized
    @ColumnDefault("NULL")
    @Column(name = "MoTa", length = 50)
    private String moTa;

    @Column(name = "SoLuongTon")
    private Integer soLuongTon;

    @ColumnDefault("0")
    @Column(name = "GiaNhap", precision = 20)
    private Double giaNhap;

    @ColumnDefault("0")
    @Column(name = "GiaBan", precision = 20)
    private Double giaBan;

    @ManyToOne
    @JoinColumn(name = "IdSP", referencedColumnName = "Id")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "IdNsx", referencedColumnName = "Id")
    private Nsx nsx;

    @ManyToOne
    @JoinColumn(name = "IdMauSac", referencedColumnName = "Id")
    private MauSac mauSac;

    @ManyToOne
    @JoinColumn(name = "IdDongSP", referencedColumnName = "Id")
    private DongSP dongSP;

}