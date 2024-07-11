package lk.SMP.DAO;

import lk.SMP.DAO.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getDAOFactory() {
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOType {
        CUSTOMER,SUPPLIER,ORDER,USER,EMPLOYEE,HARVEST,ORDER_DETAIL,SALARY,STOCK,
    }
    public SuperDAO getDAO(DAOType types) {
        switch (types){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case USER:
                return new UserDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case HARVEST:
                return new HarvestDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
