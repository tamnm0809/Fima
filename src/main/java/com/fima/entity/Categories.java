package com.fima.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categories")
    private Long id_categories;

    @Column(name = "name", columnDefinition = "NVARCHAR(50)")
    @Size(min = 4, max = 255, message = "Tên phải có ít nhất 4 kì tự và nhỏ hơn 255 kí tự!")
    private String name;

    @Column(name = "descriptions", columnDefinition = "NVARCHAR(MAX)")
    private String descriptions;

    @Column(name = "date_cre")
    @NotBlank(message = "Vui lòng nhập ngày tạo!")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String date_cre;

    @Column(name = "date_update")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String date_update;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private Set<Services> services;
}
