#All required info
bridge_span = 100
bridge_height = 5
percent_load = 1
train_length = 100
wheel_diatribution_train = [5,45,55,95]
force_from_wheel = 50




num_panel_points = bridge_span // bridge_height
span_panel_points = bridge_span / num_panel_points

#finds pins affected by live load
def find_pins (wheel_distribution_train, percent_load, bridge_span, train_length): 
    
    affected_pins = []
    amount_affected = []
    wheel_location = [] 
    
    #where front of train is
    front_train = (1-percent_load) * bridge_span
    
    #number of wheels is equal to number of distributed loads
    for i in range(len(wheel_distribution_train)):
        
        #checking if wheel is actually in the bridge
        if (front_train + wheel_distribution_train[i]) <= bridge_span:
            wheel_location.append(front_train + wheel_distribution_train[i])
            
            affected_pins.append(int(((wheel_location[i]/bridge_span) * num_panel_points)))
            affected_pins.append(int(((wheel_location[i]/bridge_span) * num_panel_points)) + 1)
            
            pin_affected_amount = 1 - (((wheel_location[i]/bridge_span) * num_panel_points)-int(((wheel_location[i]/bridge_span) * num_panel_points)))
            amount_affected.append(pin_affected_amount)
            amount_affected.append(1 -pin_affected_amount)
    
    
    print (amount_affected)
    return affected_pins

print(find_pins(wheel_diatribution_train, percent_load, bridge_span, train_length))


        