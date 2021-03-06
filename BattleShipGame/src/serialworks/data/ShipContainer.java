/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serialworks.data;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import serialworks.app.build.field.ShipOptionsPanel;
import serialworks.ship.Aircraftcarrier;
import serialworks.ship.Battleship;
import serialworks.ship.Frigate;
import serialworks.ship.Minesweeper;
import serialworks.ship.Ship;
import serialworks.ship.ShipType;
import serialworks.ship.Submarine;
import serialworks.visual.Map;

/**
 *
 * @author bastian
 */
public class ShipContainer {

    private Map map;
    private Ship[] ships;
    private int currentPos;
    private ShipType currentShipType;
    private ShipOptionsPanel shipOptionsPanel;

    public ShipContainer(ShipOptionsPanel shipOptionsPanel) {

        this.shipOptionsPanel = shipOptionsPanel;

        ships = new Ship[5];
        map = new Map(this, 10, 10);
        currentPos = -1;

        loadShips();
    }

    public Map getMap() {
        return map;
    }

    private void loadShips() {
        ships[0] = new Aircraftcarrier();
        ships[1] = new Battleship();
        ships[2] = new Frigate();
        ships[3] = new Minesweeper();
        ships[4] = new Submarine();
    }

    public ImageIcon[] getImageShips() {

        ImageIcon[] res = new ImageIcon[5];

        for (int i = 0; i < ships.length; i++) {
            res[i] = ships[i].getImage();
        }

        return res;
    }

    public void updateSelectedShip(ShipType shipType) {
        String shipName = shipType.name();
        currentShipType = shipType;

        if (shipType.equals(ShipType.AIRCRAFTCARRIER)) {
            System.out.println("Aircraf was selected and will be sended to Map");
            map.setShipSize(ships[0].getSize());
            currentPos = 0;
        } else if (shipName.equals(ShipType.BATTLESHIP.toString())) {
            currentPos = 1;
            map.setShipSize(ships[1].getSize());

        } else if (shipName.equals(ShipType.FRIGATE.toString())) {
            currentPos = 2;
            map.setShipSize(ships[2].getSize());

        } else if (shipName.equals(ShipType.MINESWEEPER.toString())) {
            currentPos = 3;
            map.setShipSize(ships[3].getSize());

        } else if (shipName.equals(ShipType.SUBMARINE.toString())) {
            currentPos = 4;
            map.setShipSize(ships[4].getSize());
        }
    }

    public void setPosSomeShip(ArrayList<Dimension> d) {
        Dimension[] aux = new Dimension[d.size()];
        d.toArray(aux);

        System.out.println("Colocando posiciones a los barcos: ");

        for (int i = 0; i < aux.length; i++) {
            System.out.print("fil: " + aux[i].getWidth() + ", col: " + aux[i].getHeight());
        }

        ships[currentPos].setPositions(aux);
        shipOptionsPanel.disableOption(currentShipType, currentPos);
    }

    public Ship[] getShips() {
        return ships;
    }
}
