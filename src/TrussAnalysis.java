import java.util.ArrayList;

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
        wheelDistribution.add(55.0);
        wheelDistribution.add(95.0);

        int numPanelPoints = (int) (bridge_span /bridge_height);
        double spanPanelPoints = bridge_span / numPanelPoints;

        bridge.setPanelPoints(numPanelPoints);
        bridge.setPanelPointSpan(spanPanelPoints);
        bridge.setHeight(bridge_height);
        bridge.setLength(bridge_span);

        train.setLength(train_length);

        for (int i = 0; i < (2*numPanelPoints) - 2; i++) {
            Pin pin = new Pin();
            bridge.getBridgePins().add(pin);
        }

        findPins (wheelDistribution, percent_load);



    }



    //finds pins affected by live load
    public static void findPins (ArrayList<Double> wheelDistribution, int percent_load) {

//        ArrayList affected_pins = []
//        amount_affected = []
//        wheel_location = []
//
//         //where front of train is
//            front_train = (1-percent_load) * bridge_span
//
//    #number of wheels is equal to number of distributed loads
//    for i in range(len(wheel_distribution_train)):
//
//            #checking if wheel is actually on the bridge
//        if (front_train + wheel_distribution_train[i]) <= bridge_span:
//
//            #finding where the wheel actually is on the bridge
//            wheel_location.append(front_train + wheel_distribution_train[i])
//
//            #affected pins are calculated by finding which pins the wheel lies on or between
//            affected_pins.append(int(((wheel_location[i]/bridge_span) * num_panel_points)))
//            affected_pins.append(int(((wheel_location[i]/bridge_span) * num_panel_points)) + 1)
//
//            #finds ratio of force on each pin
//            force_ratio = 1 - (((wheel_location[i]/bridge_span) * num_panel_points)-int(((wheel_location[i]/bridge_span) * num_panel_points)))
//            amount_affected.append(force_ratio)
//            amount_affected.append(1 - force_ratio)
//
//    force_calculation(amount_affected, affected_pins)
//    return affected_pins

        int jointAffected = 0;
        double amountAffected = 0;
        double forceRatio = 0;

        double frontTrain = (1-percent_load) * bridge.getLength();
        for (int i = 0; i < wheelDistribution.size(); i++) {
            if (wheelDistribution.get(i) + frontTrain <= bridge.getLength()) {

                bridge.addWheelLocation(frontTrain + wheelDistribution.get(i));

                jointAffected = (int) (bridge.getWheelLocation().get(i)/bridge.getLength() * bridge.getPanelPoints());

                forceRatio = 1 - (((bridge.getWheelLocation().get(i)/bridge.getPanelPoints()) * bridge.getPanelPoints())
                        - (int)(((bridge.getWheelLocation().get(i)/bridge.getLength()) * bridge.getPanelPoints())));

                amountAffected = forceRatio * train.getForceFromWheel();

                System.out.println(i + " " + jointAffected);
                bridge.getBridgePins().get(jointAffected - 1).addForceOnPins(amountAffected);

            }

        }

        for (int j = 0; j < bridge.getBridgePins().size(); j++) {
        }

    }
}
