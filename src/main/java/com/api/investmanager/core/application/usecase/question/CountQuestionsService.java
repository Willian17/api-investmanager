package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CountQuestionResponse;
import com.api.investmanager.core.application.port.input.question.CountQuestionsUseCase;
import com.api.investmanager.core.application.port.output.question.CountQuestionOutput;
import com.api.investmanager.core.domain.enuns.CategoryQuestionEnum;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CountQuestionsService implements CountQuestionsUseCase {

    private final CountQuestionOutput countQuestionOutput;

    public CountQuestionsService(CountQuestionOutput countQuestionOutput) {
        this.countQuestionOutput = countQuestionOutput;
    }

    @Override
    public List<CountQuestionResponse> execute(String idUser) {
        List<CountQuestionResponse> response = countQuestionOutput.execute(idUser);
        addCategoriesNotPresent(response);
        return response;
    }

    private static void addCategoriesNotPresent(List<CountQuestionResponse> response) {
        Predicate<CategoryQuestionEnum> categoriesNotPresent = (CategoryQuestionEnum category) -> response.stream().noneMatch(r -> r.category().equals(category));
        Arrays.stream(CategoryQuestionEnum.values())
                .filter(categoriesNotPresent)
                .forEach(addQuestionCount(response));
    }

    private static Consumer<CategoryQuestionEnum> addQuestionCount(List<CountQuestionResponse> response) {
        return categoryEnum -> {
            response.add(new CountQuestionResponse(categoryEnum, 0));
        };
    }
}
