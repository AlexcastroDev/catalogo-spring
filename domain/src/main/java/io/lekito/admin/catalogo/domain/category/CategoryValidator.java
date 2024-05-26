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
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.category.getName();
        if(name == null) {
            this.validationHandler().append(new Error("'name' cannot be null"));
            return;
        }

        if(name.isBlank()) {
            this.validationHandler().append(new Error("'name' cannot be empty"));
            return;
        }

        final int length = name.trim().length();
        if(length > 255 || length < 3) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
            return;
        }
    }
}
