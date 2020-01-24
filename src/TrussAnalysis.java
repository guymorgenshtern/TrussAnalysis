import java.util.ArrayList;
import java.math.*;

public class TrussAnalysis {

    static Bridge bridge = new Bridge();
    static Train train = new Train();

    public static void main (String[] args) {
        double bridge_span = 100;
        double bridge_height = 5;
        int percent_load = 1;
        double train_length = 100;
        ArrayList<Double> wheelDistribution = new ArrayList<Double>();
        double force_from_wheel = 50;

        wheelDistribution.add(5.0);
        wheelDistribution.add(45.0);
        wheelDistribution.add(53.0);
        wheelDistribution.add(95.0);

        int numPanelPoints = (int) (bridge_span /bridge_height);
        double spanPanelPoints = bridge_span / numPanelPoints;

        bridge.setPanelPoints(numPanelPoints);
        bridge.setPanelPointSpan(spanPanelPoints);
        bridge.setHeight(bridge_height);
        bridge.setLength(bridge_span);

        train.setLength(train_length);
        train.setForceFromWheel(force_from_wheel);

        for (int i = 0; i < (2*numPanelPoints) - 2; i++) {
            Pin pin = new Pin();
            bridge.getBridgePins().add(pin);
        }

        findPins (wheelDistribution, percent_load);

    }

    //finds pins affected by live load
    public static void findPins (ArrayList<Double> wheelDistribution, int percent_load) {

        int jointAffected = 0;
        double amountAffected = 0;
        double forceRatio;

        double frontTrain = (1-percent_load) * bridge.getLength();
        for (int i = 0; i < wheelDistribution.size(); i++) {
            if (wheelDistribution.get(i) + frontTrain <= bridge.getLength()) {

                bridge.addWheelLocation(frontTrain + wheelDistribution.get(i));

                jointAffected = (int) (bridge.getWheelLocation().get(i)/bridge.getLength() * bridge.getPanelPoints());

                forceRatio = 1.0 - (((bridge.getWheelLocation().get(i)/bridge.getLength()) * bridge.getPanelPoints())
                        - Math.floor((((bridge.getWheelLocation().get(i)/bridge.getLength()) * bridge.getPanelPoints()))));


                amountAffected = forceRatio * train.getForceFromWheel();

                bridge.getBridgePins().get(jointAffected - 1).addForceOnPins(amountAffected);
               System.out.println(bridge.getBridgePins().get(jointAffected - 1).getForceOnPins());

               if (forceRatio < 1) {
                   amountAffected = forceRatio * train.getForceFromWheel();
                   bridge.getBridgePins().get(jointAffected).addForceOnPins(amountAffected);
               }

            }

        }

    }
}
