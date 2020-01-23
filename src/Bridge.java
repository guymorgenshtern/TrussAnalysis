import java.util.ArrayList;

public class Bridge {
    ArrayList<Pin> bridgePins = new ArrayList<Pin>();
    boolean topSupported;
    double length;
    double height;
    int panelPoints;

    public ArrayList<Pin> getBridgePins() {
        return bridgePins;
    }

    public void addBridgePins(Pin bridgePins) {
        this.bridgePins.add(bridgePins);
    }

    public boolean isTopSupported() {
        return topSupported;
    }

    public void setTopSupported(boolean topSupported) {
        this.topSupported = topSupported;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getPanelPoints() {
        return panelPoints;
    }

    public void setPanelPoints(int panelPoints) {
        this.panelPoints = panelPoints;
    }
}
