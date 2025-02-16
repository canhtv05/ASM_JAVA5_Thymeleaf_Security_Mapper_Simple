package com.canhtv05.asm_java5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "MauSac", schema = "dbo")
public class MauSac {
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

}