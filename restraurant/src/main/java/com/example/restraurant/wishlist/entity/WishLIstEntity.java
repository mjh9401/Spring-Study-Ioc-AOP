package com.example.restraurant.wishlist.entity;

import com.example.restraurant.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishLIstEntity extends MemoryDbEntity {
    private String title;
    private String category;
    private String address;
    private String roadAdress;
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisit;

}
