package lk.SMP.bo;

import lk.SMP.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null)? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        SUPPLIER, USER, HARVEST, CUSTOMER, ORDER, PLACEORDER, EMPLOYEE
    }
    public SuperBO getBO(BOTypes types) {
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case USER:
                return new UserBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case HARVEST:
                return new HarvestBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
