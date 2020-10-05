package sample;
    /*
        Class for working with time intervals. It is needed to calculate how much time has passed between actions.
    */
public class TimeInterval {

    private long lastJoinDate = 0;
    private String accDate = null;

    public long getTimeIntervalAuto (String accountDateKey) {
        accDate = new PetsAccount().getValue(accountDateKey);
        lastJoinDate = Long.valueOf(new PetsAccount().getValue("date_last_join")); // time of last closing of the program window
        long accountDate =  Long.valueOf(accDate);
        long currentDate = System.currentTimeMillis();
        long different = (currentDate - accountDate)-(lastJoinDate-accountDate);
        long dif = different/60000;

        return dif;
    }

        public long getTimeIntervalForGeneral (String accountDateKey) {
            accDate = new PetsAccount().getValue(accountDateKey);
            lastJoinDate = Long.valueOf(new PetsAccount().getValue("date_last_join"));
            long accountDate =  Long.valueOf(accDate);
            long currentDate = System.currentTimeMillis();
            long different = currentDate-accountDate;
            long dif = different/60000;
            return dif;
        }
}
