import java.util.ArrayList;

class Pin extends Bridge {
    ArrayList<Pin> attachedPins = new ArrayList<Pin>();
    ArrayList<Double> forceOnPins = new ArrayList<Double>();

    public ArrayList<Pin> getAttachedPins() {
        return attachedPins;
    }

    public void addAttachedPins(Pin attachedPins) {
        this.attachedPins.add(attachedPins);
    }

    public ArrayList<Double> getForceOnPins() {
        return forceOnPins;
    }

    public void addForceOnPins(Double forceOnPins) {
        this.forceOnPins.add(forceOnPins);
    }
}
