package com.fima.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Services implements Serializable {

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

    @OneToOne(mappedBy = "services")
    private Staff staff;
}
