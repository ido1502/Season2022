package frc.robot.utils;

public class PID {
    private double KP, KI, KD;
    private double last_error, sum_error, error;
    private double PID;
    private double setPoint;
    
    public PID(double KP, double KI, double KD){
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
    }
    
    public void setPoint(double setPoint){
        this.setPoint = setPoint;
        sum_error = 0;
        last_error = 0;
        error = 0;
    }

    public double calculate(double current){
        error = setPoint - current;
        sum_error += error;
        PID = (error * KP) + (sum_error * KI) + ((last_error-error)*KD);
        last_error = error;
        return Math.min(Math.max(PID,-1),1);// between -1 to 1
    }
}
