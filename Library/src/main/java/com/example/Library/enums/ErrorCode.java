package com.example.Library.enums;

public enum ErrorCode {
    BOOK_DONOT_EXISTED(1001,"Book don't exist!!"),
    INVALID_QUANTITY(1002,"Invalid quantity!"),
    INVALID_YEAR(1003,"Invalid year!"),
    INVALID_AGE(1004,"Invalid age!"),
    CUSTOMER_DONOT_EXISTED(1005,"Customer don't exist!!"),
    OUT_OF_STOCK(1006,"Out of stock !!"),
    ALREADY(1007,"The book already borrow, Please return first !!"),
    NOT_BORROWED(1008,"The customer don't borrow this book !!"),

    ;
    private int code;
    private String massage;

    ErrorCode(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
