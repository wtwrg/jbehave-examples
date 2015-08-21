package com.github.kkrull.jbehave;

import com.google.common.base.Predicate;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;
import org.junit.Test;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

public class JBehaveStories extends JUnitStories {
    @Test //Make it easier for IDEs to recognize this as a test
    @Override
    public void run() throws Throwable { super.run(); }

    @Override
    protected List<String> storyPaths() {
        StoryFinder finder = new StoryFinder();
        return finder.findPaths(CodeLocations.codeLocationFromClass(getClass()), "**/*.story", "");
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        String packageName = "com.github.kkrull.jbehave";
        List<?> stepDefinitionInstances = stepDefinitionClasses(packageName)
            .map(JBehaveStories::onlyConstructor)
            .map(JBehaveStories::newInstance)
            .collect(toList());
        return new InstanceStepsFactory(configuration(), stepDefinitionInstances);
    }

    @Override
    public Configuration configuration() {
        StoryReporterBuilder storyReporter = new StoryReporterBuilder().withFormats(ANSI_CONSOLE, HTML);
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(getClass()))
            .useStoryReporterBuilder(storyReporter)
            .useStepMonitor(new SilentStepMonitor());
    }
    
    private Stream<Class<?>> stepDefinitionClasses(String packageName) {
        return new Reflections(packageName)
            .getTypesAnnotatedWith(Steps.class)
            .stream();
    }

    private static <T> Constructor<T> onlyConstructor(Class<T> theClass) {
        Set<Constructor> constructors = ReflectionUtils.getConstructors(theClass, new Predicate<Constructor>() {
            public boolean apply(Constructor constructor) {
                return constructor.getParameterCount() == 0;
            }
        });

        //Impossible to have 2 constructors with 0 parameters, because their parameter lists would be exactly the same
        if(constructors.isEmpty())
            throw new RuntimeException(String.format("Step definition class %s does not have any no-arg constructors", theClass));
        else
            return constructors.iterator().next();
    }

    private static <T> T newInstance(Constructor<T> noArgConstructor) {
        try {
            return noArgConstructor.newInstance();
        } catch(Exception e) {
            throw new RuntimeException(String.format("Failed to instantiate", noArgConstructor), e);
        }
    }
}
