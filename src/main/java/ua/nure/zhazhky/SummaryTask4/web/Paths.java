package ua.nure.zhazhky.SummaryTask4.web;

public final class Paths {



    private Paths() {
    }

    //html
    public static final String AUTHENTICATE_PAGE = "html/authenticate.html";
    public static final String REGISTRATION_PAGE = "html/registration.html";

    //jspCommon
    public static final String ERROR_PAGE = "WEB-INF/jsp/error.jsp";

    //userJsp
    public static final String CABINET_PAGE = "WEB-INF/jsp/user/cabinet.jsp";
    public static final String UPDATE_USER_PAGE = "WEB-INF/jsp/user/updateUser.jsp";
    public static final String CREATE_ACCOUNT_PAGE = "WEB-INF/jsp/user/accounts/createAccount.jsp";
    public static final String CREATE_CARD_PAGE = "WEB-INF/jsp/user/cards/createCard.jsp";
    public static final String CREATE_PAYMENT_PAGE = "WEB-INF/jsp/user/payments/createPayment.jsp";
    public static final String PROFILE_PAGE = "WEB-INF/jsp/user/profile.jsp";
    public static final String ADD_CREDIT_PAGE = "WEB-INF/jsp/user/accounts/addCredit.jsp";
    public static final String PAYMENT_CONFIRM_PAGE = "WEB-INF/jsp/user/payments/paymentConfirm.jsp";
    public static final String PAYMENT_MANAGEMENT_PAGE = "WEB-INF/jsp/user/payments/paymentManagement.jsp";
    public static final String BLOCK_ACCOUNT_PAGE = "WEB-INF/jsp/user/accounts/blockAccount.jsp";
    public static final String UNBLOCK_ACCOUNT_REQUEST_PAGE = "WEB-INF/jsp/user/accounts/unblockAccountRequest.jsp";
    public static final String DELETE_UNSENT_PAYMENTS_PAGE = "WEB-INF/jsp/user/payments/deleteUnsentPayments.jsp";

    //adminJsp
    public static final String ADMIN_CABINET_PAGE = "WEB-INF/jsp/admin/adminCabinet.jsp";
    public static final String UNBLOCK_ACCOUNT_PAGE = "WEB-INF/jsp/admin/unblockAccount.jsp";
    public static final String BLOCK_USER_PAGE = "WEB-INF/jsp/admin/blockUser.jsp";
    public static final String UNBLOCK_USER_PAGE = "WEB-INF/jsp/admin/unblockUser.jsp";

    //servlets
    public static final String ERROR_SERVLET = "error";
    public static final String AUTHENTICATE_SERVLET = "authenticate";
    public static final String CABINET_SERVLET = "cabinet";
    public static final String REGISTRATION_SERVLET = "registration";
    public static final String CREATE_ACCOUNT_SERVLET = "createAccount";
    public static final String CREATE_CARD_SERVLET = "createCard";
    public static final String PROFILE_SERVLET = "profile";
    public static final String PAYMENT_CONFIRM_SERVLET = "paymentConfirm";
    public static final String PAYMENT_MANAGEMENT_SERVLET = "paymentManagement";
    public static final String ADMIN_CABINET_SERVLET = "adminCabinet";
}