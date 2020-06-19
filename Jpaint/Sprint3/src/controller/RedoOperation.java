package controller;
// perfornm redo
public class RedoOperation implements IOperation
{
    @Override
    public void run() 
    {
    	// print statement
        System.out.println("RedoOperation executed.");
        OperationHistory.redo();
       
    }
}
