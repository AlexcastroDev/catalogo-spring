package io.lekito.admin.catalogo.domain.category;

import io.lekito.admin.catalogo.domain.validation.Error;
import io.lekito.admin.catalogo.domain.validation.ValidationHandler;
import io.lekito.admin.catalogo.domain.validation.Validator;

public class CategoryValidator extends Validator {
    private final Category category;
    public CategoryValidator(final Category category, final ValidationHandler handler) {
        super(handler);
        this.category = category;
    }

    @Override
    public void validate() {
        if(this.category.getName() == null) {
            this.validationHandler().append(new Error("'name' cannot be null"));
        }
    }
}
