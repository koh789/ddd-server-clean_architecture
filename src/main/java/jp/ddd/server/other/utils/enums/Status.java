package jp.ddd.server.other.utils.enums;

public enum Status {

    INVALID((byte) 0, "invalid"),
    VALID((byte) 1, "valid");

    private byte code;

    private String value;

    private Status(byte code, String value) {
        this.code = code;
        this.value = value;
    }

    public byte getCode() {
        return code;
    }

    public String getValue() {
        return this.value;
    }

    public static Status get(byte code) {
        for (Status deleted : values()) {
            if (deleted.getCode() == code) {
                return deleted;
            }
        }
        return null;
    }
}
