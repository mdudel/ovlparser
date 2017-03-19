/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import ovl.schema.gccs.ArcType;
import ovl.schema.gccs.ArrowType;
import ovl.schema.gccs.BoxType;
import ovl.schema.gccs.CircleType;
import ovl.schema.gccs.CorridorType;
import ovl.schema.gccs.EllipseType;
import ovl.schema.gccs.EllipticalArcType;
import ovl.schema.gccs.MilbobjectType;
import ovl.schema.gccs.PolygonType;
import ovl.schema.gccs.RectangleType;
import ovl.schema.gccs.SectorType;
import ovl.schema.gccs.SymbolType;
import ovl.schema.gccs.TacsymbolType;
import ovl.schema.gccs.TextType;
import ovl.schema.gccs.UnitType;

/**
 *
 * @author marty
 */
public interface OvlGraphicVisitor {

    public void visit(CircleType circle);

    public void visit(EllipseType ellipse);

    public void visit(RectangleType rectangle);

    public void visit(BoxType box);

    public void visit(ArcType arc);

    public void visit(SectorType arc);

    public void visit(ArrowType arrow);

    public void visit(EllipticalArcType elliArc);

    public void visit(PolygonType polygon);

    public void visit(CorridorType corridor);

    public void visit(MilbobjectType milbobject);

    public void visit(SymbolType symbol);

    public void visit(TacsymbolType tacsymbol);

    public void visit(UnitType unit);

    public void visit(TextType text);
}
