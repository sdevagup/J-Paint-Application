
package controller;

import java.util.Stack;

import interfaces.IUndoable;

// OperationHistory class it has undo and redo
public class OperationHistory 
{
    private static final Stack<IUndoable> undoOperation = new Stack<IUndoable>();
    private static final Stack<IUndoable> redoOperation = new Stack<IUndoable>();

    public static void add(IUndoable cmd)
    {
        undoOperation.push(cmd);
        redoOperation.clear();
    }
// perform undo
    public static boolean undo() 
    {
        boolean result = !undoOperation.empty();
        if (result) {
            IUndoable c = undoOperation.pop();
            redoOperation.push(c);
            c.undo();
        }
        return result;
    }
// perform redo
    public static boolean redo() 
    {
        boolean result = !redoOperation.empty();
        if (result) {
            IUndoable c = redoOperation.pop();
            undoOperation.push(c);
            c.redo();
        }
        return result;
    }
// print function
    public static void printOperationHistory() 
    {
        System.out.println("Operation History");
        System.out.println("Undo Stack");
        System.out.println(undoOperation);
        System.out.println("Operation History");
        System.out.println(redoOperation);


    }
}
