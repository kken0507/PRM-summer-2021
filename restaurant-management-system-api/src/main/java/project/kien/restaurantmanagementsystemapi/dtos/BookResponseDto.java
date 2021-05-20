package project.kien.restaurantmanagementsystemapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto implements Serializable
{
    private int bookCopyId;

    private int bookId;

    private String barcode;

    private String borrowedAt;

    private String dueAt;

    private int overdueDays;

    private String isbn;

    private String title;

    private String subtitle;

    private String publisher;

    private Integer publishYear;

    private Integer edition;

    private String language;

    private Integer pageNumber;

    private String callNumber;

    private boolean available;

    private int availableCopies;

    private int numberOfCopy;

    private String img;

    private String status;

    private String authors;

    private String genres;
}