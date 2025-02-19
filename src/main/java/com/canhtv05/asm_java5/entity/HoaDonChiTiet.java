package com.canhtv05.asm_java5.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "HoaDonChiTiet", schema = "dbo")
public class HoaDonChiTiet {
    @EmbeddedId
    private HoaDonChiTietId id;

    @ManyToOne
    @MapsId("idHoaDon")
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @ManyToOne
    @MapsId("idChiTietSP")
    @JoinColumn(name = "IdChiTietSP")
    private ChiTietSP chiTietSP;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @ColumnDefault("0")
    @Column(name = "DonGia", precision = 20)
    private Double donGia;

}