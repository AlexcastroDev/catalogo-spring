package io.lekito.admin.catalogo.application;

import io.lekito.admin.catalogo.domain.Category;

public class UseCase {
    public Category execute() {
        return new Category();
    }
}