package io.lekito.admin.catalogo.domain.category;

import io.lekito.admin.catalogo.domain.category.Category;
import io.lekito.admin.catalogo.domain.exceptions.DomainException;
import io.lekito.admin.catalogo.domain.exceptions.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstanceACategory() {
        final var expectedName = "Films";
        final var expectedDescription = "Most viewed";
        final var expectedActive = true;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedActive);

        Assertions.assertNotNull(category);
        Assertions.assertNotNull(category.getId());
        Assertions.assertEquals(expectedName, category.getName());
        Assertions.assertEquals(expectedActive, category.isActive());
        Assertions.assertNotNull(category.getCreatedAt());
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertNull(category.getDeletedAt());
    }

    @Test
    public void givenAnInvalidName_whenCallNewCategory_thenReceiveError() {
        final String expectedName = null;
        final var expectedDescription = "Most viewed";
        final var expectedActive = true;
        final var expectErrorMessage = "'name' cannot be null";
        final var expectedErrorCount = 1;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedActive);
        final var instanceError = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, instanceError.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, instanceError.getErrors().get(0).message());
    }
}
