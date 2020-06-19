package model;

import model.interfaces.IlistShapeObserver;
import model.interfaces.IlistShapeSubject;
import view.interfaces.IDrawShape;
import view.interfaces.IMouseAdapterObserver;

import java.util.ArrayList;

public class listShape implements IlistShapeSubject 
{
    private final ArrayList<IDrawShape> internalShapesList;
    private final ArrayList<IlistShapeObserver> observers;
    private final ArrayList<IDrawShape> presentShapeList;
    private final ArrayList<IDrawShape> clipBoard;
// list shape method

    public listShape() 
    {
        internalShapesList = new ArrayList<IDrawShape>();
        observers = new ArrayList<IlistShapeObserver>();
        presentShapeList = new ArrayList<IDrawShape>();
        clipBoard = new ArrayList<IDrawShape>();

    }
// add the shape
    public void add(IDrawShape shapes)
    {
       
        internalShapesList.add(shapes);
        notifyObserver();
    }
// remove the shape
    public void remove(IDrawShape shape)
    {
        
        internalShapesList.remove(shape);
        notifyObserver();
    }

    public ArrayList<IDrawShape> getlistShape()
    {
        return internalShapesList;
    }

    @Override
    public void register(IlistShapeObserver observer) 
    {
        observers.add(observer);
    }


    @Override
    public void unsubscribe(IlistShapeObserver observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver()
{
        

        for (IlistShapeObserver newObserver : observers) 
        {
            newObserver.update();
        }
    }

// add list

    public void addSelectedList(IDrawShape shapes) 
    {
        presentShapeList.add(shapes);

    }
// remove list
    public void removeSelectedList()
    {
        presentShapeList.removeAll(presentShapeList);
        notifyObserver();

    }
// clear list
    public void clearSelectedlistShape()
    {
        presentShapeList.clear();
    }

    public ArrayList<IDrawShape> getpresentShapeList() 
    {
        return presentShapeList;
    }

    //Clipboard listShape

    public void addClipBoardShapes(IDrawShape shapes)
    {
        clipBoard.add(shapes);

    }
// remove clipboard
    public void removeClipBoardShapes()
    {
        clipBoard.removeAll(presentShapeList);
        notifyObserver();

    }
// clear function
    public void clearClipBoard() 
    {
        clipBoard.clear();
    }

    public ArrayList<IDrawShape> getClipBoard() 
    {
        return clipBoard;
    }

}