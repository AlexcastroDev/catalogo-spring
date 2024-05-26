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
        final var expectedDescription = "Most viewed";
        final var expectedActive = true;
        final var expectErrorMessage = "'name' cannot be null";
        final var expectedErrorCount = 1;

        Category category = Category.newCategory(null, expectedDescription, expectedActive);
        final var instanceError = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, instanceError.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, instanceError.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategory_thenReceiveError() {
        final var expectedDescription = "Most viewed";
        final var expectedActive = true;
        final var expectErrorMessage = "'name' cannot be empty";
        final var expectedErrorCount = 1;

        Category category = Category.newCategory(" ", expectedDescription, expectedActive);
        final var instanceError = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, instanceError.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, instanceError.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategory_thenReceiveError() {
        final String expectedName = "Al";
        final var expectedDescription = "Most viewed";
        final var expectedActive = true;
        final var expectErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedActive);
        final var instanceError = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, instanceError.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, instanceError.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMoreThan255_whenCallNewCategory_thenReceiveError() {
        final String expectedName = "Percebemos, cada vez mais, que a determinação clara de objetivos garante a contribuição de um grupo importante na determinação das novas proposições. A nível organizacional, a complexidade dos estudos efetuados cumpre um papel essencial na formulação das posturas dos órgãos dirigentes com relação às suas atribuições. O empenho em analisar a constante divulgação das informações agrega valor ao estabelecimento dos conhecimentos estratégicos para atingir a excelência. Neste sentido, a consolidação das estruturas obstaculiza a apreciação da importância dos modos de operação convencionais";
        final var expectedDescription = "Most viewed";
        final var expectedActive = true;
        final var expectErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        Category category = Category.newCategory(expectedName, expectedDescription, expectedActive);
        final var instanceError = Assertions.assertThrows(DomainException.class, () -> category.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, instanceError.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, instanceError.getErrors().get(0).message());
    }

    @Test
    public void givenAnEmptyDescription_whenCallNewCategory_thenNotReceiveError() {
        final var expectedDescription = " ";
        final var expectedActive = true;

        Category category = Category.newCategory("Lekito", expectedDescription, expectedActive);
        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
    }

    @Test
    public void givenAnActiveFalse_whenCallNewCategory_thenNotReceiveError() {
        final var expectedDescription = " ";
        final var expectedActive = false;

        Category category = Category.newCategory("Lekito", expectedDescription, expectedActive);
        Assertions.assertDoesNotThrow(() -> category.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedActive, category.isActive());
    }
}
