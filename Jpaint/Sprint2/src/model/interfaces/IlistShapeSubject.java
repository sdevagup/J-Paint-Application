package model.interfaces;

import view.interfaces.IDrawShape;

import java.util.ArrayList;

public interface IlistShapeSubject
{
    void add(IDrawShape shape);

    void remove(IDrawShape shape);
    

    ArrayList<IDrawShape> getlistShape();
    

    void register(IlistShapeObserver paintCanvas);

    void unsubscribe(IlistShapeObserver paintCanvas);

    void notifyObserver();

    void addSelectedList(IDrawShape shapes);
    

    ArrayList<IDrawShape> getpresentShapeList();
    

    void removeSelectedList();

    void clearSelectedlistShape();

    void addClipBoardShapes(IDrawShape shapes);

    void removeClipBoardShapes();

    void clearClipBoard();
    

    ArrayList<IDrawShape> getClipBoard();
}
