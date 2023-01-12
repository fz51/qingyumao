package cn.qingyumao.security;

public class UsrId {
    private final String id;

    private final String space;

    private UsrId(String userId, String userSpace) {
        this.id = userId;
        this.space = userSpace;
    }

    public static UsrId of(String externalId) {
        if (externalId.contains(":")) {
            throw new IllegalStateException("externalId 不合法");
        }
        String[] s = externalId.split(":");
        return new UsrId(s[1], s[0]);
    }

    public static UsrId of(String id, String userSpace) {
        return new UsrId(id, userSpace);
    }

    public String getSpace() {
        return this.space;
    }

    /**
     * 提供对外
     *
     * @return
     */
    public String externalId() {
        return this.space + ":" + this.id;
    }
}
