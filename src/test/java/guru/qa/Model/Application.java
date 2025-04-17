package guru.qa.Model;

import java.util.List;

public class Application {

    private String kNIncomeConfirmationType;


    private String searchType;


    private String vipClientFlg;


    private String availableLimitDecrease;


    private List<Loans> currentLoans;

    private boolean enableKnAdditionalService;

    private int existProfileCardFlag;

    public String getkNIncomeConfirmationType() {
        return kNIncomeConfirmationType;
    }

    public String getSearchType() {
        return searchType;
    }

    public String getVipClientFlg() {
        return vipClientFlg;
    }

    public String getAvailableLimitDecrease() {
        return availableLimitDecrease;
    }

    public List<Loans> getCurrentLoans() {
        return currentLoans;
    }

    public boolean isEnableKnAdditionalService() {
        return enableKnAdditionalService;
    }

    public int getExistProfileCardFlag() {
        return existProfileCardFlag;
    }

}
