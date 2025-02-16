package com.canhtv05.asm_java5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Embeddable
public class GioHangChiTietId implements Serializable {
    private static final long serialVersionUID = 6208000037905388180L;
    @NotNull
    @Column(name = "IdGioHang", nullable = false)
    private Integer idGioHang;

    @NotNull
    @Column(name = "IdChiTietSP", nullable = false)
    private Integer idChiTietSP;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GioHangChiTietId entity = (GioHangChiTietId) o;
        return Objects.equals(this.idChiTietSP, entity.idChiTietSP) &&
                Objects.equals(this.idGioHang, entity.idGioHang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idChiTietSP, idGioHang);
    }

}