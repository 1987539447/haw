package com.bigknow.demo.model;

import com.bigknow.frame.entity.BaseEntity;
import com.bigknow.frame.entity.IEntity;

import java.math.BigDecimal;

/**
 * Created by Zhan on 2016/11/9 0009.
 */
public class Book extends BaseEntity implements IEntity {
    private String bookId;
    private String bookName;
    private String author;
    private BigDecimal price;



    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public void setId(String id) {

    }
    @Override
    public String getId() {
        return null;
    }
}
