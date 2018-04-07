/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.graf.component;

import com.vaadin.shared.ui.JavaScriptComponentState;
import java.util.ArrayList;

/**
 *
 * @author Artem
 */
public class GrafState extends JavaScriptComponentState{
    public String event;
    public int nodesId;
    public ArrayList<Node> nodes;
    public ArrayList<Edge> edges;
}
