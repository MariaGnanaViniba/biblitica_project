package com.vapasi.biblioteca.exceptions;

public enum ErrorMessagesEnum {
    BookNotFound("Book not found, please try again."),
    CustomerNotFound("Customer not found, please try again."),
    BookAlreadyIssued("This book is already issued to you."),
    BookSuccessfullyIssued("Book is successfully issued."),
    ImproperDetails("Please provide proper details, try again.");
    private String message;

    private ErrorMessagesEnum(String s) {
        message = s;
    }

    public String getMessage() {
        return message;
    }

}
