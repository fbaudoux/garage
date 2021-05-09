package fr.crew.generator;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

public class GeneratorTask extends DefaultTask {

    @Input
    String inputName;

    public String getInputName() {
        return inputName;
    }

    @Option(option = "inputName", description = "Set the filename of the file to be opened.")
    public void setInputName(String inputName) {
        this.inputName = inputName;
    }
    
    @TaskAction
    void printToolVersion() {
        switch (inputName) {
            case "java":
               System.out.println("ok for java");
                break;
            case "groovy":
                System.out.println("ok groovy");
                break;
            default:
                throw new IllegalArgumentException("Unknown tool");
        }
    }


}
