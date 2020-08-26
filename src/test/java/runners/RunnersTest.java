package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"},
		snippets = SnippetType.CAMELCASE,
		monochrome = true,
		features = {"src/test/resources/features/features/DesafioWebYaman.feature"},
		glue = {"steps"},
		tags = {"@Yaman"}
		
		)

public class RunnersTest {

}
