package com.company.framework;

public enum ShopSubMenuOptions {
    Warriors_Shop("Warriors Shop"),
    Mens("Men''s");

    private String value;

    ShopSubMenuOptions(String s) {
        value = s;

    }

    public String getValue() {
        return value;
    }
}
