import Search.Controller.SnappFoodCtrl;

import java.util.ArrayList;

/**
 * Created by moein on 7/4/2018.
 */
public class main
{
    public static void main(String[] args) {
        SnappFoodCtrl snappFoodCtrl = new SnappFoodCtrl();
        ArrayList<String> proIds = new ArrayList<>();
        proIds.add("3373871");
        proIds.add("3373881");
        proIds.add("3373871");
        snappFoodCtrl.addToBasket("37qnm0", proIds, "https://snappfood.ir/order/cart/update/");
    }
}
