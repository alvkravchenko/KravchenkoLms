package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> { // один класс для для страницы с пагинацией с любым DTO через дженерик
    // T — тип DTO внутри (параметр типа класса)

    private List<T> content; // список DTO типа T
    private int page; // номер страницы
    private int size; // кол-во элементов на странице
    private long totalElements; // всего элементов в БД
    private int totalPages; // всего страниц

    public static <T> PageResponseDTO<T> from(Page<T> page) { // метод работает с любым типом DTO, принимает его и отдает его
        return new PageResponseDTO<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}