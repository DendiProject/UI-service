/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service.receipe;

import com.netcracker.ui.service.graf.component.Node;
import com.netcracker.ui.service.graf.component.NodesConnection;
import java.util.ArrayList;

/**
 * Представляет собой конкретный рецепт
 * @author Artem
 */
public class Receipe {
    public String receipesName;
    public ArrayList<Node> nodes;
    public ArrayList<NodesConnection> nodesConnections;
}
