package sample;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
    /*
     * This class implements the ability to take into account the length of time without attention to the pet, even with the program / PC turned off.
     * Implemented by subtracting a certain number of lives and "hobby" points, in accordance with the time interval,
     * between when was the last data update (food, game) and the current time on the local machine.
     *
     * Subtraction of points is made according to the scheme: 1 minute = 5% of the points of the "health" and "hobby" scale.
     * Thus, complete exhaustion of the pet without replenishing the health = 20 minutes.
     */
public class ScaleEatAndPlay {
    Timer timer = new Timer();

    TimeInterval timeInterval = new TimeInterval();
    private long interval = 0;
    void eat() {

        try {
            interval = timeInterval.getTimeIntervalAuto("last_time_health_change_StartEra");
        } catch (Exception e) {
            System.out.println("Error 0005x1");
        }
        while (JoinPet.statusEat>0.0 && interval>0 ){
            interval--;
            new JoinPet().setEat(0.05, "minus");
        }
        timer.schedule(new EatTimer(), 60000, 60000);
    }

    void hobbie() {
        try {
            interval = timeInterval.getTimeIntervalAuto("last_time_mood_change_StartEra");
        } catch (Exception e) {
            System.out.println("Error 0005x2");
        }
        while (JoinPet.statusHobb>0.0 && interval>0 ){
            interval--;
            new JoinPet().setHobbie(0.05, "minus");
        }
            timer.schedule(new HobbieTimer(), 60000, 60000);
    }
}

class HobbieTimer extends TimerTask {
    @Override
    public void run(){
        try {
            if (JoinPet.statusHobb>0.1) {new JoinPet().setHobbie(0.05,"minus");}
            else{this.cancel();
                JOptionPane.showMessageDialog(null, "Your pet misses you", "Somebody!",1);
            }
        } catch (Exception e) { }
    }
}

class EatTimer extends TimerTask {
    @Override
    public void run(){
        try {
            if (JoinPet.statusEat>0.05) {new JoinPet().setEat(0.05,"minus"); }
            else{this.cancel();}
        } catch (Exception e) { }
    }
}
