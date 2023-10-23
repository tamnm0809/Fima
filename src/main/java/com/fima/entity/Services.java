package com.fima.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_services")
    private Long id_services;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    @Size(min = 4, max = 100, message = "Tên phải có ít nhất 4 kì tự và nhỏ hơn 255 kí tự!")
    private String name;

    @Column(name = "prices")
    private Double prices;

    @Column(name = "descriptions", columnDefinition = "NVARCHAR(MAX)")
    @Size(min = 10, max = 255, message = "Mô tả cần nhập ít nhất 10 kí tự!")
    private String descriptions;

    @Column(name = "image")
    private String image;

    @Column(name = "date_cre")
    @NotNull()
    @NotBlank(message = "Vui lòng nhập ngày tạo dịch vụ này!")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String date_cre;

    @Column(name = "date_update")
    @NotNull(message = "Vui lòng chọn ngày!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categories")
    private Categories categories;

}
