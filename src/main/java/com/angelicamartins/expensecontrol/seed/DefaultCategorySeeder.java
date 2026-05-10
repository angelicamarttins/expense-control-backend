package com.angelicamartins.expensecontrol.seed;

import static com.angelicamartins.expensecontrol.seed.SeedBuilders.buildDefaultCategory;

import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultCategorySeeder implements ApplicationRunner {

  private final CategoryRepository categoryRepository;

  @Override
  @Transactional
  public void run(ApplicationArguments args) throws Exception {

    InputStream inputStream = getClass()
      .getResourceAsStream("/seed/mutable-default-categories.csv");

    if (inputStream == null) {
      throw new IllegalStateException(
        "Seed file not found: /seed/mutable-default-categories.csv"
      );
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

      String line;

      br.readLine();

      while ((line = br.readLine()) != null) {

        String[] columns = line.split(";");

        UUID categoryId = UUID.fromString(columns[0]);
        String categoryName = columns[1];

        Category category = categoryRepository
          .findById(categoryId)
          .orElseGet(() -> buildDefaultCategory(categoryId, categoryName));

        categoryRepository.save(category);
      }
    }
  }
}
