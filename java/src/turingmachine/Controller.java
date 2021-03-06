package turingmachine;

import java.io.IOException;
import turingmachine.engine.TuringMachine;
import turingmachine.engine.TuringMachineImpl;
import turingmachine.exception.BadInputArgsException;
import turingmachine.exception.BadInputSymsException;
import turingmachine.inputdata.InputData;
import turingmachine.inputdata.InputDataInteractive;
import turingmachine.output.Output;

public class Controller {

    private InputData inputData;
    private TuringMachine tm;
    private Output outPut;

    public void run(String[] args) {
        
        inputData = new InputDataInteractive();
        
        try {
            inputData.processInput(args);
        } catch (BadInputArgsException ex) {
            System.err.println("BadInputArgsException");
            return;
            
        } catch (BadInputSymsException ex ) {
            System.err.println("BadInputSymsException");
            return;
        
        } catch (IOException ex) {
            System.err.println("Machine description not found.");
            return;
        }
        
        // First get the user input and use it to initiate the Turing Machine.
        tm = new TuringMachineImpl(inputData);
        
        outPut = new Output(tm.isAccepted(), tm.getSteps(), inputData.getPathOutFile());
        outPut.showResults(tm.getTape(), tm.getHeadPosition());
        
    }
}
