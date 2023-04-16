package org.shahul.productservice.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public class ProductRequestDTO {

   // @NotBlank(message = "Title cannot be blank")
    private String title;
    @Min(value = 1,message = "Min should be 1")
    private BigDecimal price;
    @Min(value = 1,message = "Qty min should be 1")
    @Max(value = 10,message = "Qty max should be 10")
    private Integer quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
