package ua.nure.zhazhky.SummaryTask4.exceptions;

public class Messages {

    //Database
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Can't obtain data source from a content file!";
    public static final String ERR_CANNOT_GET_CONNECTION_MESSAGE = "Can't connect to the database!";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Can't close database connection!";
    public static final String ERR_FAILED_TO_MAKE_CHANGES = "Can't resolve transaction!";
    public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Can't rollback the transaction!";

    //User
    public static final String MAYBE_YOUR_ROBOT_MESSAGE = "You need to check captha!";
    public static final String ERR_CANNOT_GET_USER_BY_LOGIN_AND_PASSWORD = "Can't find user by login and password";
    public static final String ERR_CANNOT_CREATE_USER = "Can' create user! Login must be unique.";
    public static final String USER_NOT_EXIST_MESSAGE = "Input data doesn't match to any user!";
    public static final String ERR_CANNOT_GET_USERS = "Can't get all users!";
    public static final String ERR_CANNOT_UPDATE_USER = "Can't update user!";
    public static final String ERR_CANNOT_BLOCK_OR_UNBLOCK_USER = "Can't block user!";
    public static final String ERR_USER_IS_BLOCKED = "Your account is blocked, we can't allow you to come in.";

    //Account
    public static final String ERR_CANNOT_CREATE_ACCOUNT = "Can't create account! Account id must be unique.";
    public static final String ERR_CANNOT_GET_ACCOUNTS_BY_USER = "Can't get accounts by user!";
    public static final String ERR_CANNOT_GET_ACCOUNT_BY_ID = "Can't get account by id!";
    public static final String ERR_CANNOT_GET_BALANCE_BY_ACCOUNT_ID = "Can't get account's balance!";
    public static final String ERR_CANNOT_BLOCK_ACCOUNT = "Can't block account!";
    public static final String ERR_CANNOT_UNBLOCK_ACCOUNT = "Can't unblock account!";

    //CreditCard
    public static final String ERR_CANNOT_GET_CARDS_BY_ACCOUNT = "Can't get cards by account!";
    public static final String ERR_CANNOT_CREATE_CARD = "Can't create credit card!";

    //Payment
    public static final String ERR_CANNOT_CREATE_PAYMENT =
            "Can't deliver payment! Check if entered data is correct and you have enough finances on your account.";
    public static final String ERR_NOT_ENOUGH_BALANCE = "You have not enough balance for this payment!";
    public static final String ERR_CANNOT_UPDATE_PAYMENT_AND_ACCOUNTS =
            "Can't update payment!  status and account's balance!";
    public static final String ERR_RECEIVER_NOT_EXIST = "Account with such id does not exist!";
    public static final String ERR_CANNOT_GET_PAYMENTS_BY_USER = "Can't get payments by user!";
    public static final String ERR_CANNOT_DELETE_PAYMENTS = "Can't delete prepared payments, maybe they're not exist.";
}
