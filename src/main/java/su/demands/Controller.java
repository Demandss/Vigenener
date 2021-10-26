package su.demands;

public class Controller {
    private final int bias, letters;

    /**
     * Определяет алфавит шифрования
     * смещение определяется если привести первую букву алфавита к int в одинарных кавычках
     *
     * @param bias - смещение по первому символу алфавита
     * @param letters - количество букв алфавита
     */
    public Controller(final int bias, final int letters) {
        this.bias = bias;
        this.letters = letters;
    }

    /**
     * Шифрует текст по ключу методом Виженера
     *
     * @param text - текс по котору зашифровать
     * @param key - ключ шифрования
     * @return - зашифрованный текст
     */
    public String encrypt(final String text, final String key) {
        String encrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * this.bias) % this.letters) + this.bias);
        }
        return encrypt;
    }

    /**
     * Дешифрует текст по ключу методом Виженера
     *
     * @param cipher - зашифрованный текст
     * @param key - ключ шифрования
     * @return - дешифрованный текст
     */
    public String decrypt(final String cipher, final String key) {
        String decrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = cipher.length(); i < len; i++) {
            decrypt += (char) (((cipher.charAt(i) - key.charAt(i % keyLen) + this.letters) % this.letters) + this.bias);
        }
        return decrypt;
    }
}
