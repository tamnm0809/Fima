package com.fima.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prices implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prices")
    private Long id_prices;

    @Column(name = "prices")
    private Double prices;

    @Column(name = "time_apply")
    private String time_apply;

    @Column(name = "date_cre")
    private String date_cre;

    @Column(name = "date_update")
    private String date_update;

    @OneToOne(mappedBy = "prices")
    private Services services;
}
