package Recommender;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//Weather
    public class Today {
        private volatile static Today today;
        private boolean isEatingTime;
        private boolean isSpecial;

        public static Today getInstance(){
            if(today==null){
                synchronized (Today.class){
                    if(today==null){
                        today = new Today();
                    }
                }
            }
            return today;
        }

        private Today(){
            //to be done
            //badWeather = isBadWeather(wea);
            //this.weather = wea;
            isEatingTime = eatingTimeCalc();
            isSpecial = specialDayCalc();
        }

        private String getWeekday(){
            Date today = new Date();
            //SimpleDateFormat ft = new SimpleDateFormat("E yyy.MM.dd 'at' hh:mm:ss a zzz");
            SimpleDateFormat ft = new SimpleDateFormat("E", Locale.ENGLISH);
            //String weekdayEnd = ft.format(new Date());
            return ft.format(today);
        }

        /*
        private String getTime(){
            SimpleDateFormat ft = new SimpleDateFormat("HH");
            return ft.format(new Date());
        }
        */

        private boolean eatingTimeCalc(){
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

            if(hour>=12&&hour<=14 || hour>=17&&hour<=20){
                return true;
            }

            return false;
        }

        private boolean specialDayCalc(){
            String date = getWeekday();
            if(date.charAt(0)=='S'|| date.charAt(0)=='F')
                return true;
            return false;
        }

        public boolean isEatingTime(){
            return isEatingTime;
        }

        public boolean isSpecial(){
            return isSpecial;
        }

    }


