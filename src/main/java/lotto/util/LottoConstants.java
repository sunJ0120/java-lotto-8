package lotto.util;

public class LottoConstants {
    public static final String ERROR_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1 ~ 45까지의 범위만 허용합니다. : %d";
    public static final String ERROR_NUMBER_DUPLICATE = "[ERROR] 로또 번호에 중복된 숫자가 있습니다.";
    public static final String ERROR_INVALID_SIZE = "[ERROR] 로또 번호에 중복된 숫자가 있습니다.";
    public static final String ERROR_NOT_NUMBER = "[ERROR] '%s'는 숫자가 아닙니다.";
    public static final String ERROR_INVALID_PURCHASE_UNIT = "[ERROR] 구입 금액은 1,000원 단위여야 합니다.";

    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int LOTTO_SIZE = 6;
    public static final int UNIT = 1000;
}
