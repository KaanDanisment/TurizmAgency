package PatikaTurizmAgency.Model;

public class Price {

    public static int calculate(Hotel hotel, String roomType,int days,String pensionType, int roomCount,int adultCount, int childCount){
        int firstPrice = 0;
        int price = 0;
        if (roomType.equals("single")){
            firstPrice = ((hotel.getNightlyAdultPrice() * adultCount) + (hotel.getNightlyChildrenPrice() * childCount)) * days;
        } else if (roomType.equals("double")) {
            firstPrice = (((hotel.getNightlyAdultPrice() * adultCount) + (hotel.getNightlyChildrenPrice() * childCount)) * days) * 2;
        } else if (roomType.equals("suit")) {
            firstPrice = (((hotel.getNightlyAdultPrice() * adultCount) + (hotel.getNightlyChildrenPrice() * childCount)) * days) * 3;
        }

        if (pensionType.equals("Ultra All Inclusive")){
            price =  firstPrice * 6;
        } else if (pensionType.equals("All Inclusive")) {
            price = firstPrice * 5;
        } else if (pensionType.equals("Full Pension")) {
            price = firstPrice * 4;
        } else if (pensionType.equals("Half Pension")) {
            price = firstPrice * 3;
        } else if (pensionType.equals("Room Breakfest")) {
            price = firstPrice * 2;
        } else if (pensionType.equals("Just Bed")) {
            price = firstPrice;
        }
        return price * roomCount;
    }
}
