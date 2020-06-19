package controller;

import interfaces.IUndoable;
import model.interfaces.IlistShapeSubject;
import view.interfaces.IDrawShape;

import java.util.ArrayList;
// create the clearalloperation class and implement ioperation and iundoable.
public class ClearAllOperation implements IOperation, IUndoable 
{
    private IlistShapeSubject listShape;
    // create the arraylist
    private final ArrayList<IDrawShape> allShape = new ArrayList<IDrawShape>();

    public ClearAllOperation(IlistShapeSubject listShape) 
    {
        this.listShape = listShape;
    }
// create the run function
    @Override
    public void run() 
    {
        for (IDrawShape shape : listShape.getlistShape())
        {
            allShape.add(shape);
        }
        listShape.clearSelectedlistShape();
        listShape.getClipBoard().clear();
        listShape.getlistShape().clear();
        listShape.notifyObserver();
        OperationHistory.add(this);
    }

   // Create the undo operation
    @Override
    public void undo() 
    {
        for (IDrawShape shape : listShape.getlistShape()) 
        {
            listShape.add(shape);
            listShape.notifyObserver();
        }
    }
// create the redo operation
    @Override
    public void redo()
    {
        listShape.clearSelectedlistShape();
        listShape.getClipBoard().clear();
        listShape.getlistShape().clear();
    }
}
