package indianrail.robosoft.com.indianrail.model;

import java.util.ArrayList;

/**
 * Created by deena on 9/5/16.
 */
public class PnrStatus {
    private String train_name;
    private String from_station;
    private String to_station;
    private String chart_prepared;
    private String doj;
    private ArrayList<Passengers> passengersArrayList;

    class Passengers {
        private String no, booking_status, current_status, coach_position;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getBooking_status() {
            return booking_status;
        }

        public void setBooking_status(String booking_status) {
            this.booking_status = booking_status;
        }

        public String getCurrent_status() {
            return current_status;
        }

        public void setCurrent_status(String current_status) {
            this.current_status = current_status;
        }

        public String getCoach_position() {
            return coach_position;
        }

        public void setCoach_position(String coach_position) {
            this.coach_position = coach_position;
        }
    }

    public ArrayList<Passengers> getPassengersArrayList() {
        return passengersArrayList;
    }

    public void setPassengersArrayList(ArrayList<Passengers> passengersArrayList) {
        this.passengersArrayList = passengersArrayList;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getFrom_station() {
        return from_station;
    }

    public void setFrom_station(String from_station) {
        this.from_station = from_station;
    }

    public String getTo_station() {
        return to_station;
    }

    public void setTo_station(String to_station) {
        this.to_station = to_station;
    }

    public String getChart_prepared() {
        return chart_prepared;
    }

    public void setChart_prepared(String chart_prepared) {
        this.chart_prepared = chart_prepared;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }
}
