package ru.junit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ru.junit.utils.AllCategories;

@Suite.SuiteClasses({Task3Test.class})
@RunWith(Categories.class)
@Categories.IncludeCategory(AllCategories.PositiveTests.class)
public class PositiveSuiteRun {
}
