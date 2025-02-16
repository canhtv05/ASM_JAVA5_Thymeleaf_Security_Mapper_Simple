package com.canhtv05.asm_java5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class HoaDonChiTietId implements Serializable {
    private static final long serialVersionUID = -3763605346798430172L;
    @NotNull
    @Column(name = "IdHoaDon", nullable = false)
    private Integer idHoaDon;

    @NotNull
    @Column(name = "IdChiTietSP", nullable = false)
    private Integer idChiTietSP;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HoaDonChiTietId entity = (HoaDonChiTietId) o;
        return Objects.equals(this.idHoaDon, entity.idHoaDon) &&
                Objects.equals(this.idChiTietSP, entity.idChiTietSP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHoaDon, idChiTietSP);
    }

}