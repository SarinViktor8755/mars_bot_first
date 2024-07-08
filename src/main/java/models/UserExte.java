package models;

import com.pengrad.telegrambot.model.User;

public class UserExte extends com.pengrad.telegrambot.model.User {
    private Boolean is_bot;
    private String first_name;
    private String last_name;
    private String username;
    private String language_code;
    private Boolean is_premium;
    private Boolean added_to_attachment_menu;
    private Boolean can_join_groups;
    private Boolean can_read_all_group_messages;
    private Boolean supports_inline_queries;
    private Boolean can_connect_to_business;

    public UserExte(Long id) {
        super(id);
        this.km = 0;
    }

    public UserExte(Long id, User user) {
        super(id);
        this.km = 0;
        //this.id;
        this.is_bot = user.isBot();
        this.first_name = user.firstName();
        this.last_name = user.lastName();
        this.username = user.username();
        this.language_code = user.languageCode();
        this.is_premium = user.isPremium();
        this.added_to_attachment_menu = user.addedToAttachmentMenu();
        this.can_join_groups = user.canJoinGroups();
        this.can_read_all_group_messages = user.canReadAllGroupMessages();
        this.supports_inline_queries = user.supportsInlineQueries();
        this.can_connect_to_business = user.canConnectToBusiness();
    }

    private int km;

    public void add_km(int add_km) {
        this.km += add_km;

    }


    @Override
    public String toString() {
        return "UserExte{" +
                "is_bot=" + is_bot +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", language_code='" + language_code + '\'' +
                ", is_premium=" + is_premium +
                ", added_to_attachment_menu=" + added_to_attachment_menu +
                ", can_join_groups=" + can_join_groups +
                ", can_read_all_group_messages=" + can_read_all_group_messages +
                ", supports_inline_queries=" + supports_inline_queries +
                ", can_connect_to_business=" + can_connect_to_business +
                ", km=" + km +
                "} " ;
    }
}
