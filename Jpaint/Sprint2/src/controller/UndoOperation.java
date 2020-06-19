package controller;

import controller.IOperation;
// undo function
public class UndoOperation implements IOperation 
{

    @Override
    public void run()
    {
        System.out.println("UndoOperation executed.");
        OperationHistory.undo();
      
    }
}
